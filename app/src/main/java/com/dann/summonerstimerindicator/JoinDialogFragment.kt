package com.dann.summonerstimerindicator

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dann.summonerstimerindicator.databinding.FragmentDialogJoinBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class JoinDialogFragment(
    private val onSubmitClickListener: (String) -> Unit
) : DialogFragment() {

    private var _binding: FragmentDialogJoinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogJoinBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)

        isCancelable = false

        binding.okButton.setOnClickListener {
            val code = binding.codeET.text.toString()
            if (code.isNotEmpty()) {
                if (code.length > 5) {
                    hideKeyboard()
                    binding.codeLayout.visibility = View.GONE
                    binding.lltLoading.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({
                        okCode(code)
                    }, 1000)
                } else {
                    Toast.makeText(context, "The code must have 6 characters!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(context, "Unfilled fields!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    private fun hideKeyboard() {
        val keyboard: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.codeET.windowToken, 0)
    }

    private fun showKeyboard() {
        val keyboard: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.showSoftInput(binding.codeET, 0)
    }

    private fun okCode(code: String) {
        val database = Firebase.database
        val myRef = database.getReference(code)
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value != null) {
                    val key = snapshot.key!!
                    onSubmitClickListener(key)
                    dismiss()
                } else {
                    Toast.makeText(context, "Game not found!", Toast.LENGTH_SHORT).show()
                    binding.codeLayout.visibility = View.VISIBLE
                    binding.lltLoading.visibility = View.GONE
                    showKeyboard()
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}
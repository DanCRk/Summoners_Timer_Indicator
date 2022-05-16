package com.dann.summonerstimerindicator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dann.summonerstimerindicator.databinding.FragmentDialogDeleteBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class DeleteDialogFragment(
    private val code: String,
    private val host: Boolean,
    private val onSubmitClickListener: (Boolean) -> Unit
) : DialogFragment() {

    private var _binding: FragmentDialogDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogDeleteBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)

        isCancelable = false

        binding.cancelButton.setOnClickListener { dismiss() }

        binding.okButton.setOnClickListener {
            binding.codeLayout.visibility = View.GONE
            binding.lltLoading.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                if (host) {
                    deleteGame()
                } else {
                    onSubmitClickListener(true)
                    dismiss()
                }
            }, 1500)
        }

        return binding.root
    }

    private fun deleteGame() {
        val database = Firebase.database
        val myRef = database.getReference(code)
        myRef.removeValue().addOnCompleteListener {
            if (it.isSuccessful) {
                onSubmitClickListener(true)
                dismiss()
            } else {
                dismiss()
                Toast.makeText(
                    context,
                    "Can not delete the game, try again please",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
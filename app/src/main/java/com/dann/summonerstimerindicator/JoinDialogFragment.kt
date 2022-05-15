package com.dann.summonerstimerindicator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dann.summonerstimerindicator.databinding.FragmentDialogJoinBinding

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

        binding.okButton.setOnClickListener {
            val code = binding.codeET.text.toString()
            if (code.isNotEmpty()) okCode(code) else Toast.makeText(
                context,
                "Campos sin llenar!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    private fun okCode(code: String) {
        onSubmitClickListener(code)
        dismiss()
    }

}
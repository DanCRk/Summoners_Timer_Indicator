package com.dann.summonerstimerindicator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dann.summonerstimerindicator.databinding.FragmentDialogCreateBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CreateDialogFragment(
    private val onSubmitClickListener: (String) -> Unit
) : DialogFragment() {

    private var _binding: FragmentDialogCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogCreateBinding.inflate(layoutInflater)
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent)

        isCancelable = false

        Handler(Looper.getMainLooper()).postDelayed({
            createGame()
        }, 1000)


        return binding.root
    }

    private fun createGame() {
        val database = Firebase.database
        val randomCode = "123456"//randomNumber(100000..999999).toString()
        val myRef = database.getReference(randomCode)
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value != null) {
                    Toast.makeText(context, "Error, try again later", Toast.LENGTH_SHORT).show()
                    dismiss()
                } else {
                    myRef.child("top").child("sum1").setValue(true)
                    myRef.child("top").child("sum2").setValue(true)
                    myRef.child("jg").child("sum1").setValue(true)
                    myRef.child("jg").child("sum2").setValue(true)
                    myRef.child("mid").child("sum1").setValue(true)
                    myRef.child("mid").child("sum2").setValue(true)
                    myRef.child("adc").child("sum1").setValue(true)
                    myRef.child("adc").child("sum2").setValue(true)
                    myRef.child("sup").child("sum1").setValue(true)
                    myRef.child("sup").child("sum2").setValue(true)
                    onSubmitClickListener(randomCode)
                    dismiss()
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
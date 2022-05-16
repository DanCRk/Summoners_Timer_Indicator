package com.dann.summonerstimerindicator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dann.summonerstimerindicator.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private lateinit var code: String
    private var host:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        host = intent.extras?.getBoolean("host", false) ?: false
        setCode()

        binding.back.setOnClickListener{
            DeleteDialogFragment(onSubmitClickListener = { response ->
                if (response){
                    finish()
                }
            }, code = code, host = host).show(supportFragmentManager, "addDialogFragment")
        }
    }

    override fun onBackPressed() {
        DeleteDialogFragment(onSubmitClickListener = { response ->
            if (response){
                finish()
            }
        }, code = code, host = host).show(supportFragmentManager, "addDialogFragment")
    }

    private fun setCode() {
        code = intent.getStringExtra("code").toString()
        binding.textView.text = code
    }
}
package com.dann.summonerstimerindicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dann.summonerstimerindicator.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    private lateinit var code: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCode()
    }

    private fun setCode() {
        code = intent.getStringExtra("code").toString()
        binding.textView.text = code
    }
}
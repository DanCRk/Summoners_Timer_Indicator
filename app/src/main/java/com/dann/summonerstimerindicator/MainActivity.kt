package com.dann.summonerstimerindicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dann.summonerstimerindicator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.join.setOnClickListener{
            JoinDialogFragment(onSubmitClickListener = { code ->
                Toast.makeText(this, "$code", Toast.LENGTH_SHORT).show()
            }).show(supportFragmentManager, "addDialogFragment")
        }
    }
}
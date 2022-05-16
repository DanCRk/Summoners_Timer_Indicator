package com.dann.summonerstimerindicator

import android.content.Intent
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
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("code", code)
                intent.putExtra("host", false)
                startActivity(intent)
            }).show(supportFragmentManager, "addDialogFragment")
        }

        binding.create.setOnClickListener{
            CreateDialogFragment(onSubmitClickListener = { code ->
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("code", code)
                intent.putExtra("host", true)
                startActivity(intent)
            }).show(supportFragmentManager, "addDialogFragment")
        }
    }
}
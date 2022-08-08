package com.test.miniTiktok

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_main.*

class MainAct : AppCompatActivity(),View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        btn_dsj.setOnClickListener(this)
        btn_zy.setOnClickListener(this)
        btn_dy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_dsj -> {
                startActivity(Intent(this, DsjAct::class.java))
            }
            R.id.btn_zy -> {
                startActivity(Intent(this, ZyAct::class.java))
            }
            R.id.btn_dy -> {
                startActivity(Intent(this, DyAct::class.java))
            }
        }
    }
}
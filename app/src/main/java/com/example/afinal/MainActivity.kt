package com.example.afinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentManager
import fragment.tabFragment1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var tabFragment = tabFragment1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 액션바 대신 툴바를 사용하도록 설정
        setSupportActionBar(toolbar)


        var tran = supportFragmentManager.beginTransaction()
        tran.replace(R.id.fragment, tabFragment)
        tran.commit()
    }

}

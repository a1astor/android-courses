package com.example.homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val randomList = RandomGenerator().generateRandomList()
        OperationData.data = randomList
        addSubscriber()
        val intent = Intent(this, CalcActivity::class.java)
        startActivity(intent)
    }

    private fun addSubscriber() {
        val subscriber = object : ResultSubscriber {
            override fun call(result: Double) {
                println(result)
            }
        }
        OperationData.subscribers.add(subscriber)
    }
}


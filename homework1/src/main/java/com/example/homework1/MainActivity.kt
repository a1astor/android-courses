package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity() {
    val waterFilter: (String) -> String = {filter -> filter}

    val newVF: (String, Int, Double) ->Triple<String, Int, Double> = {first, second, third -> Triple(first,second,third)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }}
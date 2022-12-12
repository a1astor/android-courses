package com.example.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework2.operations.AverageOperationStrategy
import com.example.homework2.operations.ComplexOperationStrategy
import com.example.homework2.operations.SumOperationStrategy

class CalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data = OperationData.data
        val context: Context = Context(SumOperationStrategy())
        OperationData.sum = context.calculate(data)
        context.strategy = AverageOperationStrategy()
        OperationData.average = context.calculate(data)
        context.strategy = ComplexOperationStrategy()
        OperationData.complexResult = context.calculate(data)
    }
}
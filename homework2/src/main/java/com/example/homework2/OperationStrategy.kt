package com.example.homework2.operations

interface OperationStrategy {
    fun calculate(numbers: Set<Int>): Double
}

class SumOperationStrategy : OperationStrategy {
    override fun calculate(numbers: Set<Int>): Double = numbers.sum().toDouble()
}

class AverageOperationStrategy : OperationStrategy {
    override fun calculate(numbers: Set<Int>): Double = numbers.average()
}

class ComplexOperationStrategy : OperationStrategy {
    override fun calculate(numbers: Set<Int>): Double {
        val firstHalf = numbers.filterIndexed { index, i -> index <= numbers.size / 2 }
        val secondHalf = numbers.filterIndexed { index, i -> index > numbers.size / 2 }
        var substractionResult = 0
        secondHalf.forEach({ i -> substractionResult -= i })
        return firstHalf.sum().toDouble() / substractionResult
    }
}
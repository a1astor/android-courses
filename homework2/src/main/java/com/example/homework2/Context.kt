package com.example.homework2

import com.example.homework2.operations.OperationStrategy

class Context(var strategy: OperationStrategy) {

    fun calculate(collection: Set<Int>):Double = strategy.calculate(collection)
}
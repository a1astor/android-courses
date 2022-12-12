package com.example.homework2

object OperationData {
    var subscribers = ArrayList<ResultSubscriber>()

    lateinit var data: Set<Int>
    var sum: Double = Double.MIN_VALUE
        set(value) {
            field = value
            subscribers.forEach { subscriber -> subscriber.call(value) }
        }
    var average: Double = Double.MIN_VALUE
        set(value) {
            field = value
            subscribers.forEach { subscriber -> subscriber.call(value) }
        }

    var complexResult: Double = Double.MIN_VALUE
        set(value) {
            field = value
            subscribers.forEach { subscriber -> subscriber.call(value) }
        }
}

interface ResultSubscriber {
    fun call(result: Double)
}


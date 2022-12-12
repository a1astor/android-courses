package com.example.homework2

class RandomGenerator {
    fun generateRandomList(): Set<Int> {
        val randomSet = mutableSetOf<Int>()
        var length: Int = generateRandomIntValue()
        if (length % 2 == 0) {
            length += 1
        }

        for (i in 0..length) {
            addValueToSet(randomSet)
        }
        return randomSet
    }

    private fun addValueToSet(randomSet: MutableSet<Int>) {
        val randomValue = generateRandomIntValue()
        val result = randomSet.add(randomValue)
        if (!result) {
            addValueToSet(randomSet)
        }
    }

    private fun generateRandomIntValue(): Int = (Math.random() * 100).toInt()
}
package com.dann.summonerstimerindicator

import androidx.fragment.app.Fragment
import kotlin.random.Random.Default.nextInt

fun randomNumber(range: IntRange): Int {
    return range.first + nextInt(range.last - range.first)
}
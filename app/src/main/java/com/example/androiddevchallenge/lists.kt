package com.example.androiddevchallenge

fun <E> List<E>.replace(old: E, new: E): List<E> {
    return this.map {
        if (it === old) {
            new
        } else {
            it
        }
    }
}

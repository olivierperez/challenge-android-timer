package com.example.androiddevchallenge.domain

data class Timer(
    var remainingSeconds: Int = 0,
    var state: TimerState = TimerState.PRISTINE
)

enum class TimerState(
    val sign: String,
    val visibleFor: () -> Array<TimerState>
) {
    PRISTINE("?", { emptyArray() }),
    SETUP("?", { emptyArray() }),
    PLAY(">", { arrayOf(PRISTINE, SETUP, PAUSE) }),
    PAUSE("||", { arrayOf(PLAY) }),
    STOP("S", { arrayOf(PLAY, PAUSE) })
}

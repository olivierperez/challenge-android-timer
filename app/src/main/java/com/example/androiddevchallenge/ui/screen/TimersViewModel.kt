package com.example.androiddevchallenge.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.Timer
import com.example.androiddevchallenge.domain.TimerState

class TimersViewModel : ViewModel() {

    private val _state = MutableLiveData(State())
    val state: LiveData<State> get() = _state

    fun onTimerDecreased(timer: Timer) {
        if (timer.remainingSeconds <= 0) return
        if (timer.state == TimerState.PRISTINE) {
            _state.postValue(
                State(
                    _state.value!!.timers.replace(
                        timer,
                        Timer(
                            timer.remainingSeconds - 1,
                            TimerState.SETUP
                        )
                    ) + Timer()
                )
            )
        } else {
            _state.postValue(
                _state.value!!.replaceTimer(timer) { old ->
                    Timer(
                        old.remainingSeconds - 1,
                        TimerState.SETUP
                    )
                }
            )
        }
    }

    fun onTimerIncreased(timer: Timer) {
        if (timer.state == TimerState.PRISTINE) {
            _state.postValue(
                State(
                    _state.value!!.timers.replace(
                        timer,
                        Timer(
                            timer.remainingSeconds + 1,
                            TimerState.SETUP
                        )
                    ) + Timer()
                )
            )
        } else {
            _state.postValue(
                _state.value!!.replaceTimer(timer) { old ->
                    Timer(
                        old.remainingSeconds + 1,
                        TimerState.SETUP
                    )
                }
            )
        }
    }

    fun onStateClicked(timer: Timer, state: TimerState) {
        if (state == TimerState.STOP) {
            _state.postValue(_state.value!!.remove(timer))
        } else {
            _state.postValue(
                _state.value!!.replaceTimer(timer) { old ->
                    old.copy(state = state)
                }
            )
        }
    }
}

private fun State.remove(timer: Timer): State {
    val newTimers = timers
        .filterNot { it == timer }
        .takeUnless { it.isEmpty() }
    return State(newTimers ?: listOf(Timer()))
}

class State(
    val timers: List<Timer> = listOf(Timer())
)

private fun State.replaceTimer(timer: Timer, transform: (Timer) -> Timer): State =
    State(timers.replace(timer, transform(timer)))

private fun <E> List<E>.replace(old: E, new: E): List<E> {
    return this.map {
        if (it === old) {
            new
        } else {
            it
        }
    }
}
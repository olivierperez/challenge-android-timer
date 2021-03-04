/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.Timer
import com.example.androiddevchallenge.domain.TimerState
import com.example.androiddevchallenge.replace

class TimersViewModel : ViewModel() {

    private val _state = MutableLiveData(State())
    val state: LiveData<State> get() = _state

    init {
        Thread {
            while (true) {
                Thread.sleep(1000)
                tick()
            }
        }.start()
    }

    private fun tick() {
        _state.postValue(
            _state.value!!.tick()
        )
    }

    fun onTimerDecreased(timer: Timer) {
        if (timer.remainingSeconds <= 0) return
        if (timer.state == TimerState.PRISTINE && _state.value!!.showAddPristine(timer)) {
            _state.postValue(
                State(
                    _state.value!!.timers.replace(
                        timer,
                        Timer(
                            timer.remainingSeconds - 10,
                            TimerState.SETUP
                        )
                    ) + Timer()
                )
            )
        } else {
            _state.postValue(
                _state.value!!.replaceTimer(timer) { old ->
                    Timer(
                        old.remainingSeconds - 10,
                        TimerState.SETUP
                    )
                }
            )
        }
    }

    fun onTimerIncreased(timer: Timer) {
        if (timer.state == TimerState.PRISTINE && _state.value!!.showAddPristine(timer)) {
            _state.postValue(
                State(
                    _state.value!!.timers.replace(
                        timer,
                        Timer(
                            timer.remainingSeconds + 10,
                            TimerState.SETUP
                        )
                    ) + Timer()
                )
            )
        } else {
            _state.postValue(
                _state.value!!.replaceTimer(timer) { old ->
                    Timer(
                        old.remainingSeconds + 10,
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

private fun State.showAddPristine(modified: Timer): Boolean {
    val last = timers.last()
    return modified === last || last.state != TimerState.PRISTINE
}

private fun State.tick(): State {
    return State(
        timers.map { timer ->
            when {
                timer.state != TimerState.PLAY -> timer
                timer.remainingSeconds > 1 -> timer.copy(remainingSeconds = timer.remainingSeconds - 1)
                else -> Timer(0, TimerState.PRISTINE)
            }
        }
    )
}

private fun State.remove(timer: Timer): State {
    val newTimers = timers
        .filterNot { it == timer }
        .takeUnless { it.isEmpty() }
    return State(newTimers ?: listOf(Timer()))
}

private fun State.replaceTimer(timer: Timer, transform: (Timer) -> Timer): State =
    State(timers.replace(timer, transform(timer)))

package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.TimerView

@Composable
fun TimerApp(viewModel: TimersViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                "Challenge\nTimers",
                style = MaterialTheme.typography.h1
            )
            val state: State by viewModel.state.observeAsState(State())
            state.timers.forEach { timer ->
                TimerView(
                    timer,
                    onTimerDecreased = viewModel::onTimerDecreased,
                    onTimerIncreased = viewModel::onTimerIncreased,
                    onStateClicked = viewModel::onStateClicked,
                )
            }
        }
    }
}

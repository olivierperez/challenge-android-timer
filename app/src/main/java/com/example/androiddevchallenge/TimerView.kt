package com.example.androiddevchallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.domain.Timer
import com.example.androiddevchallenge.domain.TimerState
import com.example.androiddevchallenge.domain.TimerState.PRISTINE
import com.example.androiddevchallenge.domain.TimerState.SETUP
import com.example.androiddevchallenge.ui.theme.TimerTheme

@Composable
fun TimerView(
    timer: Timer,
    onTimerDecreased: (Timer) -> Unit,
    onTimerIncreased: (Timer) -> Unit,
    onStateClicked: (Timer, TimerState) -> Unit
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(16.dp, 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            timer.remainingSeconds.toString(),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f))

        if (timer.state in arrayOf(PRISTINE, SETUP)) {
            Button(
                onClick = { onTimerDecreased(timer) },
                shape = RoundedCornerShape(0.dp)
            ) {
                Text("-")
            }
            Button(
                onClick = { onTimerIncreased(timer) },
                shape = RoundedCornerShape(0.dp)
            ) {
                Text("+")
            }
        }

        TimerState.values()
            .filter { state -> timer.state in state.visibleFor() }
            .forEach { state ->
                Button(
                    onClick = { onStateClicked(timer, state) },
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(state.sign)
                }
            }
    }
}

@Composable
@Preview
fun PreviewTimer() {
    TimerTheme {
        TimerView(
            Timer(50, TimerState.PAUSE),
            onTimerDecreased = {},
            onTimerIncreased = {},
            onStateClicked = { _, _ -> },
        )
    }
}
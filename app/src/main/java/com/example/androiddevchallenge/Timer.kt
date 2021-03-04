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
import com.example.androiddevchallenge.ui.theme.TimerTheme

@Composable
fun Timer() {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(16.dp, 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "00:00",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {}, shape = RoundedCornerShape(0.dp)) {
            Text("-")
        }
        Button(onClick = {}, shape = RoundedCornerShape(0.dp)) {
            Text("+")
        }
        Button(onClick = {}, shape = RoundedCornerShape(0.dp)) {
            Text(">")
        }
    }
}

@Composable
@Preview
fun PreviewTimer() {
    TimerTheme {
        Timer()
    }
}
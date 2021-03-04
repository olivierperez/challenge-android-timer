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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFFD06A4F),
    onPrimary = Color(0XFF3D405B),
    primaryVariant = Color(0xFFE07A5F),
    secondary = Color(0XFF3D405B),
    onSecondary = Color(0xFFF4F1DE),
    background = Color(0xFF2D304B),
    surface = Color(0XFF3D405B),
    onSurface = Color(0xFFF4F1DE)
)

private val LightColorPalette = lightColors(
    primary = Color(0XFF3D405B),
    onPrimary = Color(0xFFF2CC8F),
    primaryVariant = Color(0xFF2D304B),
    secondary = Color(0xFFE07A5F),
    onSecondary = Color(0xFF3D405B),
    background = Color(0xFFF4F1DE),
    surface = Color(0xFFD4D1BE),
    onSurface = Color(0xFF3D405B)
)

@Composable
fun TimerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

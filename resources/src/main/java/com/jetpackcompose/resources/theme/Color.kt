package com.jetpackcompose.resources.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightRed = Color(0xffe91e63)
val darkRed = Color(0xffb0003a)

val lightPurple = Color(0xff9c27b0)
val darkPurple = Color(0xff6a0080)

val lightBlue = Color(0xff03a9f4)
val darkBlue = Color(0xff005b9f)

val lightGreen = Color(0xff4caf50)
val darkGreen = Color(0xff00600f)

val lightYellow = Color(0xffffeb3b)
val darkYellow = Color(0xfff57f17)

val dark = Color(0xff424242)

val black = Color(0xff212121)
val white = Color(0xFFFFFFFF)

//define custom material for add more color :)
@get:Composable
val Colors.splashColor1: Color
    get() = if (isLight) lightBlue else darkBlue

@get:Composable
val Colors.splashColor2: Color
    get() = if (isLight) lightGreen else darkGreen

@get:Composable
val Colors.chipUnselected: Color
    get() = if (isLight) dark else white

@get:Composable
val Colors.chipSelected: Color
    get() = if (isLight) white else dark

@get:Composable
val Colors.textColor: Color
    get() = if (isLight) black else white

@get:Composable
val Colors.circularProgressColor: Color
    get() = if (isLight) dark else white
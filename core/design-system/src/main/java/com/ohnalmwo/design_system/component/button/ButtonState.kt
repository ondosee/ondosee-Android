package com.ohnalmwo.design_system.component.button

sealed class ButtonState {
    data object Normal : ButtonState()
    data object Transparent : ButtonState()
}
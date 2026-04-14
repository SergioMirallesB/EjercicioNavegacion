package com.ejercicio.navegacion

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
@Composable
fun PantallaInicio(onNavigateToList: () -> Unit) {
    var pulsado by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (pulsado) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 500),
        label = "colorAnimation"
    )

    val animatedOffset by animateOffsetAsState(
        targetValue = if (pulsado) Offset(100f, 150f) else Offset.Zero,
        animationSpec = tween(durationMillis = 500),
        label = "offsetAnimation"
    )

    Column(
        modifier = Modifier.systemBarsPadding().fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pantalla Inicio", fontSize = 24.sp, modifier = Modifier.padding(bottom = 32.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .offset {
                    IntOffset(
                        x = animatedOffset.x.roundToInt(),
                        y = animatedOffset.y.roundToInt()
                    )
                }
                .background(animatedColor)
                .clickable { pulsado = !pulsado }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onNavigateToList) {
            Text("Ir a la Lista")
        }
    }
}
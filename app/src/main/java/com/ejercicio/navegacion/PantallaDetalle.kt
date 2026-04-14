package com.ejercicio.navegacion

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(itemName: String, onNavigateToBrowser: (String) -> Unit, onBack: () -> Unit) {
    var counter by remember { mutableStateOf(0) }
    var isMessageVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle: $itemName") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Volver") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Has seleccionado:", fontSize = 20.sp)
            Text(itemName, fontSize = 28.sp, color = MaterialTheme.colorScheme.primary)

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { counter-- }) { Text("-") }

                AnimatedContent(
                    targetState = counter,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (slideInVertically { height -> height } + fadeIn()).togetherWith(slideOutVertically { height -> -height } + fadeOut())
                        } else {
                            (slideInVertically { height -> -height } + fadeIn()).togetherWith(slideOutVertically { height -> height } + fadeOut())
                        }
                    },
                    label = "counterAnimation"
                ) { targetCount ->
                    Text(
                        text = "$targetCount",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                Button(onClick = { counter++ }) { Text("+") }
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Button(onClick = { isMessageVisible = !isMessageVisible }) {
                Text(if (isMessageVisible) "Ocultar Mensaje" else "Mostrar Mensaje")
            }

            AnimatedVisibility(visible = isMessageVisible) {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                    Text("¡Hola! Esto es un mensaje animado.", modifier = Modifier.padding(16.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { onNavigateToBrowser("https://www.google.es") }) {
                Text("Abrir Navegador Web")
            }
        }
    }
}

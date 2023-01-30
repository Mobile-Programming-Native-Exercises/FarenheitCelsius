package com.example.farenheitcelsius

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farenheitcelsius.ui.theme.FarenheitCelsiusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarenheitCelsiusTheme {
                FahrenheitCelsius()
            }
        }
    }
}

@Composable
fun FahrenheitCelsius() {
    var temp: String by remember { mutableStateOf("") }
    var fahrenheitSelected: Boolean by remember { mutableStateOf(true) }
    val tempFloat: Float = temp.toFloatOrNull() ?: 0.0f
    val result = when (fahrenheitSelected) {
        true -> if (tempFloat > 0.0f) (tempFloat - 32) / 1.8f else 0.0f
        false -> if (tempFloat > 0.0f) (tempFloat * 1.8f) + 32 else 0.0f
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.title),
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = temp,
            onValueChange = { temp = it },
            label = { Text(text = stringResource(R.string.input_text)) },
            textStyle = TextStyle(color = MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = fahrenheitSelected, onClick = { fahrenheitSelected = true })
            Text(text = stringResource(R.string.radio_one))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = !fahrenheitSelected, onClick = { fahrenheitSelected = false })
            Text(text = stringResource(R.string.radio_two))
        }
        Text(
            text = String.format("%.2f", result),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}
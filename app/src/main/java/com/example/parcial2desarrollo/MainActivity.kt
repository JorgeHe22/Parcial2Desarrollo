package com.example.parcial2desarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial2desarrollo.Screen.Navegacion
import com.example.parcial2desarrollo.Screen.ProductoViewModel
import com.example.parcial2desarrollo.ui.theme.Parcial2DesarrolloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial2DesarrolloTheme {
                val productoViewModel: ProductoViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Navegacion(viewModel = productoViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Parcial2DesarrolloTheme {
        Greeting("Android")
    }
}
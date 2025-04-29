package com.example.parcial2desarrollo.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ScreenCarrito(navController: NavController, viewModel: ProductoViewModel) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito de Compras") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(viewModel.carrito) { producto ->
                    Text("${producto.nombre} - $${producto.precio}")
                }
            }

            Text(
                "Total a pagar: $${viewModel.totalCarrito()}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Row {
                Button(onClick = {
                    navController.popBackStack()
                }) {
                    Text("Volver")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    viewModel.vaciarCarrito()
                    Toast.makeText(context, "¡Compra finalizada!", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                }) {
                    Text("Finalizar Compra")
                }
            }
        }
    }
}

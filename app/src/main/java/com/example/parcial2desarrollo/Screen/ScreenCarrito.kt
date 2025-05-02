package com.example.parcial2desarrollo.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCarrito(navController: NavController, viewModel: ProductoViewModel) {
    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito de Compras") })
        }
    ) { paddingValues ->

        if (showConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("Confirmar Compra") },
                text = { Text("¿Estás seguro de que deseas finalizar la compra?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.vaciarCarrito()
                        showConfirmDialog = false
                        navController.popBackStack()
                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showConfirmDialog = false
                    }) {
                        Text("No")
                    }
                }
            )
        }

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
                    showConfirmDialog = true
                }) {
                    Text("Finalizar Compra")
                }
            }
        }
    }
}

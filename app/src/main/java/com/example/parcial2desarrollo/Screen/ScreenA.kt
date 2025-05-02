package com.example.parcial2desarrollo.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.parcial2desarrollo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(navController: NavController, viewModel: ProductoViewModel) {
    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catálogo de Productos") })
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Total: $${viewModel.totalCarrito()}",
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )
                Button(onClick = {
                    navController.navigate("carrito")
                }) {
                    Text("Carrito")
                }
                Button(onClick = {
                    navController.navigate("registro")
                }) {
                    Text("Agregar")
                }
            }
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(16.dp)
        ) {
            items(viewModel.productos) { producto ->
                ProductoItem(
                    producto = producto,
                    onClick = {
                        navController.navigate("detalle/${producto.id}")
                    },
                    onEliminar = {
                        productoAEliminar = producto
                    }
                )
            }
        }

        // Diálogo de confirmación para eliminar
        productoAEliminar?.let { producto ->
            AlertDialog(
                onDismissRequest = { productoAEliminar = null },
                title = { Text("¿Eliminar producto?") },
                text = { Text("¿Estás seguro de que deseas eliminar '${producto.nombre}' del catálogo?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.eliminarProducto(producto)
                        productoAEliminar = null
                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        productoAEliminar = null
                    }) {
                        Text("No")
                    }
                }
            )
        }
    }
}

@Composable
fun ProductoItem(
    producto: Producto,
    onClick: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(producto.imagenUrl)
                    .crossfade(true)
                    .error(R.drawable.error_icono)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .clickable { onClick() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier
                .weight(1f)
                .clickable { onClick() }
            ) {
                Text(producto.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Precio: $${producto.precio}")
            }

            IconButton(onClick = onEliminar) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
/*
Scaffold es un componente que proporciona una estructura base
para una pantalla. Permite organizar de forma sencilla elementos comunes como:

- topBar: barra superior de la app (por ejemplo, con el título).
- bottomBar: barra inferior, para botones de navegación o acciones.
- floatingActionButton: botón flotante.
- drawerContent: menú lateral deslizante.
- content: el contenido principal de la pantalla, lo que va dentro.

Esto ayuda a mantener una interfaz organizada y coherente en la app.
*/

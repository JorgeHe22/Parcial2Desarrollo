package com.example.parcial2desarrollo.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(navController: NavController, viewModel: ProductoViewModel) {
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
                ProductoItem(producto = producto, onClick = {
                    navController.navigate("detalle/${producto.id}")
                })
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(producto.imagenUrl)
                    .crossfade(true)
                    .build()
            )
            val state = painter.state

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                when (state) {
                    is AsyncImagePainter.State.Error -> {
                        Text("Imagen no disponible", fontSize = 12.sp)
                    }
                    else -> {
                        AsyncImage(
                            model = producto.imagenUrl,
                            contentDescription = producto.nombre,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(producto.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Precio: $${producto.precio}")
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

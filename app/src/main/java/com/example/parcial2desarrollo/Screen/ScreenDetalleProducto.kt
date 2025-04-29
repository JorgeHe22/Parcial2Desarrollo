package com.example.parcial2desarrollo.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
fun ScreenDetalleProducto(navController: NavController, productoId: Int, viewModel: ProductoViewModel) {
    val producto = viewModel.obtenerProductoPorId(productoId)

    if (producto == null) {
        Text("Producto no encontrado")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle del Producto") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(producto.imagenUrl)
                    .crossfade(true)
                    .build()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Error -> Text("Imagen no disponible")
                    else -> AsyncImage(
                        model = producto.imagenUrl,
                        contentDescription = producto.nombre,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Text(producto.nombre, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Precio: $${producto.precio}")
            Text("Descripción: ${producto.descripcion}", modifier = Modifier.padding(top = 8.dp))

            Row(modifier = Modifier.padding(top = 16.dp)) {
                Button(onClick = {
                    viewModel.agregarAlCarrito(producto)
                    navController.popBackStack()
                }) {
                    Text("Agregar al carrito")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        }
    }
}

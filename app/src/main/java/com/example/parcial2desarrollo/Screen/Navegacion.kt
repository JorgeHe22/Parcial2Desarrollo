package com.example.parcial2desarrollo.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun Navegacion(viewModel: ProductoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "catalogo") {

        composable("catalogo") {
            ScreenA(navController = navController, viewModel = viewModel)
        }

        composable(
            "detalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ScreenDetalleProducto(navController = navController, viewModel = viewModel, productoId = id)
        }

        composable("carrito") {
            ScreenCarrito(navController = navController, viewModel = viewModel)
        }

        composable("registro") {
            ScreenRegistro(navController = navController, viewModel = viewModel)
        }
    }
}


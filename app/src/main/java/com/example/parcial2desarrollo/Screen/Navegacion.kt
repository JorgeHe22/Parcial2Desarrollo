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

    NavHost(navController = navController, startDestination = "ScreenA"){
        composable("ScreenA"){
            ScreenA(navController)
        }
        composable("ScreenRegistro"){
        }

    }
    }


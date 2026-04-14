package com.ejercicio.navegacion

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {

        // 1) Pantalla Inicio
        composable("inicio") {
            PantallaInicio(
                onNavigateToList = { navController.navigate("lista") }
            )
        }

        // 2) Pantalla Lista
        composable("lista") {
            PantallaLista(
                onNavigateToDetail = { item ->
                    navController.navigate("detalle/$item")
                },
                onBack = { navController.popBackStack() }
            )
        }

        // 3) Pantalla Detalle
        composable(
            route = "detalle/{itemName}",
            arguments = listOf(navArgument("itemName") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName") ?: ""
            PantallaDetalle(
                itemName = itemName,
                onNavigateToBrowser = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate("browser/$encodedUrl")
                },
                onBack = { navController.popBackStack() }
            )
        }

        // 4) Pantalla Browser
        composable(
            route = "browser/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())

            PantallaBrowser(
                url = decodedUrl,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
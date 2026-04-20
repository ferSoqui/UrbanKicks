package com.example.urbankicks.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.urbankicks.vistas.LoginView
import com.example.urbankicks.vistas.RegistroView
import com.example.urbankicks.vistas.HomeView
import com.example.urbankicks.vistas.DetalleView
import com.example.urbankicks.vistas.CarritoView
import com.example.urbankicks.vistas.PerfilView
import com.example.urbankicks.vistas.AjustesView

@Composable
fun NavManager(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    // Lista observable que guarda los ids de zapatillas en el carrito
    // Se comparte entre HomeView, DetalleView y CarritoView
    val carritoIds = remember { mutableStateListOf<Int>() }

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {

        // Pantalla 1: Login — pantalla inicial de la app
        composable("login") {
            LoginView(navController)
        }

        // Pantalla 2: Registro — crear cuenta nueva
        composable("registro") {
            RegistroView(navController)
        }

        // Pantalla 3: Home — lista de productos y búsqueda
        composable("home") {
            HomeView(navController)
        }

        // Pantalla 4: Detalle — recibe el id del producto como parámetro
        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString("id")
                ?.toIntOrNull() ?: 0
            DetalleView(navController, id, carritoIds)
        }

        // Pantalla 5: Carrito — recibe la lista compartida de ids
        composable("carrito") {
            CarritoView(navController, carritoIds)
        }

        // Pantalla 6: Perfil
        composable("perfil") {
            PerfilView(navController)
        }

        // Pantalla 7: Ajustes
        composable("ajustes") {
            AjustesView(navController)
        }
    }
}
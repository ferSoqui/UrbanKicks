package com.example.urbankicks.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.urbankicks.modelo.ItemCarrito
import com.example.urbankicks.persistencia.Preferencias
import com.example.urbankicks.vistas.LoginView
import com.example.urbankicks.vistas.RegistroView
import com.example.urbankicks.vistas.HomeView
import com.example.urbankicks.vistas.DetalleView
import com.example.urbankicks.vistas.CarritoView
import com.example.urbankicks.vistas.PerfilView
import com.example.urbankicks.vistas.AjustesView
import com.example.urbankicks.vistas.PagoView
import com.example.urbankicks.vistas.ConfirmacionView

@Composable
fun NavManager(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val preferencias = Preferencias(context)

    val logged by preferencias.logged.collectAsState(initial = false)
    val carritoGuardado by preferencias.carrito.collectAsState(initial = emptyList())


    val navController = rememberNavController()
    val carrito = remember { mutableStateListOf<ItemCarrito>() }


    LaunchedEffect(carritoGuardado) {
        carrito.clear()
        carrito.addAll(carritoGuardado)
    }

    NavHost(
        navController = navController,
        startDestination = if (logged) "home" else "login",
        modifier = modifier
    ) {

        composable("login") {
            LoginView(navController)
        }

        composable("registro") {
            RegistroView(navController)
        }

        composable("home") {
            HomeView(navController)
        }

        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetalleView(navController, id, carrito)
        }

        composable("carrito") {
            CarritoView(navController, carrito)
        }

        composable("pago") {
            PagoView(navController, carrito)
        }

        composable("confirmacion") {
            ConfirmacionView(navController, carrito)
        }

        composable("perfil") {
            PerfilView(navController)
        }

        composable("ajustes") {
            AjustesView(navController)
        }
    }
}
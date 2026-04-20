package com.example.urbankicks.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.R
import com.example.urbankicks.modelo.Zapatilla
import com.example.urbankicks.modelo.obtenerZapatillas
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris
import com.example.urbankicks.ui.theme.TextoOscuro

@Composable
fun HomeView(navController: NavHostController) {

    val zapatillas = obtenerZapatillas()
    var busqueda by remember { mutableStateOf("") }
    var marcaSeleccionada by remember { mutableStateOf("Todas") }

    val marcas = listOf("Todas") + zapatillas.map { it.marca }.distinct()

    val zapatillasFiltradas = zapatillas.filter { zapatilla ->
        val coincideBusqueda = busqueda.isEmpty() ||
                zapatilla.nombre.contains(busqueda, ignoreCase = true)
        zapatilla.marca.contains(busqueda, ignoreCase = true)
        zapatilla.color.contains(busqueda, ignoreCase = true)
        zapatilla.categoria.contains(busqueda, ignoreCase = true)

        val coincideMarca = marcaSeleccionada == "Todas" ||
                zapatilla.marca == marcaSeleccionada

        coincideBusqueda && coincideMarca
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "UrbanKicks",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                        IconButton(
                            onClick = { navController.navigate("carrito") },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(CafePrincipal)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Carrito",
                                tint = Color.White
                            )
                        }

                        IconButton(
                            onClick = { navController.navigate("perfil") },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(CafePrincipal)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Perfil",
                                tint = Color.White
                            )
                        }

                        IconButton(
                            onClick = { navController.navigate("ajustes") },
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(CafePrincipal)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Ajustes",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = busqueda,
                    onValueChange = { busqueda = it },
                    label = { Text("Buscar productos...") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Elegir marca",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(marcas) { marca ->
                        Button(
                            onClick = { marcaSeleccionada = marca },
                            shape = RoundedCornerShape(20.dp),
                            colors = if (marcaSeleccionada == marca)
                                ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                            else
                                ButtonDefaults.buttonColors(containerColor = Color.White),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = marca,
                                color = if (marcaSeleccionada == marca) Color.White else CafePrincipal,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            items(zapatillasFiltradas.chunked(2)) { par ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    par.forEach { zapatilla ->
                        ZapatillaCard(
                            zapatilla = zapatilla,
                            modifier = Modifier.weight(1f),
                            onClick = { navController.navigate("detalle/${zapatilla.id}") }
                        )
                    }
                    if (par.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (zapatillasFiltradas.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No se encontraron productos",
                            color = TextoGris
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ZapatillaCard(
    zapatilla: Zapatilla,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Image(
                painter = painterResource(id = when(zapatilla.id) {
                    1 -> R.drawable.zapato01
                    2 -> R.drawable.zapato02
                    3 -> R.drawable.zapato03
                    4 -> R.drawable.zapato04
                    5 -> R.drawable.zapato05
                    6 -> R.drawable.zapato06
                    7 -> R.drawable.zapato07
                    8 -> R.drawable.zapato08
                    else -> R.drawable.zapato01
                }),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = zapatilla.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )

            Text(
                text = zapatilla.marca,
                color = TextoGris,
                fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "$${zapatilla.precio}",
                fontWeight = FontWeight.Bold,
                color =  TextoOscuro,
                fontSize = 14.sp
            )
        }
    }
}
package com.example.urbankicks.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.R
import com.example.urbankicks.modelo.ItemCarrito
import com.example.urbankicks.modelo.obtenerZapatillas
import com.example.urbankicks.persistencia.Preferencias
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris
import kotlinx.coroutines.launch

@Composable
fun DetalleView(navController: NavHostController, id: Int, carrito: MutableList<ItemCarrito>) {

    val context = LocalContext.current
    val preferencias = Preferencias(context)
    val scope = rememberCoroutineScope()

    val zapatilla = obtenerZapatillas().find { it.id == id }
    var mensaje by remember { mutableStateOf("") }
    val tallas = listOf("6 MX", "7 MX", "8 MX", "9 MX", "10 MX")
    var tallaSeleccionada by remember { mutableStateOf("7 MX") }
    var cantidad by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
        if (zapatilla != null) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = CafePrincipal
                        )
                    }

                    Text(
                        text = "Detalle del producto",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    IconButton(onClick = { navController.navigate("carrito") }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito",
                            tint = CafePrincipal
                        )
                    }
                }

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
                        .height(250.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = "Talla: $tallaSeleccionada",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        tallas.forEach { talla ->
                            Button(
                                onClick = { tallaSeleccionada = talla },
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                colors = if (tallaSeleccionada == talla)
                                    ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                                else
                                    ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text(
                                    text = talla,
                                    fontSize = 11.sp,
                                    color = if (tallaSeleccionada == talla) Color.White else CafePrincipal
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${zapatilla.marca} - ${zapatilla.nombre}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "$${zapatilla.precio}",
                            fontWeight = FontWeight.Bold,
                            color = CafePrincipal,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Color: ${zapatilla.color}",
                            color = TextoGris,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Descripción del producto",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = zapatilla.descripcion,
                        color = TextoGris,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Cantidad:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { if (cantidad > 1) cantidad-- },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                        ) {
                            Text("-", fontSize = 18.sp, color = Color.White)
                        }

                        Text(
                            text = "$cantidad",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Button(
                            onClick = { cantidad++ },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                        ) {
                            Text("+", fontSize = 18.sp, color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (mensaje.isNotEmpty()) {
                        Text(
                            text = mensaje,
                            color = CafePrincipal,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(
                        onClick = {

                            scope.launch {
                                preferencias.guardarCarrito(carrito)
                            }

                            val itemExistente = carrito.find {
                                it.zapatillaId == zapatilla.id && it.talla == tallaSeleccionada
                            }
                            if (itemExistente != null) {
                                itemExistente.cantidad += cantidad
                                mensaje = "Cantidad actualizada en el carrito"
                            } else {
                                carrito.add(
                                    ItemCarrito(
                                        zapatillaId = zapatilla.id,
                                        talla = tallaSeleccionada,
                                        cantidad = cantidad
                                    )
                                )
                                mensaje = "Agregado al carrito"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddShoppingCart,
                            contentDescription = ""
                        )
                        Text(
                            text = "   Añadir al carrito",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

        } else {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Producto no encontrado")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    Text(text = "   Regresar", color = Color.White)
                }
            }
        }
    }
}
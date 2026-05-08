package com.example.urbankicks.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.urbankicks.modelo.ItemCarrito
import com.example.urbankicks.modelo.obtenerZapatillas
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.ui.res.stringResource


@Composable
fun CarritoView(navController: NavHostController, carrito: MutableList<ItemCarrito>) {

    val todasLasZapatillas = obtenerZapatillas()
    val total = carrito.sumOf { item ->
        val zapatilla = todasLasZapatillas.find { it.id == item.zapatillaId }
        (zapatilla?.precio ?: 0.0) * item.cantidad
    }

    val fechaEntrega = LocalDate.now().plusDays(7).format(
        DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("es", "MX"))
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

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
                    text = stringResource(R.string.carrito),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(48.dp))
            }

            if (carrito.isEmpty()) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "",
                        tint = CafePrincipal,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.carrito_vacio),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.agrega_productos),
                        color = TextoGris,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.popBackStack() },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        Text(text = stringResource(R.string.ver_productos), color = Color.White)
                    }
                }

            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(carrito) { item ->
                        val zapatilla = todasLasZapatillas.find { it.id == item.zapatillaId }

                        if (zapatilla != null) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
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
                                            .size(90.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = zapatilla.nombre,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        )

                                        Text(
                                            text = zapatilla.marca,
                                            color = TextoGris,
                                            fontSize = 13.sp
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                            Text(
                                                text = stringResource(R.string.cant_label) + item.talla,
                                                color = TextoGris,
                                                fontSize = 12.sp
                                            )
                                            Text(
                                                text = stringResource(R.string.cant_label) + item.cantidad,
                                                color = TextoGris,
                                                fontSize = 12.sp
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = stringResource(R.string.entrega_label) + fechaEntrega,
                                            color = CafePrincipal,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = "$${String.format("%.2f", zapatilla.precio * item.cantidad)}",
                                            fontWeight = FontWeight.Bold,
                                            color = CafePrincipal,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = stringResource(R.string.detalles_pago),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text=stringResource(R.string.subtotal), color = TextoGris)
                            Text(
                                text = "$${String.format("%.2f", total)}",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(stringResource(R.string.costo_envio), color = TextoGris)
                            Text(
                                text = stringResource(R.string.gratis),
                                color = CafePrincipal,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Divider()

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.total_pedido),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$${String.format("%.2f", total)}",
                                fontWeight = FontWeight.Bold,
                                color = CafePrincipal,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                Button(
                    onClick = { navController.navigate("pago") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                    Text(
                        text = stringResource(R.string.proceder_al_pago),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
package com.example.urbankicks.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.modelo.ItemCarrito
import com.example.urbankicks.modelo.obtenerZapatillas
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.random.Random

@Composable
fun ConfirmacionView(navController: NavHostController, carrito: MutableList<ItemCarrito>) {

    val todasLasZapatillas = obtenerZapatillas()
    val total = carrito.sumOf { item ->
        val zapatilla = todasLasZapatillas.find { it.id == item.zapatillaId }
        (zapatilla?.precio ?: 0.0) * item.cantidad
    }

    val numeroPedido = remember { Random.nextInt(100000, 999999) }

    val fechaEntrega = LocalDate.now().plusDays(7).format(
        DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", Locale("es", "MX"))
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "",
                        tint = CafePrincipal,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "¡Pago confirmado!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Tu pedido ha sido procesado exitosamente",
                        color = TextoGris,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Detalles del pedido",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Número de pedido", color = TextoGris, fontSize = 13.sp)
                            Text(
                                text = "#$numeroPedido",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Fecha de entrega", color = TextoGris, fontSize = 13.sp)
                            Text(
                                text = fechaEntrega,
                                fontWeight = FontWeight.Bold,
                                color = CafePrincipal,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Estado", color = TextoGris, fontSize = 13.sp)
                            Text(
                                text = "En proceso",
                                fontWeight = FontWeight.Bold,
                                color = CafePrincipal,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Productos comprados",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        carrito.forEach { item ->
                            val zapatilla = todasLasZapatillas.find { it.id == item.zapatillaId }
                            if (zapatilla != null) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = zapatilla.nombre,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp
                                        )
                                        Text(
                                            text = "Talla: ${item.talla} · Cant: ${item.cantidad}",
                                            color = TextoGris,
                                            fontSize = 12.sp
                                        )
                                    }
                                    Text(
                                        text = "$${String.format("%.2f", zapatilla.precio * item.cantidad)}",
                                        fontWeight = FontWeight.Bold,
                                        color = CafePrincipal,
                                        fontSize = 13.sp
                                    )
                                }
                                Divider(modifier = Modifier.padding(vertical = 4.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total pagado",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = "$${String.format("%.2f", total)}",
                                fontWeight = FontWeight.Bold,
                                color = CafePrincipal,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        carrito.clear()
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = false }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                ) {
                    Text(
                        text = "Volver al inicio",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

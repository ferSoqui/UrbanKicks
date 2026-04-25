package com.example.urbankicks.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.urbankicks.modelo.ItemCarrito
import com.example.urbankicks.modelo.obtenerZapatillas
import com.example.urbankicks.ui.theme.CafePrincipal
import com.example.urbankicks.ui.theme.FondoClaro
import com.example.urbankicks.ui.theme.TextoGris

@Composable
fun PagoView(navController: NavHostController, carrito: MutableList<ItemCarrito>) {

    val todasLasZapatillas = obtenerZapatillas()
    val total = carrito.sumOf { item ->
        val zapatilla = todasLasZapatillas.find { it.id == item.zapatillaId }
        (zapatilla?.precio ?: 0.0) * item.cantidad
    }

    var nombreTarjeta by remember { mutableStateOf("") }
    var numeroTarjeta by remember { mutableStateOf("") }
    var fechaVencimiento by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var codigoPostal by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoClaro
    ) {
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
                    text = "Datos de pago",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(48.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Información de tarjeta",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(text = "Nombre en la tarjeta", color = TextoGris, fontSize = 12.sp)
                        OutlinedTextField(
                            value = nombreTarjeta,
                            onValueChange = { nombreTarjeta = it },
                            placeholder = { Text("Juan Pérez") },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(text = "Número de tarjeta", color = TextoGris, fontSize = 12.sp)
                        OutlinedTextField(
                            value = numeroTarjeta,
                            onValueChange = { if (it.length <= 16) numeroTarjeta = it },
                            placeholder = { Text("1234 5678 9012 3456") },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Fecha vencimiento", color = TextoGris, fontSize = 12.sp)
                                OutlinedTextField(
                                    value = fechaVencimiento,
                                    onValueChange = { if (it.length <= 5) fechaVencimiento = it },
                                    placeholder = { Text("MM/AA") },
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "CVV", color = TextoGris, fontSize = 12.sp)
                                OutlinedTextField(
                                    value = cvv,
                                    onValueChange = { if (it.length <= 3) cvv = it },
                                    placeholder = { Text("123") },
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Dirección de envío",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(text = "Dirección", color = TextoGris, fontSize = 12.sp)
                        OutlinedTextField(
                            value = direccion,
                            onValueChange = { direccion = it },
                            placeholder = { Text("Calle y número") },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Ciudad", color = TextoGris, fontSize = 12.sp)
                                OutlinedTextField(
                                    value = ciudad,
                                    onValueChange = { ciudad = it },
                                    placeholder = { Text("Ciudad") },
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Código postal", color = TextoGris, fontSize = 12.sp)
                                OutlinedTextField(
                                    value = codigoPostal,
                                    onValueChange = { if (it.length <= 5) codigoPostal = it },
                                    placeholder = { Text("12345") },
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Resumen del pedido",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Subtotal", color = TextoGris)
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
                            Text("Costo de envío", color = TextoGris)
                            Text(
                                text = "Gratis",
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
                                text = "Total",
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

                Spacer(modifier = Modifier.height(16.dp))

                if (error.isNotEmpty()) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        when {
                            nombreTarjeta.trim().isEmpty() -> error = "Ingresa el nombre de la tarjeta"
                            numeroTarjeta.trim().length < 16 -> error = "Ingresa un número de tarjeta válido"
                            fechaVencimiento.trim().length < 5 -> error = "Ingresa la fecha de vencimiento"
                            cvv.trim().length < 3 -> error = "Ingresa el CVV"
                            direccion.trim().isEmpty() -> error = "Ingresa tu dirección"
                            ciudad.trim().isEmpty() -> error = "Ingresa tu ciudad"
                            codigoPostal.trim().length < 5 -> error = "Ingresa tu código postal"
                            else -> navController.navigate("confirmacion")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CafePrincipal)
                ) {
                    Text(
                        text = "Confirmar pago",
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


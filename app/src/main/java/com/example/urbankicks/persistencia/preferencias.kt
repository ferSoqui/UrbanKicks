package com.example.urbankicks.persistencia

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.urbankicks.modelo.ItemCarrito
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Preferencias(private val contexto: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("configuraciones")

        val NOMBRE = stringPreferencesKey("nombre")
        val CORREO = stringPreferencesKey("correo")
        val IDIOMA = stringPreferencesKey("idioma")

        val OFERTAS = booleanPreferencesKey("ofertas")
        val RECORDATORIOS = booleanPreferencesKey("recordatorios")

        val LOGGED = booleanPreferencesKey("logged")

        val CARRITO = stringPreferencesKey("carrito")

        val NOMBRE_TARJETA = stringPreferencesKey("nombre_tarjeta")
        val NUMERO_TARJETA = stringPreferencesKey("numero_tarjeta")
        val FECHA = stringPreferencesKey("fecha")
        val CVV = stringPreferencesKey("cvv")
        val DIRECCION = stringPreferencesKey("direccion")
        val CIUDAD = stringPreferencesKey("ciudad")
        val CP = stringPreferencesKey("cp")


    }

    val logged: Flow<Boolean> = contexto.dataStore.data.map {
        it[LOGGED] ?: false
    }

    val nombre: Flow<String> = contexto.dataStore.data.map {
        it[NOMBRE] ?: ""
    }

    val correo: Flow<String> = contexto.dataStore.data.map {
        it[CORREO] ?: ""
    }

    val idioma: Flow<String> = contexto.dataStore.data.map {
        it[IDIOMA] ?: "Español (México)"
    }

    val ofertas: Flow<Boolean> = contexto.dataStore.data.map {
        it[OFERTAS] ?: true
    }

    val recordatorios: Flow<Boolean> = contexto.dataStore.data.map {
        it[RECORDATORIOS] ?: true
    }

    val carrito: Flow<List<ItemCarrito>> = contexto.dataStore.data.map { preferences ->
        val texto = preferences[CARRITO] ?: ""

        if (texto.isEmpty()) return@map emptyList()

        texto.split(";").mapNotNull { item ->
            val partes = item.split("|")

            if (partes.size == 3) {
                ItemCarrito(
                    zapatillaId = partes[0].toIntOrNull() ?: return@mapNotNull null,
                    talla = partes[1],
                    cantidad = partes[2].toIntOrNull() ?: 1
                )
            } else null
        }


    }

    val nombreTarjeta: Flow<String> = contexto.dataStore.data.map {
        it[NOMBRE_TARJETA] ?: ""
    }

    val numeroTarjeta: Flow<String> = contexto.dataStore.data.map {
        it[NUMERO_TARJETA] ?: ""
    }

    val fecha: Flow<String> = contexto.dataStore.data.map {
        it[FECHA] ?: ""
    }

    val cvv: Flow<String> = contexto.dataStore.data.map {
        it[CVV] ?: ""
    }

    val direccion: Flow<String> = contexto.dataStore.data.map {
        it[DIRECCION] ?: ""
    }

    val ciudad: Flow<String> = contexto.dataStore.data.map {
        it[CIUDAD] ?: ""
    }

    val cp: Flow<String> = contexto.dataStore.data.map {
        it[CP] ?: ""
    }


    suspend fun guardarPerfil(nombre: String, correo: String, idioma: String) {
        contexto.dataStore.edit {
            it[NOMBRE] = nombre
            it[CORREO] = correo
            it[IDIOMA] = idioma
        }
    }

    suspend fun guardarAjustes(ofertas: Boolean, recordatorios: Boolean) {
        contexto.dataStore.edit {
            it[OFERTAS] = ofertas
            it[RECORDATORIOS] = recordatorios
        }
    }

    suspend fun guardarSesion(logged: Boolean) {
        contexto.dataStore.edit {
            it[LOGGED] = logged
        }
    }

    suspend fun guardarCarrito(carrito: List<ItemCarrito>) {
        val texto = carrito.joinToString(";") {
            "${it.zapatillaId}|${it.talla}|${it.cantidad}"
        }

        contexto.dataStore.edit {
            it[CARRITO] = texto
        }
    }

    suspend fun guardarPago(
        nombre: String,
        numero: String,
        fecha: String,
        cvv: String,
        direccion: String,
        ciudad: String,
        cp: String
    ) {
        contexto.dataStore.edit {
            it[NOMBRE_TARJETA] = nombre
            it[NUMERO_TARJETA] = numero
            it[FECHA] = fecha
            it[CVV] = cvv
            it[DIRECCION] = direccion
            it[CIUDAD] = ciudad
            it[CP] = cp
        }
    }


}



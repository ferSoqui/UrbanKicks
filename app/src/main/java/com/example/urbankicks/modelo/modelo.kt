package com.example.urbankicks.modelo


data class Zapatilla(
    val id: Int,
    val nombre: String,
    val marca: String,
    val precio: Double,
    val descripcion: String,
    val categoria: String,
    val color: String
)
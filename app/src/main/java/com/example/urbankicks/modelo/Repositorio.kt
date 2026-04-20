package com.example.urbankicks.modelo


fun obtenerZapatillas(): List<Zapatilla> {
    return listOf(
        Zapatilla(
            id = 1,
            nombre = "Air Max 90",
            marca = "Nike",
            precio = 1299.99,
            descripcion = "Zapatilla clásica con amortiguación Air visible. Ideal para uso casual y urbano.",
            categoria = "Casual",
            color = "Blanco"
        ),
        Zapatilla(
            id = 2,
            nombre = "Ultraboost 22",
            marca = "Adidas",
            precio = 1599.99,
            descripcion = "Zapatilla de alto rendimiento con tecnología Boost para máxima energía en cada paso.",
            categoria = "Running",
            color = "Negro"
        ),
        Zapatilla(
            id = 3,
            nombre = "Chuck Taylor",
            marca = "Converse",
            precio = 899.99,
            descripcion = "El clásico más icónico del mundo. Lona resistente y estilo atemporal.",
            categoria = "Casual",
            color = "Negro"
        ),
        Zapatilla(
            id = 4,
            nombre = "Old Skool",
            marca = "Vans",
            precio = 999.99,
            descripcion = "Diseño skate clásico con la icónica franja lateral. Suela de goma vulcanizada.",
            categoria = "Skate",
            color = "Negro"
        ),
        Zapatilla(
            id = 5,
            nombre = "Gel-Kayano 29",
            marca = "Asics",
            precio = 1899.99,
            descripcion = "Máximo soporte y estabilidad para corredores de larga distancia.",
            categoria = "Running",
            color = "Azul"
        ),
        Zapatilla(
            id = 6,
            nombre = "Suede Classic",
            marca = "Puma",
            precio = 1099.99,
            descripcion = "Icónica zapatilla de gamuza con estilo retro. Un clásico que nunca pasa de moda.",
            categoria = "Casual",
            color = "Azul"
        ),
        Zapatilla(
            id = 7,
            nombre = "React Infinity",
            marca = "Nike",
            precio = 1799.99,
            descripcion = "Diseñada para reducir lesiones con espuma React ultra suave y resistente.",
            categoria = "Running",
            color = "Rosa"
        ),
        Zapatilla(
            id = 8,
            nombre = "Forum Low",
            marca = "Adidas",
            precio = 1199.99,
            descripcion = "Regresa el clásico de los 80s con un diseño limpio y moderno.",
            categoria = "Casual",
            color = "Blanco"
        )
    )
}
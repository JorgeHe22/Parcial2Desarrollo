package com.example.parcial2desarrollo.Screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ProductoViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>()
    val carrito = mutableStateListOf<Producto>()
    private var contadorId = 0

    fun agregarProducto(nombre: String, precio: Double, descripcion: String, imagenUrl: String) {
        productos.add(Producto(contadorId++, nombre, precio, descripcion, imagenUrl))
    }

    fun agregarAlCarrito(producto: Producto) {
        carrito.add(producto)
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun totalCarrito(): Double {
        return carrito.sumOf { it.precio }
    }
    fun vaciarCarrito() {
        carrito.clear()
    }
    fun eliminarProducto(producto: Producto) {
        productos.remove(producto)
    }

}
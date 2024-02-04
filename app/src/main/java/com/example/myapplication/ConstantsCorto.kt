package com.example.myapplication

import android.content.Context
import android.content.res.Resources


object ConstantsCorto {
    var texto:String = "Esta es una breve descripción de este articulo " +
            "que define las principales caracteristicas del contenido"
    // Arraylist and return the Arraylist
    fun getProducto_CortoData():ArrayList<ProductoCorto>{
        // create an arraylist of type Producto_Corto class
        val Producto_CortoList=ArrayList<ProductoCorto>()
        val emp1=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp1)
        val emp2=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp2)
        return Producto_CortoList
    }
}

package com.example.myapplication

import android.content.Context
import android.content.res.Resources


object Constants {
    var texto:String = "Esta es una extendida descripción de un articulo concreto" +
            "donde se definen todas las caracteristicas ya sea para el " +
            "entendimiento o la compra según si se encuentra en la sección " +
            "de compras o de wikimedia."
    // Arraylist and return the Arraylist
    fun getProducto_LargoData():ArrayList<Producto_Largo>{
        // create an arraylist of type Producto_Largo class
        val Producto_LargoList=ArrayList<Producto_Largo>()
        val emp1=Producto_Largo(texto, R.drawable.producto_grafica)
        Producto_LargoList.add(emp1)
        val emp2=Producto_Largo(texto, R.drawable.productos_pc)
        Producto_LargoList.add(emp2)
        val emp3=Producto_Largo(texto, R.drawable.producto_placa)
        Producto_LargoList.add(emp3)
        val emp4=Producto_Largo(texto, R.drawable.producto_rpi)
        Producto_LargoList.add(emp4)

        val emp5=Producto_Largo(texto, R.drawable.producto_grafica)
        Producto_LargoList.add(emp5)
        val emp6=Producto_Largo(texto, R.drawable.productos_pc)
        Producto_LargoList.add(emp6)
        val emp7=Producto_Largo(texto, R.drawable.producto_placa)
        Producto_LargoList.add(emp7)
        val emp8=Producto_Largo(texto, R.drawable.producto_rpi)
        Producto_LargoList.add(emp8)
        return Producto_LargoList
    }
}

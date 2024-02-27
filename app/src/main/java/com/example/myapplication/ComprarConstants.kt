package com.example.myapplication

import android.database.Cursor


class ComprarConstants(mybd: Database) {
    val db: Database
    init {
        db = mybd
    }


    /*var texto:String = "Esta es una extendida descripción de un articulo concreto" +
            "donde se definen todas las caracteristicas ya sea para el " +
            "entendimiento o la compra según si se encuentra en la sección " +
            "de compras o de wikimedia."*/
    // Arraylist and return the Arraylist
    fun getProducto_LargoData():ArrayList<ComprarProducto>{
        // create an arraylist of type Producto_Largo class

        val Producto_LargoList: ArrayList<ComprarProducto>
        Producto_LargoList=ArrayList<ComprarProducto>()
        for(i in 1..3) {
            var productos_result: Cursor? = db.getProductos()
            var exit = false
            while (!exit) {
                val myList = mutableListOf<Any>()
                if (productos_result?.moveToNext() == true) {
                    myList.add(productos_result.getString(3))
                    myList.add(productos_result.getInt(1))
                } else {
                    exit = true
                }
                if (!exit) {
                    var pc: ComprarProducto = ComprarProducto(myList.get(0).toString(), myList.get(1).toString().toInt())
                    Producto_LargoList.add(pc)
                }
            }
        }
        /*
        val emp1=ComprarProducto(texto, R.drawable.producto_grafica)
        Producto_LargoList.add(emp1)
        val emp2=ComprarProducto(texto, R.drawable.productos_pc)
        Producto_LargoList.add(emp2)
        val emp3=ComprarProducto(texto, R.drawable.producto_placa)
        Producto_LargoList.add(emp3)
        val emp4=ComprarProducto(texto, R.drawable.producto_rpi)
        Producto_LargoList.add(emp4)

        val emp5=ComprarProducto(texto, R.drawable.producto_grafica)
        Producto_LargoList.add(emp5)
        val emp6=ComprarProducto(texto, R.drawable.productos_pc)
        Producto_LargoList.add(emp6)
        val emp7=ComprarProducto(texto, R.drawable.producto_placa)
        Producto_LargoList.add(emp7)
        val emp8=ComprarProducto(texto, R.drawable.producto_rpi)
        Producto_LargoList.add(emp8)

        val emp9=ComprarProducto(texto, R.drawable.producto_grafica)
        Producto_LargoList.add(emp9)
        val emp10=ComprarProducto(texto, R.drawable.productos_pc)
        Producto_LargoList.add(emp10)
        val emp11=ComprarProducto(texto, R.drawable.producto_placa)
        Producto_LargoList.add(emp11)
        val emp12=ComprarProducto(texto, R.drawable.producto_rpi)
        Producto_LargoList.add(emp12)
         */
        return Producto_LargoList
    }
}

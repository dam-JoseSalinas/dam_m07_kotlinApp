package com.example.myapplication

import android.database.Cursor


class BuscarConstants(mydb: Database) {
    val db: Database
    init {
        db = mydb
    }



    /*var texto:String = "Esta es una breve descripción de este articulo " +
            "que define las principales caracteristicas del contenido"*/
    // Arraylist and return the Arraylist

    fun getProducto_CortoData():ArrayList<BuscarProducto>{
        // create an arraylist of type Producto_Corto class


        val Producto_CortoList: ArrayList<BuscarProducto>
        Producto_CortoList=ArrayList<BuscarProducto>()
        for (i  in 1..3) {
            var productos_result: Cursor? = db.getProductos()
            var exit = false
            while (!exit) {
                val myList = mutableListOf<Any>()
                for (j in 1..2) {
                    if (productos_result?.moveToNext() == true) {
                        myList.add(productos_result.getString(2))
                        myList.add(productos_result.getInt(1))
                    } else {
                        exit = true
                    }


                }
                if (!exit) {
                    var pc: BuscarProducto = BuscarProducto(myList.get(0).toString(), myList.get(1).toString().toInt(), myList.get(2).toString(), myList.get(3).toString().toInt())
                    Producto_CortoList.add(pc)
                }
            }
            productos_result?.close();
        }





        /*
        val emp1=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp1)

        val emp2=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp2)

        val emp3=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp3)

        val emp4=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp4)

        val emp5=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp5)

        val emp6=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp6)

        val emp7=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp7)

        val emp8=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp8)

        val emp9=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp9)

        val emp10=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp10)

        val emp11=ProductoCorto(texto, R.drawable.producto_grafica, texto, R.drawable.productos_pc)
        Producto_CortoList.add(emp11)

        val emp12=ProductoCorto(texto, R.drawable.producto_placa, texto, R.drawable.producto_rpi)
        Producto_CortoList.add(emp12)
         */

        return Producto_CortoList

    }
}

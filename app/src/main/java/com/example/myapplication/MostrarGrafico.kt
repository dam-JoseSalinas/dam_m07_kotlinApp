package com.example.myapplication

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.example.myapplication.databinding.MainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Random
import kotlin.math.roundToInt

class MostrarGrafico : AppCompatActivity() {
    private lateinit var btnAgregar: Button

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var spinner: Spinner
    private lateinit var editText5: EditText

    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_grafico)

        val puntos = ArrayList<Bar>()

        btnAgregar = findViewById(R.id.btnAgregar)

        editText1 = findViewById(R.id.editTextFullName)
        editText2 = findViewById(R.id.editTextUsername)
        editText3 = findViewById(R.id.editTextAge)
        spinner = findViewById(R.id.spinnerReportType)
        editText4 = findViewById(R.id.editTextStartDate)
        editText5 = findViewById(R.id.editTextEndDate)

        editText4.setOnClickListener {
            showDatePickerDialog(editText4)
        }

        editText5.setOnClickListener {
            showDatePickerDialog(editText5)
        }
        btnAgregar.setOnClickListener {
            graficarBarras(puntos)
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateEditText(editText)
        }

        val datePickerDialog = DatePickerDialog(
            this,
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateDateEditText(editText: EditText) {
        val dateFormat = "dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        editText.setText(simpleDateFormat.format(calendar.time))
    }

    fun rand(from: Int, to: Int) : Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }
    private fun differenceInMonths(startDateCal: Calendar, endDateCal: Calendar): Int {
        val startYear = startDateCal.get(Calendar.YEAR)
        val startMonth = startDateCal.get(Calendar.MONTH)
        val endYear = endDateCal.get(Calendar.YEAR)
        val endMonth = endDateCal.get(Calendar.MONTH)

        return (endYear - startYear) * 12 + endMonth - startMonth
    }

    fun graficarBarras(puntos: ArrayList<Bar>) {

        //Spinner tipo de grafico
         val tipoGrafico: String = spinner.selectedItem.toString()

        val startDate = editText4.text.toString()
        val endDate = editText5.text.toString()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val startDateCal = Calendar.getInstance()
        val endDateCal = Calendar.getInstance()

        startDateCal.time = dateFormat.parse(startDate)!!
        endDateCal.time = dateFormat.parse(endDate)!!

        val differenceInMonths = differenceInMonths(startDateCal, endDateCal)

        for (i in 0 until differenceInMonths) {
            val barra = Bar()
            val monthName = startDateCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
            barra.name = monthName
            val color = generarColorHexadecimalAleatorio()
            barra.color = Color.parseColor(color)
            if (tipoGrafico.equals("Informe de actividad")) {
                barra.value = rand(10, 30).toFloat()
            } else if(tipoGrafico.equals("Informe de mas comprados")) {
                barra.value = rand(10, 90).toFloat()
            } else if(tipoGrafico.equals("Informe de menos comprados")) {
                barra.value = rand(1, 5).toFloat()
            }

            puntos.add(barra)
            startDateCal.add(Calendar.MONTH, 1)
        }




        val grafica = findViewById<View>(R.id.graphBar) as BarGraph
        grafica.bars = puntos
    }

    fun generarColorHexadecimalAleatorio(): String {
        val letras = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var color = "#"
        for (i in 0..5) {
            color += letras[(Math.random() * 15).roundToInt()]
        }

        return color
    }
}

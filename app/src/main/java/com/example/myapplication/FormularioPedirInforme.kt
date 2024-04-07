package com.example.myapplication
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.text.TextPaint
import android.view.View
import androidx.core.app.ActivityCompat
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FormularioPedirInforme : AppCompatActivity() {

    private lateinit var editTextFullName: EditText
    private lateinit var editTextUsername: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextRole: EditText
    private lateinit var editTextStartDate: EditText
    private lateinit var editTextEndDate: EditText
    private lateinit var generarBtn: Button
    private lateinit var spinner: Spinner

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var dataInfo:String

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_pedir_informe)
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextAge = findViewById(R.id.editTextAge)
        editTextRole = findViewById(R.id.editTextRole)
        spinner = findViewById(R.id.spinnerInforme)
        editTextStartDate = findViewById(R.id.editTextStartDate)
        editTextEndDate = findViewById(R.id.editTextEndDate)
        generarBtn = findViewById(R.id.generar_pdf)
        editTextStartDate.setOnClickListener {
            showDatePickerDialog(editTextStartDate)
        }

        editTextEndDate.setOnClickListener {
            showDatePickerDialog(editTextEndDate)
        }

        if(checkPermission()) {
            Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG)
        } else {
            requestPermissions()
        }

        generarBtn.setOnClickListener(View.OnClickListener {
            val tipoInforme: String = spinner.selectedItem.toString()
            title = tipoInforme
            if (tipoInforme.equals("Informe de actividad")) {

                description = "Este es un informe autogenerado que evalua la actividad de inicio de sesion. Aqui se expondran el numero de veces que el usuario accede a la aplicacion por dia o por mes"
                dataInfo = "Nombre Completo: " + editTextFullName.text.toString() + "\n" +
                            "Nombre de usuario: " + editTextUsername.text.toString() + "\n" +
                            "Edad: " + editTextAge.text.toString() + "\n" +
                            "La persona usuaria con los datos establecidos tiene registrados los siguientes inicios de sesion en el intervalo del " + editTextStartDate.text.toString() +
                            " al " + editTextEndDate.text.toString() + ":\n" + rand(1, 80).toString()
            } else if(tipoInforme.equals("Informe de mas comprados")) {
                var arraystrings:ArrayList<String> = ArrayList<String>()
                arraystrings.add("PC gamer")
                arraystrings.add("Raspberry pi")
                arraystrings.add("Targeta grafica")
                arraystrings.add("Pantalla LED")
                description = "Este es un informe autogenerado que compara los productos mas comprados. Aqui se expondran el numero de compras del producto por dia o por mes"
                dataInfo = "Nombre Completo: " + editTextFullName.text.toString() + "\n" +
                        "Nombre de usuario: " + editTextUsername.text.toString() + "\n" +
                        "Edad: " + editTextAge.text.toString() + "\n" +
                        "Durant el periode del " + editTextStartDate.text.toString() +
                        " al " + editTextEndDate.text.toString() + " el producte mes comprat ha sigut " + arraystrings.get(rand(0, 3))
            } else if(tipoInforme.equals("Informe de menos comprados")) {
                description = "Este es un informe autogenerado que compara los productos menos comprados. Aqui se expondran el numero de compras del producto por dia o por mes"
                var arraystrings:ArrayList<String> = ArrayList<String>()
                arraystrings.add("PC gamer")
                arraystrings.add("Raspberry pi")
                arraystrings.add("Targeta grafica")
                arraystrings.add("Pantalla LED")
                dataInfo = "Nombre Completo: " + editTextFullName.text.toString() + "\n" +
                        "Nombre de usuario: " + editTextUsername.text.toString() + "\n" +
                        "Edad: " + editTextAge.text.toString() + "\n" +
                        "Durant el periode del " + editTextStartDate.text.toString() +
                        " al " + editTextEndDate.text.toString() + " el producte mes comprat ha sigut " + arraystrings.get(rand(0, 3))
            }
            generarPdf()


        })
    }

    fun generarPdf() {

        var pdfDocument = PdfDocument()
        var paint = Paint()
        //var bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_logo)
        //var bitmapEscala = Bitmap.createScaledBitmap(bitmap, 80,80, false)
        var titulo = TextPaint()
        titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
        titulo.textSize = 20f

        var descripcion = TextPaint()
        descripcion.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        descripcion.textSize = 14f

        var datosInforme = TextPaint()
        datosInforme.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        datosInforme.textSize = 14f

        var paginaInfo = PdfDocument.PageInfo.Builder(816, 1054, 1).create()
        var pagina1 = pdfDocument.startPage(paginaInfo)
        var canvas = pagina1.canvas


        //canvas.drawBitmap(bitmapEscala, 368f, 20f, paint)

        canvas.drawText(title, 10f, 150f, titulo)

        canvas.drawText(description, 10f, 200f, descripcion)
        /*var arrDescripcion = descripcionText.split("\n")

        var y = 200f
        for (item in arrDescripcion) {
            canvas.drawText(item, 10f, y, descripcion)
            y += 15
        }*/
        canvas.drawText(dataInfo, 10f, 200f, datosInforme)
        pdfDocument.finishPage(pagina1)

        val file = File(Environment.getExternalStorageDirectory(), "informe"+rand(1,1000).toString()+".pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(this, "Se creo el PDF correctamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        pdfDocument.close()

    }
    fun rand(from: Int, to: Int) : Int {
        val random = Random()
        return random.nextInt(to - from) + from
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

    private fun checkPermission(): Boolean {
        val permission1 = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        val permission2 = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
            200
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 200) {
            if(grantResults.size > 0) {
                val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if(writeStorage && readStorage) {
                    Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_LONG)
                } else {
                    Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_LONG)
                    finish()
                }
            }
        }
    }



}

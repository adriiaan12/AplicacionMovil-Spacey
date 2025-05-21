package com.example.consumo_api

import android.R.attr.textSize
import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast


import com.example.consumo_api.modules.Animal
import java.io.File
import java.io.FileOutputStream

fun generarPdf(context: Context, animales: List<Animal>) {
    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
    val page = document.startPage(pageInfo)

    val canvas = page.canvas
    val paint = Paint().apply {
        textSize = 12f
    }

    var y = 25
    animales.forEach { animal ->
        canvas.drawText("Nombre: ${animal.nombre}, Especie: ${animal.especie}", 10f, y.toFloat(), paint)
        y += 20
    }

    document.finishPage(page)

    // Guardar en almacenamiento interno.
    //Si se ejecuta en el emulador se toma la ruta View-> Tool Windows-> Device Explorer
    // Navegamos hasta sdcard-> Android -> com.example.tuproyecto-> files
    val file = File(context.getExternalFilesDir(null), "animales.pdf")
    document.writeTo(FileOutputStream(file))
    document.close()

    Toast.makeText(context, "PDF guardado en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
}
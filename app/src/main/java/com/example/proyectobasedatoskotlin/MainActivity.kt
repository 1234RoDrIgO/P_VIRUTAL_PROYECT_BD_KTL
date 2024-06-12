package com.example.proyectobasedatoskotlin

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.proyectobasedatoskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var baseDBHelper: mySqlite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Usa binding.root para configurar la vista
        setContentView(binding.root)


        // Inicializa la base de datos
        baseDBHelper = mySqlite(this)



        binding.guardar.setOnClickListener {
            if(binding.editTextText1.text.isNotBlank() &&
                binding.editTextText2.text.isNotBlank()){

                baseDBHelper.aniadeDato(binding.editTextText1.text.toString(), binding.editTextText2.text.toString())

                binding.editTextText1.text.clear()
                binding.editTextText2.text.clear()

                Toast.makeText(this, "Registro Guardado !", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "No se ha Guardado", Toast.LENGTH_LONG).show()
            }
        }

        binding.consultar.setOnClickListener {

            // limpia la tabla
            val count = binding.tableLayout.childCount
            if (count > 1) {
                binding.tableLayout.removeViews(1, count - 1)
            }

            val db: SQLiteDatabase = baseDBHelper.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM BASE",
                                    null)
            if(cursor.moveToFirst()){
                do{
                    val tableRow = TableRow(this)

                    val textViewId = TextView(this).apply {
                        text = cursor.getInt(0).toString()
                        setPadding(8, 8, 8, 8)
                    }
                    val textViewCol1 = TextView(this).apply {
                        text = cursor.getString(1).toString()
                        setPadding(8, 8, 8, 8)
                    }
                    val textViewCol2 = TextView(this).apply {
                        text = cursor.getString(2).toString()
                        setPadding(8, 8, 8, 8)
                    }

                    tableRow.addView(textViewId)
                    tableRow.addView(textViewCol1)
                    tableRow.addView(textViewCol2)

                    binding.tableLayout.addView(tableRow)
                }while (cursor.moveToNext())
            }
        }

    }
}



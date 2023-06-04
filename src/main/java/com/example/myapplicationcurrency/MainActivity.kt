package com.example.myapplicationcurrency

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val egypt = "Egyptian Pound"
    val saudi = "Saudi RS"
    val amrecan = "Amercan Dollar"

    val values = mapOf(

        amrecan to 1.0 ,
        egypt to 15.73 ,
        saudi to 3.75

    )

    lateinit var toMenu : AutoCompleteTextView
    lateinit var fromMenu : AutoCompleteTextView
    lateinit var convertBtn: Button
    lateinit var amountETxt : TextInputEditText
    lateinit var resultETxt: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        iniVeiws()
        adapterDropMenu()
        convertButton()

    }

    private fun convertButton (){

        convertBtn.setOnClickListener{

            calResult()

        }
    }

    private fun calResult (){

        if (amountETxt.text.toString().isNotEmpty()) {
            val amount = amountETxt.text.toString().toDouble()
            val toValue = values[toMenu.text.toString()]
            val fromValue = values[fromMenu.text.toString()]
            val result = amount.times(toValue!!.div(fromValue!!))
            resultETxt.setText(result.toString())
        }else {
            Snackbar.make(amountETxt,"amount is required",Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun iniVeiws(){
        convertBtn = findViewById(R.id.convertButton)
        amountETxt = findViewById(R.id.amountETxt)
        resultETxt = findViewById(R.id.result)
        toMenu = findViewById(R.id.toMenuList)
        fromMenu = findViewById(R.id.fromMenu)

    }

    private fun adapterDropMenu (){

        val listOfCurrncy = listOf(egypt,saudi,amrecan)
        val adapter = ArrayAdapter (this,R.layout.drop_down_list_view,listOfCurrncy)
        toMenu.setAdapter(adapter)
        fromMenu.setAdapter(adapter)

    }

    override fun onCreateOptionsMenu (menu: Menu?): Boolean {

        val menuInflater : MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_Action -> {
                Toast.makeText (this,"shear is done",Toast.LENGTH_LONG).show()
                Log.d( "MainActivity", "onOptionsItemSelected: shear done")
            }
            R.id.Setting_Action -> {
                Toast.makeText (this,"Setting is done",Toast.LENGTH_SHORT).show()
            }
            R.id.ContactUs_Action -> {
                Toast.makeText (this,"Conatct Us is done",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}




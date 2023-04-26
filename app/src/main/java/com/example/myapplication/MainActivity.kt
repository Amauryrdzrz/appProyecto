package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    private val apiService by lazy {
        ApiService.create()
    }
    private lateinit var binding: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btnScan)

        button.setOnClickListener{

            initScaner()

        }

    }
    private fun initScaner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
        //para encender el flash de la cámara
        //integrator.setTorchEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null){
            if(result.contents == null){
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()

            }else{
                validQr(result.contents)
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun validQr(Codigo: String){

        var call = apiService.validateCode(Codigo)

        call.enqueue(object :Callback<Void>{

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                try {

                    if(response.isSuccessful)
                    {
                        viewHome()
                    }
                }catch (e:Exception){

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun viewHome()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
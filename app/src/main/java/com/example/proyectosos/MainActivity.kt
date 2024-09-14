import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    var Enviar: Button? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Enviar = findViewById(R.id.btnEnviar) as Button?
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest.permission.SEND_SMS
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MainActivity, Manifest
                    .permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.SEND_SMS),
                1000
            )
        } else {
        }

        Enviar!!.setOnClickListener {
            enviarMensaje(
                "Celular aquí",
                "SOS! Necesito Ayuda!"
            )
        }
    }

    private fun enviarMensaje(numero: String, mensaje: String) {
        try {
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(numero, null, mensaje, null, null)
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(
                getApplicationContext(),
                "Mensaje no enviado, datos incorrectos.",
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }
    }
}



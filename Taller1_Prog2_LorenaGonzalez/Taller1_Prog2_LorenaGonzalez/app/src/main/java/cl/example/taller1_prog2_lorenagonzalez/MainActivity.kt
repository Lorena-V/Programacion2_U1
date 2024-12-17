package cl.example.taller1_prog2_lorenagonzalez

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.example.taller1_prog2_lorenagonzalez.Modelo.CuentaMesa
import cl.example.taller1_prog2_lorenagonzalez.Modelo.ItemMenu
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var etCantPastelChoclo: EditText? = null
    private var etCantCazuela: EditText? = null
    private lateinit var tvComidaTotal: TextView
    private var tvPropina: TextView? = null
    private var tvTotalTotal: TextView? = null
    private lateinit var switchPropina: Switch

    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 36000)
    private val cazuela = ItemMenu("Cazuela", 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asigna componentes de la vista
        etCantPastelChoclo = findViewById<EditText>(R.id.etCantPastelChoclo)
        etCantCazuela = findViewById<EditText>(R.id.etCantCazuela)
        tvComidaTotal = findViewById<TextView>(R.id.tvComidaTotal)
        tvPropina = findViewById<TextView>(R.id.tvPropina)
        tvTotalTotal = findViewById<TextView>(R.id.tvTotalTotal)
        switchPropina = findViewById<Switch>(R.id.switchPropina)

        // Evento TextChanged y CheckedChange
        val textWatcher:TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                actualizarTotales()
            }
        }

        etCantPastelChoclo?.addTextChangedListener(textWatcher)
        etCantCazuela?.addTextChangedListener(textWatcher)

        // actualizar el total al cambiar el estado del Switch
        switchPropina.setOnCheckedChangeListener { _, _ ->
            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        // Toma los datos ingresados por el usuario
        val cantidadPastel = etCantPastelChoclo?.text.toString().toIntOrNull() ?: 0
        val cantidadCazuela = etCantCazuela?.text.toString().toIntOrNull() ?: 0

        // Calculo de totales
        val totalComida = (cantidadPastel * pastelDeChoclo.precio) + (cantidadCazuela * cazuela.precio)

        // Calculo propina
        val propina = if (switchPropina.isChecked) (totalComida * 0.1).toInt() else 0

        // Calcular el total final
        val totalFinal = totalComida + propina

        // Formateo de valores monetarios como pesos chilenos usando NumberFormat
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

        // Mostrar los resultados en los TextView
        tvComidaTotal.text = formatoMoneda.format(totalComida)
        tvPropina?.text = formatoMoneda.format(propina)
        tvTotalTotal?.text = formatoMoneda.format(totalFinal)
    }
}

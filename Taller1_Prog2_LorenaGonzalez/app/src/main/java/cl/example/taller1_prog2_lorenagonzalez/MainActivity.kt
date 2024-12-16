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

class MainActivity : AppCompatActivity() {
    private lateinit var etCantPastelChoclo: EditText
    private lateinit var etCantCazuela: EditText
    private lateinit var tvComidaTotal: TextView
    private lateinit var tvPropina: TextView
    private lateinit var tvTotalTotal: TextView
    private lateinit var switchPropina: Switch

    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 36000)
    private val cazuela = ItemMenu("Cazuela", 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asignar los componentes de la vista
        etCantPastelChoclo = findViewById(R.id.etCantPastelChoclo)
        etCantCazuela = findViewById(R.id.etCantCazuela)
        tvComidaTotal = findViewById(R.id.tvComidaTotal)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotalTotal = findViewById(R.id.tvTotalTotal)
        switchPropina = findViewById(R.id.switchPropina)

        // TextWatcher para detectar cambios en cantidades
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                actualizarTotales()
            }
        }

        etCantPastelChoclo.addTextChangedListener(textWatcher)
        etCantCazuela.addTextChangedListener(textWatcher)

        // actualizar el total al cambiar el estado del Switch
        switchPropina.setOnCheckedChangeListener { _, _ ->
            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        // Obtener las cantidades ingresadas por el usuario
        val cantidadPastel = etCantPastelChoclo.text.toString().toIntOrNull() ?: 0
        val cantidadCazuela = etCantCazuela.text.toString().toIntOrNull() ?: 0

        // Calcular el total de los platos
        val totalComida = (cantidadPastel * pastelDeChoclo.precio) + (cantidadCazuela * cazuela.precio)

        // Calcular la propina (10% del total de comida)
        val propina = if (switchPropina.isChecked) (totalComida * 0.1).toInt() else 0

        // Calcular el total final
        val totalFinal = totalComida + propina

        // Mostrar los resultados en los TextView
        tvComidaTotal.text = "$$totalComida"
        tvPropina.text = "$$propina"
        tvTotalTotal.text = "$$totalFinal"
    }
}

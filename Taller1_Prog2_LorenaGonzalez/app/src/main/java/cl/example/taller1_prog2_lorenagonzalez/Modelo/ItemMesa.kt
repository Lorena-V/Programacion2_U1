package cl.example.taller1_prog2_lorenagonzalez.Modelo

class ItemMesa( val itemMenu: ItemMenu, val cantidad: Int ) {

    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}
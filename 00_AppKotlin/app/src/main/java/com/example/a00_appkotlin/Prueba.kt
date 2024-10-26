package com.example.a00_appkotlin


fun main() {
    println("Hello, world!!!")
    var cadena1 = "Esto es una cadena"
    //cadena1 = 5;
    var cadena2: String = "esto es otra cadena"
    println("El valor de cadena1 es: $cadena1")

    val constanteNumerica: Int = 56

    if (constanteNumerica == 56) {
        println("El valor es 56")
    } else {
        println("No es 56")
    }

    if (cadena1 == "Esto es una cadena") {
        println("Es verdadero")
    }

    //EN kotlin se puede usar un if como expresion
    var cadena3 =
        if (constanteNumerica == 56) "El valor mediante expresion es 56" else "El valor mediante expresion no es 56"
    println(cadena3)

    var cadena4 = ""
    if (constanteNumerica == 56) {
        cadena4 = "El valor mediante expresion es 56"
    } else {
        cadena4 = "El valor mediante expresion no es 56"
    }

    when (constanteNumerica) {
        59 -> println("Valgo 59")
        56 -> println("Valgo 56")
    }
    println(cadena4.get(0))

    //Nulos, hay que declarlos explicitamente con "?"
    var cadena5: String? = null
    println(cadena5)
    cadena5 = "valorNoNulo"
    println(cadena5)
    println(cadena5?.get(0))
    cadena5 = null
    println(cadena5?.get(0))
    println(cadena5?.get(0) ?: "El valor es nulo")
    //println(cadena5!!.get(0))

    //For
    for (i in 1..5) {
        println(i)
    }

    var cierto: Boolean = true
    //while
    while (cierto) {
        println("Eres un muñon")
        cierto = false
    }

    funcionSaludar()
    funcionSaludarConNombre("Muñon")
    val n1 = sumar(4, 5)
    println(n1)

    //Las funciones en kotlin se pueden usar como expresiones
    val funcion1 = funcionSaludar()
    funcion1
    val funcion2 = funcionSaludarConNombre("Pepe")
    funcion2

    println(restar(4, 3))

    //listas
    val listaNumeros = listOf(5, 6, 7)
    println(listaNumeros.get(0))
    for (i in listaNumeros) {
        println(i)
    }

    for (i in listaNumeros.indices) {
        println("Indice: $i valor: ${listaNumeros.get(i)}")
    }

    //listaNumeros.add
    val listaNumerosMutable = mutableListOf(7, 8, 9)
    listaNumerosMutable.add(10)
    println(listaNumerosMutable.get(3))


}

fun funcionSaludar() {
    println("Hola muñoncete")
}

//Es lo mismo poner : Unit que no poner nada
fun funcionSaludarSinSalida(): Unit {
    println("Hola muñoncete")
}

fun funcionSaludarConNombre(nombre: String) {
    println("Hola $nombre")
}

fun sumar(n1: Int, n2: Int): Int {
    return n1 + n2
}

fun restar(n1: Int, n2: Int) = n2 - n1

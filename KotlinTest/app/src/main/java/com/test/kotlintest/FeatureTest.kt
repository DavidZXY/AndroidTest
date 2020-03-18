package com.test.kotlintest

private class FeatureTest {

    //http://148.70.141.88:8080/swagger-ui.html

    val a : Int = 1
    var b = 1

    var x = 1

    fun a () {
        x += 1
        b += 2
    }

    fun method () = 123

    private fun asdad () {
        """
            
        """.trimIndent()
        method()
    }

}


fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun vars(vararg v : Int) {
    for (a in v)
        println(a)
}

fun main() {
    var a : Int = 1
    val b : Int = 1
}


fun getSomeString() = "asd"


fun noReturnMethod() {

}
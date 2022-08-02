package x.y

fun main() {

    println("=========")
    println(gcd(15, 25))
    println("")

    println("=========")
    println(gcd(25, 15))
    println("")

    println("=========")
    println(gcd(75, 50))
    println("")

    println("=========")
    println(gcd(121, 66))
    println("")

    println("=========")
    println(gcd(121, 88))
    println("")
}

fun gcd(p: Int, q: Int): Int {

    if (q == 0) {
        return p
    }

    val r = p % q

    println("p=$p, q=$q, r=$r")
    return gcd(q, r)
}
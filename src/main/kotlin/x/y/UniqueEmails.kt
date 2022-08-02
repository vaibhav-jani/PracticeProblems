package x.y

fun main() {
    val arr = arrayOf(
        "test.email+alex@leetcode.com",
        "test.e.mail+bob.cathy@leetcode.com",
        "testemail+david@lee.tcode.com"
    )
    val result = numUniqueEmails(arr)
    println("Result: $result")
}

fun numUniqueEmails(emails: Array<String>): Int {
    val uniqueHashset = HashSet<String>()
    emails.forEachIndexed { index, email ->
        val splits = email.split("@").toTypedArray()
        val domain = splits[1]
        val splitsLocalName = splits[0].split("+")
        val localNameBeforePlus = splitsLocalName[0]
        val localNameFiltered = localNameBeforePlus.replace(".", "")
        val address = "$localNameFiltered@$domain"
        uniqueHashset.add(address)
    }
    return uniqueHashset.size
}

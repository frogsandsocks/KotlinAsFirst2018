package exam

import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException


fun setsIntersect(inputName: String, expr: String): Set<Int> {


    /* Checking arguments for correctness */
    File(inputName).readLines().forEach {

        if (!(Regex("""[A-Z]\s=(\s(-*)[0-9]+,)*\s[0-9]""")).matches(it)) {
            throw IllegalArgumentException()
        }

    }

    /* Creating a map with given sets and their names */
    var setsMap = mutableMapOf<String, Set<Int>>()


    /* Reading given file by lines */
    File(inputName).readLines().forEach { line ->

        /* The first char of every string is a name of the set */
        val setName = line[0].toString()

        /* Remove the first four chars with a name of the set and split line to elements of set */
        val setCurrent = line
                .drop(4)
                .split(", ")
                .map { it.toInt() }
                .toSet()

        /* Add new set to the map */
        setsMap[setName] = setCurrent

    }


    /* Parse string with expression and extract multipliers */
    val expressionRead = expr.split(" ").toMutableList()
    expressionRead.removeIf { it == "&" }

    /* Assign first set to result and throw IllegalArgumentException if there are no set for given expression */
    var result = setsMap[expressionRead[0]] ?: throw IllegalArgumentException()


    /* Iterate throw multipliers in expression and find sets with inversion */
    expressionRead.forEach {

        /* Flag for sets with inversion */
        var setInversionFlag = false

        /* Change variable 'it' to mutable variable */
        var char = it

        /* If set with inversion ("!*set*"): */
        if (char[0] == '!') {

            /* Delete first char ("!" - pointer to set with inversion) and set flag to 1 */
            char = char.drop(1)
            setInversionFlag = true
        }


        /* Find set in sets' map or throw IllegalArgumentException */
        var currentSet = setsMap[char] ?: throw IllegalArgumentException()


        /* If a set with inversion -> result is the previous result without elements from current set  */
        if (setInversionFlag) {

            currentSet = result.minus(currentSet)
        }


        /* Return result - previous result AND current set */
        result = result.intersect(currentSet)


    }

    return result
}

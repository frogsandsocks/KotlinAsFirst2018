@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumByDouble { it * it })


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */

fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.sum() / list.size else 0.0


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */

fun center(list: MutableList<Double>): MutableList<Double> {
    val average = list.sum() / list.size

    list.replaceAll { item -> item - average }

    return list
}


/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */

fun times(a: List<Double>, b: List<Double>): Double = a
        .zip(b) { aCoordinate, bCoordinate -> aCoordinate * bCoordinate }
        .sum()


/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.я
 */

fun polynom(p: List<Double>, x: Double): Double = p.foldIndexed(0.0) { index, previousItem, item ->
    previousItem + item * x.pow(index)
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {

    for (i in 1..list.lastIndex) {
        list[i] += list[i - 1]
    }

    return list
}


/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {

    var remainder = n.toDouble()
    val dividers = mutableListOf<Int>()
    var integersIterator = if (n % 2 == 0) 2 else 3

    val adder = if (n % 2 == 0) 1 else 2


    while ((remainder > 1) && (integersIterator <= n / 2)) {

        while (remainder % integersIterator == 0.0) {

            remainder /= integersIterator
            dividers.add(integersIterator)
        }

        integersIterator += adder
    }

    if (dividers.isEmpty()) {
        dividers.add(n)
    }

    return dividers.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {

    val nConverted = mutableListOf<Int>()
    var buffer = n

    while (buffer > 0) {
        nConverted.add(buffer % base)
        buffer /= base
    }

    return if (n > 0) nConverted.reversed() else listOf(0)
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */

fun convertToString(n: Int, base: Int): String {

    val list = convert(n, base)

    /*
    * A correlation between given number more than 9 (for numeral systems
    * more than decimal) and a char in ASCII code for this number:
    *
    * number + baseOffset = ASCII code for number in letter form
    */
    val baseOffset = 87

    return list.joinToString(separator = "") {
        if (it > 9) "${(it + baseOffset).toChar()}"
        else "$it"
    }
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */

fun decimal(digits: List<Int>, base: Int): Int = digits.foldIndexed(0) { index, previousResult, item ->
    previousResult + item * base.toDouble().pow(digits.lastIndex - index).toInt()
}


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */

fun decimalFromString(str: String, base: Int): Int {

    /*
    * A correlation between given number more than 9 (for numeral systems
    * more than decimal) and a char in ASCII code for this number:
    *
    * ASCII char code for number in letter form - baseOffset = number in decimal form
    */
    val baseOffset = 87

    val integersList = str.map {
        if (it in ('0'..'9')) it.toString().toInt()
        else it.toInt() - baseOffset
    }

    return decimal(integersList, base)
}


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {

    val numbers = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val numbersToString = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    var numbersIndex = 0
    var buffer = n
    var result = ""

    while (numbersIndex < 13) {
        if (buffer - numbers[numbersIndex] >= 0) {
            buffer -= numbers[numbersIndex]
            result += numbersToString[numbersIndex]
        } else {
            numbersIndex++
        }
    }

    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */


fun hundredsToRussian(n: Int): String {

    val units = mapOf(
            0 to "", 1 to "один", 2 to "два", 3 to "три",
            4 to "четыре", 5 to "пять", 6 to "шесть",
            7 to "семь", 8 to "восемь", 9 to "девять"
    )

    val fromTenToTwenty = mapOf(
            11 to "одиннадцать", 12 to "двенадцать", 13 to "тринадцать",
            14 to "четырнадцать", 15 to "пятнадцать", 16 to "шестнадцать",
            17 to "семнадцать", 18 to "восемнадцать", 19 to "девятнадцать"
    )

    val tens = mapOf(
            0 to "",
            1 to "десять", 2 to "двадцать", 3 to "тридцать",
            4 to "сорок", 5 to "пятьдесят", 6 to "шестьдесят",
            7 to "семьдесят", 8 to "восемьдесят", 9 to "девяносто"
    )

    val hundreds = mapOf(
            0 to "", 1 to "сто", 2 to "двести", 3 to "триста",
            4 to "четыреста", 5 to "пятьсот", 6 to "шестьсот",
            7 to "семьсот", 8 to "восемьсот", 9 to "девятьсот"
    )

    val nHundreds = n / 100
    val nTens = n % 100
    val nUnits = n % 10

    val nHundredsToString = hundreds[nHundreds]
    val nTensToString = if (nTens in 11..19) fromTenToTwenty[nTens] else tens[nTens / 10]
    val nUnitsToString = if (nTens in 11..19) "" else units[nUnits]

    return listOf(nHundredsToString, nTensToString, nUnitsToString).filter { it != "" }.joinToString(separator = " ")

}


fun russian(n: Int): String {

    val nSecondPart = n % 1000
    val nFirstPart = n / 1000

    val nSecondPartToString = hundredsToRussian(nSecondPart)
    val nFirstPartToString = hundredsToRussian(nFirstPart)

    val result = when {

        nFirstPart == 0 -> return nSecondPartToString

        nFirstPart % 100 in 11..19 -> "$nFirstPartToString тысяч"

        nFirstPart % 10 == 1 -> "${nFirstPartToString.dropLast(4)}одна тысяча"
        nFirstPart % 10 == 2 -> "${nFirstPartToString.dropLast(3)}две тысячи"
        nFirstPart % 10 in 3..4 -> "$nFirstPartToString тысячи"

        else -> "$nFirstPartToString тысяч"
    }

    return if (nSecondPart > 0) "$result $nSecondPartToString" else result
}
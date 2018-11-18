@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {

    var result = 1
    var remainder = abs(n)

    while (remainder > 9) {
        remainder /= 10
        result += 1
    }

    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */

fun fib(n: Int): Int {

    var previous = 0
    var result = 1
    var buffer: Int

    for (i in 2..n) {

        buffer = previous + result
        previous = result
        result = buffer
    }

    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

fun lcm(m: Int, n: Int): Int {

    val maxValue = max(m, n)
    val minValue = min(m, n)

    if ((maxValue.toDouble() % minValue.toDouble()) == 0.0) return maxValue

    var maxValueMultiplicator = 2

    while (true) {
        if ((maxValue * maxValueMultiplicator).toDouble() % minValue.toDouble() == 0.0) {
            return maxValue * maxValueMultiplicator
        } else {
            maxValueMultiplicator++
        }
    }
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */

fun minDivisor(n: Int): Int {

    return if (n.toDouble() % 2.0 == 0.0) 2
    else {

        var minDivider = 3

        while (n.toDouble() % minDivider.toDouble() != 0.0) {
            minDivider += 2
        }

        minDivider
    }
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */

fun maxDivisor(n: Int): Int = n / minDivisor(n)


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {

    val minValue = min(m, n)
    val maxValue = max(m, n)

    if ((m.toDouble() % 2.0 == 0.0) && (n.toDouble() % 2.0 == 0.0)) return false
    else if ((m == 1) or (n == 1)) return true
    else if (maxValue.toDouble() % minValue.toDouble() == 0.0) return false
    else {
        for (i in (minValue / 2) downTo 2) {

            if ((m.toDouble() % i.toDouble() == 0.0) && (n.toDouble() % i.toDouble() == 0.0)) return false
        }
    }

    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {

    val nSquareRoot = sqrt(n.toDouble())
    val mSquareRoot = sqrt(m.toDouble())

    if (nSquareRoot - mSquareRoot == 0.0) return true

    if ((nSquareRoot.toInt() * nSquareRoot.toInt() >= m) && (nSquareRoot.toInt() * nSquareRoot.toInt() <= n)) {
        return true
    }

    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {

    var buffer = x
    var stepsCounter = 0

    while (buffer > 1) {
        if (buffer.toDouble() % 2 == 0.0) buffer /= 2
        else buffer = 3 * buffer + 1
        stepsCounter++
    }

    return stepsCounter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun numberLength(number: Int): Int {

    var numberRemainderBuffer = number
    var numberLengthCounter = 0

    while (numberRemainderBuffer > 0) {
        numberRemainderBuffer /= 10
        numberLengthCounter++
    }

    return numberLengthCounter
}

fun revert(n: Int): Int {

    var result = 0
    var buffer = n

    while (buffer > 0) {
        val digit = buffer % 10
        result = result * 10 + digit
        buffer /= 10
    }

    return result
}


/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun isPalindrome(n: Int): Boolean {

    val nNumbersLength = numberLength(n)

    if (nNumbersLength == 1) return true

    var nFirstPart = n / (10.toDouble().pow(nNumbersLength / 2).toInt())
    val nSecondPart = n % (10.toDouble().pow(nNumbersLength / 2).toInt())

    if (nNumbersLength % 2 != 0) nFirstPart /= 10

    val nFirstPartReversed = revert(nFirstPart)

    return nFirstPartReversed == nSecondPart
}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun hasDifferentDigits(n: Int): Boolean {

    val nDigit = n % 10
    var buffer = n

    while (buffer > 0) {
        if (nDigit != buffer % 10) return true
        buffer /= 10
    }

    return false
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun squareSequenceDigit(n: Int): Int {

    var sequenceLength = 0
    var integersSequenceIterator = 0
    var integersSequenceIteratorSquare = 0

    while (sequenceLength < n) {

        integersSequenceIterator++
        integersSequenceIteratorSquare = integersSequenceIterator * integersSequenceIterator

        sequenceLength += numberLength(integersSequenceIteratorSquare)
    }

    return (integersSequenceIteratorSquare / 10.0.pow(sequenceLength - n).toInt()) % 10
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {

    var fibSequenceLength = 1
    var fibSequenceIterator = 1
    var fibSequencePreviousItem = 0
    var buffer: Int

    while (fibSequenceLength < n) {

        buffer = fibSequenceIterator

        fibSequenceIterator += fibSequencePreviousItem
        fibSequencePreviousItem = buffer

        fibSequenceLength += numberLength(fibSequenceIterator)
    }

    return (fibSequenceIterator / 10.0.pow(fibSequenceLength - n).toInt()) % 10
}

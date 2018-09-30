@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {

    val firstSum = ((number / 100) % 10) + (number / 1000)
    val secondSum = ((number % 100) / 10) + (number % 10)

    return firstSum == secondSum
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =

        (x1 == x2) || (y1 == y2) || (abs(x1 - x2) == abs(y1 - y2))

/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {

    // Check year number for exception
    if ((month == 2) && ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))) return 29

    // Return number of days by month's number
    return when (month) {
        in listOf(1, 3, 5, 7, 8, 10, 12) -> 31
        2 -> 28
        else -> 30
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean {

    // Calculate distance between two circle centres
    val distanceBetweenTwoCenters = sqrt((abs(x1 - x2)).pow(2) + (abs(y1 - y2)).pow(2))

    // Add radius of small circle to distance between centres and compare it with radius of bigger circle
    return (r2 >= (distanceBetweenTwoCenters + r1))
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {

    // Sort values of brick sides to find the two smallest
    val brickListOfSides = listOf(a, b, c).sorted()

    // Compare two smallest sides of brick with two sides in a hole
    return (brickListOfSides[0] <= min(r, s)) && (brickListOfSides[1] <= max(r, s))
}

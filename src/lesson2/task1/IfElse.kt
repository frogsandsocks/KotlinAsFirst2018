@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson8.task2.bishopMoveNumber
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {

    // Check for exceptions
    if (age % 100 in 11..14) return "$age лет"

    // Check number on which age ends
    return when (age % 10) {
        1 -> "$age год"
        in 2..4 -> "$age года"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {

    // Calculate each segment of a way and a half of a way
    val way1 = t1 * v1
    val way2 = t2 * v2
    val way3 = t3 * v3

    val halfWay = (way1 + way2 + way3) / 2

    return when {
        // If half of a way is shorter than first segment, then calculate
        halfWay < way1 -> halfWay / v1

        // If half of a way is shorter than first and second segment, but longer than first segment
        halfWay < (way1 + way2) -> (halfWay - way1) / v2 + t1

        // If half of a way is shorter than all way, but longer than first and second segment
        else -> (halfWay - (way1 + way2)) / v3 + t1 + t2
    }

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {

    // Check for a king from first rook
    val firstRookCheck = ((kingX == rookX1) || (kingY == rookY1))

    // Check for a king from second rook
    val secondRookCheck = ((kingX == rookX2) || (kingY == rookY2))

    return when {
        firstRookCheck && secondRookCheck -> 3
        secondRookCheck -> 2
        firstRookCheck -> 1
        else -> 0
    }

}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {

    // Check for a king from bishop
    val bishopCheckToKing = (abs(kingX - bishopX) == abs(kingY - bishopY))

    // Check for a king from rook
    val rookCheckToKing = ((kingX == rookX) || (kingY == rookY))

    return when {
        bishopCheckToKing && rookCheckToKing -> 3
        bishopCheckToKing -> 2
        rookCheckToKing -> 1
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    // Check for existence
    if ((a > b + c) || (b > a + c) || (c > a + b)) return -1


    if ((c > a) && (c > b)) {

        // If c is the largest side of triangle
        return when {
            c.pow(2) < a.pow(2) + b.pow(2) -> 0
            c.pow(2) == a.pow(2) + b.pow(2) -> 1
            else -> 2
        }

    } else if ((b > a) && (b > c)) {

        // If b is the largest side of triangle
        return when {
            b.pow(2) < a.pow(2) + c.pow(2) -> 0
            b.pow(2) == a.pow(2) + c.pow(2) -> 1
            else -> 2
        }

    } else {

        // If a is the largest side of triangle
        return when {
            a.pow(2) < c.pow(2) + b.pow(2) -> 0
            a.pow(2) == c.pow(2) + b.pow(2) -> 1
            else -> 2
        }
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {

    // If the ends of a segments never cross
    (c > b) || (a > d) -> -1

    // if the ends of a segments cross in one point
    (b == c) || (d == a) -> 0

    // Calculate the intersection
    (b < d) -> b - max(a, c)
    else -> d - max(c, a)
}
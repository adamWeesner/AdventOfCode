import kotlin.math.absoluteValue

data class Point(var x: Int = origin.x, var y: Int = origin.y) {
    val distanceFromOrigin get() = (x.absoluteValue - origin.x.absoluteValue) + (y.absoluteValue - origin.y.absoluteValue)

    var movement = mutableListOf<Point>()

    fun move(instruction: String) {
        if (x == 1 && y == 1) movement.add(this.copy())

        val amount = instruction.substring(1).toInt()
        when (instruction[0]) {
            'U' -> y += amount
            'D' -> y -= amount
            'L' -> x -= amount
            'R' -> x += amount
            else -> throw IllegalArgumentException("You have to move in a cardinal direction")
        }

        movement.add(this.copy())
    }

    fun moveOnPath(instructions: List<String>) {
        instructions.forEach { move(it) }
    }

    fun closestCrossDistanceToOrigin(origin: Point, second: Point): Int {
        var closestDistance: Int = Int.MAX_VALUE

        for (m1 in 0..movement.lastIndex) {
            for (m2 in 0..second.movement.lastIndex) {
                if (m1 < movement.lastIndex && m2 < second.movement.lastIndex) {
                    val line1 = Line(movement[m1], movement[m1 + 1], closestDistance)
                    val line2 = Line(second.movement[m2], second.movement[m2 + 1], closestDistance)

                    line1.compare(line2)

                    if (line1.crossLocation != origin) {
                        val distance = line1.crossLocation.distanceFromOrigin

                        if (distance != 0 && distance < closestDistance) {
                            closestDistance = distance
                        }
                    }
                }
            }
        }

        return closestDistance
    }

    companion object {
        val origin = Point(0, 0)
    }
}

data class Line(val start: Point, val end: Point, val closest: Int) {
    var segments = mutableListOf<Point>()
    private val isForward = start.x < end.x
    private val isUp = start.y < end.y

    var crossLocation: Point = Point()

    init {
        for (x in if (isForward) start.x..end.x else start.x downTo end.x) {
            for (y in if (isUp) start.y..end.y else start.y downTo end.y) {
                val newPoint = Point(x, y)

                if (newPoint.distanceFromOrigin < closest)
                    segments.add(newPoint)
            }
        }
    }

    fun compare(line: Line) {
        segments.forEach {
            if (line.segments.contains(it)) {
                crossLocation = it
                return@forEach
            }
        }
    }
}
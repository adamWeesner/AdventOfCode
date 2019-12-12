import Point.Companion.origin
import kotlin.math.absoluteValue

data class Point(val x: Int = origin.x, val y: Int = origin.y) {
    val distanceFromOrigin
        get() =
            if (x == origin.x && y == origin.y) Int.MAX_VALUE
            else (x.absoluteValue - origin.x.absoluteValue) + (y.absoluteValue - origin.y.absoluteValue)

    fun move(instruction: String): Line {
        val amount = instruction.substring(1).toInt()
        val newPoint = when (instruction[0]) {
            'U' -> Point(x, y + amount)
            'D' -> Point(x, y - amount)
            'L' -> Point(x - amount, y)
            'R' -> Point(x + amount, y)
            else -> throw IllegalArgumentException("You have to move in a cardinal direction")
        }

        return Line(this, newPoint)
    }

    fun moveOnPath(instructions: List<String>): Path {
        val path = Path()
        var startingPoint = this.copy()
        instructions.forEach {
            val newLine = startingPoint.move(it)
            path.segments.add(newLine)
            startingPoint = newLine.end
        }
        return path
    }

    companion object {
        val origin = Point(0, 0)
    }
}

data class Line(val start: Point, val end: Point) {
    var segments = mutableListOf<Point>()
    private val isForward = start.x < end.x
    private val isUp = start.y < end.y

    var crossLocation: Point = Point()

    private fun generateSegment(closest: Int) {
        segments = mutableListOf()
        for (x in if (isForward) start.x..end.x else start.x downTo end.x) {
            for (y in if (isUp) start.y..end.y else start.y downTo end.y) {
                val newPoint = Point(x, y)

                if (newPoint != origin && newPoint.distanceFromOrigin < closest) {
                    segments.add(newPoint)
                }
            }
        }
    }

    fun compare(line: Line, closest: Int) {
        generateSegment(closest)
        line.generateSegment(closest)
        segments.forEach {
            if (line.segments.contains(it)) {
                crossLocation = it
                return@forEach
            }
        }
    }
}

data class Path(var segments: MutableList<Line> = mutableListOf()) {
    fun findClosestCrossPoint(wire2: Path): Point {
        var closestPoint = origin
        for (i in 0..segments.lastIndex) {
            for (j in 0..wire2.segments.lastIndex) {
                segments[i].compare(wire2.segments[j], closestPoint.distanceFromOrigin)

                val crossLocation = segments[i].crossLocation
                if (crossLocation != origin && crossLocation.distanceFromOrigin < closestPoint.distanceFromOrigin)
                    closestPoint = crossLocation
            }
        }

        return closestPoint
    }
}
private val validInstructions = listOf(1, 2)
private val paramCount = 4

fun intCodeInstruction(opCodeIndex: Int, intCodes: MutableList<Int>): Int {
    val instruction = intCodes[opCodeIndex]
    val address1 = intCodes[opCodeIndex + 1]
    val address2 = intCodes[opCodeIndex + 2]

    val parameter1 = intCodes[address1]
    val parameter2 = intCodes[address2]

    return when (instruction) {
        validInstructions[0] -> parameter1 + parameter2
        validInstructions[1] -> parameter1 * parameter2
        else -> throw IllegalStateException("How? This should never happen. Instruction $instruction for params($parameter1, $parameter2)")
    }
}

fun intCodeProgram(intCodes: MutableList<Int>): Int {
    var opCodeIndex = 0

    while (opCodeIndex < intCodes.size) {
        val instruction = intCodes[opCodeIndex]
        if (instruction == 99 || !validInstructions.contains(instruction)) break

        val overridePos = intCodes[opCodeIndex + 3]
        intCodes[overridePos] = intCodeInstruction(opCodeIndex, intCodes)
        opCodeIndex += paramCount
    }

    return intCodes[0]
}
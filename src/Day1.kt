import kotlin.math.floor

private val starsList = listOf(
    108546,
    76196,
    144412,
    100530,
    143908,
    79763,
    109927,
    53656,
    82633,
    103781,
    97202,
    81600,
    115278,
    90095,
    85533,
    58230,
    142490,
    65176,
    132915,
    82319,
    148743,
    91444,
    145760,
    78002,
    127484,
    67225,
    74721,
    145620,
    146254,
    135544,
    74198,
    88015,
    53595,
    142036,
    113928,
    65217,
    56126,
    110117,
    57729,
    99052,
    89262,
    141540,
    70472,
    145271,
    81548,
    68065,
    93431,
    125210,
    66454,
    67709,
    149409,
    101787,
    130111,
    60569,
    131869,
    149702,
    135564,
    135094,
    71358,
    100169,
    127644,
    147741,
    102918,
    93503,
    74752,
    135883,
    120158,
    94570,
    129517,
    85602,
    55921,
    76746,
    107055,
    79320,
    81991,
    58982,
    63009,
    91360,
    147253,
    51139,
    61871,
    107140,
    146767,
    77441,
    125533,
    70317,
    125271,
    73189,
    141359,
    144549,
    104812,
    91315,
    145163,
    147202,
    95111,
    82628,
    116839,
    132358,
    99704,
    85305
)

fun calculateFuelCost(mass: Int): Int {
    val fuelCost = (floor(mass.toFloat() / 3) - 2).toInt()

    return if (fuelCost < 0) 0 else fuelCost
}

fun calculateFuelWeightCost(fuel: Int) = calculateFuelCost(fuel)

fun calculateNavCost(module: Int): Int {
    val fuelCost = calculateFuelCost(module)
    var totalFuelWeightCost = 0
    var fuelToWeigh = fuelCost

    while(fuelToWeigh > 0){
        fuelToWeigh = calculateFuelWeightCost(fuelToWeigh)
        totalFuelWeightCost += fuelToWeigh
    }

    return fuelCost + totalFuelWeightCost
}

fun calculateTotalNavCost(): Int {
    var totalFuelCost = 0

    starsList.forEach {
        totalFuelCost += calculateNavCost(it)
    }

    return totalFuelCost
}
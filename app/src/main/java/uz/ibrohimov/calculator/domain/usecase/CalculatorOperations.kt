package uz.ibrohimov.calculator.domain.usecase

import uz.ibrohimov.calculator.domain.model.CalculatorOperation

fun calculateResult(
    number1: String,
    operation: CalculatorOperation,
    number2: String
): Double {
    val n1 = number1.toDoubleOrNull() ?: 0.0
    val n2 = number2.toDoubleOrNull() ?: 0.0

    return when (operation) {
        CalculatorOperation.Add -> n1 + n2
        CalculatorOperation.Divide -> n1 - n2
        CalculatorOperation.Multiply -> n1 * n2
        CalculatorOperation.Subtract -> if (n2 != 0.0) n1 / n2 else Double.NaN
    }
}
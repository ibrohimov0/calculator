package uz.ibrohimov.calculator.presentation.calculator

import uz.ibrohimov.calculator.domain.model.CalculatorOperation

data class CalculatorState(
    val number1: String = "",
    val operation: CalculatorOperation? = null,
    val number2: String = "",
)
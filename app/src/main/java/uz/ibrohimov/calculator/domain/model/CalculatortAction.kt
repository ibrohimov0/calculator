package uz.ibrohimov.calculator.domain.model

sealed class CalculatortAction {
    data class Number(val number: Int) : CalculatortAction()
    data class Operation(val operation: CalculatorOperation) : CalculatortAction()
    object Clear : CalculatortAction()
    object Delete : CalculatortAction()
    object Calculate : CalculatortAction()
    object Decimal : CalculatortAction()
}

enum class CalculatorOperation(val symbol: String) {
    Add("+"), Subtract("-"), Multiply("x"), Divide("÷")
}
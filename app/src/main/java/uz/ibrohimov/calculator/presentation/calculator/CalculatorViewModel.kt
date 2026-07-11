package uz.ibrohimov.calculator.presentation.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import uz.ibrohimov.calculator.domain.model.CalculatorOperation
import uz.ibrohimov.calculator.domain.model.CalculatorAction
import uz.ibrohimov.calculator.domain.usecase.calculateResult

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> perfomDelete()
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= 8) return
            state = state.copy(number1 = state.number1 + number)
        } else {
            if (state.number2.length >= 8) return
            state = state.copy(number2 = state.number2 + number)
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null) {
            if (!state.number1.contains(".") && state.number1.isNotBlank()) {
                state = state.copy(number1 = state.number1 + ".")
            }
        } else {
            if (!state.number2.contains(".") && state.number2.isNotBlank()) {
                state = state.copy(number2 = state.number2 + ".")
            }
        }
    }

    private fun perfomDelete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(number2 = state.number2.dropLast(1))
            state.operation != null -> state = state.copy(operation = null)
            state.number1.isNotBlank() -> state = state.copy(number1 = state.number1.dropLast(1))
        }
    }

    private fun performCalculation() {
        val operation = state.operation ?: return
        if (state.number1.isBlank() && state.number2.isBlank()) return

        val result = calculateResult(state.number1, operation, state.number2)
        state = state.copy(
            number1 = result.toString(),
            operation = null,
            number2 = ""
        )
    }
}


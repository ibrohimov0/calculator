package uz.ibrohimov.calculator.presentation.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.ibrohimov.calculator.ui.theme.CalculatorTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.outlined.Delete
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.ibrohimov.calculator.domain.model.CalculatorOperation
import uz.ibrohimov.calculator.domain.model.CalculatorAction
import uz.ibrohimov.calculator.ui.components.CalculatorDisplay
import uz.ibrohimov.calculator.ui.components.CalculatorButton

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, viewModel: CalculatorViewModel = viewModel()) {
    val state = viewModel.state

    val expressionText = buildString {
        append(state.number1)
        state.operation?.let { append("${it.symbol}") }
        append(state.number2)
    }
    val resultText = state.number1.ifBlank { "0" }

    Column(
        modifier = modifier.padding(vertical = 20.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CalculatorDisplay(
            expressionText = expressionText,
            resultText = if (state.number2.isNotBlank()) state.number2 else resultText
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
                    items(
                        listOf(
                            Triple("d", Icons.Outlined.Delete, CalculatorAction.Clear),
                            Triple("b", Icons.Filled.Backspace, CalculatorAction.Delete),
                            Triple("%", Icons.Filled.Percent, CalculatorAction.Decimal)
                        )
                    ) { (label, symbol, action) ->
                        CalculatorButton(
                            text = label,
                            symbol = symbol,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            onClick = { viewModel.onAction(action) }
                        )
                    }
                }

                val numbers = remember { (9 downTo 0).toList() }
                LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(
                        items = numbers,
                        span = { _, n -> if (n == 0) GridItemSpan(2) else GridItemSpan(1) }) { _, number ->
                        CalculatorButton(
                            text = number.toString(),
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            onClick = { viewModel.onAction(CalculatorAction.Number(number)) }
                        )
                    }
                }
            }
            Column {
                listOf(
                    CalculatorOperation.Divide,
                    CalculatorOperation.Multiply,
                    CalculatorOperation.Subtract,
                    CalculatorOperation.Add
                ).forEach { op ->
                    CalculatorButton(
                        text = op.symbol,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        onClick = { viewModel.onAction(CalculatorAction.Operation(op)) }
                    )
                }
                CalculatorButton(
                    text = "=",
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { viewModel.onAction(CalculatorAction.Calculate) }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CalculatorScreenPreview() {
    CalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CalculatorScreen()
        }
    }
}
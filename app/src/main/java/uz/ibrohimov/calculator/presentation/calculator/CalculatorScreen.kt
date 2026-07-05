package uz.ibrohimov.calculator.presentation.calculator

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import uz.ibrohimov.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            "128+47",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        )
        Text(
            "175",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                val topActions = remember { listOf("AC", "+/-", "%") }
                LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
                    items(topActions) { action ->
                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.padding(all = 3.dp)
                        ) {
                            Text(
                                text = action,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(horizontal = 1.dp, vertical = 15.dp),
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }
                val numbers = remember { (9 downTo 0).toList() + "." }

                LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(
                        items = numbers,
                        span = { index, number ->
                            if (number == 0) GridItemSpan(2) else GridItemSpan(
                                1
                            )
                        }) { index, number ->
                        val isLast = index == numbers.lastIndex

                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            ),
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .padding(
                                    vertical = 3.dp,
                                    horizontal = if (isLast) 7.dp else 3.dp
                                )
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = number.toString(),
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(horizontal = 1.dp, vertical = 15.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Column {
                listOf("÷", "x", "-", "+", "=").forEach {
                    FilledTonalButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.padding(all = 3.dp)
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CalculatorScreen()
        }
    }
}
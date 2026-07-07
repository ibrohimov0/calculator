package uz.ibrohimov.calculator.presentation.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.ibrohimov.calculator.ui.theme.CalculatorTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon

data class ActionButton(
    val label: String,
    val icon: ImageVector? = null,
    val type: String
)

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var displayNumber by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(vertical = 20.dp, horizontal = 10.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            "128+47=175",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
        Text(
            text = if (displayNumber.isEmpty()) "0" else displayNumber,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                val topActions = remember {
                    listOf(
                        ActionButton(icon = Icons.Outlined.Delete, type = "Delete", label = "d"),
                        ActionButton(
                            icon = Icons.Filled.Backspace,
                            type = "Backspace",
                            label = "b"
                        ),
                        ActionButton(icon = Icons.Filled.Percent, type = "Percent", label = "%")
                    )
                }
                LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
                    items(topActions) { action ->
                        FilledTonalButton(
                            onClick = {
                                when (action.type) {
                                    "Delete" -> displayNumber = ""
                                    "Backspace" -> {
                                        if (displayNumber.isNotEmpty()) {
                                            displayNumber = displayNumber.dropLast(1)
                                        }
                                    }

                                    "Percent" -> {
                                        if (displayNumber.isNotEmpty() && displayNumber.last() != '%')
                                            displayNumber += "%"
                                    }
                                }
                            },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier.padding(all = 3.dp)
                        ) {
                            if (action.icon != null) {
                                Icon(
                                    imageVector = action.icon,
                                    contentDescription = action.label,
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 1.dp,
                                            vertical = 20.dp
                                        )
                                        .size(25.dp)
                                )
                            } else {
                                Text(
                                    text = action.label,
                                    modifier = Modifier.padding(
                                        horizontal = 1.dp,
                                        vertical = 15.dp
                                    ),
                                    fontWeight = FontWeight.Bold
                                )
                            }

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
                            onClick = { displayNumber += number.toString() },
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
                listOf(
                    ActionButton(label = "÷", type = "÷"),
                    ActionButton(label = "x", type = "x"),
                    ActionButton(label = "-", type = "-"),
                    ActionButton(label = "+", type = "+"),
                    ActionButton(label = "=", type = "=")
                ).forEach { action ->
                    FilledTonalButton(
                        onClick = {
                            if (action.type == "=") {
                                displayNumber = "We are working on result :)"
                            } else if (!displayNumber.isEmpty()) {
                                displayNumber += action.label.toString()
                            }
                        },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.padding(all = 3.dp)
                    ) {
                        Text(
                            text = action.label,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 14.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
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
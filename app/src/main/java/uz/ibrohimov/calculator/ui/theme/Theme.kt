package uz.ibrohimov.calculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = AccentBlue,
    onPrimary = PureWhite,
    background = WarmWhite,
    surface = PureWhite,
    surfaceVariant = UtilityGray,
    onSurface = TextPrimary,
    onSurfaceVariant = TextMuted
)

private val DarkColors = darkColorScheme(
    primary = AccentBlue,
    onPrimary = PureWhite,
    background = DarkBg,
    surface = DarkSurface,
    surfaceVariant = DarkUtility,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = TextMuted
)

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = CalculatorTypography,
        content = content
    )
}
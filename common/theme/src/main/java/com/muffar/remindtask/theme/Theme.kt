package com.muffar.remindtask.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.muffar.remindtask.theme.color.MainColor

private val DarkColorScheme = darkColorScheme(
    primary = MainColor.Blue.dark,
    onPrimary = MainColor.White,
    primaryContainer = MainColor.Blue.light,
    onPrimaryContainer = MainColor.White,
    inversePrimary = MainColor.Blue.primary,

    secondary = MainColor.Yellow.kindaDark,
    onSecondary = MainColor.White,
    secondaryContainer = MainColor.Yellow.light,
    onSecondaryContainer = MainColor.White,

    tertiary = MainColor.Green.primary,
    onTertiary = MainColor.White,
    tertiaryContainer = MainColor.Green.light,
    onTertiaryContainer = MainColor.White,

    error = MainColor.Red.primary,
    onError = MainColor.White,
    errorContainer = MainColor.Red.light,
    onErrorContainer = MainColor.White,

    background = MainColor.Black,
    onBackground = MainColor.White,
    surface = MainColor.Black,
    onSurface = MainColor.White,
    surfaceVariant = MainColor.ExtraDarkGray,
    onSurfaceVariant = MainColor.White,
    surfaceTint = MainColor.White,
    inverseSurface = MainColor.LightGray,
    inverseOnSurface = MainColor.Black,

    outline = MainColor.Gray,
    outlineVariant = MainColor.LightGray,
    scrim = MainColor.ExtraLightGray.copy(alpha = 0.8f)
)

private val LightColorScheme = lightColorScheme(
    primary = MainColor.Blue.dark,
    onPrimary = MainColor.White,
    primaryContainer = MainColor.Blue.light,
    onPrimaryContainer = MainColor.White,
    inversePrimary = MainColor.Blue.primary,

    secondary = MainColor.Yellow.kindaDark,
    onSecondary = MainColor.White,
    secondaryContainer = MainColor.Yellow.light,
    onSecondaryContainer = MainColor.White,

    tertiary = MainColor.Green.primary,
    onTertiary = MainColor.White,
    tertiaryContainer = MainColor.Green.light,
    onTertiaryContainer = MainColor.White,

    error = MainColor.Red.primary,
    onError = MainColor.White,
    errorContainer = MainColor.Red.light,
    onErrorContainer = MainColor.White,

    background = MainColor.White,
    onBackground = MainColor.Black,
    surface = MainColor.White,
    onSurface = MainColor.Black,
    surfaceVariant = MainColor.ExtraLightGray,
    onSurfaceVariant = MainColor.Black,
    surfaceTint = MainColor.Black,
    inverseSurface = MainColor.DarkGray,
    inverseOnSurface = MainColor.White,

    outline = MainColor.Gray,
    outlineVariant = MainColor.DarkGray,
    scrim = MainColor.ExtraDarkGray.copy(alpha = 0.8f)
)

@Composable
fun RemindTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            typography = Typography,
            content = content,
            shapes = Shapes
        )
    }
}
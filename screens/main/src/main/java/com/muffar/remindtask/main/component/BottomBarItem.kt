package com.muffar.remindtask.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.muffar.remindtask.navigation.Screens
import com.muffar.remindtask.theme.color.MainColor
import com.muffar.remindtask.theme.spacing

@Composable
fun BottomBarItem(
    navController: NavHostController,
    icon: ImageVector,
    label: String,
    activeColor: Color,
    route : String,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isActive = currentRoute == route

    val color =
        if (!isActive) MainColor.Gray else activeColor

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = MaterialTheme.spacing.kindaSmall)
            .clip(CircleShape)
            .clickable {
                if (!isActive) {
                    navController.navigate(route) {
                        popUpTo(Screens.Tasks.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
            .padding(vertical = MaterialTheme.spacing.kindaSmall)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color
        )

        AnimatedVisibility(visible = isActive) {
            Text(
                text = label,
                color = color,
                modifier = Modifier.padding(start = MaterialTheme.spacing.small)
            )
        }
    }
}
package com.muffar.remindtask.screen.main.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.muffar.remindtask.R
import com.muffar.remindtask.ui.navigation.Screens
import com.muffar.remindtask.ui.theme.RemindTaskTheme
import com.muffar.remindtask.ui.theme.spacing

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    containerColor: Color = BottomAppBarDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = 1.dp,
    contentPadding: PaddingValues = BottomAppBarDefaults.ContentPadding,
    contentHeight: Dp = 80.dp,
    windowInsets: WindowInsets = BottomAppBarDefaults.windowInsets,
    navController: NavHostController,
    onAddClick: () -> Unit,
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(contentHeight)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val activeColor = MaterialTheme.colorScheme.primary
            BottomBarItem(
                navController = navController,
                icon = FaIcons.Tasks,
                label = stringResource(R.string.tasks_menu),
                activeColor = activeColor,
                route = Screens.Tasks.route,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(0.3f)
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 1.dp,
                        color = activeColor,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onAddClick() },
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    faIcon = FaIcons.Plus,
                    tint = activeColor,
                    size = 20.dp
                )
            }

            BottomBarItem(
                navController = navController,
                icon = FaIcons.StickyNote,
                label = stringResource(R.string.notes_menu),
                activeColor = activeColor,
                route = Screens.Notes.route,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .weight(0.3f)
            )
        }
    }
}

@Preview
@Composable
fun MainBottomBarPreview() {
    RemindTaskTheme {
        MainBottomBar(
            navController = NavHostController(LocalContext.current),
            onAddClick = {}
        )
    }
}
package com.muffar.remindtask.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.muffar.remindtask.resources.R

@Composable
fun EmptyResult(
    modifier: Modifier = Modifier,
    message: String,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_result))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box {
            LottieAnimation(
                modifier = Modifier.size(250.dp),
                composition = composition,
                progress = { progress }
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            )
        }
    }
}
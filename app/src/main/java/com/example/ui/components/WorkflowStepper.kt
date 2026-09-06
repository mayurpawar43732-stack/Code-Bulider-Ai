package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.models.WorkflowStep
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.PrimaryIndigoLight

@Composable
fun WorkflowStepper(
    currentStep: WorkflowStep,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val steps = WorkflowStep.values()

    // Auto-scroll to current step
    LaunchedEffect(currentStep) {
        val targetIndex = steps.indexOf(currentStep)
        if (targetIndex >= 0) {
            scrollState.animateScrollTo((targetIndex * 120))
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0D131F))
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.forEachIndexed { index, step ->
                val isCurrent = step == currentStep
                val isPassed = step.stepNumber < currentStep.stepNumber

                val badgeBg by animateColorAsState(
                    targetValue = when {
                        isCurrent -> PrimaryIndigo
                        isPassed -> AccentEmerald
                        else -> Color(0xFF1E293B)
                    },
                    label = "badgeBg"
                )

                val textColor by animateColorAsState(
                    targetValue = when {
                        isCurrent -> Color.White
                        isPassed -> Color(0xFFCBD5E1)
                        else -> Color(0xFF64748B)
                    },
                    label = "textColor"
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (isCurrent) Color(0x336366F1) else Color.Transparent
                            )
                            .border(
                                width = if (isCurrent) 1.5.dp else 1.dp,
                                color = if (isCurrent) AccentCyan else if (isPassed) AccentEmerald.copy(alpha = 0.5f) else Color(0xFF334155),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(badgeBg),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isPassed) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Done",
                                    tint = Color.Black,
                                    modifier = Modifier.size(13.dp)
                                )
                            } else {
                                Text(
                                    text = "${step.stepNumber}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCurrent) Color.White else Color(0xFF94A3B8)
                                )
                            }
                        }

                        Text(
                            text = step.shortTitle,
                            fontSize = 12.sp,
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                            color = textColor
                        )
                    }

                    if (index < steps.size - 1) {
                        Box(
                            modifier = Modifier
                                .width(18.dp)
                                .height(2.dp)
                                .background(
                                    if (isPassed) AccentEmerald else Color(0xFF334155)
                                )
                        )
                    }
                }
            }
        }
    }
}

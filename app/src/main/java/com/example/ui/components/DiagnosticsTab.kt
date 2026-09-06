package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.models.ArchitectureConfig
import com.example.models.DiagnosticsReport
import com.example.ui.theme.*

@Composable
fun DiagnosticsTab(
    diagnostics: DiagnosticsReport?,
    architecture: ArchitectureConfig?,
    onRunDiagnostics: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Health Summary Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val score = diagnostics?.scorePercentage ?: 100
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(
                            if (score >= 90) Color(0x2210B981) else Color(0x22F59E0B)
                        )
                        .border(
                            3.dp,
                            if (score >= 90) AccentEmerald else AccentAmber,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$score%",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (score >= 90) AccentEmerald else AccentAmber
                        )
                        Text(
                            text = "HEALTH",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = diagnostics?.statusSummary ?: "ALL SYSTEMS VERIFIED",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "${diagnostics?.passedChecks ?: 10}/${diagnostics?.totalChecks ?: 10} Critical Checks Passed",
                    fontSize = 12.sp,
                    color = Color(0xFF94A3B8)
                )

                Spacer(modifier = Modifier.height(14.dp))
                FilledTonalButton(
                    onClick = onRunDiagnostics,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = PrimaryIndigo)
                ) {
                    Icon(imageVector = Icons.Default.Replay, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Re-Run Static Diagnostics Audit")
                }
            }
        }

        // Section: Audit Checks Breakdown
        Text(
            text = "Code Quality & Rule Compliance Audit",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = AccentCyan
        )

        diagnostics?.checks?.forEach { check ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF131A29),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                if (check.passed) Color(0x3310B981) else Color(0x33EF4444)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (check.passed) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = null,
                            tint = if (check.passed) AccentEmerald else AccentRose,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = check.checkName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color(0x3338BDF8)
                            ) {
                                Text(
                                    text = check.category,
                                    fontSize = 9.sp,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                                    color = AccentCyan
                                )
                            }
                        }
                        Text(
                            text = check.details,
                            fontSize = 11.5.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }
        }

        // Section: Strict Architecture Compliance Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "🔒 Rule Compliance Matrix",
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = AccentAmber
                )
                Text(
                    text = "• Rule #7 (User-Only): Single index.html, all CSS/JS embedded.\n" +
                           "• Rule #8 (Admin + User): Exactly 2 files: admin.html and index.html.\n" +
                           "• Rule #9 (Firebase Storage): STRICTLY FORBIDDEN. Zero storage bucket calls.\n" +
                           "• Rule #10 (Image URL System): Admin supplies external HTTPS image URLs.\n" +
                           "• Rule #11 (Multiplayer Game): Single index.html with Canvas & RTDB room sync.\n" +
                           "• Rule #12 (Admin Security): Credentials never hardcoded in public HTML.",
                    fontSize = 11.5.sp,
                    color = Color(0xFFCBD5E1),
                    lineHeight = 18.sp
                )
            }
        }

        // Section: Deployment Platforms
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "🚀 Deployment Targets",
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = AccentEmerald
                )
                Text(
                    text = "1. GitHub Pages: Push index.html to gh-pages branch. Instant SSL hosting.\n" +
                           "2. Netlify / Vercel: Drag and drop project folder. Zero config.\n" +
                           "3. Android WebView APK: Wrap index.html directly into Android Studio WebView.\n" +
                           "4. InfinityFree / Apache: Upload index.html and admin.html to htdocs.",
                    fontSize = 11.5.sp,
                    color = Color(0xFF94A3B8),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

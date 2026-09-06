package com.example.ui.components

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.*

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LiveRunnerTab(
    indexHtml: String,
    adminHtml: String?,
    modifier: Modifier = Modifier
) {
    var runningFile by remember { mutableStateOf("index.html") }
    var reloadTrigger by remember { mutableStateOf(0) }
    var isPhoneFrameMode by remember { mutableStateOf(false) }

    val currentHtml = if (runningFile == "admin.html" && adminHtml != null) adminHtml else indexHtml

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Runner Toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F172A))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // File selector
                Surface(
                    onClick = { runningFile = "index.html" },
                    shape = RoundedCornerShape(8.dp),
                    color = if (runningFile == "index.html") PrimaryIndigo else Color(0xFF1E293B)
                ) {
                    Text(
                        text = "User View (index.html)",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        fontSize = 11.5.sp,
                        fontWeight = if (runningFile == "index.html") FontWeight.Bold else FontWeight.Normal,
                        color = Color.White
                    )
                }

                if (adminHtml != null) {
                    Surface(
                        onClick = { runningFile = "admin.html" },
                        shape = RoundedCornerShape(8.dp),
                        color = if (runningFile == "admin.html") AccentAmber else Color(0xFF1E293B)
                    ) {
                        Text(
                            text = "Admin Panel (admin.html)",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            fontSize = 11.5.sp,
                            fontWeight = if (runningFile == "admin.html") FontWeight.Bold else FontWeight.Normal,
                            color = if (runningFile == "admin.html") Color.Black else Color.White
                        )
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                // Device frame toggle
                IconButton(
                    onClick = { isPhoneFrameMode = !isPhoneFrameMode },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = if (isPhoneFrameMode) Icons.Default.PhoneAndroid else Icons.Default.Fullscreen,
                        contentDescription = "Toggle Frame",
                        tint = if (isPhoneFrameMode) AccentCyan else Color(0xFF94A3B8),
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Reload
                IconButton(
                    onClick = { reloadTrigger++ },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reload",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // WebView Runner Container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color(0xFF030712))
                .padding(if (isPhoneFrameMode) 16.dp else 0.dp),
            contentAlignment = Alignment.Center
        ) {
            val containerModifier = if (isPhoneFrameMode) {
                Modifier
                    .fillMaxHeight()
                    .widthIn(max = 420.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .border(3.dp, Color(0xFF334155), RoundedCornerShape(24.dp))
            } else {
                Modifier.fillMaxSize()
            }

            if (currentHtml.isBlank()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayCircle,
                        contentDescription = null,
                        tint = Color(0xFF475569),
                        modifier = Modifier.size(56.dp)
                    )
                    Text(
                        text = "No code generated yet",
                        color = Color(0xFF94A3B8),
                        fontSize = 14.sp
                    )
                }
            } else {
                key(runningFile, reloadTrigger) {
                    AndroidView(
                        factory = { ctx ->
                            WebView(ctx).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                settings.databaseEnabled = true
                                settings.loadWithOverviewMode = true
                                settings.useWideViewPort = true
                                webChromeClient = WebChromeClient()
                                webViewClient = WebViewClient()
                                loadDataWithBaseURL(
                                    "https://applet.local/",
                                    currentHtml,
                                    "text/html",
                                    "UTF-8",
                                    null
                                )
                            }
                        },
                        modifier = containerModifier
                    )
                }
            }
        }
    }
}

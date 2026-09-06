package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.models.DiagnosticsReport
import com.example.ui.theme.*

@Composable
fun CodeViewerTab(
    indexHtml: String,
    adminHtml: String?,
    devPrompt: String,
    diagnostics: DiagnosticsReport?,
    activeTab: String,
    onTabSelect: (String) -> Unit,
    onUpdateCode: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isEditMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    val currentContent = when (activeTab) {
        "index.html" -> indexHtml
        "admin.html" -> adminHtml ?: "// No admin.html for this architecture."
        "dev-prompt.md" -> devPrompt
        "diagnostics.txt" -> {
            diagnostics?.checks?.joinToString("\n") {
                "[${if (it.passed) "PASS" else "FAIL"}] ${it.category} > ${it.checkName}: ${it.details}"
            } ?: "Diagnostics not yet executed."
        }
        else -> indexHtml
    }

    var editableCode by remember(currentContent) { mutableStateOf(currentContent) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // File Tabs Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F172A))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val tabs = mutableListOf("index.html")
            if (adminHtml != null) tabs.add("admin.html")
            tabs.add("dev-prompt.md")
            tabs.add("diagnostics.txt")

            tabs.forEach { tabName ->
                val isActive = tabName == activeTab
                Surface(
                    onClick = { onTabSelect(tabName) },
                    shape = RoundedCornerShape(8.dp),
                    color = if (isActive) PrimaryIndigo else Color(0xFF1E293B),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isActive) AccentCyan else Color(0xFF334155)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = when {
                                tabName.endsWith(".html") -> Icons.Default.Code
                                tabName.endsWith(".md") -> Icons.Default.Description
                                else -> Icons.Default.FactCheck
                            },
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = if (isActive) Color.White else Color(0xFF94A3B8)
                        )
                        Text(
                            text = tabName,
                            fontSize = 12.sp,
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                            color = if (isActive) Color.White else Color(0xFFCBD5E1)
                        )
                    }
                }
            }
        }

        // Toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF131B2E))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${editableCode.lines().size} lines • ${(editableCode.length / 1024f).toInt()} KB",
                fontSize = 11.5.sp,
                color = Color(0xFF94A3B8),
                fontFamily = FontFamily.Monospace
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Copy Button
                IconButton(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText(activeTab, editableCode)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "$activeTab copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        tint = AccentCyan,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Share Button
                IconButton(
                    onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, editableCode)
                            putExtra(Intent.EXTRA_TITLE, activeTab)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, "Share $activeTab")
                        context.startActivity(shareIntent)
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color(0xFFCBD5E1),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Edit/Save toggle
                if (activeTab.endsWith(".html")) {
                    FilledTonalButton(
                        onClick = {
                            if (isEditMode) {
                                onUpdateCode(activeTab, editableCode)
                                Toast.makeText(context, "Saved changes to $activeTab", Toast.LENGTH_SHORT).show()
                            }
                            isEditMode = !isEditMode
                        },
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (isEditMode) AccentEmerald else Color(0xFF1E293B)
                        )
                    ) {
                        Icon(
                            imageVector = if (isEditMode) Icons.Default.Check else Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (isEditMode) "Save" else "Edit", fontSize = 12.sp)
                    }
                }
            }
        }

        // Code Area
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(Color(0xFF070B14))
        ) {
            if (isEditMode) {
                OutlinedTextField(
                    value = editableCode,
                    onValueChange = { editableCode = it },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp,
                        color = Color(0xFFE2E8F0)
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            } else {
                val scrollStateV = rememberScrollState()
                val scrollStateH = rememberScrollState()

                SelectionContainer {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollStateV)
                            .horizontalScroll(scrollStateH)
                            .padding(12.dp)
                    ) {
                        // Line numbers
                        val lines = editableCode.lines()
                        Column(
                            modifier = Modifier.padding(end = 12.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            lines.indices.forEach { idx ->
                                Text(
                                    text = "${idx + 1}",
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.5.sp,
                                    color = Color(0xFF475569)
                                )
                            }
                        }

                        // Code lines
                        Column {
                            lines.forEach { line ->
                                Text(
                                    text = line.ifEmpty { " " },
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.5.sp,
                                    color = when {
                                        line.trim().startsWith("<") -> Color(0xFF38BDF8)
                                        line.trim().startsWith("//") || line.trim().startsWith("/*") -> Color(0xFF64748B)
                                        line.contains("function") || line.contains("const") || line.contains("let") -> Color(0xFFA855F7)
                                        line.contains("{") || line.contains("}") -> Color(0xFFF59E0B)
                                        else -> Color(0xFFE2E8F0)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.models.*
import com.example.ui.theme.*

@Composable
fun ChatBubble(message: ChatMessage, modifier: Modifier = Modifier) {
    val isUser = message.sender == "user"

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.92f),
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
        ) {
            if (!isUser) {
                Image(
                    painter = painterResource(id = R.drawable.img_app_logo),
                    contentDescription = "Code Build AI",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(1.dp, AccentCyan, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            Surface(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (isUser) 16.dp else 4.dp,
                    bottomEnd = if (isUser) 4.dp else 16.dp
                ),
                color = if (isUser) Color(0xFF312E81) else Color(0xFF1E293B),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isUser) Color(0xFF4F46E5) else Color(0xFF334155)
                ),
                shadowElevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    FormattedMarkdownText(text = message.content)
                }
            }
        }
    }
}

@Composable
fun FormattedMarkdownText(text: String) {
    val lines = text.split("\n")
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        lines.forEach { line ->
            when {
                line.startsWith("### ") -> {
                    Text(
                        text = line.removePrefix("### "),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = AccentCyan
                    )
                }
                line.startsWith("## ") -> {
                    Text(
                        text = line.removePrefix("## "),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = AccentCyan
                    )
                }
                line.startsWith("- ") || line.startsWith("• ") -> {
                    Row(
                        modifier = Modifier.padding(start = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("•", color = PrimaryIndigoLight, fontWeight = FontWeight.Bold)
                        Text(
                            text = line.removePrefix("- ").removePrefix("• "),
                            fontSize = 13.5.sp,
                            color = Color(0xFFF1F5F9),
                            lineHeight = 19.sp
                        )
                    }
                }
                line.trim().isNotBlank() -> {
                    Text(
                        text = line,
                        fontSize = 13.5.sp,
                        color = Color(0xFFF8FAFC),
                        lineHeight = 19.sp
                    )
                }
                else -> {
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    }
}

@Composable
fun TrendingIdeasSelector(
    ideas: List<IdeaConcept>,
    onSelectIdea: (IdeaConcept) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Select a Concept to Build:",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = AccentAmber
        )

        ideas.forEach { concept ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectIdea(concept) },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${concept.id}. ${concept.title}",
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = when (concept.difficulty) {
                                "Beginner" -> Color(0x3310B981)
                                "Intermediate" -> Color(0x33F59E0B)
                                else -> Color(0x33EF4444)
                            }
                        ) {
                            Text(
                                text = concept.difficulty,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = when (concept.difficulty) {
                                    "Beginner" -> Color(0xFF10B981)
                                    "Intermediate" -> Color(0xFFF59E0B)
                                    else -> Color(0xFFEF4444)
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = concept.concept,
                        fontSize = 12.5.sp,
                        color = Color(0xFF94A3B8)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "⭐ USP: ${concept.usp}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = AccentCyan
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { onSelectIdea(concept) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Select Concept & Discover Features", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureDiscoverySelector(
    features: List<FeatureItem>,
    onToggleFeature: (Int) -> Unit,
    onSelectAll: (Boolean) -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Operational Features (${features.count { it.isSelected }}/${features.size})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { onSelectAll(true) },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("All", fontSize = 12.sp, color = AccentCyan)
                    }
                    TextButton(
                        onClick = { onSelectAll(false) },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("None", fontSize = 12.sp, color = Color(0xFF94A3B8))
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                features.forEach { item ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onToggleFeature(item.id) },
                        shape = RoundedCornerShape(10.dp),
                        color = if (item.isSelected) Color(0xFF1E293B) else Color(0xFF131A29),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (item.isSelected) PrimaryIndigoLight.copy(alpha = 0.6f) else Color(0xFF1F2937)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = item.isSelected,
                                onCheckedChange = { onToggleFeature(item.id) },
                                colors = CheckboxDefaults.colors(checkedColor = PrimaryIndigo)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = "${item.id}. ${item.title}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (item.isSelected) Color.White else Color(0xFF94A3B8)
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = Color(0x3364748B)
                                    ) {
                                        Text(
                                            text = item.ownership,
                                            fontSize = 9.sp,
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                                            color = Color(0xFFCBD5E1)
                                        )
                                    }
                                }
                                Text(
                                    text = item.description,
                                    fontSize = 11.5.sp,
                                    color = Color(0xFF94A3B8),
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AccentEmerald)
            ) {
                Icon(imageVector = Icons.Default.Palette, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Confirm Features & Choose Theme", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ThemeSelector(
    themes: List<ThemeOption>,
    selectedTheme: ThemeOption,
    onSelectTheme: (ThemeOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Select Application Theme (10 Curated Options):",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = AccentCyan
        )

        themes.forEach { theme ->
            val isSelected = theme.id == selectedTheme.id
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectTheme(theme) },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) AccentCyan else Color(0xFF1E293B)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = theme.name,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(
                                        try {
                                            Color(android.graphics.Color.parseColor(theme.bgHex))
                                        } catch (e: Exception) { Color.Black }
                                    )
                                    .border(1.dp, Color.White.copy(alpha = 0.4f), CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(
                                        try {
                                            Color(android.graphics.Color.parseColor(theme.accentHex))
                                        } catch (e: Exception) { PrimaryIndigo }
                                    )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = theme.visualDescription,
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Best for: ${theme.recommendedUseCase}",
                        fontSize = 11.sp,
                        color = AccentAmber
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onSelectTheme(theme) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) AccentEmerald else Color(0xFF1E293B)
                        )
                    ) {
                        Text(if (isSelected) "Theme Applied ✓" else "Apply ${theme.name}", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FontSelector(
    fonts: List<FontOption>,
    selectedFont: FontOption,
    onSelectFont: (FontOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Select Typography Pairing (5 Professional Options):",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = AccentCyan
        )

        fonts.forEach { font ->
            val isSelected = font.id == selectedFont.id
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectFont(font) },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                border = androidx.compose.foundation.BorderStroke(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) AccentCyan else Color(0xFF1E293B)
                )
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = font.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = font.previewHeading,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AccentCyan
                    )
                    Text(
                        text = font.previewBody,
                        fontSize = 12.5.sp,
                        color = Color(0xFF94A3B8)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { onSelectFont(font) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) AccentEmerald else PrimaryIndigo
                        )
                    ) {
                        Text(if (isSelected) "Typography Selected ✓" else "Select ${font.name}", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

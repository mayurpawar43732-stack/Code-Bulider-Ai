package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.ProjectEntity
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProjectsTab(
    projects: List<ProjectEntity>,
    currentProjectId: Long,
    onSelectProject: (ProjectEntity) -> Unit,
    onNewProject: () -> Unit,
    onDeleteProject: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy • HH:mm", Locale.getDefault())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        // Top Action Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Saved Projects",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "${projects.size} local project workspace(s)",
                    fontSize = 12.sp,
                    color = Color(0xFF94A3B8)
                )
            }

            Button(
                onClick = onNewProject,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("New Project", fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (projects.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FolderOpen,
                        contentDescription = null,
                        tint = Color(0xFF475569),
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = "No saved projects yet",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFCBD5E1)
                    )
                    Text(
                        text = "Start by describing an app or game idea in the Architect tab!",
                        fontSize = 12.5.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(projects, key = { it.id }) { proj ->
                    val isActive = proj.id == currentProjectId
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectProject(proj) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isActive) Color(0xFF1E293B) else Color(0xFF0F172A)
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            width = if (isActive) 1.5.dp else 1.dp,
                            color = if (isActive) AccentCyan else Color(0xFF1E293B)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = proj.title,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    if (isActive) {
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = Color(0x3338BDF8)
                                        ) {
                                            Text(
                                                text = "ACTIVE",
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = AccentCyan,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = proj.originalIdea.ifBlank { "No prompt specified" },
                                    fontSize = 12.sp,
                                    color = Color(0xFF94A3B8),
                                    maxLines = 1
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = Color(0x226366F1)
                                    ) {
                                        Text(
                                            text = proj.architectureType,
                                            fontSize = 10.sp,
                                            color = PrimaryIndigoLight,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                    Text(
                                        text = dateFormat.format(Date(proj.updatedAt)),
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF64748B)
                                    )
                                }
                            }

                            IconButton(
                                onClick = { onDeleteProject(proj.id) },
                                modifier = Modifier.size(36.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DeleteOutline,
                                    contentDescription = "Delete",
                                    tint = Color(0xFF64748B),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

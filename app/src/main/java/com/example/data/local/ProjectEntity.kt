package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val originalIdea: String,
    val currentStage: String,
    val selectedIdeaJson: String = "",
    val selectedFeaturesJson: String = "",
    val selectedThemeId: String = "amoled_dark",
    val selectedFontId: String = "inter_space",
    val architectureType: String = "User-Only Web App",
    val devPrompt: String = "",
    val indexHtmlCode: String = "",
    val adminHtmlCode: String = "",
    val diagnosticsJson: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectId: Long,
    val sender: String,
    val content: String,
    val stage: String,
    val timestamp: Long = System.currentTimeMillis()
)

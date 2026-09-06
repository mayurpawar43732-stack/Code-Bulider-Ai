package com.example.models

enum class WorkflowStep(val stepNumber: Int, val title: String, val shortTitle: String) {
    IDEA_INPUT(1, "User Idea Input", "Idea"),
    IDEA_ANALYSIS(2, "Idea Analysis", "Analysis"),
    TRENDING_IDEAS(3, "Trending Concepts", "Trends"),
    FEATURE_DISCOVERY(4, "Feature Discovery", "Features"),
    THEME_SELECTION(5, "Theme Selection", "Theme"),
    FONT_SELECTION(6, "Font Selection", "Font"),
    ARCHITECTURE(7, "Technical Architecture", "Arch"),
    DEV_PROMPT(8, "Development Prompt", "Prompt"),
    CODE_GENERATION(9, "Code Generation", "Code"),
    DIAGNOSTICS(10, "Code Diagnostics", "Diag"),
    FILE_EXPORT(11, "Export & Live Runner", "Export"),
    CONTINUE_DEV(12, "Iterate & Continue", "Iterate")
}

data class IdeaConcept(
    val id: Int,
    val title: String,
    val concept: String,
    val targetAudience: String,
    val usp: String,
    val importantFeatures: List<String>,
    val difficulty: String // "Beginner" | "Intermediate" | "Advanced"
)

data class FeatureItem(
    val id: Int,
    val title: String,
    val description: String,
    val userInteraction: String,
    val techImplementation: String,
    val ownership: String, // "User" | "Admin" | "Shared"
    val firebaseRequirement: String,
    val dependencies: List<String>,
    val isSelected: Boolean = true
)

data class ThemeOption(
    val id: String,
    val name: String,
    val visualDescription: String,
    val recommendedUseCase: String,
    val characteristics: List<String>,
    val bgHex: String,
    val cardHex: String,
    val accentHex: String,
    val textColor: String
)

data class FontOption(
    val id: String,
    val name: String,
    val headingFont: String,
    val bodyFont: String,
    val previewHeading: String,
    val previewBody: String
)

data class ArchitectureConfig(
    val type: String, // "User-Only Web App" | "Admin + User Web Platform" | "Single-File Multiplayer HTML5 Game"
    val isUserOnly: Boolean,
    val isAdminPlusUser: Boolean,
    val isMultiplayerGame: Boolean,
    val frontendFiles: List<String>,
    val usesFirebaseRealtimeDb: Boolean,
    val firebaseStorageForbidden: Boolean = true,
    val imageHostingSystem: String,
    val adminAuthDetails: String,
    val stackDetails: List<String>,
    val explanation: String
)

data class DiagCheck(
    val category: String, // "HTML" | "CSS" | "JavaScript" | "Firebase" | "Features"
    val checkName: String,
    val passed: Boolean,
    val details: String
)

data class DiagnosticsReport(
    val totalChecks: Int,
    val passedChecks: Int,
    val scorePercentage: Int,
    val statusSummary: String,
    val checks: List<DiagCheck>
)

data class ChatMessage(
    val id: String,
    val sender: String, // "user" | "ai" | "system"
    val content: String,
    val stage: WorkflowStep,
    val timestamp: Long = System.currentTimeMillis()
)

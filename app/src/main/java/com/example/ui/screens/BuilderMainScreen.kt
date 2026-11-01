package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.models.WorkflowStep
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.viewmodel.BuilderUiState
import com.example.viewmodel.BuilderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuilderMainScreen(
    viewModel: BuilderViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val savedProjects by viewModel.allSavedProjects.collectAsState()
    var selectedNavTab by remember { mutableStateOf(0) } // 0: Architect, 1: Code, 2: Runner, 3: Audit, 4: Projects, 5: Settings
    var userInputText by remember { mutableStateOf("") }
    val chatListState = rememberLazyListState()

    // Auto-scroll chat when new messages arrive
    LaunchedEffect(uiState.chatMessages.size) {
        if (uiState.chatMessages.isNotEmpty()) {
            chatListState.animateScrollToItem(uiState.chatMessages.size - 1)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_app_logo),
                            contentDescription = "Code Build AI Logo",
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = "ULTRA CODE ULTIMATE",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = uiState.projectTitle,
                                fontSize = 11.sp,
                                color = AccentCyan,
                                maxLines = 1
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { selectedNavTab = 5 }) {\n                        Icon(imageVector = Icons.Default.Settings, contentDescription = "AI Provider Settings", tint = Color.White)\n                    }\n                    // AI Status Badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (uiState.hasGeminiApiKey) Color(0x3310B981) else Color(0x336366F1),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (uiState.hasGeminiApiKey) AccentEmerald else PrimaryIndigoLight
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(if (uiState.hasGeminiApiKey) AccentEmerald else AccentCyan)
                            )
                            Text(
                                text = if (uiState.hasGeminiApiKey) "GEMINI PRO" else "AI ENGINE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    IconButton(onClick = { viewModel.startNewProject() }) {
                        Icon(
                            imageVector = Icons.Default.AddCircleOutline,
                            contentDescription = "New Project",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F172A)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0F172A),
                contentColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedNavTab == 0,
                    onClick = { selectedNavTab = 0 },
                    icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "Architect") },
                    label = { Text("Architect", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentCyan,
                        indicatorColor = Color(0xFF1E293B)
                    )
                )
                NavigationBarItem(
                    selected = selectedNavTab == 1,
                    onClick = { selectedNavTab = 1 },
                    icon = { Icon(Icons.Default.Code, contentDescription = "Code") },
                    label = { Text("Code & Files", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentCyan,
                        indicatorColor = Color(0xFF1E293B)
                    )
                )
                NavigationBarItem(
                    selected = selectedNavTab == 2,
                    onClick = { selectedNavTab = 2 },
                    icon = { Icon(Icons.Default.PlayCircle, contentDescription = "Live Runner") },
                    label = { Text("Live Runner", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentCyan,
                        indicatorColor = Color(0xFF1E293B)
                    )
                )
                NavigationBarItem(
                    selected = selectedNavTab == 3,
                    onClick = { selectedNavTab = 3 },
                    icon = { Icon(Icons.Default.FactCheck, contentDescription = "Audit") },
                    label = { Text("Audit", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentCyan,
                        indicatorColor = Color(0xFF1E293B)
                    )
                )
                NavigationBarItem(
                    selected = selectedNavTab == 4,
                    onClick = { selectedNavTab = 4 },
                    icon = { Icon(Icons.Default.Folder, contentDescription = "Projects") },
                    label = { Text("Projects", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AccentCyan,
                        indicatorColor = Color(0xFF1E293B)
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Pipeline Stepper indicator
            WorkflowStepper(currentStep = uiState.currentStep)

            // Dynamic Content Tabs
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when (selectedNavTab) {
                    0 -> ArchitectChatTab(
                        uiState = uiState,
                        userInputText = userInputText,
                        onUserInputChange = { userInputText = it },
                        onSubmit = {
                            if (uiState.currentStep == WorkflowStep.CONTINUE_DEV || uiState.currentStep == WorkflowStep.FILE_EXPORT) {
                                viewModel.continueDevelopment(userInputText)
                            } else {
                                viewModel.submitUserIdea(userInputText)
                            }
                            userInputText = ""
                        },
                        onSelectConcept = { viewModel.selectConcept(it) },
                        onToggleFeature = { viewModel.toggleFeature(it) },
                        onSelectAllFeatures = { viewModel.selectAllFeatures(it) },
                        onConfirmFeatures = { viewModel.confirmFeatures() },
                        onSelectTheme = { viewModel.selectTheme(it) },
                        onSelectFont = { viewModel.selectFont(it) },
                        onGenerateCode = { viewModel.generateCode() },
                        onSwitchTab = { selectedNavTab = it },
                        chatListState = chatListState
                    )
                    1 -> CodeViewerTab(
                        indexHtml = uiState.indexHtml,
                        adminHtml = uiState.adminHtml,
                        devPrompt = uiState.devPrompt,
                        diagnostics = uiState.diagnostics,
                        activeTab = uiState.activeFileTab,
                        onTabSelect = { viewModel.setActiveFileTab(it) },
                        onUpdateCode = { name, code -> viewModel.updateCode(name, code) }
                    )
                    2 -> LiveRunnerTab(
                        indexHtml = uiState.indexHtml,
                        adminHtml = uiState.adminHtml
                    )
                    3 -> DiagnosticsTab(
                        diagnostics = uiState.diagnostics,
                        architecture = uiState.architecture,
                        onRunDiagnostics = { viewModel.runDiagnostics() }
                    )
                    4 -> ProjectsTab(
                        projects = savedProjects,
                        currentProjectId = uiState.currentProjectId,
                        onSelectProject = { viewModel.loadProject(it) },
                        onNewProject = {
                            viewModel.startNewProject()
                            selectedNavTab = 0
                        },
                        onDeleteProject = { viewModel.deleteProject(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun ArchitectChatTab(
    uiState: BuilderUiState,
    userInputText: String,
    onUserInputChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onSelectConcept: (com.example.models.IdeaConcept) -> Unit,
    onToggleFeature: (Int) -> Unit,
    onSelectAllFeatures: (Boolean) -> Unit,
    onConfirmFeatures: () -> Unit,
    onSelectTheme: (com.example.models.ThemeOption) -> Unit,
    onSelectFont: (com.example.models.FontOption) -> Unit,
    onGenerateCode: () -> Unit,
    onSwitchTab: (Int) -> Unit,
    chatListState: androidx.compose.foundation.lazy.LazyListState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Chat Stream
        LazyColumn(
            state = chatListState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 14.dp)
        ) {
            items(uiState.chatMessages, key = { it.id }) { msg ->
                ChatBubble(message = msg)
            }

            // Interactive Stage Widgets
            item {
                when (uiState.currentStep) {
                    WorkflowStep.IDEA_INPUT -> {
                        QuickIdeaPills(onSelect = { onUserInputChange(it) })
                    }
                    WorkflowStep.TRENDING_IDEAS -> {
                        if (uiState.trendingIdeas.isNotEmpty()) {
                            TrendingIdeasSelector(
                                ideas = uiState.trendingIdeas,
                                onSelectIdea = onSelectConcept
                            )
                        }
                    }
                    WorkflowStep.FEATURE_DISCOVERY -> {
                        if (uiState.discoveredFeatures.isNotEmpty()) {
                            FeatureDiscoverySelector(
                                features = uiState.discoveredFeatures,
                                onToggleFeature = onToggleFeature,
                                onSelectAll = onSelectAllFeatures,
                                onConfirm = onConfirmFeatures
                            )
                        }
                    }
                    WorkflowStep.THEME_SELECTION -> {
                        ThemeSelector(
                            themes = com.example.ai.AIBuilderEngine.THEMES,
                            selectedTheme = uiState.selectedTheme,
                            onSelectTheme = onSelectTheme
                        )
                    }
                    WorkflowStep.FONT_SELECTION -> {
                        FontSelector(
                            fonts = com.example.ai.AIBuilderEngine.FONTS,
                            selectedFont = uiState.selectedFont,
                            onSelectFont = onSelectFont
                        )
                    }
                    WorkflowStep.ARCHITECTURE -> {
                        ArchitectureReadyCard(
                            architecture = uiState.architecture,
                            onGenerateCode = onGenerateCode
                        )
                    }
                    WorkflowStep.FILE_EXPORT, WorkflowStep.CONTINUE_DEV -> {
                        ProjectGeneratedCard(
                            onViewCode = { onSwitchTab(1) },
                            onTestLive = { onSwitchTab(2) }
                        )
                    }
                    else -> {}
                }
            }

            if (uiState.isLoading) {
                item {
                    LoadingStatusBubble(status = uiState.statusMessage)
                }
            }
        }

        // Bottom Input Dock
        Surface(
            color = Color(0xFF0F172A),
            tonalElevation = 6.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = userInputText,
                    onValueChange = onUserInputChange,
                    placeholder = {
                        Text(
                            text = if (uiState.currentStep == WorkflowStep.FILE_EXPORT || uiState.currentStep == WorkflowStep.CONTINUE_DEV)
                                "Continue from last, add feature, change theme..."
                            else
                                "Describe your app or game idea...",
                            fontSize = 13.sp,
                            color = Color(0xFF64748B)
                        )
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryIndigo,
                        unfocusedBorderColor = Color(0xFF334155),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xFF1E293B),
                        unfocusedContainerColor = Color(0xFF1E293B)
                    ),
                    maxLines = 3
                )

                IconButton(
                    onClick = onSubmit,
                    enabled = userInputText.isNotBlank() && !uiState.isLoading,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(if (userInputText.isNotBlank()) PrimaryIndigo else Color(0xFF1E293B))
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = if (userInputText.isNotBlank()) Color.White else Color(0xFF64748B),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun QuickIdeaPills(onSelect: (String) -> Unit) {
    val quickIdeas = listOf(
        "4K AMOLED Wallpaper App",
        "Free Fire Tournament Hub",
        "Admin + User Ecommerce Store",
        "Single-File Multiplayer Pong Game",
        "Cyber Velocity 2D Racer",
        "Smart AI Chatbot Assistant"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Hero Logo Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_app_logo),
                    contentDescription = "Code Build AI",
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "CODE BUILDER",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 0.5.sp
                        )
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0x3338BDF8)
                        ) {
                            Text(
                                text = "[ AI ]",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentCyan,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Architect, design & compile single-file web apps, admin systems & multiplayer games.",
                        fontSize = 11.5.sp,
                        color = Color(0xFF94A3B8),
                        lineHeight = 16.sp
                    )
                }
            }
        }

        Text(
            text = "⚡ Quick Inspiration Prompts:",
            fontSize = 12.sp,
            color = Color(0xFF94A3B8),
            fontWeight = FontWeight.Medium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            quickIdeas.forEach { idea ->
                Surface(
                    onClick = { onSelect(idea) },
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFF1E293B),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF334155))
                ) {
                    Text(
                        text = idea,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 11.5.sp,
                        color = AccentCyan
                    )
                }
            }
        }
    }
}

@Composable
fun ArchitectureReadyCard(
    architecture: com.example.models.ArchitectureConfig?,
    onGenerateCode: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, AccentEmerald)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.Verified, contentDescription = null, tint = AccentEmerald)
                Text(
                    text = "Specification & Rules Locked",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Text(
                text = architecture?.explanation ?: "Architecture verified according to development rules.",
                fontSize = 12.sp,
                color = Color(0xFFCBD5E1),
                lineHeight = 17.sp
            )

            Button(
                onClick = onGenerateCode,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
            ) {
                Icon(Icons.Default.Code, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Generate Complete Working Code", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ProjectGeneratedCard(
    onViewCode: () -> Unit,
    onTestLive: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = androidx.compose.foundation.BorderStroke(1.dp, AccentCyan)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = AccentEmerald)
                Text(
                    text = "Production Code Generated",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onTestLive,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentEmerald)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Test in Runner", fontSize = 12.sp)
                }

                FilledTonalButton(
                    onClick = onViewCode,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color(0xFF1E293B))
                ) {
                    Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Inspect Files", fontSize = 12.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun LoadingStatusBubble(status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(18.dp),
            strokeWidth = 2.dp,
            color = AccentCyan
        )
        Text(
            text = status.ifBlank { "Processing architectural stage..." },
            fontSize = 12.sp,
            color = Color(0xFF94A3B8)
        )
    }
}

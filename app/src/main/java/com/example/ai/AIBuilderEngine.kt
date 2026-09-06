package com.example.ai

import com.example.models.*

object AIBuilderEngine {

    val THEMES: List<ThemeOption> = listOf(
        ThemeOption(
            id = "amoled_dark",
            name = "Premium AMOLED Dark",
            visualDescription = "Pitch-black background (#000000) with ultra-high contrast accents, sleek neon borders, and subtle glow effects.",
            recommendedUseCase = "Media hubs, wallpaper galleries, gaming apps, battery-saving mobile platforms.",
            characteristics = listOf("Deep black #000000 canvas", "Neon indigo & cyan accents", "Glass-bordered cards", "Minimalist visual noise"),
            bgHex = "#000000",
            cardHex = "#121212",
            accentHex = "#6366F1",
            textColor = "#F8FAFC"
        ),
        ThemeOption(
            id = "material_3",
            name = "Material 3 (M3)",
            visualDescription = "Google Material You design with dynamic tonal palettes, pill-shaped chips, floating app bars, and soft elevation.",
            recommendedUseCase = "Productivity suites, utility apps, dashboards, standard modern Android apps.",
            characteristics = listOf("Tonal surface elevation", "Large rounded corners (16-24px)", "Floating bottom navigation", "Expressive ripple feedback"),
            bgHex = "#0F172A",
            cardHex = "#1E293B",
            accentHex = "#38BDF8",
            textColor = "#F1F5F9"
        ),
        ThemeOption(
            id = "glassmorphism",
            name = "Glassmorphism",
            visualDescription = "Frosted translucent panels with backdrop-blur, subtle multi-layered drop shadows, and soft ambient reflections.",
            recommendedUseCase = "Creative portfolios, fintech dashboards, modern mobile web applications.",
            characteristics = listOf("Backdrop filter blur(16px)", "Translucent border (rgba 255,255,255,0.1)", "Deep gradient backdrops", "Layered floating cards"),
            bgHex = "#0D1117",
            cardHex = "#161B22",
            accentHex = "#A855F7",
            textColor = "#E6EDF3"
        ),
        ThemeOption(
            id = "cyberpunk",
            name = "Cyberpunk Neo",
            visualDescription = "High-energy electric yellows, hot pinks, cyan lasers, and dark geometric gridlines inspired by sci-fi interfaces.",
            recommendedUseCase = "Action games, esports tournament portals, crypto hubs, futuristic tools.",
            characteristics = listOf("Electric pink #FF007F & Cyan #00F0FF", "Angle-cut buttons & badges", "Scanline decorative effects", "Monospace telemetry numbers"),
            bgHex = "#080710",
            cardHex = "#141124",
            accentHex = "#00F0FF",
            textColor = "#FFE700"
        ),
        ThemeOption(
            id = "modern_gaming",
            name = "Modern Gaming Hub",
            visualDescription = "Console-grade UI with rich purple/violet gradients, tournament badges, live status beacons, and gold badges.",
            recommendedUseCase = "Free Fire, PUBG, esports brackets, clan management, game companion apps.",
            characteristics = listOf("Deep purple to obsidian gradient", "Gold #F59E0B trophy highlights", "Animated live pulse dots", "Segmented leaderboard cards"),
            bgHex = "#0B0A1A",
            cardHex = "#181433",
            accentHex = "#8B5CF6",
            textColor = "#F3F4F6"
        ),
        ThemeOption(
            id = "luxury_black",
            name = "Luxury Black & Gold",
            visualDescription = "Understated matte black finishes paired with champagne gold typography, slim borders, and editorial spacing.",
            recommendedUseCase = "High-end ecommerce, premium memberships, VIP booking, luxury catalogs.",
            characteristics = listOf("Matte carbon #111111", "Metallic champagne gold #D4AF37", "Refined serif/sans pairing", "Generous whitespace"),
            bgHex = "#0A0A0A",
            cardHex = "#171717",
            accentHex = "#EAB308",
            textColor = "#FAFAFA"
        ),
        ThemeOption(
            id = "minimal_white",
            name = "Minimal Clean White",
            visualDescription = "Crisp, airy white background with graphite typography, subtle gray dividers, and a singular focused accent.",
            recommendedUseCase = "Documentation, clean note-taking, school systems, medical portals.",
            characteristics = listOf("Pure #FFFFFF / #F8FAFC canvas", "Charcoal #1E293B typography", "1px razor dividers", "Zero clutter"),
            bgHex = "#F8FAFC",
            cardHex = "#FFFFFF",
            accentHex = "#2563EB",
            textColor = "#0F172A"
        ),
        ThemeOption(
            id = "neon_gradient",
            name = "Neon Gradient Synth",
            visualDescription = "Vibrant multi-stop gradients flowing between violet, fuchsia, and electric blue on dark velvet surfaces.",
            recommendedUseCase = "Social communities, music apps, creative builders, modern web apps.",
            characteristics = listOf("Linear gradient(135deg, #EC4899, #8B5CF6)", "Vibrant interactive pill buttons", "Dark velvet backdrop", "Glowing badges"),
            bgHex = "#090A0F",
            cardHex = "#13141F",
            accentHex = "#EC4899",
            textColor = "#FFFFFF"
        ),
        ThemeOption(
            id = "professional_saas",
            name = "Professional SaaS Dark",
            visualDescription = "Enterprise-grade dark slate theme with crisp data density, status badges, and refined navigation bars.",
            recommendedUseCase = "Admin panels, inventory management, CRM, school dashboards.",
            characteristics = listOf("Deep slate #0F172A background", "Cool zinc surface cards #1E293B", "Status indicators (emerald, amber, rose)", "Structured table layout"),
            bgHex = "#0F172A",
            cardHex = "#1E293B",
            accentHex = "#10B981",
            textColor = "#F8FAFC"
        ),
        ThemeOption(
            id = "custom_emerald",
            name = "Bio-Emerald Cyber (Custom)",
            visualDescription = "Matrix-inspired bio-emerald phosphor glow on deep charcoal, balancing technical clarity with distinct personality.",
            recommendedUseCase = "Developer tools, terminal simulators, AI assistant portals, security tools.",
            characteristics = listOf("Charcoal #0A100D base", "Luminescent emerald #10B981 accents", "Code block high contrast", "Tactile toggle buttons"),
            bgHex = "#060D0A",
            cardHex = "#0E1A14",
            accentHex = "#10B981",
            textColor = "#ECFDF5"
        )
    )

    val FONTS: List<FontOption> = listOf(
        FontOption(
            id = "inter_space",
            name = "Inter + Space Grotesk",
            headingFont = "Space Grotesk, sans-serif",
            bodyFont = "Inter, sans-serif",
            previewHeading = "Tech Innovation Hub",
            previewBody = "Structured typography optimized for high-density modern web applications."
        ),
        FontOption(
            id = "poppins_montserrat",
            name = "Poppins + Montserrat",
            headingFont = "Poppins, sans-serif",
            bodyFont = "Montserrat, sans-serif",
            previewHeading = "Dynamic Mobile Experience",
            previewBody = "Friendly geometric curves paired with crisp legibility for engaging consumer apps."
        ),
        FontOption(
            id = "outfit_inter",
            name = "Outfit + Inter",
            headingFont = "Outfit, sans-serif",
            bodyFont = "Inter, sans-serif",
            previewHeading = "Clean Digital Canvas",
            previewBody = "Contemporary neo-grotesque style delivering maximum readability on all screens."
        ),
        FontOption(
            id = "manrope_space",
            name = "Manrope + Space Grotesk",
            headingFont = "Space Grotesk, sans-serif",
            bodyFont = "Manrope, sans-serif",
            previewHeading = "Future-Ready Architecture",
            previewBody = "Semi-rounded structural typeface crafted for digital interfaces and gaming hubs."
        ),
        FontOption(
            id = "roboto_poppins",
            name = "Roboto + Poppins",
            headingFont = "Poppins, sans-serif",
            bodyFont = "Roboto, sans-serif",
            previewHeading = "Familiar Android Native",
            previewBody = "Canonical Material Design pairing with rock-solid universal platform support."
        )
    )

    fun analyzeIdea(userQuery: String): List<IdeaConcept> {
        val q = userQuery.lowercase().trim()
        return when {
            q.contains("wallpaper") -> listOf(
                IdeaConcept(
                    id = 1,
                    title = "AI Wallpaper Platform",
                    concept = "Curated AI-generated 4K/8K wallpapers with prompt tags, search, and dynamic live previews.",
                    targetAudience = "Design enthusiasts, smartphone customizers, AI art fans",
                    usp = "Real-time AMOLED dark preview with color palette extractor and category filters",
                    importantFeatures = listOf("Prompt search", "AMOLED dark filter", "One-click download/copy URL", "Favorites manager", "Admin upload panel"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 2,
                    title = "4K AMOLED Wallpaper App",
                    concept = "Ultra-black wallpapers designed specifically to maximize OLED display contrast and battery efficiency.",
                    targetAudience = "AMOLED phone users, minimalists, mobile gamers",
                    usp = "True-black pixel verification with 0% backlight bleeding aesthetics",
                    importantFeatures = listOf("True black categories", "Resolution badges (4K/8K)", "Device frame preview", "Favorites collection"),
                    difficulty = "Beginner"
                ),
                IdeaConcept(
                    id = 3,
                    title = "Gaming Wallpaper Hub",
                    concept = "High-octane esports, battle royale, and anime wallpapers with animated live particle effects.",
                    targetAudience = "Esports gamers, Free Fire / BGMI / Valorant fans",
                    usp = "Hero character selection, tier ranking art, and live desktop mockups",
                    importantFeatures = listOf("Game title filters", "Character tags", "Trending tier list", "Admin image management"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 4,
                    title = "Anime Wallpaper Community",
                    concept = "Curated anime art gallery featuring seasonal anime, retro aesthetic, and character showcases.",
                    targetAudience = "Anime community, manga readers, Japanese pop-culture fans",
                    usp = "Studio and series categorization with instant full-screen viewer",
                    importantFeatures = listOf("Series filter", "Tag taxonomy", "HD full view modal", "Share button"),
                    difficulty = "Beginner"
                ),
                IdeaConcept(
                    id = 5,
                    title = "Dynamic Live Wallpaper Portal",
                    concept = "Interactive canvas-rendered particle animations and ambient moving geometric backgrounds.",
                    targetAudience = "Custom launcher power users and tech enthusiasts",
                    usp = "HTML5 Canvas real-time particle simulation with interactive touch effects",
                    importantFeatures = listOf("Canvas physics engine", "Speed/color sliders", "Export canvas state", "Preset library"),
                    difficulty = "Advanced"
                )
            )
            q.contains("free fire") || q.contains("tournament") || q.contains("esports") -> listOf(
                IdeaConcept(
                    id = 1,
                    title = "Esports Tournament Hub",
                    concept = "Complete tournament portal with match schedules, entry fee tracking, room ID/passwords, and live standings.",
                    targetAudience = "Free Fire / BGMI / Mobile Legends competitive players and clan leaders",
                    usp = "Admin dashboard to broadcast Room ID/Pass at countdown zero, with live slot booking",
                    importantFeatures = listOf("Slot registration", "Room ID/Pass reveal", "Prize pool calculator", "Admin match management", "Kill leaderboard"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 2,
                    title = "Battle Royale Scrims Manager",
                    concept = "Daily scrims scheduling app with automated team slots, discord ping simulation, and match logs.",
                    targetAudience = "Tier 1/2/3 scrim hosts and competitive squads",
                    usp = "Slot locking system with instant squad confirmation receipt",
                    importantFeatures = listOf("Squad slot booking", "Leaderboard tally", "Point system rules", "Admin approval workflow"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 3,
                    title = "Gaming Clan Arena",
                    concept = "Clan community hub with recruitment tests, player statistics, match history, and clan war scheduling.",
                    targetAudience = "Gaming guilds and competitive clans",
                    usp = "Roster management with K/D tracking and match highlights",
                    importantFeatures = listOf("Player roster cards", "War challenge log", "Recruitment form", "Admin roster editor"),
                    difficulty = "Beginner"
                ),
                IdeaConcept(
                    id = 4,
                    title = "Free Fire Custom Room Organizer",
                    concept = "Fast-paced tournament organizer with countdown clocks, rules checklist, and team verification.",
                    targetAudience = "Streamers and community tournament organizers",
                    usp = "Streamer overlay mode with real-time bracket display",
                    importantFeatures = listOf("Bracket generator", "Timer countdown", "Admin room broadcaster", "Result submission"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 5,
                    title = "Live Scoreboard & Tally App",
                    concept = "Point calculation engine for battle royale scoring (placement points + kill multipliers).",
                    targetAudience = "Tournament casters and official referees",
                    usp = "Instant match tally with auto-generated exportable leaderboard graphic",
                    importantFeatures = listOf("Kill/placement input", "Auto point math", "Leaderboard table", "Shareable summary card"),
                    difficulty = "Beginner"
                )
            )
            q.contains("game") || q.contains("multiplayer") || q.contains("racing") -> listOf(
                IdeaConcept(
                    id = 1,
                    title = "Single-File Online Multiplayer Pong Arena",
                    concept = "Real-time 2-player paddle game running in a single HTML file with Firebase Realtime DB room synchronization.",
                    targetAudience = "Casual mobile & web gamers",
                    usp = "Zero installation, instantaneous room code joining, smooth 60fps canvas physics",
                    importantFeatures = listOf("Room code matchmaking", "Real-time paddle sync", "Ball trajectory math", "Touch screen on-screen controls", "Scoreboard"),
                    difficulty = "Advanced"
                ),
                IdeaConcept(
                    id = 2,
                    title = "Cyber Velocity: 2D Neon Car Racer",
                    concept = "Fast-paced top-down neon highway racing game with obstacle dodging, nitro boosts, and high-score saves.",
                    targetAudience = "Arcade game enthusiasts and retro gamers",
                    usp = "Silky 60fps HTML5 Canvas particle rendering with touch steering and progressive difficulty",
                    importantFeatures = listOf("Touch steer buttons", "Procedural traffic cars", "Nitro speed surge", "High score local storage", "Audio synthesizer effects"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 3,
                    title = "Multiplayer Turn-Based Strategy Tactics",
                    concept = "Grid-based tactical arena battle where 2 players connect via Firebase room code to duel.",
                    targetAudience = "Turn-based strategy and chess lovers",
                    usp = "Firebase Realtime DB state machine with turn timer and move validation",
                    importantFeatures = listOf("Grid board system", "Turn rotation", "Unit stats & abilities", "Room join code", "Victory detection"),
                    difficulty = "Advanced"
                ),
                IdeaConcept(
                    id = 4,
                    title = "Space Odyssey: Neon Asteroid Blaster",
                    concept = "Physics-driven spaceship arcade shooter with 360-degree rotation, weapon upgrades, and boss battles.",
                    targetAudience = "Classic arcade fans",
                    usp = "Inertia flight physics with particle trails and screen-wrapping action",
                    importantFeatures = listOf("Virtual joystick", "Weapon upgrades", "Asteroid splitting", "Boss waves", "Sound effects"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 5,
                    title = "Realtime Multiplayer Trivia Battle",
                    concept = "Synchronized multiplayer quiz game with live timer, question sync, and rapid score tallies.",
                    targetAudience = "Friends, students, and trivia enthusiasts",
                    usp = "Real-time room syncing via Firebase RTDB with fast answer scoring",
                    importantFeatures = listOf("Host/join room", "Synchronized countdown", "Scoreboard", "Category selector"),
                    difficulty = "Intermediate"
                )
            )
            q.contains("ecommerce") || q.contains("store") || q.contains("amazon") || q.contains("shop") -> listOf(
                IdeaConcept(
                    id = 1,
                    title = "Apex Shop: Admin + User Commerce Platform",
                    concept = "Full-featured shopping platform with User Storefront (index.html) and Admin Inventory Portal (admin.html).",
                    targetAudience = "Online shoppers and boutique merchants",
                    usp = "External image URL integration, cart drawer, checkout modal, and real-time inventory management",
                    importantFeatures = listOf("Product grid & search", "Category filter chips", "Cart drawer with total math", "Admin product addition via image URL", "Order submission simulation"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 2,
                    title = "Sneaker Vault: Luxury Drop Platform",
                    concept = "Hype sneaker marketplace featuring limited edition drops, countdown timers, and release alerts.",
                    targetAudience = "Sneakerheads and fashion collectors",
                    usp = "Interactive drop countdowns, 360 image gallery simulator, and instant order confirmations",
                    importantFeatures = listOf("Drop calendar", "Raffle entry simulation", "Admin drop manager", "Size picker"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 3,
                    title = "Digital Asset Marketplace",
                    concept = "Curated store for digital assets, UI kits, templates, and 3D models with instant preview and download.",
                    targetAudience = "Developers, designers, and creators",
                    usp = "Live interactive asset viewer, license chooser, and filter tags",
                    importantFeatures = listOf("Instant file preview", "License tier selector", "Admin item manager", "Rating reviews"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 4,
                    title = "Fresh Market Grocery Express",
                    concept = "Mobile-first grocery delivery app with category aisles, quantity steppers, and quick checkout.",
                    targetAudience = "Busy families and daily grocery shoppers",
                    usp = "Fast 1-tap cart additions with delivery time slot picker",
                    importantFeatures = listOf("Aisle navigation", "Weight/unit selector", "Delivery slot picker", "Admin stock editor"),
                    difficulty = "Beginner"
                ),
                IdeaConcept(
                    id = 5,
                    title = "Boutique Fashion Lookbook & Shop",
                    concept = "Editorial fashion storefront blending magazine-style lookbooks with direct shop-the-look cards.",
                    targetAudience = "Style-conscious shoppers",
                    usp = "Lookbook hotspot tags that reveal product cards seamlessly",
                    importantFeatures = listOf("Hotspot visual tags", "Size guide modal", "Admin lookbook builder", "Wishlist"),
                    difficulty = "Intermediate"
                )
            )
            q.contains("chat") || q.contains("bot") || q.contains("ai") -> listOf(
                IdeaConcept(
                    id = 1,
                    title = "Nova AI: Smart Conversational Assistant",
                    concept = "Mobile AI chat app with multiple specialized personas, markdown code formatting, and exportable history.",
                    targetAudience = "Professionals, programmers, and students",
                    usp = "Integrated persona switching (Coding Mentor, Creative Writer, Business Analyst) with copyable code blocks",
                    importantFeatures = listOf("Persona selector", "Markdown code rendering", "Chat history persistence", "Prompt templates", "Admin knowledge editor"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 2,
                    title = "Customer Support Live AI Desk",
                    concept = "Admin + User customer support portal with live triage, automated FAQ answers, and ticket status.",
                    targetAudience = "Businesses and support agents",
                    usp = "Admin panel to manage ticket queues and configure auto-reply rules",
                    importantFeatures = listOf("Ticket creation", "Live chat interface", "Admin ticket status toggle", "FAQ accordion"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 3,
                    title = "Language Tutor AI Companion",
                    concept = "Interactive language learning bot with voice simulation, vocabulary cards, and grammar corrections.",
                    targetAudience = "Language learners and travelers",
                    usp = "Real-time grammar breakdown and phonetic guide",
                    importantFeatures = listOf("Difficulty level chips", "Grammar hint cards", "Daily vocabulary list", "Score tracker"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 4,
                    title = "Code Reviewer & Debugger AI",
                    concept = "Specialized coding assistant providing instant code review, bug detection, and refactoring suggestions.",
                    targetAudience = "Software developers and students",
                    usp = "Syntax-highlighted diff viewer and performance checklist",
                    importantFeatures = listOf("Multi-language snippet input", "Bug explanation breakdown", "Refactored code copy", "History"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 5,
                    title = "Story Crafter AI Adventure",
                    concept = "Interactive choose-your-own-adventure story engine guided by dynamic choices and character stats.",
                    targetAudience = "Creative writers, gamers, and book lovers",
                    usp = "Dynamic inventory and health tracking based on branching story decisions",
                    importantFeatures = listOf("Branching narrative buttons", "Character inventory sheet", "Audio mood toggle", "Save chapters"),
                    difficulty = "Intermediate"
                )
            )
            else -> listOf(
                IdeaConcept(
                    id = 1,
                    title = "ProSuite: Modern Admin + User Web Platform",
                    concept = "Comprehensive two-panel application with customer-facing interface (index.html) and administrative dashboard (admin.html).",
                    targetAudience = "Business managers, operators, and end-users",
                    usp = "Separation of concerns with real-time data flow, external image URL handling, and zero client storage bottlenecks",
                    importantFeatures = listOf("User dashboard & actions", "Admin data creation & management", "Live search & filters", "Responsive mobile-first navigation", "Firebase RTDB synchronization"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 2,
                    title = "Pulse: Dynamic Mobile Web Application",
                    concept = "High-fidelity single-file web app featuring native Android UI styling, bottom navigation, and rich tactile cards.",
                    targetAudience = "Mobile power users and digital natives",
                    usp = "Zero framework overhead, blazing fast execution, native app feel with app bars and bottom navigation",
                    importantFeatures = listOf("Bottom navigation bar", "Interactive cards with micro animations", "Search & category tabs", "Local state management", "Modal action sheets"),
                    difficulty = "Beginner"
                ),
                IdeaConcept(
                    id = 3,
                    title = "Nexus: Realtime Interactive Community Hub",
                    concept = "Community platform with user profiles, feed posting, image URL attachments, and live admin moderation.",
                    targetAudience = "Interest-based communities and clubs",
                    usp = "External image URL system, live interaction counters, and admin pin/delete controls",
                    importantFeatures = listOf("Post creation with image URL", "Like & comment counter", "Category chips", "Admin moderation panel", "User profile modal"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 4,
                    title = "Velocity: HTML5 Arcade Experience",
                    concept = "Smooth canvas-based interactive web game with touch controls, particle physics, and sound synthesizer.",
                    targetAudience = "Casual mobile web gamers",
                    usp = "Runs in a single index.html file with 60fps performance and zero external dependencies",
                    importantFeatures = listOf("Canvas physics engine", "On-screen touch controls", "High score tracking", "Audio effects", "Pause/restart system"),
                    difficulty = "Intermediate"
                ),
                IdeaConcept(
                    id = 5,
                    title = "OmniTrack: Smart Booking & Resource Manager",
                    concept = "Resource scheduling and booking platform with time slot reservation, status tracking, and admin calendar.",
                    targetAudience = "Clients, patients, service providers, and staff",
                    usp = "Slot availability verification with instant booking confirmation and admin approvals",
                    importantFeatures = listOf("Date & slot selector", "Booking status tracking", "Admin slot manager", "Customer notifications", "Search filters"),
                    difficulty = "Intermediate"
                )
            )
        }
    }

    fun discoverFeatures(concept: IdeaConcept): List<FeatureItem> {
        val title = concept.title.lowercase()
        val isAdminApp = title.contains("admin") || title.contains("platform") || title.contains("tournament") || title.contains("hub") || title.contains("shop") || title.contains("commerce")
        val isGame = title.contains("game") || title.contains("racer") || title.contains("pong") || title.contains("blaster")

        val list = mutableListOf<FeatureItem>()

        if (isGame) {
            list.add(FeatureItem(1, "HTML5 Canvas Render Engine", "Renders 60fps smooth game graphics, player entities, and particle animations.", "Automatic continuous game loop with requestAnimationFrame", "Vanilla JS canvas 2d context with delta time physics", "User", "None (Local Engine)", listOf()))
            list.add(FeatureItem(2, "Touch-Friendly Mobile Controls", "On-screen touch buttons or virtual joystick designed for thumb ergonomics.", "User taps or drags on-screen directional touch controls", "Pointer events & touch listener with preventsDefault", "User", "None", listOf("HTML5 Canvas Render Engine")))
            list.add(FeatureItem(3, "Game State & Score Machine", "Tracks score, health, lives, levels, game over, and restart states.", "Scores increment on achievements; game over modal appears on zero health", "JavaScript finite state machine with custom events", "User", "None", listOf()))
            list.add(FeatureItem(4, "High Score Local Persistence", "Saves personal best scores and achievements in device localStorage.", "Display high score badge on main menu and game over screen", "localStorage.setItem / getItem with JSON parsing", "User", "None", listOf("Game State & Score Machine")))
            list.add(FeatureItem(5, "Sound Effects Synthesizer", "Generates retro arcade sound effects without external audio files.", "Audio triggers on button taps, scoring, collisions, and game over", "Web Audio API (AudioContext) oscillator nodes", "User", "None", listOf()))
            list.add(FeatureItem(6, "Difficulty Progression Engine", "Increases speed, obstacle spawn rate, and complexity as game progresses.", "Subtle micro-adjustments to game loop velocity over elapsed time", "Time-based difficulty multiplier function", "User", "None", listOf("Game State & Score Machine")))
            list.add(FeatureItem(7, "Pause & Settings Modal Sheet", "Allows user to pause the game, adjust volume, and toggle particle effects.", "Tap floating pause button to freeze loop and show bottom sheet", "Overlay backdrop-filter modal with resume/restart actions", "User", "None", listOf()))
            list.add(FeatureItem(8, "Firebase Multiplayer Room Sync", "Synchronizes player coordinates, ball trajectory, and scores across devices.", "Host creates room code; Guest joins code to start live match", "Firebase Realtime Database /rooms/{roomId} child event listeners", "Shared", "Firebase Realtime Database", listOf("Touch-Friendly Mobile Controls")))
            list.add(FeatureItem(9, "Leaderboard & Match Summary", "Displays post-match kill/point breakdown and global ranking.", "End of match shows full victory/defeat banner with stats", "DOM table rendering with animated count-up numbers", "Shared", "Firebase Realtime Database (Optional)", listOf("Game State & Score Machine")))
            list.add(FeatureItem(10, "Particle Explosion Effects", "Dynamic bursting particles on impact, score points, or game over.", "Visual flair around collision points", "Array of particle objects with velocity, decay, and color fade", "User", "None", listOf("HTML5 Canvas Render Engine")))
        } else {
            list.add(FeatureItem(1, "Mobile-First App Bar & Brand Header", "Displays app logo, title, search trigger, and notifications badge.", "Fixed top app bar with Android native elevation and icon buttons", "Semantic header with CSS flexbox and elevation shadow", "User", "None", listOf()))
            list.add(FeatureItem(2, "Category Filter Navigation Chips", "Horizontally scrollable filter pills to browse content by category.", "User taps a chip; list updates instantly with active highlight", "CSS overflow-x auto with JavaScript event delegation", "User", "None", listOf()))
            list.add(FeatureItem(3, "Instant Search & Keyword Filter", "Live search bar that filters displayed items in real-time as user types.", "Typing queries immediately matches title, tags, and description", "JS input event listener with debounce and case-insensitive matching", "User", "None", listOf()))
            list.add(FeatureItem(4, "Interactive Item Cards Grid", "Modern Material 3 cards displaying image, title, badge, and action button.", "Tap card to open full-screen detail view; tap action to bookmark/cart", "CSS CSS Grid auto-fit with smooth scale transitions", "User", "None", listOf("Category Filter Navigation Chips")))
            list.add(FeatureItem(5, "Full-Screen Detail Modal Sheet", "Bottom sheet or modal displaying full details, high-res image, and actions.", "Tapping any item slides up a native-feeling bottom sheet", "CSS translate-y transition with touch-close swipe gesture", "User", "None", listOf("Interactive Item Cards Grid")))
            list.add(FeatureItem(6, "Favorites / Bookmarks System", "Allows users to save favorite items with instant heart toggle.", "Heart icon toggles red and updates saved items count", "Local state with localStorage persistence or Firebase RTDB", "User", "Optional Firebase", listOf("Interactive Item Cards Grid")))
            list.add(FeatureItem(7, "Bottom Navigation Bar", "Native Android 3-5 destination navigation bar with active pill indicator.", "Switch between Home, Categories, Favorites, and Profile/Admin", "Fixed bottom nav with CSS window-insets safe padding", "User", "None", listOf()))
            list.add(FeatureItem(8, "Admin Dashboard & Stats Overview", "Displays total items, active users, views, and activity metrics.", "Admin logs in to view quick metric summary cards", "Metric calculation engine with icon highlights", "Admin", "Firebase Realtime Database", listOf()))
            list.add(FeatureItem(9, "Admin Content Creator (Image URL System)", "Form for admin to add new items using externally hosted image URLs.", "Admin pastes image URL (https://...), enters title, price/specs, and submits", "Form validation with live image preview before DB write", "Admin", "Firebase Realtime Database", listOf("Admin Dashboard & Stats Overview")))
            list.add(FeatureItem(10, "Content Management (Edit / Delete)", "Allows admin to update existing entries or remove outdated items.", "Admin taps edit/delete buttons on management table", "Firebase RTDB child remove() and update() operations", "Admin", "Firebase Realtime Database", listOf("Admin Content Creator (Image URL System)")))
            list.add(FeatureItem(11, "Secure Admin Authentication Portal", "Protects admin actions without hardcoding secrets in public HTML.", "Admin enters credentials via auth dialog or configured key", "Firebase Auth / secure session state check", "Admin", "Firebase Authentication (or RTDB rule check)", listOf("Admin Dashboard & Stats Overview")))
            list.add(FeatureItem(12, "Notification Toast & Micro-Alerts", "Displays sleek transient toasts on user actions (e.g. Added to Cart, Saved).", "Toast slides down from top or up from bottom for 3 seconds", "DOM toast queue with CSS keyframe animation", "Shared", "None", listOf()))
        }

        return list
    }

    fun determineArchitecture(
        concept: IdeaConcept,
        features: List<FeatureItem>
    ): ArchitectureConfig {
        val selectedFeatures = features.filter { it.isSelected }
        val hasAdmin = selectedFeatures.any { it.ownership == "Admin" } || concept.title.contains("Admin") || concept.title.contains("Platform")
        val isGame = concept.title.contains("Game") || concept.title.contains("Racer") || concept.title.contains("Pong") || concept.title.contains("Arcade")
        val isMultiplayer = isGame && selectedFeatures.any { it.title.contains("Multiplayer") || it.firebaseRequirement.contains("Firebase") }

        return when {
            isMultiplayer -> ArchitectureConfig(
                type = "Single-File Multiplayer HTML5 Game",
                isUserOnly = true,
                isAdminPlusUser = false,
                isMultiplayerGame = true,
                frontendFiles = listOf("index.html"),
                usesFirebaseRealtimeDb = true,
                firebaseStorageForbidden = true,
                imageHostingSystem = "None (Procedural Canvas Graphics + SVG / External Sprites)",
                adminAuthDetails = "Host creates room code; guest connects via room ID in Firebase RTDB /rooms/{roomId}",
                stackDetails = listOf("HTML5 (Single index.html)", "CSS3 (Embedded)", "Vanilla JavaScript (Embedded)", "HTML5 Canvas API", "Firebase Realtime Database", "Web Audio API"),
                explanation = "Single-file online multiplayer game. Uses Firebase Realtime Database strictly for room discovery, player coordinate synchronization, and live match state. All CSS, JS, and game loops reside within index.html for maximum portability."
            )
            hasAdmin -> ArchitectureConfig(
                type = "Admin + User Web Platform",
                isUserOnly = false,
                isAdminPlusUser = true,
                isMultiplayerGame = false,
                frontendFiles = listOf("index.html", "admin.html"),
                usesFirebaseRealtimeDb = true,
                firebaseStorageForbidden = true,
                imageHostingSystem = "External Image URL Hosting (Admin enters https://... URLs; stored in Realtime Database; User panel renders via <img src>)",
                adminAuthDetails = "Admin credentials are not hardcoded in public HTML. Configured via Firebase Auth or project owner database access rule check.",
                stackDetails = listOf("HTML5 (index.html for User, admin.html for Admin)", "CSS3 (Embedded per file)", "Vanilla JavaScript (Embedded per file)", "Font Awesome (Icons)", "Firebase Realtime Database (Data & URLs)", "External Image URL Architecture"),
                explanation = "Exactly 2 frontend files: index.html (User Storefront/Portal) and admin.html (Admin Management Panel). All data is synchronized via Firebase Realtime Database. Firebase Storage is STRICTLY FORBIDDEN per rule #9; images are ingested purely as external URLs."
            )
            else -> ArchitectureConfig(
                type = "User-Only Web App",
                isUserOnly = true,
                isAdminPlusUser = false,
                isMultiplayerGame = false,
                frontendFiles = listOf("index.html"),
                usesFirebaseRealtimeDb = false,
                firebaseStorageForbidden = true,
                imageHostingSystem = "High-Quality Unsplash/Cloudflare Image URLs embedded or user-curated",
                adminAuthDetails = "Not applicable (User-only client-side architecture)",
                stackDetails = listOf("HTML5 (Single index.html)", "CSS3 (Embedded in index.html)", "Vanilla JavaScript (Embedded in index.html)", "Font Awesome (Icons)", "LocalStorage for persistence"),
                explanation = "User-only client-side application contained entirely within a single index.html file. Zero framework bloat. All CSS and JavaScript are self-contained. Fast, offline-capable, and immediately deployable to GitHub Pages, Netlify, or Android WebViews."
            )
        }
    }

    fun generateDetailedDevPrompt(
        concept: IdeaConcept,
        features: List<FeatureItem>,
        theme: ThemeOption,
        font: FontOption,
        arch: ArchitectureConfig
    ): String {
        val selected = features.filter { it.isSelected }
        val featureListStr = selected.mapIndexed { idx, f ->
            "${idx + 1}. **${f.title}** (${f.ownership}): ${f.description}\n   - *Interaction*: ${f.userInteraction}\n   - *Implementation*: ${f.techImplementation}\n   - *Backend*: ${f.firebaseRequirement}"
        }.joinToString("\n")

        return """
# MASTER DEVELOPMENT SPECIFICATION & PROMPT

## 1. Project Overview
- **Project Name**: ${concept.title}
- **Project Objective**: Build a production-grade, native-Android-styled web application/game that transforms "${concept.concept}" into a fully functional reality.
- **Target Users**: ${concept.targetAudience}
- **Unique Selling Proposition (USP)**: ${concept.usp}
- **Architecture Type**: ${arch.type}

## 2. Selected UI & Design System
- **Theme Name**: ${theme.name}
- **Visual Description**: ${theme.visualDescription}
- **Color Palette**:
  - Background: `${theme.bgHex}`
  - Surface/Card: `${theme.cardHex}`
  - Accent / Brand: `${theme.accentHex}`
  - Text Primary: `${theme.textColor}`
- **Typography Pair**:
  - Heading Font: `${font.headingFont}`
  - Body Font: `${font.bodyFont}`
- **Native Android UI Aesthetics**:
  - Material 3 cards with rounded corners and subtle border highlights.
  - Floating action button (FAB) with elevation ripple.
  - Bottom navigation bar with active pill indicators and safe-area padding.
  - Full-screen modal sheets with smooth touch gestures.
  - Micro-animations for feedback, badge counts, and loading states.

## 3. Frontend Architecture & File Structure
- **Target Frontend Stack**: HTML5, CSS3, Vanilla JavaScript, Font Awesome.
- **Strict File Rule**: ${arch.frontendFiles.joinToString(" and ")} (${arch.frontendFiles.size} frontend file(s) total).
- **Embedded Rule**: All styles (<style>) and scripts (<script>) must be contained within their respective HTML files.
${if (arch.isAdminPlusUser) "- **Admin Panel**: `admin.html` with inventory/content management, image URL ingestion, and stats overview.\n- **User Panel**: `index.html` with catalog browsing, instant search, categories, favorites, and detail view." else ""}
${if (arch.isMultiplayerGame) "- **Multiplayer Engine**: `index.html` containing complete 60fps canvas loop, touch controls, and Firebase Realtime Database room synchronization." else ""}

## 4. Firebase & Data Rules
${if (arch.usesFirebaseRealtimeDb) """
- **Firebase Realtime Database**: YES, use Realtime Database for all structured records and room synchronization.
- **Firebase Storage**: STRICTLY FORBIDDEN. Never use or initialize Firebase Storage.
- **Image System**: Admin enters external image URLs (e.g., https://images.unsplash.com/...). URLs are stored in RTDB and rendered directly in user components.
- **Admin Authentication**: Admin credentials must NEVER be hardcoded in public HTML. Use configuration checks or Firebase Auth.
""" else """
- **Backend Requirement**: None required. Client-side state managed via modern Vanilla JS and localStorage.
"""}

## 5. Comprehensive Feature Implementation Checklist
$featureListStr

## 6. Code Diagnostics & Quality Standards
- Strict HTML5 semantic structure with valid closing tags and accessible meta tags.
- Responsive mobile-first CSS with CSS Grid, Flexbox, and touch-target sizing (min 48px).
- Zero placeholder stubs: no `// TODO` or `// implement later`. Every feature must be operational.
- Cross-platform deployment compatibility with GitHub Pages, Netlify, InfinityFree, and Android WebView wrappers.
        """.trimIndent()
    }
}

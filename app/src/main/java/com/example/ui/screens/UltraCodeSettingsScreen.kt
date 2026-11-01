package com.example.ui.screens

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import java.nio.ByteBuffer
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

private object SecureProviderKeys {
    private const val STORE = "ultra_code_secure_keys"
    private const val KEY_ALIAS = "ultra_code_aes_key"
    private lateinit var appContext: android.content.Context

    fun init(context: android.content.Context) { appContext = context.applicationContext }

    private fun secretKey(): SecretKey {
        val ks = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        (ks.getKey(KEY_ALIAS, null) as? SecretKey)?.let { return it }
        val generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        generator.init(KeyGenParameterSpec.Builder(KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build())
        return generator.generateKey()
    }

    fun get(name: String): String {
        val encoded = appContext.getSharedPreferences(STORE, 0).getString(name, null) ?: return ""
        return try {
            val bytes = Base64.decode(encoded, Base64.NO_WRAP)
            val ivSize = ByteBuffer.wrap(bytes, 0, 4).int
            val iv = bytes.copyOfRange(4, 4 + ivSize)
            val encrypted = bytes.copyOfRange(4 + ivSize, bytes.size)
            Cipher.getInstance("AES/GCM/NoPadding").run {
                init(Cipher.DECRYPT_MODE, secretKey(), GCMParameterSpec(128, iv))
                String(doFinal(encrypted), Charsets.UTF_8)
            }
        } catch (_: Exception) { "" }
    }

    fun save(name: String, value: String) {
        if (value.isBlank()) { delete(name); return }
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey())
        val iv = cipher.iv
        val encrypted = cipher.doFinal(value.toByteArray(Charsets.UTF_8))
        val packed = ByteBuffer.allocate(4 + iv.size + encrypted.size).putInt(iv.size).put(iv).put(encrypted).array()
        appContext.getSharedPreferences(STORE, 0).edit().putString(name, Base64.encodeToString(packed, Base64.NO_WRAP)).apply()
    }

    fun delete(name: String) { appContext.getSharedPreferences(STORE, 0).edit().remove(name).apply() }
}

@Composable
fun UltraCodeSettingsScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current
    LaunchedEffect(Unit) { SecureProviderKeys.init(context) }
    val providers = listOf(
        "Gemini API key" to "gemini",
        "Groq API key" to "groq",
        "OpenRouter API key" to "openrouter",
        "Cloudflare API token" to "cloudflare_token",
        "Cloudflare Account ID" to "cloudflare_account"
    )
    val values = remember { mutableStateMapOf<String, String>() }
    var showKeys by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("Keys are encrypted on this device using Android Keystore.") }
    var localModel by remember { mutableStateOf("No local model installed") }

    LaunchedEffect(Unit) { providers.forEach { (label, key) -> values[key] = SecureProviderKeys.get(key) } }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("ULTRA CODE ULTIMATE", style = MaterialTheme.typography.headlineSmall)
        Text("AI Provider Settings", style = MaterialTheme.typography.titleLarge)
        Text("Add your own keys. They are encrypted locally and are not embedded in the APK or sent to GitHub.")
        providers.forEach { (label, key) ->
            OutlinedTextField(
                value = values[key].orEmpty(),
                onValueChange = { values[key] = it },
                label = { Text(label) },
                leadingIcon = { Icon(Icons.Default.Key, contentDescription = null) },
                visualTransformation = if (showKeys) androidx.compose.ui.text.input.VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = { SecureProviderKeys.delete(key); values[key] = ""; message = "$label deleted from this device." }) { Text("Delete") }
            }
        }
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = showKeys, onCheckedChange = { showKeys = it })
            Text("Show API keys")
        }
        Button(onClick = {
            providers.forEach { (_, key) -> SecureProviderKeys.save(key, values[key].orEmpty().trim()) }
            message = "Saved securely on this device."
        }, modifier = Modifier.fillMaxWidth()) { Text("Save API Keys") }
        HorizontalDivider()
        Text("Local AI", style = MaterialTheme.typography.titleLarge)
        Text("Local models can run offline only when a compatible inference runtime and model are installed. This APK does not yet bundle a local inference runtime, so offline code generation is not active.")
        OutlinedTextField(value = localModel, onValueChange = { localModel = it },
            label = { Text("Local model note") }, modifier = Modifier.fillMaxWidth())
        Text(message, color = MaterialTheme.colorScheme.primary)
    }
}

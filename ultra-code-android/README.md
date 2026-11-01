# Ultra Code Ultimate — Android

A separate Android app project. The original Code Builder AI app and files remain untouched.

## Build
GitHub Actions builds a debug APK on pushes to `main`. Open the repository's **Actions** tab, select **Build Ultra Code Ultimate APK**, open the latest successful run, then download artifact `Ultra-Code-Ultimate-debug-apk`. The artifact is a ZIP containing the APK.

## AI providers
The app includes encrypted on-device storage for Gemini, Groq, OpenRouter, and Cloudflare Workers AI credentials, plus live provider connectivity tests and a prompt workspace. Cloudflare requires both an account ID and API token. Never share keys or commit them to Git.

## Local AI status
This starter provides local model storage/download management and a clear device-storage interface. A downloaded model file by itself does not run inference; an Android-compatible inference runtime and a compatible model are required before offline generation can be called fully implemented. Do not enter private keys into public demos.

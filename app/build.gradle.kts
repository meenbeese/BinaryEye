plugins {
	id("com.android.application")
	id("kotlin-android")
}

android {
	namespace = "de.markusfisch.android.binaryeye"
	compileSdk = sdk_version

	defaultConfig {
		minSdk = 9
		targetSdk = sdk_version

		versionCode = 138
		versionName = "1.63.10"
	}

	signingConfigs {
		create("release") {
			keyAlias = System.getenv("ANDROID_KEY_ALIAS")
			keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
			storePassword = System.getenv("ANDROID_STORE_PASSWORD")
			val filePath = System.getenv("ANDROID_KEYFILE")
			storeFile = if (filePath != null) file(filePath) else null
		}
	}

	sourceSets {
		getByName("main").java.srcDirs("src/main/kotlin")
		getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
		getByName("test").java.srcDirs("src/test/kotlin")
	}

	buildTypes {
		getByName("debug") {
			applicationIdSuffix = ".debug"
		}

		getByName("release") {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
			signingConfig = signingConfigs.getByName("release")
		}
	}

	buildFeatures {
		buildConfig = true
	}

	bundle {
		language {
			// To make the app bundle contain all language resources
			// so the in-app language setting works. Another solution
			// would be to use the PlayCore API to download language
			// resources on demand, but it makes much more sense to
			// simply include the ~60 kb than adding fragile code and
			// dependencies.
			enableSplit = false
		}
	}

	compileOptions {
		// Required for Gradle 8.
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
}

dependencies {
	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

	// Support
	implementation("com.android.support:appcompat-v7:$support_version")
	implementation("com.android.support:design:$support_version")
	implementation("com.android.support:preference-v7:$support_version")
	implementation("com.android.support:preference-v14:$support_version")

	// External
	implementation("com.github.markusfisch:CameraView:1.10.0")
	implementation("com.github.markusfisch:ScalingImageView:1.4.1")
	implementation("com.github.markusfisch:zxing-cpp:v2.2.0.5")

	// Testing
	testImplementation("junit:junit:4.13.2")
}

buildscript {
	val kotlinVersion by extra("1.9.22")
	val toolsVersion by extra("8.5.1")
	val sdkVersion by extra(34)
	val supportVersion by extra("25.3.1")

	repositories {
		google()
		mavenCentral()
	}

	dependencies {
		classpath("com.android.tools.build:gradle:$toolsVersion")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
	}
}

allprojects {
	gradle.projectsEvaluated {
		tasks.withType<JavaCompile>().configureEach {
			options.compilerArgs.add("-Xlint:unchecked")
		}
	}
}

tasks.register<Delete>("clean") {
	delete(rootProject.buildDir)
}

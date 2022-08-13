plugins {
    id("java-library")  // applied id("java")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

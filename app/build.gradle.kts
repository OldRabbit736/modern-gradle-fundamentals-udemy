plugins {
    id("my-application")
}

application {
    mainClass.set("com.example.MyApplication")
}

dependencies {
    implementation(platform("com.example:platform"))

    implementation(project(":data-model"))
    implementation(project(":business-logic"))

    // 실제로 코드를 돌리는 곳에서 api 에 대한 implementation 을 선택 --> runtimeOnly
    runtimeOnly("org.slf4j:slf4j-simple")
    //runtimeOnly(libs.slf4j.simple) // gradle/libs.versions.toml
}

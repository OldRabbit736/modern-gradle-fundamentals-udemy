import com.example.gradle.JarCount

plugins {
    id("application")
    id("my-java-base")
}

tasks.register<JarCount>("countJars") {
    group = "My Group"
    description = "Counts!"

    allJars.from(tasks.jar)
    allJars.from(configurations.runtimeClasspath)

    countFile.set(layout.buildDirectory.file("gen/count.txt"))
}

// 이미 있는 task type ZIP 을 사용하여 새로운 "bundle" 이라는 task 를 생성 (타입을 지정하지 않고 생성할 수도 있다.)
tasks.register<Zip>("bundle") {
    group = "My Group"
    description = "packages it all!"


    // 아래처럼 직접 경로를 설정할 수도 있지만,
    // from("build/libs/app.jar")

    // 아래와 같이 jar task 의 아웃풋을 인수로 설정한다.
    from(tasks.jar)
    // runtimeClasspath 의 모듈의 jar 를 인수로 설정한다.
    from(configurations.runtimeClasspath)

    destinationDirectory.set(layout.buildDirectory.dir("distribution"))
}

tasks.build {
    dependsOn(tasks.named("bundle"))
}

tasks.register("buildAll") {
    description = "Builds even more!"

    dependsOn(tasks.build)
    dependsOn(tasks.named("countJars"))
}

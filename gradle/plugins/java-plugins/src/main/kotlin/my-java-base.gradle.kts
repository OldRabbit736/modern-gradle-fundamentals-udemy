plugins {
    id("java")
    id("com.diffplug.spotless")
}

// TaskContainer 를 사용하여 플러그인에 의해 등록된 task 를 세팅할 수 있다.
// 또는 task type 이나 이름으로 task 에 접근할 수도 있다.
// task 직접 세팅하기 보다는 extension 세팅이 더 좋은 방법이다. (가능하다면)

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

// 타입으로 task 접근
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}



/*
테스트 분리 방법은 다음의 2가지가 있다.
- tag 분리
- sourceSet 분리
 */

/*
방법1 - tag 분리
 */
tasks.test {
    useJUnitPlatform {
        excludeTags("slow")
    }

    maxParallelForks = 4

    maxHeapSize = "1g"
}

tasks.register<Test>("testSlow") {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useJUnitPlatform {
        includeTags("slow")
    }
}

/*
방법2 - sourceSet 분리
 */
sourceSets.create("integrationTest")    // 신규 source set 정의

tasks.register<Test>("integrationTest") {
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath

    useJUnitPlatform()
}


//sourceSets.main {
//    java.setSrcDirs(listOf(layout.projectDirectory.dir("sources")))
//}
//sourceSets.test


//// 이름으로 task 접근 - groovy dsl 에서는 이 방법을 사용해야 한다.
//tasks.named<JavaCompile>("compileJava") {
//
//}
//
//// TaskContainer
//tasks.compileJava {
//    //options.encoding = "UTF-8"
//}
//
//tasks.compileTestJava {
//    //options.encoding = "UTF-8"
//}
//
//
//tasks.check {
//    dependsOn(tasks.named("testSlow"))
//}
//
//tasks.javadoc {
//
//}

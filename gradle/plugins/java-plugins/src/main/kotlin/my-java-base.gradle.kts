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

// 이름으로 task 접근 - groovy dsl 에서는 이 방법을 사용해야 한다.
tasks.named<JavaCompile>("compileJava") {

}

// TaskContainer
tasks.compileJava {
    //options.encoding = "UTF-8"
}

tasks.compileTestJava {
    //options.encoding = "UTF-8"
}

tasks.test {

}

tasks.javadoc {

}

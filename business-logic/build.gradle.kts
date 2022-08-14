plugins {
    id("my-java-library")
}

dependencies {
    //implementation(platform("com.example:platform"))

    implementation(project(":data-model"))

    //implementation("org.apache.commons:commons-lang3")
    implementation(libs.commons.lang) // gradle/libs.versions.toml

    /*
    slf4j implementation 모듈은 runtime 시에만 필요 --> 따라서 app module 에서는 implementation 모듈을 runtimeOnly 로 등록
     */
    //implementation("org.slf4j:slf4j-api")
    implementation(libs.slf4j.api) // gradle/libs.versions.toml

    /*
    org.apache.commons.lang3.StringUtils 과 같은 commons-lang3에 정의되어 있는 클래스가 app module 에서도 사용가능하게 된다.
     */
    //api("org.apache.commons:commons-lang3:3.12.0") <-- dependency is transitively visible as compilation

    /*
    해당 디펜던시는 런타임 시에만 classpath 에 추가된다.
    API, Implementation 이 구분되어 있을 경우에 주로 사용된다.
     */
    //runtimeOnly("group:name") <-- only at runtime

    // 사용되는 경우는 거의 없다. 라이브러리는 런타임 시 사용되려고 받는 경우가 거의 대부분일 것이기 때문이다.
    //compileOnly("group:name") <-- only at compile time
}

//configurations {
//    compileClasspath    // <- Compile time "view" (aka Variant) // implementation, compileOnly, ...
//    runtimeClasspath    // <- Runtime "view" (aka Variant) // implementation, runtimeOnly, ...
//}

//dependencies.constraints {
//    implementation("org.apache.commons:commons-lang3:3.12.0")
//}

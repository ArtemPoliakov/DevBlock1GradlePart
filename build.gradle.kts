import org.gradle.jvm.tasks.Jar
plugins {
    id("java")
}
group = "org.example"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")
}
java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
val fatJar = task("fatJar", type = Jar::class) {
    manifest {
        attributes["Implementation-Title"] = "GradleHwPart"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "org.example.Main"
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}
val customTask = task("CustomTask"){
    println("Hello from Artem")
}
tasks {
    "build" {
        dependsOn(fatJar)
    }
    "fatJar" {
        dependsOn(customTask)
    }
}

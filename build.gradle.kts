import com.diffplug.spotless.LineEnding

plugins {
  java
  `java-library`
  `maven-publish`
  signing
  id("checkstyle")
  id("com.diffplug.spotless") version "6.6.1"
  id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "io.github.shiruka"

val signRequired = !rootProject.property("dev").toString().toBoolean()

checkstyle {
  configFile = file("checkstyle.xml")
}

spotless {
  lineEndings = LineEnding.UNIX

  java {
    importOrder()
    removeUnusedImports()
    endWithNewline()
    indentWithSpaces(2)
    trimTrailingWhitespace()
    prettier(
      mapOf(
        "prettier" to "2.3.1",
        "prettier-plugin-java" to "1.6.1"
      )
    ).config(
      mapOf(
        "parser" to "java",
        "tabWidth" to 2,
        "useTabs" to false
      )
    )
  }
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

repositories {
  mavenCentral()
  maven(SNAPSHOTS)
  mavenLocal()
}

dependencies {
  compileOnlyApi(nbtLibrary)

  compileOnly(lombokLibrary)
  compileOnly(annotationsLibrary)
  compileOnly(fastutilLibrary)
  compileOnly(guavaLibrary)
  compileOnly(commonsLang3Library)
  compileOnly(nettyBufferLibrary)
  compileOnly(jacksonDatabindLibrary)
  compileOnly(jacksonDataformatYamlLibrary)
  compileOnly(log4jLibrary)
  compileOnly(guiceLibrary)

  annotationProcessor(lombokLibrary)
  annotationProcessor(annotationsLibrary)

  testImplementation(lombokLibrary)
  testImplementation(annotationsLibrary)

  testAnnotationProcessor(lombokLibrary)
  testAnnotationProcessor(annotationsLibrary)
}

tasks {
  jar {
    define()
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name()
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
    (options as StandardJavadocDocletOptions).tags("todo")
    exclude("io/github/shiruka/api/base/Colors.java")
  }

  val javadocJar by creating(Jar::class) {
    dependsOn("javadoc")
    define(classifier = "javadoc")
    from(javadoc)
  }

  val sourcesJar by creating(Jar::class) {
    dependsOn("classes")
    define(classifier = "sources")
    from(sourceSets["main"].allSource)
  }

  checkstyleMain {
    dependsOn(spotlessApply)
  }

  build {
    dependsOn(spotlessApply)
    dependsOn(jar)
    dependsOn(sourcesJar)
    dependsOn(javadocJar)
  }

  test {
    useJUnitPlatform()
  }

  checkstyleTest {
    isEnabled = false
  }

  checkstyleMain {
    exclude("io/github/shiruka/api/base/Colors.java")
  }
}

publishing {
  publications {
    val publication = create<MavenPublication>("mavenJava") {
      groupId = project.group.toString()
      artifactId = project.name
      version = project.version.toString()

      from(components["java"])
      artifact(tasks["sourcesJar"])
      artifact(tasks["javadocJar"])
      pom {
        name.set("Api")
        description.set("An Api project that helps developers to create unique plugins for Shiru ka.")
        url.set("https://shiruka.github.io/api")
        licenses {
          license {
            name.set("MIT License")
            url.set("https://mit-license.org/license.txt")
          }
        }
        developers {
          developer {
            id.set("portlek")
            name.set("Hasan Demirtaş")
            email.set("utsukushihito@outlook.com")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/shiruka/api.git")
          developerConnection.set("scm:git:ssh://github.com/shiruka/api.git")
          url.set("https://github.com/shiruka/api")
        }
      }
    }

    signing {
      isRequired = signRequired
      if (isRequired) {
        useGpgCmd()
        sign(publication)
      }
    }
  }
}

nexusPublishing {
  repositories {
    sonatype()
  }
}

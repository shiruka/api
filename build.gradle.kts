import com.diffplug.spotless.LineEnding

plugins {
  java
  `java-library`
  `maven-publish`
  signing
  id("checkstyle")
  alias(libs.plugins.spotless)
  alias(libs.plugins.nexus)
}

group = "io.github.shiruka"

checkstyle {
  configFile = file("checkstyle.xml")
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
  mavenLocal()
}

dependencies {
  compileOnlyApi(libs.nbt)

  compileOnly(libs.lombok)
  compileOnly(libs.annotations)
  compileOnly(libs.fastutil)
  compileOnly(libs.guava)
  compileOnly(libs.commonslang)
  compileOnly(libs.netty)
  compileOnly(libs.jackson.databind)
  compileOnly(libs.jackson.yaml)
  compileOnly(libs.log4j)
  compileOnly(libs.guice)

  annotationProcessor(libs.lombok)
  annotationProcessor(libs.annotations)

  testImplementation(libs.lombok)
  testImplementation(libs.annotations)

  testAnnotationProcessor(libs.lombok)
  testAnnotationProcessor(libs.annotations)
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks {
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
    archiveClassifier.set("javadoc")
    from(javadoc)
  }

  val sourcesJar by creating(Jar::class) {
    dependsOn("classes")
    archiveClassifier.set("sources")
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

val spotlessApply = rootProject.property("spotless.apply").toString().toBoolean()
val signRequired = !rootProject.property("dev").toString().toBoolean()

if (spotlessApply) {
  spotless {
    lineEndings = LineEnding.UNIX

    format("encoding") {
      target("*.*")
      encoding("UTF-8")
    }

    java {
      target("**/src/**/java/**/*.java")
      importOrder()
      removeUnusedImports()
      endWithNewline()
      indentWithSpaces(2)
      trimTrailingWhitespace()
      prettier(
        mapOf(
          "prettier" to "2.7.1",
          "prettier-plugin-java" to "1.6.2"
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

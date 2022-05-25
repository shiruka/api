import org.gradle.api.tasks.bundling.AbstractArchiveTask

fun AbstractArchiveTask.define(
  name: String = project.name,
  classifier: String? = null,
  version: String? = null
) {
  archiveClassifier.set(classifier)
  archiveClassifier.convention(classifier)
  archiveBaseName.set(name)
  archiveBaseName.convention(name)
  archiveVersion.set(version)
  archiveVersion.convention(version)
}

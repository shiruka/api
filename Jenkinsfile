pipeline {
    agent any
    tools {
        maven 'Maven 3'
        jdk 'Java 11'
    }
    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '5'))
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                    archiveArtifacts artifacts: 'target/api-1.0.0-SNAPSHOT.jar', fingerprint: true
                }
            }
        }

        stage ('Deploy') {
            when {
                anyOf {
                    branch 'master'
                }
            }
            steps {
                sh 'mvn clean deploy'
                step([$class: 'JavadocArchiver',
                        javadocDir: 'target/site/apidocs',
                        keepAll: false])
            }
        }
    }

    post {
        always {
            deleteDir()
        }
    }
}

pipeline {
    agent any

    tools {
        jdk 'JDK_17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test -DskipUnitTests=false -DskipIntegrationTests=true'
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Integration Tests') {
            steps {
                sh 'mvn verify -DskipUnitTests=true -DskipIntegrationTests=false'
                junit '**/target/failsafe-reports/*.xml'
            }
        }

        stage('Controller Tests') {
            steps {
                sh 'mvn verify -DskipUnitTests=true -DskipIntegrationTests=true -DskipWebTests=false'
            }
        }

        stage('SonarQube analysis') {
            steps {
                // Roda apenas o Maven Sonar Goal
                sh """
                    mvn sonar:sonar \
                        -Dsonar.projectKey=faithwords-library-backend \
                        -Dsonar.sources=src \
                        -Dsonar.host.url=http://sonarqube:9000 \
                        -Dsonar.login=sonar \
                        -Dsonar.password=sonar
                """
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            junit '**/target/failsafe-reports/*.xml'
        }
    }
}

pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://sonarqube:9000'
    }

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
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh '''
                        mvn sonar:sonar \
                            -Dsonar.projectKey=faithwords-library-backend \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_TOKEN \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.tests=src/test/java
                    '''
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
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

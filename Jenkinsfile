pipeline {
    agent any
    environment {
        SONAR_HOST_URL = 'http://sonarqube:9000'
        SONAR_USER = 'sonar'       // substitua se necessário
        SONAR_PASSWORD = 'sonar'   // substitua se necessário
    }

    tools {
        jdk 'JDK_17'        // Nome configurado no Jenkins
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
                sh """
                    sonar-scanner \
                        -Dsonar.projectKey=faithwords-library-backend \
                        -Dsonar.sources=src \
                        -Dsonar.host.url=${SONAR_HOST_URL} \
                        -Dsonar.login=${SONAR_USER} \
                        -Dsonar.password=${SONAR_PASSWORD}
                """
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            junit 'target/failsafe-reports/*.xml'
        }
 //       failure {
 //           mail to: 'time@empresa.com',
 //                subject: "Falha no build ${env.BUILD_NUMBER}",
 //                body: "Verificar os logs do Jenkins: ${env.BUILD_URL}"
 //       }
    }
}

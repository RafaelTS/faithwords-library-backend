pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://sonarqube:9000'
        SONAR_USER = 'sonar'
        SONAR_PASSWORD = 'sonar'
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

        stage('Controller (Web Slice) Tests') {
            steps {
                sh 'mvn verify -DskipUnitTests=true -DskipIntegrationTests=true -DskipWebTests=false'
                // opcional: adicionar junit pattern se você gerar relatórios específicos para Web Tests
                // junit '**/target/web-test-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner -Dsonar.projectKey=faithwords-library-backend -Dsonar.sources=src"
                }
            }
        }
    }

    post {
        always {
            // garante que todos os relatórios de teste sejam publicados
            junit '**/target/surefire-reports/*.xml'
            junit '**/target/failsafe-reports/*.xml'
            // junit '**/target/web-test-reports/*.xml' // se houver
        }
        // failure {
        //     mail to: 'time@empresa.com',
        //          subject: "Falha no build ${env.BUILD_NUMBER}",
        //          body: "Verificar os logs do Jenkins: ${env.BUILD_URL}"
        // }
    }
}

pipeline {
    agent any

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
        stage('SonarQube Analysis') {
            steps {
                sh """
                    mvn sonar:sonar \
                        -Dsonar.projectKey=faithwords-library-backend \
                        -Dsonar.host.url=$SONAR_HOST_URL \
                        -Dsonar.login=$SONAR_TOKEN
                """
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            junit 'target/failsafe-reports/*.xml'
        }
        failure {
            mail to: 'time@empresa.com',
                 subject: "Falha no build ${env.BUILD_NUMBER}",
                 body: "Verificar os logs do Jenkins: ${env.BUILD_URL}"
        }
    }
}

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
                 sh 'mvn -B -DskipTests=false test'
             }
        }
        stage('Testes de Integração') {
            steps {
                sh 'mvn verify -Pintegration'
            }
        }

        stage('Testes Web (UI)') {
            steps {
                sh 'mvn verify -Pweb'
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

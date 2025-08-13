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
    }
}

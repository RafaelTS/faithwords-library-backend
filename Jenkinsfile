pipeline {
    agent any

    tools {
        maven 'Maven_3.8.6' // Nome configurado no Jenkins
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
    }
}

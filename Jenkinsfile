pipeline {
  agent any

  tools {
    maven 'Maven_3.8.6'  // Configure no Jenkins sua instalação do Maven com este nome
    jdk 'JDK_17'         // Configure JDK 17 no Jenkins com esse nome
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

    stage('Test') {
      steps {
        sh 'mvn test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Archive') {
      steps {
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }
  }

  post {
    success {
      echo 'Build and tests succeeded!'
    }
    failure {
      echo 'Build or tests failed.'
    }
  }
}

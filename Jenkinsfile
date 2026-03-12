pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn test -Dsurefire.suiteXmlFiles=testng-api.xml'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {

        always {
            echo 'Pipeline finished'
        }

        success {
            echo 'All API tests passed'
        }

        failure {
            echo 'Some tests failed'
        }
    }
}

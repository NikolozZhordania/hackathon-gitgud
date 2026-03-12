pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=testng-api.xml'
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                reportDir: 'target/allure-results',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
        }
        success {
            echo 'All API tests passed'
        }
        failure {
            echo 'Some API tests failed'
        }
    }
}

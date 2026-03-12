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
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng-api.xml'
            }
        }
    }

    post {
        always {
            echo 'Tests completed'
        }
        success {
            echo 'All API tests passed'
        }
        failure {
            echo 'Some API tests failed'
        }
    }
}

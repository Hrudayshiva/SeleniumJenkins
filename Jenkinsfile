pipeline {
    agent any

    stages {
	    stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Build app'
            }
        }
        stage('Test') {
            steps {
                echo 'Test app'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy app'
            }
        }
    }
	post {
		success {
			emailext body: '', subject: 'Pipeline success', to: 'hrudayshiva97@gmail.com'
		}
		failure {
			emailext body: '', subject: 'Pipeline failure', to: 'hrudayshiva97@gmail.com'
		}
	
	}
}

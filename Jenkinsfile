pipeline {
    agent any

    tools {
        maven "Maven"
    }

    stages {
        stage("Clean target folder") {
            steps {
                bat "mvn clean"
            }
        }

        stage("Run tests") {
            steps {
                bat "mvn test -Dbrowser=chrome"
            }
        }
    }

  post {
    always {
        mail to: 'slimshadyy62@gmail.com',
             subject: "Simpler Test from Jenkins",
             body: "This is a test email from Jenkins"
    }
}
}

pipeline {
    agent any

    stages {
        stage("Clean target folder") {
            steps {
                bat "C://ProgramData//chocolatey//lib//maven//apache-maven-3.9.9//bin//mvn clean"
            }
        }

        stage("Run tests") {
            steps {
                bat "C://ProgramData//chocolatey//lib//maven//apache-maven-3.9.9//bin//mvn test -Dbrowser=chrome"
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

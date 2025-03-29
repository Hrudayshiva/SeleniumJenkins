pipeline {
    agent any

    stages {
        stage("Navigate to repo") {
            steps {
                bat "cd /d D:IJ//SeleniumTestNGPractice//SeTnPractice"
            }
        }
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
            mail to: 'slimshadyy62@gmail.com'
            subject: "Execution status of ${env.$PROJECT_NAME}"
            body: """

                This is the build status
                ${BUILD_NUMBER} >> ${env.$BUILD_STATUS}

                """
        }
    }
}

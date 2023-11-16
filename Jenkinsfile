pipeline{
    agent any

    stages{


        stage('Cloning from GitHub') {
                steps {
                    git branch: 'master', url: 'https://github.com/marwamemmi/devopsspringproject.git'
                }

            }

      stage('Clean'){
            steps {
                sh 'mvn clean '
            }

        }
        stage('Compile'){
            steps {
                sh 'mvn compile '
            }

        }


 stage('Collect JaCoCo Coverage') {
             steps{
                    jacoco(execPattern: '**/target/jacoco.exec')
     }
         }

     stage('JUNIT TEST with JaCoCo') {
       steps {
         sh 'mvn test jacoco:report'
         echo 'Test stage done'
       }
     }
 stage('SonarQube Analysis') {

   steps {
     withSonarQubeEnv('sonar-scanner') {
       sh 'mvn sonar:sonar'
     }
   }
 }


 stage("Nexus Deploy Stage") {
     steps {
             sh 'mvn deploy -DskipTests'

     }
 }




        stage('Build Docker Image') {
            steps {
                sh 'docker build -t marwamemmi/alpine:1.0.0  .'
            }
        }

        
     stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }

}
post {
            always {
                emailext(
                    subject: "Pipeline Status: ${currentBuild.currentResult}",
                    body: """<html>
                            <body>
                                <p>Build Status: ${currentBuild.currentResult}</p>
                                <p>Build Number: ${currentBuild.number}</p>
                                <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
                            </body>
                            </html>""",
                    to: 'memmimarwa47@gmail.com',
                    from: 'jenkins@example.com',
                    replyTo: 'jenkins@example.com',
                    mimeType: 'text/html'
                )
            }
        }
}

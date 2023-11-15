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


     stage('Archive Artifacts') {
            steps {
                archiveArtifacts(artifacts: 'target/*.jar', allowEmptyArchive: true)
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

 stage("Nexus Deploy Stage") {
     steps {
         withMaven(maven: 'mvn') {
             sh 'mvn deploy -DskipTests'
         }
     }
 }
        
}
}

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
        

 stage('SonarQube Analysis') {
     steps {
         sh 'mvn sonar:sonar -Dsonar.language=java -Dsonar.login=admin -Dsonar.password=sonar'
     }
 }
   stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('UNIT test'){
            steps{
                sh 'mvn test'
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

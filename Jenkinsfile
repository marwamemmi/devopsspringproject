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
        
        
        stage('UNIT test'){
            steps{
                sh 'mvn test'
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

        stage('Publish to Nexus') {
            steps {
                withMaven(mavenSettingsConfig: '5d2b2f5d69be') {
                    sh 'mvn deploy'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t marwamemmi/alpine:1.0.0  .'
            }
        }

    }
}

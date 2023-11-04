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


    }
}

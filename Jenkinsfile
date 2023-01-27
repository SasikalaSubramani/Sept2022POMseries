pipeline {
    
    
    agent any
    
<<<<<<< HEAD
    stages{
=======
    tools{
    	maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
                   
>>>>>>> 005d6f4254be305fcef9e7431d2b2629e245412e
        
        stage("build"){
            steps{
                echo("build the project")
            }
        }
        
        stage("deploy to dev"){
            steps{
                echo("deploy to dev")
            }
        }
        
        stage("RUN UTs"){
            steps{
                echo("run unit tests")
            }
        }
        stage("deploy to QA"){
            steps{
                echo("deploy to QA")
            }
        }
        
        stage("RUN Automation tests"){
            steps{
                echo("run automation tests")
            }
        }
        stage("deploy to stage"){
            steps{
                echo("deploy to stage")
            }
        }
        
        stage("RUN Sanity tests"){
            steps{
                echo("run sanity tests")
            }
        }
        
         stage("deploy to PROD"){
            steps{
                echo("deploy to prod")
            }
        }
        
        
    }
<<<<<<< HEAD
    
    
    
    
}
=======
}
>>>>>>> 005d6f4254be305fcef9e7431d2b2629e245412e

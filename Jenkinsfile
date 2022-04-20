@Library('ceiba-jenkins-library@master') _
pipeline{
	
    agent {
        label 'Slave_Induccion'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK11_Centos'
    }

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout scm
            }
        }

        stage('Compilacion y Test Unitarios'){
           
            steps{
                
                echo "------------>Clean Tests<------------"

                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle clean'
                
                echo "------------>compile & Unit Tests<------------"
                
                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle test'
            }
            
        }
		
		stage('Static Code Analysis') {
			steps{
                echo '------------>Análisis de código estático<------------'

				sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:hostal.henry.mosquera',
                sonarName:'CeibaADN-Hostal(henry.mosquera)',
                sonarPathProperties:'./sonar-project.properties')
			}
		}

        stage('Build'){
            steps{
                echo "------------>Build<------------"
                sh './microservicio/gradlew --b ./microservicio/build.gradle build -x test'
            }
         }
    }

    post {
        always {
            echo 'This will always run'
        }
        failure {
            echo 'This will run only if failed'
            mail(
                to: 'henry.mosquera@ceiba.com.co',
                body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}"
            )
            //updateGitlabCommitStatus name: 'IC Jenkins', state: 'failed'
        }
        success {
            echo 'This will run only if successful'
            junit 'build/test-results/test/*.xml'
            //updateGitlabCommitStatus name: 'IC Jenkins', state: 'success'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}

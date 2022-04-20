@Library('ceiba-jenkins-library@master') _
pipeline{
	// any -> tomaria slave 5 u 8
	// Para mobile se debe especificar el slave -> {label 'Slave_Mac'}
	// Para proyectos de arus se debe tomar el slave 6 o 7 -> {label 'Slave6'} o {label 'Slave7'}

    agent {
        label 'Slave_Induccion'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
        //gitLabConnection('GitCeiba')
    }

    /*environment {
        PROJECT_PATH_BACK = 'microservicio'
    }*/


    tools {
        jdk 'JDK11_Centos'
    }

    // Parametros disponibles en jenkins
     /*parameters{
            string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
            booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
            choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
            password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a passwor')
     }*/

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout scm
                
                /*dir("${PROJECT_PATH_BACK}"){
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean'
                }*/
            }
        }

        stage('Compilacion y Test Unitarios'){
           
            steps{
                echo "------------>compile & Unit Tests<------------"
                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle test'
            }
            
        }
		
		stage('Static Code Analysis') {
			steps{
                echo '------------>Análisis de código estático<------------'

				/*sonarqubeMasQualityGates(sonarKey:'co.com.ceiba.adn:hostal.henry.mosquera', 
				sonarName:'CeibaADN-Hostal(henry.mosquera)', 
				sonarPathProperties:'./sonar-project.properties')*/
                withSonarQubeEnv(​'Sonar'​){
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }
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

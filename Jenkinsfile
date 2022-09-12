pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube analysis') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'sonarqube', passwordVariable: 'sonarqubeToken', usernameVariable: 'sonarqubeUrl')]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=${sonarqubeUrl} -Dsonar.login=${sonarqubeToken}'
                }
            }
        }

        stage('Docker build') {
            steps {
                sh 'docker build --tag library-ms-members:1.0.2 .'
                sh 'docker tag library-ms-members:1.0.2 lkamal/library-ms-members:1.0.2'
            }
        }

        stage('Docker push') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerpwd')]) {
                    sh 'echo ${dockerpwd} | docker login -u lkamal --password-stdin'
                    sh 'docker push lkamal/library-ms-members:1.0.2'
                }
            }
        }

        stage('Deploy to EKS with Helm') {
            steps {
                sh 'sudo helm upgrade library-ms-members helm/library-ms-members/.'
            }
        }
    }
}
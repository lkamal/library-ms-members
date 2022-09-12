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
                sh 'mvn sonar:sonar -Dsonar.host.url=http://3.230.2.128:9000 -Dsonar.login=87e3d858a81dc7c4435a44a487db6374494d0a08'
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
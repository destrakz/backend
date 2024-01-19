pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven3"
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('credentials-docker')
        DOCKERHUB_REGISTRY = 'seifkhadraoui/backend'
        SCANNER_HOME=tool 'sonar-scanner'
        NEXUS_VERSION = 'nexus3'
        NEXUS_PROTOCOL = 'http'
        NEXUS_URL = 'nexus:8081'
        NEXUS_REPOSITORY = 'backend-nexus-repo'
        NEXUS_CREDENTIAL_ID = 'nexus-user-credential'
        NEXUS_CREDENTIALS = credentials("${NEXUS_CREDENTIAL_ID}")
        ARTIFACT_VERSION = '0.0.1-SNAPSHOT'
        ARTIFACT_ID = 'flower-meadow-generator'
        GROUP_ID = 'io.github.raboro'
        CLASSIFIER = ''
        EXTENSION = 'jar'
    }

    stages {
        stage('Git Clone Project') {
            steps {
                git branch: 'main', url: 'https://github.com/destrakz/backend.git'
            }
        }

        stage('Compile Code') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube Analysis And Scan') {
            steps() {
                sh "echo hello from sonar"
                /*withSonarQubeEnv('sonarqube-server') {
                    //sh "mvn sonar:sonar"
                    sh '${SCANNER_HOME}/bin/sonar-scanner --version'
                }*/
            }
        }

        stage('Build Package Artifact') {
            steps() {
                sh "mvn clean package -DskipTests=true"
                archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
            }
        }

        stage('Publish Artifact to Nexus') {
            steps() {
                nexusArtifactUploader artifacts:
                [
                    [
                        artifactId: ARTIFACT_ID,
                        classifier: '',
                        file: "target/${ARTIFACT_ID}-${ARTIFACT_VERSION}.jar",
                        type: 'jar'
                    ]
                ],
                nexusVersion: NEXUS_VERSION,
                protocol: NEXUS_PROTOCOL,
                nexusUrl: NEXUS_URL,
                groupId: GROUP_ID,
                version: ARTIFACT_VERSION,
                repository: NEXUS_REPOSITORY,
                credentialsId: NEXUS_CREDENTIAL_ID
            }
        }



        stage('Build & Push Docker Image') {
            steps() {
                script() {
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    sh 'docker build -t $DOCKERHUB_REGISTRY:latest .'
                    sh 'docker push $DOCKERHUB_REGISTRY:latest'
                }
            }
        }

        stage('Deployment') {
            steps() {
                script() {
                    sh "docker compose -p 'devops' down || echo 'project devops not running'"
                    sh "docker compose -p 'devops' up -d --build"
                }
            }
        }
    }
}

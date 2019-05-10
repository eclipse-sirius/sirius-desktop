pipeline {
    agent any

    def mvnHome
    
    stages {
        stage('Prepare') {
            steps {
                git 'http://git.eclipse.org/gitroot/sirius/org.eclipse.sirius.git'
                mvnHome = tool 'apache-maven-latest'
            }
        }
        stage('Build') {
            steps {
                sh "'${mvnHome}/bin/mvn'-Dplatform-version-name='${env.PLATFORM}' -f packaging/org.eclipse.sirius.parent/pom.xml -P full,headless,headless-server clean package"
            }
        }
        /*
        stage('Publish') {
            steps {
                sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
                    sh 'releng/org.eclipse.sirius.releng/publish-nightly-jiro.sh'
                }
            }
        }
        */
    }
}

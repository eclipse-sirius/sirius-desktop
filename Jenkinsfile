pipeline {
    agent any

    stages {
        def mvnHome
        def javaHome
        
        stage('Prepare') {
            steps {
                cleanWs()
                mvnHome = tool 'apache-maven-latest'
                javaHome = tool 'oracle-jdk8-latest'
            }
        }
        stage('Build') {
            steps {
                sh "JAVA_HOME=${javaHome} ${mvnHome}/bin/mvn-Dplatform-version-name=${env.PLATFORM} -f packaging/org.eclipse.sirius.parent/pom.xml -P full,headless,headless-server clean package"
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

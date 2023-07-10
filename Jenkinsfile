pipeline {
    agent {
        label 'centos-latest'
    }

	tools {
		maven 'apache-maven-latest'
		jdk 'openjdk-jdk17-latest'
	}

    stages {
        stage('Build') {
            steps {
                script {
                    sh "xvfb-run mvn -B -Dplatform-version-name=2023-03 -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full clean package"
                }
            }
        }
    }
}

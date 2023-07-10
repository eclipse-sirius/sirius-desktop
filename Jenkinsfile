pipeline {
    agent {
        label 'migration'
    }

	tools {
		maven 'apache-maven-latest'
		jdk 'openjdk-jdk14-latest'
	}

    stages {
        stage('Build') {
            steps {
                script {
                    sh "xvfb-run mvn -B -Dplatform-version-name=2020-03 -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full,headless-server clean package"
                }
            }
        }
    }
}

pipeline {
    agent {
	kubernetes {
	    label 'sirius-buildtest'
	    defaultContainer 'environment'
	    yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: environment
    image: eclipsecbi/ubuntu-gtk3-metacity
    tty: true
    command: [ "uid_entrypoint", "cat" ]
    resources:
      requests:
        memory: "2.6Gi"
        cpu: "1.3"
      limits:
        memory: "2.6Gi"
        cpu: "1.3"
  - name: jnlp
    image: 'eclipsecbi/jenkins-jnlp-agent'
    volumeMounts:
    - mountPath: /home/jenkins/.ssh
      name: volume-known-hosts
  volumes:
  - configMap:
      name: known-hosts
    name: volume-known-hosts
"""
	}
    }

    stages {
        stage('Prepare') {
            steps {
                git 'http://git.eclipse.org/gitroot/sirius/org.eclipse.sirius.git'
            }
        }
        stage('Build') {
            steps {
                sh "JAVA_HOME=/opt/tools/java/oracle/jdk-8/latest /opt/tools/apache-maven/latest/bin/mvn -B -Dplatform-version-name=${env.PLATFORM} -f packaging/org.eclipse.sirius.parent/pom.xml -P full,headless,headless-server clean package"
            }
        }
        stage('Test') {
            steps {
                wrap([$class: 'Xvnc', useXauthority: true]) {
                    sh "JAVA_HOME=/opt/tools/java/oracle/jdk-8/latest /opt/tools/apache-maven/latest/bin/mvn -B -Dplatform-version-name=${env.PLATFORM} -f packaging/org.eclipse.sirius.parent/pom.xml -P full,headless,headless-server,gerrit-junit verify"
                }
            }
            // post {
            //     always {
            //         archiveArtifacts artifacts: 'plugins/***/target/work/configuration/*.log,plugins/**/target/work/data/.metadata/.log*', fingerprint: false
            //         junit 'plugins/**/target/surefire-reports/TEST-*.xml'
            //     }
            // }
        }
        stage('Publish') {
            steps {
                sshagent(['projects-storage.eclipse.org-bot-ssh']) {
                    sh 'releng/org.eclipse.sirius.releng/publish-nightly-jiro.sh'
                }
            }
        }
    }
}

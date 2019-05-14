pipeline {
    agent {
	kubernetes {
	    label 'sirius-buildtest'
	    defaultContainer 'jnlp'
	    yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: uitests
    image: mickaelistria/eclipse-acute-build-test-env:test
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
    resources:
      requests:
        memory: "2.6Gi"
        cpu: "1.3"
      limits:
        memory: "2.6Gi"
        cpu: "1.3"
    volumeMounts:
    - name: volume-known-hosts
      mountPath: /home/jenkins/.ssh
    - name: tools
      mountPath: /opt/tools
  volumes:
  - name: volume-known-hosts
    configMap:
      name: known-hosts
  - name: tools
    persistentVolumeClaim:
      claimName: tools-claim-jiro-sirius
"""
	}
    }

    stages {
        stage('Prepare') {
            steps {
                container('jnlp') {
                    git 'http://git.eclipse.org/gitroot/sirius/org.eclipse.sirius.git'
                }
            }
        }
        stage('Build') {
            steps {
                container('jnlp') {
                    sh "JAVA_HOME=/opt/tools/java/oracle/jdk-8/latest /opt/tools/apache-maven/latest/bin/mvn -B -Dplatform-version-name=${env.PLATFORM} -f packaging/org.eclipse.sirius.parent/pom.xml -P full,headless,headless-server clean package"
                }
            }
        }
        stage('Test') {
            steps {
                container('uitests') {
                    wrap([$class: 'Xvnc', useXauthority: true]) {
                        sh "mvn -B -Dplatform-version-name=${env.PLATFORM} -f packaging/org.eclipse.sirius.parent/pom.xml -P full,headless,headless-server,gerrit-junit verify"
                    }
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
                container('jnlp') {
                    sshagent(['projects-storage.eclipse.org-bot-ssh']) {
                        sh 'releng/org.eclipse.sirius.releng/publish-nightly-jiro.sh'
                    }
                }
            }
        }
    }
}

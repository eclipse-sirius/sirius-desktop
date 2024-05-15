pipeline {
    agent {
        label 'centos-latest'
    }

	tools {
		maven 'apache-maven-latest'
		jdk 'openjdk-jdk17-latest'
	}

    options {
      timestamps ()
    }

    stages {
        stage('Check Resource Lock') {
            steps {
                script {
                    def isLockedByOther = true
                    def resourceName = 'sirius-desktop-tests'
                    lock(resource: resourceName, skipIfLocked : true) {
                        isLockedByOther = false
                    }

                    if (isLockedByOther) {
                        error "ABORTING because the ressource '${resourceName}' is locked."
                    }
                }
            }
        }
        stage('Parallel Tests') {
            when {
                not {
                    branch 'master'
                }
            }
            parallel {
                stage('JUnit') {
                    agent {
                        label 'migration'
                    }
                    steps {
                        timeout(time: 2, unit: 'HOURS') {
                            script {
                                wrap([$class: 'Xvnc', takeScreenshot: true, useXauthority: true]) {
                                    sh "mvn -B -Dplatform-version-name=2023-03 -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full,gerrit-junit integration-test"
                                }
                            }
                        }
                    }
                    post {
                        always {
                            junit(
                                allowEmptyResults: true,
                                testResults: '**/target/surefire-reports/*.xml'
                            )
                            archiveArtifacts artifacts: 'plugins/*.test*/**/screenshots/*.jpeg,plugins/*.test*/target/work/data/.metadata/*log,plugins/org.eclipse.sirius.tests*/org_eclipse_sirius_tests_*.txt', allowEmptyArchive: true
                        }
                    }
                }
                stage('SWTbot') {
                    agent {
                        label 'migration'
                    }
                    steps {
                        timeout(time: 2, unit: 'HOURS') {
                            script {
                                wrap([$class: 'Xvnc', takeScreenshot: true, useXauthority: true]) {
                                    sh '''
                                    rm -rf "$WORKSPACE/plugins/org.eclipse.sirius.tests.swtbot/screenshots"
                                    xrandr -s 1920x1200
                                    xsetroot -solid grey
                                    vncconfig -iconic &
                                    xhost +
                                    sleep 2
                                    metacity --replace --sm-disable --display=${DISPLAY} &
                                    sleep 2
                                    rm -f plugins/org.eclipse.sirius.tests*/org_eclipse_sirius_tests_*.txt

                                    mvn -B -Dplatform-version-name=2023-03 -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full,gerrit-swtbot integration-test
                                    '''
                                }
                            }
                        }
                    }
                    post {
                        always {
                            junit(
                                allowEmptyResults: true,
                                testResults: '**/target/surefire-reports/*.xml'
                            )
                            archiveArtifacts artifacts: 'plugins/*.test*/**/screenshots/*.jpeg,plugins/*.test*/target/work/data/.metadata/*log,plugins/org.eclipse.sirius.tests*/org_eclipse_sirius_tests_*.txt', allowEmptyArchive: true
                        }
                    }
                }
                stage('SWTbot Sequence') {
                    agent {
                        label 'migration'
                    }
                    steps {
                        timeout(time: 2, unit: 'HOURS') {
                            script {
                                wrap([$class: 'Xvnc', takeScreenshot: true, useXauthority: true]) {
                                    sh '''
                                    rm -rf "$WORKSPACE/plugins/org.eclipse.sirius.tests.swtbot/screenshots"
                                    xrandr -s 1920x1200
                                    xsetroot -solid grey
                                    vncconfig -iconic &
                                    xhost +
                                    sleep 2
                                    metacity --replace --sm-disable --display=${DISPLAY} &
                                    sleep 2
                                    rm -f plugins/org.eclipse.sirius.tests*/org_eclipse_sirius_tests_*.txt

                                    mvn -B -Dplatform-version-name=2023-03 -f packaging/org.eclipse.sirius.parent/pom.xml -P headless,full,gerrit-swtbot-sequence integration-test
                                    '''
                                }
                            }
                        }
                    }
                    post {
                        always {
                            junit(
                                allowEmptyResults: true,
                                testResults: '**/target/surefire-reports/*.xml'
                            )
                            archiveArtifacts artifacts: 'plugins/*.test*/**/screenshots/*.jpeg,plugins/*.test*/target/work/data/.metadata/*log,plugins/org.eclipse.sirius.tests*/org_eclipse_sirius_tests_*.txt', allowEmptyArchive: true
                        }
                    }
                }
            }
        }
    }
}

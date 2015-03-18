#!/bin/sh
# ====================================================================
# Copyright (c) 2015 Obeo
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================


# Make sure the Xvnc server is completely started by looking for its output in the current job's console
wait_for_xvnc() {
    local readonly message="Listening for VNC connections on TCP port"
    local readonly logFileURL="https://hudson.eclipse.org/sirius/job/sirius.gerrit/$BUILD_NUMBER/PLATFORM=$PLATFORM,SUITE=$SUITE/consoleText"

    sleep 5

    local attempt=1
    while ! ( curl -s "$logFileURL" | grep -q " $message " ); do
        sleep 5
        if [ "$attempt" -gt "4" ]; then
            echo "Xvnc not ready; abandonning."
            exit 1
        else
            attempt=$(( attempt + 1 ))
        fi
    done
}

# Make sure we have a properly configured window manager for SWTbot tests
start_window_manager() {
    /usr/bin/metacity --display="$DISPLAY" --replace --sm-disable &
    export WM_PID="$!"
    sleep 1
    /usr/bin/metacity-message disable-keybindings
}

kill_window_manager() {
    if ps --pid "$WM_PID" -o pid,args | grep -q metacity ; then
        kill "$WM_PID"
    fi
}

# Create a well-formed but empty test report so that tests result
# publication does not fail even in the matrix cells where we do not
# actually run any tests.
create_dummy_test_report() {
    local readonly REPORT_DIR="$WORKSPACE/plugins/org.eclipse.sirius.tests.junit/target/surefire-reports"
    local readonly REPORT='<?xml version="1.0" encoding="UTF-8"?><testrun name="Sirius Dummy Test Suite" project="org.eclipse.sirius.tests.unit" tests="1" started="0" failures="0" errors="0" ignored="1"><testsuite name="Sirius Dummy Tests" time="0.001"><testcase name="testNothingJustToMakeHudsonHappy" classname="org.eclipse.sirius.tests.unit.DummyTest" time="0.01"/></testsuite></testrun>'
    mkdir -p "$REPORT_DIR"
    echo  "$REPORT" > "$REPORT_DIR/empty-test-results.xml"
}

# Adjust the target platform used to build/execute the tests to consume the local version of the core we just built
adjust_tests_target_platform() {
    if [ "$GERRIT_BRANCH" = "master" ]; then
        export STREAM="latest"
    else
        export STREAM=$(echo "$GERRIT_BRANCH" | sed -e 's/v//')
    fi
    sed -i -e "s!http://download.eclipse.org/sirius/updates/nightly/$STREAM/$PLATFORM!file://$WORKSPACE/packaging/org.eclipse.sirius.update/target/repository!" "releng/org.eclipse.sirius.targets/sirius_tests_$PLATFORM.target"
}

invoke_maven() {
    /shared/common/apache-maven-latest/bin/mvn -V -X -B -DBUILD_SIGN=false -Dmaven.repo.local="$WORKSPACE/.maven/repo" -DPLATFORM="$PLATFORM" -Dplatform-version-name="$PLATFORM" "$@"
}

remove_cached_sirius_bundles() {
    find "$WORKSPACE/.maven/repo/p2/osgi/bundle" -type d -name "org.eclipse.sirius.*" -print0 | xargs -0 rm -rf
}

readonly REFERENCE_PLATFORM="luna"
if [ "$PLATFORM" = "$REFERENCE_PLATFORM" -o "$SUITE" = "gerrit-junit" ]; then
    remove_cached_sirius_bundles
    # Build Sirius core
    invoke_maven -f packaging/org.eclipse.sirius.parent/pom.xml clean package
    export BUILD_RESULT="$?"
    echo "BUILD_RESULT=$BUILD_RESULT"

    # Build the tests, and run them on the reference platform
    if [ "$GERRIT_BRANCH" = "master" -a "$PLATFORM" = "$REFERENCE_PLATFORM" ]; then
        # Build and run Sirius tests
        wait_for_xvnc
        start_window_manager
        adjust_tests_target_platform
        if [ "$PLATFORM" = "luna" ]; then
            # Gtk3 support under Luna can lead to crashes (looks like resource leaks inside the X server),
            # so for the use of Gtk2 in this case.
            export SWT_GTK3=0
        fi
        invoke_maven -f packaging/org.eclipse.sirius.tests.parent/pom.xml -P"$SUITE" clean integration-test
        readonly TESTS_RESULT="$?"
        echo "TESTS_RESULT=$TESTS_RESULT"
        kill_window_manager
    else
        # Build Sirius tests but do not execute them
        adjust_tests_target_platform
        invoke_maven -f packaging/org.eclipse.sirius.tests.parent/pom.xml clean package
        readonly TESTS_RESULT="$?"
        echo "TESTS_RESULT=$TESTS_RESULT"
        create_dummy_test_report
    fi
else
    export BUILD_RESULT="0"
    export TESTS_RESULT="0"
    create_dummy_test_report
fi

# if [[ "$BUILD_RESULT" = 0 && "$TESTS_RESULT" = 0 ]]; then
#   exit 0
# else
echo "BUILD_RESULT=$BUILD_RESULT"
echo "TESTS_RESULT=$TESTS_RESULT"
#   exit 1
# fi

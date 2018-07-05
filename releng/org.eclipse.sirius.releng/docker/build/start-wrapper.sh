#!/bin/sh

BUILD_POM="git/packaging/org.eclipse.sirius.parent/pom.xml"

# Compute JAVA_HOME and export it. There is no way to do it inside docker : the ENV command doesn't accept expressions
export JAVA_HOME=$(readlink -f "$(dirname "$(readlink -f "$(which java)")")/../..")

# Start VNC and X server
.vnc/xstartup.sh

FAE=""
if [ $FAIL_AT_END ];
then
    FAE=" -fae"
fi

MAVEN_COMMAND="SWT_GTK3=1 mvn -Dplatform-version-name="$PLATFORM" -Dbuild-folder-name="$BUILD_FOLDER" -Declipse.p2.mirrors="$USE_P2_MIRRORS" -f "$BUILD_POM" -Pfull,headless,"$TESTS_SUITES$FAE" clean "$GOAL

echo ""
echo ""
echo "####  Executed maven command  ####"
echo $MAVEN_COMMAND
echo ""
echo ""

eval $MAVEN_COMMAND

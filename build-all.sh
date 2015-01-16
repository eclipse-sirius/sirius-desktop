#!/bin/sh

set -e

readonly PLATFORM=${1:-luna}
readonly TESTS_GOAL=${2:-package}
readonly TESTS_SUITES=${3:-junit,swtbot-sequence,swtbot}
readonly BASEDIR=$(pwd)
readonly BUILD_POM="packaging/org.eclipse.sirius.parent/pom.xml"
readonly TESTS_POM="packaging/org.eclipse.sirius.tests.parent/pom.xml"
readonly TESTS_SETTINGS="packaging/org.eclipse.sirius.tests.parent/sirius-local-settings.xml"

mvn -Dplatform-version-name="$PLATFORM" -f "$BUILD_POM" clean package
sed -i -e "s!@BASEDIR@!$BASEDIR!" "$TESTS_SETTINGS"

    export SWT_GTK3=0
mvn -Dplatform-version-name="$PLATFORM" -s "$TESTS_SETTINGS" -f "$TESTS_POM" -P"$TESTS_SUITES" clean "$TESTS_GOAL"

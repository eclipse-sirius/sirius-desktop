#!/bin/sh

set -e

readonly PLATFORM=${1:-luna}
readonly TESTS_GOAL=${2:-package}
readonly BASEDIR=$(pwd)
readonly BUILD_POM="packaging/org.eclipse.sirius.parent/pom.xml"
readonly TESTS_POM="packaging/org.eclipse.sirius.tests.parent/pom.xml"
readonly TESTS_SETTINGS="packaging/org.eclipse.sirius.tests.parent/sirius-local-settings.xml"

mvn -Dplatform-version-name="$PLATFORM" -f "$BUILD_POM" clean package
sed -i -e "s!@BASEDIR@!$BASEDIR!" "$TESTS_SETTINGS"
mvn -Dplatform-version-name="$PLATFORM" -s "$TESTS_SETTINGS" -f "$TESTS_POM" clean "$TESTS_GOAL"

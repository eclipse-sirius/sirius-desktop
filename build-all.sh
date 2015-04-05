#!/bin/sh

set -e

readonly PLATFORM=${1:-luna}
readonly TESTS_GOAL=${2:-package}
readonly TESTS_SUITES=${3:-junit,swtbot-sequence,swtbot}
readonly BASEDIR=$(pwd)
readonly BUILD_POM="packaging/org.eclipse.sirius.parent/pom.xml"
readonly TESTS_POM="packaging/org.eclipse.sirius.tests.parent/pom.xml"
readonly TESTS_SETTINGS="packaging/org.eclipse.sirius.tests.parent/sirius-local-settings.xml"

# Build Sirius core using the normal Target Platform definition
mvn -Dplatform-version-name="$PLATFORM" -Declipse.p2.mirrors=false -f "$BUILD_POM" clean package

# Build (and optionally execute) the Sirius test suites, using an
# adjusted Target Platform which uses the locally build Sirius core
# instead of the latest published nightly.
readonly ORIGINAL_SETTINGS=$(mktemp)
cp "$TESTS_SETTINGS" "$ORIGINAL_SETTINGS"
sed -i -e "s!@BASEDIR@!$BASEDIR!" "$TESTS_SETTINGS"
SWT_GTK3=0 mvn -Dplatform-version-name="$PLATFORM" -Declipse.p2.mirrors=false -s "$TESTS_SETTINGS" -f "$TESTS_POM" -P"$TESTS_SUITES" clean "$TESTS_GOAL"
mv "$ORIGINAL_SETTINGS" "$TESTS_SETTINGS"

#!/bin/sh

set -e

readonly PLATFORM=${1:-mars}
readonly TESTS_GOAL=${2:-package}
readonly TESTS_SUITES=${3:-junit,swtbot-sequence,swtbot}
readonly BASEDIR=$(pwd)
readonly BUILD_POM="packaging/org.eclipse.sirius.parent/pom.xml"
readonly TESTS_POM="packaging/org.eclipse.sirius.tests.parent/pom.xml"
readonly TESTS_SETTINGS="packaging/org.eclipse.sirius.tests.parent/sirius-local-settings.xml"

# Build Sirius core using the normal Target Platform definition
mvn -Dplatform-version-name="$PLATFORM" -f "$BUILD_POM" clean package

# Build (and optionally execute) the Sirius test suites, using an
# adjusted Target Platform which uses the locally build Sirius core
# instead of the latest published nightly.
readonly ORIGINAL_SETTINGS=$(mktemp)
cp "$TESTS_SETTINGS" "$ORIGINAL_SETTINGS"
sed -i -e "s|@BASEDIR@|$BASEDIR|" "$TESTS_SETTINGS"
if [ "$OSTYPE" = "msys" ]; then
    # for windows, replace file:///c/xxx by  file:///c:/xxx
    sed -r -i -e "s|file:///([A-Za-z]{1})/|file:///\1:/|" "$TESTS_SETTINGS"
fi

SWT_GTK3=0 mvn -Dplatform-version-name="$PLATFORM" -s "$TESTS_SETTINGS" -f "$TESTS_POM" -P "$TESTS_SUITES" clean "$TESTS_GOAL"
mv "$ORIGINAL_SETTINGS" "$TESTS_SETTINGS"

#!/bin/sh

set -e

readonly PLATFORM=${1:-2023-03}
readonly GOAL=${2:-package}
readonly TESTS_SUITES=${3:-junit,swtbot-sequence,swtbot}
readonly BUILD_POM="packaging/org.eclipse.sirius.parent/pom.xml"
readonly USE_P2_MIRRORS="true"

mvn -Dplatform-version-name="$PLATFORM" -Declipse.p2.mirrors="$USE_P2_MIRRORS" -f "$BUILD_POM" -P headless,full,"$TESTS_SUITES" clean "$GOAL"

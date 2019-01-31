#!/bin/sh
# ====================================================================
# Copyright (c) 2015 Obeo.
# This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================
info() {
    echo "    [INFO] " "$*"
}

warning() {
    echo "    [WARNING] " "$*"
}

error() {
    echo "    [ERROR] " "$*"
}

check() {
    echo "CHECKING: $1"
}

readonly IGNORE_MAVEN_REPO="-prune -o ( -type d -name '.maven' )"

# The embedded web server used by the Eclipse Help expects HTML files to be parsable XML
check "All published documentation files must be well-formed XML"
find plugins/org.eclipse.sirius.doc/doc -name "*.html" -exec xmllint --noout {} \;
find plugins/org.eclipse.sirius.eef.adapters/doc -name "*.html" -exec xmllint --noout {} \;

check "All *.xml files must be well-formed XML"
find . -name "*.xml" -type f $IGNORE_MAVEN_REPO -exec xmllint --noout {} \;

check "All *.target files must be well-formed XML"
find . -name "*.target" $IGNORE_MAVEN_REPO -exec xmllint --noout {} \;

check "All plug-ins must have an about.html file at their root"
for plugin in plugins/*; do
    if [ -d "$plugin" ]; then
       [ -f "$plugin/about.html" ] || {
           error "Missing about.html in $plugin"
       }
    fi
done

# The about.html should be referenced in build.properties
# All the about.html should be identical
# find . -name "about.html" -exec md5sum {} \; | sort | cut -d' ' -f1 | sort -u

readonly BREE="JavaSE-1.8"
check "All plug-ins have a consistent BREE ($BREE)"
BREES=$(git grep Bundle-RequiredExecutionEnvironment -- '**/MANIFEST.MF' | awk '{print $2}' | sort -u)
[ "$BREES" = "JavaSE-1.8" ] || {
    error "Inconsistent BREE detected: " $(echo "$BREES")
}

check "All plug-ins have 'Bundle-Localization: plugin'"
find plugins -name MANIFEST.MF -path "*/META-INF/*" | while read m; do grep -q "Bundle-Localization: plugin" $m || error "$m"; done

check "All plug-ins must externalize their 'Bundle-Name' and 'Bundle-Vendor'"
find plugins -name MANIFEST.MF -path "*/META-INF/*" | \
    while read m; do
        grep -q "Bundle-Name: %pluginName" "$m" || error "Bundle-Name not externalized for $m"
        grep -q "Bundle-Vendor: %providerName" "$m" || error "Bundle-Vendor not externalized for $m"
        grep -q "Bundle-Localization: plugin" "$m" || error "Bundle-Localization not correctly set for $m"
    done

readonly PROVIDER_NAME="Eclipse Modeling Project"
check "All plug-ins should have the same providerName ($PROVIDER_NAME)"
PROVIDERS=$(find plugins -name "plugin.properties" -exec grep providerName {} \; | sort -u)
[ "$PROVIDERS" = "providerName = $PROVIDER_NAME" ] || {
    error "Inconsistent plug-in providerName detected: " $(echo "$PROVIDERS" | sed -e 's/\s/./g')
}

check "All Java files should have the EPL license header"
# Note that this is a very rough approximation, to be improved later.
# Also, we ignore the paperclips source, which have some problems in
# this area, but as code imported from another project, we can not
# take the responsibility to set/change the copyright attribution
# and/or license.
find plugins -name "*.java" | while read f; do  grep -q "Eclipse Public" $f || echo $f; done | grep -v '/src-paperclips/' | sed -e "s/^/[ERROR] Source file missing EPL header: /"


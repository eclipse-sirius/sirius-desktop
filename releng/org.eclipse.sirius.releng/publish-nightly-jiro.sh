#!/bin/sh
# ====================================================================
# Copyright (c) 2013, 2015, 2019 Obeo
# This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================

[ -z "$WORKSPACE" -o -z "$PLATFORM" -o -z "$GIT_BRANCH" -o -z "$BUILD_TIMESTAMP" ] && {
     echo "Execution aborted.

One or more of the required variables is not set. They are normally
provided by the Jenkins build.

- WORKSPACE       : the build workspace root.
- PLATFORM        : the name of the target Eclipse release (e.g. photon, 2019-03...).
- GIT_BRANCH      : the name fo the Git branch being build/published.
- BUILD_TIMESTAMP : timestamp to use to identify this particular build (e.g. 20180201-113000)
"
     echo "****** CURRENT ENVIRONMENT ******"
     env | sort
     
     exit 1
}

######################################################################
# Setup
######################################################################

# Exit on error
set -e

# The full version (should be taken as an argument)
export VERSION="7.2.1"

# The type of build being published
export BUILD_TYPE="nightly"
export BUILD_TYPE_PREFIX="N"

# The SSH account to use
export SSH_ACCOUNT="genie.sirius@projects-storage.eclipse.org"

# The root folder for all Sirius udpate sites
export SIRIUS_UPDATES_ROOT="/home/data/httpd/download.eclipse.org/sirius/updates"

# Streams are of the form 1.0.x: only keep major and minor version number parts
export STREAM=$(echo "$VERSION" | sed -r -e 's/^([0-9]+\.[0-9]+\.).*$/\1x/')

# The short version, common to all versions in that stream
export SHORT_VERSION=$(echo "$VERSION" | sed -r -e 's/^([0-9]+\.[0-9]+)\..*$/\1/')

# The timestamp in the p2 composite repos used to implement redirects
export P2_TIMESTAMP=$(date +"%s000")

# The full version for this build, e.g. 0.9.0-N20131015-070707
export FULL_VERSION="${VERSION}-${BUILD_TYPE_PREFIX}${BUILD_TIMESTAMP}"

# The root folder where all the builds of the same type as this one
# are published
export TARGET_ROOT="$SIRIUS_UPDATES_ROOT/$BUILD_TYPE"

# The folder for this particular build
export TARGET_DIR="$TARGET_ROOT/$FULL_VERSION/$PLATFORM"

######################################################################
# Publish the build
######################################################################
export WKS="."

# Ensure the target folder exists
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_DIR"
# The actual publication of the p2 repo produced by the build
scp -rp "$WKS"/packaging/org.eclipse.sirius.update/target/repository/* "$SSH_ACCOUNT:$TARGET_DIR"
# Also publish the tests repo right under the main one
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_DIR/tests"
scp -rp "$WKS"/packaging/org.eclipse.sirius.tests.update/target/repository/* "$SSH_ACCOUNT:$TARGET_DIR/tests"
# Publish the target platform definitions used, so that dowstream projects can reference them
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_DIR/targets"
scp -rp "$WKS"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$TARGET_DIR/targets"
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_ROOT/$VERSION/$PLATFORM/targets"
scp -rp "$WKS"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$TARGET_ROOT/$VERSION/$PLATFORM/targets"
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_ROOT/$STREAM/$PLATFORM/targets"
scp -rp "$WKS"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$TARGET_ROOT/$STREAM/$PLATFORM/targets"
# Also update publish targets "$BUILD_TYPE/targets and "$BUILD_TYPE/latest/targets" links if we are building master
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_ROOT/targets"
if [ "origin/master" = "$GIT_BRANCH" ]; then
    ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_ROOT/targets"
    scp -rp "$WKS"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$TARGET_ROOT/targets"
    ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_ROOT/latest/targets"
    scp -rp "$WKS"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$TARGET_ROOT/latest/targets"
fi
# Publish a dump of the build environment, may be useful to debug
env | sort > build_env.txt
scp build_env.txt "$SSH_ACCOUNT:$TARGET_DIR/build_env.txt"
rm build_env.txt

######################################################################
# Setup or update the redirects (implemented as composite repos)
######################################################################

# Create a p2 composite repo to setup a redirect
create_redirect() {
    FROM="$1"
    TO="$2"

    ssh "$SSH_ACCOUNT" mkdir -p  "$FROM"
    cat > compositeArtifacts.xml <<EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeArtifactRepository version='1.0.0'?>
<repository name='Eclipse Sirius' type='org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository' version='1.0.0'>
  <properties size='1'>
    <property name='p2.timestamp' value='$P2_TIMESTAMP'/>
  </properties>
  <children size='1'>
    <child location='https://download.eclipse.org/sirius/updates/$TO'/>
  </children>
</repository>
EOF
    scp compositeArtifacts.xml "$SSH_ACCOUNT:$FROM/compositeArtifacts.xml"

    cat > compositeContent.xml <<EOF
<?xml version='1.0' encoding='UTF-8'?>
<?compositeMetadataRepository version='1.0.0'?>
<repository name='Eclipse Sirius' type='org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository' version='1.0.0'>
  <properties size='1'>
    <property name='p2.timestamp' value='$P2_TIMESTAMP'/>
  </properties>
  <children size='1'>
    <child location='https://download.eclipse.org/sirius/updates/$TO'/>
  </children>
</repository>
EOF
    scp compositeContent.xml "$SSH_ACCOUNT:$FROM/compositeContent.xml"
}

# First, a link for the $VERSION (e.g. "1.2.0/luna" => "1.2.0-NYYYYMMDD-HHMM/luna")
create_redirect "$TARGET_ROOT/$VERSION/$PLATFORM" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM"
create_redirect "$TARGET_ROOT/$VERSION/$PLATFORM/tests" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM/tests"
ssh "$SSH_ACCOUNT" mkdir -p  "$TARGET_ROOT/$VERSION/$PLATFORM/targets"
scp -rp "$WKS"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$TARGET_ROOT/$VERSION/$PLATFORM/targets"
# Also create a link for the $STREAM (e.g. "1.2.x/luna" => "1.2.0-NYYYYMMDD-HHMM/luna")
# and publish the zipped versions there, at stable URLs
create_redirect "$TARGET_ROOT/$STREAM/$PLATFORM" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM"
scp -rp "$WKS"/packaging/org.eclipse.sirius.update/target/org.eclipse.sirius.update-*.zip "$SSH_ACCOUNT:$TARGET_ROOT/$STREAM/org.eclipse.sirius-$VERSION-$PLATFORM.zip"
create_redirect "$TARGET_ROOT/$STREAM/$PLATFORM/tests" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM/tests"
scp -rp "$WKS"/packaging/org.eclipse.sirius.tests.update/target/org.eclipse.sirius.tests.update-*.zip "$SSH_ACCOUNT:$TARGET_ROOT/$STREAM/org.eclipse.sirius.tests-$VERSION-$PLATFORM.zip"
# Also update the global "latest" links if we are building master
if [ "origin/master" = "$GIT_BRANCH" ]; then
    create_redirect "$TARGET_ROOT/latest/$PLATFORM" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM"
    scp -rp "$WKS"/packaging/org.eclipse.sirius.update/target/org.eclipse.sirius.update-*.zip "$SSH_ACCOUNT:$TARGET_ROOT/$STREAM/org.eclipse.sirius-$VERSION-$PLATFORM.zip"
    create_redirect "$TARGET_ROOT/latest/$PLATFORM/tests" "$BUILD_TYPE/$FULL_VERSION/$PLATFORM/tests"
    scp -rp "$WKS"/packaging/org.eclipse.sirius.tests.update/target/org.eclipse.sirius.tests.update-*.zip "$SSH_ACCOUNT:$TARGET_ROOT/$STREAM/org.eclipse.sirius.tests-$VERSION-$PLATFORM.zip"
fi

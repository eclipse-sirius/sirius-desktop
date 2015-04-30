#!/bin/sh
# ====================================================================
# Copyright (c) 2007, 2015 THALES GLOBAL SERVICES
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================
#
# Copy the result of the build into a stable location on the intranet
# so that it can be consumed as an update-site by other builds.

[ -z "$WORKSPACE" -o -z "$SSH_ACCOUNT" -o -z "$PROMOTION_ROOT" -o -z "$PLATFORM" ] && {
     echo "Execution aborted.

One or more of the required variables is not set. They are normally
provided by the Hudson build.

- WORKSPACE      : the build workspace root.
- SSH_ACCOUNT    : the SSH account (login@machine) to use for the copy.
- PROMOTION_ROOT : the remote location in which all the builds are published.
- PLATFORM       : the name of the target Eclipse release (e.g. indigo)
"
    exit 1
}

promote()
{
  UPDATE_SITE="$1"
  MODULE="$2"

  if [ -f "$WORKSPACE/$UPDATE_SITE/target/repository/artifacts.jar" ]
  then
      ssh "$SSH_ACCOUNT" rm -rf   "$PROMOTION_ROOT/$MODULE"
      ssh "$SSH_ACCOUNT" mkdir -p "$PROMOTION_ROOT/$MODULE"
      scp -r "$WORKSPACE/$UPDATE_SITE/target/repository/" "$SSH_ACCOUNT:$PROMOTION_ROOT/$MODULE/"
  fi
}

promote "packaging/org.eclipse.sirius.update" "org.eclipse.sirius/master/$PLATFORM"

# Publish the target platform definitions used, so that downstream projects can reference them
ssh "$SSH_ACCOUNT" mkdir -p "$PROMOTION_ROOT/$MODULE/targets"
scp -r "$WORKSPACE"/releng/org.eclipse.sirius.targets/* "$SSH_ACCOUNT:$PROMOTION_ROOT/$MODULE/targets"

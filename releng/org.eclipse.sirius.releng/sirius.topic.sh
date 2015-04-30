#!/bin/sh
# ====================================================================
# Copyright (c) 2014, 2015 Obeo
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================

# This script tries to apply (cherry-pick) all the latest Gerrit
# patch-sets targetting a specific branch and topic. This must be run
# from the root of a Git clone, preferably on the same branch as the
# one targeted by the changes. It does not try to be smart in the
# order in which it picks the changes, and will fail as soon as a
# single change can not be applied cleanly.
#
# The script requires jq from http://stedolan.github.io/jq/ (MIT
# License) to be installed in order to interpret the JSON returned by
# the Gerrit query.
#
# Arguments (all optional):
# - the branch of the Gerrit changes to pick (defaults to "master")
# - the topic of the Gerrit  changes to pick (defaults to "next")
#
# Examples:
#
# % sirius.topic.sh v2.0.x proposed
# % sirius.topic.sh master m4

set -e

BRANCH=${1:-master}
TOPIC=${2:-next}
PROJECT=sirius/org.eclipse.sirius

which jq > /dev/null || {
    echo "Aborting: this script require jq (http://stedolan.github.io/jq/) to be installed and available in the path."
    exit 1
}

changes_for() {
    local readonly BRANCH="$1"
    local readonly TOPIC="$2"

    # Note: the confusing 'tail' invocation is here to drop the first
    # line from the Gerrit result, see
    # https://git.eclipse.org/r/Documentation/rest-api.html#output for
    # why this is needed.

    curl -s "https://git.eclipse.org/r/changes/?q=status:open+project:$PROJECT+branch:$BRANCH+topic:$TOPIC&o=CURRENT_REVISION" \
        | tail -n +2 \
        | jq  '.[] | select(has("current_revision")) | .current_revision as $rev | .revisions[$rev] | .fetch.git.ref' \
        | sed -e 's/"//g'
}

echo Starting from $(git describe HEAD)
for change in $(changes_for "$BRANCH" "$TOPIC"); do
    git fetch git://git.eclipse.org/gitroot/$PROJECT $change && git cherry-pick FETCH_HEAD
done

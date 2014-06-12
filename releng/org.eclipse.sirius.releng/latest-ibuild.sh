#!/bin/sh
BASE="http://download.eclipse.org/eclipse/updates/4.4-I-builds"
TEMPFILE=$(mktemp --suffix=.jar) || exit
curl -s "$BASE/compositeContent.jar" > "$TEMPFILE"
LATEST=$(unzip -p "$TEMPFILE" compositeContent.xml | grep "<child location='I" | sort -r | head -1 | sed -e "s/\s*<child location='//" -e "s|'/>\s*||")
echo "$BASE/$LATEST"
rm "$TEMPFILE"

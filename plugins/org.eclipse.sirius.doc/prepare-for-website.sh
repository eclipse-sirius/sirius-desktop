#!/bin/sh
#
# Add references to the Google Analytics JS code to all HTML pages before they are
# published on http://www.eclipse.org/sirius/doc

find doc -name "*.html" | while read f; do
     sed -i -e 's|<link type="text/css" rel="stylesheet" href="resources/custom.css"/>|<link type="text/css" rel="stylesheet" href="resources/custom.css"/>\n    <script type="text/javascript" src="../js/googleAnalytics.js"></script>|g' "$f"
     sed -i -e 's|<link type="text/css" rel="stylesheet" href="../resources/custom.css"/>|<link type="text/css" rel="stylesheet" href="../resources/custom.css"/>\n    <script type="text/javascript" src="../../js/googleAnalytics.js"></script>|g' "$f"
     sed -i -e 's|<link type="text/css" rel="stylesheet" href="../../resources/custom.css"/>|<link type="text/css" rel="stylesheet" href="../../resources/custom.css"/>\n    <script type="text/javascript" src="../../../js/googleAnalytics.js"></script>|g' "$f"
done


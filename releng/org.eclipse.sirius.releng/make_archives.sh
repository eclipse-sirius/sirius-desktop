#!/bin/sh

root=$(pwd)
version=$(basename "$root")
for platform in *; do
    if [ -d "$platform" ]; then
        (
          cd "$platform"
          echo "Archiving org.eclipse.sirius-${version}-${platform}.zip"
          zip -rq "$root/org.eclipse.sirius-${version}-${platform}.zip" artifacts.jar content.jar features plugins
        )

        (
          cd "$platform/tests"
          echo "Archiving org.eclipse.sirius.tests-${version}-${platform}.zip"
          zip -rq "$root/org.eclipse.sirius.tests-${version}-${platform}.zip" artifacts.jar content.jar features plugins
        )
    fi
done

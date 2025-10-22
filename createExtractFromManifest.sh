#!/bin/bash

# Parcours récursif de tous les MANIFEST.MF
find . -name "MANIFEST.MF" | while read manifest; do
    dir=$(dirname "$manifest")
    extract="$dir/extract.txt"

    # Initialisation du fichier extract.txt
    > "$extract"

    inside_require=0
    buffer=""

    while IFS= read -r line; do
        # Début de Require-Bundle:
        if [[ $line =~ ^Require-Bundle: ]]; then
            inside_require=1
            buffer="${line#*:}"
            buffer=$(echo "$buffer" | tr -d '[:space:]')
            continue
        fi

        # Lignes continuées (début par espace)
        if [[ $inside_require -eq 1 && $line =~ ^[[:space:]] ]]; then
            buffer+=",${line// /}"  # enlever espaces
            continue
        fi

        # Fin de section Require-Bundle, traiter buffer
        if [[ $inside_require -eq 1 && ! $line =~ ^[[:space:]] ]]; then
            inside_require=0
        fi

        if [[ -n $buffer ]]; then
            # Split par virgule
            IFS=',' read -ra bundles <<< "$buffer"
            for b in "${bundles[@]}"; do
                if [[ $b =~ ^org\.eclipse\.sirius ]]; then
                    name="${b%%;*}"  # supprimer tout après le premier ;
                    echo "    <dependency>
      <groupId>org.eclipse.sirius</groupId>
      <artifactId>$name</artifactId>
      <version>\${project.version}</version>
    </dependency>" >> "$extract"
                fi
            done
            buffer=""
        fi
    done < "$manifest"

    echo "[OK] $extract"
done

#!/bin/bash

# Parcours tous les extract.txt
find . -path "*/META-INF/extract.txt" | while read extract; do
    # Récupère le dossier parent de META-INF = monCheminProjet
    project_dir=$(dirname "$extract")/..  
    pom="$project_dir/pom.xml"

    if [[ ! -f "$pom" ]]; then
        echo "[WARN] pom.xml introuvable dans $project_dir"
        continue
    fi

    echo "[INFO] Mise à jour de $pom avec $extract"

    # Création d'un fichier temporaire
    tmp=$(mktemp)

    # On utilise awk pour remplacer le bloc <dependencies>...</dependencies>
    awk -v insert_file="$extract" '
        BEGIN {
            inserting=0
            # lire le contenu à insérer dans un tableau
            while ((getline line < insert_file) > 0) {
                insert[++n] = line
            }
            close(insert_file)
        }
        {
            if ($0 ~ /<dependencies>/) {
                print $0
                # insérer le contenu de extract.txt
                for (i=1;i<=n;i++) print insert[i]
                inserting=1
                next
            }
            if ($0 ~ /<\/dependencies>/ && inserting==1) {
                print $0
                inserting=0
                next
            }
            if (inserting==0) print $0
        }
    ' "$pom" > "$tmp"

    # Remplacer l'ancien pom.xml par le nouveau
    mv "$tmp" "$pom"
done

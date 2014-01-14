/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.migration.AbstractMigrationParticipant;
import org.osgi.framework.Version;

/**
 * The VSM and representations file migration participant to handle the
 * metamodel split of the diagram concepts into a specific EPackage in
 * viewpoint.ecore.
 * 
 * @author mporhel
 * 
 */
public class DiagramSplitMigrationParticipant extends AbstractMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    // TODO During review, choose the version (8.0.0, 7.1.0, ..)
    private static final Version MIGRATION_VERSION = new Version("8.0.0");

    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        EClassifier type = null;
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            // org.eclipse.emf.ecore.EPackage.getEClassifier(String) will return
            // null if not found.
            // A map name -> classifier is populated once.
            if (org.eclipse.sirius.viewpoint.ViewpointPackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.DiagramPackage.eINSTANCE.getEClassifier(name);
            } else if (org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEClassifier(name);
            } else if (org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE.getEClassifier(name);
            } else if (org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE.getEClassifier(name);
            }
        }
        return type;
    }
}

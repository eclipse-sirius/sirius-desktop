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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import org.eclipse.emf.ecore.EClass;
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
     * The version 8.0.0 corresponds to the file format of Sirius 1.0.0 M5.
     */
    private static final Version SIRIUS_1_0_0_M5_VERSION = new Version("8.0.0"); //$NON-NLS-1$

    /**
     * The version 8.1.0 corresponds to the file format of Sirius 1.0.0 M6.
     */
    private static final Version SIRIUS_1_0_0_M6_VERSION = new Version("8.1.0"); //$NON-NLS-1$

    public Version getMigrationVersion() {
        return SIRIUS_1_0_0_M6_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        EClassifier type = null;

        // org.eclipse.emf.ecore.EPackage.getEClassifier(String) will return
        // null if not found.
        // A map name -> classifier is populated once.

        if (Version.parseVersion(loadedVersion).compareTo(SIRIUS_1_0_0_M5_VERSION) < 0) {
            if (org.eclipse.sirius.viewpoint.ViewpointPackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.DiagramPackage.eINSTANCE.getEClassifier(name);
            } else if (org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEClassifier(name);
            } else if (org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE.getEClassifier(name);
            } else if (org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE.equals(ePackage)) {
                type = org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE.getEClassifier(name);
            }
        } else if (Version.parseVersion(loadedVersion).compareTo(SIRIUS_1_0_0_M6_VERSION) < 0) {
            // Moved EClasses:
            EClass containerDropDescription = org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE.getContainerDropDescription();
            EClass dragAndDropTarget = org.eclipse.sirius.diagram.DiagramPackage.eINSTANCE.getDragAndDropTarget();
            EClass dragAndDropTargetDescription = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDragAndDropTargetDescription();

            if (org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.equals(ePackage) && containerDropDescription.getName().equals(name)) {
                type = containerDropDescription;
            } else if (org.eclipse.sirius.viewpoint.ViewpointPackage.eINSTANCE.equals(ePackage) && dragAndDropTarget.getName().equals(name)) {
                type = dragAndDropTarget;
            } else if (org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.equals(ePackage) && dragAndDropTargetDescription.getName().equals(name)) {
                type = dragAndDropTargetDescription;
            }
        }
        return type;
    }
}

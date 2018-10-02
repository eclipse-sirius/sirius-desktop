/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.migration;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.osgi.framework.Version;

/**
 * Replace all instance of FilterVariableValue by {@link EObjectVariableValue}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class FilterVariableValueMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("11.0.0.201601261200"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        EClassifier eClass = null;
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if ("FilterVariableValue".equals(name)) { //$NON-NLS-1$
                eClass = DiagramPackage.eINSTANCE.getEObjectVariableValue();
            }
            if ("FilterVariable".equals(name)) { //$NON-NLS-1$
                eClass = ToolPackage.eINSTANCE.getSelectModelElementVariable();
            }
        }
        return eClass;
    }

    @Override
    public EStructuralFeature getLocalElement(EClass eClass, String name, String loadedVersion) {
        EStructuralFeature feature = null;
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (DiagramPackage.eINSTANCE.getEObjectVariableValue().equals(eClass)) {
                if (name.equals("variableDefinition")) { //$NON-NLS-1$
                    feature = DiagramPackage.eINSTANCE.getEObjectVariableValue_VariableDefinition();
                } else if (name.equals("modelElement")) { //$NON-NLS-1$
                    feature = DiagramPackage.eINSTANCE.getEObjectVariableValue_ModelElement();
                }
            }
        }
        return feature;
    }
}

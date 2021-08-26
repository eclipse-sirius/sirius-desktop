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
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.osgi.framework.Version;

/**
 * Replace the old instance of FilterVariable by an instance of
 * SelectModelElementVariable.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class VariableMigrationParticipant extends AbstractVSMMigrationParticipant {
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
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            // ownedVariables was of concrete type FilterVariable, so xsi::type
            // was not serialized. Thus, the required type of the class is asked
            // with the name of the new feature type and not with
            // "VariableFilter"
            if ("InteractiveVariableDescription".equals(name)) { //$NON-NLS-1$
                return ToolPackage.eINSTANCE.getSelectModelElementVariable();
            }
        }
        return null;
    }

    @Override
    public EStructuralFeature getLocalElement(EClass eClass, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (org.eclipse.sirius.diagram.description.filter.FilterPackage.eINSTANCE.getVariableFilter().equals(eClass) && name.equals("ownedVariables")) { //$NON-NLS-1$
                return org.eclipse.sirius.diagram.description.filter.FilterPackage.eINSTANCE.getVariableFilter_OwnedVariables();
            }
        }
        return null;
    }
}

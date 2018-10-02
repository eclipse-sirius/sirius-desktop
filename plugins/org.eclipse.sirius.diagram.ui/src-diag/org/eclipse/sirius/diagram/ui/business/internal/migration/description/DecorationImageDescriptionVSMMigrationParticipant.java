/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.migration.description;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.osgi.framework.Version;

/**
 * This VSM migration participant handles the migration from feature name change from
 * DecorationDescription.decoratorPath to DecorationDescription.imageExpression.
 * 
 * @author lfasani
 *
 */
public class DecorationImageDescriptionVSMMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("12.0.0.201704030900"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EStructuralFeature getAttribute(EClass eClass, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (DescriptionPackage.eINSTANCE.getMappingBasedDecoration().isSuperTypeOf(eClass)
                    || org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getSemanticBasedDecoration().isSuperTypeOf(eClass)) {
                if (name.equals("decoratorPath")) { //$NON-NLS-1$
                    return org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getDecorationDescription_ImageExpression();
                }
            }
        }
        return super.getAttribute(eClass, name, loadedVersion);
    }

}

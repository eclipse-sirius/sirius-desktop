/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.properties.core.internal.migration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * The migration participant used to create a new Default Category for View
 * Extension Description which does not defined one yet. It moves the existing
 * pages and groups under the newly created default category. If a category
 * already exists it moves the pages and the groups under it.
 * 
 * @author mbats
 */
public class CategoryMigrationParticipant extends AbstractCategoryMigrationParticipant {
    /**
     * The migration version.
     */
    private static final Version MIGRATION_VERSION = new Version("12.0.0.201702091400"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unknownFeature, Object valueOfUnknownFeature) {
        EObject eContainer = owner.eContainer();
        if (eContainer instanceof Group) {
            Group group = (Group) eContainer;
            if (Version.parseVersion(group.getVersion()).compareTo(ReferenceWidgetMigrationParticipant.MIGRATION_VERSION) >= 0) {
                super.handleFeature(owner, unknownFeature, valueOfUnknownFeature);
            }
        }
    }
}

/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.ui.business.internal.query.CustomizableQuery;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.Style;
import org.osgi.framework.Version;

/**
 * Migration contribution for style with custom attribute at true.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StyleCustomizationsRepresentationsFileMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("6.5.0.201209191025"); //$NON-NLS-1$

    /**
     * Overridden to migrate the custom feature of Style.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void handleFeature(EObject owner, final EStructuralFeature unkownFeature, final Object valueOfUnknownFeature) {
        super.handleFeature(owner, unkownFeature, valueOfUnknownFeature);
        if (owner instanceof Style) {
            Style style = (Style) owner;
            if ("custom".equals(unkownFeature.getName()) && valueOfUnknownFeature instanceof String && Boolean.parseBoolean((String) valueOfUnknownFeature)) { //$NON-NLS-1$
                style.getCustomFeatures().addAll(new CustomizableQuery(style).getCustomizableFeatureNames());
                TreeIterator<EObject> eAllContents = style.eAllContents();
                while (eAllContents.hasNext()) {
                    EObject next = eAllContents.next();
                    if (next instanceof Customizable) {
                        Customizable customizable = (Customizable) next;
                        customizable.getCustomFeatures().addAll(new CustomizableQuery(customizable).getCustomizableFeatureNames());
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.migration.IMigrationParticipant#getMigrationVersion()
     */
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }
}

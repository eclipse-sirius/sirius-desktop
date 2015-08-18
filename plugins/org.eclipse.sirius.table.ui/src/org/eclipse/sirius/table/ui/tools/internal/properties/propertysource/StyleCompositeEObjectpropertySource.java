/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.properties.propertysource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * A specific PropertySource for the
 * {@link org.eclipse.sirius.table.metamodel.table.DCellStyle style} of a
 * DCell.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class StyleCompositeEObjectpropertySource extends TableCompositeEObjectPropertySource {
    /**
     * Creates a new <code>StyleCompositeEObjectpropertySource</code>.
     */
    public StyleCompositeEObjectpropertySource() {
        super();
    }

    /**
     * Build the category name for this eObject.<BR>
     * This method do specific task for the forgroundColoer and the
     * backgroundColor of a DCellStyle.
     * 
     * @param eObject
     *            The eObject
     * @return A category name
     */
    @Override
    protected String buildCategoryName(final EObject eObject) {
        String result = super.buildCategoryName(eObject);
        if (eObject instanceof ColorDescription) {
            if (getCategoryNames().containsKey(eObject)) {
                getCategoryNames().remove(eObject);
            }
            result += " (" + eObject.eContainingFeature().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            getCategoryNames().put(eObject, result);
        }
        return result;
    }
}

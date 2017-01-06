/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.filter;

import org.eclipse.eef.properties.ui.api.IEEFTabDescriptor;
import org.eclipse.eef.properties.ui.api.IEEFTabDescriptorFilter;
import org.eclipse.sirius.properties.core.api.preferences.SiriusPropertiesCorePreferences;

/**
 * The {@link IEEFTabDescriptorFilter} for Eclipse Sirius.
 * 
 * @author mbats
 */
public class SiriusTabDescriptorFilter implements IEEFTabDescriptorFilter {

    /**
     * Id of the default tab.
     */
    private static final String DEFAULT_TAB_ID = "org.eclipse.sirius.ui.tools.views.model.explorer.tab"; //$NON-NLS-1$

    /**
     * Id of the semantic tab.
     */
    private static final String SEMANTIC_TAB_ID = "property.tab.semantic"; //$NON-NLS-1$

    @Override
    public boolean filter(IEEFTabDescriptor tabDescriptor) {
        boolean result = true;
        // Filter the default tab existing in the properties view when an
        // element is selected from the model explorer and the semantic tab when
        // an element is selected from a Sirius editor
        if (SEMANTIC_TAB_ID.equals(tabDescriptor.getId())) {
            result = SiriusPropertiesCorePreferences.INSTANCE.isSemanticTabFiltered();
        } else if (DEFAULT_TAB_ID.equals(tabDescriptor.getId())) {
            result = SiriusPropertiesCorePreferences.INSTANCE.isDefaultTabFiltered();
        }

        return result;
    }

}

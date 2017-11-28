/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.business.api.views.properties.tabbed;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderDescriptor;
import org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed.LabelProviderProviderRegistry;

/**
 * A Service class to get the first contributed {@link ILabelProvider} provided
 * for a specified EObject.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelProviderProviderService {

    /**
     * Get the first contributed {@link ILabelProvider} provided for the
     * specified EObject.
     * 
     * @param eObject
     *            the specified {@link Object}
     * @return the first contributed {@link ILabelProvider} provided for the
     *         specified {@link Object}
     */
    public ILabelProvider getFirstProvidedLabelProvider(Object eObject) {
        ILabelProvider labelProvider = null;
        for (LabelProviderProviderDescriptor labelProviderProviderDescriptor : LabelProviderProviderRegistry.getRegisteredExtensions()) {
            ILabelProviderProvider labelProviderProvider = labelProviderProviderDescriptor.getLabelProviderProvider();
            if (labelProviderProvider != null) {
                if (labelProviderProvider.provides(eObject)) {
                    labelProvider = labelProviderProvider.getLabelProvider();
                    if (labelProvider != null) {
                        break;
                    }
                }
            }
        }
        return labelProvider;
    }
}

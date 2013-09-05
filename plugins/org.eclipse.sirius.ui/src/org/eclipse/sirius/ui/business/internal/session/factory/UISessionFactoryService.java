/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;

/**
 * Service class to get the most priority {@link UISessionFactory}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class UISessionFactoryService {

    /**
     * Singleton of {@link UISessionFactoryService}.
     */
    public static final UISessionFactoryService INSTANCE = new UISessionFactoryService();

    /**
     * Get a default {@link UISessionFactory} to create IEditingSession. Compute
     * the first contribution which is not overridden by another extension
     * 
     * @return a default UISessionFactory
     */
    public UISessionFactory getUISessionFactory() {
        UISessionFactory result = null;
        List<UISessionFactoryDescriptor> uiSessionFactoryDescriptors = UISessionFactoryDescriptorRegistry.getRegisteredExtensions();
        if (!uiSessionFactoryDescriptors.isEmpty()) {
            UISessionFactoryDescriptor uiSessionFactoryDescriptor = getFirstMostOverrider(uiSessionFactoryDescriptors);
            if (uiSessionFactoryDescriptor != null) {
                result = uiSessionFactoryDescriptor.getUISessionFactory();
            }
        }
        if (result == null) {
            result = new DefaultUISessionFactoryImpl();
        }
        return result;
    }

    /**
     * Get the first {@link UISessionFactoryDescriptor} of extension list or the
     * the most overriding extension.
     * 
     * @param uiSessionFactoryDescriptors
     * 
     * @return
     */
    private UISessionFactoryDescriptor getFirstMostOverrider(List<UISessionFactoryDescriptor> uiSessionFactoryDescriptors) {
        List<String> overriddenUISessionFactorIDs = new ArrayList<String>();
        UISessionFactoryDescriptor firstMostOverridingUISessionFactoryDescriptor = null;
        Iterator<UISessionFactoryDescriptor> descriptorsIterator = uiSessionFactoryDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Recovers all overridden EditingDomainFactoryDescriptor
            firstMostOverridingUISessionFactoryDescriptor = descriptorsIterator.next();
            String overrideValue = firstMostOverridingUISessionFactoryDescriptor.getOverrideValue();
            if (overrideValue != null) {
                overriddenUISessionFactorIDs.add(overrideValue);
            }
        }
        descriptorsIterator = uiSessionFactoryDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Find the first UISessionFactoryDescriptor that is not
            // overridden
            firstMostOverridingUISessionFactoryDescriptor = descriptorsIterator.next();
            if (!overriddenUISessionFactorIDs.contains(firstMostOverridingUISessionFactoryDescriptor.getId())) {
                return firstMostOverridingUISessionFactoryDescriptor;
            }
        }

        return firstMostOverridingUISessionFactoryDescriptor;
    }
}

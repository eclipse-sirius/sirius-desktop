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
package org.eclipse.sirius.common.tools.api.editing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Singleton service to get a {@link IEditingDomainFactory}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @since 0.9.0
 */
public final class EditingDomainFactoryService {

    /**
     * Singleton of {@link EditingDomainFactoryService}.
     */
    public static final EditingDomainFactoryService INSTANCE = new EditingDomainFactoryService();

    /**
     * Get a default IEditingDomainFactory to create EditingDomain. Compute the
     * first contribution which is not overridden by another extension
     * 
     * @return a default IEditingDomainFactory
     */
    public IEditingDomainFactory getEditingDomainFactory() {
        IEditingDomainFactory result = null;
        List<EditingDomainFactoryDescriptor> editingDomainFactoryDescriptors = EditingDomainFactoryRegistry.getRegisteredExtensions();
        if (editingDomainFactoryDescriptors.isEmpty()) {
            result = new DefaultEditingDomainFactory();
        } else {
            EditingDomainFactoryDescriptor editingDomainFactoryDescriptor = getFirstMostOverrider(editingDomainFactoryDescriptors);
            result = editingDomainFactoryDescriptor.getEditingDomainFactory();
        }
        return result;
    }

    /**
     * Get the first EditingDomainFactoryDescriptor of extension list or the the
     * most overriding extension.
     * 
     * @param editingDomainFactoryDescriptors
     * 
     * @return
     */
    private EditingDomainFactoryDescriptor getFirstMostOverrider(List<EditingDomainFactoryDescriptor> editingDomainFactoryDescriptors) {
        ArrayList<String> overriddenEditingDomainFactoryIDs = new ArrayList<String>();
        EditingDomainFactoryDescriptor firstMostPriorityEditingDomainFactoryDescriptor = null;
        Iterator<EditingDomainFactoryDescriptor> descriptorsIterator = editingDomainFactoryDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Recovers all overridden EditingDomainFactoryDescriptor
            firstMostPriorityEditingDomainFactoryDescriptor = descriptorsIterator.next();
            String overrideValue = firstMostPriorityEditingDomainFactoryDescriptor.getOverrideValue();
            if (overrideValue != null) {
                overriddenEditingDomainFactoryIDs.add(overrideValue);
            }
        }
        descriptorsIterator = editingDomainFactoryDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Find the first EditingDomainFactoryDescriptor that is not
            // overridden
            firstMostPriorityEditingDomainFactoryDescriptor = descriptorsIterator.next();
            if (!overriddenEditingDomainFactoryIDs.contains(firstMostPriorityEditingDomainFactoryDescriptor.getId())) {
                return firstMostPriorityEditingDomainFactoryDescriptor;
            }
        }

        return firstMostPriorityEditingDomainFactoryDescriptor;
    }

}

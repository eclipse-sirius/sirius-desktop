/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.provider;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;

/**
 * An itemProvider helper to modify the emf generation.
 * 
 * @author jdupont
 * 
 */
public final class ItemProviderHelper {

    /**
     * Constructor
     */
    private ItemProviderHelper() {
        // Nothing
    }

    /**
     * Filter ChildDescriptors by type.
     * 
     * @param newChildDescriptors
     *            the childDescriptors to filter
     * @param typesToFilter
     *            the filter's type
     */
    public static void filterChildDescriptorsByType(Collection<Object> newChildDescriptors, Collection<EClass> typesToFilter) {
        // CHECKSTYLE:OFF
        for (Iterator<Object> iter = newChildDescriptors.iterator(); iter.hasNext();) {
            // CHECKSTYLE:ON
            Object desc = iter.next();
            if (desc instanceof CommandParameter) {
                CommandParameter cp = (CommandParameter) desc;
                if (cp.getValue() instanceof EObject) {
                    EObject obj = (EObject) cp.getValue();
                    if (typesToFilter.contains(obj.eClass())) {
                        iter.remove();
                    }
                }
            }
        }
    }
}

/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.ui;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.sirius.tools.internal.ui.ExternalJavaActionDescriptor;

/**
 * The provider of java actions extensions.
 * 
 * @author mporhel
 */
public final class ExternalJavaActionProvider {

    /** The shared instance of the provider. */
    public static final ExternalJavaActionProvider INSTANCE = new ExternalJavaActionProvider();

    private Collection<ExternalJavaActionDescriptor> externalJavaActionDescriptors = new HashSet<ExternalJavaActionDescriptor>();

    private ExternalJavaActionProvider() {
    }

    /**
     * Returns the interpreter for the given expression.
     * 
     * @param id
     *            the id of the wanted extension.
     * @return the corresponding IJavaActionMenuItem.
     */
    public IExternalJavaAction getJavaActionById(final String id) {
        for (ExternalJavaActionDescriptor javaActionMenuItemDescriptor : getJavaActionDescriptor()) {
            if (javaActionMenuItemDescriptor.getId().equalsIgnoreCase(id)) {
                return javaActionMenuItemDescriptor.createJavaActionMenuItem();
            }
        }
        return null;
    }

    /**
     * Returns all registered java actions.
     * 
     * @return all registered java actions.
     */
    public Collection<ExternalJavaActionDescriptor> getJavaActionDescriptor() {
        return externalJavaActionDescriptors;
    }

    /**
     * Adds the given action to the list of available external java actions.
     * 
     * @param actionDescriptor
     *            describes the action that is to be made available.
     */
    public void addAction(ExternalJavaActionDescriptor actionDescriptor) {
        externalJavaActionDescriptors.add(actionDescriptor);
    }

    /**
     * Clears this registry from all registered actions.
     */
    public void clearRegistry() {
        externalJavaActionDescriptors.clear();
    }

    /**
     * Removes from the registry the action corresponding to the given class
     * name.
     * 
     * @param actionClassName
     *            The action we need to remove.
     */
    public void removeAction(String actionClassName) {
        Iterator<ExternalJavaActionDescriptor> iterator = externalJavaActionDescriptors.iterator();
        while (iterator.hasNext()) {
            ExternalJavaActionDescriptor action = iterator.next();
            if (action.getActionClass().equals(actionClassName)) {
                iterator.remove();
            }
        }
    }
}

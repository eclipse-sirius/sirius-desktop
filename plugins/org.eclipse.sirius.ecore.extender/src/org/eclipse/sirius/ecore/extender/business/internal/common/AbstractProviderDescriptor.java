/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.common;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;

/**
 * Descriptor class for any kind of "provider" like class with priorities.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractProviderDescriptor implements Comparable<AbstractProviderDescriptor> {
    /**
     * the provider priority.
     */
    protected int priority = ExtenderConstants.NORMAL_PRIORITY;

    /**
     * the provider class name.
     */
    protected String providerClassName;

    /** Configuration element of this descriptor. */
    protected final IConfigurationElement element;

    /**
     * Create a new provider descriptor.
     * 
     * @param element
     *            {@link IConfigurationElement} from OSGI.
     */
    public AbstractProviderDescriptor(final IConfigurationElement element) {
        this.element = element;
        this.providerClassName = getAttribute("providerClass", null); //$NON-NLS-1$
        this.priority = PermissionService.getPriorityValue(getAttribute("priority", "low")); //$NON-NLS-1$//$NON-NLS-2$

    }

    /**
     * Returns the value of the attribute <code>name</code> of this descriptor's
     * configuration element. if the attribute hasn't been set, we'll return
     * <code>defaultValue</code> instead.
     * 
     * @param name
     *            Name of the attribute we seek the value of.
     * @param defaultValue
     *            Value to return if the attribute hasn't been set.
     * @return The value of the attribute <code>name</code>,
     *         <code>defaultValue</code> if it hasn't been set.
     */
    private String getAttribute(final String name, final String defaultValue) {
        final String value = element.getAttribute(name);
        if (value != null) {
            return value;
        }
        if (defaultValue != null) {
            return defaultValue;
        }
        throw new IllegalArgumentException(MessageFormat.format(Messages.AbstractProviderDescriptor_attributeMissing, name));
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Comparable implementation.
     * 
     * @param other
     *            the {@link AbstractProviderDescriptor} instance to compare
     * @return the result of the comparison
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(final AbstractProviderDescriptor other) {
        final int nombre1 = other.getPriority();
        final int nombre2 = priority;
        return nombre2 - nombre1;
    }

}

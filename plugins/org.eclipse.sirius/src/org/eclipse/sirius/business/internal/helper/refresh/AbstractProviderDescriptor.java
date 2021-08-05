/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.helper.refresh;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.business.api.refresh.RefreshConstants;
import org.eclipse.sirius.tools.api.Messages;

/**
 * The provider descriptor for the refresh API.
 * 
 * @author ymortier
 */
public abstract class AbstractProviderDescriptor implements Comparable<AbstractProviderDescriptor> {

    /** Configuration element of this descriptor. */
    protected final IConfigurationElement element;

    /** The priority of the provider. */
    private int priority = RefreshConstants.NORMAL_PRIORITY;

    /** The name of the provider class. */
    private String providerClassName;

    /**
     * Create a new {@link AbstractProviderDescriptor} with the specified configuration element.
     * 
     * @param element
     *            the configuration.
     */
    public AbstractProviderDescriptor(final IConfigurationElement element) {
        this.element = element;
        this.providerClassName = getAttribute("providerClass", null); //$NON-NLS-1$
        this.priority = getPriorityValue(getAttribute("priority", "low")); //$NON-NLS-1$//$NON-NLS-2$

    }

    /**
     * Returns the name of the provider class.
     * 
     * @return the name of the provider class.
     */
    public String getProviderClassName() {
        return providerClassName;
    }

    /**
     * Returns the value of the attribute <code>name</code> of this descriptor's configuration element. if the attribute
     * hasn't been set, we'll return <code>defaultValue</code> instead.
     * 
     * @param name
     *            Name of the attribute we seek the value of.
     * @param defaultValue
     *            Value to return if the attribute hasn't been set.
     * @return The value of the attribute <code>name</code>, <code>defaultValue</code> if it hasn't been set.
     */
    private String getAttribute(final String name, final String defaultValue) {
        final String value = element.getAttribute(name);
        if (value != null) {
            return value;
        }
        if (defaultValue != null) {
            return defaultValue;
        }
        throw new IllegalArgumentException(MessageFormat.format(Messages.AbstractProviderDescriptor_attributeMissingMsg, name));
    }

    /**
     * Return the priority of this provider.
     * 
     * @return the priority of this provider.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the value of the priority described by the given {@link String}. <br/>
     * Returned values according to <code>priorityString</code> value :
     * <ul>
     * <li>&quot;lowest&quot; =&gt; {@value RefreshConstants#PRIORITY_LOWEST}</li>
     * <li>&quot;low&quot; =&gt; {@value RefreshConstants#PRIORITY_LOW}</li>
     * <li>&quot;high&quot; =&gt; {@value RefreshConstants#PRIORITY_HIGH}</li>
     * <li>&quot;highest&quot; =&gt; {@value RefreshConstants#PRIORITY_HIGHEST}</li>
     * <li>anything else =&gt; {@value RefreshConstants#PRIORITY_NORMAL}</li>
     * </ul>
     * 
     * @param priorityString
     *            {@link String} value of the priority we seek.
     * @return <code>int</code> corresponding to the given priority {@link String}.
     */
    private int getPriorityValue(final String priorityString) {
        int priorityValue = RefreshConstants.NORMAL_PRIORITY;
        if ("lowest".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = RefreshConstants.LOWEST_PRIORITY;
        } else if ("low".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = RefreshConstants.LOW_PRIORITY;
        } else if ("high".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = RefreshConstants.HIGH_PRIORITY;
        } else if ("highest".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = RefreshConstants.HIGHEST_PRIORITY;
        }
        return priorityValue;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Comparable#compareTo(T)
     */
    @Override
    public int compareTo(final AbstractProviderDescriptor other) {
        return doCompareTo(other);
    }

    /**
     * @param other
     * @return
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    private int doCompareTo(final AbstractProviderDescriptor other) {
        final int nombre1 = other.getPriority();
        final int nombre2 = priority;
        return nombre2 - nombre1;
    }

}

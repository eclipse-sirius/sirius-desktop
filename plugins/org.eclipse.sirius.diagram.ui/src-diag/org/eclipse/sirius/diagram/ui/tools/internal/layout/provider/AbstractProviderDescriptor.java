/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutConstants;

/**
 * A basic descriptor.
 *
 * @author ymortier
 */
public abstract class AbstractProviderDescriptor implements Comparable<AbstractProviderDescriptor> {

    /** Configuration element of this descriptor. */
    protected final IConfigurationElement element;

    /** The priority of this provider. */
    private int priority = LayoutConstants.NORMAL_PRIORITY;

    /** The name of the provider. */
    private String providerClassName;

    /**
     * Create a new {@link AbstractProviderDescriptor}.
     *
     * @param element
     *            the configuration element.
     */
    public AbstractProviderDescriptor(final IConfigurationElement element) {
        this.element = element;
        this.priority = getPriorityValue(getAttribute("priority", "low")); //$NON-NLS-1$//$NON-NLS-2$
        this.providerClassName = getAttribute("providerClass", null); //$NON-NLS-1$
    }

    /**
     * Return the provider class name.
     *
     * @return the provider class name.
     */
    public String getProviderClassName() {
        return providerClassName;
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
        throw new IllegalArgumentException(MessageFormat.format(Messages.AbstractProviderDescriptor_missingAttributeMsg, name));
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
     * Returns the value of the priority described by the given {@link String}.
     * <br/>
     * Returned values according to <code>priorityString</code> value :
     * <ul>
     * <li>&quot;lowest&quot; =&gt;
     * {@value org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutConstants#LOWEST_PRIORITY}
     * </li>
     * <li>&quot;low&quot; =&gt;
     * {@value org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutConstants#LOW_PRIORITY}
     * </li>
     * <li>&quot;high&quot; =&gt;
     * {@value org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutConstants#HIGH_PRIORITY}
     * </li>
     * <li>&quot;highest&quot; =&gt;
     * {@value org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutConstants#HIGHEST_PRIORITY}
     * </li>
     * <li>anything else =&gt;
     * {@value org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutConstants#NORMAL_PRIORITY}
     * </li>
     * </ul>
     *
     * @param priorityString
     *            {@link String} value of the priority we seek.
     * @return <code>int</code> corresponding to the given priority
     *         {@link String}.
     */
    private int getPriorityValue(final String priorityString) {
        int priorityValue = LayoutConstants.NORMAL_PRIORITY;
        if ("lowest".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = LayoutConstants.LOWEST_PRIORITY;
        } else if ("low".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = LayoutConstants.LOW_PRIORITY;
        } else if ("high".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = LayoutConstants.HIGH_PRIORITY;
        } else if ("highest".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = LayoutConstants.HIGHEST_PRIORITY;
        }
        return priorityValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final AbstractProviderDescriptor other) {
        final int nombre1 = other.getPriority();
        final int nombre2 = priority;
        return nombre2 - nombre1;
    }

}

/******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.elk.debug.gmf.layout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.gmf.runtime.common.core.internal.CommonCoreDebugOptions;
import org.eclipse.gmf.runtime.common.core.internal.CommonCorePlugin;
import org.eclipse.gmf.runtime.common.core.util.EnumeratedType;
import org.eclipse.gmf.runtime.common.core.util.Trace;

/**
 * An enumeration of service provider priorities.
 * <P>
 * Each service provider has a <code>ProviderPriority</code> that is declared
 * in its extension descriptor. It is the
 * {@link org.eclipse.gmf.runtime.common.core.service.ExecutionStrategy} that
 * determines how service provider priorities are used to select a provider to
 * service each client request. For example, if the
 * {@link org.eclipse.gmf.runtime.common.core.service.ExecutionStrategy#FIRST} 
 * is used, the provider with the highest priority will give an answer to the
 * request.
 * 
 * @see org.eclipse.gmf.runtime.common.core.service
 * 
 * @author khussey
 */
public class ProviderPriority extends EnumeratedType {

    private static final long serialVersionUID = 1L;

    /**
     * An internal unique identifier for provider priorities.
     */
    private static int nextOrdinal = 0;

    /**
     * The lowest provider priority.
     */
    public static final ProviderPriority LOWEST = new ProviderPriority("Lowest"); //$NON-NLS-1$

    /**
     * The second-lowest provider priority.
     */
    public static final ProviderPriority LOW = new ProviderPriority("Low"); //$NON-NLS-1$

    /**
     * The middle provider priority.
     */
    public static final ProviderPriority MEDIUM = new ProviderPriority("Medium"); //$NON-NLS-1$

    /**
     * The second-highest provider priority.
     */
    public static final ProviderPriority HIGH = new ProviderPriority("High"); //$NON-NLS-1$

    /**
     * The highest provider priority.
     */
    public static final ProviderPriority HIGHEST = new ProviderPriority("Highest"); //$NON-NLS-1$

    /**
     * The list of values for this enumerated type.
     */
    private static final ProviderPriority[] VALUES =
        { LOWEST, LOW, MEDIUM, HIGH, HIGHEST };

    /**
     * Retrieves the provider priority with the specified name.
     * 
     * @param name The name of the provider priority to be retrieved.
     * @return The provider priority with the specified name.
     * @exception IllegalArgumentException If a provider priority with the
     *                                      specified name does not exist.
     */
    public static ProviderPriority parse(String name) {
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i].getName().equals(name)) {
                return VALUES[i];
            }
        }

        IllegalArgumentException iae = new IllegalArgumentException(name);
        Trace.throwing(CommonCorePlugin.getDefault(), CommonCoreDebugOptions.EXCEPTIONS_THROWING, ProviderPriority.class, "parse", iae); //$NON-NLS-1$
        throw iae;
    }

    /**
     * Constructs a new provider priority with the specified name.
     * 
     * @param name The name of the new provider priority.
     */
    private ProviderPriority(String name) {
        super(name, nextOrdinal++);
    }

    /**
     * Constructs a new provider priority with the specified name and ordinal.
     * 
     * @param name The name of the new provider priority.
     * @param ordinal The ordinal for the new provider priority .
     */
    protected ProviderPriority(String name, int ordinal) {
        super(name, ordinal);
    }

    /**
     * Retrieves the list of constants for this enumerated type.
     * 
     * @return The list of constants for this enumerated type.
     */
    @Override
    protected List getValues() {
        return Collections.unmodifiableList(Arrays.asList(VALUES));
    }

    /**
     * Compares this provider priority with the specified object for order.
     * Returns a negative integer, zero, or a positive integer as this provider
     * priority is less than, equal to, or greater than the specified object.
     * 
     * @param object The object to be compared.
     * @return A negative integer, zero, or a positive integer as this
     *          provider priority is less than, equal to, or greater than the
     *          specified object.
     * @exception ClassCastException If the specified object's type
     *                                prevents it from being compared to
     *                                this provider priority.
     */
    public int compareTo(Object object) {
        List values = getValues();
        return values.indexOf(this) - values.indexOf(object);
    }

}

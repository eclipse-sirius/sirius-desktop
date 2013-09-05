/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.common;

import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.IMetamodelExtender;

/**
 * Descriptor used to keep track of the provided extenders.
 * 
 * @author cbrun
 * 
 */
public class ExtenderDescriptor implements Comparable<ExtenderDescriptor> {
    int priority = ExtenderConstants.NORMAL_PRIORITY;

    IMetamodelExtender extender;

    /**
     * Create a new descriptor from an extender and a priority.
     * 
     * @param extender
     *            a metamodel extender.
     * @param priority
     *            a priority value from {@link ExtenderConstants}.
     */
    public ExtenderDescriptor(final IMetamodelExtender extender, final int priority) {
        this.extender = extender;
        this.priority = priority;

    }

    public IMetamodelExtender getExtender() {
        return extender;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * compare two descriptors.
     * 
     * @param other
     *            another decriptor
     * 
     * @return same value as java.lang.Comparable#compareTo(java.lang.Object)
     * 
     */
    public int compareTo(final ExtenderDescriptor other) {
        final int nombre1 = other.getPriority();
        final int nombre2 = priority;
        return nombre2 - nombre1;
    }
}

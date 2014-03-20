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
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Default {@link IPropertySource}.
 * 
 * @author edugueperoux
 */
public class DefaultPropertySource implements IPropertySource {

    private IPropertyDescriptor[] propertyDescriptors;

    /**
     * Default constructor to have IPropertyDescriptor(s).
     * 
     * @param propertyDescriptors
     *            the {@link IPropertySource}s to return with
     *            {@link IPropertySource#getPropertyDescriptors()}
     */
    public DefaultPropertySource(IPropertyDescriptor[] propertyDescriptors) {
        this.propertyDescriptors = propertyDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    public Object getEditableValue() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return propertyDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    public Object getPropertyValue(Object id) {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPropertySet(Object id) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void resetPropertyValue(Object id) {

    }

    /**
     * {@inheritDoc}
     */
    public void setPropertyValue(Object id, Object value) {

    }

}

/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.emf.ui.properties;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * An Extensible Property Source that creates a
 * {@link ExtensiblePropertyDescriptor}. This property source, allows clients to
 * customize <code>CellEditor</code> if it's used instead of the default
 * {@link PropertySource}.
 * 
 * @author Florian Barbin
 *
 */
public class ExtensiblePropertySource extends PropertySource {

    /** The {@link CellEditorProviderCollector}. */
    protected CellEditorProviderCollector collector;

    /**
     * Default constructor.
     * 
     * @param object
     *            a semantic eObject.
     * @param itemPropertySource
     *            emf property source object.
     * @param collector
     *            the collector that will provide the list of
     *            <code>ICellEditorProvider</code>.
     */
    public ExtensiblePropertySource(Object object, IItemPropertySource itemPropertySource, CellEditorProviderCollector collector) {
        super(object, itemPropertySource);
        this.collector = collector;
    }

    @Override
    protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
        return new ExtensiblePropertyDescriptor(object, itemPropertyDescriptor, collector);
    }
}

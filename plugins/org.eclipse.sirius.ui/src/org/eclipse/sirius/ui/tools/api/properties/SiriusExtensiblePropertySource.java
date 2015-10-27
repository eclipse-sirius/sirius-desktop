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
package org.eclipse.sirius.ui.tools.api.properties;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.sirius.ext.emf.ui.properties.ExtensiblePropertySource;
import org.eclipse.sirius.ui.tools.internal.properties.SiriusCellEditorProviderCollector;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * Clients that want to provide custom cell editor through the extension point
 * {@link SiriusExtensiblePropertySource#EXTENSION_POINT_ID} should use this
 * PropertySource instead of the default PropertySource.
 * 
 * @author Florian Barbin
 *
 */
public class SiriusExtensiblePropertySource extends ExtensiblePropertySource {

    /**
     * Constructor.
     * 
     * @param object
     *            a semantic eObject.
     * @param itemPropertySource
     *            emf property source object.
     */
    public SiriusExtensiblePropertySource(Object object, IItemPropertySource itemPropertySource) {
        super(object, itemPropertySource, SiriusCellEditorProviderCollector.getInstance());
    }

    @Override
    protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
        return new SiriusExtensiblePropertyDescriptor(object, itemPropertyDescriptor, collector);
    }

}

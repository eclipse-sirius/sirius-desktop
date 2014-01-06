/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.interpreterview;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;

/**
 * The content provider for variables.
 * 
 * @author ymortier
 */
public class VariableContentProvider extends AdapterFactoryContentProvider {

    /**
     * Constructor.
     * 
     * @param adapterFactory
     *            is the adapter factory
     */
    public VariableContentProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getElements(final Object object) {
        Object[] elements = null;
        if (object instanceof Map) {
            elements = ((Map<?, ?>) object).entrySet().toArray();
        } else if (object instanceof Map.Entry) {
            final Object[] result = new Object[1];
            result[0] = ((Map.Entry<?, ?>) object).getValue();
            elements = result;
        } else if (object instanceof Collection) {
            elements = ((Collection<?>) object).toArray();
        } else if (object instanceof TreeItemWrapper) {
            elements = new Object[] { ((TreeItemWrapper) object).getWrappedObject() };
        } else {
            elements = super.getElements(object);
        }
        return elements;
    }
}

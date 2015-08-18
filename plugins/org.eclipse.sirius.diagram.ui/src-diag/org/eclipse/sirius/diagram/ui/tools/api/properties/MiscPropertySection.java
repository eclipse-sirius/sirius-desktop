/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.properties.DefaultPropertySource;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * This property section provides debug informations.
 * 
 * @author ymortier
 */
public class MiscPropertySection extends AdvancedPropertySection implements IPropertySourceProvider {

    /**
     * Returns the property source of the specified object.
     * 
     * @param object
     *            the object.
     * @return the property source of the specified object.
     */
    public IPropertySource getPropertySource(final Object object) {

        IPropertySource propSrc = null;

        if (object instanceof IPropertySource) {
            propSrc = (IPropertySource) object;
        } else if (object instanceof EditPart) {
            propSrc = new MiscPropertySource(object);
        } else if (object instanceof Collection<?>) {
            final Collection<?> collection = (Collection<?>) object;
            final IPropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[collection.size()];
            final Iterator<?> iterCollection = collection.iterator();
            int i = 0;
            while (iterCollection.hasNext()) {
                final Object next = iterCollection.next();
                propertyDescriptors[i] = new PropertyDescriptor(next, i + StringUtil.EMPTY_STRING);
                i++;
            }
            propSrc = new DefaultPropertySource(propertyDescriptors);

        } else if (object instanceof Object[]) {
            final Object[] collection = (Object[]) object;
            final IPropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[collection.length];
            for (int i = 0; i < collection.length; i++) {
                Object next = collection[i];
                if (next == null) {
                    next = "null object";
                }
                propertyDescriptors[i] = new PropertyDescriptor(next, i + ""); //$NON-NLS-1$
            }
            propSrc = new DefaultPropertySource(propertyDescriptors);
        } else if (object != null) {
            propSrc = new MiscPropertySource(object);
        }
        return propSrc;
    }

    /**
     * Returns the provider.
     * 
     * @return the provider.
     */
    @Override
    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    /**
     * Modify/unwrap selection.
     * 
     * @param selected
     *            the current selected object
     * @return the unwrapped object
     */
    protected Object transformSelection(final Object selected) {

        if (selected instanceof EditPart) {
            return selected;

        }
        return selected;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        if (selection.isEmpty() || !(selection instanceof StructuredSelection)) {
            super.setInput(part, selection);
            return;
        }
        final StructuredSelection structuredSelection = (StructuredSelection) selection;
        final ArrayList<Object> transformedSelection = new ArrayList<Object>(structuredSelection.size());
        final Iterator<?> it = structuredSelection.iterator();
        while (it.hasNext()) {
            final Object r = transformSelection(it.next());
            if (r != null) {
                transformedSelection.add(r);
            }
        }
        super.setInput(part, new StructuredSelection(transformedSelection));
    }
}

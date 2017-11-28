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
package org.eclipse.sirius.table.ui.tools.internal.properties.section.core;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.ui.tools.internal.properties.propertysource.TableCompositeEObjectPropertySource;
import org.eclipse.sirius.table.ui.tools.internal.properties.section.common.AbstractDTablePropertySection;
import org.eclipse.sirius.ui.tools.api.properties.AbstractEObjectPropertySource;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Properties for the semantic model.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class CorePropertySection extends AbstractDTablePropertySection {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
     */
    public IPropertySource getPropertySource(final Object object) {
        IPropertySource propSrc = null;

        if (object instanceof IPropertySource) {
            propSrc = (IPropertySource) object;
        } else if (object instanceof DCell) {
            final DCell cell = (DCell) object;
            propSrc = getPropertySource(cell);
        } else if (object instanceof DLine) {
            final DLine line = (DLine) object;
            propSrc = getPropertySource(line);
        } else {
            final AdapterFactory af = getAdapterFactory(object);
            if (af != null) {
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    return new PropertySource(object, ips);
                }
            }
            if (object instanceof IAdaptable) {
                propSrc = ((IAdaptable) object).getAdapter(IPropertySource.class);
            }
        }
        return propSrc;
    }

    /**
     * Returns a property source for the given {@link DLine line}.
     * 
     * @param line
     *            the line
     * @return the property source for the line passed in (maybe
     *         <code>null</code>)
     */
    protected AbstractEObjectPropertySource getPropertySource(final DLine line) {
        final AbstractEObjectPropertySource propertySource = new TableCompositeEObjectPropertySource();
        final AdapterFactory af = getAdapterFactory(line);
        if (af != null) {
            final IItemPropertySource ips = (IItemPropertySource) af.adapt(line, IItemPropertySource.class);
            if (ips != null) {
                final IPropertySource targetPropertySource = new PropertySource(line, ips);
                propertySource.addPropertySource(line, targetPropertySource);
            }
        }
        return propertySource;
    }

    /**
     * Returns a property source for the given {@link DCell cell}.
     * 
     * @param cell
     *            the cell
     * @return the property source for the cell passed in (maybe
     *         <code>null</code>)
     */
    protected IPropertySource getPropertySource(final DCell cell) {
        final AbstractEObjectPropertySource propertySource = new TableCompositeEObjectPropertySource();
        final AdapterFactory af = getAdapterFactory(cell);
        if (af != null) {
            final IItemPropertySource ips = (IItemPropertySource) af.adapt(cell, IItemPropertySource.class);
            if (ips != null) {
                final IPropertySource targetPropertySource = new PropertySource(cell, ips);
                propertySource.addPropertySource(cell, targetPropertySource);
            }
        }
        return propertySource;
    }

}

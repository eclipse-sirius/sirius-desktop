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
package org.eclipse.sirius.table.ui.tools.internal.properties.section.style;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.ui.tools.internal.properties.propertysource.StyleCompositeEObjectpropertySource;
import org.eclipse.sirius.table.ui.tools.internal.properties.section.common.AbstractDTablePropertySection;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Properties for the semantic model.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StylePropertySection extends AbstractDTablePropertySection {

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
        } else {
            final AdapterFactory af = getAdapterFactory(object);
            if (af != null) {
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    return new PropertySource(object, ips);
                }
            }
            if (object instanceof IAdaptable) {
                propSrc = (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class);
            }
        }
        return propSrc;
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
        final StyleCompositeEObjectpropertySource propertySource = new StyleCompositeEObjectpropertySource();
        DCellQuery cellQuery = new DCellQuery(cell);
        DTableElementStyle foregroundStyleToApply = cellQuery.getForegroundStyleToApply().get();
        DTableElementStyle backgroundStyleToApply = cellQuery.getBackgroundStyleToApply().get();
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(foregroundStyleToApply);
        if (!(accessor.isExtension(foregroundStyleToApply))) {
            final AdapterFactory af = getAdapterFactory(foregroundStyleToApply);
            if (af != null) {
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(foregroundStyleToApply, IItemPropertySource.class);
                if (ips != null) {
                    final IPropertySource targetPropertySource = new PropertySource(foregroundStyleToApply, ips);
                    propertySource.addPropertySource(foregroundStyleToApply, targetPropertySource);
                }
            }
            final RGBValues backgroundColor = backgroundStyleToApply.getBackgroundColor();
            if (backgroundColor != null) {
                final AdapterFactory afBg = getAdapterFactory(backgroundColor);
                if (afBg != null) {
                    final IItemPropertySource ips = (IItemPropertySource) afBg.adapt(backgroundColor, IItemPropertySource.class);
                    if (ips != null) {
                        final IPropertySource targetPropertySource = new PropertySource(backgroundColor, ips);
                        propertySource.addPropertySource(backgroundColor, targetPropertySource);
                    }
                }
            }
            final RGBValues foregroundColor = foregroundStyleToApply.getForegroundColor();
            if (foregroundColor != null) {
                final AdapterFactory afFg = getAdapterFactory(foregroundColor);
                if (afFg != null) {
                    final IItemPropertySource ips = (IItemPropertySource) afFg.adapt(foregroundColor, IItemPropertySource.class);
                    if (ips != null) {
                        final IPropertySource targetPropertySource = new PropertySource(foregroundColor, ips);
                        propertySource.addPropertySource(foregroundColor, targetPropertySource);
                    }
                }
            }
        }
        return propertySource;
    }
}

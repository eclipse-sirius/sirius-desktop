/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.ui.tools.internal.properties.section.style;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.ui.tools.internal.properties.propertysource.StyleCompositeEObjectpropertySource;
import org.eclipse.sirius.table.ui.tools.internal.properties.section.common.AbstractDTablePropertySection;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.ui.views.properties.IPropertySource;

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
    @Override
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
                propSrc = ((IAdaptable) object).getAdapter(IPropertySource.class);
            }
        }
        return propSrc;
    }

    /**
     * Returns a property source for the given {@link DCell cell}.
     * 
     * @param cell
     *            the cell
     * @return the property source for the cell passed in (maybe <code>null</code>)
     */
    protected IPropertySource getPropertySource(final DCell cell) {
        final StyleCompositeEObjectpropertySource propertySource = new StyleCompositeEObjectpropertySource();
        DCellQuery cellQuery = new DCellQuery(cell);
        DTableElementStyle foregroundStyleToApply = cellQuery.getForegroundStyleToApply().get();
        DTableElementStyle backgroundStyleToApply = cellQuery.getBackgroundStyleToApply().get();
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(foregroundStyleToApply);
        if (foregroundStyleToApply != null && !(accessor.isExtension(foregroundStyleToApply))) {
            final AdapterFactory af = getAdapterFactory(foregroundStyleToApply);
            if (af != null) {
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(foregroundStyleToApply, IItemPropertySource.class);
                if (ips != null) {
                    final IPropertySource targetPropertySource = new PropertySource(foregroundStyleToApply, ips);
                    propertySource.addPropertySource(foregroundStyleToApply, targetPropertySource);
                }
            }
        }
        if (backgroundStyleToApply != null && backgroundStyleToApply != foregroundStyleToApply && !(accessor.isExtension(backgroundStyleToApply))) {
            final AdapterFactory afBg = getAdapterFactory(backgroundStyleToApply);
            if (afBg != null) {
                final IItemPropertySource ips = (IItemPropertySource) afBg.adapt(backgroundStyleToApply, IItemPropertySource.class);
                if (ips != null) {
                    final IPropertySource targetPropertySource = new PropertySource(backgroundStyleToApply, ips);
                    propertySource.addPropertySource(backgroundStyleToApply, targetPropertySource);
                }
            }
        }
        return propertySource;
    }
}

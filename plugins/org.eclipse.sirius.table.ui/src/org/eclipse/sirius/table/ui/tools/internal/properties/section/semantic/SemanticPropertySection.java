/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.properties.section.semantic;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.ui.tools.internal.properties.propertysource.TableCompositeEObjectPropertySource;
import org.eclipse.sirius.table.ui.tools.internal.properties.section.common.AbstractDTablePropertySection;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.tools.api.properties.AbstractCompositeEObjectPropertySource;
import org.eclipse.sirius.ui.tools.api.properties.SiriusExtensiblePropertySource;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Properties for the semantic model.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SemanticPropertySection extends AbstractDTablePropertySection {

    @Override
    public IPropertySource getPropertySource(final Object object) {
        IPropertySource propSrc = null;

        if (object instanceof IPropertySource) {
            propSrc = (IPropertySource) object;
        } else if (object instanceof DTableElement) {
            final DTableElement cell = (DTableElement) object;
            propSrc = getPropertySource(cell);
        } else if (object instanceof DSemanticDecorator) {
            final DSemanticDecorator semanticDecorator = (DSemanticDecorator) object;
            propSrc = getPropertySource(semanticDecorator);
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
     * Returns a property source for the given {@link DTableElement table element}.
     * 
     * @param tableElement
     *            the table element
     * @return the property source for the cell passed in (maybe <code>null</code>)
     */
    protected IPropertySource getPropertySource(final DTableElement tableElement) {
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(tableElement);
        return getPropertySource(accessor, tableElement.getSemanticElements());
    }

    /**
     * Returns a property source for the given cell.
     * 
     * @param accessor
     *            the model accessor
     * @param eObjects
     *            collection of object
     * @return the property source for the cell passed in (maybe <code>null</code>)
     */
    protected IPropertySource getPropertySource(final ModelAccessor accessor, final Collection<EObject> eObjects) {
        final Iterator<EObject> iterElements = eObjects.iterator();
        final TableCompositeEObjectPropertySource propertySource = new TableCompositeEObjectPropertySource();
        while (iterElements.hasNext()) {
            final EObject semanticElement = iterElements.next();
            fillPropertySource(accessor, semanticElement, propertySource);
        }
        return propertySource;
    }

    /**
     * Returns a property source for the given {@link DSemanticDecorator semanticDecorator}.
     * 
     * @param semanticDecorator
     *            the semanticDecorator
     * @return the property source for the semanticDecorator passed in (maybe <code>null</code>)
     */
    protected IPropertySource getPropertySource(final DSemanticDecorator semanticDecorator) {
        final TableCompositeEObjectPropertySource propertySource = new TableCompositeEObjectPropertySource();
        final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticDecorator);
        final EObject semanticElement = semanticDecorator.getTarget();
        fillPropertySource(accessor, semanticElement, propertySource);
        return propertySource;
    }

    private void fillPropertySource(final ModelAccessor accessor, final EObject semanticElement, final AbstractCompositeEObjectPropertySource propertySource) {
        if (!(accessor.isExtension(semanticElement))) {
            final AdapterFactory af = getAdapterFactory(semanticElement);
            if (af != null) {
                final IItemPropertySource ips = (IItemPropertySource) af.adapt(semanticElement, IItemPropertySource.class);
                if (ips != null) {
                    final IPropertySource targetPropertySource = new SiriusExtensiblePropertySource(semanticElement, ips);
                    propertySource.addPropertySource(semanticElement, targetPropertySource);
                }
            }
        }
    }
}

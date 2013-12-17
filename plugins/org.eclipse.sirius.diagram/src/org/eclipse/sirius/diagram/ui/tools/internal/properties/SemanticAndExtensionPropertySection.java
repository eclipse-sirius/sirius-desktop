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
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * Displays the semantic and the extension properties.
 * 
 * @author ymortier
 */
public class SemanticAndExtensionPropertySection extends SemanticPropertySection {

    /** The provider for the extension. */
    private IPropertySourceProvider extendedPropertySourceProvider;

    /**
     * Creates a new <code>SemanticAndExtensionPropertySection</code>.
     */
    public SemanticAndExtensionPropertySection() {
        this.extendedPropertySourceProvider = new ExtensionSemanticPropertiesSection();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.SemanticPropertySection#getPropertySource(java.lang.Object)
     */
    @Override
    public IPropertySource getPropertySource(final Object object) {
        final IPropertySource propertySource = super.getPropertySource(object);
        if (propertySource instanceof CompositeEObjectPropertySource) {
            final CompositeEObjectPropertySource compositePropertySource = (CompositeEObjectPropertySource) propertySource;
            if (object instanceof DDiagramElement) {
                final DDiagramElement viewPointElement = (DDiagramElement) object;
                final Iterator<EObject> iterElementsToDestroy = viewPointElement.getSemanticElements().iterator();
                while (iterElementsToDestroy.hasNext()) {
                    final EObject semanticElement = iterElementsToDestroy.next();
                    final IPropertySource extendedPropertySource = this.extendedPropertySourceProvider.getPropertySource(object);
                    compositePropertySource.addPropertySource(semanticElement, extendedPropertySource);
                }
            }
        }
        return propertySource;
    }
}

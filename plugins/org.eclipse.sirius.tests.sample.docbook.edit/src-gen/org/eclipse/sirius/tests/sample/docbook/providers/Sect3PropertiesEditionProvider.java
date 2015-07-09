/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.providers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.impl.utils.EEFUtils;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.providers.impl.PropertiesEditingProviderImpl;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Sect3;
import org.eclipse.sirius.tests.sample.docbook.components.Sect3PropertiesEditionComponent;

/**
 *
 *
 */
public class Sect3PropertiesEditionProvider extends PropertiesEditingProviderImpl {

    /**
     * Constructor without provider for super types.
     */
    public Sect3PropertiesEditionProvider() {
        super();
    }

    /**
     * Constructor with providers for super types.
     *
     * @param superProviders
     *            providers to use for super types.
     */
    public Sect3PropertiesEditionProvider(List<PropertiesEditingProvider> superProviders) {
        super(superProviders);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext)
     *
     */
    @Override
    public boolean provides(PropertiesEditingContext editingContext) {
        return (editingContext.getEObject() instanceof Sect3) && (DocbookPackage.Literals.SECT3 == editingContext.getEObject().eClass());
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
     *      java.lang.String)
     *
     */
    @Override
    public boolean provides(PropertiesEditingContext editingContext, String part) {
        return (editingContext.getEObject() instanceof Sect3) && (Sect3PropertiesEditionComponent.BASE_PART.equals(part));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
     *      java.lang.Class)
     *
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean provides(PropertiesEditingContext editingContext, java.lang.Class refinement) {
        return (editingContext.getEObject() instanceof Sect3) && (refinement == Sect3PropertiesEditionComponent.class);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#provides(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
     *      java.lang.String, java.lang.Class)
     *
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean provides(PropertiesEditingContext editingContext, String part, java.lang.Class refinement) {
        return (editingContext.getEObject() instanceof Sect3) && ((Sect3PropertiesEditionComponent.BASE_PART.equals(part) && refinement == Sect3PropertiesEditionComponent.class));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
     *      java.lang.String)
     *
     */
    @Override
    public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode) {
        if (editingContext.getEObject() instanceof Sect3) {
            return new Sect3PropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
        }
        return super.getPropertiesEditingComponent(editingContext, mode);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
     *      java.lang.String, java.lang.String)
     *
     */
    @Override
    public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part) {
        if (editingContext.getEObject() instanceof Sect3) {
            if (Sect3PropertiesEditionComponent.BASE_PART.equals(part)) {
                return new Sect3PropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
            }
        }
        return super.getPropertiesEditingComponent(editingContext, mode, part);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider#getPropertiesEditingComponent(org.eclipse.emf.eef.runtime.context.PropertiesEditingContext,
     *      java.lang.String, java.lang.String, java.lang.Class)
     */
    @Override
    @SuppressWarnings("rawtypes")
    public IPropertiesEditionComponent getPropertiesEditingComponent(PropertiesEditingContext editingContext, String mode, String part, java.lang.Class refinement) {
        if (editingContext.getEObject() instanceof Sect3) {
            if (Sect3PropertiesEditionComponent.BASE_PART.equals(part) && refinement == Sect3PropertiesEditionComponent.class) {
                return new Sect3PropertiesEditionComponent(editingContext, editingContext.getEObject(), mode);
            }
        }
        return super.getPropertiesEditingComponent(editingContext, mode, part, refinement);
    }

    /**
     * Provides the filter used by the plugin.xml to assign part forms.
     */
    public static class EditionFilter implements IFilter {

        /**
         * {@inheritDoc}
         *
         * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
         */
        @Override
        public boolean select(Object toTest) {
            EObject eObj = EEFUtils.resolveSemanticObject(toTest);
            return eObj != null && DocbookPackage.Literals.SECT3 == eObj.eClass();
        }

    }

}

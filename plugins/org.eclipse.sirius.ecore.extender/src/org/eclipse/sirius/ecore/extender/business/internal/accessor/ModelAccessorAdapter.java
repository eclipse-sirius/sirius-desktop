/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.accessor;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * An {@link Adapter} to associate a {@link ModelAccessor} to a
 * {@link ResourceSet}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ModelAccessorAdapter implements Adapter {
    private ModelAccessor modelAccessor;

    /**
     * Default constructor.
     * 
     * @param modelAccesor
     *            The {@link ModelAccessor} for which this adapter is created
     */
    protected ModelAccessorAdapter(ModelAccessor modelAccesor) {
        this.modelAccessor = modelAccesor;
    }

    /**
     * Create a new ModelAccessorAdapter with <code>modelAccessor</code>, add it
     * to the <code>resourceSet</code> and return it for convenience.
     * 
     * @param resourceSet
     *            The resource set on which to add the new ModelAccessorAdapter
     * @param modelAccessor
     *            the ModelAccessor to reference from the new
     *            ModelAccessorAdapter.
     */
    public static void addAdapter(ResourceSet resourceSet, ModelAccessor modelAccessor) {
        ModelAccessorAdapter modelAccessorAdapter = new ModelAccessorAdapter(modelAccessor);
        resourceSet.eAdapters().add(modelAccessorAdapter);
    }

    /**
     * Return the ModelAccessorAdapter associated to this
     * <code>resourceSet</code> if it exists, empty {@link Option} otherwise.
     * 
     * @param resourceSet
     *            The resource set from which to retrieve the
     *            ModelAccessorAdapter
     * @return the associated ModelAccessorAdapter if it exists
     */
    public static Option<ModelAccessorAdapter> getAdapter(ResourceSet resourceSet) {
        for (Adapter adapter : resourceSet.eAdapters()) {
            if (adapter instanceof ModelAccessorAdapter) {
                return Options.newSome((ModelAccessorAdapter) adapter);
            }
        }
        return Options.newNone();
    }

    /**
     * Remove the ModelAccessorAdapter from the list of adapters of this
     * <code>resourceSet</code> if it exists.
     * 
     * @param resourceSet
     *            The resource set from which to remove the ModelAccessorAdapter
     * @return the ModelAccessor of the removed adapter
     */
    public static Option<ModelAccessor> removeAdapter(ResourceSet resourceSet) {
        Option<ModelAccessorAdapter> optionalAdapter = getAdapter(resourceSet);
        if (optionalAdapter.some()) {
            resourceSet.eAdapters().remove(optionalAdapter.get());
            return Options.newSome(optionalAdapter.get().getModelAccessor());
        }
        return Options.newNone();
    }

    /**
     * Remove the ModelAccessorAdapter from the list of adapters of this
     * <code>resourceSet</code> if it exists, and dispose the corresponding
     * {@link ModelAccessor}.
     * 
     * @param resourceSet
     *            The resource set from which to remove the ModelAccessorAdapter
     */
    public static void disposeModelAccessor(ResourceSet resourceSet) {
        Option<ModelAccessor> optionalRemovedAdapter = removeAdapter(resourceSet);
        if (optionalRemovedAdapter.some()) {
            optionalRemovedAdapter.get().dispose();
        }
    }

    /**
     * Return The model accessor of this {@link Adapter}.
     * 
     * @return The model accessor of this {@link Adapter}.
     */
    public ModelAccessor getModelAccessor() {
        return modelAccessor;
    }

    @Override
    public void notifyChanged(Notification notification) {
    }

    @Override
    public Notifier getTarget() {
        return null;
    }

    @Override
    public void setTarget(Notifier target) {
    }

    @Override
    public boolean isAdapterForType(Object type) {
        return false;
    }
}

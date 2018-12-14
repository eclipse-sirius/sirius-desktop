/*******************************************************************************
 * Copyright (c) 2007-2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ModelAccessorAdapter;
import java.util.Optional;

/**
 * Registry keeping track of the model accessors.
 * 
 * @author cbrun
 * 
 */
public class ModelAccessorsRegistry {

    /**
     * The model accessor to use when the resource of an {@link EObject} is
     * <code>null</code>.
     */
    private ModelAccessor nullResourceModelAccessor;

    /**
     * The first created model accessor is used as fallback if no
     * <code>nullResourceModelAccessor</code> is defined.
     */
    private ModelAccessor firstCreatedModelAccessor;

    /**
     * Create a new registry.
     */
    public ModelAccessorsRegistry() {
    }

    /**
     * Sets the default model accessor. This model accessor is used if the
     * resource if the instance is <code>null</code> or if the discriminant
     * resource can not be found.
     * 
     * @param nullResourceModelAccessor
     *            the default model accessor.
     */
    public void setNullResourceModelAccessor(final ModelAccessor nullResourceModelAccessor) {
        this.nullResourceModelAccessor = nullResourceModelAccessor;
    }

    /**
     * return the model accessor corresponding the the given model element.
     * 
     * @param modelElement
     *            Any model element.
     * @return the model accessor corresponding the the given model element.
     */
    public ModelAccessor getModelAccessor(final EObject modelElement) {
        ModelAccessor result = null;
        Resource modelElementResource = null;
        if (modelElement != null) {
            modelElementResource = modelElement.eResource();
        }
        if (modelElement == null || modelElementResource == null || modelElementResource.getResourceSet() == null) {
            if (nullResourceModelAccessor != null) {
                result = nullResourceModelAccessor;
            } else if (firstCreatedModelAccessor != null) {
                result = firstCreatedModelAccessor;
            } else {
                // here we really can't manage something
                throw new RuntimeException(Messages.ModelAccessorsRegistry_noResourceFound);
            }
        } else {
            result = getModelAccessor(modelElementResource.getResourceSet());
        }
        return result;
    }

    /**
     * return the model accessor corresponding the the given model.
     * 
     * @param resourceSet
     *            Any model.
     * @return the model accessor corresponding the the given model.
     */
    public ModelAccessor getModelAccessor(final ResourceSet resourceSet) {
        Optional<ModelAccessorAdapter> modelAccessorAdapter = ModelAccessorAdapter.getAdapter(resourceSet);
        if (modelAccessorAdapter.isPresent()) {
            return modelAccessorAdapter.get().getModelAccessor();
        } else {
            final ModelAccessor newPack = ExtenderService.createModelAccessor(resourceSet);
            if (firstCreatedModelAccessor == null) {
                firstCreatedModelAccessor = newPack;
            }
            newPack.init(resourceSet);
            ModelAccessorAdapter.addAdapter(resourceSet, newPack);
            return newPack;
        }
    }

    /**
     * Clear the {@link ModelAccessor} corresponding to the model element.
     * 
     * @param modelElement
     *            any model element.
     * @param airDescriptionExtension
     *            the discriminant file extension.
     */
    public void disposeModelAccessor(final EObject modelElement, final String airDescriptionExtension) {
        Resource modelElementResource = modelElement.eResource();
        if (modelElementResource != null && modelElementResource.getResourceSet() != null) {
            Optional<ModelAccessor> optionalModelAccesor = ModelAccessorAdapter.removeAdapter(modelElementResource.getResourceSet());
            if (optionalModelAccesor.isPresent()) {
                if (optionalModelAccesor.get().equals(firstCreatedModelAccessor)) {
                    firstCreatedModelAccessor = null;
                }
                optionalModelAccesor.get().dispose();
            }

        }
    }

    /**
     * Clear the whole registry.
     */
    public void dispose() {
        if (firstCreatedModelAccessor != null) {
            firstCreatedModelAccessor.dispose();
            firstCreatedModelAccessor = null;
        }
        if (nullResourceModelAccessor != null) {
            nullResourceModelAccessor.dispose();
            nullResourceModelAccessor = null;
        }
    }
}

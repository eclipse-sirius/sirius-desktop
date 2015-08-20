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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;

/**
 * Registry keeping track of the model accessors.
 * 
 * @author cbrun
 * 
 */
public class ModelAccessorsRegistry {

    private String discriminant;

    private Map<String, ModelAccessor> root2ExPackage = new HashMap<String, ModelAccessor>();

    /**
     * The model accessor to use when the resource of an {@link EObject} is
     * <code>null</code>.
     */
    private ModelAccessor nullResourceModelAccessor;

    /**
     * Create a new registry.
     * 
     * @param discriminantFileExtension
     *            file extension used to discrimine a given resource set.
     */
    public ModelAccessorsRegistry(final String discriminantFileExtension) {
        discriminant = discriminantFileExtension;
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
            } else if (root2ExPackage.size() > 0) {
                result = root2ExPackage.values().iterator().next();
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
        final String uri = getMapKeyFromResource(resourceSet);
        if (!root2ExPackage.containsKey(uri)) {
            final ModelAccessor newPack = ExtenderService.createModelAccessor(resourceSet);
            newPack.init(resourceSet);
            root2ExPackage.put(uri, newPack);
        }
        return root2ExPackage.get(uri);
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
            final String root = getMapKeyFromResource(modelElementResource.getResourceSet());
            if (root2ExPackage.containsKey(root)) {
                root2ExPackage.remove(root);
            }
        }
    }

    /**
     * Clear the whole registry.
     */
    public void dispose() {
        final Iterator<ModelAccessor> it = root2ExPackage.values().iterator();
        while (it.hasNext()) {
            final ModelAccessor access = it.next();
            access.dispose();
        }
        root2ExPackage.clear();
        if (nullResourceModelAccessor != null) {
            nullResourceModelAccessor.dispose();
            nullResourceModelAccessor = null;
        }

    }

    private Resource getDiscriminantResource(final ResourceSet resourceSet) {
        final Iterator<Resource> it = new ArrayList<Resource>(resourceSet.getResources()).iterator();
        while (it.hasNext()) {
            final Resource res = it.next();
            if (res.getURI() != null && res.getURI().fileExtension() != null && res.getURI().fileExtension().equals(discriminant)) {
                return res;
            }
        }
        return null;
    }

    private String getMapKeyFromResource(final ResourceSet resourceSet) {
        String uri = ""; //$NON-NLS-1$
        final Resource airResource = getDiscriminantResource(resourceSet);
        if (airResource != null && airResource.getURI() != null) {
            uri = airResource.getURI().toString();
        }
        return uri;
    }

}

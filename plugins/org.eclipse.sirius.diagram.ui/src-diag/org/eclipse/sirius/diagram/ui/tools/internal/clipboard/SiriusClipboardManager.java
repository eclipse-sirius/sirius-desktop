/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.clipboard;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.description.Group;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This class allows Sirius to handle copy/paste operations between several session/editing domains.
 * 
 * @author mporhel
 * 
 */
public final class SiriusClipboardManager {

    private static final SiriusClipboardManager INSTANCE = new SiriusClipboardManager();

    private TransactionalEditingDomain sourceDomain;

    private Collection<Object> clipboard;

    private SiriusClipboardManager() {
        // Avoid instanciation
    }

    /**
     * Returns the shared instance.
     * 
     * @return the global Sirius clipboard manager.
     */
    public static SiriusClipboardManager getInstance() {
        return INSTANCE;
    }

    /**
     * Remove all references to resources which do not belongs to the given domain semantic resources.
     * 
     * @param targetedDomain
     * 
     * @param targetedDomain
     *            the targeted domain;
     */
    private Collection<Object> getDomainSafeClipboard(TransactionalEditingDomain targetedDomain, Collection<Object> copyOfClipboard) {
        if (copyOfClipboard == null || targetedDomain == null) {
            return copyOfClipboard;
        }

        Collection<Resource> resources = targetedDomain.getResourceSet().getResources();
        boolean keepDescriptions = false;

        for (EObject eObject : Iterables.filter(copyOfClipboard, EObject.class)) {
            removeExternalReferences(eObject, resources, keepDescriptions);
        }
        return copyOfClipboard;
    }

    private void removeExternalReferences(EObject eObject, Collection<Resource> domainResources, boolean keepDescriptions) {
        // 1. Reference to other elements
        for (EReference feature : eObject.eClass().getEAllReferences()) {
            Object value = eObject.eGet(feature);
            if (!feature.isDerived()) {
                if (value instanceof EObject) {
                    EObject target = (EObject) value;
                    if (feature.isContainment()) {
                        removeExternalReferences(target, domainResources, keepDescriptions);
                    } else if (isExternalEObject(target, domainResources, keepDescriptions)) {
                        eObject.eUnset(feature);
                    }
                } else if (value instanceof EList<?>) {
                    EList<?> values = (EList<?>) value;
                    for (EObject target : Lists.newArrayList(Iterables.filter(values, EObject.class))) {
                        if (feature.isContainment()) {
                            removeExternalReferences(target, domainResources, keepDescriptions);
                        } else if (isExternalEObject(target, domainResources, keepDescriptions)) {
                            values.remove(target);
                        }
                    }
                }
            }
        }

        // 2. References through EAnnotations
        if (eObject instanceof EModelElement) {
            for (EAnnotation annotation : ((EModelElement) eObject).getEAnnotations()) {
                removeExternalReferences(annotation, domainResources, keepDescriptions);
            }
        }

        // 3. References through EAnnotation references
        if (eObject instanceof EAnnotation) {
            EList<EObject> references = ((EAnnotation) eObject).getReferences();
            for (EObject target : new ArrayList<EObject>(references)) {
                if (isExternalEObject(target, domainResources, keepDescriptions)) {
                    references.remove(target);
                }
            }
        }
    }

    private boolean isExternalEObject(EObject target, Collection<Resource> domainResources, boolean keepDescriptions) {
        Resource targetResource = target.eResource();
        boolean isExternal = target.eIsProxy() || targetResource != null && !domainResources.contains(targetResource);

        // Check description link.
        if (isExternal && targetResource != null) {
            isExternal = !(targetResource.getContents().iterator().next() instanceof Group);
        }

        return isExternal;
    }

    private Collection<Object> getCopyOfClipboard() {
        if (clipboard != null && !clipboard.isEmpty()) {
            return SiriusCopierHelper.copyAllWithNoUidDuplication(clipboard);
        }
        return null;
    }

    /**
     * Fill the targeted domain clipboard.
     * 
     * If targeted domain and clipboard source domain are different, references to EObjects which do not belongs to the
     * targeted domain will be removed.
     * 
     * Except for references to VSM descriptions.
     * 
     * 
     * @param targetedDomain
     *            the targeted domain.
     */
    public void setDomainClipboard(TransactionalEditingDomain targetedDomain) {
        Collection<Object> copyOfClipboard = getCopyOfClipboard();

        if (targetedDomain != sourceDomain) {
            copyOfClipboard = getDomainSafeClipboard(targetedDomain, copyOfClipboard);
        }
        targetedDomain.setClipboard(copyOfClipboard);
    }

    /**
     * Fill the viewpoint clipboard from the source domain clipboard, and clear it.
     * 
     * @param domain
     *            the source domain.
     */
    public void setSiriusClipboard(TransactionalEditingDomain domain) {
        this.sourceDomain = domain;
        if (sourceDomain != null) {
            this.clipboard = sourceDomain.getClipboard();
            sourceDomain.setClipboard(null);
        } else {
            this.clipboard = null;
        }
    }

    /**
     * Set the viewpoint clipboard with the given collection.
     * 
     * @param copies
     *            a collection of copied objects.
     */
    public void setSiriusClipboard(Collection<Object> copies) {
        this.sourceDomain = null;
        this.clipboard = copies;
    }

    /**
     * Check if there is data to paste.
     * 
     * @return <code>true</code> if there is data to paste.
     */
    public boolean hasPasteData() {
        return clipboard != null && !clipboard.isEmpty();
    }

    /**
     * Dispose the viewpoint clipboard.
     */
    public void dispose() {
        if (this.sourceDomain != null) {
            this.sourceDomain.setClipboard(null);
        }
        this.sourceDomain = null;
        this.clipboard = null;
    }

}

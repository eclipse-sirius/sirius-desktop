/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Provides an EObject which represent the categories defined fir the structural
 * features of an eObject.
 * 
 * @see SiriusToolServices#eefViewCategories(EObject)
 * @see SiriusToolServices#eefViewEStructuralFeatures(EObject)
 * 
 * @author mbats
 */
public class EEFViewCategory implements EObject {
    private EObject eObject;

    private String category;

    /**
     * Default constructor.
     * 
     * @param eObject
     *            The EObject
     * @param category
     *            The category
     */
    public EEFViewCategory(EObject eObject, String category) {
        this.eObject = eObject;
        this.category = category;
    }

    @Override
    public EList<Adapter> eAdapters() {
        return eObject.eAdapters();
    }

    @Override
    public boolean eDeliver() {
        return eObject.eDeliver();
    }

    @Override
    public void eSetDeliver(boolean deliver) {
        eObject.eSetDeliver(deliver);
    }

    @Override
    public void eNotify(Notification notification) {
        eObject.eNotify(notification);
    }

    @Override
    public EClass eClass() {
        return eObject.eClass();
    }

    @Override
    public Resource eResource() {
        return eObject.eResource();
    }

    @Override
    public EObject eContainer() {
        return eObject.eContainer();
    }

    @Override
    public EStructuralFeature eContainingFeature() {
        return eObject.eContainingFeature();
    }

    @Override
    public EReference eContainmentFeature() {
        return eObject.eContainmentFeature();
    }

    @Override
    public EList<EObject> eContents() {
        return eObject.eContents();
    }

    @Override
    public TreeIterator<EObject> eAllContents() {
        return eObject.eAllContents();
    }

    @Override
    public boolean eIsProxy() {
        return eObject.eIsProxy();
    }

    @Override
    public EList<EObject> eCrossReferences() {
        return eObject.eCrossReferences();
    }

    @Override
    public Object eGet(EStructuralFeature feature) {
        return eObject.eGet(feature);
    }

    @Override
    public Object eGet(EStructuralFeature feature, boolean resolve) {
        return eObject.eGet(feature, resolve);
    }

    @Override
    public void eSet(EStructuralFeature feature, Object newValue) {
        eObject.eSet(feature, newValue);
    }

    @Override
    public boolean eIsSet(EStructuralFeature feature) {
        return eObject.eIsSet(feature);
    }

    @Override
    public void eUnset(EStructuralFeature feature) {
        eObject.eUnset(feature);
    }

    @Override
    public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
        return eObject.eInvoke(operation, arguments);
    }

    public String getCategory() {
        return category;
    }

}

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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;

/**
 * Provides an EObject which represent the categories defined for the structural
 * features of an eObject.
 * 
 * @see SiriusToolServices#eefViewCategories(EObject)
 * @see SiriusToolServices#eefViewEStructuralFeatures(EObject)
 * 
 * @author mbats
 */
public class EEFViewCategory implements InternalEObject {
    private InternalEObject eObject;

    private String category;

    /**
     * Default constructor.
     * 
     * @param eObject
     *            The EObject
     * @param category
     *            The category
     */
    public EEFViewCategory(InternalEObject eObject, String category) {
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

    @Override
    public boolean eNotificationRequired() {
        return eObject.eNotificationRequired();
    }

    @Override
    public String eURIFragmentSegment(EStructuralFeature eFeature, EObject object) {
        return eObject.eURIFragmentSegment(eFeature, object);
    }

    @Override
    public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
        return eObject.eObjectForURIFragmentSegment(uriFragmentSegment);
    }

    @Override
    public void eSetClass(EClass eClass) {
        eObject.eSetClass(eClass);
    }

    @Override
    public Setting eSetting(EStructuralFeature feature) {
        return eObject.eSetting(feature);
    }

    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        return eObject.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    @Override
    public int eContainerFeatureID() {
        return eObject.eContainerFeatureID();
    }

    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        return eObject.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    @Override
    public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
        return eObject.eDerivedOperationID(baseOperationID, baseClass);
    }

    @Override
    public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
        return eObject.eSetResource(resource, notifications);
    }

    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain notifications) {
        return eObject.eInverseAdd(otherEnd, featureID, baseClass, notifications);
    }

    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain notifications) {
        return eObject.eInverseRemove(otherEnd, featureID, baseClass, notifications);
    }

    @Override
    public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain notifications) {
        return eObject.eBasicSetContainer(newContainer, newContainerFeatureID, notifications);
    }

    @Override
    public NotificationChain eBasicRemoveFromContainer(NotificationChain notifications) {
        return eObject.eBasicRemoveFromContainer(notifications);
    }

    @Override
    public URI eProxyURI() {
        return eObject.eProxyURI();
    }

    @Override
    public void eSetProxyURI(URI uri) {
        eObject.eSetProxyURI(uri);
    }

    @Override
    public EObject eResolveProxy(InternalEObject proxy) {
        return eObject.eResolveProxy(proxy);
    }

    @Override
    public InternalEObject eInternalContainer() {
        return eObject.eInternalContainer();
    }

    @Override
    public Internal eInternalResource() {
        return eObject.eInternalResource();
    }

    @Override
    public Internal eDirectResource() {
        return eObject.eDirectResource();
    }

    @Override
    public EStore eStore() {
        return eObject.eStore();
    }

    @Override
    public void eSetStore(EStore store) {
        eObject.eSetStore(store);
    }

    @Override
    public Object eGet(EStructuralFeature eFeature, boolean resolve, boolean coreType) {
        return eObject.eGet(eFeature, resolve, coreType);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        return eObject.eGet(featureID, resolve, coreType);
    }

    @Override
    public void eSet(int featureID, Object newValue) {
        eObject.eSet(featureID, newValue);
    }

    @Override
    public void eUnset(int featureID) {
        eObject.eUnset(featureID);
    }

    @Override
    public boolean eIsSet(int featureID) {
        return eObject.eIsSet(featureID);
    }

    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        return eObject.eInvoke(operationID, arguments);
    }

    @Override
    public int hashCode() {
        return eObject.hashCode();
    }
}

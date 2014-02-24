/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Computed Style Description Registry</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl#getComputedStyleDescriptions
 * <em>Computed Style Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl#getCache
 * <em>Cache</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComputedStyleDescriptionRegistryImpl extends EObjectImpl implements ComputedStyleDescriptionRegistry {
    /**
     * The cached value of the '{@link #getComputedStyleDescriptions()
     * <em>Computed Style Descriptions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getComputedStyleDescriptions()
     * @generated
     * @ordered
     */
    protected EList<StyleDescription> computedStyleDescriptions;

    /**
     * The cached value of the '{@link #getCache() <em>Cache</em>}' map. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCache()
     * @generated
     * @ordered
     */
    protected EMap<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> cache;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ComputedStyleDescriptionRegistryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.COMPUTED_STYLE_DESCRIPTION_REGISTRY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<StyleDescription> getComputedStyleDescriptions() {
        if (computedStyleDescriptions == null) {
            computedStyleDescriptions = new EObjectContainmentEList.Resolving<StyleDescription>(StyleDescription.class, this,
                    DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS);
        }
        return computedStyleDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> getCache() {
        if (cache == null) {
            cache = new EcoreEMap<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>>(DiagramPackage.Literals.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT,
                    DiagramElementMapping2ModelElementImpl.class, this, DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE);
        }
        return cache;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS:
            return ((InternalEList<?>) getComputedStyleDescriptions()).basicRemove(otherEnd, msgs);
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE:
            return ((InternalEList<?>) getCache()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS:
            return getComputedStyleDescriptions();
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE:
            if (coreType)
                return getCache();
            else
                return getCache().map();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS:
            getComputedStyleDescriptions().clear();
            getComputedStyleDescriptions().addAll((Collection<? extends StyleDescription>) newValue);
            return;
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE:
            ((EStructuralFeature.Setting) getCache()).set(newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS:
            getComputedStyleDescriptions().clear();
            return;
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE:
            getCache().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS:
            return computedStyleDescriptions != null && !computedStyleDescriptions.isEmpty();
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE:
            return cache != null && !cache.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ComputedStyleDescriptionRegistryImpl

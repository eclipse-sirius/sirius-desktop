/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DRepresentation Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DRepresentationElementImpl#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DRepresentationElementImpl extends MinimalEObjectImpl.Container implements DRepresentationElement {
    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObject target;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = DRepresentationElementImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getSemanticElements()
     * <em>Semantic Elements</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSemanticElements()
     * @generated
     * @ordered
     */
    protected EList<EObject> semanticElements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DRepresentationElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DREPRESENTATION_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET, oldTarget, target));
                }
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTarget(EObject newTarget) {
        EObject oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_ELEMENT__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getSemanticElements() {
        if (semanticElements == null) {
            semanticElements = new EObjectResolvingEList<EObject>(EObject.class, this, ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS);
        }
        return semanticElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void refresh() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Style getStyle() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RepresentationElementMapping getMapping() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case ViewpointPackage.DREPRESENTATION_ELEMENT__NAME:
            return getName();
        case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
            return getSemanticElements();
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
        case ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET:
            setTarget((EObject) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_ELEMENT__NAME:
            setName((String) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
            getSemanticElements().addAll((Collection<? extends EObject>) newValue);
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
        case ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET:
            setTarget((EObject) null);
            return;
        case ViewpointPackage.DREPRESENTATION_ELEMENT__NAME:
            setName(DRepresentationElementImpl.NAME_EDEFAULT);
            return;
        case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
            getSemanticElements().clear();
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
        case ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET:
            return target != null;
        case ViewpointPackage.DREPRESENTATION_ELEMENT__NAME:
            return DRepresentationElementImpl.NAME_EDEFAULT == null ? name != null : !DRepresentationElementImpl.NAME_EDEFAULT.equals(name);
        case ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS:
            return semanticElements != null && !semanticElements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == DStylizable.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DRefreshable.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DSemanticDecorator.class) {
            switch (derivedFeatureID) {
            case ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET:
                return ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == DStylizable.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DRefreshable.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == DSemanticDecorator.class) {
            switch (baseFeatureID) {
            case ViewpointPackage.DSEMANTIC_DECORATOR__TARGET:
                return ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // DRepresentationElementImpl

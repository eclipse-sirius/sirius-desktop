/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.component.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tests.sample.component.ComponentPackage;
import org.eclipse.sirius.tests.sample.component.util.PayloadMarkerAdapter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#isPayload <em>Payload</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends MinimalEObjectImpl.Container implements Component {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isPayload() <em>Payload</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPayload()
     * @generated
     * @ordered
     */
    protected static final boolean PAYLOAD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPayload() <em>Payload</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPayload()
     * @generated
     * @ordered
     */
    protected boolean payload = PAYLOAD_EDEFAULT;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<Component> children;

    /**
     * The cached value of the '{@link #getReferences() <em>References</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferences()
     * @generated
     * @ordered
     */
    protected EList<Component> references;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.COMPONENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isPayload() {
        return payload;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setPayload(boolean newPayload) {
        boolean oldPayload = payload;
        payload = newPayload;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__PAYLOAD, oldPayload, payload));
        if (!oldPayload && payload && !PayloadMarkerAdapter.isPayload(this)) {
            eAdapters().add(PayloadMarkerAdapter.INSTANCE);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList<Component> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList<Component>(Component.class, this, ComponentPackage.COMPONENT__CHILDREN);
        }
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess((Setting) children);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public EList<Component> getReferences() {
        if (references == null) {
            references = new EObjectResolvingEList<Component>(Component.class, this, ComponentPackage.COMPONENT__REFERENCES);
        }
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess((Setting) references);
        }
        return references;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.COMPONENT__CHILDREN:
                return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.COMPONENT__NAME:
                return getName();
            case ComponentPackage.COMPONENT__PAYLOAD:
                return isPayload();
            case ComponentPackage.COMPONENT__CHILDREN:
                return getChildren();
            case ComponentPackage.COMPONENT__REFERENCES:
                return getReferences();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ComponentPackage.COMPONENT__NAME:
                setName((String)newValue);
                return;
            case ComponentPackage.COMPONENT__PAYLOAD:
                setPayload((Boolean)newValue);
                return;
            case ComponentPackage.COMPONENT__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection<? extends Component>)newValue);
                return;
            case ComponentPackage.COMPONENT__REFERENCES:
                getReferences().clear();
                getReferences().addAll((Collection<? extends Component>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ComponentPackage.COMPONENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ComponentPackage.COMPONENT__PAYLOAD:
                setPayload(PAYLOAD_EDEFAULT);
                return;
            case ComponentPackage.COMPONENT__CHILDREN:
                getChildren().clear();
                return;
            case ComponentPackage.COMPONENT__REFERENCES:
                getReferences().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ComponentPackage.COMPONENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ComponentPackage.COMPONENT__PAYLOAD:
                return payload != PAYLOAD_EDEFAULT;
            case ComponentPackage.COMPONENT__CHILDREN:
                return children != null && !children.isEmpty();
            case ComponentPackage.COMPONENT__REFERENCES:
                return references != null && !references.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", payload: ");
        result.append(payload);
        result.append(')');
        return result.toString();
    }

} //ComponentImpl

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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tests.sample.component.ComponentPackage;
import org.eclipse.sirius.tests.sample.component.util.PayloadMarkerAdapter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#isPayload
 * <em>Payload</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getChildren
 * <em>Children</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getReferences
 * <em>References</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getReference
 * <em>Reference</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getOpposites
 * <em>Opposites</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.component.impl.ComponentImpl#getReferences2
 * <em>References2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends MinimalEObjectImpl.Container implements Component {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isPayload() <em>Payload</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isPayload()
     * @generated
     * @ordered
     */
    protected static final boolean PAYLOAD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPayload() <em>Payload</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isPayload()
     * @generated
     * @ordered
     */
    protected boolean payload = PAYLOAD_EDEFAULT;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<Component> children;

    /**
     * The cached value of the '{@link #getReferences() <em>References</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReferences()
     * @generated
     * @ordered
     */
    protected EList<Component> references;

    /**
     * The cached value of the '{@link #getReference() <em>Reference</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReference()
     * @generated
     * @ordered
     */
    protected Component reference;

    /**
     * The cached value of the '{@link #getOpposites() <em>Opposites</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOpposites()
     * @generated
     * @ordered
     */
    protected EList<Component> opposites;

    /**
     * The cached value of the '{@link #getReferences2() <em>References2</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReferences2()
     * @generated
     * @ordered
     */
    protected EList<Component> references2;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ComponentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.COMPONENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public String getName() {
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess(eSetting(ComponentPackage.Literals.COMPONENT__NAME));
        }

        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void setName(String newName) {
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess(eSetting(ComponentPackage.Literals.COMPONENT__NAME));
        }

        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isPayload() {
        return payload;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<Component> getReferences() {
        if (references == null) {
            references = new EObjectWithInverseResolvingEList.ManyInverse<Component>(Component.class, this, ComponentPackage.COMPONENT__REFERENCES, ComponentPackage.COMPONENT__OPPOSITES);
        }
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess((Setting) references);
        }
        return references;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public Component getReference() {
        if (reference != null && reference.eIsProxy()) {
            InternalEObject oldReference = (InternalEObject) reference;
            reference = (Component) eResolveProxy(oldReference);
            if (reference != oldReference) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.COMPONENT__REFERENCE, oldReference, reference));
            }
        }

        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess(eSetting(ComponentPackage.Literals.COMPONENT__REFERENCE));
        }
        return reference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Component basicGetReference() {
        return reference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void setReference(Component newReference) {
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess(eSetting(ComponentPackage.Literals.COMPONENT__REFERENCE));
        }

        Component oldReference = reference;
        reference = newReference;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT__REFERENCE, oldReference, reference));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<Component> getOpposites() {
        if (opposites == null) {
            opposites = new EObjectWithInverseResolvingEList.ManyInverse<Component>(Component.class, this, ComponentPackage.COMPONENT__OPPOSITES, ComponentPackage.COMPONENT__REFERENCES);
        }
        if (PayloadMarkerAdapter.isPayload(this)) {
            PayloadMarkerAdapter pma = PayloadMarkerAdapter.getPayloadMarker(this);
            pma.logAccess((Setting) opposites);
        }
        return opposites;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Component> getReferences2() {
        if (references2 == null) {
            references2 = new EObjectResolvingEList<Component>(Component.class, this, ComponentPackage.COMPONENT__REFERENCES2);
        }
        return references2;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ComponentPackage.COMPONENT__REFERENCES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getReferences()).basicAdd(otherEnd, msgs);
        case ComponentPackage.COMPONENT__OPPOSITES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOpposites()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ComponentPackage.COMPONENT__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
        case ComponentPackage.COMPONENT__REFERENCES:
            return ((InternalEList<?>) getReferences()).basicRemove(otherEnd, msgs);
        case ComponentPackage.COMPONENT__OPPOSITES:
            return ((InternalEList<?>) getOpposites()).basicRemove(otherEnd, msgs);
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
        case ComponentPackage.COMPONENT__NAME:
            return getName();
        case ComponentPackage.COMPONENT__PAYLOAD:
            return isPayload();
        case ComponentPackage.COMPONENT__CHILDREN:
            return getChildren();
        case ComponentPackage.COMPONENT__REFERENCES:
            return getReferences();
        case ComponentPackage.COMPONENT__REFERENCE:
            if (resolve)
                return getReference();
            return basicGetReference();
        case ComponentPackage.COMPONENT__OPPOSITES:
            return getOpposites();
        case ComponentPackage.COMPONENT__REFERENCES2:
            return getReferences2();
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
        case ComponentPackage.COMPONENT__NAME:
            setName((String) newValue);
            return;
        case ComponentPackage.COMPONENT__PAYLOAD:
            setPayload((Boolean) newValue);
            return;
        case ComponentPackage.COMPONENT__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends Component>) newValue);
            return;
        case ComponentPackage.COMPONENT__REFERENCES:
            getReferences().clear();
            getReferences().addAll((Collection<? extends Component>) newValue);
            return;
        case ComponentPackage.COMPONENT__REFERENCE:
            setReference((Component) newValue);
            return;
        case ComponentPackage.COMPONENT__OPPOSITES:
            getOpposites().clear();
            getOpposites().addAll((Collection<? extends Component>) newValue);
            return;
        case ComponentPackage.COMPONENT__REFERENCES2:
            getReferences2().clear();
            getReferences2().addAll((Collection<? extends Component>) newValue);
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
        case ComponentPackage.COMPONENT__REFERENCE:
            setReference((Component) null);
            return;
        case ComponentPackage.COMPONENT__OPPOSITES:
            getOpposites().clear();
            return;
        case ComponentPackage.COMPONENT__REFERENCES2:
            getReferences2().clear();
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
        case ComponentPackage.COMPONENT__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case ComponentPackage.COMPONENT__PAYLOAD:
            return payload != PAYLOAD_EDEFAULT;
        case ComponentPackage.COMPONENT__CHILDREN:
            return children != null && !children.isEmpty();
        case ComponentPackage.COMPONENT__REFERENCES:
            return references != null && !references.isEmpty();
        case ComponentPackage.COMPONENT__REFERENCE:
            return reference != null;
        case ComponentPackage.COMPONENT__OPPOSITES:
            return opposites != null && !opposites.isEmpty();
        case ComponentPackage.COMPONENT__REFERENCES2:
            return references2 != null && !references2.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", payload: "); //$NON-NLS-1$
        result.append(payload);
        result.append(')');
        return result.toString();
    }

} // ComponentImpl

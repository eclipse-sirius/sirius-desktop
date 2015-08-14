/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Representation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl#getMappingId
 * <em>Mapping Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl#getLayout
 * <em>Layout</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl#isDisplayed
 * <em>Displayed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl#isHidden
 * <em>Hidden</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl#isPinned
 * <em>Pinned</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractRepresentationImpl extends EObjectImpl implements AbstractRepresentation {
    /**
     * The default value of the '{@link #getMappingId() <em>Mapping Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMappingId()
     * @generated
     * @ordered
     */
    protected static final String MAPPING_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMappingId() <em>Mapping Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMappingId()
     * @generated
     * @ordered
     */
    protected String mappingId = AbstractRepresentationImpl.MAPPING_ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getLayout() <em>Layout</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected Layout layout;

    /**
     * The default value of the '{@link #isDisplayed() <em>Displayed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isDisplayed()
     * @generated
     * @ordered
     */
    protected static final boolean DISPLAYED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isDisplayed() <em>Displayed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isDisplayed()
     * @generated
     * @ordered
     */
    protected boolean displayed = AbstractRepresentationImpl.DISPLAYED_EDEFAULT;

    /**
     * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isHidden()
     * @generated
     * @ordered
     */
    protected static final boolean HIDDEN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isHidden()
     * @generated
     * @ordered
     */
    protected boolean hidden = AbstractRepresentationImpl.HIDDEN_EDEFAULT;

    /**
     * The default value of the '{@link #isPinned() <em>Pinned</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isPinned()
     * @generated
     * @ordered
     */
    protected static final boolean PINNED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isPinned() <em>Pinned</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isPinned()
     * @generated
     * @ordered
     */
    protected boolean pinned = AbstractRepresentationImpl.PINNED_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractRepresentationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.ABSTRACT_REPRESENTATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getMappingId() {
        return mappingId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMappingId(String newMappingId) {
        String oldMappingId = mappingId;
        mappingId = newMappingId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID, oldMappingId, mappingId));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Layout getLayout() {
        return layout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLayout(Layout newLayout, NotificationChain msgs) {
        Layout oldLayout = layout;
        layout = newLayout;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT, oldLayout, newLayout);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLayout(Layout newLayout) {
        if (newLayout != layout) {
            NotificationChain msgs = null;
            if (layout != null) {
                msgs = ((InternalEObject) layout).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT, null, msgs);
            }
            if (newLayout != null) {
                msgs = ((InternalEObject) newLayout).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT, null, msgs);
            }
            msgs = basicSetLayout(newLayout, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT, newLayout, newLayout));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDisplayed(boolean newDisplayed) {
        boolean oldDisplayed = displayed;
        displayed = newDisplayed;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED, oldDisplayed, displayed));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isHidden() {
        return hidden;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHidden(boolean newHidden) {
        boolean oldHidden = hidden;
        hidden = newHidden;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN, oldHidden, hidden));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isPinned() {
        return pinned;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPinned(boolean newPinned) {
        boolean oldPinned = pinned;
        pinned = newPinned;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED, oldPinned, pinned));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT:
            return basicSetLayout(null, msgs);
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
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID:
            return getMappingId();
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT:
            return getLayout();
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED:
            return isDisplayed();
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN:
            return isHidden();
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED:
            return isPinned();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID:
            setMappingId((String) newValue);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT:
            setLayout((Layout) newValue);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED:
            setDisplayed((Boolean) newValue);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN:
            setHidden((Boolean) newValue);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED:
            setPinned((Boolean) newValue);
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
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID:
            setMappingId(AbstractRepresentationImpl.MAPPING_ID_EDEFAULT);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT:
            setLayout((Layout) null);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED:
            setDisplayed(AbstractRepresentationImpl.DISPLAYED_EDEFAULT);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN:
            setHidden(AbstractRepresentationImpl.HIDDEN_EDEFAULT);
            return;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED:
            setPinned(AbstractRepresentationImpl.PINNED_EDEFAULT);
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
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID:
            return AbstractRepresentationImpl.MAPPING_ID_EDEFAULT == null ? mappingId != null : !AbstractRepresentationImpl.MAPPING_ID_EDEFAULT.equals(mappingId);
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT:
            return layout != null;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED:
            return displayed != AbstractRepresentationImpl.DISPLAYED_EDEFAULT;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN:
            return hidden != AbstractRepresentationImpl.HIDDEN_EDEFAULT;
        case MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED:
            return pinned != AbstractRepresentationImpl.PINNED_EDEFAULT;
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
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (mappingId: "); //$NON-NLS-1$
        result.append(mappingId);
        result.append(", displayed: "); //$NON-NLS-1$
        result.append(displayed);
        result.append(", hidden: "); //$NON-NLS-1$
        result.append(hidden);
        result.append(", pinned: "); //$NON-NLS-1$
        result.append(pinned);
        result.append(')');
        return result.toString();
    }

} // AbstractRepresentationImpl

/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.formatdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.FormatdataPackage;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Abstract Format Data</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl#getSiriusStyle <em>Sirius
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl#getGmfView <em>Gmf View</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractFormatDataImpl extends EObjectImpl implements AbstractFormatData {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = AbstractFormatDataImpl.ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected NodeFormatData label;

    /**
     * The cached value of the '{@link #getSiriusStyle() <em>Sirius Style</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSiriusStyle()
     * @generated
     * @ordered
     */
    protected Style siriusStyle;

    /**
     * The cached value of the '{@link #getGmfView() <em>Gmf View</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getGmfView()
     * @generated
     * @ordered
     */
    protected View gmfView;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractFormatDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FormatdataPackage.Literals.ABSTRACT_FORMAT_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__ID, oldId, id));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeFormatData getLabel() {
        if (label != null && label.eIsProxy()) {
            InternalEObject oldLabel = (InternalEObject) label;
            label = (NodeFormatData) eResolveProxy(oldLabel);
            if (label != oldLabel) {
                InternalEObject newLabel = (InternalEObject) label;
                NotificationChain msgs = oldLabel.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, null, null);
                if (newLabel.eInternalContainer() == null) {
                    msgs = newLabel.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, oldLabel, label));
                }
            }
        }
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NodeFormatData basicGetLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLabel(NodeFormatData newLabel, NotificationChain msgs) {
        NodeFormatData oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, oldLabel, newLabel);
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
    public void setLabel(NodeFormatData newLabel) {
        if (newLabel != label) {
            NotificationChain msgs = null;
            if (label != null) {
                msgs = ((InternalEObject) label).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, null, msgs);
            }
            if (newLabel != null) {
                msgs = ((InternalEObject) newLabel).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, null, msgs);
            }
            msgs = basicSetLabel(newLabel, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL, newLabel, newLabel));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Style getSiriusStyle() {
        if (siriusStyle != null && siriusStyle.eIsProxy()) {
            InternalEObject oldSiriusStyle = (InternalEObject) siriusStyle;
            siriusStyle = (Style) eResolveProxy(oldSiriusStyle);
            if (siriusStyle != oldSiriusStyle) {
                InternalEObject newSiriusStyle = (InternalEObject) siriusStyle;
                NotificationChain msgs = oldSiriusStyle.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, null, null);
                if (newSiriusStyle.eInternalContainer() == null) {
                    msgs = newSiriusStyle.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, oldSiriusStyle, siriusStyle));
                }
            }
        }
        return siriusStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Style basicGetSiriusStyle() {
        return siriusStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSiriusStyle(Style newSiriusStyle, NotificationChain msgs) {
        Style oldSiriusStyle = siriusStyle;
        siriusStyle = newSiriusStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, oldSiriusStyle, newSiriusStyle);
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
    public void setSiriusStyle(Style newSiriusStyle) {
        if (newSiriusStyle != siriusStyle) {
            NotificationChain msgs = null;
            if (siriusStyle != null) {
                msgs = ((InternalEObject) siriusStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, null, msgs);
            }
            if (newSiriusStyle != null) {
                msgs = ((InternalEObject) newSiriusStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, null, msgs);
            }
            msgs = basicSetSiriusStyle(newSiriusStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE, newSiriusStyle, newSiriusStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public View getGmfView() {
        if (gmfView != null && gmfView.eIsProxy()) {
            InternalEObject oldGmfView = (InternalEObject) gmfView;
            gmfView = (View) eResolveProxy(oldGmfView);
            if (gmfView != oldGmfView) {
                InternalEObject newGmfView = (InternalEObject) gmfView;
                NotificationChain msgs = oldGmfView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, null, null);
                if (newGmfView.eInternalContainer() == null) {
                    msgs = newGmfView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, oldGmfView, gmfView));
                }
            }
        }
        return gmfView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public View basicGetGmfView() {
        return gmfView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetGmfView(View newGmfView, NotificationChain msgs) {
        View oldGmfView = gmfView;
        gmfView = newGmfView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, oldGmfView, newGmfView);
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
    public void setGmfView(View newGmfView) {
        if (newGmfView != gmfView) {
            NotificationChain msgs = null;
            if (gmfView != null) {
                msgs = ((InternalEObject) gmfView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, null, msgs);
            }
            if (newGmfView != null) {
                msgs = ((InternalEObject) newGmfView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, null, msgs);
            }
            msgs = basicSetGmfView(newGmfView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW, newGmfView, newGmfView));
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
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL:
            return basicSetLabel(null, msgs);
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE:
            return basicSetSiriusStyle(null, msgs);
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW:
            return basicSetGmfView(null, msgs);
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
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__ID:
            return getId();
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL:
            if (resolve) {
                return getLabel();
            }
            return basicGetLabel();
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE:
            if (resolve) {
                return getSiriusStyle();
            }
            return basicGetSiriusStyle();
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW:
            if (resolve) {
                return getGmfView();
            }
            return basicGetGmfView();
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
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__ID:
            setId((String) newValue);
            return;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL:
            setLabel((NodeFormatData) newValue);
            return;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE:
            setSiriusStyle((Style) newValue);
            return;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW:
            setGmfView((View) newValue);
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
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__ID:
            setId(AbstractFormatDataImpl.ID_EDEFAULT);
            return;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL:
            setLabel((NodeFormatData) null);
            return;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE:
            setSiriusStyle((Style) null);
            return;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW:
            setGmfView((View) null);
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
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__ID:
            return AbstractFormatDataImpl.ID_EDEFAULT == null ? id != null : !AbstractFormatDataImpl.ID_EDEFAULT.equals(id);
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL:
            return label != null;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE:
            return siriusStyle != null;
        case FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW:
            return gmfView != null;
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(')');
        return result.toString();
    }

} // AbstractFormatDataImpl

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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.FormatdataPackage;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Node Format Data</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl#getWidth <em>Width</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl#getHeight <em>Height</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl#getChildren <em>Children</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl#getOutgoingEdges <em>Outgoing
 * Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeFormatDataImpl extends AbstractFormatDataImpl implements NodeFormatData {
    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = -2;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = NodeFormatDataImpl.WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = -2;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = NodeFormatDataImpl.HEIGHT_EDEFAULT;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<NodeFormatData> children;

    /**
     * The cached value of the '{@link #getOutgoingEdges() <em>Outgoing Edges</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOutgoingEdges()
     * @generated
     * @ordered
     */
    protected EList<EdgeFormatData> outgoingEdges;

    /**
     * The cached value of the '{@link #getLocation() <em>Location</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getLocation()
     * @generated
     * @ordered
     */
    protected Point location;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeFormatDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FormatdataPackage.Literals.NODE_FORMAT_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.NODE_FORMAT_DATA__WIDTH, oldWidth, width));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHeight(int newHeight) {
        int oldHeight = height;
        height = newHeight;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.NODE_FORMAT_DATA__HEIGHT, oldHeight, height));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeFormatData> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList.Resolving<NodeFormatData>(NodeFormatData.class, this, FormatdataPackage.NODE_FORMAT_DATA__CHILDREN);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeFormatData> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new EObjectContainmentEList.Resolving<EdgeFormatData>(EdgeFormatData.class, this, FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES);
        }
        return outgoingEdges;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Point getLocation() {
        if (location != null && location.eIsProxy()) {
            InternalEObject oldLocation = (InternalEObject) location;
            location = (Point) eResolveProxy(oldLocation);
            if (location != oldLocation) {
                InternalEObject newLocation = (InternalEObject) location;
                NotificationChain msgs = oldLocation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.NODE_FORMAT_DATA__LOCATION, null, null);
                if (newLocation.eInternalContainer() == null) {
                    msgs = newLocation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.NODE_FORMAT_DATA__LOCATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatdataPackage.NODE_FORMAT_DATA__LOCATION, oldLocation, location));
                }
            }
        }
        return location;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Point basicGetLocation() {
        return location;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLocation(Point newLocation, NotificationChain msgs) {
        Point oldLocation = location;
        location = newLocation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatdataPackage.NODE_FORMAT_DATA__LOCATION, oldLocation, newLocation);
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
    public void setLocation(Point newLocation) {
        if (newLocation != location) {
            NotificationChain msgs = null;
            if (location != null) {
                msgs = ((InternalEObject) location).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.NODE_FORMAT_DATA__LOCATION, null, msgs);
            }
            if (newLocation != null) {
                msgs = ((InternalEObject) newLocation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - FormatdataPackage.NODE_FORMAT_DATA__LOCATION, null, msgs);
            }
            msgs = basicSetLocation(newLocation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FormatdataPackage.NODE_FORMAT_DATA__LOCATION, newLocation, newLocation));
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
        case FormatdataPackage.NODE_FORMAT_DATA__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
        case FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES:
            return ((InternalEList<?>) getOutgoingEdges()).basicRemove(otherEnd, msgs);
        case FormatdataPackage.NODE_FORMAT_DATA__LOCATION:
            return basicSetLocation(null, msgs);
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
        case FormatdataPackage.NODE_FORMAT_DATA__WIDTH:
            return getWidth();
        case FormatdataPackage.NODE_FORMAT_DATA__HEIGHT:
            return getHeight();
        case FormatdataPackage.NODE_FORMAT_DATA__CHILDREN:
            return getChildren();
        case FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES:
            return getOutgoingEdges();
        case FormatdataPackage.NODE_FORMAT_DATA__LOCATION:
            if (resolve) {
                return getLocation();
            }
            return basicGetLocation();
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
        case FormatdataPackage.NODE_FORMAT_DATA__WIDTH:
            setWidth((Integer) newValue);
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends NodeFormatData>) newValue);
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            getOutgoingEdges().addAll((Collection<? extends EdgeFormatData>) newValue);
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__LOCATION:
            setLocation((Point) newValue);
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
        case FormatdataPackage.NODE_FORMAT_DATA__WIDTH:
            setWidth(NodeFormatDataImpl.WIDTH_EDEFAULT);
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__HEIGHT:
            setHeight(NodeFormatDataImpl.HEIGHT_EDEFAULT);
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__CHILDREN:
            getChildren().clear();
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            return;
        case FormatdataPackage.NODE_FORMAT_DATA__LOCATION:
            setLocation((Point) null);
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
        case FormatdataPackage.NODE_FORMAT_DATA__WIDTH:
            return width != NodeFormatDataImpl.WIDTH_EDEFAULT;
        case FormatdataPackage.NODE_FORMAT_DATA__HEIGHT:
            return height != NodeFormatDataImpl.HEIGHT_EDEFAULT;
        case FormatdataPackage.NODE_FORMAT_DATA__CHILDREN:
            return children != null && !children.isEmpty();
        case FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES:
            return outgoingEdges != null && !outgoingEdges.isEmpty();
        case FormatdataPackage.NODE_FORMAT_DATA__LOCATION:
            return location != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        if (location != null) {
            result.append(" (x: "); //$NON-NLS-1$
            result.append(location.getX());
            result.append(", y: "); //$NON-NLS-1$
            result.append(location.getY());
            result.append(") "); //$NON-NLS-1$
        }

        result.append(" (width: "); //$NON-NLS-1$
        result.append(width);
        result.append(", height: "); //$NON-NLS-1$
        result.append(height);
        result.append(')');
        return result.toString();
    }

} // NodeFormatDataImpl

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
package org.eclipse.sirius.diagram.layoutdata.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.Point;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node Layout Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl#getHeight
 * <em>Height</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl#getChildren
 * <em>Children</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl#getOutgoingEdges
 * <em>Outgoing Edges</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl#getLocation
 * <em>Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeLayoutDataImpl extends AbstractLayoutDataImpl implements NodeLayoutData {
    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = -2;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = -2;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<NodeLayoutData> children;

    /**
     * The cached value of the '{@link #getOutgoingEdges()
     * <em>Outgoing Edges</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOutgoingEdges()
     * @generated
     * @ordered
     */
    protected EList<EdgeLayoutData> outgoingEdges;

    /**
     * The cached value of the '{@link #getLocation() <em>Location</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
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
    protected NodeLayoutDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LayoutdataPackage.Literals.NODE_LAYOUT_DATA;
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
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.NODE_LAYOUT_DATA__WIDTH, oldWidth, width));
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
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.NODE_LAYOUT_DATA__HEIGHT, oldHeight, height));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<NodeLayoutData> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList.Resolving<NodeLayoutData>(NodeLayoutData.class, this, LayoutdataPackage.NODE_LAYOUT_DATA__CHILDREN);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EdgeLayoutData> getOutgoingEdges() {
        if (outgoingEdges == null) {
            outgoingEdges = new EObjectContainmentEList.Resolving<EdgeLayoutData>(EdgeLayoutData.class, this, LayoutdataPackage.NODE_LAYOUT_DATA__OUTGOING_EDGES);
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
                NotificationChain msgs = oldLocation.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, null, null);
                if (newLocation.eInternalContainer() == null) {
                    msgs = newLocation.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, oldLocation, location));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, oldLocation, newLocation);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
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
            if (location != null)
                msgs = ((InternalEObject) location).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, null, msgs);
            if (newLocation != null)
                msgs = ((InternalEObject) newLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, null, msgs);
            msgs = basicSetLocation(newLocation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION, newLocation, newLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case LayoutdataPackage.NODE_LAYOUT_DATA__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
        case LayoutdataPackage.NODE_LAYOUT_DATA__OUTGOING_EDGES:
            return ((InternalEList<?>) getOutgoingEdges()).basicRemove(otherEnd, msgs);
        case LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION:
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
        case LayoutdataPackage.NODE_LAYOUT_DATA__WIDTH:
            return getWidth();
        case LayoutdataPackage.NODE_LAYOUT_DATA__HEIGHT:
            return getHeight();
        case LayoutdataPackage.NODE_LAYOUT_DATA__CHILDREN:
            return getChildren();
        case LayoutdataPackage.NODE_LAYOUT_DATA__OUTGOING_EDGES:
            return getOutgoingEdges();
        case LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION:
            if (resolve)
                return getLocation();
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
        case LayoutdataPackage.NODE_LAYOUT_DATA__WIDTH:
            setWidth((Integer) newValue);
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends NodeLayoutData>) newValue);
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            getOutgoingEdges().addAll((Collection<? extends EdgeLayoutData>) newValue);
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION:
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
        case LayoutdataPackage.NODE_LAYOUT_DATA__WIDTH:
            setWidth(WIDTH_EDEFAULT);
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__HEIGHT:
            setHeight(HEIGHT_EDEFAULT);
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__CHILDREN:
            getChildren().clear();
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__OUTGOING_EDGES:
            getOutgoingEdges().clear();
            return;
        case LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION:
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
        case LayoutdataPackage.NODE_LAYOUT_DATA__WIDTH:
            return width != WIDTH_EDEFAULT;
        case LayoutdataPackage.NODE_LAYOUT_DATA__HEIGHT:
            return height != HEIGHT_EDEFAULT;
        case LayoutdataPackage.NODE_LAYOUT_DATA__CHILDREN:
            return children != null && !children.isEmpty();
        case LayoutdataPackage.NODE_LAYOUT_DATA__OUTGOING_EDGES:
            return outgoingEdges != null && !outgoingEdges.isEmpty();
        case LayoutdataPackage.NODE_LAYOUT_DATA__LOCATION:
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

} // NodeLayoutDataImpl

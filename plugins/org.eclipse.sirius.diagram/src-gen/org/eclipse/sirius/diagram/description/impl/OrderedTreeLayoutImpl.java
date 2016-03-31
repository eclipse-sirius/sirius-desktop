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
package org.eclipse.sirius.diagram.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ordered Tree Layout</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.OrderedTreeLayoutImpl#getChildrenExpression
 * <em>Children Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.OrderedTreeLayoutImpl#getNodeMapping
 * <em>Node Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrderedTreeLayoutImpl extends DocumentedElementImpl implements OrderedTreeLayout {
    /**
     * The default value of the '{@link #getChildrenExpression()
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getChildrenExpression()
     * @generated
     * @ordered
     */
    protected static final String CHILDREN_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getChildrenExpression()
     * <em>Children Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getChildrenExpression()
     * @generated
     * @ordered
     */
    protected String childrenExpression = OrderedTreeLayoutImpl.CHILDREN_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getNodeMapping() <em>Node Mapping</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNodeMapping()
     * @generated
     * @ordered
     */
    protected EList<AbstractNodeMapping> nodeMapping;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected OrderedTreeLayoutImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ORDERED_TREE_LAYOUT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getChildrenExpression() {
        return childrenExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setChildrenExpression(String newChildrenExpression) {
        String oldChildrenExpression = childrenExpression;
        childrenExpression = newChildrenExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION, oldChildrenExpression, childrenExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractNodeMapping> getNodeMapping() {
        if (nodeMapping == null) {
            nodeMapping = new EObjectResolvingEList<AbstractNodeMapping>(AbstractNodeMapping.class, this, DescriptionPackage.ORDERED_TREE_LAYOUT__NODE_MAPPING);
        }
        return nodeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION:
            return getChildrenExpression();
        case DescriptionPackage.ORDERED_TREE_LAYOUT__NODE_MAPPING:
            return getNodeMapping();
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
        case DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION:
            setChildrenExpression((String) newValue);
            return;
        case DescriptionPackage.ORDERED_TREE_LAYOUT__NODE_MAPPING:
            getNodeMapping().clear();
            getNodeMapping().addAll((Collection<? extends AbstractNodeMapping>) newValue);
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
        case DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION:
            setChildrenExpression(OrderedTreeLayoutImpl.CHILDREN_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.ORDERED_TREE_LAYOUT__NODE_MAPPING:
            getNodeMapping().clear();
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
        case DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION:
            return OrderedTreeLayoutImpl.CHILDREN_EXPRESSION_EDEFAULT == null ? childrenExpression != null : !OrderedTreeLayoutImpl.CHILDREN_EXPRESSION_EDEFAULT.equals(childrenExpression);
        case DescriptionPackage.ORDERED_TREE_LAYOUT__NODE_MAPPING:
            return nodeMapping != null && !nodeMapping.isEmpty();
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
        result.append(" (childrenExpression: "); //$NON-NLS-1$
        result.append(childrenExpression);
        result.append(')');
        return result.toString();
    }

} // OrderedTreeLayoutImpl

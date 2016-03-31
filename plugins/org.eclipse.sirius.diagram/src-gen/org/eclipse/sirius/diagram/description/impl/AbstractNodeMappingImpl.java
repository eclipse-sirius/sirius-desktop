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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Node Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl#getBorderedNodeMappings
 * <em>Bordered Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl#getReusedBorderedNodeMappings
 * <em>Reused Bordered Node Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractNodeMappingImpl extends DiagramElementMappingImpl implements AbstractNodeMapping {
    /**
     * The default value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = AbstractNodeMappingImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getDomainClass() <em>Domain Class</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected static final String DOMAIN_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDomainClass() <em>Domain Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected String domainClass = AbstractNodeMappingImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderedNodeMappings()
     * <em>Bordered Node Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderedNodeMappings()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> borderedNodeMappings;

    /**
     * The cached value of the '{@link #getReusedBorderedNodeMappings()
     * <em>Reused Bordered Node Mappings</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReusedBorderedNodeMappings()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> reusedBorderedNodeMappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractNodeMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION, oldDocumentation, documentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDomainClass() {
        return domainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDomainClass(String newDomainClass) {
        String oldDomainClass = domainClass;
        domainClass = newDomainClass;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getBorderedNodeMappings() {
        if (borderedNodeMappings == null) {
            borderedNodeMappings = new EObjectContainmentEList.Resolving<NodeMapping>(NodeMapping.class, this, DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS);
        }
        return borderedNodeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getReusedBorderedNodeMappings() {
        if (reusedBorderedNodeMappings == null) {
            reusedBorderedNodeMappings = new EObjectResolvingEList<NodeMapping>(NodeMapping.class, this, DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS);
        }
        return reusedBorderedNodeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DDiagramElement> findDNodeFromEObject(EObject eObject) {
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
    public void clearDNodesDone() {
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
    public void addDoneNode(DSemanticDecorator node) {
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
    public EList<NodeMapping> getAllBorderedNodeMappings() {
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
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS:
            return ((InternalEList<?>) getBorderedNodeMappings()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION:
            return getDocumentation();
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS:
            return getBorderedNodeMappings();
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS:
            return getReusedBorderedNodeMappings();
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
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS:
            getBorderedNodeMappings().clear();
            getBorderedNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
            return;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS:
            getReusedBorderedNodeMappings().clear();
            getReusedBorderedNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
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
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION:
            setDocumentation(AbstractNodeMappingImpl.DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS:
            setDomainClass(AbstractNodeMappingImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS:
            getBorderedNodeMappings().clear();
            return;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS:
            getReusedBorderedNodeMappings().clear();
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
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION:
            return AbstractNodeMappingImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !AbstractNodeMappingImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS:
            return AbstractNodeMappingImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !AbstractNodeMappingImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS:
            return borderedNodeMappings != null && !borderedNodeMappings.isEmpty();
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS:
            return reusedBorderedNodeMappings != null && !reusedBorderedNodeMappings.isEmpty();
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
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
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
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(')');
        return result.toString();
    }

} // AbstractNodeMappingImpl

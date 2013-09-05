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
package org.eclipse.sirius.table.metamodel.table.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.ContributionProvider;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableExtensionDescription;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edition Table Extension Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getSiriusURI
 * <em>Sirius URI</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getRepresentationName
 * <em>Representation Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getMetamodel
 * <em>Metamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getContributions
 * <em>Contributions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getOwnedLineMappings
 * <em>Owned Line Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getOwnedColumnMappings
 * <em>Owned Column Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getOwnedTools
 * <em>Owned Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableExtensionDescriptionImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EditionTableExtensionDescriptionImpl extends EObjectImpl implements EditionTableExtensionDescription {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation\n";

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
     * The default value of the '{@link #getSiriusURI()
     * <em>Sirius URI</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSiriusURI()
     * @generated
     * @ordered
     */
    protected static final String VIEWPOINT_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSiriusURI()
     * <em>Sirius URI</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSiriusURI()
     * @generated
     * @ordered
     */
    protected String viewpointURI = VIEWPOINT_URI_EDEFAULT;

    /**
     * The default value of the '{@link #getRepresentationName()
     * <em>Representation Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRepresentationName()
     * @generated
     * @ordered
     */
    protected static final String REPRESENTATION_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepresentationName()
     * <em>Representation Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRepresentationName()
     * @generated
     * @ordered
     */
    protected String representationName = REPRESENTATION_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMetamodel()
     * @generated
     * @ordered
     */
    protected EList<EPackage> metamodel;

    /**
     * The cached value of the '{@link #getContributions()
     * <em>Contributions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getContributions()
     * @generated
     * @ordered
     */
    protected EList<Contribution> contributions;

    /**
     * The cached value of the '{@link #getOwnedLineMappings()
     * <em>Owned Line Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedLineMappings()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> ownedLineMappings;

    /**
     * The cached value of the '{@link #getOwnedColumnMappings()
     * <em>Owned Column Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedColumnMappings()
     * @generated
     * @ordered
     */
    protected EList<FeatureColumnMapping> ownedColumnMappings;

    /**
     * The cached value of the '{@link #getOwnedTools() <em>Owned Tools</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedTools()
     * @generated
     * @ordered
     */
    protected EList<TableTool> ownedTools;

    /**
     * The default value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = PRECONDITION_EXPRESSION_EDEFAULT;

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
    protected String domainClass = DOMAIN_CLASS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EditionTableExtensionDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.EDITION_TABLE_EXTENSION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSiriusURI() {
        return viewpointURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSiriusURI(String newSiriusURI) {
        String oldSiriusURI = viewpointURI;
        viewpointURI = newSiriusURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__VIEWPOINT_URI, oldSiriusURI, viewpointURI));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRepresentationName() {
        return representationName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRepresentationName(String newRepresentationName) {
        String oldRepresentationName = representationName;
        representationName = newRepresentationName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__REPRESENTATION_NAME, oldRepresentationName, representationName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<EPackage> getMetamodel() {
        if (metamodel == null) {
            metamodel = new EObjectResolvingEList<EPackage>(EPackage.class, this, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__METAMODEL);
        }
        return metamodel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Contribution> getContributions() {
        if (contributions == null) {
            contributions = new EObjectContainmentEList.Resolving<Contribution>(Contribution.class, this, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS);
        }
        return contributions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<LineMapping> getOwnedLineMappings() {
        if (ownedLineMappings == null) {
            ownedLineMappings = new EObjectContainmentEList<LineMapping>(LineMapping.class, this, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS);
        }
        return ownedLineMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<FeatureColumnMapping> getOwnedColumnMappings() {
        if (ownedColumnMappings == null) {
            ownedColumnMappings = new EObjectContainmentEList<FeatureColumnMapping>(FeatureColumnMapping.class, this, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS);
        }
        return ownedColumnMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TableTool> getOwnedTools() {
        if (ownedTools == null) {
            ownedTools = new EObjectContainmentEList<TableTool>(TableTool.class, this, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS);
        }
        return ownedTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPreconditionExpression() {
        return preconditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPreconditionExpression(String newPreconditionExpression) {
        String oldPreconditionExpression = preconditionExpression;
        preconditionExpression = newPreconditionExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDomainClass() {
        return domainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDomainClass(String newDomainClass) {
        String oldDomainClass = domainClass;
        domainClass = newDomainClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS:
            return ((InternalEList<?>) getContributions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS:
            return ((InternalEList<?>) getOwnedLineMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return ((InternalEList<?>) getOwnedColumnMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS:
            return ((InternalEList<?>) getOwnedTools()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__NAME:
            return getName();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            return getSiriusURI();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            return getRepresentationName();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__METAMODEL:
            return getMetamodel();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS:
            return getContributions();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS:
            return getOwnedLineMappings();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return getOwnedColumnMappings();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS:
            return getOwnedTools();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
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
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            setSiriusURI((String) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            setRepresentationName((String) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            getMetamodel().addAll((Collection<? extends EPackage>) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS:
            getContributions().clear();
            getContributions().addAll((Collection<? extends Contribution>) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS:
            getOwnedLineMappings().clear();
            getOwnedLineMappings().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            getOwnedColumnMappings().clear();
            getOwnedColumnMappings().addAll((Collection<? extends FeatureColumnMapping>) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS:
            getOwnedTools().clear();
            getOwnedTools().addAll((Collection<? extends TableTool>) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
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
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__NAME:
            setName(NAME_EDEFAULT);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            setSiriusURI(VIEWPOINT_URI_EDEFAULT);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            setRepresentationName(REPRESENTATION_NAME_EDEFAULT);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS:
            getContributions().clear();
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS:
            getOwnedLineMappings().clear();
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            getOwnedColumnMappings().clear();
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS:
            getOwnedTools().clear();
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(DOMAIN_CLASS_EDEFAULT);
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
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            return VIEWPOINT_URI_EDEFAULT == null ? viewpointURI != null : !VIEWPOINT_URI_EDEFAULT.equals(viewpointURI);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            return REPRESENTATION_NAME_EDEFAULT == null ? representationName != null : !REPRESENTATION_NAME_EDEFAULT.equals(representationName);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__METAMODEL:
            return metamodel != null && !metamodel.isEmpty();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS:
            return contributions != null && !contributions.isEmpty();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_LINE_MAPPINGS:
            return ownedLineMappings != null && !ownedLineMappings.isEmpty();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_COLUMN_MAPPINGS:
            return ownedColumnMappings != null && !ownedColumnMappings.isEmpty();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__OWNED_TOOLS:
            return ownedTools != null && !ownedTools.isEmpty();
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__PRECONDITION_EXPRESSION:
            return PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null : !PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__DOMAIN_CLASS:
            return DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !DOMAIN_CLASS_EDEFAULT.equals(domainClass);
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
        if (baseClass == ContributionProvider.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS:
                return ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS;
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
        if (baseClass == ContributionProvider.class) {
            switch (baseFeatureID) {
            case ContributionPackage.CONTRIBUTION_PROVIDER__CONTRIBUTIONS:
                return DescriptionPackage.EDITION_TABLE_EXTENSION_DESCRIPTION__CONTRIBUTIONS;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", viewpointURI: ");
        result.append(viewpointURI);
        result.append(", representationName: ");
        result.append(representationName);
        result.append(", preconditionExpression: ");
        result.append(preconditionExpression);
        result.append(", domainClass: ");
        result.append(domainClass);
        result.append(')');
        return result.toString();
    }

} // EditionTableExtensionDescriptionImpl

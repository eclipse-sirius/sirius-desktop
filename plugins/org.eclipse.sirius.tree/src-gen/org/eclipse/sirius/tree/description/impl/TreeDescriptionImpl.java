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
package org.eclipse.sirius.tree.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemMappingContainer;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tree Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getEndUserDocumentation
 * <em>End User Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getTitleExpression
 * <em>Title Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#isInitialisation
 * <em>Initialisation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getMetamodel
 * <em>Metamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#isShowOnStartup
 * <em>Show On Startup</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getSubItemMappings
 * <em>Sub Item Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getDropTools
 * <em>Drop Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getCreateTreeItem
 * <em>Create Tree Item</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getOwnedRepresentationCreationDescriptions
 * <em>Owned Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeDescriptionImpl#getOwnedRepresentationNavigationDescriptions
 * <em>Owned Representation Navigation Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeDescriptionImpl extends DocumentedElementImpl implements TreeDescription {
    /**
     * The default value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected static final String END_USER_DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected String endUserDocumentation = TreeDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT;

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
    protected String name = TreeDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = TreeDescriptionImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getTitleExpression()
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTitleExpression()
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected String titleExpression = TreeDescriptionImpl.TITLE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isInitialisation()
     * <em>Initialisation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isInitialisation()
     * @generated
     * @ordered
     */
    protected static final boolean INITIALISATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInitialisation()
     * <em>Initialisation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isInitialisation()
     * @generated
     * @ordered
     */
    protected boolean initialisation = TreeDescriptionImpl.INITIALISATION_EDEFAULT;

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
     * The default value of the '{@link #isShowOnStartup()
     * <em>Show On Startup</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isShowOnStartup()
     * @generated
     * @ordered
     */
    protected static final boolean SHOW_ON_STARTUP_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isShowOnStartup()
     * <em>Show On Startup</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isShowOnStartup()
     * @generated
     * @ordered
     */
    protected boolean showOnStartup = TreeDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT;

    /**
     * The cached value of the '{@link #getSubItemMappings()
     * <em>Sub Item Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSubItemMappings()
     * @generated
     * @ordered
     */
    protected EList<TreeItemMapping> subItemMappings;

    /**
     * The cached value of the '{@link #getDropTools() <em>Drop Tools</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDropTools()
     * @generated
     * @ordered
     */
    protected EList<TreeItemContainerDropTool> dropTools;

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
    protected String domainClass = TreeDescriptionImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = TreeDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getCreateTreeItem()
     * <em>Create Tree Item</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCreateTreeItem()
     * @generated
     * @ordered
     */
    protected EList<TreeItemCreationTool> createTreeItem;

    /**
     * The cached value of the '
     * {@link #getOwnedRepresentationCreationDescriptions()
     * <em>Owned Representation Creation Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRepresentationCreationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationCreationDescription> ownedRepresentationCreationDescriptions;

    /**
     * The cached value of the '
     * {@link #getOwnedRepresentationNavigationDescriptions()
     * <em>Owned Representation Navigation Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRepresentationNavigationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationNavigationDescription> ownedRepresentationNavigationDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEndUserDocumentation() {
        return endUserDocumentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEndUserDocumentation(String newEndUserDocumentation) {
        String oldEndUserDocumentation = endUserDocumentation;
        endUserDocumentation = newEndUserDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION, oldEndUserDocumentation, endUserDocumentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTitleExpression() {
        return titleExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTitleExpression(String newTitleExpression) {
        String oldTitleExpression = titleExpression;
        titleExpression = newTitleExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isInitialisation() {
        return initialisation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInitialisation(boolean newInitialisation) {
        boolean oldInitialisation = initialisation;
        initialisation = newInitialisation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__INITIALISATION, oldInitialisation, initialisation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EPackage> getMetamodel() {
        if (metamodel == null) {
            metamodel = new EObjectResolvingEList<EPackage>(EPackage.class, this, DescriptionPackage.TREE_DESCRIPTION__METAMODEL);
        }
        return metamodel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isShowOnStartup() {
        return showOnStartup;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setShowOnStartup(boolean newShowOnStartup) {
        boolean oldShowOnStartup = showOnStartup;
        showOnStartup = newShowOnStartup;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__SHOW_ON_STARTUP, oldShowOnStartup, showOnStartup));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPreconditionExpression() {
        return preconditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPreconditionExpression(String newPreconditionExpression) {
        String oldPreconditionExpression = preconditionExpression;
        preconditionExpression = newPreconditionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemMapping> getSubItemMappings() {
        if (subItemMappings == null) {
            subItemMappings = new EObjectContainmentEList<TreeItemMapping>(TreeItemMapping.class, this, DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS);
        }
        return subItemMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemContainerDropTool> getDropTools() {
        if (dropTools == null) {
            dropTools = new EObjectContainmentEList<TreeItemContainerDropTool>(TreeItemContainerDropTool.class, this, DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS);
        }
        return dropTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemCreationTool> getCreateTreeItem() {
        if (createTreeItem == null) {
            createTreeItem = new EObjectContainmentEList<TreeItemCreationTool>(TreeItemCreationTool.class, this, DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM);
        }
        return createTreeItem;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationCreationDescription> getOwnedRepresentationCreationDescriptions() {
        if (ownedRepresentationCreationDescriptions == null) {
            ownedRepresentationCreationDescriptions = new EObjectContainmentEList<RepresentationCreationDescription>(RepresentationCreationDescription.class, this,
                    DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
        }
        return ownedRepresentationCreationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationNavigationDescription> getOwnedRepresentationNavigationDescriptions() {
        if (ownedRepresentationNavigationDescriptions == null) {
            ownedRepresentationNavigationDescriptions = new EObjectContainmentEList<RepresentationNavigationDescription>(RepresentationNavigationDescription.class, this,
                    DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        }
        return ownedRepresentationNavigationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS:
            return ((InternalEList<?>) getSubItemMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS:
            return ((InternalEList<?>) getDropTools()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM:
            return ((InternalEList<?>) getCreateTreeItem()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return ((InternalEList<?>) getOwnedRepresentationCreationDescriptions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return ((InternalEList<?>) getOwnedRepresentationNavigationDescriptions()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION:
            return getEndUserDocumentation();
        case DescriptionPackage.TREE_DESCRIPTION__NAME:
            return getName();
        case DescriptionPackage.TREE_DESCRIPTION__LABEL:
            return getLabel();
        case DescriptionPackage.TREE_DESCRIPTION__TITLE_EXPRESSION:
            return getTitleExpression();
        case DescriptionPackage.TREE_DESCRIPTION__INITIALISATION:
            return isInitialisation();
        case DescriptionPackage.TREE_DESCRIPTION__METAMODEL:
            return getMetamodel();
        case DescriptionPackage.TREE_DESCRIPTION__SHOW_ON_STARTUP:
            return isShowOnStartup();
        case DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS:
            return getSubItemMappings();
        case DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS:
            return getDropTools();
        case DescriptionPackage.TREE_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM:
            return getCreateTreeItem();
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return getOwnedRepresentationCreationDescriptions();
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return getOwnedRepresentationNavigationDescriptions();
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
        case DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION:
            setEndUserDocumentation((String) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__INITIALISATION:
            setInitialisation((Boolean) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            getMetamodel().addAll((Collection<? extends EPackage>) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__SHOW_ON_STARTUP:
            setShowOnStartup((Boolean) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS:
            getSubItemMappings().clear();
            getSubItemMappings().addAll((Collection<? extends TreeItemMapping>) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS:
            getDropTools().clear();
            getDropTools().addAll((Collection<? extends TreeItemContainerDropTool>) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM:
            getCreateTreeItem().clear();
            getCreateTreeItem().addAll((Collection<? extends TreeItemCreationTool>) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            getOwnedRepresentationCreationDescriptions().clear();
            getOwnedRepresentationCreationDescriptions().addAll((Collection<? extends RepresentationCreationDescription>) newValue);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            getOwnedRepresentationNavigationDescriptions().clear();
            getOwnedRepresentationNavigationDescriptions().addAll((Collection<? extends RepresentationNavigationDescription>) newValue);
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
        case DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION:
            setEndUserDocumentation(TreeDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__NAME:
            setName(TreeDescriptionImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__LABEL:
            setLabel(TreeDescriptionImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression(TreeDescriptionImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__INITIALISATION:
            setInitialisation(TreeDescriptionImpl.INITIALISATION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            return;
        case DescriptionPackage.TREE_DESCRIPTION__SHOW_ON_STARTUP:
            setShowOnStartup(TreeDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS:
            getSubItemMappings().clear();
            return;
        case DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS:
            getDropTools().clear();
            return;
        case DescriptionPackage.TREE_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(TreeDescriptionImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(TreeDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM:
            getCreateTreeItem().clear();
            return;
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            getOwnedRepresentationCreationDescriptions().clear();
            return;
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            getOwnedRepresentationNavigationDescriptions().clear();
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
        case DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION:
            return TreeDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null : !TreeDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT.equals(endUserDocumentation);
        case DescriptionPackage.TREE_DESCRIPTION__NAME:
            return TreeDescriptionImpl.NAME_EDEFAULT == null ? name != null : !TreeDescriptionImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.TREE_DESCRIPTION__LABEL:
            return TreeDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !TreeDescriptionImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.TREE_DESCRIPTION__TITLE_EXPRESSION:
            return TreeDescriptionImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !TreeDescriptionImpl.TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
        case DescriptionPackage.TREE_DESCRIPTION__INITIALISATION:
            return initialisation != TreeDescriptionImpl.INITIALISATION_EDEFAULT;
        case DescriptionPackage.TREE_DESCRIPTION__METAMODEL:
            return metamodel != null && !metamodel.isEmpty();
        case DescriptionPackage.TREE_DESCRIPTION__SHOW_ON_STARTUP:
            return showOnStartup != TreeDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT;
        case DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS:
            return subItemMappings != null && !subItemMappings.isEmpty();
        case DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS:
            return dropTools != null && !dropTools.isEmpty();
        case DescriptionPackage.TREE_DESCRIPTION__DOMAIN_CLASS:
            return TreeDescriptionImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !TreeDescriptionImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.TREE_DESCRIPTION__PRECONDITION_EXPRESSION:
            return TreeDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null : !TreeDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case DescriptionPackage.TREE_DESCRIPTION__CREATE_TREE_ITEM:
            return createTreeItem != null && !createTreeItem.isEmpty();
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return ownedRepresentationCreationDescriptions != null && !ownedRepresentationCreationDescriptions.isEmpty();
        case DescriptionPackage.TREE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return ownedRepresentationNavigationDescriptions != null && !ownedRepresentationNavigationDescriptions.isEmpty();
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
        if (baseClass == EndUserDocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_DESCRIPTION__NAME:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.TREE_DESCRIPTION__LABEL:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == TreeItemMappingContainer.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS:
                return DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS;
            case DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS:
                return DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS;
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
        if (baseClass == EndUserDocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
                return DescriptionPackage.TREE_DESCRIPTION__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.TREE_DESCRIPTION__NAME;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.TREE_DESCRIPTION__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == TreeItemMappingContainer.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS:
                return DescriptionPackage.TREE_DESCRIPTION__SUB_ITEM_MAPPINGS;
            case DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS:
                return DescriptionPackage.TREE_DESCRIPTION__DROP_TOOLS;
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
        result.append(" (endUserDocumentation: "); //$NON-NLS-1$
        result.append(endUserDocumentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", titleExpression: "); //$NON-NLS-1$
        result.append(titleExpression);
        result.append(", initialisation: "); //$NON-NLS-1$
        result.append(initialisation);
        result.append(", showOnStartup: "); //$NON-NLS-1$
        result.append(showOnStartup);
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(')');
        return result.toString();
    }

} // TreeDescriptionImpl

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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemDeletionTool;
import org.eclipse.sirius.tree.description.TreeItemDragTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.tree.description.TreeItemMappingContainer;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreePopupMenu;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tree Item Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getDefaultStyle
 * <em>Default Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getDirectEdit
 * <em>Direct Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getSubItemMappings
 * <em>Sub Item Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getDropTools
 * <em>Drop Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getReusedTreeItemMappings
 * <em>Reused Tree Item Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getAllSubMappings
 * <em>All Sub Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getSpecialize
 * <em>Specialize</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getDelete
 * <em>Delete</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getCreate
 * <em>Create</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getDndTools
 * <em>Dnd Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemMappingImpl#getPopupMenus
 * <em>Popup Menus</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeItemMappingImpl extends TreeMappingImpl implements TreeItemMapping {
    /**
     * The cached value of the '{@link #getDefaultStyle()
     * <em>Default Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDefaultStyle()
     * @generated
     * @ordered
     */
    protected TreeItemStyleDescription defaultStyle;

    /**
     * The cached value of the '{@link #getConditionalStyles()
     * <em>Conditional Styles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionalStyles()
     * @generated
     * @ordered
     */
    protected EList<ConditionalTreeItemStyleDescription> conditionalStyles;

    /**
     * The cached value of the '{@link #getDirectEdit() <em>Direct Edit</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDirectEdit()
     * @generated
     * @ordered
     */
    protected TreeItemEditionTool directEdit;

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
    protected String domainClass = TreeItemMappingImpl.DOMAIN_CLASS_EDEFAULT;

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
    protected String preconditionExpression = TreeItemMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getSemanticCandidatesExpression()
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSemanticCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSemanticCandidatesExpression()
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSemanticCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String semanticCandidatesExpression = TreeItemMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getReusedTreeItemMappings()
     * <em>Reused Tree Item Mappings</em>}' reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getReusedTreeItemMappings()
     * @generated
     * @ordered
     */
    protected EList<TreeItemMapping> reusedTreeItemMappings;

    /**
     * The cached value of the '{@link #getSpecialize() <em>Specialize</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSpecialize()
     * @generated
     * @ordered
     */
    protected TreeItemMapping specialize;

    /**
     * The cached value of the '{@link #getDelete() <em>Delete</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDelete()
     * @generated
     * @ordered
     */
    protected TreeItemDeletionTool delete;

    /**
     * The cached value of the '{@link #getCreate() <em>Create</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCreate()
     * @generated
     * @ordered
     */
    protected EList<TreeItemCreationTool> create;

    /**
     * The cached value of the '{@link #getDndTools() <em>Dnd Tools</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDndTools()
     * @generated
     * @ordered
     */
    protected EList<TreeItemDragTool> dndTools;

    /**
     * The cached value of the '{@link #getPopupMenus() <em>Popup Menus</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPopupMenus()
     * @generated
     * @ordered
     */
    protected EList<TreePopupMenu> popupMenus;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeItemMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_ITEM_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemStyleDescription getDefaultStyle() {
        return defaultStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDefaultStyle(TreeItemStyleDescription newDefaultStyle, NotificationChain msgs) {
        TreeItemStyleDescription oldDefaultStyle = defaultStyle;
        defaultStyle = newDefaultStyle;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE, oldDefaultStyle, newDefaultStyle);
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
    public void setDefaultStyle(TreeItemStyleDescription newDefaultStyle) {
        if (newDefaultStyle != defaultStyle) {
            NotificationChain msgs = null;
            if (defaultStyle != null) {
                msgs = ((InternalEObject) defaultStyle).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE, null, msgs);
            }
            if (newDefaultStyle != null) {
                msgs = ((InternalEObject) newDefaultStyle).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE, null, msgs);
            }
            msgs = basicSetDefaultStyle(newDefaultStyle, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE, newDefaultStyle, newDefaultStyle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS, oldDomainClass, domainClass));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
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
            subItemMappings = new EObjectContainmentEList<TreeItemMapping>(TreeItemMapping.class, this, DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS);
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
            dropTools = new EObjectContainmentEList<TreeItemContainerDropTool>(TreeItemContainerDropTool.class, this, DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS);
        }
        return dropTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ConditionalTreeItemStyleDescription> getConditionalStyles() {
        if (conditionalStyles == null) {
            conditionalStyles = new EObjectContainmentEList<ConditionalTreeItemStyleDescription>(ConditionalTreeItemStyleDescription.class, this,
                    DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES);
        }
        return conditionalStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemEditionTool getDirectEdit() {
        return directEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDirectEdit(TreeItemEditionTool newDirectEdit, NotificationChain msgs) {
        TreeItemEditionTool oldDirectEdit = directEdit;
        directEdit = newDirectEdit;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT, oldDirectEdit, newDirectEdit);
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
    public void setDirectEdit(TreeItemEditionTool newDirectEdit) {
        if (newDirectEdit != directEdit) {
            NotificationChain msgs = null;
            if (directEdit != null) {
                msgs = ((InternalEObject) directEdit).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT, null, msgs);
            }
            if (newDirectEdit != null) {
                msgs = ((InternalEObject) newDirectEdit).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT, null, msgs);
            }
            msgs = basicSetDirectEdit(newDirectEdit, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT, newDirectEdit, newDirectEdit));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSemanticCandidatesExpression() {
        return semanticCandidatesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSemanticCandidatesExpression(String newSemanticCandidatesExpression) {
        String oldSemanticCandidatesExpression = semanticCandidatesExpression;
        semanticCandidatesExpression = newSemanticCandidatesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, oldSemanticCandidatesExpression, semanticCandidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemMapping> getReusedTreeItemMappings() {
        if (reusedTreeItemMappings == null) {
            reusedTreeItemMappings = new EObjectResolvingEList<TreeItemMapping>(TreeItemMapping.class, this, DescriptionPackage.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS);
        }
        return reusedTreeItemMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemMapping> getAllSubMappings() {
        // TODO: implement this method to return the 'All Sub Mappings'
        // reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemMapping getSpecialize() {
        if (specialize != null && specialize.eIsProxy()) {
            InternalEObject oldSpecialize = (InternalEObject) specialize;
            specialize = (TreeItemMapping) eResolveProxy(oldSpecialize);
            if (specialize != oldSpecialize) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE, oldSpecialize, specialize));
                }
            }
        }
        return specialize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TreeItemMapping basicGetSpecialize() {
        return specialize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSpecialize(TreeItemMapping newSpecialize) {
        TreeItemMapping oldSpecialize = specialize;
        specialize = newSpecialize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE, oldSpecialize, specialize));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeItemDeletionTool getDelete() {
        return delete;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDelete(TreeItemDeletionTool newDelete, NotificationChain msgs) {
        TreeItemDeletionTool oldDelete = delete;
        delete = newDelete;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DELETE, oldDelete, newDelete);
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
    public void setDelete(TreeItemDeletionTool newDelete) {
        if (newDelete != delete) {
            NotificationChain msgs = null;
            if (delete != null) {
                msgs = ((InternalEObject) delete).eInverseRemove(this, DescriptionPackage.TREE_ITEM_DELETION_TOOL__MAPPING, TreeItemDeletionTool.class, msgs);
            }
            if (newDelete != null) {
                msgs = ((InternalEObject) newDelete).eInverseAdd(this, DescriptionPackage.TREE_ITEM_DELETION_TOOL__MAPPING, TreeItemDeletionTool.class, msgs);
            }
            msgs = basicSetDelete(newDelete, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_MAPPING__DELETE, newDelete, newDelete));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemCreationTool> getCreate() {
        if (create == null) {
            create = new EObjectContainmentEList<TreeItemCreationTool>(TreeItemCreationTool.class, this, DescriptionPackage.TREE_ITEM_MAPPING__CREATE);
        }
        return create;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeItemDragTool> getDndTools() {
        if (dndTools == null) {
            dndTools = new EObjectContainmentEList<TreeItemDragTool>(TreeItemDragTool.class, this, DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS);
        }
        return dndTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreePopupMenu> getPopupMenus() {
        if (popupMenus == null) {
            popupMenus = new EObjectContainmentEList<TreePopupMenu>(TreePopupMenu.class, this, DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS);
        }
        return popupMenus;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabelComputationExpression() {
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
    public TreeItemCreationTool getCreateTreeItem() {
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
            if (delete != null) {
                msgs = ((InternalEObject) delete).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_MAPPING__DELETE, null, msgs);
            }
            return basicSetDelete((TreeItemDeletionTool) otherEnd, msgs);
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
        case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
            return basicSetDefaultStyle(null, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
            return ((InternalEList<?>) getConditionalStyles()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
            return basicSetDirectEdit(null, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
            return ((InternalEList<?>) getSubItemMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
            return ((InternalEList<?>) getDropTools()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
            return basicSetDelete(null, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__CREATE:
            return ((InternalEList<?>) getCreate()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS:
            return ((InternalEList<?>) getDndTools()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS:
            return ((InternalEList<?>) getPopupMenus()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
            return getDefaultStyle();
        case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
            return getConditionalStyles();
        case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
            return getDirectEdit();
        case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
            return getSubItemMappings();
        case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
            return getDropTools();
        case DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return getSemanticCandidatesExpression();
        case DescriptionPackage.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS:
            return getReusedTreeItemMappings();
        case DescriptionPackage.TREE_ITEM_MAPPING__ALL_SUB_MAPPINGS:
            return getAllSubMappings();
        case DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE:
            if (resolve) {
                return getSpecialize();
            }
            return basicGetSpecialize();
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
            return getDelete();
        case DescriptionPackage.TREE_ITEM_MAPPING__CREATE:
            return getCreate();
        case DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS:
            return getDndTools();
        case DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS:
            return getPopupMenus();
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
        case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
            setDefaultStyle((TreeItemStyleDescription) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            getConditionalStyles().addAll((Collection<? extends ConditionalTreeItemStyleDescription>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
            setDirectEdit((TreeItemEditionTool) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
            getSubItemMappings().clear();
            getSubItemMappings().addAll((Collection<? extends TreeItemMapping>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
            getDropTools().clear();
            getDropTools().addAll((Collection<? extends TreeItemContainerDropTool>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression((String) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS:
            getReusedTreeItemMappings().clear();
            getReusedTreeItemMappings().addAll((Collection<? extends TreeItemMapping>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE:
            setSpecialize((TreeItemMapping) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
            setDelete((TreeItemDeletionTool) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__CREATE:
            getCreate().clear();
            getCreate().addAll((Collection<? extends TreeItemCreationTool>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS:
            getDndTools().clear();
            getDndTools().addAll((Collection<? extends TreeItemDragTool>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS:
            getPopupMenus().clear();
            getPopupMenus().addAll((Collection<? extends TreePopupMenu>) newValue);
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
        case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
            setDefaultStyle((TreeItemStyleDescription) null);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
            getConditionalStyles().clear();
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
            setDirectEdit((TreeItemEditionTool) null);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
            getSubItemMappings().clear();
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
            getDropTools().clear();
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS:
            setDomainClass(TreeItemMappingImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION:
            setPreconditionExpression(TreeItemMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression(TreeItemMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS:
            getReusedTreeItemMappings().clear();
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE:
            setSpecialize((TreeItemMapping) null);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
            setDelete((TreeItemDeletionTool) null);
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__CREATE:
            getCreate().clear();
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS:
            getDndTools().clear();
            return;
        case DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS:
            getPopupMenus().clear();
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
        case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
            return defaultStyle != null;
        case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
            return conditionalStyles != null && !conditionalStyles.isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
            return directEdit != null;
        case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
            return subItemMappings != null && !subItemMappings.isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
            return dropTools != null && !dropTools.isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__DOMAIN_CLASS:
            return TreeItemMappingImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !TreeItemMappingImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.TREE_ITEM_MAPPING__PRECONDITION_EXPRESSION:
            return TreeItemMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null : !TreeItemMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case DescriptionPackage.TREE_ITEM_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return TreeItemMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT == null ? semanticCandidatesExpression != null : !TreeItemMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT
                    .equals(semanticCandidatesExpression);
        case DescriptionPackage.TREE_ITEM_MAPPING__REUSED_TREE_ITEM_MAPPINGS:
            return reusedTreeItemMappings != null && !reusedTreeItemMappings.isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__ALL_SUB_MAPPINGS:
            return !getAllSubMappings().isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__SPECIALIZE:
            return specialize != null;
        case DescriptionPackage.TREE_ITEM_MAPPING__DELETE:
            return delete != null;
        case DescriptionPackage.TREE_ITEM_MAPPING__CREATE:
            return create != null && !create.isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__DND_TOOLS:
            return dndTools != null && !dndTools.isEmpty();
        case DescriptionPackage.TREE_ITEM_MAPPING__POPUP_MENUS:
            return popupMenus != null && !popupMenus.isEmpty();
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
        if (baseClass == StyleUpdater.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE;
            case DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES:
                return DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES;
            default:
                return -1;
            }
        }
        if (baseClass == TreeItemUpdater.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT:
                return DescriptionPackage.TREE_ITEM_UPDATER__DIRECT_EDIT;
            default:
                return -1;
            }
        }
        if (baseClass == TreeItemMappingContainer.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS:
                return DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS;
            case DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS:
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
        if (baseClass == StyleUpdater.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_STYLE:
                return DescriptionPackage.TREE_ITEM_MAPPING__DEFAULT_STYLE;
            case DescriptionPackage.STYLE_UPDATER__CONDITIONAL_STYLES:
                return DescriptionPackage.TREE_ITEM_MAPPING__CONDITIONAL_STYLES;
            default:
                return -1;
            }
        }
        if (baseClass == TreeItemUpdater.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.TREE_ITEM_UPDATER__DIRECT_EDIT:
                return DescriptionPackage.TREE_ITEM_MAPPING__DIRECT_EDIT;
            default:
                return -1;
            }
        }
        if (baseClass == TreeItemMappingContainer.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__SUB_ITEM_MAPPINGS:
                return DescriptionPackage.TREE_ITEM_MAPPING__SUB_ITEM_MAPPINGS;
            case DescriptionPackage.TREE_ITEM_MAPPING_CONTAINER__DROP_TOOLS:
                return DescriptionPackage.TREE_ITEM_MAPPING__DROP_TOOLS;
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
        result.append(" (domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(", semanticCandidatesExpression: "); //$NON-NLS-1$
        result.append(semanticCandidatesExpression);
        result.append(')');
        return result.toString();
    }

} // TreeItemMappingImpl

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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.PrecedingSiblingsVariables;
import org.eclipse.sirius.tree.description.TreeDragSource;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemTool;
import org.eclipse.sirius.tree.description.TreeVariable;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tree Item Container Drop Tool</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getFirstModelOperation
 * <em>First Model Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getVariables
 * <em>Variables</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getOldContainer
 * <em>Old Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getNewContainer
 * <em>New Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getNewViewContainer
 * <em>New View Container</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getPrecedingSiblings
 * <em>Preceding Siblings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemContainerDropToolImpl#getDragSource
 * <em>Drag Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeItemContainerDropToolImpl extends MappingBasedToolDescriptionImpl implements TreeItemContainerDropTool {
    /**
     * The cached value of the '{@link #getFirstModelOperation()
     * <em>First Model Operation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFirstModelOperation()
     * @generated
     * @ordered
     */
    protected ModelOperation firstModelOperation;

    /**
     * The cached value of the '{@link #getVariables() <em>Variables</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVariables()
     * @generated
     * @ordered
     */
    protected EList<TreeVariable> variables;

    /**
     * The cached value of the '{@link #getOldContainer()
     * <em>Old Container</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOldContainer()
     * @generated
     * @ordered
     */
    protected DropContainerVariable oldContainer;

    /**
     * The cached value of the '{@link #getNewContainer()
     * <em>New Container</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getNewContainer()
     * @generated
     * @ordered
     */
    protected DropContainerVariable newContainer;

    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementDropVariable element;

    /**
     * The cached value of the '{@link #getNewViewContainer()
     * <em>New View Container</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getNewViewContainer()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable newViewContainer;

    /**
     * The cached value of the '{@link #getPrecedingSiblings()
     * <em>Preceding Siblings</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getPrecedingSiblings()
     * @generated
     * @ordered
     */
    protected PrecedingSiblingsVariables precedingSiblings;

    /**
     * The default value of the '{@link #getDragSource() <em>Drag Source</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDragSource()
     * @generated
     * @ordered
     */
    protected static final TreeDragSource DRAG_SOURCE_EDEFAULT = TreeDragSource.TREE;

    /**
     * The cached value of the '{@link #getDragSource() <em>Drag Source</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDragSource()
     * @generated
     * @ordered
     */
    protected TreeDragSource dragSource = TreeItemContainerDropToolImpl.DRAG_SOURCE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeItemContainerDropToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_ITEM_CONTAINER_DROP_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ModelOperation getFirstModelOperation() {
        return firstModelOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetFirstModelOperation(ModelOperation newFirstModelOperation, NotificationChain msgs) {
        ModelOperation oldFirstModelOperation = firstModelOperation;
        firstModelOperation = newFirstModelOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION, oldFirstModelOperation,
                    newFirstModelOperation);
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
    public void setFirstModelOperation(ModelOperation newFirstModelOperation) {
        if (newFirstModelOperation != firstModelOperation) {
            NotificationChain msgs = null;
            if (firstModelOperation != null) {
                msgs = ((InternalEObject) firstModelOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION,
                        null, msgs);
            }
            if (newFirstModelOperation != null) {
                msgs = ((InternalEObject) newFirstModelOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION,
                        null, msgs);
            }
            msgs = basicSetFirstModelOperation(newFirstModelOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION, newFirstModelOperation, newFirstModelOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<TreeVariable> getVariables() {
        if (variables == null) {
            variables = new EObjectContainmentEList<TreeVariable>(TreeVariable.class, this, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DropContainerVariable getOldContainer() {
        return oldContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOldContainer(DropContainerVariable newOldContainer, NotificationChain msgs) {
        DropContainerVariable oldOldContainer = oldContainer;
        oldContainer = newOldContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER, oldOldContainer, newOldContainer);
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
    public void setOldContainer(DropContainerVariable newOldContainer) {
        if (newOldContainer != oldContainer) {
            NotificationChain msgs = null;
            if (oldContainer != null) {
                msgs = ((InternalEObject) oldContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER, null, msgs);
            }
            if (newOldContainer != null) {
                msgs = ((InternalEObject) newOldContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER, null, msgs);
            }
            msgs = basicSetOldContainer(newOldContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER, newOldContainer, newOldContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DropContainerVariable getNewContainer() {
        return newContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetNewContainer(DropContainerVariable newNewContainer, NotificationChain msgs) {
        DropContainerVariable oldNewContainer = newContainer;
        newContainer = newNewContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER, oldNewContainer, newNewContainer);
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
    public void setNewContainer(DropContainerVariable newNewContainer) {
        if (newNewContainer != newContainer) {
            NotificationChain msgs = null;
            if (newContainer != null) {
                msgs = ((InternalEObject) newContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER, null, msgs);
            }
            if (newNewContainer != null) {
                msgs = ((InternalEObject) newNewContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER, null, msgs);
            }
            msgs = basicSetNewContainer(newNewContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER, newNewContainer, newNewContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ElementDropVariable getElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetElement(ElementDropVariable newElement, NotificationChain msgs) {
        ElementDropVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT, oldElement, newElement);
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
    public void setElement(ElementDropVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ContainerViewVariable getNewViewContainer() {
        return newViewContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetNewViewContainer(ContainerViewVariable newNewViewContainer, NotificationChain msgs) {
        ContainerViewVariable oldNewViewContainer = newViewContainer;
        newViewContainer = newNewViewContainer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER, oldNewViewContainer,
                    newNewViewContainer);
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
    public void setNewViewContainer(ContainerViewVariable newNewViewContainer) {
        if (newNewViewContainer != newViewContainer) {
            NotificationChain msgs = null;
            if (newViewContainer != null) {
                msgs = ((InternalEObject) newViewContainer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER, null,
                        msgs);
            }
            if (newNewViewContainer != null) {
                msgs = ((InternalEObject) newNewViewContainer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER, null,
                        msgs);
            }
            msgs = basicSetNewViewContainer(newNewViewContainer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER, newNewViewContainer, newNewViewContainer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PrecedingSiblingsVariables getPrecedingSiblings() {
        return precedingSiblings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetPrecedingSiblings(PrecedingSiblingsVariables newPrecedingSiblings, NotificationChain msgs) {
        PrecedingSiblingsVariables oldPrecedingSiblings = precedingSiblings;
        precedingSiblings = newPrecedingSiblings;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS, oldPrecedingSiblings,
                    newPrecedingSiblings);
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
    public void setPrecedingSiblings(PrecedingSiblingsVariables newPrecedingSiblings) {
        if (newPrecedingSiblings != precedingSiblings) {
            NotificationChain msgs = null;
            if (precedingSiblings != null) {
                msgs = ((InternalEObject) precedingSiblings).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS, null,
                        msgs);
            }
            if (newPrecedingSiblings != null) {
                msgs = ((InternalEObject) newPrecedingSiblings).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS, null,
                        msgs);
            }
            msgs = basicSetPrecedingSiblings(newPrecedingSiblings, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS, newPrecedingSiblings, newPrecedingSiblings));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TreeDragSource getDragSource() {
        return dragSource;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDragSource(TreeDragSource newDragSource) {
        TreeDragSource oldDragSource = dragSource;
        dragSource = newDragSource == null ? TreeItemContainerDropToolImpl.DRAG_SOURCE_EDEFAULT : newDragSource;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE, oldDragSource, dragSource));
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
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION:
            return basicSetFirstModelOperation(null, msgs);
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES:
            return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER:
            return basicSetOldContainer(null, msgs);
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER:
            return basicSetNewContainer(null, msgs);
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT:
            return basicSetElement(null, msgs);
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER:
            return basicSetNewViewContainer(null, msgs);
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS:
            return basicSetPrecedingSiblings(null, msgs);
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
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION:
            return getFirstModelOperation();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES:
            return getVariables();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER:
            return getOldContainer();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER:
            return getNewContainer();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT:
            return getElement();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER:
            return getNewViewContainer();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS:
            return getPrecedingSiblings();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE:
            return getDragSource();
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
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION:
            setFirstModelOperation((ModelOperation) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES:
            getVariables().clear();
            getVariables().addAll((Collection<? extends TreeVariable>) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER:
            setOldContainer((DropContainerVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER:
            setNewContainer((DropContainerVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT:
            setElement((ElementDropVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER:
            setNewViewContainer((ContainerViewVariable) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS:
            setPrecedingSiblings((PrecedingSiblingsVariables) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE:
            setDragSource((TreeDragSource) newValue);
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
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION:
            setFirstModelOperation((ModelOperation) null);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES:
            getVariables().clear();
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER:
            setOldContainer((DropContainerVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER:
            setNewContainer((DropContainerVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT:
            setElement((ElementDropVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER:
            setNewViewContainer((ContainerViewVariable) null);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS:
            setPrecedingSiblings((PrecedingSiblingsVariables) null);
            return;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE:
            setDragSource(TreeItemContainerDropToolImpl.DRAG_SOURCE_EDEFAULT);
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
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION:
            return firstModelOperation != null;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES:
            return variables != null && !variables.isEmpty();
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__OLD_CONTAINER:
            return oldContainer != null;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_CONTAINER:
            return newContainer != null;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__ELEMENT:
            return element != null;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__NEW_VIEW_CONTAINER:
            return newViewContainer != null;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__PRECEDING_SIBLINGS:
            return precedingSiblings != null;
        case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__DRAG_SOURCE:
            return dragSource != TreeItemContainerDropToolImpl.DRAG_SOURCE_EDEFAULT;
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
        if (baseClass == TreeItemTool.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION:
                return DescriptionPackage.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION;
            case DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES:
                return DescriptionPackage.TREE_ITEM_TOOL__VARIABLES;
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
        if (baseClass == TreeItemTool.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.TREE_ITEM_TOOL__FIRST_MODEL_OPERATION:
                return DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__FIRST_MODEL_OPERATION;
            case DescriptionPackage.TREE_ITEM_TOOL__VARIABLES:
                return DescriptionPackage.TREE_ITEM_CONTAINER_DROP_TOOL__VARIABLES;
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
        result.append(" (dragSource: "); //$NON-NLS-1$
        result.append(dragSource);
        result.append(')');
        return result.toString();
    }

} // TreeItemContainerDropToolImpl

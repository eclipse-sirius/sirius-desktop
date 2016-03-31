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
package org.eclipse.sirius.diagram.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node Creation Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl#getVariable
 * <em>Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl#getViewVariable
 * <em>View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl#getExtraMappings
 * <em>Extra Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeCreationDescriptionImpl extends MappingBasedToolDescriptionImpl implements NodeCreationDescription {
    /**
     * The cached value of the '{@link #getNodeMappings() <em>Node Mappings</em>
     * }' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNodeMappings()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> nodeMappings;

    /**
     * The cached value of the '{@link #getVariable() <em>Variable</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVariable()
     * @generated
     * @ordered
     */
    protected NodeCreationVariable variable;

    /**
     * The cached value of the '{@link #getViewVariable() <em>View Variable</em>
     * }' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getViewVariable()
     * @generated
     * @ordered
     */
    protected ContainerViewVariable viewVariable;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialNodeCreationOperation initialOperation;

    /**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected static final String ICON_PATH_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected String iconPath = NodeCreationDescriptionImpl.ICON_PATH_EDEFAULT;

    /**
     * The cached value of the '{@link #getExtraMappings()
     * <em>Extra Mappings</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getExtraMappings()
     * @generated
     * @ordered
     */
    protected EList<AbstractNodeMapping> extraMappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeCreationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.NODE_CREATION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getNodeMappings() {
        if (nodeMappings == null) {
            nodeMappings = new EObjectResolvingEList<NodeMapping>(NodeMapping.class, this, ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS);
        }
        return nodeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeCreationVariable getVariable() {
        if (variable != null && variable.eIsProxy()) {
            InternalEObject oldVariable = (InternalEObject) variable;
            variable = (NodeCreationVariable) eResolveProxy(oldVariable);
            if (variable != oldVariable) {
                InternalEObject newVariable = (InternalEObject) variable;
                NotificationChain msgs = oldVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, null, null);
                if (newVariable.eInternalContainer() == null) {
                    msgs = newVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, oldVariable, variable));
                }
            }
        }
        return variable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NodeCreationVariable basicGetVariable() {
        return variable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetVariable(NodeCreationVariable newVariable, NotificationChain msgs) {
        NodeCreationVariable oldVariable = variable;
        variable = newVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, oldVariable, newVariable);
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
    public void setVariable(NodeCreationVariable newVariable) {
        if (newVariable != variable) {
            NotificationChain msgs = null;
            if (variable != null) {
                msgs = ((InternalEObject) variable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, null, msgs);
            }
            if (newVariable != null) {
                msgs = ((InternalEObject) newVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, null, msgs);
            }
            msgs = basicSetVariable(newVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE, newVariable, newVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerViewVariable getViewVariable() {
        if (viewVariable != null && viewVariable.eIsProxy()) {
            InternalEObject oldViewVariable = (InternalEObject) viewVariable;
            viewVariable = (ContainerViewVariable) eResolveProxy(oldViewVariable);
            if (viewVariable != oldViewVariable) {
                InternalEObject newViewVariable = (InternalEObject) viewVariable;
                NotificationChain msgs = oldViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, null, null);
                if (newViewVariable.eInternalContainer() == null) {
                    msgs = newViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, oldViewVariable, viewVariable));
                }
            }
        }
        return viewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerViewVariable basicGetViewVariable() {
        return viewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetViewVariable(ContainerViewVariable newViewVariable, NotificationChain msgs) {
        ContainerViewVariable oldViewVariable = viewVariable;
        viewVariable = newViewVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, oldViewVariable, newViewVariable);
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
    public void setViewVariable(ContainerViewVariable newViewVariable) {
        if (newViewVariable != viewVariable) {
            NotificationChain msgs = null;
            if (viewVariable != null) {
                msgs = ((InternalEObject) viewVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, null, msgs);
            }
            if (newViewVariable != null) {
                msgs = ((InternalEObject) newViewVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetViewVariable(newViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE, newViewVariable, newViewVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialNodeCreationOperation getInitialOperation() {
        if (initialOperation != null && initialOperation.eIsProxy()) {
            InternalEObject oldInitialOperation = (InternalEObject) initialOperation;
            initialOperation = (InitialNodeCreationOperation) eResolveProxy(oldInitialOperation);
            if (initialOperation != oldInitialOperation) {
                InternalEObject newInitialOperation = (InternalEObject) initialOperation;
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
                }
            }
        }
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InitialNodeCreationOperation basicGetInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialNodeCreationOperation newInitialOperation, NotificationChain msgs) {
        InitialNodeCreationOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
    public void setInitialOperation(InitialNodeCreationOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIconPath() {
        return iconPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractNodeMapping> getExtraMappings() {
        if (extraMappings == null) {
            extraMappings = new EObjectResolvingEList<AbstractNodeMapping>(AbstractNodeMapping.class, this, ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS);
        }
        return extraMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE:
            return basicSetVariable(null, msgs);
        case ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE:
            return basicSetViewVariable(null, msgs);
        case ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
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
        case ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS:
            return getNodeMappings();
        case ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE:
            if (resolve) {
                return getVariable();
            }
            return basicGetVariable();
        case ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE:
            if (resolve) {
                return getViewVariable();
            }
            return basicGetViewVariable();
        case ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH:
            return getIconPath();
        case ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS:
            return getExtraMappings();
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
        case ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS:
            getNodeMappings().clear();
            getNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE:
            setVariable((NodeCreationVariable) newValue);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE:
            setViewVariable((ContainerViewVariable) newValue);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialNodeCreationOperation) newValue);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS:
            getExtraMappings().clear();
            getExtraMappings().addAll((Collection<? extends AbstractNodeMapping>) newValue);
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
        case ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS:
            getNodeMappings().clear();
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE:
            setVariable((NodeCreationVariable) null);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE:
            setViewVariable((ContainerViewVariable) null);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialNodeCreationOperation) null);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH:
            setIconPath(NodeCreationDescriptionImpl.ICON_PATH_EDEFAULT);
            return;
        case ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS:
            getExtraMappings().clear();
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
        case ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS:
            return nodeMappings != null && !nodeMappings.isEmpty();
        case ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE:
            return variable != null;
        case ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE:
            return viewVariable != null;
        case ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH:
            return NodeCreationDescriptionImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !NodeCreationDescriptionImpl.ICON_PATH_EDEFAULT.equals(iconPath);
        case ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS:
            return extraMappings != null && !extraMappings.isEmpty();
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
        result.append(" (iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(')');
        return result.toString();
    }

} // NodeCreationDescriptionImpl

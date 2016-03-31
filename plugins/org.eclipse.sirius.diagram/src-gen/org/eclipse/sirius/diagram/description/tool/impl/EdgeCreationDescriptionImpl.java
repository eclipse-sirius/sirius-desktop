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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Creation Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getSourceVariable
 * <em>Source Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getTargetVariable
 * <em>Target Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getSourceViewVariable
 * <em>Source View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getTargetViewVariable
 * <em>Target View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getExtraSourceMappings
 * <em>Extra Source Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getExtraTargetMappings
 * <em>Extra Target Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl#getConnectionStartPrecondition
 * <em>Connection Start Precondition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeCreationDescriptionImpl extends MappingBasedToolDescriptionImpl implements EdgeCreationDescription {
    /**
     * The cached value of the '{@link #getEdgeMappings() <em>Edge Mappings</em>
     * }' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEdgeMappings()
     * @generated
     * @ordered
     */
    protected EList<EdgeMapping> edgeMappings;

    /**
     * The cached value of the '{@link #getSourceVariable()
     * <em>Source Variable</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getSourceVariable()
     * @generated
     * @ordered
     */
    protected SourceEdgeCreationVariable sourceVariable;

    /**
     * The cached value of the '{@link #getTargetVariable()
     * <em>Target Variable</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getTargetVariable()
     * @generated
     * @ordered
     */
    protected TargetEdgeCreationVariable targetVariable;

    /**
     * The cached value of the '{@link #getSourceViewVariable()
     * <em>Source View Variable</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSourceViewVariable()
     * @generated
     * @ordered
     */
    protected SourceEdgeViewCreationVariable sourceViewVariable;

    /**
     * The cached value of the '{@link #getTargetViewVariable()
     * <em>Target View Variable</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetViewVariable()
     * @generated
     * @ordered
     */
    protected TargetEdgeViewCreationVariable targetViewVariable;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitEdgeCreationOperation initialOperation;

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
    protected String iconPath = EdgeCreationDescriptionImpl.ICON_PATH_EDEFAULT;

    /**
     * The cached value of the '{@link #getExtraSourceMappings()
     * <em>Extra Source Mappings</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getExtraSourceMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> extraSourceMappings;

    /**
     * The cached value of the '{@link #getExtraTargetMappings()
     * <em>Extra Target Mappings</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getExtraTargetMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> extraTargetMappings;

    /**
     * The default value of the '{@link #getConnectionStartPrecondition()
     * <em>Connection Start Precondition</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getConnectionStartPrecondition()
     * @generated
     * @ordered
     */
    protected static final String CONNECTION_START_PRECONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getConnectionStartPrecondition()
     * <em>Connection Start Precondition</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getConnectionStartPrecondition()
     * @generated
     * @ordered
     */
    protected String connectionStartPrecondition = EdgeCreationDescriptionImpl.CONNECTION_START_PRECONDITION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeCreationDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.EDGE_CREATION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeMapping> getEdgeMappings() {
        if (edgeMappings == null) {
            edgeMappings = new EObjectResolvingEList<EdgeMapping>(EdgeMapping.class, this, ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS);
        }
        return edgeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceEdgeCreationVariable getSourceVariable() {
        if (sourceVariable != null && sourceVariable.eIsProxy()) {
            InternalEObject oldSourceVariable = (InternalEObject) sourceVariable;
            sourceVariable = (SourceEdgeCreationVariable) eResolveProxy(oldSourceVariable);
            if (sourceVariable != oldSourceVariable) {
                InternalEObject newSourceVariable = (InternalEObject) sourceVariable;
                NotificationChain msgs = oldSourceVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, null, null);
                if (newSourceVariable.eInternalContainer() == null) {
                    msgs = newSourceVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, oldSourceVariable, sourceVariable));
                }
            }
        }
        return sourceVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SourceEdgeCreationVariable basicGetSourceVariable() {
        return sourceVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSourceVariable(SourceEdgeCreationVariable newSourceVariable, NotificationChain msgs) {
        SourceEdgeCreationVariable oldSourceVariable = sourceVariable;
        sourceVariable = newSourceVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, oldSourceVariable, newSourceVariable);
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
    public void setSourceVariable(SourceEdgeCreationVariable newSourceVariable) {
        if (newSourceVariable != sourceVariable) {
            NotificationChain msgs = null;
            if (sourceVariable != null) {
                msgs = ((InternalEObject) sourceVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, null, msgs);
            }
            if (newSourceVariable != null) {
                msgs = ((InternalEObject) newSourceVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, null, msgs);
            }
            msgs = basicSetSourceVariable(newSourceVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE, newSourceVariable, newSourceVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TargetEdgeCreationVariable getTargetVariable() {
        if (targetVariable != null && targetVariable.eIsProxy()) {
            InternalEObject oldTargetVariable = (InternalEObject) targetVariable;
            targetVariable = (TargetEdgeCreationVariable) eResolveProxy(oldTargetVariable);
            if (targetVariable != oldTargetVariable) {
                InternalEObject newTargetVariable = (InternalEObject) targetVariable;
                NotificationChain msgs = oldTargetVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, null, null);
                if (newTargetVariable.eInternalContainer() == null) {
                    msgs = newTargetVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, oldTargetVariable, targetVariable));
                }
            }
        }
        return targetVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TargetEdgeCreationVariable basicGetTargetVariable() {
        return targetVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTargetVariable(TargetEdgeCreationVariable newTargetVariable, NotificationChain msgs) {
        TargetEdgeCreationVariable oldTargetVariable = targetVariable;
        targetVariable = newTargetVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, oldTargetVariable, newTargetVariable);
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
    public void setTargetVariable(TargetEdgeCreationVariable newTargetVariable) {
        if (newTargetVariable != targetVariable) {
            NotificationChain msgs = null;
            if (targetVariable != null) {
                msgs = ((InternalEObject) targetVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, null, msgs);
            }
            if (newTargetVariable != null) {
                msgs = ((InternalEObject) newTargetVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, null, msgs);
            }
            msgs = basicSetTargetVariable(newTargetVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE, newTargetVariable, newTargetVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceEdgeViewCreationVariable getSourceViewVariable() {
        if (sourceViewVariable != null && sourceViewVariable.eIsProxy()) {
            InternalEObject oldSourceViewVariable = (InternalEObject) sourceViewVariable;
            sourceViewVariable = (SourceEdgeViewCreationVariable) eResolveProxy(oldSourceViewVariable);
            if (sourceViewVariable != oldSourceViewVariable) {
                InternalEObject newSourceViewVariable = (InternalEObject) sourceViewVariable;
                NotificationChain msgs = oldSourceViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, null, null);
                if (newSourceViewVariable.eInternalContainer() == null) {
                    msgs = newSourceViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, oldSourceViewVariable, sourceViewVariable));
                }
            }
        }
        return sourceViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SourceEdgeViewCreationVariable basicGetSourceViewVariable() {
        return sourceViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSourceViewVariable(SourceEdgeViewCreationVariable newSourceViewVariable, NotificationChain msgs) {
        SourceEdgeViewCreationVariable oldSourceViewVariable = sourceViewVariable;
        sourceViewVariable = newSourceViewVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, oldSourceViewVariable, newSourceViewVariable);
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
    public void setSourceViewVariable(SourceEdgeViewCreationVariable newSourceViewVariable) {
        if (newSourceViewVariable != sourceViewVariable) {
            NotificationChain msgs = null;
            if (sourceViewVariable != null) {
                msgs = ((InternalEObject) sourceViewVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, null, msgs);
            }
            if (newSourceViewVariable != null) {
                msgs = ((InternalEObject) newSourceViewVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetSourceViewVariable(newSourceViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE, newSourceViewVariable, newSourceViewVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TargetEdgeViewCreationVariable getTargetViewVariable() {
        if (targetViewVariable != null && targetViewVariable.eIsProxy()) {
            InternalEObject oldTargetViewVariable = (InternalEObject) targetViewVariable;
            targetViewVariable = (TargetEdgeViewCreationVariable) eResolveProxy(oldTargetViewVariable);
            if (targetViewVariable != oldTargetViewVariable) {
                InternalEObject newTargetViewVariable = (InternalEObject) targetViewVariable;
                NotificationChain msgs = oldTargetViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, null, null);
                if (newTargetViewVariable.eInternalContainer() == null) {
                    msgs = newTargetViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, oldTargetViewVariable, targetViewVariable));
                }
            }
        }
        return targetViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TargetEdgeViewCreationVariable basicGetTargetViewVariable() {
        return targetViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTargetViewVariable(TargetEdgeViewCreationVariable newTargetViewVariable, NotificationChain msgs) {
        TargetEdgeViewCreationVariable oldTargetViewVariable = targetViewVariable;
        targetViewVariable = newTargetViewVariable;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, oldTargetViewVariable, newTargetViewVariable);
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
    public void setTargetViewVariable(TargetEdgeViewCreationVariable newTargetViewVariable) {
        if (newTargetViewVariable != targetViewVariable) {
            NotificationChain msgs = null;
            if (targetViewVariable != null) {
                msgs = ((InternalEObject) targetViewVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, null, msgs);
            }
            if (newTargetViewVariable != null) {
                msgs = ((InternalEObject) newTargetViewVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetTargetViewVariable(newTargetViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE, newTargetViewVariable, newTargetViewVariable));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitEdgeCreationOperation getInitialOperation() {
        if (initialOperation != null && initialOperation.eIsProxy()) {
            InternalEObject oldInitialOperation = (InternalEObject) initialOperation;
            initialOperation = (InitEdgeCreationOperation) eResolveProxy(oldInitialOperation);
            if (initialOperation != oldInitialOperation) {
                InternalEObject newInitialOperation = (InternalEObject) initialOperation;
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
    public InitEdgeCreationOperation basicGetInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitEdgeCreationOperation newInitialOperation, NotificationChain msgs) {
        InitEdgeCreationOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
    public void setInitialOperation(InitEdgeCreationOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getExtraSourceMappings() {
        if (extraSourceMappings == null) {
            extraSourceMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS);
        }
        return extraSourceMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getExtraTargetMappings() {
        if (extraTargetMappings == null) {
            extraTargetMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS);
        }
        return extraTargetMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getConnectionStartPrecondition() {
        return connectionStartPrecondition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setConnectionStartPrecondition(String newConnectionStartPrecondition) {
        String oldConnectionStartPrecondition = connectionStartPrecondition;
        connectionStartPrecondition = newConnectionStartPrecondition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION, oldConnectionStartPrecondition, connectionStartPrecondition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeMapping getBestMapping(EdgeTarget source, EdgeTarget target, EList<EObject> createdElements) {
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
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
            return basicSetSourceVariable(null, msgs);
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
            return basicSetTargetVariable(null, msgs);
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
            return basicSetSourceViewVariable(null, msgs);
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
            return basicSetTargetViewVariable(null, msgs);
        case ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
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
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS:
            return getEdgeMappings();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
            if (resolve) {
                return getSourceVariable();
            }
            return basicGetSourceVariable();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
            if (resolve) {
                return getTargetVariable();
            }
            return basicGetTargetVariable();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
            if (resolve) {
                return getSourceViewVariable();
            }
            return basicGetSourceViewVariable();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
            if (resolve) {
                return getTargetViewVariable();
            }
            return basicGetTargetViewVariable();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH:
            return getIconPath();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS:
            return getExtraSourceMappings();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS:
            return getExtraTargetMappings();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
            return getConnectionStartPrecondition();
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
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            getEdgeMappings().addAll((Collection<? extends EdgeMapping>) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
            setSourceVariable((SourceEdgeCreationVariable) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
            setTargetVariable((TargetEdgeCreationVariable) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
            setSourceViewVariable((SourceEdgeViewCreationVariable) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
            setTargetViewVariable((TargetEdgeViewCreationVariable) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitEdgeCreationOperation) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS:
            getExtraSourceMappings().clear();
            getExtraSourceMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS:
            getExtraTargetMappings().clear();
            getExtraTargetMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
            setConnectionStartPrecondition((String) newValue);
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
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
            setSourceVariable((SourceEdgeCreationVariable) null);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
            setTargetVariable((TargetEdgeCreationVariable) null);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
            setSourceViewVariable((SourceEdgeViewCreationVariable) null);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
            setTargetViewVariable((TargetEdgeViewCreationVariable) null);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitEdgeCreationOperation) null);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH:
            setIconPath(EdgeCreationDescriptionImpl.ICON_PATH_EDEFAULT);
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS:
            getExtraSourceMappings().clear();
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS:
            getExtraTargetMappings().clear();
            return;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
            setConnectionStartPrecondition(EdgeCreationDescriptionImpl.CONNECTION_START_PRECONDITION_EDEFAULT);
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
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS:
            return edgeMappings != null && !edgeMappings.isEmpty();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
            return sourceVariable != null;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
            return targetVariable != null;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
            return sourceViewVariable != null;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
            return targetViewVariable != null;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH:
            return EdgeCreationDescriptionImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !EdgeCreationDescriptionImpl.ICON_PATH_EDEFAULT.equals(iconPath);
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS:
            return extraSourceMappings != null && !extraSourceMappings.isEmpty();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS:
            return extraTargetMappings != null && !extraTargetMappings.isEmpty();
        case ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
            return EdgeCreationDescriptionImpl.CONNECTION_START_PRECONDITION_EDEFAULT == null ? connectionStartPrecondition != null
                    : !EdgeCreationDescriptionImpl.CONNECTION_START_PRECONDITION_EDEFAULT.equals(connectionStartPrecondition);
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
        result.append(", connectionStartPrecondition: "); //$NON-NLS-1$
        result.append(connectionStartPrecondition);
        result.append(')');
        return result.toString();
    }

} // EdgeCreationDescriptionImpl

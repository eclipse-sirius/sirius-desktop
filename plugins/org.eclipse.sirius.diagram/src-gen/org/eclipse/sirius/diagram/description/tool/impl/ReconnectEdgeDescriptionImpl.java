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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Reconnect Edge Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getReconnectionKind
 * <em>Reconnection Kind</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getSourceView
 * <em>Source View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getTargetView
 * <em>Target View</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getElement
 * <em>Element</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl#getEdgeView
 * <em>Edge View</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReconnectEdgeDescriptionImpl extends MappingBasedToolDescriptionImpl implements ReconnectEdgeDescription {
    /**
     * The default value of the '{@link #getReconnectionKind()
     * <em>Reconnection Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getReconnectionKind()
     * @generated
     * @ordered
     */
    protected static final ReconnectionKind RECONNECTION_KIND_EDEFAULT = ReconnectionKind.RECONNECT_TARGET_LITERAL;

    /**
     * The cached value of the '{@link #getReconnectionKind()
     * <em>Reconnection Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getReconnectionKind()
     * @generated
     * @ordered
     */
    protected ReconnectionKind reconnectionKind = ReconnectEdgeDescriptionImpl.RECONNECTION_KIND_EDEFAULT;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected SourceEdgeCreationVariable source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected TargetEdgeCreationVariable target;

    /**
     * The cached value of the '{@link #getSourceView() <em>Source View</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSourceView()
     * @generated
     * @ordered
     */
    protected SourceEdgeViewCreationVariable sourceView;

    /**
     * The cached value of the '{@link #getTargetView() <em>Target View</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetView()
     * @generated
     * @ordered
     */
    protected TargetEdgeViewCreationVariable targetView;

    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected ElementSelectVariable element;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * The cached value of the '{@link #getEdgeView() <em>Edge View</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEdgeView()
     * @generated
     * @ordered
     */
    protected ElementSelectVariable edgeView;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ReconnectEdgeDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.RECONNECT_EDGE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ReconnectionKind getReconnectionKind() {
        return reconnectionKind;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setReconnectionKind(ReconnectionKind newReconnectionKind) {
        ReconnectionKind oldReconnectionKind = reconnectionKind;
        reconnectionKind = newReconnectionKind == null ? ReconnectEdgeDescriptionImpl.RECONNECTION_KIND_EDEFAULT : newReconnectionKind;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND, oldReconnectionKind, reconnectionKind));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceEdgeCreationVariable getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (SourceEdgeCreationVariable) eResolveProxy(oldSource);
            if (source != oldSource) {
                InternalEObject newSource = (InternalEObject) source;
                NotificationChain msgs = oldSource.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, null, null);
                if (newSource.eInternalContainer() == null) {
                    msgs = newSource.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, oldSource, source));
                }
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SourceEdgeCreationVariable basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSource(SourceEdgeCreationVariable newSource, NotificationChain msgs) {
        SourceEdgeCreationVariable oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, oldSource, newSource);
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
    public void setSource(SourceEdgeCreationVariable newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null) {
                msgs = ((InternalEObject) source).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, null, msgs);
            }
            if (newSource != null) {
                msgs = ((InternalEObject) newSource).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, null, msgs);
            }
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE, newSource, newSource));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TargetEdgeCreationVariable getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (TargetEdgeCreationVariable) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                InternalEObject newTarget = (InternalEObject) target;
                NotificationChain msgs = oldTarget.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, null, null);
                if (newTarget.eInternalContainer() == null) {
                    msgs = newTarget.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, oldTarget, target));
                }
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TargetEdgeCreationVariable basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTarget(TargetEdgeCreationVariable newTarget, NotificationChain msgs) {
        TargetEdgeCreationVariable oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, oldTarget, newTarget);
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
    public void setTarget(TargetEdgeCreationVariable newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null) {
                msgs = ((InternalEObject) target).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, null, msgs);
            }
            if (newTarget != null) {
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, null, msgs);
            }
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET, newTarget, newTarget));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SourceEdgeViewCreationVariable getSourceView() {
        if (sourceView != null && sourceView.eIsProxy()) {
            InternalEObject oldSourceView = (InternalEObject) sourceView;
            sourceView = (SourceEdgeViewCreationVariable) eResolveProxy(oldSourceView);
            if (sourceView != oldSourceView) {
                InternalEObject newSourceView = (InternalEObject) sourceView;
                NotificationChain msgs = oldSourceView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, null, null);
                if (newSourceView.eInternalContainer() == null) {
                    msgs = newSourceView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, oldSourceView, sourceView));
                }
            }
        }
        return sourceView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SourceEdgeViewCreationVariable basicGetSourceView() {
        return sourceView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSourceView(SourceEdgeViewCreationVariable newSourceView, NotificationChain msgs) {
        SourceEdgeViewCreationVariable oldSourceView = sourceView;
        sourceView = newSourceView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, oldSourceView, newSourceView);
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
    public void setSourceView(SourceEdgeViewCreationVariable newSourceView) {
        if (newSourceView != sourceView) {
            NotificationChain msgs = null;
            if (sourceView != null) {
                msgs = ((InternalEObject) sourceView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, null, msgs);
            }
            if (newSourceView != null) {
                msgs = ((InternalEObject) newSourceView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, null, msgs);
            }
            msgs = basicSetSourceView(newSourceView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW, newSourceView, newSourceView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TargetEdgeViewCreationVariable getTargetView() {
        if (targetView != null && targetView.eIsProxy()) {
            InternalEObject oldTargetView = (InternalEObject) targetView;
            targetView = (TargetEdgeViewCreationVariable) eResolveProxy(oldTargetView);
            if (targetView != oldTargetView) {
                InternalEObject newTargetView = (InternalEObject) targetView;
                NotificationChain msgs = oldTargetView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, null, null);
                if (newTargetView.eInternalContainer() == null) {
                    msgs = newTargetView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, oldTargetView, targetView));
                }
            }
        }
        return targetView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TargetEdgeViewCreationVariable basicGetTargetView() {
        return targetView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTargetView(TargetEdgeViewCreationVariable newTargetView, NotificationChain msgs) {
        TargetEdgeViewCreationVariable oldTargetView = targetView;
        targetView = newTargetView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, oldTargetView, newTargetView);
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
    public void setTargetView(TargetEdgeViewCreationVariable newTargetView) {
        if (newTargetView != targetView) {
            NotificationChain msgs = null;
            if (targetView != null) {
                msgs = ((InternalEObject) targetView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, null, msgs);
            }
            if (newTargetView != null) {
                msgs = ((InternalEObject) newTargetView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, null, msgs);
            }
            msgs = basicSetTargetView(newTargetView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW, newTargetView, newTargetView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementSelectVariable getElement() {
        if (element != null && element.eIsProxy()) {
            InternalEObject oldElement = (InternalEObject) element;
            element = (ElementSelectVariable) eResolveProxy(oldElement);
            if (element != oldElement) {
                InternalEObject newElement = (InternalEObject) element;
                NotificationChain msgs = oldElement.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, null, null);
                if (newElement.eInternalContainer() == null) {
                    msgs = newElement.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, oldElement, element));
                }
            }
        }
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementSelectVariable basicGetElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetElement(ElementSelectVariable newElement, NotificationChain msgs) {
        ElementSelectVariable oldElement = element;
        element = newElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, oldElement, newElement);
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
    public void setElement(ElementSelectVariable newElement) {
        if (newElement != element) {
            NotificationChain msgs = null;
            if (element != null) {
                msgs = ((InternalEObject) element).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, null, msgs);
            }
            if (newElement != null) {
                msgs = ((InternalEObject) newElement).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, null, msgs);
            }
            msgs = basicSetElement(newElement, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT, newElement, newElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getInitialOperation() {
        if (initialOperation != null && initialOperation.eIsProxy()) {
            InternalEObject oldInitialOperation = (InternalEObject) initialOperation;
            initialOperation = (InitialOperation) eResolveProxy(oldInitialOperation);
            if (initialOperation != oldInitialOperation) {
                InternalEObject newInitialOperation = (InternalEObject) initialOperation;
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
    public InitialOperation basicGetInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialOperation newInitialOperation, NotificationChain msgs) {
        InitialOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ElementSelectVariable getEdgeView() {
        if (edgeView != null && edgeView.eIsProxy()) {
            InternalEObject oldEdgeView = (InternalEObject) edgeView;
            edgeView = (ElementSelectVariable) eResolveProxy(oldEdgeView);
            if (edgeView != oldEdgeView) {
                InternalEObject newEdgeView = (InternalEObject) edgeView;
                NotificationChain msgs = oldEdgeView.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, null, null);
                if (newEdgeView.eInternalContainer() == null) {
                    msgs = newEdgeView.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, oldEdgeView, edgeView));
                }
            }
        }
        return edgeView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ElementSelectVariable basicGetEdgeView() {
        return edgeView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetEdgeView(ElementSelectVariable newEdgeView, NotificationChain msgs) {
        ElementSelectVariable oldEdgeView = edgeView;
        edgeView = newEdgeView;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, oldEdgeView, newEdgeView);
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
    public void setEdgeView(ElementSelectVariable newEdgeView) {
        if (newEdgeView != edgeView) {
            NotificationChain msgs = null;
            if (edgeView != null) {
                msgs = ((InternalEObject) edgeView).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, null, msgs);
            }
            if (newEdgeView != null) {
                msgs = ((InternalEObject) newEdgeView).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, null, msgs);
            }
            msgs = basicSetEdgeView(newEdgeView, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW, newEdgeView, newEdgeView));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeMapping> getMappings() {
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
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE:
            return basicSetSource(null, msgs);
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET:
            return basicSetTarget(null, msgs);
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW:
            return basicSetSourceView(null, msgs);
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW:
            return basicSetTargetView(null, msgs);
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT:
            return basicSetElement(null, msgs);
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW:
            return basicSetEdgeView(null, msgs);
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
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND:
            return getReconnectionKind();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE:
            if (resolve) {
                return getSource();
            }
            return basicGetSource();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW:
            if (resolve) {
                return getSourceView();
            }
            return basicGetSourceView();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW:
            if (resolve) {
                return getTargetView();
            }
            return basicGetTargetView();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT:
            if (resolve) {
                return getElement();
            }
            return basicGetElement();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW:
            if (resolve) {
                return getEdgeView();
            }
            return basicGetEdgeView();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND:
            setReconnectionKind((ReconnectionKind) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE:
            setSource((SourceEdgeCreationVariable) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET:
            setTarget((TargetEdgeCreationVariable) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW:
            setSourceView((SourceEdgeViewCreationVariable) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW:
            setTargetView((TargetEdgeViewCreationVariable) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT:
            setElement((ElementSelectVariable) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW:
            setEdgeView((ElementSelectVariable) newValue);
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
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND:
            setReconnectionKind(ReconnectEdgeDescriptionImpl.RECONNECTION_KIND_EDEFAULT);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE:
            setSource((SourceEdgeCreationVariable) null);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET:
            setTarget((TargetEdgeCreationVariable) null);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW:
            setSourceView((SourceEdgeViewCreationVariable) null);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW:
            setTargetView((TargetEdgeViewCreationVariable) null);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT:
            setElement((ElementSelectVariable) null);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW:
            setEdgeView((ElementSelectVariable) null);
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
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND:
            return reconnectionKind != ReconnectEdgeDescriptionImpl.RECONNECTION_KIND_EDEFAULT;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE:
            return source != null;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET:
            return target != null;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW:
            return sourceView != null;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW:
            return targetView != null;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__ELEMENT:
            return element != null;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW:
            return edgeView != null;
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
        result.append(" (reconnectionKind: "); //$NON-NLS-1$
        result.append(reconnectionKind);
        result.append(')');
        return result.toString();
    }

} // ReconnectEdgeDescriptionImpl

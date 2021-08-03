/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.sequence.description.EventMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Reorder Tool</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl#getMappings
 * <em>Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl#getStartingEndPredecessorBefore
 * <em>Starting End Predecessor Before</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl#getStartingEndPredecessorAfter
 * <em>Starting End Predecessor After</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl#getFinishingEndPredecessorBefore
 * <em>Finishing End Predecessor Before</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl#getFinishingEndPredecessorAfter
 * <em>Finishing End Predecessor After</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl#getOnEventMovedOperation <em>On
 * Event Moved Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReorderToolImpl extends AbstractToolDescriptionImpl implements ReorderTool {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<EventMapping> mappings;

    /**
     * The cached value of the '{@link #getStartingEndPredecessorBefore() <em>Starting End Predecessor Before</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStartingEndPredecessorBefore()
     * @generated
     * @ordered
     */
    protected MessageEndVariable startingEndPredecessorBefore;

    /**
     * The cached value of the '{@link #getStartingEndPredecessorAfter() <em>Starting End Predecessor After</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStartingEndPredecessorAfter()
     * @generated
     * @ordered
     */
    protected MessageEndVariable startingEndPredecessorAfter;

    /**
     * The cached value of the '{@link #getFinishingEndPredecessorBefore() <em>Finishing End Predecessor Before</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFinishingEndPredecessorBefore()
     * @generated
     * @ordered
     */
    protected MessageEndVariable finishingEndPredecessorBefore;

    /**
     * The cached value of the '{@link #getFinishingEndPredecessorAfter() <em>Finishing End Predecessor After</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFinishingEndPredecessorAfter()
     * @generated
     * @ordered
     */
    protected MessageEndVariable finishingEndPredecessorAfter;

    /**
     * The cached value of the '{@link #getOnEventMovedOperation() <em>On Event Moved Operation</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOnEventMovedOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation onEventMovedOperation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ReorderToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.REORDER_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EventMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectResolvingEList<>(EventMapping.class, this, ToolPackage.REORDER_TOOL__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEndVariable getStartingEndPredecessorBefore() {
        return startingEndPredecessorBefore;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStartingEndPredecessorBefore(MessageEndVariable newStartingEndPredecessorBefore, NotificationChain msgs) {
        MessageEndVariable oldStartingEndPredecessorBefore = startingEndPredecessorBefore;
        startingEndPredecessorBefore = newStartingEndPredecessorBefore;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE, oldStartingEndPredecessorBefore,
                    newStartingEndPredecessorBefore);
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
    public void setStartingEndPredecessorBefore(MessageEndVariable newStartingEndPredecessorBefore) {
        if (newStartingEndPredecessorBefore != startingEndPredecessorBefore) {
            NotificationChain msgs = null;
            if (startingEndPredecessorBefore != null) {
                msgs = ((InternalEObject) startingEndPredecessorBefore).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE, null,
                        msgs);
            }
            if (newStartingEndPredecessorBefore != null) {
                msgs = ((InternalEObject) newStartingEndPredecessorBefore).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE, null,
                        msgs);
            }
            msgs = basicSetStartingEndPredecessorBefore(newStartingEndPredecessorBefore, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE, newStartingEndPredecessorBefore, newStartingEndPredecessorBefore));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEndVariable getStartingEndPredecessorAfter() {
        return startingEndPredecessorAfter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStartingEndPredecessorAfter(MessageEndVariable newStartingEndPredecessorAfter, NotificationChain msgs) {
        MessageEndVariable oldStartingEndPredecessorAfter = startingEndPredecessorAfter;
        startingEndPredecessorAfter = newStartingEndPredecessorAfter;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER, oldStartingEndPredecessorAfter,
                    newStartingEndPredecessorAfter);
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
    public void setStartingEndPredecessorAfter(MessageEndVariable newStartingEndPredecessorAfter) {
        if (newStartingEndPredecessorAfter != startingEndPredecessorAfter) {
            NotificationChain msgs = null;
            if (startingEndPredecessorAfter != null) {
                msgs = ((InternalEObject) startingEndPredecessorAfter).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER, null,
                        msgs);
            }
            if (newStartingEndPredecessorAfter != null) {
                msgs = ((InternalEObject) newStartingEndPredecessorAfter).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER, null,
                        msgs);
            }
            msgs = basicSetStartingEndPredecessorAfter(newStartingEndPredecessorAfter, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER, newStartingEndPredecessorAfter, newStartingEndPredecessorAfter));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEndVariable getFinishingEndPredecessorBefore() {
        return finishingEndPredecessorBefore;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFinishingEndPredecessorBefore(MessageEndVariable newFinishingEndPredecessorBefore, NotificationChain msgs) {
        MessageEndVariable oldFinishingEndPredecessorBefore = finishingEndPredecessorBefore;
        finishingEndPredecessorBefore = newFinishingEndPredecessorBefore;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE, oldFinishingEndPredecessorBefore,
                    newFinishingEndPredecessorBefore);
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
    public void setFinishingEndPredecessorBefore(MessageEndVariable newFinishingEndPredecessorBefore) {
        if (newFinishingEndPredecessorBefore != finishingEndPredecessorBefore) {
            NotificationChain msgs = null;
            if (finishingEndPredecessorBefore != null) {
                msgs = ((InternalEObject) finishingEndPredecessorBefore).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE, null,
                        msgs);
            }
            if (newFinishingEndPredecessorBefore != null) {
                msgs = ((InternalEObject) newFinishingEndPredecessorBefore).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE, null,
                        msgs);
            }
            msgs = basicSetFinishingEndPredecessorBefore(newFinishingEndPredecessorBefore, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE, newFinishingEndPredecessorBefore, newFinishingEndPredecessorBefore));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEndVariable getFinishingEndPredecessorAfter() {
        return finishingEndPredecessorAfter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFinishingEndPredecessorAfter(MessageEndVariable newFinishingEndPredecessorAfter, NotificationChain msgs) {
        MessageEndVariable oldFinishingEndPredecessorAfter = finishingEndPredecessorAfter;
        finishingEndPredecessorAfter = newFinishingEndPredecessorAfter;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER, oldFinishingEndPredecessorAfter,
                    newFinishingEndPredecessorAfter);
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
    public void setFinishingEndPredecessorAfter(MessageEndVariable newFinishingEndPredecessorAfter) {
        if (newFinishingEndPredecessorAfter != finishingEndPredecessorAfter) {
            NotificationChain msgs = null;
            if (finishingEndPredecessorAfter != null) {
                msgs = ((InternalEObject) finishingEndPredecessorAfter).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER, null,
                        msgs);
            }
            if (newFinishingEndPredecessorAfter != null) {
                msgs = ((InternalEObject) newFinishingEndPredecessorAfter).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER, null,
                        msgs);
            }
            msgs = basicSetFinishingEndPredecessorAfter(newFinishingEndPredecessorAfter, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER, newFinishingEndPredecessorAfter, newFinishingEndPredecessorAfter));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getOnEventMovedOperation() {
        return onEventMovedOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetOnEventMovedOperation(InitialOperation newOnEventMovedOperation, NotificationChain msgs) {
        InitialOperation oldOnEventMovedOperation = onEventMovedOperation;
        onEventMovedOperation = newOnEventMovedOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION, oldOnEventMovedOperation, newOnEventMovedOperation);
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
    public void setOnEventMovedOperation(InitialOperation newOnEventMovedOperation) {
        if (newOnEventMovedOperation != onEventMovedOperation) {
            NotificationChain msgs = null;
            if (onEventMovedOperation != null) {
                msgs = ((InternalEObject) onEventMovedOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION, null, msgs);
            }
            if (newOnEventMovedOperation != null) {
                msgs = ((InternalEObject) newOnEventMovedOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION, null, msgs);
            }
            msgs = basicSetOnEventMovedOperation(newOnEventMovedOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION, newOnEventMovedOperation, newOnEventMovedOperation));
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
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE:
            return basicSetStartingEndPredecessorBefore(null, msgs);
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER:
            return basicSetStartingEndPredecessorAfter(null, msgs);
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE:
            return basicSetFinishingEndPredecessorBefore(null, msgs);
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER:
            return basicSetFinishingEndPredecessorAfter(null, msgs);
        case ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION:
            return basicSetOnEventMovedOperation(null, msgs);
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
        case ToolPackage.REORDER_TOOL__MAPPINGS:
            return getMappings();
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE:
            return getStartingEndPredecessorBefore();
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER:
            return getStartingEndPredecessorAfter();
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE:
            return getFinishingEndPredecessorBefore();
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER:
            return getFinishingEndPredecessorAfter();
        case ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION:
            return getOnEventMovedOperation();
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
        case ToolPackage.REORDER_TOOL__MAPPINGS:
            getMappings().clear();
            getMappings().addAll((Collection<? extends EventMapping>) newValue);
            return;
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE:
            setStartingEndPredecessorBefore((MessageEndVariable) newValue);
            return;
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER:
            setStartingEndPredecessorAfter((MessageEndVariable) newValue);
            return;
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE:
            setFinishingEndPredecessorBefore((MessageEndVariable) newValue);
            return;
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER:
            setFinishingEndPredecessorAfter((MessageEndVariable) newValue);
            return;
        case ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION:
            setOnEventMovedOperation((InitialOperation) newValue);
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
        case ToolPackage.REORDER_TOOL__MAPPINGS:
            getMappings().clear();
            return;
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE:
            setStartingEndPredecessorBefore((MessageEndVariable) null);
            return;
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER:
            setStartingEndPredecessorAfter((MessageEndVariable) null);
            return;
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE:
            setFinishingEndPredecessorBefore((MessageEndVariable) null);
            return;
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER:
            setFinishingEndPredecessorAfter((MessageEndVariable) null);
            return;
        case ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION:
            setOnEventMovedOperation((InitialOperation) null);
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
        case ToolPackage.REORDER_TOOL__MAPPINGS:
            return mappings != null && !mappings.isEmpty();
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE:
            return startingEndPredecessorBefore != null;
        case ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER:
            return startingEndPredecessorAfter != null;
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE:
            return finishingEndPredecessorBefore != null;
        case ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER:
            return finishingEndPredecessorAfter != null;
        case ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION:
            return onEventMovedOperation != null;
        }
        return super.eIsSet(featureID);
    }

} // ReorderToolImpl

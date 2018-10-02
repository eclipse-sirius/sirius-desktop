/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.tool.impl.ContainerCreationDescriptionImpl;
import org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.SequenceDiagramToolDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Combined Fragment Creation Tool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl#getStartingEndPredecessor
 * <em>Starting End Predecessor</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl#getFinishingEndPredecessor
 * <em>Finishing End Predecessor</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl#getCoveredLifelines
 * <em>Covered Lifelines</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CombinedFragmentCreationToolImpl extends ContainerCreationDescriptionImpl implements CombinedFragmentCreationTool {
    /**
     * The cached value of the '{@link #getStartingEndPredecessor() <em>Starting End Predecessor</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStartingEndPredecessor()
     * @generated
     * @ordered
     */
    protected MessageEndVariable startingEndPredecessor;

    /**
     * The cached value of the '{@link #getFinishingEndPredecessor() <em>Finishing End Predecessor</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFinishingEndPredecessor()
     * @generated
     * @ordered
     */
    protected MessageEndVariable finishingEndPredecessor;

    /**
     * The cached value of the '{@link #getCoveredLifelines() <em>Covered Lifelines</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCoveredLifelines()
     * @generated
     * @ordered
     */
    protected CoveredLifelinesVariable coveredLifelines;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CombinedFragmentCreationToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.COMBINED_FRAGMENT_CREATION_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEndVariable getStartingEndPredecessor() {
        return startingEndPredecessor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetStartingEndPredecessor(MessageEndVariable newStartingEndPredecessor, NotificationChain msgs) {
        MessageEndVariable oldStartingEndPredecessor = startingEndPredecessor;
        startingEndPredecessor = newStartingEndPredecessor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR, oldStartingEndPredecessor,
                    newStartingEndPredecessor);
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
    public void setStartingEndPredecessor(MessageEndVariable newStartingEndPredecessor) {
        if (newStartingEndPredecessor != startingEndPredecessor) {
            NotificationChain msgs = null;
            if (startingEndPredecessor != null) {
                msgs = ((InternalEObject) startingEndPredecessor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR,
                        null, msgs);
            }
            if (newStartingEndPredecessor != null) {
                msgs = ((InternalEObject) newStartingEndPredecessor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR,
                        null, msgs);
            }
            msgs = basicSetStartingEndPredecessor(newStartingEndPredecessor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR, newStartingEndPredecessor, newStartingEndPredecessor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEndVariable getFinishingEndPredecessor() {
        return finishingEndPredecessor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetFinishingEndPredecessor(MessageEndVariable newFinishingEndPredecessor, NotificationChain msgs) {
        MessageEndVariable oldFinishingEndPredecessor = finishingEndPredecessor;
        finishingEndPredecessor = newFinishingEndPredecessor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR, oldFinishingEndPredecessor,
                    newFinishingEndPredecessor);
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
    public void setFinishingEndPredecessor(MessageEndVariable newFinishingEndPredecessor) {
        if (newFinishingEndPredecessor != finishingEndPredecessor) {
            NotificationChain msgs = null;
            if (finishingEndPredecessor != null) {
                msgs = ((InternalEObject) finishingEndPredecessor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR,
                        null, msgs);
            }
            if (newFinishingEndPredecessor != null) {
                msgs = ((InternalEObject) newFinishingEndPredecessor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR,
                        null, msgs);
            }
            msgs = basicSetFinishingEndPredecessor(newFinishingEndPredecessor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR, newFinishingEndPredecessor, newFinishingEndPredecessor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CoveredLifelinesVariable getCoveredLifelines() {
        return coveredLifelines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCoveredLifelines(CoveredLifelinesVariable newCoveredLifelines, NotificationChain msgs) {
        CoveredLifelinesVariable oldCoveredLifelines = coveredLifelines;
        coveredLifelines = newCoveredLifelines;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES, oldCoveredLifelines, newCoveredLifelines);
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
    public void setCoveredLifelines(CoveredLifelinesVariable newCoveredLifelines) {
        if (newCoveredLifelines != coveredLifelines) {
            NotificationChain msgs = null;
            if (coveredLifelines != null) {
                msgs = ((InternalEObject) coveredLifelines).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES, null, msgs);
            }
            if (newCoveredLifelines != null) {
                msgs = ((InternalEObject) newCoveredLifelines).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES, null, msgs);
            }
            msgs = basicSetCoveredLifelines(newCoveredLifelines, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES, newCoveredLifelines, newCoveredLifelines));
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
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
            return basicSetStartingEndPredecessor(null, msgs);
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            return basicSetFinishingEndPredecessor(null, msgs);
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES:
            return basicSetCoveredLifelines(null, msgs);
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
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
            return getStartingEndPredecessor();
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            return getFinishingEndPredecessor();
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES:
            return getCoveredLifelines();
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
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
            setStartingEndPredecessor((MessageEndVariable) newValue);
            return;
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            setFinishingEndPredecessor((MessageEndVariable) newValue);
            return;
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES:
            setCoveredLifelines((CoveredLifelinesVariable) newValue);
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
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
            setStartingEndPredecessor((MessageEndVariable) null);
            return;
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            setFinishingEndPredecessor((MessageEndVariable) null);
            return;
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES:
            setCoveredLifelines((CoveredLifelinesVariable) null);
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
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
            return startingEndPredecessor != null;
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            return finishingEndPredecessor != null;
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES:
            return coveredLifelines != null;
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
        if (baseClass == SequenceDiagramToolDescription.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == OrderedElementCreationTool.class) {
            switch (derivedFeatureID) {
            case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
                return ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR;
            case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
                return ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR;
            default:
                return -1;
            }
        }
        if (baseClass == CoveringElementCreationTool.class) {
            switch (derivedFeatureID) {
            case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES:
                return ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES;
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
        if (baseClass == SequenceDiagramToolDescription.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == OrderedElementCreationTool.class) {
            switch (baseFeatureID) {
            case ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
                return ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR;
            case ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
                return ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR;
            default:
                return -1;
            }
        }
        if (baseClass == CoveringElementCreationTool.class) {
            switch (baseFeatureID) {
            case ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES:
                return ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} // CombinedFragmentCreationToolImpl

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
package org.eclipse.sirius.diagram.sequence.description.tool.impl;

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
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable;
import org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Message Creation Tool</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getPrecondition
 * <em>Precondition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#isForceRefresh
 * <em>Force Refresh</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getElementsToSelect
 * <em>Elements To Select</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#isInverseSelectionOrder
 * <em>Inverse Selection Order</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getSourceVariable
 * <em>Source Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getTargetVariable
 * <em>Target Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getSourceViewVariable
 * <em>Source View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getTargetViewVariable
 * <em>Target View Variable</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getExtraSourceMappings
 * <em>Extra Source Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getExtraTargetMappings
 * <em>Extra Target Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getConnectionStartPrecondition
 * <em>Connection Start Precondition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getStartingEndPredecessor
 * <em>Starting End Predecessor</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl#getFinishingEndPredecessor
 * <em>Finishing End Predecessor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageCreationToolImpl extends SequenceDiagramToolDescriptionImpl implements MessageCreationTool {
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
    protected String documentation = MessageCreationToolImpl.DOCUMENTATION_EDEFAULT;

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
    protected String name = MessageCreationToolImpl.NAME_EDEFAULT;

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
    protected String label = MessageCreationToolImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getPrecondition()
     * <em>Precondition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected String precondition = MessageCreationToolImpl.PRECONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #isForceRefresh()
     * <em>Force Refresh</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected static final boolean FORCE_REFRESH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isForceRefresh() <em>Force Refresh</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected boolean forceRefresh = MessageCreationToolImpl.FORCE_REFRESH_EDEFAULT;

    /**
     * The cached value of the '{@link #getFilters() <em>Filters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFilters()
     * @generated
     * @ordered
     */
    protected EList<ToolFilterDescription> filters;

    /**
     * The default value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected static final String ELEMENTS_TO_SELECT_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected String elementsToSelect = MessageCreationToolImpl.ELEMENTS_TO_SELECT_EDEFAULT;

    /**
     * The default value of the '{@link #isInverseSelectionOrder()
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected static final boolean INVERSE_SELECTION_ORDER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInverseSelectionOrder()
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected boolean inverseSelectionOrder = MessageCreationToolImpl.INVERSE_SELECTION_ORDER_EDEFAULT;

    /**
     * The cached value of the '{@link #getEdgeMappings()
     * <em>Edge Mappings</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
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
    protected String iconPath = MessageCreationToolImpl.ICON_PATH_EDEFAULT;

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
    protected String connectionStartPrecondition = MessageCreationToolImpl.CONNECTION_START_PRECONDITION_EDEFAULT;

    /**
     * The cached value of the '{@link #getStartingEndPredecessor()
     * <em>Starting End Predecessor</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStartingEndPredecessor()
     * @generated
     * @ordered
     */
    protected MessageEndVariable startingEndPredecessor;

    /**
     * The cached value of the '{@link #getFinishingEndPredecessor()
     * <em>Finishing End Predecessor</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFinishingEndPredecessor()
     * @generated
     * @ordered
     */
    protected MessageEndVariable finishingEndPredecessor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MessageCreationToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.MESSAGE_CREATION_TOOL;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION, oldDocumentation, documentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPrecondition() {
        return precondition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPrecondition(String newPrecondition) {
        String oldPrecondition = precondition;
        precondition = newPrecondition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION, oldPrecondition, precondition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isForceRefresh() {
        return forceRefresh;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setForceRefresh(boolean newForceRefresh) {
        boolean oldForceRefresh = forceRefresh;
        forceRefresh = newForceRefresh;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH, oldForceRefresh, forceRefresh));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ToolFilterDescription> getFilters() {
        if (filters == null) {
            filters = new EObjectContainmentEList.Resolving<ToolFilterDescription>(ToolFilterDescription.class, this, ToolPackage.MESSAGE_CREATION_TOOL__FILTERS);
        }
        return filters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getElementsToSelect() {
        return elementsToSelect;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setElementsToSelect(String newElementsToSelect) {
        String oldElementsToSelect = elementsToSelect;
        elementsToSelect = newElementsToSelect;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT, oldElementsToSelect, elementsToSelect));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isInverseSelectionOrder() {
        return inverseSelectionOrder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInverseSelectionOrder(boolean newInverseSelectionOrder) {
        boolean oldInverseSelectionOrder = inverseSelectionOrder;
        inverseSelectionOrder = newInverseSelectionOrder;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER, oldInverseSelectionOrder, inverseSelectionOrder));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EdgeMapping> getEdgeMappings() {
        if (edgeMappings == null) {
            edgeMappings = new EObjectResolvingEList<EdgeMapping>(EdgeMapping.class, this, ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS);
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
                NotificationChain msgs = oldSourceVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, null, null);
                if (newSourceVariable.eInternalContainer() == null) {
                    msgs = newSourceVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, oldSourceVariable, sourceVariable));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, oldSourceVariable, newSourceVariable);
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
                msgs = ((InternalEObject) sourceVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, null, msgs);
            }
            if (newSourceVariable != null) {
                msgs = ((InternalEObject) newSourceVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, null, msgs);
            }
            msgs = basicSetSourceVariable(newSourceVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE, newSourceVariable, newSourceVariable));
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
                NotificationChain msgs = oldTargetVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, null, null);
                if (newTargetVariable.eInternalContainer() == null) {
                    msgs = newTargetVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, oldTargetVariable, targetVariable));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, oldTargetVariable, newTargetVariable);
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
                msgs = ((InternalEObject) targetVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, null, msgs);
            }
            if (newTargetVariable != null) {
                msgs = ((InternalEObject) newTargetVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, null, msgs);
            }
            msgs = basicSetTargetVariable(newTargetVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE, newTargetVariable, newTargetVariable));
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
                NotificationChain msgs = oldSourceViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, null, null);
                if (newSourceViewVariable.eInternalContainer() == null) {
                    msgs = newSourceViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, oldSourceViewVariable, sourceViewVariable));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, oldSourceViewVariable, newSourceViewVariable);
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
                msgs = ((InternalEObject) sourceViewVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, null, msgs);
            }
            if (newSourceViewVariable != null) {
                msgs = ((InternalEObject) newSourceViewVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetSourceViewVariable(newSourceViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE, newSourceViewVariable, newSourceViewVariable));
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
                NotificationChain msgs = oldTargetViewVariable.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, null, null);
                if (newTargetViewVariable.eInternalContainer() == null) {
                    msgs = newTargetViewVariable.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, oldTargetViewVariable, targetViewVariable));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, oldTargetViewVariable, newTargetViewVariable);
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
                msgs = ((InternalEObject) targetViewVariable).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, null, msgs);
            }
            if (newTargetViewVariable != null) {
                msgs = ((InternalEObject) newTargetViewVariable).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, null, msgs);
            }
            msgs = basicSetTargetViewVariable(newTargetViewVariable, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE, newTargetViewVariable, newTargetViewVariable));
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
                NotificationChain msgs = oldInitialOperation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, null, null);
                if (newInitialOperation.eInternalContainer() == null) {
                    msgs = newInitialOperation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, oldInitialOperation, initialOperation));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH, oldIconPath, iconPath));
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
            extraSourceMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS);
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
            extraTargetMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION, oldConnectionStartPrecondition, connectionStartPrecondition));
        }
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR, oldStartingEndPredecessor,
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
                msgs = ((InternalEObject) startingEndPredecessor)
                        .eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR, null, msgs);
            }
            if (newStartingEndPredecessor != null) {
                msgs = ((InternalEObject) newStartingEndPredecessor)
                        .eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR, null, msgs);
            }
            msgs = basicSetStartingEndPredecessor(newStartingEndPredecessor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR, newStartingEndPredecessor, newStartingEndPredecessor));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR, oldFinishingEndPredecessor,
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
                msgs = ((InternalEObject) finishingEndPredecessor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR, null,
                        msgs);
            }
            if (newFinishingEndPredecessor != null) {
                msgs = ((InternalEObject) newFinishingEndPredecessor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR, null,
                        msgs);
            }
            msgs = basicSetFinishingEndPredecessor(newFinishingEndPredecessor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR, newFinishingEndPredecessor, newFinishingEndPredecessor));
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
        case ToolPackage.MESSAGE_CREATION_TOOL__FILTERS:
            return ((InternalEList<?>) getFilters()).basicRemove(otherEnd, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE:
            return basicSetSourceVariable(null, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE:
            return basicSetTargetVariable(null, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE:
            return basicSetSourceViewVariable(null, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE:
            return basicSetTargetViewVariable(null, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR:
            return basicSetStartingEndPredecessor(null, msgs);
        case ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            return basicSetFinishingEndPredecessor(null, msgs);
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
        case ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION:
            return getDocumentation();
        case ToolPackage.MESSAGE_CREATION_TOOL__NAME:
            return getName();
        case ToolPackage.MESSAGE_CREATION_TOOL__LABEL:
            return getLabel();
        case ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION:
            return getPrecondition();
        case ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH:
            return isForceRefresh();
        case ToolPackage.MESSAGE_CREATION_TOOL__FILTERS:
            return getFilters();
        case ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT:
            return getElementsToSelect();
        case ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER:
            return isInverseSelectionOrder();
        case ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS:
            return getEdgeMappings();
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE:
            if (resolve) {
                return getSourceVariable();
            }
            return basicGetSourceVariable();
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE:
            if (resolve) {
                return getTargetVariable();
            }
            return basicGetTargetVariable();
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE:
            if (resolve) {
                return getSourceViewVariable();
            }
            return basicGetSourceViewVariable();
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE:
            if (resolve) {
                return getTargetViewVariable();
            }
            return basicGetTargetViewVariable();
        case ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION:
            if (resolve) {
                return getInitialOperation();
            }
            return basicGetInitialOperation();
        case ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH:
            return getIconPath();
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS:
            return getExtraSourceMappings();
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS:
            return getExtraTargetMappings();
        case ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION:
            return getConnectionStartPrecondition();
        case ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR:
            return getStartingEndPredecessor();
        case ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            return getFinishingEndPredecessor();
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
        case ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__NAME:
            setName((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__LABEL:
            setLabel((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION:
            setPrecondition((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH:
            setForceRefresh((Boolean) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__FILTERS:
            getFilters().clear();
            getFilters().addAll((Collection<? extends ToolFilterDescription>) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT:
            setElementsToSelect((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder((Boolean) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            getEdgeMappings().addAll((Collection<? extends EdgeMapping>) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE:
            setSourceVariable((SourceEdgeCreationVariable) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE:
            setTargetVariable((TargetEdgeCreationVariable) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE:
            setSourceViewVariable((SourceEdgeViewCreationVariable) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE:
            setTargetViewVariable((TargetEdgeViewCreationVariable) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION:
            setInitialOperation((InitEdgeCreationOperation) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS:
            getExtraSourceMappings().clear();
            getExtraSourceMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS:
            getExtraTargetMappings().clear();
            getExtraTargetMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION:
            setConnectionStartPrecondition((String) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR:
            setStartingEndPredecessor((MessageEndVariable) newValue);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            setFinishingEndPredecessor((MessageEndVariable) newValue);
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
        case ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION:
            setDocumentation(MessageCreationToolImpl.DOCUMENTATION_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__NAME:
            setName(MessageCreationToolImpl.NAME_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__LABEL:
            setLabel(MessageCreationToolImpl.LABEL_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION:
            setPrecondition(MessageCreationToolImpl.PRECONDITION_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH:
            setForceRefresh(MessageCreationToolImpl.FORCE_REFRESH_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__FILTERS:
            getFilters().clear();
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT:
            setElementsToSelect(MessageCreationToolImpl.ELEMENTS_TO_SELECT_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder(MessageCreationToolImpl.INVERSE_SELECTION_ORDER_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE:
            setSourceVariable((SourceEdgeCreationVariable) null);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE:
            setTargetVariable((TargetEdgeCreationVariable) null);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE:
            setSourceViewVariable((SourceEdgeViewCreationVariable) null);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE:
            setTargetViewVariable((TargetEdgeViewCreationVariable) null);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION:
            setInitialOperation((InitEdgeCreationOperation) null);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH:
            setIconPath(MessageCreationToolImpl.ICON_PATH_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS:
            getExtraSourceMappings().clear();
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS:
            getExtraTargetMappings().clear();
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION:
            setConnectionStartPrecondition(MessageCreationToolImpl.CONNECTION_START_PRECONDITION_EDEFAULT);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR:
            setStartingEndPredecessor((MessageEndVariable) null);
            return;
        case ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            setFinishingEndPredecessor((MessageEndVariable) null);
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
        case ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION:
            return MessageCreationToolImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !MessageCreationToolImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case ToolPackage.MESSAGE_CREATION_TOOL__NAME:
            return MessageCreationToolImpl.NAME_EDEFAULT == null ? name != null : !MessageCreationToolImpl.NAME_EDEFAULT.equals(name);
        case ToolPackage.MESSAGE_CREATION_TOOL__LABEL:
            return MessageCreationToolImpl.LABEL_EDEFAULT == null ? label != null : !MessageCreationToolImpl.LABEL_EDEFAULT.equals(label);
        case ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION:
            return MessageCreationToolImpl.PRECONDITION_EDEFAULT == null ? precondition != null : !MessageCreationToolImpl.PRECONDITION_EDEFAULT.equals(precondition);
        case ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH:
            return forceRefresh != MessageCreationToolImpl.FORCE_REFRESH_EDEFAULT;
        case ToolPackage.MESSAGE_CREATION_TOOL__FILTERS:
            return filters != null && !filters.isEmpty();
        case ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT:
            return MessageCreationToolImpl.ELEMENTS_TO_SELECT_EDEFAULT == null ? elementsToSelect != null : !MessageCreationToolImpl.ELEMENTS_TO_SELECT_EDEFAULT.equals(elementsToSelect);
        case ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER:
            return inverseSelectionOrder != MessageCreationToolImpl.INVERSE_SELECTION_ORDER_EDEFAULT;
        case ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS:
            return edgeMappings != null && !edgeMappings.isEmpty();
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE:
            return sourceVariable != null;
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE:
            return targetVariable != null;
        case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE:
            return sourceViewVariable != null;
        case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE:
            return targetViewVariable != null;
        case ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION:
            return initialOperation != null;
        case ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH:
            return MessageCreationToolImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !MessageCreationToolImpl.ICON_PATH_EDEFAULT.equals(iconPath);
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS:
            return extraSourceMappings != null && !extraSourceMappings.isEmpty();
        case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS:
            return extraTargetMappings != null && !extraTargetMappings.isEmpty();
        case ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION:
            return MessageCreationToolImpl.CONNECTION_START_PRECONDITION_EDEFAULT == null ? connectionStartPrecondition != null : !MessageCreationToolImpl.CONNECTION_START_PRECONDITION_EDEFAULT
                    .equals(connectionStartPrecondition);
        case ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR:
            return startingEndPredecessor != null;
        case ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR:
            return finishingEndPredecessor != null;
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
            case ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION:
                return DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case ToolPackage.MESSAGE_CREATION_TOOL__NAME:
                return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case ToolPackage.MESSAGE_CREATION_TOOL__LABEL:
                return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == ToolEntry.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == AbstractToolDescription.class) {
            switch (derivedFeatureID) {
            case ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION:
                return org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;
            case ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH:
                return org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;
            case ToolPackage.MESSAGE_CREATION_TOOL__FILTERS:
                return org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;
            case ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT:
                return org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;
            case ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER:
                return org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;
            default:
                return -1;
            }
        }
        if (baseClass == MappingBasedToolDescription.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == EdgeCreationDescription.class) {
            switch (derivedFeatureID) {
            case ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS;
            case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE;
            case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE;
            case ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE;
            case ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE;
            case ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION;
            case ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH;
            case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS;
            case ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS;
            case ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION:
                return org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION;
            default:
                return -1;
            }
        }
        if (baseClass == OrderedElementCreationTool.class) {
            switch (derivedFeatureID) {
            case ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR:
                return ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR;
            case ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR:
                return ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR;
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
            case DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return ToolPackage.MESSAGE_CREATION_TOOL__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return ToolPackage.MESSAGE_CREATION_TOOL__NAME;
            case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return ToolPackage.MESSAGE_CREATION_TOOL__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == ToolEntry.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == AbstractToolDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
                return ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION;
            case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH:
                return ToolPackage.MESSAGE_CREATION_TOOL__FORCE_REFRESH;
            case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
                return ToolPackage.MESSAGE_CREATION_TOOL__FILTERS;
            case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
                return ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT;
            case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER:
                return ToolPackage.MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER;
            default:
                return -1;
            }
        }
        if (baseClass == MappingBasedToolDescription.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == EdgeCreationDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS:
                return ToolPackage.MESSAGE_CREATION_TOOL__EDGE_MAPPINGS;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE:
                return ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VARIABLE;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE:
                return ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VARIABLE;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE:
                return ToolPackage.MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE:
                return ToolPackage.MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION:
                return ToolPackage.MESSAGE_CREATION_TOOL__INITIAL_OPERATION;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__ICON_PATH:
                return ToolPackage.MESSAGE_CREATION_TOOL__ICON_PATH;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS:
                return ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS:
                return ToolPackage.MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS;
            case org.eclipse.sirius.diagram.description.tool.ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
                return ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION;
            default:
                return -1;
            }
        }
        if (baseClass == OrderedElementCreationTool.class) {
            switch (baseFeatureID) {
            case ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR:
                return ToolPackage.MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR;
            case ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR:
                return ToolPackage.MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR;
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
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", precondition: "); //$NON-NLS-1$
        result.append(precondition);
        result.append(", forceRefresh: "); //$NON-NLS-1$
        result.append(forceRefresh);
        result.append(", elementsToSelect: "); //$NON-NLS-1$
        result.append(elementsToSelect);
        result.append(", inverseSelectionOrder: "); //$NON-NLS-1$
        result.append(inverseSelectionOrder);
        result.append(", iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(", connectionStartPrecondition: "); //$NON-NLS-1$
        result.append(connectionStartPrecondition);
        result.append(')');
        return result.toString();
    }

} // MessageCreationToolImpl

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
package org.eclipse.sirius.diagram.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.impl.RepresentationElementMappingImpl;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram Element Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getPasteDescriptions
 * <em>Paste Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getDeletionDescription
 * <em>Deletion Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getLabelDirectEdit
 * <em>Label Direct Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#isCreateElements
 * <em>Create Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getSemanticElements
 * <em>Semantic Elements</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#getDoubleClickDescription
 * <em>Double Click Description</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl#isSynchronizationLock
 * <em>Synchronization Lock</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DiagramElementMappingImpl extends RepresentationElementMappingImpl implements DiagramElementMapping {
    /**
     * The cached value of the '{@link #getPasteDescriptions()
     * <em>Paste Descriptions</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getPasteDescriptions()
     * @generated
     * @ordered
     */
    protected EList<PasteDescription> pasteDescriptions;

    /**
     * The default value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = DiagramElementMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getDeletionDescription()
     * <em>Deletion Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDeletionDescription()
     * @generated
     * @ordered
     */
    protected DeleteElementDescription deletionDescription;

    /**
     * The cached value of the '{@link #getLabelDirectEdit()
     * <em>Label Direct Edit</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelDirectEdit()
     * @generated
     * @ordered
     */
    protected DirectEditLabel labelDirectEdit;

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
    protected String semanticCandidatesExpression = DiagramElementMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isCreateElements()
     * <em>Create Elements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isCreateElements()
     * @generated
     * @ordered
     */
    protected static final boolean CREATE_ELEMENTS_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isCreateElements()
     * <em>Create Elements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isCreateElements()
     * @generated
     * @ordered
     */
    protected boolean createElements = DiagramElementMappingImpl.CREATE_ELEMENTS_EDEFAULT;

    /**
     * The default value of the '{@link #getSemanticElements()
     * <em>Semantic Elements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSemanticElements()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_ELEMENTS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSemanticElements()
     * <em>Semantic Elements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSemanticElements()
     * @generated
     * @ordered
     */
    protected String semanticElements = DiagramElementMappingImpl.SEMANTIC_ELEMENTS_EDEFAULT;

    /**
     * The cached value of the '{@link #getDoubleClickDescription()
     * <em>Double Click Description</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDoubleClickDescription()
     * @generated
     * @ordered
     */
    protected DoubleClickDescription doubleClickDescription;

    /**
     * The default value of the '{@link #isSynchronizationLock()
     * <em>Synchronization Lock</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isSynchronizationLock()
     * @generated
     * @ordered
     */
    protected static final boolean SYNCHRONIZATION_LOCK_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isSynchronizationLock()
     * <em>Synchronization Lock</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isSynchronizationLock()
     * @generated
     * @ordered
     */
    protected boolean synchronizationLock = DiagramElementMappingImpl.SYNCHRONIZATION_LOCK_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DiagramElementMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PasteDescription> getPasteDescriptions() {
        if (pasteDescriptions == null) {
            pasteDescriptions = new EObjectResolvingEList<PasteDescription>(PasteDescription.class, this, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS);
        }
        return pasteDescriptions;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DeleteElementDescription getDeletionDescription() {
        if (deletionDescription != null && deletionDescription.eIsProxy()) {
            InternalEObject oldDeletionDescription = (InternalEObject) deletionDescription;
            deletionDescription = (DeleteElementDescription) eResolveProxy(oldDeletionDescription);
            if (deletionDescription != oldDeletionDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION, oldDeletionDescription, deletionDescription));
                }
            }
        }
        return deletionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DeleteElementDescription basicGetDeletionDescription() {
        return deletionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDeletionDescription(DeleteElementDescription newDeletionDescription) {
        DeleteElementDescription oldDeletionDescription = deletionDescription;
        deletionDescription = newDeletionDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION, oldDeletionDescription, deletionDescription));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DirectEditLabel getLabelDirectEdit() {
        if (labelDirectEdit != null && labelDirectEdit.eIsProxy()) {
            InternalEObject oldLabelDirectEdit = (InternalEObject) labelDirectEdit;
            labelDirectEdit = (DirectEditLabel) eResolveProxy(oldLabelDirectEdit);
            if (labelDirectEdit != oldLabelDirectEdit) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT, oldLabelDirectEdit, labelDirectEdit));
                }
            }
        }
        return labelDirectEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DirectEditLabel basicGetLabelDirectEdit() {
        return labelDirectEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelDirectEdit(DirectEditLabel newLabelDirectEdit) {
        DirectEditLabel oldLabelDirectEdit = labelDirectEdit;
        labelDirectEdit = newLabelDirectEdit;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT, oldLabelDirectEdit, labelDirectEdit));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, oldSemanticCandidatesExpression,
                    semanticCandidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isCreateElements() {
        return createElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCreateElements(boolean newCreateElements) {
        boolean oldCreateElements = createElements;
        createElements = newCreateElements;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS, oldCreateElements, createElements));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSemanticElements() {
        return semanticElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSemanticElements(String newSemanticElements) {
        String oldSemanticElements = semanticElements;
        semanticElements = newSemanticElements;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS, oldSemanticElements, semanticElements));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DoubleClickDescription getDoubleClickDescription() {
        if (doubleClickDescription != null && doubleClickDescription.eIsProxy()) {
            InternalEObject oldDoubleClickDescription = (InternalEObject) doubleClickDescription;
            doubleClickDescription = (DoubleClickDescription) eResolveProxy(oldDoubleClickDescription);
            if (doubleClickDescription != oldDoubleClickDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION, oldDoubleClickDescription, doubleClickDescription));
                }
            }
        }
        return doubleClickDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DoubleClickDescription basicGetDoubleClickDescription() {
        return doubleClickDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDoubleClickDescription(DoubleClickDescription newDoubleClickDescription, NotificationChain msgs) {
        DoubleClickDescription oldDoubleClickDescription = doubleClickDescription;
        doubleClickDescription = newDoubleClickDescription;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION, oldDoubleClickDescription,
                    newDoubleClickDescription);
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
    public void setDoubleClickDescription(DoubleClickDescription newDoubleClickDescription) {
        if (newDoubleClickDescription != doubleClickDescription) {
            NotificationChain msgs = null;
            if (doubleClickDescription != null) {
                msgs = ((InternalEObject) doubleClickDescription).eInverseRemove(this, ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS, DoubleClickDescription.class, msgs);
            }
            if (newDoubleClickDescription != null) {
                msgs = ((InternalEObject) newDoubleClickDescription).eInverseAdd(this, ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS, DoubleClickDescription.class, msgs);
            }
            msgs = basicSetDoubleClickDescription(newDoubleClickDescription, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION, newDoubleClickDescription, newDoubleClickDescription));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isSynchronizationLock() {
        return synchronizationLock;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSynchronizationLock(boolean newSynchronizationLock) {
        boolean oldSynchronizationLock = synchronizationLock;
        synchronizationLock = newSynchronizationLock;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK, oldSynchronizationLock, synchronizationLock));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean checkPrecondition(EObject modelElement, EObject container, EObject containerView) {
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
    public EList<DiagramElementMapping> getAllMappings() {
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
    public boolean isFrom(DMappingBased element) {
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
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION:
            if (doubleClickDescription != null) {
                msgs = ((InternalEObject) doubleClickDescription).eInverseRemove(this, ToolPackage.DOUBLE_CLICK_DESCRIPTION__MAPPINGS, DoubleClickDescription.class, msgs);
            }
            return basicSetDoubleClickDescription((DoubleClickDescription) otherEnd, msgs);
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
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION:
            return basicSetDoubleClickDescription(null, msgs);
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
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS:
            return getPasteDescriptions();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION:
            if (resolve) {
                return getDeletionDescription();
            }
            return basicGetDeletionDescription();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT:
            if (resolve) {
                return getLabelDirectEdit();
            }
            return basicGetLabelDirectEdit();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return getSemanticCandidatesExpression();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS:
            return isCreateElements();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS:
            return getSemanticElements();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION:
            if (resolve) {
                return getDoubleClickDescription();
            }
            return basicGetDoubleClickDescription();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK:
            return isSynchronizationLock();
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
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS:
            getPasteDescriptions().clear();
            getPasteDescriptions().addAll((Collection<? extends PasteDescription>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION:
            setDeletionDescription((DeleteElementDescription) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT:
            setLabelDirectEdit((DirectEditLabel) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS:
            setCreateElements((Boolean) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS:
            setSemanticElements((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION:
            setDoubleClickDescription((DoubleClickDescription) newValue);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK:
            setSynchronizationLock((Boolean) newValue);
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
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS:
            getPasteDescriptions().clear();
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION:
            setPreconditionExpression(DiagramElementMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION:
            setDeletionDescription((DeleteElementDescription) null);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT:
            setLabelDirectEdit((DirectEditLabel) null);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression(DiagramElementMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS:
            setCreateElements(DiagramElementMappingImpl.CREATE_ELEMENTS_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS:
            setSemanticElements(DiagramElementMappingImpl.SEMANTIC_ELEMENTS_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION:
            setDoubleClickDescription((DoubleClickDescription) null);
            return;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK:
            setSynchronizationLock(DiagramElementMappingImpl.SYNCHRONIZATION_LOCK_EDEFAULT);
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
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS:
            return pasteDescriptions != null && !pasteDescriptions.isEmpty();
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION:
            return DiagramElementMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null
                    : !DiagramElementMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION:
            return deletionDescription != null;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT:
            return labelDirectEdit != null;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return DiagramElementMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT == null ? semanticCandidatesExpression != null
                    : !DiagramElementMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT.equals(semanticCandidatesExpression);
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS:
            return createElements != DiagramElementMappingImpl.CREATE_ELEMENTS_EDEFAULT;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS:
            return DiagramElementMappingImpl.SEMANTIC_ELEMENTS_EDEFAULT == null ? semanticElements != null : !DiagramElementMappingImpl.SEMANTIC_ELEMENTS_EDEFAULT.equals(semanticElements);
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION:
            return doubleClickDescription != null;
        case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK:
            return synchronizationLock != DiagramElementMappingImpl.SYNCHRONIZATION_LOCK_EDEFAULT;
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
        if (baseClass == PasteTargetDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS;
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
        if (baseClass == PasteTargetDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS:
                return DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS;
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
        result.append(" (preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(", semanticCandidatesExpression: "); //$NON-NLS-1$
        result.append(semanticCandidatesExpression);
        result.append(", createElements: "); //$NON-NLS-1$
        result.append(createElements);
        result.append(", semanticElements: "); //$NON-NLS-1$
        result.append(semanticElements);
        result.append(", synchronizationLock: "); //$NON-NLS-1$
        result.append(synchronizationLock);
        result.append(')');
        return result.toString();
    }

} // DiagramElementMappingImpl

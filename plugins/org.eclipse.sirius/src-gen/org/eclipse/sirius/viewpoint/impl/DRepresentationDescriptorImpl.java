/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.representation.DRepresentationDescriptorToDRepresentationLinkManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>DRepresentation Descriptor</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getEAnnotations
 * <em>EAnnotations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getDescription <em>Description</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getRepresentation
 * <em>Representation</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getRepPath <em>Rep Path</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DRepresentationDescriptorImpl#getChangeId <em>Change Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DRepresentationDescriptorImpl extends IdentifiedElementImpl implements DRepresentationDescriptor {
    /**
     * The cached value of the '{@link #getEAnnotations() <em>EAnnotations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEAnnotations()
     * @generated
     * @ordered
     */
    protected EList<DAnnotation> eAnnotations;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = DRepresentationDescriptorImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected RepresentationDescription description;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObject target;

    /**
     * The default value of the '{@link #getRepPath() <em>Rep Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRepPath()
     * @generated
     * @ordered
     */
    protected static final ResourceDescriptor REP_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepPath() <em>Rep Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRepPath()
     * @generated
     * @ordered
     */
    protected ResourceDescriptor repPath = DRepresentationDescriptorImpl.REP_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getChangeId() <em>Change Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getChangeId()
     * @generated
     * @ordered
     */
    protected static final String CHANGE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getChangeId() <em>Change Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getChangeId()
     * @generated
     * @ordered
     */
    protected String changeId = DRepresentationDescriptorImpl.CHANGE_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DRepresentationDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DAnnotation> getEAnnotations() {
        if (eAnnotations == null) {
            eAnnotations = new EObjectContainmentEList<DAnnotation>(DAnnotation.class, this, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS);
        }
        return eAnnotations;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RepresentationDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (RepresentationDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION, oldDescription, description));
                }
            }
        }
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RepresentationDescription basicGetDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDescription(RepresentationDescription newDescription) {
        RepresentationDescription oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET, oldTarget, target));
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
    public EObject basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTarget(EObject newTarget) {
        EObject oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET, oldTarget, target));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DRepresentation getRepresentation() {
        DRepresentationDescriptorToDRepresentationLinkManager pathManager = new DRepresentationDescriptorToDRepresentationLinkManager(this);
        Optional<DRepresentation> representation = pathManager.getRepresentation(true);
        return representation.orElse(null);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    public DRepresentation basicGetRepresentation() {
        DRepresentationDescriptorToDRepresentationLinkManager pathManager = new DRepresentationDescriptorToDRepresentationLinkManager(this);
        Optional<DRepresentation> representation = pathManager.getRepresentation(false);
        return representation.orElse(null);
    }

    /**
     * <!-- begin-user-doc --><!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setRepresentation(DRepresentation newRepresentation) {
        DRepresentation oldRepresentation = getRepresentation();
        updateRepresentation(newRepresentation);
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION, oldRepresentation, newRepresentation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceDescriptor getRepPath() {
        return repPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRepPath(ResourceDescriptor newRepPath) {
        ResourceDescriptor oldRepPath = repPath;
        repPath = newRepPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH, oldRepPath, repPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getChangeId() {
        return changeId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setChangeId(String newChangeId) {
        String oldChangeId = changeId;
        changeId = newChangeId;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID, oldChangeId, changeId));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DAnnotation getDAnnotation(String source) {
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
            return ((InternalEList<?>) getEAnnotations()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
            return getEAnnotations();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            return getName();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            if (resolve) {
                return getDescription();
            }
            return basicGetDescription();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            if (resolve) {
                return getTarget();
            }
            return basicGetTarget();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            if (resolve) {
                return getRepresentation();
            }
            return basicGetRepresentation();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH:
            return getRepPath();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID:
            return getChangeId();
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
            getEAnnotations().clear();
            getEAnnotations().addAll((Collection<? extends DAnnotation>) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            setName((String) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            setDescription((RepresentationDescription) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            setTarget((EObject) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            setRepresentation((DRepresentation) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH:
            setRepPath((ResourceDescriptor) newValue);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID:
            setChangeId((String) newValue);
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
            getEAnnotations().clear();
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            setName(DRepresentationDescriptorImpl.NAME_EDEFAULT);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            setDescription((RepresentationDescription) null);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            setTarget((EObject) null);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            setRepresentation((DRepresentation) null);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH:
            setRepPath(DRepresentationDescriptorImpl.REP_PATH_EDEFAULT);
            return;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID:
            setChangeId(DRepresentationDescriptorImpl.CHANGE_ID_EDEFAULT);
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
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
            return eAnnotations != null && !eAnnotations.isEmpty();
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__NAME:
            return DRepresentationDescriptorImpl.NAME_EDEFAULT == null ? name != null : !DRepresentationDescriptorImpl.NAME_EDEFAULT.equals(name);
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__DESCRIPTION:
            return description != null;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__TARGET:
            return target != null;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REPRESENTATION:
            return basicGetRepresentation() != null;
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__REP_PATH:
            return DRepresentationDescriptorImpl.REP_PATH_EDEFAULT == null ? repPath != null : !DRepresentationDescriptorImpl.REP_PATH_EDEFAULT.equals(repPath);
        case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__CHANGE_ID:
            return DRepresentationDescriptorImpl.CHANGE_ID_EDEFAULT == null ? changeId != null : !DRepresentationDescriptorImpl.CHANGE_ID_EDEFAULT.equals(changeId);
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
        if (baseClass == DModelElement.class) {
            switch (derivedFeatureID) {
            case ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS:
                return DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS;
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
        if (baseClass == DModelElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DMODEL_ELEMENT__EANNOTATIONS:
                return ViewpointPackage.DREPRESENTATION_DESCRIPTOR__EANNOTATIONS;
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", repPath: "); //$NON-NLS-1$
        result.append(repPath);
        result.append(", changeId: "); //$NON-NLS-1$
        result.append(changeId);
        result.append(')');
        return result.toString();
    }

} // DRepresentationDescriptorImpl

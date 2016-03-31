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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram Extension Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getViewpointURI
 * <em>Viewpoint URI</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getRepresentationName
 * <em>Representation Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getMetamodel
 * <em>Metamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getLayers
 * <em>Layers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getValidationSet
 * <em>Validation Set</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl#getConcerns
 * <em>Concerns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramExtensionDescriptionImpl extends MinimalEObjectImpl.Container implements DiagramExtensionDescription {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = DiagramExtensionDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getViewpointURI()
     * <em>Viewpoint URI</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getViewpointURI()
     * @generated
     * @ordered
     */
    protected static final String VIEWPOINT_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getViewpointURI() <em>Viewpoint URI</em>
     * }' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getViewpointURI()
     * @generated
     * @ordered
     */
    protected String viewpointURI = DiagramExtensionDescriptionImpl.VIEWPOINT_URI_EDEFAULT;

    /**
     * The default value of the '{@link #getRepresentationName()
     * <em>Representation Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRepresentationName()
     * @generated
     * @ordered
     */
    protected static final String REPRESENTATION_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepresentationName()
     * <em>Representation Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRepresentationName()
     * @generated
     * @ordered
     */
    protected String representationName = DiagramExtensionDescriptionImpl.REPRESENTATION_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMetamodel()
     * @generated
     * @ordered
     */
    protected EList<EPackage> metamodel;

    /**
     * The cached value of the '{@link #getLayers() <em>Layers</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLayers()
     * @generated
     * @ordered
     */
    protected EList<AdditionalLayer> layers;

    /**
     * The cached value of the '{@link #getValidationSet()
     * <em>Validation Set</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getValidationSet()
     * @generated
     * @ordered
     */
    protected ValidationSet validationSet;

    /**
     * The cached value of the '{@link #getConcerns() <em>Concerns</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConcerns()
     * @generated
     * @ordered
     */
    protected ConcernSet concerns;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DiagramExtensionDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DIAGRAM_EXTENSION_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getViewpointURI() {
        return viewpointURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setViewpointURI(String newViewpointURI) {
        String oldViewpointURI = viewpointURI;
        viewpointURI = newViewpointURI;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI, oldViewpointURI, viewpointURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getRepresentationName() {
        return representationName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRepresentationName(String newRepresentationName) {
        String oldRepresentationName = representationName;
        representationName = newRepresentationName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME, oldRepresentationName, representationName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EPackage> getMetamodel() {
        if (metamodel == null) {
            metamodel = new EObjectResolvingEList<EPackage>(EPackage.class, this, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL);
        }
        return metamodel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AdditionalLayer> getLayers() {
        if (layers == null) {
            layers = new EObjectContainmentEList.Resolving<AdditionalLayer>(AdditionalLayer.class, this, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS);
        }
        return layers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ValidationSet getValidationSet() {
        if (validationSet != null && validationSet.eIsProxy()) {
            InternalEObject oldValidationSet = (InternalEObject) validationSet;
            validationSet = (ValidationSet) eResolveProxy(oldValidationSet);
            if (validationSet != oldValidationSet) {
                InternalEObject newValidationSet = (InternalEObject) validationSet;
                NotificationChain msgs = oldValidationSet.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, null, null);
                if (newValidationSet.eInternalContainer() == null) {
                    msgs = newValidationSet.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, oldValidationSet, validationSet));
                }
            }
        }
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ValidationSet basicGetValidationSet() {
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetValidationSet(ValidationSet newValidationSet, NotificationChain msgs) {
        ValidationSet oldValidationSet = validationSet;
        validationSet = newValidationSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, oldValidationSet, newValidationSet);
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
    public void setValidationSet(ValidationSet newValidationSet) {
        if (newValidationSet != validationSet) {
            NotificationChain msgs = null;
            if (validationSet != null) {
                msgs = ((InternalEObject) validationSet).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            if (newValidationSet != null) {
                msgs = ((InternalEObject) newValidationSet).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            msgs = basicSetValidationSet(newValidationSet, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET, newValidationSet, newValidationSet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ConcernSet getConcerns() {
        if (concerns != null && concerns.eIsProxy()) {
            InternalEObject oldConcerns = (InternalEObject) concerns;
            concerns = (ConcernSet) eResolveProxy(oldConcerns);
            if (concerns != oldConcerns) {
                InternalEObject newConcerns = (InternalEObject) concerns;
                NotificationChain msgs = oldConcerns.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, null, null);
                if (newConcerns.eInternalContainer() == null) {
                    msgs = newConcerns.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, oldConcerns, concerns));
                }
            }
        }
        return concerns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConcernSet basicGetConcerns() {
        return concerns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetConcerns(ConcernSet newConcerns, NotificationChain msgs) {
        ConcernSet oldConcerns = concerns;
        concerns = newConcerns;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, oldConcerns, newConcerns);
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
    public void setConcerns(ConcernSet newConcerns) {
        if (newConcerns != concerns) {
            NotificationChain msgs = null;
            if (concerns != null) {
                msgs = ((InternalEObject) concerns).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, null, msgs);
            }
            if (newConcerns != null) {
                msgs = ((InternalEObject) newConcerns).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, null, msgs);
            }
            msgs = basicSetConcerns(newConcerns, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS, newConcerns, newConcerns));
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
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS:
            return ((InternalEList<?>) getLayers()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET:
            return basicSetValidationSet(null, msgs);
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS:
            return basicSetConcerns(null, msgs);
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
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__NAME:
            return getName();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            return getViewpointURI();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            return getRepresentationName();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL:
            return getMetamodel();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS:
            return getLayers();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET:
            if (resolve) {
                return getValidationSet();
            }
            return basicGetValidationSet();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS:
            if (resolve) {
                return getConcerns();
            }
            return basicGetConcerns();
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
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            setViewpointURI((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            setRepresentationName((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            getMetamodel().addAll((Collection<? extends EPackage>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS:
            getLayers().clear();
            getLayers().addAll((Collection<? extends AdditionalLayer>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET:
            setValidationSet((ValidationSet) newValue);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS:
            setConcerns((ConcernSet) newValue);
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
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__NAME:
            setName(DiagramExtensionDescriptionImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            setViewpointURI(DiagramExtensionDescriptionImpl.VIEWPOINT_URI_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            setRepresentationName(DiagramExtensionDescriptionImpl.REPRESENTATION_NAME_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS:
            getLayers().clear();
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET:
            setValidationSet((ValidationSet) null);
            return;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS:
            setConcerns((ConcernSet) null);
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
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__NAME:
            return DiagramExtensionDescriptionImpl.NAME_EDEFAULT == null ? name != null : !DiagramExtensionDescriptionImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI:
            return DiagramExtensionDescriptionImpl.VIEWPOINT_URI_EDEFAULT == null ? viewpointURI != null : !DiagramExtensionDescriptionImpl.VIEWPOINT_URI_EDEFAULT.equals(viewpointURI);
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME:
            return DiagramExtensionDescriptionImpl.REPRESENTATION_NAME_EDEFAULT == null ? representationName != null
                    : !DiagramExtensionDescriptionImpl.REPRESENTATION_NAME_EDEFAULT.equals(representationName);
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL:
            return metamodel != null && !metamodel.isEmpty();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__LAYERS:
            return layers != null && !layers.isEmpty();
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET:
            return validationSet != null;
        case DescriptionPackage.DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS:
            return concerns != null;
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", viewpointURI: "); //$NON-NLS-1$
        result.append(viewpointURI);
        result.append(", representationName: "); //$NON-NLS-1$
        result.append(representationName);
        result.append(')');
        return result.toString();
    }

} // DiagramExtensionDescriptionImpl

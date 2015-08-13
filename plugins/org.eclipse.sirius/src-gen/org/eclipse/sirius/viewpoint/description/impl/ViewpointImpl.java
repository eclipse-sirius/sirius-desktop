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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Viewpoint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getEndUserDocumentation
 * <em>End User Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getModelFileExtension
 * <em>Model File Extension</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getValidationSet
 * <em>Validation Set</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getOwnedRepresentations
 * <em>Owned Representations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getOwnedRepresentationExtensions
 * <em>Owned Representation Extensions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getOwnedJavaExtensions
 * <em>Owned Java Extensions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getOwnedMMExtensions
 * <em>Owned MM Extensions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getOwnedFeatureExtensions
 * <em>Owned Feature Extensions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getIcon
 * <em>Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getOwnedTemplates
 * <em>Owned Templates</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getConflicts
 * <em>Conflicts</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getReuses
 * <em>Reuses</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#getCustomizes
 * <em>Customizes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewpointImpl extends DocumentedElementImpl implements Viewpoint {
    /**
     * The default value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected static final String END_USER_DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected String endUserDocumentation = ViewpointImpl.END_USER_DOCUMENTATION_EDEFAULT;

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
    protected String name = ViewpointImpl.NAME_EDEFAULT;

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
    protected String label = ViewpointImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getModelFileExtension()
     * <em>Model File Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getModelFileExtension()
     * @generated
     * @ordered
     */
    protected static final String MODEL_FILE_EXTENSION_EDEFAULT = "*"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getModelFileExtension()
     * <em>Model File Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getModelFileExtension()
     * @generated
     * @ordered
     */
    protected String modelFileExtension = ViewpointImpl.MODEL_FILE_EXTENSION_EDEFAULT;

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
     * The cached value of the '{@link #getOwnedRepresentations()
     * <em>Owned Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedRepresentations()
     * @generated
     * @ordered
     */
    protected EList<RepresentationDescription> ownedRepresentations;

    /**
     * The cached value of the '{@link #getOwnedRepresentationExtensions()
     * <em>Owned Representation Extensions</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRepresentationExtensions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationExtensionDescription> ownedRepresentationExtensions;

    /**
     * The cached value of the '{@link #getOwnedJavaExtensions()
     * <em>Owned Java Extensions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedJavaExtensions()
     * @generated
     * @ordered
     */
    protected EList<JavaExtension> ownedJavaExtensions;

    /**
     * The cached value of the '{@link #getOwnedMMExtensions()
     * <em>Owned MM Extensions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedMMExtensions()
     * @generated
     * @ordered
     */
    protected EList<MetamodelExtensionSetting> ownedMMExtensions;

    /**
     * The cached value of the '{@link #getOwnedFeatureExtensions()
     * <em>Owned Feature Extensions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedFeatureExtensions()
     * @generated
     * @ordered
     */
    protected EList<FeatureExtensionDescription> ownedFeatureExtensions;

    /**
     * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected static final String ICON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected String icon = ViewpointImpl.ICON_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedTemplates()
     * <em>Owned Templates</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedTemplates()
     * @generated
     * @ordered
     */
    protected EList<RepresentationTemplate> ownedTemplates;

    /**
     * The cached value of the '{@link #getConflicts() <em>Conflicts</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getConflicts()
     * @generated
     * @ordered
     */
    protected EList<URI> conflicts;

    /**
     * The cached value of the '{@link #getReuses() <em>Reuses</em>}' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReuses()
     * @generated
     * @ordered
     */
    protected EList<URI> reuses;

    /**
     * The cached value of the '{@link #getCustomizes() <em>Customizes</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCustomizes()
     * @generated
     * @ordered
     */
    protected EList<URI> customizes;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ViewpointImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.VIEWPOINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEndUserDocumentation() {
        return endUserDocumentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEndUserDocumentation(String newEndUserDocumentation) {
        String oldEndUserDocumentation = endUserDocumentation;
        endUserDocumentation = newEndUserDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION, oldEndUserDocumentation, endUserDocumentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getModelFileExtension() {
        return modelFileExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setModelFileExtension(String newModelFileExtension) {
        String oldModelFileExtension = modelFileExtension;
        modelFileExtension = newModelFileExtension;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION, oldModelFileExtension, modelFileExtension));
        }
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
                NotificationChain msgs = oldValidationSet.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.VIEWPOINT__VALIDATION_SET, null, null);
                if (newValidationSet.eInternalContainer() == null) {
                    msgs = newValidationSet.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.VIEWPOINT__VALIDATION_SET, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.VIEWPOINT__VALIDATION_SET, oldValidationSet, validationSet));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__VALIDATION_SET, oldValidationSet, newValidationSet);
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
                msgs = ((InternalEObject) validationSet).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.VIEWPOINT__VALIDATION_SET, null, msgs);
            }
            if (newValidationSet != null) {
                msgs = ((InternalEObject) newValidationSet).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.VIEWPOINT__VALIDATION_SET, null, msgs);
            }
            msgs = basicSetValidationSet(newValidationSet, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__VALIDATION_SET, newValidationSet, newValidationSet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationDescription> getOwnedRepresentations() {
        if (ownedRepresentations == null) {
            ownedRepresentations = new EObjectContainmentEList.Resolving<RepresentationDescription>(RepresentationDescription.class, this, DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS);
        }
        return ownedRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationExtensionDescription> getOwnedRepresentationExtensions() {
        if (ownedRepresentationExtensions == null) {
            ownedRepresentationExtensions = new EObjectContainmentEList.Resolving<RepresentationExtensionDescription>(RepresentationExtensionDescription.class, this,
                    DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS);
        }
        return ownedRepresentationExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<JavaExtension> getOwnedJavaExtensions() {
        if (ownedJavaExtensions == null) {
            ownedJavaExtensions = new EObjectContainmentEList.Resolving<JavaExtension>(JavaExtension.class, this, DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS);
        }
        return ownedJavaExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<MetamodelExtensionSetting> getOwnedMMExtensions() {
        if (ownedMMExtensions == null) {
            ownedMMExtensions = new EObjectContainmentEList.Resolving<MetamodelExtensionSetting>(MetamodelExtensionSetting.class, this, DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS);
        }
        return ownedMMExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<FeatureExtensionDescription> getOwnedFeatureExtensions() {
        if (ownedFeatureExtensions == null) {
            ownedFeatureExtensions = new EObjectContainmentEList.Resolving<FeatureExtensionDescription>(FeatureExtensionDescription.class, this, DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS);
        }
        return ownedFeatureExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIcon(String newIcon) {
        String oldIcon = icon;
        icon = newIcon;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.VIEWPOINT__ICON, oldIcon, icon));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationTemplate> getOwnedTemplates() {
        if (ownedTemplates == null) {
            ownedTemplates = new EObjectContainmentEList.Resolving<RepresentationTemplate>(RepresentationTemplate.class, this, DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES);
        }
        return ownedTemplates;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<URI> getConflicts() {
        if (conflicts == null) {
            conflicts = new EDataTypeUniqueEList<URI>(URI.class, this, DescriptionPackage.VIEWPOINT__CONFLICTS);
        }
        return conflicts;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<URI> getReuses() {
        if (reuses == null) {
            reuses = new EDataTypeUniqueEList<URI>(URI.class, this, DescriptionPackage.VIEWPOINT__REUSES);
        }
        return reuses;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<URI> getCustomizes() {
        if (customizes == null) {
            customizes = new EDataTypeUniqueEList<URI>(URI.class, this, DescriptionPackage.VIEWPOINT__CUSTOMIZES);
        }
        return customizes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void initView(EObject model) {
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
        case DescriptionPackage.VIEWPOINT__VALIDATION_SET:
            return basicSetValidationSet(null, msgs);
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS:
            return ((InternalEList<?>) getOwnedRepresentations()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS:
            return ((InternalEList<?>) getOwnedRepresentationExtensions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS:
            return ((InternalEList<?>) getOwnedJavaExtensions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS:
            return ((InternalEList<?>) getOwnedMMExtensions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS:
            return ((InternalEList<?>) getOwnedFeatureExtensions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES:
            return ((InternalEList<?>) getOwnedTemplates()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION:
            return getEndUserDocumentation();
        case DescriptionPackage.VIEWPOINT__NAME:
            return getName();
        case DescriptionPackage.VIEWPOINT__LABEL:
            return getLabel();
        case DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION:
            return getModelFileExtension();
        case DescriptionPackage.VIEWPOINT__VALIDATION_SET:
            if (resolve) {
                return getValidationSet();
            }
            return basicGetValidationSet();
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS:
            return getOwnedRepresentations();
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS:
            return getOwnedRepresentationExtensions();
        case DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS:
            return getOwnedJavaExtensions();
        case DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS:
            return getOwnedMMExtensions();
        case DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS:
            return getOwnedFeatureExtensions();
        case DescriptionPackage.VIEWPOINT__ICON:
            return getIcon();
        case DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES:
            return getOwnedTemplates();
        case DescriptionPackage.VIEWPOINT__CONFLICTS:
            return getConflicts();
        case DescriptionPackage.VIEWPOINT__REUSES:
            return getReuses();
        case DescriptionPackage.VIEWPOINT__CUSTOMIZES:
            return getCustomizes();
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
        case DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION:
            setEndUserDocumentation((String) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION:
            setModelFileExtension((String) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__VALIDATION_SET:
            setValidationSet((ValidationSet) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            getOwnedRepresentations().addAll((Collection<? extends RepresentationDescription>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS:
            getOwnedRepresentationExtensions().clear();
            getOwnedRepresentationExtensions().addAll((Collection<? extends RepresentationExtensionDescription>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS:
            getOwnedJavaExtensions().clear();
            getOwnedJavaExtensions().addAll((Collection<? extends JavaExtension>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS:
            getOwnedMMExtensions().clear();
            getOwnedMMExtensions().addAll((Collection<? extends MetamodelExtensionSetting>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS:
            getOwnedFeatureExtensions().clear();
            getOwnedFeatureExtensions().addAll((Collection<? extends FeatureExtensionDescription>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__ICON:
            setIcon((String) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES:
            getOwnedTemplates().clear();
            getOwnedTemplates().addAll((Collection<? extends RepresentationTemplate>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__CONFLICTS:
            getConflicts().clear();
            getConflicts().addAll((Collection<? extends URI>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__REUSES:
            getReuses().clear();
            getReuses().addAll((Collection<? extends URI>) newValue);
            return;
        case DescriptionPackage.VIEWPOINT__CUSTOMIZES:
            getCustomizes().clear();
            getCustomizes().addAll((Collection<? extends URI>) newValue);
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
        case DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION:
            setEndUserDocumentation(ViewpointImpl.END_USER_DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.VIEWPOINT__NAME:
            setName(ViewpointImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.VIEWPOINT__LABEL:
            setLabel(ViewpointImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION:
            setModelFileExtension(ViewpointImpl.MODEL_FILE_EXTENSION_EDEFAULT);
            return;
        case DescriptionPackage.VIEWPOINT__VALIDATION_SET:
            setValidationSet((ValidationSet) null);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS:
            getOwnedRepresentations().clear();
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS:
            getOwnedRepresentationExtensions().clear();
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS:
            getOwnedJavaExtensions().clear();
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS:
            getOwnedMMExtensions().clear();
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS:
            getOwnedFeatureExtensions().clear();
            return;
        case DescriptionPackage.VIEWPOINT__ICON:
            setIcon(ViewpointImpl.ICON_EDEFAULT);
            return;
        case DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES:
            getOwnedTemplates().clear();
            return;
        case DescriptionPackage.VIEWPOINT__CONFLICTS:
            getConflicts().clear();
            return;
        case DescriptionPackage.VIEWPOINT__REUSES:
            getReuses().clear();
            return;
        case DescriptionPackage.VIEWPOINT__CUSTOMIZES:
            getCustomizes().clear();
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
        case DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION:
            return ViewpointImpl.END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null : !ViewpointImpl.END_USER_DOCUMENTATION_EDEFAULT.equals(endUserDocumentation);
        case DescriptionPackage.VIEWPOINT__NAME:
            return ViewpointImpl.NAME_EDEFAULT == null ? name != null : !ViewpointImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.VIEWPOINT__LABEL:
            return ViewpointImpl.LABEL_EDEFAULT == null ? label != null : !ViewpointImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.VIEWPOINT__MODEL_FILE_EXTENSION:
            return ViewpointImpl.MODEL_FILE_EXTENSION_EDEFAULT == null ? modelFileExtension != null : !ViewpointImpl.MODEL_FILE_EXTENSION_EDEFAULT.equals(modelFileExtension);
        case DescriptionPackage.VIEWPOINT__VALIDATION_SET:
            return validationSet != null;
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATIONS:
            return ownedRepresentations != null && !ownedRepresentations.isEmpty();
        case DescriptionPackage.VIEWPOINT__OWNED_REPRESENTATION_EXTENSIONS:
            return ownedRepresentationExtensions != null && !ownedRepresentationExtensions.isEmpty();
        case DescriptionPackage.VIEWPOINT__OWNED_JAVA_EXTENSIONS:
            return ownedJavaExtensions != null && !ownedJavaExtensions.isEmpty();
        case DescriptionPackage.VIEWPOINT__OWNED_MM_EXTENSIONS:
            return ownedMMExtensions != null && !ownedMMExtensions.isEmpty();
        case DescriptionPackage.VIEWPOINT__OWNED_FEATURE_EXTENSIONS:
            return ownedFeatureExtensions != null && !ownedFeatureExtensions.isEmpty();
        case DescriptionPackage.VIEWPOINT__ICON:
            return ViewpointImpl.ICON_EDEFAULT == null ? icon != null : !ViewpointImpl.ICON_EDEFAULT.equals(icon);
        case DescriptionPackage.VIEWPOINT__OWNED_TEMPLATES:
            return ownedTemplates != null && !ownedTemplates.isEmpty();
        case DescriptionPackage.VIEWPOINT__CONFLICTS:
            return conflicts != null && !conflicts.isEmpty();
        case DescriptionPackage.VIEWPOINT__REUSES:
            return reuses != null && !reuses.isEmpty();
        case DescriptionPackage.VIEWPOINT__CUSTOMIZES:
            return customizes != null && !customizes.isEmpty();
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
        if (baseClass == Component.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == EndUserDocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION:
                return DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.VIEWPOINT__NAME:
                return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.VIEWPOINT__LABEL:
                return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
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
        if (baseClass == Component.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        if (baseClass == EndUserDocumentedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
                return DescriptionPackage.VIEWPOINT__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.VIEWPOINT__NAME;
            case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.VIEWPOINT__LABEL;
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
        result.append(" (endUserDocumentation: "); //$NON-NLS-1$
        result.append(endUserDocumentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", modelFileExtension: "); //$NON-NLS-1$
        result.append(modelFileExtension);
        result.append(", icon: "); //$NON-NLS-1$
        result.append(icon);
        result.append(", conflicts: "); //$NON-NLS-1$
        result.append(conflicts);
        result.append(", reuses: "); //$NON-NLS-1$
        result.append(reuses);
        result.append(", customizes: "); //$NON-NLS-1$
        result.append(customizes);
        result.append(')');
        return result.toString();
    }

} // ViewpointImpl

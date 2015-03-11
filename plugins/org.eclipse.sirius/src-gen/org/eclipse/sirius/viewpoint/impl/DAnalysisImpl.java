/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DAnalysis</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getReferencedAnalysis
 * <em>Referenced Analysis</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getSemanticResources
 * <em>Semantic Resources</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getModels <em>
 * Models</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getEAnnotations
 * <em>EAnnotations</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getOwnedViews <em>
 * Owned Views</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getSelectedViews
 * <em>Selected Views</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getOwnedFeatureExtensions
 * <em>Owned Feature Extensions</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.DAnalysisImpl#getVersion <em>
 * Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DAnalysisImpl extends MinimalEObjectImpl.Container implements DAnalysis {
    /**
     * The cached value of the '{@link #getReferencedAnalysis()
     * <em>Referenced Analysis</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getReferencedAnalysis()
     * @generated
     * @ordered
     */
    protected EList<DAnalysis> referencedAnalysis;

    /**
     * The cached value of the '{@link #getSemanticResources()
     * <em>Semantic Resources</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSemanticResources()
     * @generated
     * @ordered
     */
    protected EList<ResourceDescriptor> semanticResources;

    /**
     * The cached value of the '{@link #getEAnnotations() <em>EAnnotations</em>}
     * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getEAnnotations()
     * @generated
     * @ordered
     */
    protected EList<DAnnotationEntry> eAnnotations;

    /**
     * The cached value of the '{@link #getOwnedViews() <em>Owned Views</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedViews()
     * @generated
     * @ordered
     */
    protected EList<DView> ownedViews;

    /**
     * The cached value of the '{@link #getSelectedViews()
     * <em>Selected Views</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSelectedViews()
     * @generated
     * @ordered
     */
    protected EList<DView> selectedViews;

    /**
     * The cached value of the '{@link #getOwnedFeatureExtensions()
     * <em>Owned Feature Extensions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedFeatureExtensions()
     * @generated
     * @ordered
     */
    protected EList<DFeatureExtension> ownedFeatureExtensions;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = DAnalysisImpl.VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DAnalysisImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.DANALYSIS;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DAnalysis> getReferencedAnalysis() {
        if (referencedAnalysis == null) {
            referencedAnalysis = new EObjectResolvingEList<DAnalysis>(DAnalysis.class, this, ViewpointPackage.DANALYSIS__REFERENCED_ANALYSIS);
        }
        return referencedAnalysis;
    }

    /**
     * <!-- begin-user-doc --> This method will load all resources which URI is
     * returned by <code>getSemanticResources</code>. <br/>
     * The returned list is unmodifiable. <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public EList<EObject> getModels() {
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.

        // user code
        Collection<EObject> models = new ArrayList<EObject>();
        for (ResourceDescriptor resourceDescriptor : getSemanticResources()) {
            Resource eResource = eResource();
            if (eResource != null) {
                try {
                    Resource resource = eResource.getResourceSet().getResource(resourceDescriptor.getResourceURI(), true);
                    if (resource != null) {
                        EObject eObject = findSemanticRoot(resource);
                        if (eObject != null) {
                            models.add(eObject);
                        }
                    }
                } catch (WrappedException e) {
                    // a problem occured during demand load.
                }
            }
        }

        return new EcoreEList.UnmodifiableEList<EObject>(this, ViewpointPackage.eINSTANCE.getDAnalysis_Models(), models.size(), models.toArray());
    }

    /**
     * Find the root EObject from the resource It is the first EObject of the
     * resource except for XSD like resource
     *
     * @param resource
     *            the resource from which to find the root
     * @return the root EObject
     *
     * @generated NOT
     */
    private EObject findSemanticRoot(final Resource res) {
        EObject root = null;
        EList<EObject> contents = res.getContents();
        if (contents != null && contents.size() > 0) {
            root = contents.get(0);

            /*
             * Attempt to determine if the Ecore model was generated from XSD by
             * inspecting the annotation on the root. XSD->ECore will use the
             * ExtendedMetaData as the annotation source, set the 'name' to an
             * empty string (since the generated DocumentRoot doesn't have an
             * underlying Element name), and have a 'kind' of 'mixed'.
             */
            EClass eClass = root.eClass();
            ExtendedMetaData extendedMetaData = ExtendedMetaData.INSTANCE;
            if (extendedMetaData.isDocumentRoot(eClass)) {

                /*
                 * Step over the "mixed", "xMLNSPrefixMap", and
                 * "xSISchemaLocation" features of the injected DocumentRoot
                 * found in an XSD generated Ecore model extracted from
                 * https://www
                 * .eclipse.org/modeling/emf/docs/overviews/XMLSchemaToEcoreMapping
                 * .pdf (section 1.5) ... The document root EClass looks like
                 * one corresponding to a mixed complex type (see section 3.4)
                 * including a "mixed" feature, and derived implementations for
                 * the other features in the class. This allows it to maintain
                 * comments and white space that appears in the document, before
                 * the root element. A document root class contains two more
                 * EMap features, both String to String, to record the namespace
                 * to prefix mappings (xMLNSPrefixMap) and xsi:schemaLocation
                 * mappings (xSISchemaLocation) of an XML instance document.
                 */
                EReference xmlnsPrefixMapFeature = extendedMetaData.getXMLNSPrefixMapFeature(eClass);
                EReference xsiSchemaLocationFeature = extendedMetaData.getXSISchemaLocationMapFeature(eClass);
                EAttribute mixedFeatureFeature = extendedMetaData.getMixedFeature(eClass);

                for (int featureID = 0; featureID < eClass.getFeatureCount(); featureID++) {
                    EStructuralFeature feature = eClass.getEStructuralFeature(featureID);
                    if (feature != mixedFeatureFeature && feature != xmlnsPrefixMapFeature && feature != xsiSchemaLocationFeature) {
                        Object obj = root.eGet(feature);
                        if (obj instanceof EObject) {
                            root = (EObject) obj;
                            break;
                        }
                    }
                } // for
            }
        }
        return root;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DAnnotationEntry> getEAnnotations() {
        if (eAnnotations == null) {
            eAnnotations = new EObjectContainmentEList<DAnnotationEntry>(DAnnotationEntry.class, this, ViewpointPackage.DANALYSIS__EANNOTATIONS);
        }
        return eAnnotations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DView> getOwnedViews() {
        if (ownedViews == null) {
            ownedViews = new EObjectContainmentEList.Resolving<DView>(DView.class, this, ViewpointPackage.DANALYSIS__OWNED_VIEWS);
        }
        return ownedViews;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DView> getSelectedViews() {
        if (selectedViews == null) {
            selectedViews = new EObjectResolvingEList<DView>(DView.class, this, ViewpointPackage.DANALYSIS__SELECTED_VIEWS);
        }
        return selectedViews;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DFeatureExtension> getOwnedFeatureExtensions() {
        if (ownedFeatureExtensions == null) {
            ownedFeatureExtensions = new EObjectContainmentEList.Resolving<DFeatureExtension>(DFeatureExtension.class, this, ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS);
        }
        return ownedFeatureExtensions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.DANALYSIS__VERSION, oldVersion, version));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ResourceDescriptor> getSemanticResources() {
        if (semanticResources == null) {
            semanticResources = new EDataTypeUniqueEList<ResourceDescriptor>(ResourceDescriptor.class, this, ViewpointPackage.DANALYSIS__SEMANTIC_RESOURCES);
        }
        return semanticResources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.DANALYSIS__EANNOTATIONS:
            return ((InternalEList<?>) getEAnnotations()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DANALYSIS__OWNED_VIEWS:
            return ((InternalEList<?>) getOwnedViews()).basicRemove(otherEnd, msgs);
        case ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS:
            return ((InternalEList<?>) getOwnedFeatureExtensions()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.DANALYSIS__REFERENCED_ANALYSIS:
            return getReferencedAnalysis();
        case ViewpointPackage.DANALYSIS__SEMANTIC_RESOURCES:
            return getSemanticResources();
        case ViewpointPackage.DANALYSIS__MODELS:
            return getModels();
        case ViewpointPackage.DANALYSIS__EANNOTATIONS:
            return getEAnnotations();
        case ViewpointPackage.DANALYSIS__OWNED_VIEWS:
            return getOwnedViews();
        case ViewpointPackage.DANALYSIS__SELECTED_VIEWS:
            return getSelectedViews();
        case ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS:
            return getOwnedFeatureExtensions();
        case ViewpointPackage.DANALYSIS__VERSION:
            return getVersion();
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
        case ViewpointPackage.DANALYSIS__REFERENCED_ANALYSIS:
            getReferencedAnalysis().clear();
            getReferencedAnalysis().addAll((Collection<? extends DAnalysis>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__SEMANTIC_RESOURCES:
            getSemanticResources().clear();
            getSemanticResources().addAll((Collection<? extends ResourceDescriptor>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__MODELS:
            getModels().clear();
            getModels().addAll((Collection<? extends EObject>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__EANNOTATIONS:
            getEAnnotations().clear();
            getEAnnotations().addAll((Collection<? extends DAnnotationEntry>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__OWNED_VIEWS:
            getOwnedViews().clear();
            getOwnedViews().addAll((Collection<? extends DView>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__SELECTED_VIEWS:
            getSelectedViews().clear();
            getSelectedViews().addAll((Collection<? extends DView>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS:
            getOwnedFeatureExtensions().clear();
            getOwnedFeatureExtensions().addAll((Collection<? extends DFeatureExtension>) newValue);
            return;
        case ViewpointPackage.DANALYSIS__VERSION:
            setVersion((String) newValue);
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
        case ViewpointPackage.DANALYSIS__REFERENCED_ANALYSIS:
            getReferencedAnalysis().clear();
            return;
        case ViewpointPackage.DANALYSIS__SEMANTIC_RESOURCES:
            getSemanticResources().clear();
            return;
        case ViewpointPackage.DANALYSIS__MODELS:
            getModels().clear();
            return;
        case ViewpointPackage.DANALYSIS__EANNOTATIONS:
            getEAnnotations().clear();
            return;
        case ViewpointPackage.DANALYSIS__OWNED_VIEWS:
            getOwnedViews().clear();
            return;
        case ViewpointPackage.DANALYSIS__SELECTED_VIEWS:
            getSelectedViews().clear();
            return;
        case ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS:
            getOwnedFeatureExtensions().clear();
            return;
        case ViewpointPackage.DANALYSIS__VERSION:
            setVersion(DAnalysisImpl.VERSION_EDEFAULT);
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
        case ViewpointPackage.DANALYSIS__REFERENCED_ANALYSIS:
            return referencedAnalysis != null && !referencedAnalysis.isEmpty();
        case ViewpointPackage.DANALYSIS__SEMANTIC_RESOURCES:
            return semanticResources != null && !semanticResources.isEmpty();
        case ViewpointPackage.DANALYSIS__MODELS:
            return !getModels().isEmpty();
        case ViewpointPackage.DANALYSIS__EANNOTATIONS:
            return eAnnotations != null && !eAnnotations.isEmpty();
        case ViewpointPackage.DANALYSIS__OWNED_VIEWS:
            return ownedViews != null && !ownedViews.isEmpty();
        case ViewpointPackage.DANALYSIS__SELECTED_VIEWS:
            return selectedViews != null && !selectedViews.isEmpty();
        case ViewpointPackage.DANALYSIS__OWNED_FEATURE_EXTENSIONS:
            return ownedFeatureExtensions != null && !ownedFeatureExtensions.isEmpty();
        case ViewpointPackage.DANALYSIS__VERSION:
            return DAnalysisImpl.VERSION_EDEFAULT == null ? version != null : !DAnalysisImpl.VERSION_EDEFAULT.equals(version);
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
        result.append(" (semanticResources: ");
        result.append(semanticResources);
        result.append(", version: ");
        result.append(version);
        result.append(')');
        return result.toString();
    }

} // DAnalysisImpl

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

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.metamodel.spec.DRepresentationContainerSpec;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.SyncStatus;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Splitter;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ViewpointFactoryImpl extends EFactoryImpl implements ViewpointFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static ViewpointFactory init() {
        try {
            ViewpointFactory theViewpointFactory = (ViewpointFactory) EPackage.Registry.INSTANCE.getEFactory(ViewpointPackage.eNS_URI);
            if (theViewpointFactory != null) {
                return theViewpointFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ViewpointFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ViewpointFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case ViewpointPackage.DANALYSIS:
            return createDAnalysis();
        case ViewpointPackage.DREPRESENTATION_CONTAINER:
            return createDRepresentationContainer();
        case ViewpointPackage.DVIEW:
            return createDView();
        case ViewpointPackage.META_MODEL_EXTENSION:
            return createMetaModelExtension();
        case ViewpointPackage.DECORATION:
            return createDecoration();
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA:
            return createDAnalysisCustomData();
        case ViewpointPackage.LABEL_STYLE:
            return createLabelStyle();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT:
            return createDAnalysisSessionEObject();
        case ViewpointPackage.SESSION_MANAGER_EOBJECT:
            return createSessionManagerEObject();
        case ViewpointPackage.DFILE:
            return createDFile();
        case ViewpointPackage.DRESOURCE_CONTAINER:
            return createDResourceContainer();
        case ViewpointPackage.DPROJECT:
            return createDProject();
        case ViewpointPackage.DFOLDER:
            return createDFolder();
        case ViewpointPackage.DMODEL:
            return createDModel();
        case ViewpointPackage.BASIC_LABEL_STYLE:
            return createBasicLabelStyle();
        case ViewpointPackage.UI_STATE:
            return createUIState();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case ViewpointPackage.FONT_FORMAT:
            return createFontFormatFromString(eDataType, initialValue);
        case ViewpointPackage.LABEL_ALIGNMENT:
            return createLabelAlignmentFromString(eDataType, initialValue);
        case ViewpointPackage.SYNC_STATUS:
            return createSyncStatusFromString(eDataType, initialValue);
        case ViewpointPackage.RGB_VALUES:
            return createRGBValuesFromString(eDataType, initialValue);
        case ViewpointPackage.RESOURCE_DESCRIPTOR:
            return createResourceDescriptorFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case ViewpointPackage.FONT_FORMAT:
            return convertFontFormatToString(eDataType, instanceValue);
        case ViewpointPackage.LABEL_ALIGNMENT:
            return convertLabelAlignmentToString(eDataType, instanceValue);
        case ViewpointPackage.SYNC_STATUS:
            return convertSyncStatusToString(eDataType, instanceValue);
        case ViewpointPackage.RGB_VALUES:
            return convertRGBValuesToString(eDataType, instanceValue);
        case ViewpointPackage.RESOURCE_DESCRIPTOR:
            return convertResourceDescriptorToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DAnalysis createDAnalysis() {
        DAnalysisImpl dAnalysis = new DAnalysisImpl();
        return dAnalysis;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DRepresentationContainer createDRepresentationContainer() {
        DRepresentationContainerImpl dRepresentationContainer = new DRepresentationContainerSpec();
        return dRepresentationContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DView createDView() {
        DViewImpl dView = new DViewImpl();
        return dView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MetaModelExtension createMetaModelExtension() {
        MetaModelExtensionImpl metaModelExtension = new MetaModelExtensionImpl();
        return metaModelExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Decoration createDecoration() {
        DecorationImpl decoration = new DecorationImpl();
        return decoration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DAnalysisCustomData createDAnalysisCustomData() {
        DAnalysisCustomDataImpl dAnalysisCustomData = new DAnalysisCustomDataImpl();
        return dAnalysisCustomData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LabelStyle createLabelStyle() {
        LabelStyleImpl labelStyle = new LabelStyleImpl();
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DAnalysisSessionEObject createDAnalysisSessionEObject() {
        DAnalysisSessionEObjectImpl dAnalysisSessionEObject = new DAnalysisSessionEObjectImpl();
        return dAnalysisSessionEObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SessionManagerEObject createSessionManagerEObject() {
        SessionManagerEObjectImpl sessionManagerEObject = new SessionManagerEObjectImpl();
        return sessionManagerEObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DFile createDFile() {
        DFileImpl dFile = new DFileImpl();
        return dFile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DResourceContainer createDResourceContainer() {
        DResourceContainerImpl dResourceContainer = new DResourceContainerImpl();
        return dResourceContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DProject createDProject() {
        DProjectImpl dProject = new DProjectImpl();
        return dProject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DFolder createDFolder() {
        DFolderImpl dFolder = new DFolderImpl();
        return dFolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DModel createDModel() {
        DModelImpl dModel = new DModelImpl();
        return dModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BasicLabelStyle createBasicLabelStyle() {
        BasicLabelStyleImpl basicLabelStyle = new BasicLabelStyleImpl();
        return basicLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public UIState createUIState() {
        UIStateImpl uiState = new UIStateImpl();
        return uiState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FontFormat createFontFormatFromString(EDataType eDataType, String initialValue) {
        FontFormat result = FontFormat.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertFontFormatToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     */
    public LabelAlignment createLabelAlignmentFromString(EDataType eDataType, String initialValue) {
        LabelAlignment result = LabelAlignment.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     */
    public String convertLabelAlignmentToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SyncStatus createSyncStatusFromString(EDataType eDataType, String initialValue) {
        SyncStatus result = SyncStatus.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertSyncStatusToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    public RGBValues createRGBValuesFromString(EDataType eDataType, String initialValue) {
        int r = 0;
        int g = 0;
        int b = 0;
        if (!StringUtil.isEmpty(initialValue)) {
            Iterator<String> it = Splitter.on(',').split(initialValue).iterator();
            if (it.hasNext()) {
                r = toInt(it.next());
            }
            if (it.hasNext()) {
                g = toInt(it.next());
            }
            if (it.hasNext()) {
                b = toInt(it.next());
            }
        }
        return RGBValues.create(r, g, b);
    }

    private int toInt(String next) {
        if (next.length() > 0) {
            try {
                return Integer.valueOf(next);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    public String convertRGBValuesToString(EDataType eDataType, Object instanceValue) {
        if (instanceValue instanceof RGBValues) {
            return instanceValue.toString();
        }
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ResourceDescriptor createResourceDescriptorFromString(EDataType eDataType, String initialValue) {
        return (ResourceDescriptor) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertResourceDescriptorToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ViewpointPackage getViewpointPackage() {
        return (ViewpointPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ViewpointPackage getPackage() {
        return ViewpointPackage.eINSTANCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public RGBValues createRGBValues() {
        return RGBValues.create(0, 0, 0);
    }
} // ViewpointFactoryImpl

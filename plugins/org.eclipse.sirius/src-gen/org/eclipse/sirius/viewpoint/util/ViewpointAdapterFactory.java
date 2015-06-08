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
package org.eclipse.sirius.viewpoint.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage
 * @generated
 */
public class ViewpointAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ViewpointPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ViewpointAdapterFactory() {
        if (ViewpointAdapterFactory.modelPackage == null) {
            ViewpointAdapterFactory.modelPackage = ViewpointPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == ViewpointAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == ViewpointAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ViewpointSwitch<Adapter> modelSwitch = new ViewpointSwitch<Adapter>() {
        @Override
        public Adapter caseDAnalysis(DAnalysis object) {
            return createDAnalysisAdapter();
        }

        @Override
        public Adapter caseDFeatureExtension(DFeatureExtension object) {
            return createDFeatureExtensionAdapter();
        }

        @Override
        public Adapter caseDStylizable(DStylizable object) {
            return createDStylizableAdapter();
        }

        @Override
        public Adapter caseDRefreshable(DRefreshable object) {
            return createDRefreshableAdapter();
        }

        @Override
        public Adapter caseDMappingBased(DMappingBased object) {
            return createDMappingBasedAdapter();
        }

        @Override
        public Adapter caseDRepresentationContainer(DRepresentationContainer object) {
            return createDRepresentationContainerAdapter();
        }

        @Override
        public Adapter caseDSemanticDecorator(DSemanticDecorator object) {
            return createDSemanticDecoratorAdapter();
        }

        @Override
        public Adapter caseDRepresentation(DRepresentation object) {
            return createDRepresentationAdapter();
        }

        @Override
        public Adapter caseDRepresentationElement(DRepresentationElement object) {
            return createDRepresentationElementAdapter();
        }

        @Override
        public Adapter caseDView(DView object) {
            return createDViewAdapter();
        }

        @Override
        public Adapter caseMetaModelExtension(MetaModelExtension object) {
            return createMetaModelExtensionAdapter();
        }

        @Override
        public Adapter caseDecoration(Decoration object) {
            return createDecorationAdapter();
        }

        @Override
        public Adapter caseDAnalysisCustomData(DAnalysisCustomData object) {
            return createDAnalysisCustomDataAdapter();
        }

        @Override
        public Adapter caseLabelStyle(LabelStyle object) {
            return createLabelStyleAdapter();
        }

        @Override
        public Adapter caseStyle(Style object) {
            return createStyleAdapter();
        }

        @Override
        public Adapter caseDAnalysisSessionEObject(DAnalysisSessionEObject object) {
            return createDAnalysisSessionEObjectAdapter();
        }

        @Override
        public Adapter caseSessionManagerEObject(SessionManagerEObject object) {
            return createSessionManagerEObjectAdapter();
        }

        @Override
        public Adapter caseDResource(DResource object) {
            return createDResourceAdapter();
        }

        @Override
        public Adapter caseDFile(DFile object) {
            return createDFileAdapter();
        }

        @Override
        public Adapter caseDResourceContainer(DResourceContainer object) {
            return createDResourceContainerAdapter();
        }

        @Override
        public Adapter caseDProject(DProject object) {
            return createDProjectAdapter();
        }

        @Override
        public Adapter caseDFolder(DFolder object) {
            return createDFolderAdapter();
        }

        @Override
        public Adapter caseDModel(DModel object) {
            return createDModelAdapter();
        }

        @Override
        public Adapter caseBasicLabelStyle(BasicLabelStyle object) {
            return createBasicLabelStyleAdapter();
        }

        @Override
        public Adapter caseCustomizable(Customizable object) {
            return createCustomizableAdapter();
        }

        @Override
        public Adapter caseUIState(UIState object) {
            return createUIStateAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseDModelElement(DModelElement object) {
            return createDModelElementAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis <em>DAnalysis</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DAnalysis
     * @generated
     */
    public Adapter createDAnalysisAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DFeatureExtension
     * <em>DFeature Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DFeatureExtension
     * @generated
     */
    public Adapter createDFeatureExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @generated
     */
    public Adapter createDStylizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @generated
     */
    public Adapter createDRefreshableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DMappingBased
     * <em>DMapping Based</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @generated
     */
    public Adapter createDMappingBasedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer
     * <em>DRepresentation Container</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentationContainer
     * @generated
     */
    public Adapter createDRepresentationContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DSemanticDecorator
     * <em>DSemantic Decorator</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator
     * @generated
     */
    public Adapter createDSemanticDecoratorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation
     * <em>DRepresentation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentation
     * @generated
     */
    public Adapter createDRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement
     * <em>DRepresentation Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement
     * @generated
     */
    public Adapter createDRepresentationElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DView <em>DView</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DView
     * @generated
     */
    public Adapter createDViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.MetaModelExtension
     * <em>Meta Model Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.MetaModelExtension
     * @generated
     */
    public Adapter createMetaModelExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Decoration <em>Decoration</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Decoration
     * @generated
     */
    public Adapter createDecorationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * <em>DAnalysis Custom Data</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * @generated
     */
    public Adapter createDAnalysisCustomDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.LabelStyle <em>Label Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.LabelStyle
     * @generated
     */
    public Adapter createLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Style <em>Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Style
     * @generated
     */
    public Adapter createStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * <em>DAnalysis Session EObject</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * @generated
     */
    public Adapter createDAnalysisSessionEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.SessionManagerEObject
     * <em>Session Manager EObject</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.SessionManagerEObject
     * @generated
     */
    public Adapter createSessionManagerEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DResource
     * @generated
     */
    public Adapter createDResourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DFile <em>DFile</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DFile
     * @generated
     */
    public Adapter createDFileAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DResourceContainer
     * <em>DResource Container</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DResourceContainer
     * @generated
     */
    public Adapter createDResourceContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DProject <em>DProject</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DProject
     * @generated
     */
    public Adapter createDProjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DFolder <em>DFolder</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DFolder
     * @generated
     */
    public Adapter createDFolderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DModel <em>DModel</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DModel
     * @generated
     */
    public Adapter createDModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle
     * <em>Basic Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle
     * @generated
     */
    public Adapter createBasicLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Customizable <em>Customizable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Customizable
     * @generated
     */
    public Adapter createCustomizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.UIState <em>UI State</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.UIState
     * @generated
     */
    public Adapter createUIStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DModelElement
     * @generated
     */
    public Adapter createDModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // ViewpointAdapterFactory

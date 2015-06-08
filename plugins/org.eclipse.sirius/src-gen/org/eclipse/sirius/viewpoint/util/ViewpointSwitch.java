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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage
 * @generated
 */
public class ViewpointSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ViewpointPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ViewpointSwitch() {
        if (ViewpointSwitch.modelPackage == null) {
            ViewpointSwitch.modelPackage = ViewpointPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == ViewpointSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case ViewpointPackage.DANALYSIS: {
            DAnalysis dAnalysis = (DAnalysis) theEObject;
            T result = caseDAnalysis(dAnalysis);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DFEATURE_EXTENSION: {
            DFeatureExtension dFeatureExtension = (DFeatureExtension) theEObject;
            T result = caseDFeatureExtension(dFeatureExtension);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DSTYLIZABLE: {
            DStylizable dStylizable = (DStylizable) theEObject;
            T result = caseDStylizable(dStylizable);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DREFRESHABLE: {
            DRefreshable dRefreshable = (DRefreshable) theEObject;
            T result = caseDRefreshable(dRefreshable);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DMAPPING_BASED: {
            DMappingBased dMappingBased = (DMappingBased) theEObject;
            T result = caseDMappingBased(dMappingBased);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DREPRESENTATION_CONTAINER: {
            DRepresentationContainer dRepresentationContainer = (DRepresentationContainer) theEObject;
            T result = caseDRepresentationContainer(dRepresentationContainer);
            if (result == null) {
                result = caseDView(dRepresentationContainer);
            }
            if (result == null) {
                result = caseDRefreshable(dRepresentationContainer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DSEMANTIC_DECORATOR: {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) theEObject;
            T result = caseDSemanticDecorator(dSemanticDecorator);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DREPRESENTATION: {
            DRepresentation dRepresentation = (DRepresentation) theEObject;
            T result = caseDRepresentation(dRepresentation);
            if (result == null) {
                result = caseDocumentedElement(dRepresentation);
            }
            if (result == null) {
                result = caseDRefreshable(dRepresentation);
            }
            if (result == null) {
                result = caseDModelElement(dRepresentation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DREPRESENTATION_ELEMENT: {
            DRepresentationElement dRepresentationElement = (DRepresentationElement) theEObject;
            T result = caseDRepresentationElement(dRepresentationElement);
            if (result == null) {
                result = caseDMappingBased(dRepresentationElement);
            }
            if (result == null) {
                result = caseDStylizable(dRepresentationElement);
            }
            if (result == null) {
                result = caseDRefreshable(dRepresentationElement);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dRepresentationElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DVIEW: {
            DView dView = (DView) theEObject;
            T result = caseDView(dView);
            if (result == null) {
                result = caseDRefreshable(dView);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.META_MODEL_EXTENSION: {
            MetaModelExtension metaModelExtension = (MetaModelExtension) theEObject;
            T result = caseMetaModelExtension(metaModelExtension);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DECORATION: {
            Decoration decoration = (Decoration) theEObject;
            T result = caseDecoration(decoration);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA: {
            DAnalysisCustomData dAnalysisCustomData = (DAnalysisCustomData) theEObject;
            T result = caseDAnalysisCustomData(dAnalysisCustomData);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.LABEL_STYLE: {
            LabelStyle labelStyle = (LabelStyle) theEObject;
            T result = caseLabelStyle(labelStyle);
            if (result == null) {
                result = caseBasicLabelStyle(labelStyle);
            }
            if (result == null) {
                result = caseCustomizable(labelStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.STYLE: {
            Style style = (Style) theEObject;
            T result = caseStyle(style);
            if (result == null) {
                result = caseDRefreshable(style);
            }
            if (result == null) {
                result = caseCustomizable(style);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT: {
            DAnalysisSessionEObject dAnalysisSessionEObject = (DAnalysisSessionEObject) theEObject;
            T result = caseDAnalysisSessionEObject(dAnalysisSessionEObject);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.SESSION_MANAGER_EOBJECT: {
            SessionManagerEObject sessionManagerEObject = (SessionManagerEObject) theEObject;
            T result = caseSessionManagerEObject(sessionManagerEObject);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DRESOURCE: {
            DResource dResource = (DResource) theEObject;
            T result = caseDResource(dResource);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DFILE: {
            DFile dFile = (DFile) theEObject;
            T result = caseDFile(dFile);
            if (result == null) {
                result = caseDResource(dFile);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DRESOURCE_CONTAINER: {
            DResourceContainer dResourceContainer = (DResourceContainer) theEObject;
            T result = caseDResourceContainer(dResourceContainer);
            if (result == null) {
                result = caseDResource(dResourceContainer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DPROJECT: {
            DProject dProject = (DProject) theEObject;
            T result = caseDProject(dProject);
            if (result == null) {
                result = caseDResourceContainer(dProject);
            }
            if (result == null) {
                result = caseDResource(dProject);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DFOLDER: {
            DFolder dFolder = (DFolder) theEObject;
            T result = caseDFolder(dFolder);
            if (result == null) {
                result = caseDResourceContainer(dFolder);
            }
            if (result == null) {
                result = caseDResource(dFolder);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.DMODEL: {
            DModel dModel = (DModel) theEObject;
            T result = caseDModel(dModel);
            if (result == null) {
                result = caseDFile(dModel);
            }
            if (result == null) {
                result = caseDResource(dModel);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.BASIC_LABEL_STYLE: {
            BasicLabelStyle basicLabelStyle = (BasicLabelStyle) theEObject;
            T result = caseBasicLabelStyle(basicLabelStyle);
            if (result == null) {
                result = caseCustomizable(basicLabelStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.CUSTOMIZABLE: {
            Customizable customizable = (Customizable) theEObject;
            T result = caseCustomizable(customizable);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ViewpointPackage.UI_STATE: {
            UIState uiState = (UIState) theEObject;
            T result = caseUIState(uiState);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnalysis</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnalysis</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnalysis(DAnalysis object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DFeature Extension</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DFeature Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFeatureExtension(DFeatureExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DStylizable</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DStylizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDStylizable(DStylizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRefreshable</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRefreshable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRefreshable(DRefreshable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DMapping Based</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DMapping Based</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDMappingBased(DMappingBased object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRepresentation Container</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRepresentation Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentationContainer(DRepresentationContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DSemantic Decorator</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DSemantic Decorator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSemanticDecorator(DSemanticDecorator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRepresentation</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRepresentation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentation(DRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRepresentation Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRepresentation Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentationElement(DRepresentationElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DView</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DView</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDView(DView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Meta Model Extension</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Meta Model Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetaModelExtension(MetaModelExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Decoration</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Decoration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDecoration(Decoration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnalysis Custom Data</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnalysis Custom Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnalysisCustomData(DAnalysisCustomData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyle(LabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Style</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyle(Style object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnalysis Session EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnalysis Session EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnalysisSessionEObject(DAnalysisSessionEObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Session Manager EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Session Manager EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSessionManagerEObject(SessionManagerEObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DResource</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DResource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDResource(DResource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DFile</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DFile</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFile(DFile object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DResource Container</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DResource Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDResourceContainer(DResourceContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DProject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DProject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDProject(DProject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DFolder</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DFolder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFolder(DFolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DModel</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DModel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModel(DModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Basic Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Basic Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyle(BasicLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Customizable</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Customizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomizable(Customizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>UI State</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>UI State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUIState(UIState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Documented Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DModel Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModelElement(DModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // ViewpointSwitch

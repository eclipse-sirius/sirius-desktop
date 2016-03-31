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
package org.eclipse.sirius.diagram.description.style.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.CustomStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage
 * @generated
 */
public class StyleSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static StylePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public StyleSwitch() {
        if (StyleSwitch.modelPackage == null) {
            StyleSwitch.modelPackage = StylePackage.eINSTANCE;
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
        if (theEClass.eContainer() == StyleSwitch.modelPackage) {
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
        case StylePackage.BORDERED_STYLE_DESCRIPTION: {
            BorderedStyleDescription borderedStyleDescription = (BorderedStyleDescription) theEObject;
            T result = caseBorderedStyleDescription(borderedStyleDescription);
            if (result == null) {
                result = caseStyleDescription(borderedStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.NODE_STYLE_DESCRIPTION: {
            NodeStyleDescription nodeStyleDescription = (NodeStyleDescription) theEObject;
            T result = caseNodeStyleDescription(nodeStyleDescription);
            if (result == null) {
                result = caseBorderedStyleDescription(nodeStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(nodeStyleDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(nodeStyleDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(nodeStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(nodeStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(nodeStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.CUSTOM_STYLE_DESCRIPTION: {
            CustomStyleDescription customStyleDescription = (CustomStyleDescription) theEObject;
            T result = caseCustomStyleDescription(customStyleDescription);
            if (result == null) {
                result = caseNodeStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(customStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.SQUARE_DESCRIPTION: {
            SquareDescription squareDescription = (SquareDescription) theEObject;
            T result = caseSquareDescription(squareDescription);
            if (result == null) {
                result = caseNodeStyleDescription(squareDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(squareDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(squareDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(squareDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(squareDescription);
            }
            if (result == null) {
                result = caseStyleDescription(squareDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(squareDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.LOZENGE_NODE_DESCRIPTION: {
            LozengeNodeDescription lozengeNodeDescription = (LozengeNodeDescription) theEObject;
            T result = caseLozengeNodeDescription(lozengeNodeDescription);
            if (result == null) {
                result = caseNodeStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = caseStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(lozengeNodeDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.ELLIPSE_NODE_DESCRIPTION: {
            EllipseNodeDescription ellipseNodeDescription = (EllipseNodeDescription) theEObject;
            T result = caseEllipseNodeDescription(ellipseNodeDescription);
            if (result == null) {
                result = caseNodeStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = caseStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(ellipseNodeDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION: {
            BundledImageDescription bundledImageDescription = (BundledImageDescription) theEObject;
            T result = caseBundledImageDescription(bundledImageDescription);
            if (result == null) {
                result = caseNodeStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = caseStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(bundledImageDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.NOTE_DESCRIPTION: {
            NoteDescription noteDescription = (NoteDescription) theEObject;
            T result = caseNoteDescription(noteDescription);
            if (result == null) {
                result = caseNodeStyleDescription(noteDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(noteDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(noteDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(noteDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(noteDescription);
            }
            if (result == null) {
                result = caseStyleDescription(noteDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(noteDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.DOT_DESCRIPTION: {
            DotDescription dotDescription = (DotDescription) theEObject;
            T result = caseDotDescription(dotDescription);
            if (result == null) {
                result = caseNodeStyleDescription(dotDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(dotDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(dotDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(dotDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(dotDescription);
            }
            if (result == null) {
                result = caseStyleDescription(dotDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(dotDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION: {
            GaugeCompositeStyleDescription gaugeCompositeStyleDescription = (GaugeCompositeStyleDescription) theEObject;
            T result = caseGaugeCompositeStyleDescription(gaugeCompositeStyleDescription);
            if (result == null) {
                result = caseNodeStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(gaugeCompositeStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.GAUGE_SECTION_DESCRIPTION: {
            GaugeSectionDescription gaugeSectionDescription = (GaugeSectionDescription) theEObject;
            T result = caseGaugeSectionDescription(gaugeSectionDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION: {
            SizeComputationContainerStyleDescription sizeComputationContainerStyleDescription = (SizeComputationContainerStyleDescription) theEObject;
            T result = caseSizeComputationContainerStyleDescription(sizeComputationContainerStyleDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION: {
            RoundedCornerStyleDescription roundedCornerStyleDescription = (RoundedCornerStyleDescription) theEObject;
            T result = caseRoundedCornerStyleDescription(roundedCornerStyleDescription);
            if (result == null) {
                result = caseStyleDescription(roundedCornerStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.CONTAINER_STYLE_DESCRIPTION: {
            ContainerStyleDescription containerStyleDescription = (ContainerStyleDescription) theEObject;
            T result = caseContainerStyleDescription(containerStyleDescription);
            if (result == null) {
                result = caseRoundedCornerStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(containerStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION: {
            FlatContainerStyleDescription flatContainerStyleDescription = (FlatContainerStyleDescription) theEObject;
            T result = caseFlatContainerStyleDescription(flatContainerStyleDescription);
            if (result == null) {
                result = caseContainerStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseSizeComputationContainerStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseRoundedCornerStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(flatContainerStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION: {
            ShapeContainerStyleDescription shapeContainerStyleDescription = (ShapeContainerStyleDescription) theEObject;
            T result = caseShapeContainerStyleDescription(shapeContainerStyleDescription);
            if (result == null) {
                result = caseContainerStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseSizeComputationContainerStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseRoundedCornerStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(shapeContainerStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.WORKSPACE_IMAGE_DESCRIPTION: {
            WorkspaceImageDescription workspaceImageDescription = (WorkspaceImageDescription) theEObject;
            T result = caseWorkspaceImageDescription(workspaceImageDescription);
            if (result == null) {
                result = caseNodeStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseContainerStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseBorderedStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseLabelStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseTooltipStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseRoundedCornerStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = caseBasicLabelStyleDescription(workspaceImageDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.EDGE_STYLE_DESCRIPTION: {
            EdgeStyleDescription edgeStyleDescription = (EdgeStyleDescription) theEObject;
            T result = caseEdgeStyleDescription(edgeStyleDescription);
            if (result == null) {
                result = caseStyleDescription(edgeStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.BEGIN_LABEL_STYLE_DESCRIPTION: {
            BeginLabelStyleDescription beginLabelStyleDescription = (BeginLabelStyleDescription) theEObject;
            T result = caseBeginLabelStyleDescription(beginLabelStyleDescription);
            if (result == null) {
                result = caseBasicLabelStyleDescription(beginLabelStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.CENTER_LABEL_STYLE_DESCRIPTION: {
            CenterLabelStyleDescription centerLabelStyleDescription = (CenterLabelStyleDescription) theEObject;
            T result = caseCenterLabelStyleDescription(centerLabelStyleDescription);
            if (result == null) {
                result = caseBasicLabelStyleDescription(centerLabelStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.END_LABEL_STYLE_DESCRIPTION: {
            EndLabelStyleDescription endLabelStyleDescription = (EndLabelStyleDescription) theEObject;
            T result = caseEndLabelStyleDescription(endLabelStyleDescription);
            if (result == null) {
                result = caseBasicLabelStyleDescription(endLabelStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.BRACKET_EDGE_STYLE_DESCRIPTION: {
            BracketEdgeStyleDescription bracketEdgeStyleDescription = (BracketEdgeStyleDescription) theEObject;
            T result = caseBracketEdgeStyleDescription(bracketEdgeStyleDescription);
            if (result == null) {
                result = caseEdgeStyleDescription(bracketEdgeStyleDescription);
            }
            if (result == null) {
                result = caseStyleDescription(bracketEdgeStyleDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case StylePackage.HIDE_LABEL_CAPABILITY_STYLE_DESCRIPTION: {
            HideLabelCapabilityStyleDescription hideLabelCapabilityStyleDescription = (HideLabelCapabilityStyleDescription) theEObject;
            T result = caseHideLabelCapabilityStyleDescription(hideLabelCapabilityStyleDescription);
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
     * <em>Bordered Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bordered Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBorderedStyleDescription(BorderedStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Node Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeStyleDescription(NodeStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomStyleDescription(CustomStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Square Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Square Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSquareDescription(SquareDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Lozenge Node Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Lozenge Node Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLozengeNodeDescription(LozengeNodeDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Ellipse Node Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Ellipse Node Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEllipseNodeDescription(EllipseNodeDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bundled Image Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bundled Image Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBundledImageDescription(BundledImageDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Note Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Note Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNoteDescription(NoteDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Dot Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Dot Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDotDescription(DotDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gauge Composite Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gauge Composite Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeCompositeStyleDescription(GaugeCompositeStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gauge Section Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gauge Section Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeSectionDescription(GaugeSectionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Size Computation Container Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null
     * result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Size Computation Container Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSizeComputationContainerStyleDescription(SizeComputationContainerStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Rounded Corner Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Rounded Corner Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRoundedCornerStyleDescription(RoundedCornerStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerStyleDescription(ContainerStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Flat Container Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Flat Container Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlatContainerStyleDescription(FlatContainerStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Shape Container Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Shape Container Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeContainerStyleDescription(ShapeContainerStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Workspace Image Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Workspace Image Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWorkspaceImageDescription(WorkspaceImageDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeStyleDescription(EdgeStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Begin Label Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Begin Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBeginLabelStyleDescription(BeginLabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Center Label Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Center Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCenterLabelStyleDescription(CenterLabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>End Label Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>End Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndLabelStyleDescription(EndLabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bracket Edge Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bracket Edge Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBracketEdgeStyleDescription(BracketEdgeStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hide Label Capability Style Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hide Label Capability Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHideLabelCapabilityStyleDescription(HideLabelCapabilityStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyleDescription(StyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Basic Label Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Basic Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyleDescription(LabelStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Tooltip Style Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch.
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Tooltip Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTooltipStyleDescription(TooltipStyleDescription object) {
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

} // StyleSwitch

/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.style.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.CustomStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.model.business.internal.color.DiagramDefaultColorStyleDescription;
import org.eclipse.sirius.diagram.model.business.internal.description.style.spec.BeginLabelStyleDescriptionSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.style.spec.CenterLabelStyleDescriptionSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.style.spec.EndLabelStyleDescriptionSpec;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class StyleFactoryImpl extends EFactoryImpl implements StyleFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static StyleFactory init() {
        try {
            StyleFactory theStyleFactory = (StyleFactory) EPackage.Registry.INSTANCE.getEFactory(StylePackage.eNS_URI);
            if (theStyleFactory != null) {
                return theStyleFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new StyleFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public StyleFactoryImpl() {
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
        case StylePackage.BORDERED_STYLE_DESCRIPTION:
            return createBorderedStyleDescription();
        case StylePackage.CUSTOM_STYLE_DESCRIPTION:
            return createCustomStyleDescription();
        case StylePackage.SQUARE_DESCRIPTION:
            return createSquareDescription();
        case StylePackage.LOZENGE_NODE_DESCRIPTION:
            return createLozengeNodeDescription();
        case StylePackage.ELLIPSE_NODE_DESCRIPTION:
            return createEllipseNodeDescription();
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION:
            return createBundledImageDescription();
        case StylePackage.NOTE_DESCRIPTION:
            return createNoteDescription();
        case StylePackage.DOT_DESCRIPTION:
            return createDotDescription();
        case StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION:
            return createGaugeCompositeStyleDescription();
        case StylePackage.GAUGE_SECTION_DESCRIPTION:
            return createGaugeSectionDescription();
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION:
            return createFlatContainerStyleDescription();
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION:
            return createShapeContainerStyleDescription();
        case StylePackage.WORKSPACE_IMAGE_DESCRIPTION:
            return createWorkspaceImageDescription();
        case StylePackage.EDGE_STYLE_DESCRIPTION:
            return createEdgeStyleDescription();
        case StylePackage.BEGIN_LABEL_STYLE_DESCRIPTION:
            return createBeginLabelStyleDescription();
        case StylePackage.CENTER_LABEL_STYLE_DESCRIPTION:
            return createCenterLabelStyleDescription();
        case StylePackage.END_LABEL_STYLE_DESCRIPTION:
            return createEndLabelStyleDescription();
        case StylePackage.BRACKET_EDGE_STYLE_DESCRIPTION:
            return createBracketEdgeStyleDescription();
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
        case StylePackage.SIDE:
            return createSideFromString(eDataType, initialValue);
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
        case StylePackage.SIDE:
            return convertSideToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BorderedStyleDescription createBorderedStyleDescription() {
        BorderedStyleDescriptionImpl borderedStyleDescription = new BorderedStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(borderedStyleDescription);
        return borderedStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CustomStyleDescription createCustomStyleDescription() {
        CustomStyleDescriptionImpl customStyleDescription = new CustomStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(customStyleDescription);
        return customStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public SquareDescription createSquareDescription() {
        SquareDescriptionImpl squareDescription = new SquareDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(squareDescription);
        return squareDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public LozengeNodeDescription createLozengeNodeDescription() {
        LozengeNodeDescriptionImpl lozengeNodeDescription = new LozengeNodeDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(lozengeNodeDescription);
        return lozengeNodeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EllipseNodeDescription createEllipseNodeDescription() {
        EllipseNodeDescriptionImpl ellipseNodeDescription = new EllipseNodeDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(ellipseNodeDescription);
        return ellipseNodeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BundledImageDescription createBundledImageDescription() {
        BundledImageDescriptionImpl bundledImageDescription = new BundledImageDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(bundledImageDescription);
        return bundledImageDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public NoteDescription createNoteDescription() {
        NoteDescriptionImpl noteDescription = new NoteDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(noteDescription);
        return noteDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DotDescription createDotDescription() {
        DotDescriptionImpl dotDescription = new DotDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(dotDescription);
        return dotDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public GaugeCompositeStyleDescription createGaugeCompositeStyleDescription() {
        GaugeCompositeStyleDescriptionImpl gaugeCompositeStyleDescription = new GaugeCompositeStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(gaugeCompositeStyleDescription);
        return gaugeCompositeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public GaugeSectionDescription createGaugeSectionDescription() {
        GaugeSectionDescriptionImpl gaugeSectionDescription = new GaugeSectionDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(gaugeSectionDescription);
        return gaugeSectionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public FlatContainerStyleDescription createFlatContainerStyleDescription() {
        FlatContainerStyleDescriptionImpl flatContainerStyleDescription = new FlatContainerStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(flatContainerStyleDescription);
        return flatContainerStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ShapeContainerStyleDescription createShapeContainerStyleDescription() {
        ShapeContainerStyleDescriptionImpl shapeContainerStyleDescription = new ShapeContainerStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(shapeContainerStyleDescription);
        return shapeContainerStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public WorkspaceImageDescription createWorkspaceImageDescription() {
        WorkspaceImageDescriptionImpl workspaceImageDescription = new WorkspaceImageDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(workspaceImageDescription);
        return workspaceImageDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EdgeStyleDescription createEdgeStyleDescription() {
        EdgeStyleDescriptionImpl edgeStyleDescription = new EdgeStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(edgeStyleDescription);
        return edgeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BeginLabelStyleDescription createBeginLabelStyleDescription() {
        BeginLabelStyleDescriptionImpl beginLabelStyleDescription = new BeginLabelStyleDescriptionSpec();
        new DiagramDefaultColorStyleDescription().setDefaultColors(beginLabelStyleDescription);
        return beginLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CenterLabelStyleDescription createCenterLabelStyleDescription() {
        CenterLabelStyleDescriptionImpl centerLabelStyleDescription = new CenterLabelStyleDescriptionSpec();
        new DiagramDefaultColorStyleDescription().setDefaultColors(centerLabelStyleDescription);
        return centerLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EndLabelStyleDescription createEndLabelStyleDescription() {
        EndLabelStyleDescriptionImpl endLabelStyleDescription = new EndLabelStyleDescriptionSpec();
        new DiagramDefaultColorStyleDescription().setDefaultColors(endLabelStyleDescription);
        return endLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BracketEdgeStyleDescription createBracketEdgeStyleDescription() {
        BracketEdgeStyleDescriptionImpl bracketEdgeStyleDescription = new BracketEdgeStyleDescriptionImpl();
        new DiagramDefaultColorStyleDescription().setDefaultColors(bracketEdgeStyleDescription);
        return bracketEdgeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Side createSideFromString(EDataType eDataType, String initialValue) {
        Side result = Side.get(initialValue);
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
    public String convertSideToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StylePackage getStylePackage() {
        return (StylePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static StylePackage getPackage() {
        return StylePackage.eINSTANCE;
    }

} // StyleFactoryImpl

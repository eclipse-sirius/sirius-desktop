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
package org.eclipse.sirius.viewpoint.description.style.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.internal.color.DefaultColorStyleDescription;
import org.eclipse.sirius.business.internal.metamodel.description.style.spec.BeginLabelStyleDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.style.spec.CenterLabelStyleDescriptionSpec;
import org.eclipse.sirius.business.internal.metamodel.description.style.spec.EndLabelStyleDescriptionSpec;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BundledImageDescription;
import org.eclipse.sirius.viewpoint.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.CustomStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.DotDescription;
import org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription;
import org.eclipse.sirius.viewpoint.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription;
import org.eclipse.sirius.viewpoint.description.style.NoteDescription;
import org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.SquareDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleFactory;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.WorkspaceImageDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class StyleFactoryImpl extends EFactoryImpl implements StyleFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
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
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
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
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION:
            return createBasicLabelStyleDescription();
        case StylePackage.LABEL_STYLE_DESCRIPTION:
            return createLabelStyleDescription();
        case StylePackage.LABEL_BORDER_STYLES:
            return createLabelBorderStyles();
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION:
            return createLabelBorderStyleDescription();
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
        case StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION:
            return createFlatContainerStyleDescription();
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION:
            return createShapeContainerStyleDescription();
        case StylePackage.WORKSPACE_IMAGE_DESCRIPTION:
            return createWorkspaceImageDescription();
        case StylePackage.EDGE_STYLE_DESCRIPTION:
            return createEdgeStyleDescription();
        case StylePackage.TOOLTIP_STYLE_DESCRIPTION:
            return createTooltipStyleDescription();
        case StylePackage.GAUGE_SECTION_DESCRIPTION:
            return createGaugeSectionDescription();
        case StylePackage.BEGIN_LABEL_STYLE_DESCRIPTION:
            return createBeginLabelStyleDescription();
        case StylePackage.CENTER_LABEL_STYLE_DESCRIPTION:
            return createCenterLabelStyleDescription();
        case StylePackage.END_LABEL_STYLE_DESCRIPTION:
            return createEndLabelStyleDescription();
        case StylePackage.BRACKET_EDGE_STYLE_DESCRIPTION:
            return createBracketEdgeStyleDescription();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BorderedStyleDescription createBorderedStyleDescription() {
        BorderedStyleDescriptionImpl borderedStyleDescription = new BorderedStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(borderedStyleDescription);
        return borderedStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BasicLabelStyleDescription createBasicLabelStyleDescription() {
        BasicLabelStyleDescriptionImpl basicLabelStyleDescription = new BasicLabelStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(basicLabelStyleDescription);
        return basicLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public LabelStyleDescription createLabelStyleDescription() {
        LabelStyleDescriptionImpl labelStyleDescription = new LabelStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(labelStyleDescription);
        return labelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LabelBorderStyles createLabelBorderStyles() {
        LabelBorderStylesImpl labelBorderStyles = new LabelBorderStylesImpl();
        return labelBorderStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LabelBorderStyleDescription createLabelBorderStyleDescription() {
        LabelBorderStyleDescriptionImpl labelBorderStyleDescription = new LabelBorderStyleDescriptionImpl();
        return labelBorderStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public CustomStyleDescription createCustomStyleDescription() {
        CustomStyleDescriptionImpl customStyleDescription = new CustomStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(customStyleDescription);
        return customStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public SquareDescription createSquareDescription() {
        SquareDescriptionImpl squareDescription = new SquareDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(squareDescription);
        return squareDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public LozengeNodeDescription createLozengeNodeDescription() {
        LozengeNodeDescriptionImpl lozengeNodeDescription = new LozengeNodeDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(lozengeNodeDescription);
        return lozengeNodeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public EllipseNodeDescription createEllipseNodeDescription() {
        EllipseNodeDescriptionImpl ellipseNodeDescription = new EllipseNodeDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(ellipseNodeDescription);
        return ellipseNodeDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BundledImageDescription createBundledImageDescription() {
        BundledImageDescriptionImpl bundledImageDescription = new BundledImageDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(bundledImageDescription);
        return bundledImageDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public NoteDescription createNoteDescription() {
        NoteDescriptionImpl noteDescription = new NoteDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(noteDescription);
        return noteDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DotDescription createDotDescription() {
        DotDescriptionImpl dotDescription = new DotDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(dotDescription);
        return dotDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public GaugeCompositeStyleDescription createGaugeCompositeStyleDescription() {
        GaugeCompositeStyleDescriptionImpl gaugeCompositeStyleDescription = new GaugeCompositeStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(gaugeCompositeStyleDescription);
        return gaugeCompositeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public FlatContainerStyleDescription createFlatContainerStyleDescription() {
        FlatContainerStyleDescriptionImpl flatContainerStyleDescription = new FlatContainerStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(flatContainerStyleDescription);
        return flatContainerStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ShapeContainerStyleDescription createShapeContainerStyleDescription() {
        ShapeContainerStyleDescriptionImpl shapeContainerStyleDescription = new ShapeContainerStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(shapeContainerStyleDescription);
        return shapeContainerStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public WorkspaceImageDescription createWorkspaceImageDescription() {
        WorkspaceImageDescriptionImpl workspaceImageDescription = new WorkspaceImageDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(workspaceImageDescription);
        return workspaceImageDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public EdgeStyleDescription createEdgeStyleDescription() {
        EdgeStyleDescriptionImpl edgeStyleDescription = new EdgeStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(edgeStyleDescription);
        return edgeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TooltipStyleDescription createTooltipStyleDescription() {
        TooltipStyleDescriptionImpl tooltipStyleDescription = new TooltipStyleDescriptionImpl();
        return tooltipStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public GaugeSectionDescription createGaugeSectionDescription() {
        GaugeSectionDescriptionImpl gaugeSectionDescription = new GaugeSectionDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(gaugeSectionDescription);
        return gaugeSectionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BeginLabelStyleDescription createBeginLabelStyleDescription() {
        BeginLabelStyleDescriptionImpl beginLabelStyleDescription = new BeginLabelStyleDescriptionSpec();
        new DefaultColorStyleDescription().setDefaultColors(beginLabelStyleDescription);
        return beginLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public CenterLabelStyleDescription createCenterLabelStyleDescription() {
        CenterLabelStyleDescriptionImpl centerLabelStyleDescription = new CenterLabelStyleDescriptionSpec();
        new DefaultColorStyleDescription().setDefaultColors(centerLabelStyleDescription);
        return centerLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public EndLabelStyleDescription createEndLabelStyleDescription() {
        EndLabelStyleDescriptionImpl endLabelStyleDescription = new EndLabelStyleDescriptionSpec();
        new DefaultColorStyleDescription().setDefaultColors(endLabelStyleDescription);
        return endLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BracketEdgeStyleDescription createBracketEdgeStyleDescription() {
        BracketEdgeStyleDescriptionImpl bracketEdgeStyleDescription = new BracketEdgeStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(bracketEdgeStyleDescription);
        return bracketEdgeStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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

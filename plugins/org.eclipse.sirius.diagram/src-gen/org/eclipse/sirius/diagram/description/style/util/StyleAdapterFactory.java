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
package org.eclipse.sirius.diagram.description.style.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage
 * @generated
 */
public class StyleAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static StylePackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public StyleAdapterFactory() {
        if (StyleAdapterFactory.modelPackage == null) {
            StyleAdapterFactory.modelPackage = StylePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == StyleAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == StyleAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected StyleSwitch<Adapter> modelSwitch = new StyleSwitch<Adapter>() {
        @Override
        public Adapter caseBorderedStyleDescription(BorderedStyleDescription object) {
            return createBorderedStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseNodeStyleDescription(NodeStyleDescription object) {
            return createNodeStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseCustomStyleDescription(CustomStyleDescription object) {
            return createCustomStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseSquareDescription(SquareDescription object) {
            return createSquareDescriptionAdapter();
        }

        @Override
        public Adapter caseLozengeNodeDescription(LozengeNodeDescription object) {
            return createLozengeNodeDescriptionAdapter();
        }

        @Override
        public Adapter caseEllipseNodeDescription(EllipseNodeDescription object) {
            return createEllipseNodeDescriptionAdapter();
        }

        @Override
        public Adapter caseBundledImageDescription(BundledImageDescription object) {
            return createBundledImageDescriptionAdapter();
        }

        @Override
        public Adapter caseNoteDescription(NoteDescription object) {
            return createNoteDescriptionAdapter();
        }

        @Override
        public Adapter caseDotDescription(DotDescription object) {
            return createDotDescriptionAdapter();
        }

        @Override
        public Adapter caseGaugeCompositeStyleDescription(GaugeCompositeStyleDescription object) {
            return createGaugeCompositeStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseGaugeSectionDescription(GaugeSectionDescription object) {
            return createGaugeSectionDescriptionAdapter();
        }

        @Override
        public Adapter caseSizeComputationContainerStyleDescription(SizeComputationContainerStyleDescription object) {
            return createSizeComputationContainerStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseRoundedCornerStyleDescription(RoundedCornerStyleDescription object) {
            return createRoundedCornerStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseContainerStyleDescription(ContainerStyleDescription object) {
            return createContainerStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseFlatContainerStyleDescription(FlatContainerStyleDescription object) {
            return createFlatContainerStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseShapeContainerStyleDescription(ShapeContainerStyleDescription object) {
            return createShapeContainerStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseWorkspaceImageDescription(WorkspaceImageDescription object) {
            return createWorkspaceImageDescriptionAdapter();
        }

        @Override
        public Adapter caseEdgeStyleDescription(EdgeStyleDescription object) {
            return createEdgeStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseBeginLabelStyleDescription(BeginLabelStyleDescription object) {
            return createBeginLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseCenterLabelStyleDescription(CenterLabelStyleDescription object) {
            return createCenterLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseEndLabelStyleDescription(EndLabelStyleDescription object) {
            return createEndLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseBracketEdgeStyleDescription(BracketEdgeStyleDescription object) {
            return createBracketEdgeStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseHideLabelCapabilityStyleDescription(HideLabelCapabilityStyleDescription object) {
            return createHideLabelCapabilityStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseStyleDescription(StyleDescription object) {
            return createStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
            return createBasicLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseLabelStyleDescription(LabelStyleDescription object) {
            return createLabelStyleDescriptionAdapter();
        }

        @Override
        public Adapter caseTooltipStyleDescription(TooltipStyleDescription object) {
            return createTooltipStyleDescriptionAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription <em>Bordered Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.BorderedStyleDescription
     * @generated
     */
    public Adapter createBorderedStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.style.NodeStyleDescription <em>Node Style Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.NodeStyleDescription
     * @generated
     */
    public Adapter createNodeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.CustomStyleDescription <em>Custom Style Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.CustomStyleDescription
     * @generated
     */
    public Adapter createCustomStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.style.SquareDescription <em>Square Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.SquareDescription
     * @generated
     */
    public Adapter createSquareDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription <em>Lozenge Node Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.LozengeNodeDescription
     * @generated
     */
    public Adapter createLozengeNodeDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.EllipseNodeDescription <em>Ellipse Node Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.EllipseNodeDescription
     * @generated
     */
    public Adapter createEllipseNodeDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription <em>Bundled Image
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.BundledImageDescription
     * @generated
     */
    public Adapter createBundledImageDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.style.NoteDescription
     * <em>Note Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.NoteDescription
     * @generated
     */
    public Adapter createNoteDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.style.DotDescription
     * <em>Dot Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.DotDescription
     * @generated
     */
    public Adapter createDotDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription <em>Gauge Composite Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription
     * @generated
     */
    public Adapter createGaugeCompositeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription <em>Gauge Section
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.GaugeSectionDescription
     * @generated
     */
    public Adapter createGaugeSectionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription <em>Size
     * Computation Container Style Description</em>}'. <!-- begin-user-doc --> This default implementation returns null
     * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription
     * @generated
     */
    public Adapter createSizeComputationContainerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription <em>Rounded Corner Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription
     * @generated
     */
    public Adapter createRoundedCornerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.ContainerStyleDescription <em>Container Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.ContainerStyleDescription
     * @generated
     */
    public Adapter createContainerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription <em>Flat Container Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription
     * @generated
     */
    public Adapter createFlatContainerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription <em>Shape Container Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription
     * @generated
     */
    public Adapter createShapeContainerStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription <em>Workspace Image
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription
     * @generated
     */
    public Adapter createWorkspaceImageDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.description.style.EdgeStyleDescription <em>Edge Style Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.EdgeStyleDescription
     * @generated
     */
    public Adapter createEdgeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription <em>Begin Label Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription
     * @generated
     */
    public Adapter createBeginLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription <em>Center Label Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription
     * @generated
     */
    public Adapter createCenterLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription <em>End Label Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription
     * @generated
     */
    public Adapter createEndLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription <em>Bracket Edge Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription
     * @generated
     */
    public Adapter createBracketEdgeStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription <em>Hide Label
     * Capability Style Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription
     * @generated
     */
    public Adapter createHideLabelCapabilityStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.style.StyleDescription <em>Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.StyleDescription
     * @generated
     */
    public Adapter createStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription <em>Basic Label Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription
     * @generated
     */
    public Adapter createBasicLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription <em>Label Style Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription
     * @generated
     */
    public Adapter createLabelStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription <em>Tooltip Style
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription
     * @generated
     */
    public Adapter createTooltipStyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // StyleAdapterFactory

/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.FixedLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AirStyleDefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GaugeCompositeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GaugeSectionFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.LabelStyle;

/**
 * @was-generated
 */
public class GaugeCompositeEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3006;

    /**
     * @was-generated
     */
    protected IFigure contentPane;

    /**
     * @was-generated
     */
    protected IFigure primaryShape;

    /**
     * @was-generated
     */
    public GaugeCompositeEditPart(final View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        // No default policies.
    }

    /**
     * @not-generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new FixedLayoutEditPolicy();
    }

    /**
     * @was-generated
     */
    protected IFigure createNodeShape() {
        GaugeCompositeFigure gauge = new GaugeCompositeFigure();
        EditPart parent = this.getParent();
        if (parent instanceof IDiagramBorderNodeEditPart) {
            DiagramBorderNodeEditPartOperation.updateTransparencyMode((IDiagramBorderNodeEditPart) parent, gauge);
        }
        return primaryShape = gauge;
    }

    /**
     * @was-generated
     */
    public GaugeCompositeFigure getPrimaryShape() {
        return (GaugeCompositeFigure) primaryShape;
    }

    /**
     * @not-generated
     */
    protected NodeFigure createNodePlate() {
        return new AirStyleDefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model so
     * you may safely remove <i>generated</i> tag and modify it.
     * 
     * @not-generated XY Layout.
     */
    @Override
    protected NodeFigure createNodeFigure() {
        final NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new XYLayout());
        final IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane. Respects
     * layout one may have set for generated figure.
     * 
     * @param nodeShape
     *            instance of generated figure class
     * @was-generated
     */
    protected IFigure setupContentPane(final IFigure nodeShape) {
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * @was-generated
     */
    @Override
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * @not-generated
     * @see ShapeEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        final Node node = (Node) this.getModel();
        final EObject element = node.getElement();
        if (element instanceof GaugeCompositeStyle) {
            final GaugeCompositeStyle gaugeCompositeStyle = (GaugeCompositeStyle) element;
            final GaugeCompositeFigure figure = getPrimaryShape();
            while (gaugeCompositeStyle.getSections().size() > figure.getGauges().size()) {
                figure.addGauge();
            }
            final Iterator<GaugeSection> iterSections = gaugeCompositeStyle.getSections().iterator();
            int i = 0;
            while (iterSections.hasNext()) {
                final GaugeSection gaugeSection = iterSections.next();
                fillGaugeProperties(gaugeSection, figure.getGaugeAt(i));
                i++;
            }
            figure.setAlignment(gaugeCompositeStyle.getAlignment());
        }
        DiagramNodeEditPartOperation.refreshFigure(this);
        DiagramElementEditPartOperation.refreshLabelAlignment(((GraphicalEditPart) getParent()).getContentPane(), (LabelStyle) element);
        super.refreshVisuals();
        if (this.getParent() instanceof GraphicalEditPart) {
            // refresh parent visual - workaround : the label disappear when the
            // gauge is refreshed.
        }
    }

    /**
     * 
     * @param gaugeSection
     * @param gaugeFigure
     * @not-generated
     */
    private static void fillGaugeProperties(final GaugeSection gaugeSection, final GaugeSectionFigure gaugeFigure) {
        if (gaugeSection.getBackgroundColor() != null) {
            gaugeFigure.setBackgroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(gaugeSection.getBackgroundColor()));
        }
        if (gaugeSection.getForegroundColor() != null) {
            gaugeFigure.setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(gaugeSection.getForegroundColor()));
        }
        int minValue = 0;
        if (gaugeSection.getMin() != null) {
            minValue = gaugeSection.getMin().intValue();
        }
        int maxValue = 0;
        if (gaugeSection.getMax() != null) {
            maxValue = gaugeSection.getMax().intValue();
        }
        gaugeFigure.setLabel(gaugeSection.getLabel());
        gaugeFigure.setMin(minValue);
        gaugeFigure.setMax(maxValue);
        final Integer value = gaugeSection.getValue();
        if (value == null) {
            gaugeFigure.setValue(minValue);
        } else {
            gaugeFigure.setValue(value.intValue());
        }
    }

    /**
     * 
     * @not-generated
     */
    @Override
    public DragTracker getDragTracker(final Request request) {
        return getParent().getDragTracker(request);
    }

    protected Class<?> getMetamodelType() {
        return GaugeCompositeStyle.class;
    }
}

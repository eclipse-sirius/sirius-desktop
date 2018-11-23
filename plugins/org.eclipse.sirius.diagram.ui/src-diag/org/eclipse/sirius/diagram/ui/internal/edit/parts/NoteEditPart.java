/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramBorderNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.FixedLayoutEditPolicy;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirNoteFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusDefaultSizeNodeFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * @was-generated
 */
public class NoteEditPart extends AbstractNotSelectableShapeNodeEditPart implements IStyleEditPart {

    /**
     * @not-generated : prevent drag of elements
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 3013;

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
    public NoteEditPart(View view) {
        super(view);
    }

    /**
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        // Do nothing.
    }

    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        super.installEditPolicy(key, editPolicy);
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
        AirNoteFigure note = new AirNoteFigure();
        EditPart parent = this.getParent();
        if (parent instanceof IDiagramBorderNodeEditPart) {
            DiagramBorderNodeEditPartOperation.updateTransparencyMode((IDiagramBorderNodeEditPart) parent, note);
        }
        return primaryShape = note;
    }

    /**
     * @was-generated
     */
    public AirNoteFigure getPrimaryShape() {
        return (AirNoteFigure) primaryShape;
    }

    /**
     * @was-generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new SiriusDefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
        return result;
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag
     * and modify it.
     * 
     * @was-generated
     */
    @Override
    protected NodeFigure createNodeFigure() {
        NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane. Respects layout one may have set for generated
     * figure.
     * 
     * @param nodeShape
     *            instance of generated figure class
     * @was-generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
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

    protected Class<?> getMetamodelType() {
        return Note.class;
    }

    private RGBValues getDColor(final EObject eObject) {
        if (eObject instanceof Note) {
            return ((Note) eObject).getColor();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshBackgroundColor()
     */
    @Override
    protected void refreshBackgroundColor() {
        FillStyle style = (FillStyle) getPrimaryView().getStyle(NotationPackage.Literals.FILL_STYLE);
        if (style != null) {
            final EObject dElement = this.resolveSemanticElement();
            final RGBValues rgb = getDColor(dElement);
            if (rgb != null) {
                setBackgroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(rgb));
            }
        }
    }

    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        DiagramNodeEditPartOperation.refreshFigure(this);
        DiagramElementEditPartOperation.refreshLabelAlignment(((GraphicalEditPart) getParent()).getContentPane(), (LabelStyle) resolveSemanticElement());
        Optional<?> labelHandlingFigure = this.getPrimaryShape().getChildren().stream().findFirst();
        if (labelHandlingFigure.isPresent()) {
            DiagramElementEditPartOperation.refreshLabelAlignment((IFigure) labelHandlingFigure.get(), (LabelStyle) resolveSemanticElement());
        }
    }
}

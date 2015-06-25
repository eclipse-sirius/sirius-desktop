/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @was-generated
 */
public class SiriusEditPartFactory implements EditPartFactory {

    /**
     * @was-generated NOT
     */
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            final View view = (View) model;
            switch (SiriusVisualIDRegistry.getVisualID(view)) {

            case DDiagramEditPart.VISUAL_ID:
                return new DDiagramEditPart(view);

            case DNodeEditPart.VISUAL_ID:
                return new DNodeEditPart(view);

            case NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID:
            case NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID:
            case NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID:
            case NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID:
                return new DNodeNameEditPart(view);

            case DNodeContainerEditPart.VISUAL_ID:
                return new DNodeContainerEditPart(view);

            case DNodeContainerNameEditPart.VISUAL_ID:
                return new DNodeContainerNameEditPart(view);

            case DNodeListEditPart.VISUAL_ID:
                return new DNodeListEditPart(view);

            case DNodeListNameEditPart.VISUAL_ID:
                return new DNodeListNameEditPart(view);

            case DNode2EditPart.VISUAL_ID:
                return new DNode2EditPart(view);

            case BundledImageEditPart.VISUAL_ID:
                return new BundledImageEditPart(view);

            case DotEditPart.VISUAL_ID:
                return new DotEditPart(view);

            case GaugeCompositeEditPart.VISUAL_ID:
                return new GaugeCompositeEditPart(view);

            case SquareEditPart.VISUAL_ID:
                return new SquareEditPart(view);

            case EllipseEditPart.VISUAL_ID:
                return new EllipseEditPart(view);

            case LozengeEditPart.VISUAL_ID:
                return new LozengeEditPart(view);

            case WorkspaceImageEditPart.VISUAL_ID:
                return new WorkspaceImageEditPart(view);

            case NoteEditPart.VISUAL_ID:
                return new NoteEditPart(view);

            case CustomStyleEditPart.VISUAL_ID:
                return new CustomStyleEditPart(view);

            case DNode3EditPart.VISUAL_ID:
                return new DNode3EditPart(view);

            case DNodeContainer2EditPart.VISUAL_ID:
                return new DNodeContainer2EditPart(view);

            case DNodeContainerName2EditPart.VISUAL_ID:
                return new DNodeContainerName2EditPart(view);

            case DNodeList2EditPart.VISUAL_ID:
                return new DNodeList2EditPart(view);

            case DNodeListName2EditPart.VISUAL_ID:
                return new DNodeListName2EditPart(view);

            case DNodeListElementEditPart.VISUAL_ID:
                return new DNodeListElementEditPart(view);

            case DNode4EditPart.VISUAL_ID:
                return new DNode4EditPart(view);

            case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
                return new DNodeContainerViewNodeContainerCompartmentEditPart(view);

            case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
                return new DNodeContainerViewNodeContainerCompartment2EditPart(view);

            case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
                return new DNodeListViewNodeListCompartmentEditPart(view);

            case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
                return new DNodeListViewNodeListCompartment2EditPart(view);

            case DEdgeEditPart.VISUAL_ID:
                return new DEdgeEditPart(view);

            case DEdgeNameEditPart.VISUAL_ID:
                return new DEdgeNameEditPart(view);

            case DEdgeBeginNameEditPart.VISUAL_ID:
                return new DEdgeBeginNameEditPart(view);

            case DEdgeEndNameEditPart.VISUAL_ID:
                return new DEdgeEndNameEditPart(view);

            case BracketEdgeEditPart.VISUAL_ID:
                return new BracketEdgeEditPart(view);

            case -1:
                if (ViewType.NOTE.equals(view.getType())) {
                    return new SiriusNoteEditPart(view);
                } else if (ViewType.TEXT.equals(view.getType())) {
                    return new SiriusTextEditPart(view);
                }
            }
        }
        return createUnrecognizedEditPart(context, model);
    }

    /**
     * @was-generated
     */
    private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
        // Handle creation of unrecognized child node EditParts here
        return null;
    }

    /**
     * @not-generated
     */
    public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
        if (source.getFigure() instanceof SiriusWrapLabel)
            return new TextCellEditorLocator((SiriusWrapLabel) source.getFigure());
        else {
            return new LabelCellEditorLocator((Label) source.getFigure());
        }
    }

    /**
     * @not-generated
     */
    public static Class getTextCellEditorClass(GraphicalEditPart source) {
        if (source.getFigure() instanceof SiriusWrapLabel)
            return WrapTextCellEditor.class;
        else {
            return TextDirectEditManager.getTextCellEditorClass(source);
        }
    }

    /**
     * @was-generated
     */
    static private class TextCellEditorLocator implements CellEditorLocator {

        /**
         * @not-generated
         */
        private final SiriusWrapLabel wrapLabel;

        /**
         * @not-generated
         */
        public TextCellEditorLocator(SiriusWrapLabel wrapLabel) {
            this.wrapLabel = wrapLabel;
        }

        /**
         * @not-generated
         */
        public SiriusWrapLabel getWrapLabel() {
            return wrapLabel;
        }

        /**
         * @not-generated add 1 in the width so that the text is not getting
         *                truncated.
         */
        @Override
        public void relocate(CellEditor celleditor) {
            final Text text = (Text) celleditor.getControl();
            final Rectangle rect = new Rectangle(getWrapLabel().getTextBounds());
            getWrapLabel().translateToAbsolute(rect);
            if (getWrapLabel().isTextWrapped() && getWrapLabel().getText().length() > 0) {
                rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
            } else {
                final int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
                rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
            }
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width + 1, rect.height);
            }
        }
    }

    /**
     * @was-generated
     */
    private static class LabelCellEditorLocator implements CellEditorLocator {

        /**
         * @was-generated
         */
        private final Label label;

        /**
         * @was-generated
         */
        public LabelCellEditorLocator(Label label) {
            this.label = label;
        }

        /**
         * @was-generated
         */
        public Label getLabel() {
            return label;
        }

        /**
         * @was-generated
         */
        @Override
        public void relocate(CellEditor celleditor) {
            final Text text = (Text) celleditor.getControl();
            final Rectangle rect = new Rectangle(getLabel().getTextBounds());
            getLabel().translateToAbsolute(rect);
            final int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
            rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
}

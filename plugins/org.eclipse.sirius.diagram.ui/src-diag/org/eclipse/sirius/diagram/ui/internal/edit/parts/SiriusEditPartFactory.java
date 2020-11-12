/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Felix Dorner <felix.dorner@gmail.com> - Bug 533002
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
import org.eclipse.gmf.runtime.common.ui.services.parser.CommonParserHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
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
                } else if (CommonParserHint.DESCRIPTION.equals(view.getType())) {
                    return new SiriusDescriptionCompartmentEditPart(view);
                } else if (ViewType.DIAGRAM_NAME.equals(view.getType())) {
                    return new SiriusDiagramNameCompartmentEditPart(view);
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
            return SiriusWrapTextCellEditor.class;
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
         * @not-generated add 1 in the width so that the text is not getting truncated.
         */
        @Override
        public void relocate(CellEditor celleditor) {
            final Text text = (Text) celleditor.getControl();
            final Rectangle rect = new Rectangle(getWrapLabel().getTextBounds());
            getWrapLabel().translateToAbsolute(rect);
            if (getWrapLabel().isTextWrapped() && getWrapLabel().getText().length() > 0) {
                rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
            } else {
                Font font = text.getFont();
                if (font != null && !font.isDisposed()) {
                    final int avr = FigureUtilities.getFontMetrics(font).getAverageCharWidth();
                    rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
                }
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

    /**
     * Implementation copied from
     * org.eclipse.gmf.runtime.common.ui.services.properties.extended.ExtendedTextPropertyDescriptor.createPropertyEditor().new
     * TextCellEditor().
     *
     */
    public static final class SiriusWrapTextCellEditor extends WrapTextCellEditor {

        /**
         * Creates a new text string cell editor with no control The cell editor value is the string itself, which is
         * initially the empty string. Initially, the cell editor has no cell validator.
         */
        public SiriusWrapTextCellEditor() {
            super();
        }

        /**
         * Creates a new text string cell editor parented under the given control. The cell editor value is the string
         * itself, which is initially the empty string. Initially, the cell editor has no cell validator.
         *
         * @param parent
         *            the parent control
         */
        public SiriusWrapTextCellEditor(Composite parent) {
            super(parent);
        }

        /**
         * Creates a new text string cell editor parented under the given control. The cell editor value is the string
         * itself, which is initially the empty string. Initially, the cell editor has no cell validator.
         *
         * @param parent
         *            the parent control
         * @param style
         *            the style bits
         */
        public SiriusWrapTextCellEditor(Composite parent, int style) {
            super(parent, style);
        }

        /**
         * Processes a key release event that occurred in this cell editor.
         * <p>
         * The <code>TextCellEditor</code> implementation of this framework method ignores when the RETURN key is
         * pressed since this is handled in <code>handleDefaultSelection</code>. An exception is made for Ctrl+Enter for
         * multi-line texts, since a default selection event is not sent in this case.
         * </p>
         *
         * @param keyEvent
         *            the key event
         */
        @Override
        protected void keyReleaseOccured(KeyEvent keyEvent) {
            if (keyEvent.character == '\r') { // Return key

                // WrapTextCellEditor.keyReleaseOccured, return key case:

                // The super behavior of this method is to apply the cell editor value
                // if the 'Return' key is pressed with the 'CTRL' key. Otherwise, the
                // 'Return' key is used to insert a new line. This is exactly opposite
                // to what we expect in this editor and that is why we are reversing it.
                if ((keyEvent.stateMask & SWT.CTRL) != 0) {
                    keyEvent.stateMask &= ~SWT.CTRL;
                } else {
                    keyEvent.stateMask |= SWT.CTRL;
                }

                // TextCellEditor.keyReleaseOccured, return key case:

                // Enter is handled in handleDefaultSelection.
                // Do not apply the editor value in response to an Enter key event
                // since this can be received from the IME when the intent is -not-
                // to apply the value.
                // See bug 39074 [CellEditors] [DBCS] canna input mode fires bogus event from Text Control
                //
                // An exception is made for Ctrl+Enter for multi-line texts, since
                // a default selection event is not sent in this case.
                if (text != null && !text.isDisposed() && (text.getStyle() & SWT.MULTI) != 0) {
                    if ((keyEvent.stateMask & SWT.CTRL) != 0) {

                        // Sirius specific behavior for direct edit
                        checkElementAndProcessApplyOrCancel();
                        return;
                    }
                }
                return;

            }
            super.keyReleaseOccured(keyEvent);
        }

        private void checkElementAndProcessApplyOrCancel() {

            String value = StringUtil.EMPTY_STRING;
            if (text != null && !text.isDisposed()) {
                String newValue = text.getText();
                if (!StringUtil.isEmpty(newValue)) {
                    value = newValue;
                }
            }

            boolean newValidState = isCorrect(value);
            if (!newValidState) {
                fireCancelEditor();
                deactivate();
            } else {
                fireApplyEditorValue();
                deactivate();
            }
        }

        @Override
        protected void handleDefaultSelection(SelectionEvent event) {
            // Sirius specific behavior for direct edit
            checkElementAndProcessApplyOrCancel();
        }

        @Override
        protected void focusLost() {
            if (isActivated()) {
                // Sirius specific behavior for direct edit
                checkElementAndProcessApplyOrCancel();
            }
        }

        @Override
        protected void editOccured(ModifyEvent e) {
            if (!isActivated()) {
                // Avoid double validation and duplicated MessageDialog in case of LockedInstanceException
                return;
            }

            super.editOccured(e);
        }

    }
}

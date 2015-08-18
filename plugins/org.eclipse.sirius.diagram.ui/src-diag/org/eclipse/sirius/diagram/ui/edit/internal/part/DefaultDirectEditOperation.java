/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.DiagramNameEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ToolBasedLabelDirectEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusEditPartFactory;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusParserProvider;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Provides basic direct edit operations.
 * 
 * @author ymortier
 */
public final class DefaultDirectEditOperation {

    /**
     * Avoid instantiation
     */
    private DefaultDirectEditOperation() {
        // empty.
    }

    /**
     * Performs a direct edit.
     * 
     * @param self
     *            this edit part.
     * @param request
     *            the edit request.
     * @param figure
     *            the label.
     */
    public static void performDirectEditRequest(final IDiagramElementEditPart self, final Request request, final SiriusWrapLabel figure) {
        final Request theRequest = request;
        try {
            self.getEditingDomain().runExclusive(new Runnable() {
                public void run() {
                    if (self.isActive() && self.isEditModeEnabled() && hasDirectEditTool(self)) {

                        final DefaultTextAwareEditPart defaultTextAwareEditPart = new DefaultTextAwareEditPart(self.getNotationView(), figure, self.getViewer());
                        defaultTextAwareEditPart.setParent(self);

                        if (!DefaultDirectEditOperation.isFigureAlreadyExist(self, figure)) {
                            DiagramNameEditPartOperation.refreshFont(defaultTextAwareEditPart);
                            if (figure.getParent() == null) {
                                figure.setParent(self.getFigure());
                            }
                            figure.setLocation(self.getFigure().getBounds().getTop());
                        }

                        final DirectEditManager editManager = new TextDirectEditManager(defaultTextAwareEditPart, SiriusEditPartFactory.getTextCellEditorClass(defaultTextAwareEditPart),
                                SiriusEditPartFactory.getTextCellEditorLocator(defaultTextAwareEditPart));
                        if (theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
                            final Character initialChar = (Character) theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
                            DefaultDirectEditOperation.performDirectEdit(self, initialChar.charValue(), editManager);
                        } else if ((theRequest instanceof DirectEditRequest) && (defaultTextAwareEditPart.getEditText().equals(defaultTextAwareEditPart.getLabelText()))) {
                            final DirectEditRequest editRequest = (DirectEditRequest) theRequest;
                            DefaultDirectEditOperation.performDirectEdit(self, editRequest.getLocation(), editManager);
                        } else {
                            DefaultDirectEditOperation.performDirectEdit(self, editManager);
                        }
                    }
                }
            });
        } catch (final InterruptedException e) {
            DiagramPlugin.getDefault().logError(e.getMessage(), e);
        }
    }

    private static boolean hasDirectEditTool(IDiagramElementEditPart part) {
        DDiagramElement elt = part.resolveDiagramElement();
        if (elt != null) {
            RepresentationElementMapping mapping = elt.getMapping();
            return (mapping instanceof DiagramElementMapping) && ((DiagramElementMapping) mapping).getLabelDirectEdit() != null;
        } else {
            return false;
        }
    }

    private static boolean isFigureAlreadyExist(final IDiagramElementEditPart self, final SiriusWrapLabel figure) {
        Iterator children = self.getChildren().iterator();
        while (children.hasNext()) {
            Object obj = children.next();
            if (obj instanceof AbstractGraphicalEditPart) {
                AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) obj;
                if (ep.getContentPane() != null && ep.getContentPane().getChildren().contains(figure)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void performDirectEdit(final IDiagramElementEditPart self, final char initialCharacter, final DirectEditManager editManager) {
        if (editManager instanceof TextDirectEditManager) {
            ((TextDirectEditManager) editManager).show(initialCharacter);
        } else {
            DefaultDirectEditOperation.performDirectEdit(self, editManager);
        }
    }

    private static void performDirectEdit(final IDiagramElementEditPart self, final DirectEditManager editManager) {
        editManager.show();
    }

    private static void performDirectEdit(final IDiagramElementEditPart self, final Point eventLocation, final DirectEditManager editManager) {
        if (editManager.getClass() == TextDirectEditManager.class) {
            ((TextDirectEditManager) editManager).show(eventLocation.getSWTPoint());
        }
    }

    /**
     * A basic text aware edit part.
     * 
     * @author ymortier
     */
    private static class DefaultTextAwareEditPart extends AbstractDiagramNameEditPart {

        /** The label. */
        private IFigure label;

        /** The parser. */
        private IParser parser;

        /** The graphical viewer. */
        private EditPartViewer viewer;

        /**
         * Default constructor.
         * 
         * @param view
         */
        public DefaultTextAwareEditPart(final View modelView, final IFigure label, final EditPartViewer viewer) {
            super(modelView);
            this.label = label;
            this.viewer = viewer;
            this.installEditPolicy(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT, new ToolBasedLabelDirectEditPolicy(getEditingDomain()));
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gef.editparts.AbstractEditPart#getViewer()
         */
        @Override
        public EditPartViewer getViewer() {
            return this.viewer;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramNameEditPart#setLabel(org.eclipse.draw2d.IFigure)
         */
        public void setLabel(final IFigure label) {
            this.label = label;
        }

        public IContentAssistProcessor getCompletionProcessor() {
            return null;
        }

        public String getEditText() {
            if (resolveSemanticElement() == null || getParser() == null) {
                return ""; //$NON-NLS-1$
            }
            return getParser().getEditString(new EObjectAdapter(resolveSemanticElement()), getParserOptions().intValue());
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart#getEditTextValidator()
         */
        public ICellEditorValidator getEditTextValidator() {
            return new ICellEditorValidator() {

                public String isValid(final Object value) {
                    if (value instanceof String) {
                        final EObject element = resolveSemanticElement();
                        final IParser iParser = getParser();
                        try {
                            final IParserEditStatus valid = (IParserEditStatus) getEditingDomain().runExclusive(new RunnableWithResult.Impl() {

                                public void run() {
                                    setResult(iParser.isValidEditString(new EObjectAdapter(element), (String) value));
                                }
                            });
                            return valid.getCode() == IParserEditStatus.EDITABLE ? null : valid.getMessage();
                        } catch (final InterruptedException ie) {
                            // empty
                        }
                    }

                    // shouldn't get here
                    return null;
                }
            };
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart#getParser()
         */
        public IParser getParser() {
            if (parser == null) {
                final String parserHint = Integer.toString(NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID);
                final IAdaptable hintAdapter = new SiriusParserProvider.HintAdapter(SiriusElementTypes.DNode_2001, resolveSemanticElement(), parserHint);
                parser = ParserService.getInstance().getParser(hintAdapter);
            }
            return parser;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart#getParserOptions()
         */
        public ParserOptions getParserOptions() {
            return ParserOptions.NONE;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart#setLabelText(java.lang.String)
         */
        public void setLabelText(final String text) {
            // empty
        }

        /**
         * Returns the text of the label.
         * 
         * @return the text of the label.
         */
        public String getLabelText() {
            String text = null;
            final EObject parserElement = resolveSemanticElement();
            if (parserElement != null && getParser() != null) {
                text = getParser().getPrintString(new EObjectAdapter(parserElement), getParserOptions().intValue());
            }
            if (text == null || text.length() == 0) {
                text = ""; //$NON-NLS-1$
            }
            return text;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getFigure()
         */
        @Override
        public IFigure getFigure() {
            return this.label;
        }

    }

}

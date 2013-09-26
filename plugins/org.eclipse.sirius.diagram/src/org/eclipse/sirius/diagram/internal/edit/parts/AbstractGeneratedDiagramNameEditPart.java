/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.internal.edit.policies.SiriusTextSelectionEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Basic implementation of edit part that can have a label.
 * 
 * @author ymortier
 */
public abstract class AbstractGeneratedDiagramNameEditPart extends AbstractDiagramNameEditPart {

    /**
     * @was-generated
     */
    protected DirectEditManager manager;

    /**
     * @was-generated
     */
    protected IParser parser;

    /**
     * @was-generated
     */
    protected List parserElements;

    /**
     * @was-generated
     */
    protected String defaultText;

    /**
     * Creates a new <code>AbstractDiagramNameEditPart</code>.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractGeneratedDiagramNameEditPart(final View view) {
        super(view);
    }

    /**
     * @was-generated
     */
    protected String getLabelText() {
        String text = null;
        final EObject parserElement = getParserElement();
        if (parserElement != null && getParser() != null) {
            text = getParser().getPrintString(new EObjectAdapter(parserElement), getParserOptions().intValue());
        }
        if (text == null || text.length() == 0) {
            text = defaultText;
        }
        return text;
    }

    /**
     * @was-generated
     */
    public void setLabelText(final String text) {
        setLabelTextHelper(getFigure(), text);
        final Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (pdEditPolicy instanceof SiriusTextSelectionEditPolicy) {
            ((SiriusTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
        }
    }

    /**
     * @not-generated
     */
    protected String getLabelTextHelper(final IFigure figure) {
        if (figure instanceof SiriusWrapLabel) {
            return ((SiriusWrapLabel) figure).getText();
        } else {
            return ((Label) figure).getText();
        }
    }

    /**
     * @not-generated
     */
    protected void setLabelTextHelper(final IFigure figure, final String text) {
        if (figure instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) figure).setText(text);
        } else {
            ((Label) figure).setText(text);
        }
    }

    /**
     * @not-generated
     */
    protected Image getLabelIconHelper(final IFigure figure) {
        if (figure instanceof SiriusWrapLabel) {
            return ((SiriusWrapLabel) figure).getIcon();
        } else {
            return ((Label) figure).getIcon();
        }
    }

    /**
     * @not-generated
     */
    protected void setLabelIconHelper(final IFigure figure, final Image icon) {
        if (figure instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) figure).setIcon(icon);
        } else {
            ((Label) figure).setIcon(icon);
        }
    }

    /**
     * @was-generated
     */
    protected List getModelChildren() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @was-generated
     */
    public IGraphicalEditPart getChildBySemanticHint(final String semanticHint) {
        return null;
    }

    /**
     * @was-generated : handle permissions
     */
    protected boolean isEditable() {
        EObject resolvedSemElt = resolveSemanticElement();
        if (resolvedSemElt instanceof DSemanticDecorator && ((DSemanticDecorator) resolvedSemElt).getTarget() != null) {
            EObject target = ((DSemanticDecorator) resolvedSemElt).getTarget();
            final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(target);
            if (authority != null)
                if (!authority.canEditInstance(target))
                    return false;
        }
        return getParser() != null;
    }

    /**
     * @was-generated
     */
    public String getEditText() {
        if (getParserElement() == null || getParser() == null) {
            return ""; //$NON-NLS-1$
        }
        return getParser().getEditString(new EObjectAdapter(getParserElement()), getParserOptions().intValue());
    }

    /**
     * @was-generated
     */
    public ICellEditorValidator getEditTextValidator() {
        return new ICellEditorValidator() {

            public String isValid(final Object value) {
                if (value instanceof String) {
                    final EObject element = getParserElement();
                    final IParser validationParser = getParser();
                    try {
                        final IParserEditStatus valid = (IParserEditStatus) getEditingDomain().runExclusive(new RunnableWithResult.Impl() {

                            public void run() {
                                setResult(validationParser.isValidEditString(new EObjectAdapter(element), (String) value));
                            }
                        });
                        return valid.getCode() == ParserEditStatus.EDITABLE ? null : valid.getMessage();
                    } catch (final InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

                // shouldn't get here
                return null;
            }
        };
    }

    /**
     * @was-generated
     */
    public IContentAssistProcessor getCompletionProcessor() {
        if (getParserElement() == null || getParser() == null) {
            return null;
        }
        return getParser().getCompletionProcessor(new EObjectAdapter(getParserElement()));
    }

    /**
     * @was-generated
     */
    public ParserOptions getParserOptions() {
        return ParserOptions.NONE;
    }

    /**
     * @was-generated
     */
    protected EObject getParserElement() {
        return resolveSemanticElement();
    }

    /**
     * @was-generated
     */
    protected DirectEditManager getManager() {
        if (manager == null) {
            setManager(new TextDirectEditManager(this, SiriusEditPartFactory.getTextCellEditorClass(this), SiriusEditPartFactory.getTextCellEditorLocator(this)));
        }
        return manager;
    }

    /**
     * @was-generated
     */
    protected void setManager(final DirectEditManager manager) {
        this.manager = manager;
    }

    /**
     * @was-generated
     */
    protected void performDirectEdit() {
        getManager().show();
    }

    /**
     * @was-generated
     */
    protected void performDirectEdit(final Point eventLocation) {
        if (getManager().getClass() == TextDirectEditManager.class) {
            ((TextDirectEditManager) getManager()).show(eventLocation.getSWTPoint());
        }
    }

    /**
     * @was-generated
     */
    private void performDirectEdit(final char initialCharacter) {
        if (getManager() instanceof TextDirectEditManager) {
            ((TextDirectEditManager) getManager()).show(initialCharacter);
        } else {
            performDirectEdit();
        }
    }

    /**
     * @was-generated
     */
    protected void performDirectEditRequest(final Request request) {
        final Request theRequest = request;
        try {
            getEditingDomain().runExclusive(new Runnable() {

                public void run() {
                    if (isActive() && isEditable()) {
                        if (theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
                            final Character initialChar = (Character) theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
                            performDirectEdit(initialChar.charValue());
                        } else if ((theRequest instanceof DirectEditRequest) && (getEditText().equals(getLabelText()))) {
                            final DirectEditRequest editRequest = (DirectEditRequest) theRequest;
                            performDirectEdit(editRequest.getLocation());
                        } else {
                            performDirectEdit();
                        }
                    }
                }
            });
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @was-generated
     */
    protected void refreshVisuals() {
        super.refreshVisuals();
        refreshLabel();
        refreshFont();
        refreshFontColor();
        refreshUnderline();
        refreshStrikeThrough();
    }

    /**
     * @was-generated
     */
    protected void refreshLabel() {
        setLabelTextHelper(getFigure(), getLabelText());
        setLabelIconHelper(getFigure(), getLabelIcon());
        final Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (pdEditPolicy instanceof SiriusTextSelectionEditPolicy) {
            ((SiriusTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
        }
    }

    /**
     * @not-generated
     */
    protected void refreshUnderline() {
        final FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        if (style != null && getFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getFigure()).setTextUnderline(style.isUnderline());
        }
    }

    /**
     * @not-generated
     */
    protected void refreshStrikeThrough() {
        final FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
        if (style != null && getFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getFigure()).setTextStrikeThrough(style.isStrikeThrough());
        }
    }

    /**
     * @was-generated
     */
    protected void setFontColor(final Color color) {
        getFigure().setForegroundColor(color);
    }

    /**
     * @was-generated
     */
    protected void addSemanticListeners() {
        if (getParser() instanceof ISemanticParser) {
            final EObject element = resolveSemanticElement();
            parserElements = ((ISemanticParser) getParser()).getSemanticElementsBeingParsed(element);
            for (int i = 0; i < parserElements.size(); i++) {
                addListenerFilter("SemanticModel" + i, this, (EObject) parserElements.get(i)); //$NON-NLS-1$
            }
        } else {
            super.addSemanticListeners();
        }
    }

    /**
     * @was-generated
     */
    protected void removeSemanticListeners() {
        if (parserElements != null) {
            for (int i = 0; i < parserElements.size(); i++) {
                removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
            }
        } else {
            super.removeSemanticListeners();
        }
    }

    /**
     * @was-generated
     */
    protected AccessibleEditPart getAccessibleEditPart() {
        if (accessibleEP == null) {
            accessibleEP = new AccessibleGraphicalEditPart() {

                public void getName(final AccessibleEvent e) {
                    e.result = getLabelTextHelper(getFigure());
                }
            };
        }
        return accessibleEP;
    }

    /**
     * @was-generated
     */
    private View getFontStyleOwnerView() {
        return getPrimaryView();
    }

    /**
     * @was-generated
     */
    protected void handleNotificationEvent(final Notification event) {
        final Object feature = event.getFeature();
        if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
            final Integer c = (Integer) event.getNewValue();
            setFontColor(DiagramColorRegistry.getInstance().getColor(c));
        } else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
            refreshUnderline();
        } else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
            refreshStrikeThrough();
        } else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature)
                || NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
            refreshFont();
        } else {
            if (getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
                refreshLabel();
            }
            if (getParser() instanceof ISemanticParser) {
                final ISemanticParser modelParser = (ISemanticParser) getParser();
                if (modelParser.areSemanticElementsAffected(null, event)) {
                    removeSemanticListeners();
                    if (resolveSemanticElement() != null) {
                        addSemanticListeners();
                    }
                    refreshLabel();
                }
            }
        }
        super.handleNotificationEvent(event);
    }
}

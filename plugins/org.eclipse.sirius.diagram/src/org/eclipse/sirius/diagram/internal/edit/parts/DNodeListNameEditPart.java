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
package org.eclipse.sirius.diagram.internal.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.internal.providers.SiriusParserProvider;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;

/**
 * @was-generated
 */
public class DNodeListNameEditPart extends AbstractGeneratedDiagramNameEditPart implements ITextAwareEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 5007;

    /**
     * @was-generated
     */
    public DNodeListNameEditPart(final View view) {
        super(view);
    }

    /**
     * We want a special behavior with direct editing.
     * 
     * @not-generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new NonResizableEditPolicy() {

            protected List createSelectionHandles() {
                final List handles = new ArrayList();
                NonResizableHandleKit.addMoveHandle((GraphicalEditPart) getHost(), handles);
                return handles;
            }

            public Command getCommand(final Request request) {
                return null;
            }

            public boolean understandsRequest(final Request request) {
                return false;
            }
        });
        // Remove this edit policy because it interfers with next new edit
        // policy
        removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeCreationEditPolicy() {

            /**
             * {@inheritDoc}
             */
            @Override
            public EditPart getTargetEditPart(final Request request) {
                // Forward this request to container : label = container
                return getHost().getParent();
            }
        });
    }

    /**
     * @not-generated
     */
    public void setLabel(final SiriusWrapLabel figure) {
        unregisterVisuals();
        setFigure(figure);
        defaultText = getLabelTextHelper(figure);
        registerVisuals();
        refreshVisuals();
    }

    /**
     * @was-generated
     */
    public IParser getParser() {
        if (parser == null) {
            final String parserHint = ((View) getModel()).getType();
            final IAdaptable hintAdapter = new SiriusParserProvider.HintAdapter(SiriusElementTypes.DNodeList_2003, getParserElement(), parserHint);
            parser = ParserService.getInstance().getParser(hintAdapter);
        }
        return parser;
    }

    /**
     * @was-generated
     */
    protected void handleNotificationEvent(final Notification event) {
        final Object feature = event.getFeature();
        if (DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle() == feature) {
            refreshVisuals();
        }
        super.handleNotificationEvent(event);
    }

    /**
     * @was-generated
     */
    protected IFigure createFigure() {
        // Parent should assign one using setLabel() method
        return null;
    }

    /**
     * @not-generated
     */
    public void setLabel(final IFigure figure) {
        if (figure instanceof SiriusWrapLabel) {
            this.setLabel((SiriusWrapLabel) figure);
        }
    }

    public void activate() {
        if (!isActive()) {
            super.activate();
        }
    }

    public void deactivate() {
        if (isActive()) {
            super.deactivate();
        }
    }

    /**
     * Returns all the semantic elements (instances of <code>EObject</code>).
     * The list that is returned by this method is a view. If the client try to
     * change the content of the list then a
     * {@link UnsupportedOperationException} will be thrown.
     * 
     * @return all the semantic elements (instances of <code>EObject</code>).
     */
    public List getSemanticElements() {
        final View view = (View) getModel();
        final EObject viewpointElement = view.getElement();
        if (viewpointElement instanceof DDiagramElement) {
            final List semanticElements = Collections.unmodifiableList(((DDiagramElement) viewpointElement).getSemanticElements());
            return semanticElements;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        return super.isSelectable() && getParent().isSelectable();
    }
}

/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.tools.internal.command.builders.DirectEditCommandBuilder;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LabelDeletionEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LabelSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ToolBasedLabelDirectEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.refresh.LabelAndIconRefresher;
import org.eclipse.sirius.diagram.ui.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.swt.graphics.Image;

/**
 * Basic implementation of edit part that can have a label.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramNameEditPart extends LabelEditPart implements IDiagramNameEditPart {

    private LabelAndIconRefresher labelAndIconRefresher;

    /**
     * Creates a new <code>AbstractDiagramNameEditPart</code>.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramNameEditPart(final View view) {
        super(view);
    }

    @Override
    public void activate() {
        super.activate();
        labelAndIconRefresher = new LabelAndIconRefresher(this);
    }

    /**
     * Check if the edit part had a resizable edit policy installed or not.
     * 
     * @return true if this edit part is resizable, false otherwise
     */
    public boolean isResizable() {
        EditPolicy editPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
        if (editPolicy instanceof ResizableEditPolicy)
            return true;
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#registerModel()
     */
    @Override
    protected void registerModel() {
        super.registerModel();
        DiagramElementEditPartOperation.registerModel(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#unregisterModel()
     */
    @Override
    protected void unregisterModel() {
        super.unregisterModel();
        DiagramElementEditPartOperation.unregisterModel(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshFont()
     */
    @Override
    protected void refreshFont() {
        DiagramNameEditPartOperation.refreshFont(this);
    }

    /** Refresh the editpart's figure font colour. */
    @Override
    protected void refreshFontColor() {
    }

    @Override
    public Image getLabelIcon() {
        return DiagramNameEditPartOperation.getLabelIcon(this);
    }

    @Override
    public IStyleEditPart getStyleEditPart() {
        if (this.getParent() instanceof IDiagramElementEditPart) {
            return DiagramElementEditPartOperation.getStyleEditPart((IDiagramElementEditPart) this.getParent());
        }
        return null;
    }

    @Override
    public NotificationListener getEAdapterDiagramElement() {
        return null;
    }

    @Override
    public NotificationListener getEditModeListener() {
        return null;
    }

    @Override
    public DDiagramElement resolveDiagramElement() {
        return DiagramElementEditPartOperation.resolveDiagramElement(this);
    }

    @Override
    public List<EObject> resolveAllSemanticElements() {
        return DiagramElementEditPartOperation.resolveAllSemanticElements(this);
    }

    @Override
    public EObject resolveTargetSemanticElement() {
        return DiagramElementEditPartOperation.resolveTargetSemanticElement(this);
    }

    @Override
    public Class<?> getMetamodelType() {
        Class<?> clazz = null;
        if (this.getParent() instanceof IDiagramNodeEditPart) {
            clazz = DNode.class;
        } else if (this.getParent() instanceof IDiagramEdgeEditPart) {
            clazz = DEdge.class;
        } else if (this.getParent() instanceof IDiagramContainerEditPart) {
            clazz = DNodeContainer.class;
        } else if (this.getParent() instanceof IDiagramListEditPart) {
            clazz = DNodeList.class;
        } else {
            throw new IllegalStateException();
        }
        return clazz;
    }

    @Override
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return null;
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // We want a special behavior with direct editing.
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
        if (isDirectEditEnabled()) {
            installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new ToolBasedLabelDirectEditPolicy(getEditingDomain()));
        }

        // We want a special behavior with deletion : label view only cannot be
        // deleted, it should trigger its parent deletion.
        removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new LabelSemanticEditPolicy());
        removeEditPolicy(EditPolicy.COMPONENT_ROLE);
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new LabelDeletionEditPolicy());
    }

    /**
     * Test if this edit part must provides the direct edit.
     * 
     * @return true if the semantic element have a mapping with a direct edit
     *         tool and if the parent diagram is not in LayoutingMode, false
     *         otherwise
     */
    protected boolean isDirectEditEnabled() {
        boolean directEditEnabled = false;
        final EObject eObj = resolveSemanticElement();
        if (eObj instanceof DMappingBased) {
            final DMappingBased mappingBasedObject = (DMappingBased) eObj;
            if (mappingBasedObject.getMapping() != null && mappingBasedObject instanceof DDiagramElement
                    && ((DDiagramElement) mappingBasedObject).getDiagramElementMapping().getLabelDirectEdit() != null) {
                // check precondition
                DirectEditLabel labelDirectEdit = ((DDiagramElement) mappingBasedObject).getDiagramElementMapping().getLabelDirectEdit();
                final DirectEditCommandBuilder builder = new DirectEditCommandBuilder((DDiagramElement) mappingBasedObject, labelDirectEdit, null);
                directEditEnabled = builder.canDirectEdit();
            }
        }
        return directEditEnabled;
    }

    /**
     * Activate directEdit only if there is a directEdit tool on the mapping.
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#performRequest(org.eclipse.gef.Request)
     */
    @Override
    public void performRequest(final Request request) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(request.getType())) {
            if (isDirectEditEnabled()) {
                // if the odesign has been updated and a new direct edit tool
                // has been added, then we install a new direct edit policy if
                // needed.
                addDirectEditPolicyIfNotInstalled();
                performDirectEditRequest(request);
            }
        } else {
            super.performRequest(request);
        }
    }

    /**
     * Checks whether a {@link EditPolicy#DIRECT_EDIT_ROLE} is already install,
     * otherwise we install it.
     */
    private void addDirectEditPolicyIfNotInstalled() {
        if (getEditPolicy(EditPolicy.DIRECT_EDIT_ROLE) == null) {
            installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new ToolBasedLabelDirectEditPolicy(getEditingDomain()));
        }
    }

    /**
     * Overridden to set the tool-tip.
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        if (resolveDiagramElement() != null) {
            final DDiagramElement view = resolveDiagramElement();
            setTooltipText(view.getTooltipText());
        }
    }

    /**
     * Sets the tool-tip of an {@link IAbstractDiagramNodeEditPart} to the
     * specified text.
     * 
     * @param text
     *            the tool-tip's text.
     * @since 0.9.0
     */
    @Override
    public void setTooltipText(final String text) {
        if (!StringUtil.isEmpty(text)) {
            final IFigure tt = getFigure().getToolTip();
            if (tt instanceof Label) {
                ((Label) tt).setText(text);
            } else {
                getFigure().setToolTip(new Label(text));
            }
        } else {
            getFigure().setToolTip(null);
        }
    }

    /**
     * return the current diagram event broker.
     * 
     * @return the current diagram event broker.
     */
    protected DiagramEventBroker getDiagramEventBroker() {
        final TransactionalEditingDomain theEditingDomain = getEditingDomain();
        if (theEditingDomain != null) {
            return DiagramEventBroker.getInstance(theEditingDomain);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#getAdapter()
     */
    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class key) {
        try {
            return super.getAdapter(key);
        } catch (NullPointerException e) {
            // When a label is set to null from a direct edit operation, the
            // parent of this editpart (linked to this label) is set to null
            // by the safeRefresh method in the handleNotificationEvent of
            // AbstractDiagramNodeEditPartOperation class.
            // Then, the bringDown method of the TestDirectEditManager class is
            // called to unhook possible listeners.
            // So, getAdapter is called here and as the getParent() returns
            // null, a NullPointerException is thrown.
            // It's very likely that this exception be thrown cause of the
            // nullity of the parent. Besides, when getAdapter is called in the
            // unhookListeners method of TestDirectEditManager, the listeners
            // seems to be already reset
            // (and the nullity of the returned adapter is checked).
            return null;
        }
    }

    @Override
    public void deactivate() {
        if (!isActive()) {
            return;
        }
        labelAndIconRefresher.dispose();
        labelAndIconRefresher = null;
        super.deactivate();
    }

}

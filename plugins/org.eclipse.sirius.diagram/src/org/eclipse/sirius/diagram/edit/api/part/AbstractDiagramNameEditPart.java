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
package org.eclipse.sirius.diagram.edit.api.part;

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
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationPreCommitListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.graphical.edit.policies.LabelDeletionEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.LabelSemanticEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.ToolBasedLabelDirectEditPolicy;
import org.eclipse.sirius.diagram.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;

/**
 * Basic implementation of edit part that can have a label.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramNameEditPart extends LabelEditPart implements IDiagramNameEditPart {

    /**
     * Creates a new <code>AbstractDiagramNameEditPart</code>.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramNameEditPart(final View view) {
        super(view);
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getLabelIcon()
     */
    public Image getLabelIcon() {
        return DiagramNameEditPartOperation.getLabelIcon(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getStyleEditPart()
     */
    public IStyleEditPart getStyleEditPart() {
        if (this.getParent() instanceof IDiagramElementEditPart) {
            return DiagramElementEditPartOperation.getStyleEditPart((IDiagramElementEditPart) this.getParent());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEAdapterDiagramElement()
     */
    public NotificationListener getEAdapterDiagramElement() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEAdapterSemanticElements()
     */
    public NotificationPreCommitListener getEAdapterSemanticElements() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public NotificationListener getEditModeListener() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveDiagramElement()
     */
    public DDiagramElement resolveDiagramElement() {
        return DiagramElementEditPartOperation.resolveDiagramElement(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveAllSemanticElements()
     */
    public List<EObject> resolveAllSemanticElements() {
        return DiagramElementEditPartOperation.resolveAllSemanticElements(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveTargetSemanticElement()
     */
    public EObject resolveTargetSemanticElement() {
        return DiagramElementEditPartOperation.resolveTargetSemanticElement(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getMetamodelType()
     */
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEditPartAuthorityListener()
     */
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart#createDefaultEditPolicies()
     */
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
                // Layouting mode on diagrams
                // if the ddiagram is in LayoutingMode, we do not allow
                // direct edit
                directEditEnabled = !isInLayoutingModeDiagram((DDiagramElement) mappingBasedObject);
            }
        }
        return directEditEnabled;
    }

    /**
     * Indicates if the given {@link DDiagramElement}'s parent
     * {@link org.eclipse.sirius.viewpoint.DDiagram} is in Layouting Mode.
     * 
     * @param element
     *            the element to test
     * @return true if the given {@link DDiagramElement}'s parent
     *         {@link org.eclipse.sirius.viewpoint.DDiagram} is in Layouting Mode,
     *         false otherwise
     */
    private boolean isInLayoutingModeDiagram(DDiagramElement element) {
        if (element.getParentDiagram() != null) {
            return element.getParentDiagram().isIsInLayoutingMode();
        }
        return false;
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
     * @since 2.0
     */
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
    public Object getAdapter(@SuppressWarnings("rawtypes")
    Class key) {
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

}

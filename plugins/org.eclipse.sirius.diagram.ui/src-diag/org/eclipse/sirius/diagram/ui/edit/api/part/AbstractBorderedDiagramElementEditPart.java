/*******************************************************************************
 * Copyright (c) 2013, 2017 THALES GLOBAL SERVICES and others.
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
import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.internal.part.AbstractDiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.EditStatusUpdater;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusDecoratorEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.sirius.diagram.ui.tools.internal.ruler.SiriusSnapToHelperUtil;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * Some Default behaviors for non border IAbstractDiagramNodeEditPart: nodes, lists and containers.
 * 
 * @author mporhel
 */
public abstract class AbstractBorderedDiagramElementEditPart extends AbstractBorderedShapeEditPart implements IAbstractDiagramNodeEditPart {
    /** The authority listener. */
    protected EditPartAuthorityListener authListener = new EditPartAuthorityListener(this);

    /** Listens the diagram element. */
    private NotificationListener adapterDiagramElement;

    /** listen to semantic elements container */
    private NotificationListener editModeListener = new EditStatusUpdater(this);

    /**
     * Creates a new Node edit part.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractBorderedDiagramElementEditPart(final View view) {
        super(view);
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        removeEditPolicy(EditPolicyRoles.DECORATION_ROLE);
        installEditPolicy(EditPolicyRoles.DECORATION_ROLE, new SiriusDecoratorEditPolicy());
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
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEAdapterDiagramElement()
     */
    @Override
    public NotificationListener getEAdapterDiagramElement() {
        if (this.adapterDiagramElement == null) {
            this.adapterDiagramElement = DiagramElementEditPartOperation.createEApdaterDiagramElement(this);
        }
        return this.adapterDiagramElement;
    }

    @Override
    protected void setVisibility(boolean vis) {
        ShowingViewUtil.setVisibility(this, vis, SELECTED_NONE, getFlag(FLAG__AUTO_CONNECTIONS_VISIBILITY));
    }

    @Override
    protected void setConnectionsVisibility(boolean visibility) {
        ShowingViewUtil.setConnectionsVisibility(this, (View) getModel(), SELECTED_NONE, visibility);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationListener getEditModeListener() {
        return this.editModeListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getEditPartAuthorityListener()
     */
    @Override
    public EditPartAuthorityListener getEditPartAuthorityListener() {
        return this.authListener;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveAllSemanticElements()
     */
    @Override
    public List<EObject> resolveAllSemanticElements() {
        return DiagramElementEditPartOperation.resolveAllSemanticElements(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveDiagramElement()
     */
    @Override
    public DDiagramElement resolveDiagramElement() {
        return DiagramElementEditPartOperation.resolveDiagramElement(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#resolveTargetSemanticElement()
     */
    @Override
    public EObject resolveTargetSemanticElement() {
        return DiagramElementEditPartOperation.resolveTargetSemanticElement(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getStyleEditPart()
     */
    @Override
    public IStyleEditPart getStyleEditPart() {
        return DiagramElementEditPartOperation.getStyleEditPart(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activate() {
        if (!isActive()) {
            final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
            auth.addAuthorityListener(this.getEditPartAuthorityListener());
            super.activate();
            DiagramElementEditPartOperation.activate(this);
        }
        this.getEditPartAuthorityListener().refreshEditMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableEditMode() {
        /*
         * We want to be sure nobody is enabling the edit mode if the element is locked.
         */
        if (!this.getEditPartAuthorityListener().isLocked()) {
            super.enableEditMode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        DiagramElementEditPartOperation.deactivate(this);
        final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(getEditingDomain().getResourceSet());
        auth.removeAuthorityListener(this.getEditPartAuthorityListener());
        if (isActive()) {
            super.deactivate();
        }
    }

    @Override
    protected List getModelChildren() {
        return ShowingViewUtil.getModelChildren(getModel());
    }

    @Override
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        if (hasNotationView()) {
            ViewQuery viewQuery = new ViewQuery((View) getModel());
            Optional<DDiagram> diagram = viewQuery.getDDiagram();
            if (diagram.isPresent()) {
                addListenerFilter("ShowingMode", this, diagram.get(), DiagramPackage.eINSTANCE.getDDiagram_IsInShowingMode()); //$NON-NLS-1$
            }
        }
    }

    @Override
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("ShowingMode"); //$NON-NLS-1$
    }

    @Override
    protected List getModelSourceConnections() {
        return ShowingViewUtil.getSourceConnectionsConnectingVisibleViews((View) getModel());
    }

    @Override
    protected List getModelTargetConnections() {
        return ShowingViewUtil.getTargetConnectionsConnectingVisibleViews((View) getModel());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IAbstractDiagramNodeEditPart#createBorderItemLocator(IFigure,
     *      DDiagramElement)
     */
    @Override
    public IBorderItemLocator createBorderItemLocator(final IFigure figure, final DDiagramElement vpElementBorderItem) {
        return AbstractDiagramNodeEditPartOperation.createBorderItemLocator(this, figure, vpElementBorderItem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart#getLabelIcon()
     */
    @Override
    public Image getLabelIcon() {
        return DiagramElementEditPartOperation.getLabelIcon(this);
    }

    /**
     * Sets the tooltip of this {@link org.eclipse.gef.EditPart} to the specified text.
     * 
     * @param text
     *            the tooltip's text.
     * @since 0.9.0
     */
    @Override
    public void setTooltipText(final String text) {
        AbstractDiagramNodeEditPartOperation.setTooltipText(this, text);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IAbstractDiagramNodeEditPart#getZoomManager()
     */
    @Override
    public ZoomManager getZoomManager() {
        return AbstractDiagramNodeEditPartOperation.getZoomManager(this);
    }

    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            return SiriusSnapToHelperUtil.getSnapHelper(this);
        }
        return super.getAdapter(key);
    }
}

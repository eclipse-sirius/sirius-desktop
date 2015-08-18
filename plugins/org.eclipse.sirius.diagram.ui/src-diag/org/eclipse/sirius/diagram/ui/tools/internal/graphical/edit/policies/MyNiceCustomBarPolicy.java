/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.DropDownMenuFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.PopupBarFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.ChangeFilterActivation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

import com.google.common.collect.Iterables;

/**
 * .
 * 
 * @author mchauvin
 */
public class MyNiceCustomBarPolicy extends DiagramAssistantEditPolicy implements LayerConstants {

    /** The layers icon descriptor. */
    private static final ImageDescriptor DESC_LAYER = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/layers.gif"); //$NON-NLS-1$

    /** The filters icon descriptor. */
    private static final ImageDescriptor DESC_FILTER = DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/filters.gif"); //$NON-NLS-1$

    private static final ImageDescriptor DESC_ACTIVE_LAYER = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.ACTIVE_LAYER_ICON);

    private static final ImageDescriptor DESC_INACTIVE_LAYER = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.INACTIVE_LAYER_ICON);

    private static final ImageDescriptor DESC_ACTIVE_FILTER = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.ACTIVE_LAYER_ICON);

    private static final ImageDescriptor DESC_INACTIVE_FILTER = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.INACTIVE_LAYER_ICON);

    private IFigure layer;

    private final List<IFigure> figures;

    private PopupBarFigure toolBar;

    /**
     * .
     */
    public MyNiceCustomBarPolicy() {
        figures = new ArrayList<IFigure>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#hideDiagramAssistant()
     */
    @Override
    protected void hideDiagramAssistant() {
        for (final IFigure figure : figures) {
            layer.remove(figure);
        }
        figures.clear();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#isDiagramAssistant(java.lang.Object)
     */
    @Override
    protected boolean isDiagramAssistant(final Object object) {
        return figures.contains(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#getDisappearanceDelay()
     */
    @Override
    protected int getDisappearanceDelay() {
        return 1000;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#isDiagramAssistantShowing()
     */
    @Override
    protected boolean isDiagramAssistantShowing() {
        return !figures.isEmpty();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#showDiagramAssistant(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    protected void showDiagramAssistant(final Point referencePoint) {
        if (getMouseLocation() == null) {
            return;
        }
        translateToAbsoluteMouseLocation();

        layer = getLayer(LayerConstants.HANDLE_LAYER);

        final IFigure composite = new FreeformLayer();
        composite.setSize(80, 100);
        composite.setLocation(getMouseLocation().getCopy().getTranslated(15, 15));
        toolBar = new PopupBarFigure(composite);

        final DropDownMenuFigure dropMenu = createDropDownMenuFigure(composite, DESC_LAYER);
        fillWithLayers(dropMenu);

        final DropDownMenuFigure dropMenu2 = createDropDownMenuFigure(composite, DESC_FILTER);
        fillWithFilters(dropMenu2);

        figures.add(composite);
        layer.add(composite);
    }

    private void translateToAbsoluteMouseLocation() {
        getHostFigure().translateToAbsolute(getMouseLocation());
        IFigure parentFigure = getHostFigure().getParent();
        while (parentFigure != null) {
            if (parentFigure instanceof Viewport) {
                final Viewport viewport = (Viewport) parentFigure;
                getMouseLocation().translate(viewport.getHorizontalRangeModel().getValue(), viewport.getVerticalRangeModel().getValue());
                parentFigure = parentFigure.getParent();
            } else {
                parentFigure = parentFigure.getParent();
            }
        }
    }

    private DropDownMenuFigure createDropDownMenuFigure(final IFigure parent, final ImageDescriptor desc) {
        final DropDownMenuFigure dropMenu = new DropDownMenuFigure(new ImageFigure(SiriusEditPlugin.getPlugin().getImage(desc)), parent);

        dropMenu.addMouseMotionListener(new MouseMotionListener.Stub() {
            @Override
            public void mouseExited(final MouseEvent me) {
                setMouseLocation(null);
                setAvoidHidingDiagramAssistant(false);
            }

            @Override
            public void mouseEntered(final MouseEvent me) {
                setMouseLocation(me.getLocation());
                setAvoidHidingDiagramAssistant(true);
            }
        });
        toolBar.addToMenu(dropMenu);
        return dropMenu;
    }

    private void fillWithLayers(final DropDownMenuFigure dropMenu) {

        final DDiagram dDiagram = getCurrentDiagram();
        final DiagramDescription desc = dDiagram.getDescription();

        final Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) dDiagram).getTarget());
        final Iterable<AdditionalLayer> additionalLayers = Iterables.filter(new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), desc), AdditionalLayer.class);
        for (final AdditionalLayer additionalLayer : additionalLayers) {
            if (additionalLayer.isOptional()) {
                final boolean activated = EqualityHelper.contains(dDiagram.getActivatedLayers(), additionalLayer);
                final ImageDescriptor imgDesc = activated ? DESC_ACTIVE_LAYER : DESC_INACTIVE_LAYER;

                dropMenu.addToMenu(SiriusEditPlugin.getPlugin().getImage(imgDesc), new IdentifiedElementQuery(additionalLayer).getLabel(), new Runnable() {
                    public void run() {
                        hideDiagramAssistant();

                        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
                        Command changeActivatedLayersCmd = new ChangeLayerActivationCommand(domain, dDiagram, additionalLayer, new NullProgressMonitor());
                        domain.getCommandStack().execute(changeActivatedLayersCmd);
                    }
                });
            }
        }
        dropMenu.updateFigure();
    }

    private void fillWithFilters(final DropDownMenuFigure dropMenu) {
        final DDiagram diagram = getCurrentDiagram();
        final DiagramDescription desc = diagram.getDescription();

        final Iterable<FilterDescription> filters = desc.getFilters();
        for (final FilterDescription filter : filters) {
            final boolean activated = EqualityHelper.contains(diagram.getActivatedFilters(), filter);
            final ImageDescriptor imgDesc = activated ? DESC_ACTIVE_FILTER : DESC_INACTIVE_FILTER;

            dropMenu.addToMenu(SiriusEditPlugin.getPlugin().getImage(imgDesc), filter.getName(), new Runnable() {

                public void run() {
                    hideDiagramAssistant();
                    final ChangeFilterActivation change = new ChangeFilterActivation(getPart(), diagram, filter, !activated);
                    change.run();
                }

            });
        }
        dropMenu.updateFigure();
    }

    private IDiagramWorkbenchPart getPart() {
        final IAdaptable editor = (IAdaptable) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        final IDiagramWorkbenchPart part = (IDiagramWorkbenchPart) editor.getAdapter(IDiagramWorkbenchPart.class);
        return part;
    }

    /**
     * Returns the Sirius DDiagram of which this policy's host is part.
     */
    private DDiagram getCurrentDiagram() {
        final Object model = getHost().getModel();
        DDiagram result = null;
        if (model instanceof View) {
            final EObject element = ((View) model).getElement();
            if (element instanceof DDiagram) {
                result = (DDiagram) element;
            } else if (element instanceof DDiagramElement) {
                result = ((DDiagramElement) element).getParentDiagram();
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#activate()
     */
    @Override
    public void activate() {
        ((IGraphicalEditPart) getHost()).getFigure().addMouseMotionListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#deactivate()
     */
    @Override
    public void deactivate() {
        ((IGraphicalEditPart) getHost()).getFigure().removeMouseMotionListener(this);
        hideDiagramAssistant();
    }

}

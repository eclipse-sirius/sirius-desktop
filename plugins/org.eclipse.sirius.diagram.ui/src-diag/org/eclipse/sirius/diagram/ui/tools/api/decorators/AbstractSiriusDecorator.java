/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.decorators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Abstract Sirius decorator.
 * 
 * @author mchauvin
 */
public abstract class AbstractSiriusDecorator extends AbstractDecorator {
    /**
     * The margin to use during decoration.
     */
    protected static final int MARGIN = -1;

    /** the decorations being displayed */
    private List<IDecoration> decorations = Collections.<IDecoration> emptyList();

    /**
     * Create a decorator.
     * 
     * @param decoratorTarget
     *            target to decorate.
     */
    public AbstractSiriusDecorator(final IDecoratorTarget decoratorTarget) {
        super(decoratorTarget);
    }

    @Override
    public void activate() {
    }

    @Override
    public void refresh() {
        removeDecorations();
        final View view = (View) getDecoratorTarget().getAdapter(View.class);
        if (view != null && (shouldConsiderDetachedViews() || view.eResource() != null)) {
            final EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
            if (shouldBeDecorated(editPart)) {
                Image decorationImage = getDecorationImage(editPart);
                if (null != decorationImage) {
                    boolean isVolatile = !shouldBeVisibleAtPrint();
                    if (editPart instanceof AbstractConnectionEditPart) {
                        addDecoration(getDecoratorTarget().addConnectionDecoration(decorationImage, 50, isVolatile));
                    } else {
                        addDecoration(getDecoratorTarget().addShapeDecoration(decorationImage, getDirection(editPart), getMargin(editPart), isVolatile));
                    }
                }
            }
        }
    }

    private int getMargin(EditPart editPart) {
        // Get margin
        int margin = MARGIN;
        if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
            margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
        }
        return margin;
    }

    private void refreshTooltip(IDecoration decoration) {
        EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
        if (editPart instanceof IDiagramElementEditPart) {
            DDiagramElement dDiagramElement = ((IDiagramElementEditPart) editPart).resolveDiagramElement();
            if (dDiagramElement != null) {
                String tooltip = getToolTipText(dDiagramElement);
                if (tooltip != null) {
                    if (decoration instanceof Figure) {
                        ((Figure) decoration).setToolTip(new Label(tooltip));
                    }
                }
            }
        }
    }

    private String getToolTipText(Object element) {
        String tooltip = null;
        IToolTipProvider tooltipProvider = Platform.getAdapterManager().getAdapter(element, IToolTipProvider.class);
        if (tooltipProvider != null) {
            tooltip = tooltipProvider.getToolTipText(element);
        }
        return tooltip;
    }

    /**
     * Tells if the decoration added by this decorator should be visible at image export or print. By default true is
     * returned to have decoration visible at image export and print. Override this method to change this behavior.
     * 
     * @return true to have decorations visible at image export and at print
     */
    protected boolean shouldBeVisibleAtPrint() {
        return true;
    }

    /**
     * Indicates whether this decorator should consider detached {@link View}s (i.e. {@link View}s which eResource() is
     * null).
     * 
     * @return true if this decorator should consider detached {@link View}s, false otherwise.
     */
    protected boolean shouldConsiderDetachedViews() {
        return false;
    }

    /**
     * Specific refresh for an edit. Override if you need to do specific things.
     * 
     * @param editPart
     *            edit part
     */
    protected void refresh(final EditPart editPart) {
        /* do nothing */
    }

    /**
     * Get the position of the decorator according to edit part.
     * 
     * @param editPart
     *            the edit part
     * @return a Direction
     */
    protected abstract Direction getDirection(EditPart editPart);

    /**
     * Check if the edit part respect conditions to be decorate.
     * 
     * @param editPart
     *            the editPart to check
     * @return true if the editPart respect conditions to be decorate, false otherwise
     */
    protected boolean shouldBeDecorated(final EditPart editPart) {
        boolean shouldBeDecorated = true;
        if (editPart == null || editPart.getParent() == null || editPart.getRoot() == null || editPart.getViewer() == null) {
            shouldBeDecorated = false;
        } else if (editPart instanceof AbstractDiagramNameEditPart && !(editPart instanceof DNodeListElementEditPart)) {
            /* Check that the editPart is not a name dEditPart */
            shouldBeDecorated = false;
        } else if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
            /* Check if the size of the figure is sufficient */
            shouldBeDecorated = figureIsBigEnoughToBeDecorated(editPart);
        }
        return shouldBeDecorated;
    }

    private boolean figureIsBigEnoughToBeDecorated(final EditPart editPart) {
        final IFigure figure = ((org.eclipse.gef.GraphicalEditPart) editPart).getFigure();

        final Dimension size = figure.getSize();

        if (size.width < 10 && size.width > 0 && size.height < 10 && size.height > 0) {
            return false;
        }
        return true;
    }

    /**
     * Get the decoration image.<br>
     * 
     * @param editPart
     *            the edit part to get the decoration image from
     * @return <code>null</code> if no image found.
     */
    protected abstract Image getDecorationImage(EditPart editPart);

    private void removeDecorations() {
        for (final IDecoration decoration : decorations) {
            if (decoration instanceof IFigure && ((IFigure) decoration).getParent() != null) {
                ((IFigure) decoration).getParent().remove((IFigure) decoration);
            }

            final GraphicalEditPart ownerEditPart = (GraphicalEditPart) getDecoratorTarget().getAdapter(GraphicalEditPart.class);
            if (ownerEditPart != null && ownerEditPart.getRoot() != null && ownerEditPart.getViewer() != null) {
                ownerEditPart.getViewer().getVisualPartMap().remove(decoration);
            }
        }
        decorations = new ArrayList<IDecoration>();
    }

    @Override
    public void deactivate() {
        removeDecorations();
    }

    /**
     * Get the IDecorations of this DescribedDecorator.
     * 
     * @return Returns the decorations.
     */
    public List<IDecoration> getDecorations() {
        return decorations;
    }

    /**
     * Add an IDecoration to this DescribedDecorator.
     * 
     * @param decoration
     *            IDecoration to add.
     */
    public void addDecoration(final IDecoration decoration) {
        decorations.add(decoration);
        refreshTooltip(decoration);
    }

    /**
     * Get the underlying semantic element for given edit part.
     * 
     * @param editPart
     *            to decorate
     * @return <code>null</code> if not found.
     */
    protected EObject getUnderlyingSemanticElement(EditPart editPart) {
        EObject result = null;
        // Precondition:
        if (null == editPart) {
            return result;
        }
        if (editPart instanceof IDiagramElementEditPart) {
            result = ((IDiagramElementEditPart) editPart).resolveTargetSemanticElement();
        }
        return result;
    }

    /**
     * Indicates if the given editPart should contain decorations according to its type. For example,
     * {@link DNodeListNameEditPart}s should not be decorated.
     * 
     * @param editPart
     *            the edit part to inspect
     * @return true if the given editPart should contain decorations, false otherwise
     */
    public boolean isDecorableEditPart(IDiagramElementEditPart editPart) {
        boolean result = true;
        if (editPart instanceof DNodeNameEditPart) {
            EditPart parentEditPart = editPart.getParent();
            if (!(parentEditPart instanceof DNodeListEditPart) && !(parentEditPart instanceof AbstractDiagramListEditPart)) {
                result = false;
            }
        } else if (editPart instanceof DNodeListNameEditPart) {
            result = false;
        } else if (editPart instanceof DNodeListElementEditPart) {
            // We only decorate DNodeListElementEditParts if the semantic
            // element is different from parent editpart
            EditPart parentEditPart = editPart.getParent();
            if (parentEditPart.getModel() instanceof View && editPart.getNotationView() != null) {
                View parentView = (View) parentEditPart.getModel();
                result = parentView.getElement() != null && !parentView.getElement().equals(editPart.getNotationView().getElement());
            }
        }
        return result;
    }

}

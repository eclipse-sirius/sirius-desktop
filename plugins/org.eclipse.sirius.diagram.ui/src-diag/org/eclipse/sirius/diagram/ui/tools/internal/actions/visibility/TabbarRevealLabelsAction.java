/*******************************************************************************
 * Copyright (c) 2019, 2022 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.provider.AbstractDDiagramElementLabelItemProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Action to reveal labels in tabbar.
 * 
 * @author fbarbin
 */
public class TabbarRevealLabelsAction extends RevealOutlineLabelsAction implements Disposable {

    private static Predicate<Object> isEnabledPredicate = new Predicate<Object>() {
        @Override
        public boolean apply(Object input) {
            boolean result = false;
            if (input instanceof IGraphicalEditPart) {
                result = TabbarRevealLabelsAction.isEnabled((IGraphicalEditPart) input);
            } else if (input instanceof DDiagramElement) {
                result = TabbarRevealLabelsAction.isEnabled((DDiagramElement) input);
            } else if (input instanceof AbstractDDiagramElementLabelItemProvider) {
                Option<DDiagramElement> optionTarget = ((AbstractDDiagramElementLabelItemProvider) input).getDiagramElementTarget();
                if (optionTarget.some()) {
                    result = TabbarRevealLabelsAction.isEnabled(optionTarget.get());
                }
            }
            return result;
        }
    };

    private HideDDiagramElementLabelAction oppositeAction;

    /**
     * Constructor.
     */
    public TabbarRevealLabelsAction() {
        this(Messages.RevealOutlineLabelsAction_label);
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     */
    public TabbarRevealLabelsAction(final String text) {
        this(text, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.REVEAL_LABEL_IMG));
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     */
    public TabbarRevealLabelsAction(final String text, final ImageDescriptor image) {
        super(text, image);
        setId(text);
    }

    @Override
    public boolean isEnabled() {
        if (this.selection instanceof IStructuredSelection) {
            return isEnabled(((IStructuredSelection) this.selection).toList());
        }
        return super.isEnabled();
    }

    /**
     * Check if at least on element has a label that can be revealed.
     * 
     * @param elementsToCheck
     *            The elements to check.
     * @return true if at least on element has a label that can be revealed, false otherwise
     */
    public static boolean isEnabled(Collection<?> elementsToCheck) {
        return Iterables.any(elementsToCheck, isEnabledPredicate);
    }

    @Override
    public void selectionChanged(final IAction action, final ISelection s) {
        super.selectionChanged(action, s);
        this.setEnabled(true);
        if (s instanceof DiagramOutlinePage.TreeSelectionWrapper) {
            // Action of the outline
            this.setEnabled(TabbarRevealLabelsAction.isEnabled(((DiagramOutlinePage.TreeSelectionWrapper) s).toList()));
        } else if (s instanceof IStructuredSelection) {
            // Action of the tabbar or of the contextual menu
            this.setEnabled(TabbarRevealLabelsAction.isEnabled(((IStructuredSelection) s).toList()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doRun(final Object element) {
        boolean value = super.doRun(element);
        // We activate the Hide Label Action if needed.
        if (oppositeAction != null) {
            oppositeAction.setEnabled(oppositeAction.isEnabled());
        }
        setEnabled(isEnabled());
        return value;
    }

    private static boolean isEnabled(IGraphicalEditPart graphicalEditPart) {
        boolean result = false;
        if (graphicalEditPart.isActive() && graphicalEditPart.resolveSemanticElement() instanceof DDiagramElement) {
            DDiagramElement resolveSemanticElement = (DDiagramElement) graphicalEditPart.resolveSemanticElement();
            if (graphicalEditPart instanceof AbstractDEdgeNameEditPart) {
                result = new DDiagramElementQuery(resolveSemanticElement).isLabelHidden(new EditPartQuery(graphicalEditPart).getVisualID());
            } else {
                result = TabbarRevealLabelsAction.isEnabled(resolveSemanticElement);
            }
        }
        return result;
    }

    private static boolean isEnabled(DDiagramElement diagramElement) {
        DDiagram dDiagram = diagramElement.getParentDiagram();
        DDiagramElementQuery query = new DDiagramElementQuery(diagramElement);
        return (dDiagram != null && isEditable(dDiagram)) && query.hasAnyHiddenLabel();
    }

    private static boolean isEditable(DDiagram diagram) {
        boolean isEditable = false;
        Resource resource = diagram.eResource();
        if (resource != null) {
            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resource.getResourceSet());
            isEditable = permissionAuthority.canEditInstance(diagram);
        }
        return isEditable;
    }

    /**
     * Set the corresponding Hide Label opposite action.
     * 
     * @param action
     *            the Hide Label action.
     */
    public void setOppositeHideAction(HideDDiagramElementLabelAction action) {
        this.oppositeAction = action;
    }

    @Override
    public void dispose() {
        this.selection = null;
        this.oppositeAction = null;
        super.dispose();
    }
}

/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.decoration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;

/**
 * Abstract implementation used for sub classes that implements SiriusDecorationDescriptorProvider.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public abstract class AbstractSiriusDecorationDescriptorProvider implements SiriusDecorationDescriptorProvider {

    @Override
    public void activate(IDecoratorTarget decoratorTarget, IDecorator decorator, GraphicalEditPart editPart) {
        // do nothing
    }

    @Override
    public void deactivate(IDecorator decorator, GraphicalEditPart editPart) {
        // do nothing
    }

    @Override
    public boolean provides(IDiagramElementEditPart editPart) {
        return false;
    }

    @Override
    public List<DecorationDescriptor> getDecorationDescriptors(IDiagramElementEditPart diagramEditPart, Session session) {
        final View view = (View) diagramEditPart.getModel();
        if (view != null && (shouldConsiderDetachedViews() || view.eResource() != null)) {
            if (shouldBeDecorated(diagramEditPart)) {
                return createDecorationDescriptors(diagramEditPart, session);
            }
        }

        return new ArrayList<>();
    }

    /**
     * Create a non null list of {@link DecorationDescriptor}.
     * 
     * @param diagramEditPart
     *            the edit part
     * @param session
     *            the current Sirius Session
     * @return the created {@link DecorationDescriptor}s
     */
    protected abstract List<DecorationDescriptor> createDecorationDescriptors(IDiagramElementEditPart diagramEditPart, Session session);

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
     * Check if the edit part respect conditions to be decorated.
     * 
     * @param editPart
     *            the editPart to check
     * @return true if the editPart respect conditions to be decorated, false otherwise
     */
    protected boolean shouldBeDecorated(final EditPart editPart) {
        boolean shouldBeDecorated = true;
        if (isUnsafeEditPart(editPart)) {
            shouldBeDecorated = false;
        } else if (editPart instanceof AbstractDiagramNameEditPart && !(editPart instanceof DNodeListElementEditPart)) {
            /*
             * exclude AbstractDiagramNameEditPart but not DNodeListElementEditPart that can be decorated for
             * compartment
             */
            shouldBeDecorated = false;
        }
        return shouldBeDecorated;
    }

    private boolean isUnsafeEditPart(final EditPart editPart) {
        boolean unsafe = false;
        if (editPart == null || editPart.getParent() == null) {
            unsafe = true;
        } else {
            RootEditPart root = editPart.getRoot();
            unsafe = root == null || root.getViewer() == null;
        }
        return unsafe;
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

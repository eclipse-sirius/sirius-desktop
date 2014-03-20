/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * An edit policy that can retrieve a {@link DDiagram}.
 * 
 * @author ymortier
 */
public abstract class AbstractSiriusEditPolicy extends AbstractEditPolicy {

    /**
     * Return the {@link DDiagram}.
     * 
     * @return the {@link DDiagram}.
     */
    protected DDiagram getSirius() {
        DDiagram result = null;
        if (this.getHost().getModel() instanceof View) {
            EObject element = ((View) this.getHost().getModel()).getElement();
            while (!(element instanceof DDiagram) && element != null) {
                element = element.eContainer();
            }
            result = (DDiagram) element;
        }
        return result;
    }

    /**
     * Return the nearest {@link DDiagramElement}.
     * 
     * @return the nearest {@link DDiagramElement}.
     */
    protected DDiagramElement getFirstSiriusElement() {
        DDiagramElement result = null;
        if (this.getHost().getModel() instanceof View) {
            EObject element = ((View) this.getHost().getModel()).getElement();
            while (!(element instanceof DDiagramElement) && element != null) {
                element = element.eContainer();
            }
            result = (DDiagramElement) element;
        }
        return result;
    }

    /**
     * Return the nearest {@link DSemanticDecorator}.
     * 
     * @return the nearest {@link DSemanticDecorator}.
     */
    protected DSemanticDecorator getFirstDecorateSemanticElement() {
        DSemanticDecorator result = null;
        if (this.getHost().getModel() instanceof View) {
            EObject element = ((View) this.getHost().getModel()).getElement();
            while (!(element instanceof DSemanticDecorator) && element != null) {
                element = element.eContainer();
            }
            result = (DSemanticDecorator) element;
        }
        return result;
    }

    /**
     * Return the editing domain if it can be retrieved from the host (
     * {@link #getHost()}) or <code>null</code> otherwise.
     * 
     * @return the editing domain if it can be retrieved from the host (
     *         {@link #getHost()}) or <code>null</code> otherwise.
     */
    protected TransactionalEditingDomain getEditingDomain() {
        if (this.getHost() instanceof IGraphicalEditPart) {
            return ((IGraphicalEditPart) getHost()).getEditingDomain();
        }
        return null;
    }
}

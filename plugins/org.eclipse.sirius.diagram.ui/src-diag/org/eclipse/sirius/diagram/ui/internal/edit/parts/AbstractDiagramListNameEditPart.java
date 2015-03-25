/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;

/**
 * @was-generated
 */
public abstract class AbstractDiagramListNameEditPart extends AbstractDiagramElementContainerNameEditPart implements ITextAwareEditPart {

    /**
     * @was-generated
     */
    public AbstractDiagramListNameEditPart(final View view) {
        super(view);
    }

    /**
     * We want a special behavior with direct editing.
     * 
     * @not-generated
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();

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
}

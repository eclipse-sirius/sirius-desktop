/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.validators;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;

/**
 * A validator for a {@link ChangeBoundsRequest} which ask resize of a
 * {@link DNode}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResizeValidator {

    private ChangeBoundsRequest changeBoundsRequest;

    /**
     * Default constructor.
     * 
     * @param changeBoundsRequest
     *            the {@link ChangeBoundsRequest} to change the {@link DNode}
     */
    public ResizeValidator(ChangeBoundsRequest changeBoundsRequest) {
        this.changeBoundsRequest = changeBoundsRequest;
    }

    /**
     * Tells if the resize is allowed or not.
     * 
     * @return true if the resize is allowed, fals else
     */
    public boolean validate() {
        boolean valid = true;

        Set<DNode> dNodes = getDNodes();
        RequestQuery requestQuery = new RequestQuery(changeBoundsRequest);
        boolean verticalResize = requestQuery.isResizeFromBottom() || requestQuery.isResizeFromTop();
        boolean horizontalResize = requestQuery.isResizeFromLeft() || requestQuery.isResizeFromRight();
        for (DNode dNode : dNodes) {
            DNodeQuery dNodeQuery = new DNodeQuery(dNode);
            if (verticalResize && !dNodeQuery.allowsVerticalResize()) {
                valid = false;
            }
            if (horizontalResize && !dNodeQuery.allowsHorizontalResize()) {
                valid = false;
            }
            if (!valid) {
                break;
            }
        }

        return valid;
    }

    private Set<DNode> getDNodes() {
        Set<DNode> dNodes = new LinkedHashSet<DNode>();
        Iterator<?> iterator = changeBoundsRequest.getEditParts().iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next instanceof IGraphicalEditPart) {
                IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) next;
                EObject semanticElement = graphicalEditPart.resolveSemanticElement();
                if (semanticElement instanceof DNode) {
                    DNode dNode = (DNode) semanticElement;
                    dNodes.add(dNode);
                }
            }
        }
        return dNodes;
    }

}

/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.requests;

import java.util.List;

import org.eclipse.gef.Request;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;

/**
 * A {@link Request} to manage straighten of edges.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToRequest extends Request {

    /**
     * The straighten type must by one of:
     * <UL>
     * <LI>
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction#TO_TOP}
     * </LI>
     * <LI>
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction#TO_BOTTOM}
     * </LI>
     * <LI>
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction#TO_LEFT}
     * </LI>
     * <LI>
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction#TO_RIGHT}
     * </LI>
     * </UL>
     */
    private int straightenType;

    /**
     * The request is only send to the first selected edit parts. The returned
     * command corresponds to the move of all selected edge edit parts.
     */
    private List<AbstractDiagramEdgeEditPart> selectedEdgeEditParts;

    /**
     * Default constructor.
     */
    public StraightenToRequest() {
        super(RequestConstants.REQ_STRAIGHTEN);
    }

    /**
     * Set the {@link #straightenType}.
     * 
     * @param straightenType
     *            The straighten type to set.
     */
    public void setStraightenType(int straightenType) {
        this.straightenType = straightenType;
    }

    /**
     * Get the {@link #straightenType}.
     * 
     * @return the {@link #straightenType}
     */
    public int getStraightenType() {
        return straightenType;
    }

    /**
     * Set the {@link #selectedEdgeEditParts}.
     * 
     * @param selectedEdgeEditParts
     *            The list of selected edit parts.
     */
    public void setSelectedEdgeEditParts(List<AbstractDiagramEdgeEditPart> selectedEdgeEditParts) {
        this.selectedEdgeEditParts = selectedEdgeEditParts;
    }

    /**
     * Get the list of selected edge edit parts.
     * 
     * @return the selectedEdgeEditParts
     */
    public List<AbstractDiagramEdgeEditPart> getSelectedEdgeEditParts() {
        return selectedEdgeEditParts;
    }
}

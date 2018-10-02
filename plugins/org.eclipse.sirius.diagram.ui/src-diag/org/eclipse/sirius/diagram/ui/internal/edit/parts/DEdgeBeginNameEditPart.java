/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * @was-generated
 */
public class DEdgeBeginNameEditPart extends AbstractDEdgeNameEditPart {

    /**
     * @was-generated
     */
    public static final int VISUAL_ID = 6002;

    /**
     * @not-generated
     */
    static {
        registerSnapBackPosition(SiriusVisualIDRegistry.getType(org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart.VISUAL_ID), new Point(0, 10));
    }

    /**
     * @was-generated
     */
    public DEdgeBeginNameEditPart(View view) {
        super(view);
    }

    /**
     * @was-generated
     */
    public int getKeyPoint() {
        return ConnectionLocator.TARGET;
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // remove edit policy in begin label
        removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
    }

    protected boolean isDirectEditEnabled() {
        return false;
    }

}

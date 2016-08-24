/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.requests;

import org.eclipse.gef.Request;

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
}

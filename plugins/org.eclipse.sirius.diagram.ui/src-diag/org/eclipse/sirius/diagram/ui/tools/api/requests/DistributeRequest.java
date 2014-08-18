/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.requests;

import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * A {@link ChangeBoundsRequest} to manage distribution of shapes.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DistributeRequest extends ChangeBoundsRequest {

    /**
     * The distribution type must by one of:
     * <UL>
     * <LI>DistributeAction.HORIZONTALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_HORIZONTALLY</LI>
     * <LI>DistributeAction.VERTICALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_VERTICALLY</LI>
     * </UL>
     */
    private int distributeType;

    /**
     * Default constructor.
     */
    public DistributeRequest() {
        super(RequestConstants.REQ_DISTRIBUTE);
    }

    public void setDistributeType(int distributeType) {
        this.distributeType = distributeType;
    }

    public int getDistributeType() {
        return distributeType;
    }
}

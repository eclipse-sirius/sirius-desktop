/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;

/**
 * Specialization of a down to top diagram layout leveraging specific layout
 * settings associated with a given diagram instance.
 * 
 * @author cbrun
 */
@SuppressWarnings("restriction")
public class CompositeTopDownLayoutProvider extends AbstractCompositeLayoutProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle translateToGraph(final Rectangle r) {
        final Rectangle rDP = new Rectangle(r);
        getMapMode().LPtoDP(rDP);
        return rDP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle translateFromGraph(final Rectangle rect) {
        final Rectangle rLP = new Rectangle(rect);
        getMapMode().DPtoLP(rLP);
        return rLP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean layoutTopDown(final ConnectionEditPart poly) {
        return false;
    }
}

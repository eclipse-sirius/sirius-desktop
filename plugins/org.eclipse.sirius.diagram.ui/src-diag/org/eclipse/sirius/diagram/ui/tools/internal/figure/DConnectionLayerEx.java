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
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.DForestRouter;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.SiriusRectilinearRouter;

/**
 * The connection layer for Sirius. This layer uses the
 * {@link org.eclipse.sirius.diagram.ui.tools.internal.routers.DTreeRouter} for
 * tree connections and the {@link SiriusRectilinearRouter} for rectilinear
 * connections.
 * 
 * @author ymortier
 */
public class DConnectionLayerEx extends ConnectionLayerEx {

    /** The tree router to use. */
    private DForestRouter treeRouter;

    private ConnectionRouter rectilinearRouter;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx#getTreeRouter()
     */
    @Override
    public ConnectionRouter getTreeRouter() {
        if (treeRouter == null) {
            treeRouter = new DForestRouter();
        }
        return treeRouter;
    }

    // This code has been disabled as the layouting results were not nice enough
    // for the customer
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public ConnectionRouter getObliqueRouter() {
    // return new BorderItemObliqueRouterSpec();
    // }

    @Override
    public ConnectionRouter getRectilinearRouter() {
        if (rectilinearRouter == null)
            rectilinearRouter = new SiriusRectilinearRouter();

        return rectilinearRouter;
    }

}

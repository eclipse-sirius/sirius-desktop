/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.view;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DEdge;

/**
 * Common behavior for edge layout data.
 * 
 * @author dlecan
 */
public abstract class AbstractEdgeLayoutData extends AbstractLayoutData {

    /**
     * The target of this EdgeLayoutData.
     */
    private final DEdge target;

    /**
     * Constructor.
     * 
     * @param parent
     *            the parent layout data.
     * @param target
     *            The node to deal with
     * @param gmfEdge
     *            the corresponding GMF edge
     */
    public AbstractEdgeLayoutData(final LayoutData parent, final DEdge target, final Edge gmfEdge) {
        setParent(parent);
        this.target = target;
        if (gmfEdge != null) {
            init(gmfEdge);
        }
    }

    /**
     * Return the target.
     * 
     * @return the target
     */
    public DEdge getTarget() {
        return target;
    }

    /**
     * Initialize this object.
     * 
     * @param gmfEdge
     *            the corresponding GMF edge
     */
    protected abstract void init(Edge gmfEdge);

}

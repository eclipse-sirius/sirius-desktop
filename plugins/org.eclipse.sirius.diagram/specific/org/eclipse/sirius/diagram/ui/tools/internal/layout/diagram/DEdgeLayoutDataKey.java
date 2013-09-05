/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.EdgeLayoutDataKey;

/**
 * Kind of key use to store the layout data corresponding to an {@link DEdge}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DEdgeLayoutDataKey implements EdgeLayoutDataKey {
    /**
     * The target of this EdgeLayoutData
     */
    private DEdge target;

    /**
     * Default constructor.
     * 
     * @param key
     *            The key
     */
    public DEdgeLayoutDataKey(final DEdge key) {
        this.target = key;
    }

    protected EObject getTarget() {
        return target;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey#getId()
     */
    public String getId() {
        return EcoreUtil.getURI(getTarget()).fragment();
    }
}

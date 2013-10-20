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
import org.eclipse.sirius.diagram.ui.tools.internal.layout.NodeLayoutDataKey;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagram;

/**
 * Kind of key use to store the layout data corresponding to an
 * {@link AbstractDNode} or a {@link DDiagram}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DNodeLayoutDataKey implements NodeLayoutDataKey {
    /**
     * The target of this LayoutData (can only be a DDiagram or an
     * AbstractDNode).
     */
    private EObject target;

    /**
     * Default constructor.
     * 
     * @param target
     *            The target of the
     */
    public DNodeLayoutDataKey(final EObject target) {
        if (!(target instanceof AbstractDNode || target instanceof DDiagram)) {
            throw new IllegalArgumentException("The key uses to store this layout data can only be an AbstractDNode or a DDiagram.");
        }
        this.target = target;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof DNodeLayoutDataKey) {
            return this.getTarget().equals(((DNodeLayoutDataKey) obj).getTarget());
        } else {
            return super.equals(obj);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected EObject getTarget() {
        return target;
    }

    protected void setTarget(final EObject target) {
        this.target = target;
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

/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.model.DDiagramElementSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.impl.DEdgeImpl;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of DEdgeImpl.java.
 *
 * @author cbrun, mchauvin, ymortier
 */
public class DEdgeSpec extends DEdgeImpl {

    @Override
    public DiagramElementMapping getMapping() {
        return new IEdgeMappingQuery(getActualMapping()).getEdgeMapping().get();
    }

    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementSpecOperations.getParentDiagram(this);
    }

    @Override
    public String toString() {
        if (getTargetNode() != null && getSourceNode() != null) {
            return "Edge source:" + getSourceNode() + " | target:" + getTargetNode() + " on semantic" + getTarget(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
            return super.toString();
        }
    }

    /**
     * Overridden because of https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325. {@inheritDoc}
     *
     * @see org.eclipse.sirius.viewpoint.impl.DEdgeImpl#getPath()
     */
    @SuppressWarnings("serial")
    @Override
    public EList<EdgeTarget> getPath() {
        if (path == null) {
            path = new EObjectResolvingEList<EdgeTarget>(EdgeTarget.class, this, DiagramPackage.DEDGE__PATH) {
                /**
                 * {@inheritDoc}
                 */
                @Override
                protected boolean isUnique() {
                    return false;
                }

            };
        }
        return path;
    }
}

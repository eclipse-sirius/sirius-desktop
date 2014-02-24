/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;

/**
 * Implementation of EdgeCreationDescriptionImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier.
 */
public class EdgeCreationDescriptionSpec extends EdgeCreationDescriptionImpl {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.EdgeCreationDescriptionImpl#getBestMapping(org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.emf.common.util.EList)
     */
    @Override
    public EdgeMapping getBestMapping(final EdgeTarget source, final EdgeTarget target, final EList<EObject> createdElements) {
        EdgeMapping edgeMapping = null;
        if (!getEdgeMappings().isEmpty()) {
            edgeMapping = getEdgeMappings().get(0);
            for (EObject createdElt : createdElements) {
                EdgeMapping bestEdgeMapping = new BestMappingGetter(source, target, createdElt).getBestEdgeMapping(getEdgeMappings());
                if (bestEdgeMapping != null) {
                    edgeMapping = bestEdgeMapping;
                    break;
                }
            }
        }
        return edgeMapping;
    }
}

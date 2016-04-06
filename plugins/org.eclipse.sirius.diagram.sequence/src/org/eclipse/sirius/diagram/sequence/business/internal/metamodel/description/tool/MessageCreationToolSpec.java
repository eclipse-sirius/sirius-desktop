/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;

/**
 * Implementation of <code>MessageCreationTool</code>. Uses the same code as the
 * generic <code>EdgeCreationDescription</code>.
 * 
 * @author pcdavid
 */
public class MessageCreationToolSpec extends MessageCreationToolImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public EdgeMapping getBestMapping(final EdgeTarget source, final EdgeTarget target, final EList<EObject> createdElements) {
        EdgeMapping edgeMapping = null;
        if (!getEdgeMappings().isEmpty()) {
            edgeMapping = getEdgeMappings().get(0);
            if (!createdElements.isEmpty()) {
                edgeMapping = new BestMappingGetter(source, target, createdElements.get(0)).getBestEdgeMapping(getEdgeMappings());
            }
        }
        return edgeMapping;
    }
}

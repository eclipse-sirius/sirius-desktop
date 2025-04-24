/*******************************************************************************
 * Copyright (c) 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.model.business.internal.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.ext.base.Option;

/**
 * An helper to query Sequence MessageMapping.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class SequenceMessageMappingQuery {

    private final MessageMapping messageMapping;

    /**
     * Constructor.
     * 
     * @param iEdgeMapping
     *            the edge mapping. Must be a MessageMapping.
     */
    public SequenceMessageMappingQuery(IEdgeMapping iEdgeMapping) {
        Option<EdgeMapping> optMapping = new IEdgeMappingQuery(iEdgeMapping).getOriginalEdgeMapping();
        if (optMapping.some() && optMapping.get() instanceof MessageMapping msgMapping) {
            this.messageMapping = msgMapping;
        } else {
            throw new IllegalArgumentException("Provided IEdgeMapping does not resolve to a MessageMapping."); //$NON-NLS-1$
        }
    }

    /**
     * Check if the mapping allows messages to be oblique.
     * 
     * @return {@code true} if the mapping allows messages to be oblique; {@code false} otherwise.
     */
    public boolean isOblique() {
        boolean isOblique = false;
        if (messageMapping instanceof BasicMessageMapping basicMsgMapping) {
            isOblique = basicMsgMapping.isOblique();
        }
        return isOblique;
    }
}

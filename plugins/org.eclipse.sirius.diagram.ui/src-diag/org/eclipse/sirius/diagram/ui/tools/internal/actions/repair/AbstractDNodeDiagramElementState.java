/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;

/**
 * Diagram element state for {@link AbstractDNode}.
 * 
 * @author dlecan
 */
public class AbstractDNodeDiagramElementState extends AbstractAbstractDNodeDiagramElementState<AbstractDNode> {

    /**
     * Constructor.
     * 
     * @param id
     *            Identifier
     * @param crossReferencer
     *            the cross-referencer
     */
    public AbstractDNodeDiagramElementState(Identifier id, DiagramCrossReferencer crossReferencer) {
        super(id, crossReferencer);
    }

}

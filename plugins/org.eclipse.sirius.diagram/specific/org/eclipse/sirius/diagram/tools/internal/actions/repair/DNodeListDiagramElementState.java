/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions.repair;

import org.eclipse.sirius.diagram.tools.api.migration.DiagramCrossReferencer;
import org.eclipse.sirius.viewpoint.DNodeListElement;

/**
 * Diagram element state for {@link DNodeListElement}.
 * 
 * @author dlecan
 */
public class DNodeListDiagramElementState extends AbstractAbstractDNodeDiagramElementState<DNodeListElement> {

    /**
     * Constructor.
     * 
     * @param id
     *            Identifier
     * @param crossReferencer
     *            the cross-referencer
     */
    public DNodeListDiagramElementState(Identifier id, DiagramCrossReferencer crossReferencer) {
        super(id, crossReferencer);
    }

}

/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;

public class SequenceRefreshExtensionProvider implements IRefreshExtensionProvider {

    private IRefreshExtension sequenceRefresh;

    /**
     * {@inheritDoc}
     */
    public IRefreshExtension getRefreshExtension(DDiagram viewPoint) {
        if (sequenceRefresh == null) {
            sequenceRefresh = new SequenceRefreshExtension();
        }
        return sequenceRefresh;
    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(DDiagram viewPoint) {
        return viewPoint instanceof SequenceDDiagram;
    }

}

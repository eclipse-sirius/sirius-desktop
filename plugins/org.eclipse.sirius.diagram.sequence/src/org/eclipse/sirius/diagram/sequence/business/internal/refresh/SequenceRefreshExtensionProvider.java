/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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

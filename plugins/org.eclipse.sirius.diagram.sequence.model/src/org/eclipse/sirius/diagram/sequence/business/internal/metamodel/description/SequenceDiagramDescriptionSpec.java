/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description;

import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceFactory;
import org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl;

/**
 * Specialized version of <code>SequenceDiagramDescriptionImpl</code>.
 * 
 * @author pcdavid
 */
public class SequenceDiagramDescriptionSpec extends SequenceDiagramDescriptionImpl {
    /**
     * {@inheritDoc}
     * 
     * SequenceDiagramDescription produce <code>SequenceDDiagram</code>s at runtime.
     */
    @Override
    public DSemanticDiagram createDiagram() {
        return SequenceFactory.eINSTANCE.createSequenceDDiagram();
    }

}

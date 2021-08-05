/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.description.spec;

import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl;

/**
 * Implementation of DiagramDescriptionImpl.java.
 * 
 * @author cbrun
 */
public class DiagramDescriptionSpec extends DiagramDescriptionImpl {
    /**
     * {@inheritDoc}
     */
    @Override
    public DSemanticDiagram createDiagram() {
        // Create a plain DSemanticDiagram by default.
        return DiagramFactory.eINSTANCE.createDSemanticDiagram();
    }

}

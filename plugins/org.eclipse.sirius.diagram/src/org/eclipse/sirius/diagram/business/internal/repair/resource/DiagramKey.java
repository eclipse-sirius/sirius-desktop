/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.repair.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagram;

/**
 * Represents a key for diagram.
 * 
 * @author dlecan
 */
public class DiagramKey {
    // Cannot be null
    URI diagramURI;

    String diagramToString;

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + diagramURI.hashCode();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof DiagramKey) {
            final DiagramKey other = (DiagramKey) obj;

            // Diagram URI cannot be null
            result = diagramURI.equals(other.diagramURI);
        }
        return result;
    }

    public void setDiagramToString(String diagramToString) {
        this.diagramToString = diagramToString;
    }

    public void setDiagramURI(URI diagramURI) {
        this.diagramURI = diagramURI;
    }

    public String getDiagramToString() {
        return diagramToString;
    }

    public URI getDiagramURI() {
        return diagramURI;
    }

    /**
     * create {@link DiagramKey}.
     * 
     * @param diagram
     *            {@link DDiagram}
     * @return {@link DiagramKey}
     */
    public static DiagramKey createDiagramKey(final DDiagram diagram) {
        final DiagramKey diagramKey = new DiagramKey();
        diagramKey.setDiagramURI(EcoreUtil.getURI(diagram));
        diagramKey.setDiagramToString(diagram.getName());
        return diagramKey;
    }
}

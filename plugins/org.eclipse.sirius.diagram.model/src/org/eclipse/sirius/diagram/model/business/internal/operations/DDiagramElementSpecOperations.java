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
package org.eclipse.sirius.diagram.model.business.internal.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;

/**
 * Implementation of DDiagramElement.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public final class DDiagramElementSpecOperations {

    /**
     * Avoid instantiation.
     */
    private DDiagramElementSpecOperations() {
        // empty.
    }

    /**
     * Return the parent {@link DDiagram} instance of the element.
     * 
     * @param elem
     *            the element.
     * @return the parent {@link DDiagram} of the element.
     */
    public static DDiagram getParentDiagram(final DDiagramElement elem) {
        DDiagram result = null;
        EObject cur = elem;
        while (cur != null && result == null) {
            if (cur instanceof DDiagram) {
                result = (DDiagram) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }

}

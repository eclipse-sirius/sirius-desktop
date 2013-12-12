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
package org.eclipse.sirius.diagram.sequence.ui.business.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Class responsible for querying a model starting from an EObject.
 * 
 * @author cbrun
 * 
 */
public class EObjectQuery {

    private EObject start;

    /**
     * Create the query.
     * 
     * @param start
     *            the EObject to start the query from.
     */
    public EObjectQuery(EObject start) {
        this.start = start;
    }

    /**
     * return the first TSequenceDiagram being parent of the current EObject.
     * 
     * @return the first TSequenceDiagram being parent of the current EObject.
     */
    public Option<TSequenceDiagram> getParentSequenceDiagramTemplate() {
        EObject cur = start;
        while (cur != null) {
            if (cur instanceof TSequenceDiagram) {
                return Options.newSome((TSequenceDiagram) cur);
            }
            cur = cur.eContainer();
        }
        return Options.newNone();
    }
}

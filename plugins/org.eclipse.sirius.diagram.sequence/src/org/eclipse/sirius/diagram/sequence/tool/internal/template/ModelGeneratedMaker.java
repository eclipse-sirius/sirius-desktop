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
package org.eclipse.sirius.diagram.sequence.tool.internal.template;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * Class responsible for identifying generated model elements from a an output model.
 * 
 * @author cbrun
 * 
 */
public class ModelGeneratedMaker {

    private static final String GENERATED = "@generated"; //$NON-NLS-1$

    private boolean isGenerated(EObject eObj) {
        // FIXME : change to real check once the mared elements are handled.
        return true;
        // if (eObj instanceof DocumentedElement) {
        // return containsMarker(((DocumentedElement) eObj).getDocumentation());
        // }
        // return false;
    }

    /**
     * Clear from the given list elements which have been automatically generated.
     * 
     * @param list
     *            the list to clear.
     */
    public void clearGenerateds(List<? extends EObject> list) {
        Iterator<? extends EObject> it = list.iterator();
        while (it.hasNext()) {
            EObject cur = it.next();
            if (isGenerated(cur)) {
                it.remove();
            }
        }

    }

    /**
     * Mark an element has having been generated.
     * 
     * @param existing
     *            element to mark.
     */
    public void markGenerated(EObject existing) {
        if (existing instanceof DocumentedElement) {
            ((DocumentedElement) existing).setDocumentation(GENERATED);
        }
    }

}

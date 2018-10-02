/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

/**
 * Services to reproduce a specific test case.
 * 
 * @author lredor
 */
public class ArrangeBorderedNodesService {
    /**
     * Minimize the references of this <code>eClass</code> to have only one reference by name.
     * 
     * @param eClass
     *            The concern eClass
     * @return A list of reference
     */
    public List<EReference> getUniqueReferencesByName(EClass eClass) {
        List<String> namesAlreadyUsed = new ArrayList<String>();
        List<EReference> result = new ArrayList<EReference>();
        for (EReference ref : eClass.getEReferences()) {
            String referenceName = ref.getName();
            if (referenceName != null && !namesAlreadyUsed.contains(referenceName)) {
                namesAlreadyUsed.add(ref.getName());
                result.add(ref);
            }
        }
        return result;
    }

    /**
     * Return all the references of this <code>eClass</code>
     * 
     * @param eClass
     *            The concern eClass
     * @param name
     *            the name of the reference
     * @return A list of reference
     */
    public List<EReference> getReferencesForName(EClass eClass, String name) {
        List<EReference> result = new ArrayList<EReference>();
        for (EReference ref : eClass.getEReferences()) {
            String referenceName = ref.getName();
            if (referenceName != null && referenceName.equals(name)) {
                result.add(ref);
            }
        }
        return result;
    }
}

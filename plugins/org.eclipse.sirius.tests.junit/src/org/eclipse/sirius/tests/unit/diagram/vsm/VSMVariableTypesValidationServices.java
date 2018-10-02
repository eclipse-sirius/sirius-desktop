/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.vsm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Participant;

/**
 * Services used for in the VSM of the {@link VSMVariableTypesValidationTest}
 * test.
 * 
 * @author cedric
 *
 */
public class VSMVariableTypesValidationServices {

    public List<EPackage> returnsAListOfEPackages(EObject cur) {
        return new ArrayList<>();
    }

    public EClassifier returnsAnEClassifier(EObject cur) {
        return null;
    }

    public Participant returnsAParticipant(Execution cur) {
        return cur.getOwner();
    }

    public Diagnostic returnsANonEObjectType(EObject cur) {
        return null;
    }

    public String message(Diagnostic cur) {
        return cur.getMessage();
    }

}

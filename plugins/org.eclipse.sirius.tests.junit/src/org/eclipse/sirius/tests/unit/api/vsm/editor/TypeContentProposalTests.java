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
package org.eclipse.sirius.tests.unit.api.vsm.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.editor.tools.internal.assist.TypeContentProposal;

import junit.framework.TestCase;

/**
 * Test the class {@link TypeContentProposal}.
 */
public class TypeContentProposalTests extends TestCase {

    /**
     * Test {@link TypeContentProposal#getLabel()} method.
     * 
     * Check that label of a proposal using AQL syntax ':' or '::' provides the
     * right proposal.
     */
    public void testGetLabelWithAQLSyntax() {
        EPackage newEPackage = EcoreFactory.eINSTANCE.createEPackage();
        newEPackage.setName("testPackage");
        EClass newEClass = EcoreFactory.eINSTANCE.createEClass();
        newEPackage.getEClassifiers().add(newEClass);
        newEClass.setName("Refuge");

        TypeContentProposal typeContentProposal = new TypeContentProposal(newEClass, "testPackage:");
        assertEquals("The label should use the AQL syntax that is ::", "testPackage::Refuge", typeContentProposal.getLabel());

        typeContentProposal = new TypeContentProposal(newEClass, "testPackage::");
        assertEquals("The label should use the AQL syntax that is ::", "testPackage::Refuge", typeContentProposal.getLabel());
    }
}

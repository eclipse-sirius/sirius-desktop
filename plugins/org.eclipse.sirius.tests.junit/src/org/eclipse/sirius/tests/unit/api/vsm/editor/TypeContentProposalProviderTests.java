/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.vsm.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.sirius.editor.tools.api.assist.TypeContentProposalProvider;
import org.eclipse.sirius.editor.tools.internal.assist.TypeAssistant;

import junit.framework.TestCase;

/**
 * Standalone test for {@link TypeContentProposalProvider}. See VP-2811.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TypeContentProposalProviderTests extends TestCase {

    private Registry registryStub;

    private TypeAssistant typeAssistant;

    private TypeContentProposalProvider typeContentProposalProvider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        registryStub = new RegistryStub();
        typeAssistant = new TypeAssistant(registryStub);
        typeContentProposalProvider = new TypeContentProposalProvider(typeAssistant);
    }

    /**
     * Test that {@link TypeAssistant} doesn't return proposals for a empty
     * {@link Registry}.
     */
    public void testProposalsWithEmptyEPackageRegistry() {
        String prefix = "ECla";
        IContentProposal[] proposals = typeContentProposalProvider.getProposals(prefix, prefix.length() - 1);
        assertEquals("If the registry is empty we souldn't have proposals", 0, proposals.length);
    }

    /**
     * Test that {@link TypeAssistant} return 2 proposals for a "ECla" prefix
     * having the EcorePackage in the {@link Registry}.
     */
    public void testProposalsWithoutEPackageNamePrefix() {
        registryStub.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

        String prefix = "ECla";
        IContentProposal[] proposals = typeContentProposalProvider.getProposals(prefix, prefix.length() - 1);

        assertEquals("with ECla as beginning we should have 2 proposals", 2, proposals.length);
        assertEquals("with ECla as beginning we should have EClass proposal", EcorePackage.Literals.ECLASS.getName(), proposals[0].getLabel());
        assertEquals("with ECla as beginning we should have EClassifier proposal", EcorePackage.Literals.ECLASSIFIER.getName(), proposals[1].getLabel());
    }

    /**
     * Test that {@link TypeAssistant} return 2 proposals for a "ecore.ECla"
     * prefix having the EcorePackage in the {@link Registry}.
     */
    public void testProposalsWithEPackageNamePrefix() {
        registryStub.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

        String prefix = "ecore.ECla";
        IContentProposal[] proposals = typeContentProposalProvider.getProposals(prefix, prefix.length() - 1);

        assertEquals("with ECla as beginning we should have 2 proposals", 2, proposals.length);
        assertEquals("with ECla as beginning we should have EClass proposal", EcorePackage.eINSTANCE.getName() + "." + EcorePackage.Literals.ECLASS.getName(), proposals[0].getLabel());
        assertEquals("with ECla as beginning we should have EClassifier proposal", EcorePackage.eINSTANCE.getName() + "." + EcorePackage.Literals.ECLASSIFIER.getName(), proposals[1].getLabel());
    }

    /**
     * Test that {@link TypeAssistant} return 1 proposal for a "OneT" prefix
     * having the EPackage with name to null in the {@link Registry}.
     */
    public void testProposalsWithEPackageNameNull() {
        EPackage ePackageWithNullName = EcoreFactory.eINSTANCE.createEPackage();
        ePackageWithNullName.setNsURI("http://www.example.com/dsl/viewpoint/tests/TypeAssistantTests/0.1.0");
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("OneType");
        ePackageWithNullName.getEClassifiers().add(eClass);
        registryStub.put(ePackageWithNullName.getNsURI(), ePackageWithNullName);

        String prefix = "OneT";
        IContentProposal[] proposals = typeContentProposalProvider.getProposals(prefix, prefix.length() - 1);
        assertEquals("with OneT as beginning we should have 1 proposals", 1, proposals.length);
        assertEquals("with OneT as beginning we should have OneType proposal", eClass.getName(), proposals[0].getLabel());
    }

    @Override
    protected void tearDown() throws Exception {
        registryStub = null;
        typeAssistant = null;
        typeContentProposalProvider = null;
        super.tearDown();
    }
}

/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.suite.diagram;

import org.eclipse.sirius.tests.suite.diagram.sequence.AllSequenceDiagramsStandaloneTests;
import org.eclipse.sirius.tests.unit.api.mappings.ImportSpecClassesUnsetTests;
import org.eclipse.sirius.tests.unit.common.mock.OpaquePixelFinderTest;
import org.eclipse.sirius.tests.unit.diagram.DDiagramDAnnotationTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SemanticEdgeFormatDataKeyTest;
import org.eclipse.sirius.tests.unit.diagram.format.data.SemanticNodeFormatDataKeyTest;
import org.eclipse.sirius.tests.unit.diagram.layers.EdgeMappingImportTests;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.services.EAttributeServicesTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.services.EOperationServicesTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.services.EReferenceServicesTest;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.services.EcoreServiceTest;
import org.eclipse.sirius.tests.unit.diagram.operations.ChangeContextOperationTest;
import org.eclipse.sirius.tests.unit.diagram.operations.CreateInstanceOperationTest;
import org.eclipse.sirius.tests.unit.diagram.query.DDiagramElementQueryTest;
import org.eclipse.sirius.tests.unit.diagram.query.DDiagramInternalQueryTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.DDiagramElementSynchronizerTest;
import org.eclipse.sirius.tests.unit.diagram.refresh.StyleHelperTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.AbstractDNodeCandidateTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.DEdgeCandidateTest;
import org.eclipse.sirius.tests.unit.diagram.synchronization.RefreshIdsHolderTest;
import org.eclipse.sirius.tests.unit.diagram.tools.CustomSiriusDocumentProviderTest;
import org.eclipse.sirius.tests.unit.diagram.views.session.RepresentationDescriptionItemTest;
import org.eclipse.sirius.tests.unit.diagram.views.session.ResourceFolderItemTest;
import org.eclipse.sirius.tests.unit.diagram.views.session.ResourceItemTest;
import org.eclipse.sirius.tests.unit.diagram.views.session.ViewpointItemTest;
import org.eclipse.sirius.tests.unit.diagram.views.session.ViewpointsFolderItemTest;
import org.eclipse.sirius.tests.unit.diagram.vsm.DiagramColorTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllDiagramStandaloneTests {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The testsuite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("Diagram Standalone Tests");

        suite.addTestSuite(EdgeMappingImportTests.class);
        suite.addTestSuite(ImportSpecClassesUnsetTests.class);
        suite.addTestSuite(DEdgeCandidateTest.class);
        suite.addTestSuite(AbstractDNodeCandidateTest.class);
        suite.addTestSuite(RefreshIdsHolderTest.class);
        suite.addTestSuite(StyleHelperTest.class);
        suite.addTestSuite(RepresentationDescriptionItemTest.class);
        suite.addTestSuite(ResourceFolderItemTest.class);
        suite.addTestSuite(ResourceItemTest.class);
        suite.addTestSuite(ViewpointsFolderItemTest.class);
        suite.addTestSuite(ViewpointItemTest.class);
        suite.addTestSuite(SemanticNodeFormatDataKeyTest.class);
        suite.addTestSuite(SemanticEdgeFormatDataKeyTest.class);
        suite.addTestSuite(DDiagramInternalQueryTest.class);
        suite.addTestSuite(DDiagramElementQueryTest.class);
        suite.addTestSuite(CreateInstanceOperationTest.class);
        suite.addTestSuite(ChangeContextOperationTest.class);
        suite.addTestSuite(DDiagramDAnnotationTest.class);
        suite.addTestSuite(DDiagramElementSynchronizerTest.class);
        suite.addTestSuite(DiagramColorTest.class);
        suite.addTestSuite(CustomSiriusDocumentProviderTest.class);
        suite.addTest(AllSequenceDiagramsStandaloneTests.suite());
        // Ecore modeler services
        suite.addTest(new JUnit4TestAdapter(EcoreServiceTest.class));
        suite.addTest(new JUnit4TestAdapter(EAttributeServicesTest.class));
        suite.addTest(new JUnit4TestAdapter(EOperationServicesTest.class));
        suite.addTest(new JUnit4TestAdapter(EReferenceServicesTest.class));
        suite.addTest(new JUnit4TestAdapter(OpaquePixelFinderTest.class));

        return suite;
    }
}

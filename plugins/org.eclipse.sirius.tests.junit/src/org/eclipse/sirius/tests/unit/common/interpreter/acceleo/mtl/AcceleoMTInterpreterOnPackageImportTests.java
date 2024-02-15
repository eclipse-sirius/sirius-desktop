/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl;

import java.util.Collections;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * A test ensuring that the Acceleo MTL interpreter is able to compile expressions using packages listed in the
 * {@link DiagramDescription#getMetamodel()} feature.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class AcceleoMTInterpreterOnPackageImportTests extends SiriusDiagramTestCase {

    private static final String TEST_FOLDER_PATH = "/data/unit/interpreter/packages_import/";

    private static final String REPRESENTATION_FILE_PATH = "representations.aird";

    private static final String VSM_PATH = "My.odesign";

    private static final String SEMANTIC_MODEL_PATH = "vp-4521.ecore";

    private static final String DOCBOOK_NS_URI = "http://docbook.org/ns/docbook";

    private static final String INVALID_PROXY_URI = "http://unknown.org/ns/invalidmm";

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.ViewpointTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, TEST_FOLDER_PATH, VSM_PATH, SEMANTIC_MODEL_PATH, REPRESENTATION_FILE_PATH);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_PATH), Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + VSM_PATH),
                TEMPORARY_PROJECT_NAME + "/" + REPRESENTATION_FILE_PATH);
    }

    /**
     * A test ensuring that the Acceleo MTL interpreter is able to compile expressions referencing the prefix of
     * packages listed in the {@link DiagramDescription#getMetamodel()} feature.
     * 
     * It also checks that not found packages will not leverage platformProblemsListener.getErrors().
     */
    public void testPackageImportWithPrefix() {
        String descName = "DiagramReferencingMetamodels";
        RepresentationDescription desc = getRepresentationDescription(descName, viewpoints.iterator().next());
        assertEquals(descName + " should reference 2 metamodels.", 2, desc.getMetamodel().size());
        assertEquals(EPackage.Registry.INSTANCE.getEPackage(DOCBOOK_NS_URI), desc.getMetamodel().get(0));
        assertTrue(desc.getMetamodel().get(1).eIsProxy());
        assertEquals(INVALID_PROXY_URI, ((BasicEObjectImpl) desc.getMetamodel().get(1)).eProxyURI().toString());

        doCreateRepresentationAndCheckThatNoErrorOccur(descName);
    }

    /**
     * A test ensuring that the Acceleo MTL interpreter is able to compile expressions referencing EClasses (without
     * prefix) of packages listed in the {@link DiagramDescription#getMetamodel()} feature.
     */
    public void testPackageImportWithoutPrefix() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * junit.framework.AssertionFailedError: No compilation error should have been raised by the diagram
             * creation. Error(s) raised during test :
             * org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTInterpreterOnPackageImportTests .
             * Log Plugin : org.eclipse.core.runtime . Error from plugin:org.eclipse.acceleo.parser, message:
             * Compilation error for expression [self.eClassifiers->filter(Book)/] : Unrecognized variable: (Book),
             * exception: null . Error from plugin:org.eclipse.acceleo.parser, message: Compilation error for expression
             * [self.eClassifiers->filter(Book)/] : Unrecognized variable: (Book), exception: null at
             * junit.framework.Assert.fail(Assert.java:57) at junit.framework.Assert.assertTrue(Assert.java:22) at
             * junit.framework.Assert.assertFalse(Assert.java:39) at
             * junit.framework.TestCase.assertFalse(TestCase.java:210) at
             * org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.AcceleoMTInterpreterOnPackageImportTests.
             * doCreateRepresentationAndCheckThatNoErrorOccur(AcceleoMTInterpreterOnPackageImportTests.java:101)
             */
            return;
        }
        String descName = "DiagramReferencingMetamodelsWithoutPrefix";
        RepresentationDescription desc = getRepresentationDescription(descName, viewpoints.iterator().next());
        assertEquals(descName + " should reference 1 metamodel.", 1, desc.getMetamodel().size());
        assertEquals(DOCBOOK_NS_URI, desc.getMetamodel().get(0).getNsURI());

        doCreateRepresentationAndCheckThatNoErrorOccur(descName);
    }

    /**
     * Creates a representation from the {@link RepresentationDescription} with the given name, and checks that no
     * compilation issue is raised during refresh.
     * 
     * @param representationDescriptionName
     *            name of the {@link RepresentationDescription} from which the representation must be created
     */
    protected void doCreateRepresentationAndCheckThatNoErrorOccur(String representationDescriptionName) {
        boolean oldIsErrorCatchActive = platformProblemsListener.isErrorCatchActive();
        platformProblemsListener.setErrorCatchActive(true);
        createRepresentation(representationDescriptionName, session.getSemanticResources().iterator().next().getContents().get(0));
        assertFalse("No compilation error should have been raised by the diagram creation. " + platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
        platformProblemsListener.setErrorCatchActive(oldIsErrorCatchActive);
    }
}

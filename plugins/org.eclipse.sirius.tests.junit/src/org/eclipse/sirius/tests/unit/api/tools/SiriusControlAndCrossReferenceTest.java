/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.Collections;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.SimpleAnalysisSelector;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

public class SiriusControlAndCrossReferenceTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/control/vp-2824/";

    private static final String SEMANTIC_MODEL_FILENAME_1 = "main.ecore";

    private static final String SESSION_MODEL_FILENAME_1 = "main.aird";

    UICallBack originalSiriusUICallBack = SiriusEditPlugin.getPlugin().getUiCallback();

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME_1, SESSION_MODEL_FILENAME_1);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_1), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME_1);

        // Disabling ui callback for the SiriusEditPlugin
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // Reset the original ui callback for the SiriusEditPlugin
        SiriusEditPlugin.getPlugin().setUiCallback(originalSiriusUICallBack);
    }

    public void testControl() throws Exception {
        // Check test data
        EPackage mainPackage = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);
        final EPackage packageToControl = mainPackage.getESubpackages().get(0);
        checkCrossReferences(packageToControl, 2);

        // Control the package to control it and its representation
        final DRepresentation representationTocontrol = DialectManager.INSTANCE.getRepresentations(packageToControl, session).iterator().next();
        URI controlledModelUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/controlled_package.ecore", true);
        final URI controlledAirdUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/controlled_package.aird", true);
        DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representationTocontrol).getRepresentationDescriptor();
        SiriusControlCommand vcc = new SiriusControlCommand(packageToControl, controlledModelUri, Collections.singleton(representationDescriptor), controlledAirdUri, true, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(vcc);

        // Check that control occurs
        assertEquals(controlledModelUri, packageToControl.eResource().getURI());
        final Resource controlledAird = representationTocontrol.eResource();
        assertEquals(controlledAirdUri, controlledAird.getURI());
        assertEquals(2, session.getAllSessionResources().size());

        // Check cross referencer installation on the new aird.
        ECrossReferenceAdapter semanticCrossReferencer = session.getSemanticCrossReferencer();
        Optional<Object> internalXReferencer = ReflectionHelper.getFieldValueWithoutException(semanticCrossReferencer, "adapter");
        if (internalXReferencer.isPresent()) {
            // The lazy cross referencer has an internal cross referencer
            assertTrue("The semantic internal cross referencer should stay installed on the controlled package", packageToControl.eAdapters().contains(internalXReferencer.get()));
        } else {
            // The lazy cross referencer does not have an internal cross
            // referencer
            assertTrue("The semantic cross referencer should stay installed on the controlled package", packageToControl.eAdapters().contains(semanticCrossReferencer));
        }

        for (Resource res : session.getAllSessionResources()) {
            assertTrue("The semantic cross referencer should be installed on all aird", res.eAdapters().contains(semanticCrossReferencer));
        }

        // Check cross references are still the same
        checkCrossReferences(packageToControl, 2);

        // Add a diagram
        ((DAnalysisSession) session).setAnalysisSelector(new SimpleAnalysisSelector(((DAnalysisSessionEObject) session).getAnalyses().get(0).getReferencedAnalysis().get(0)));
        Command crc = new IdentityCommand() {
            @Override
            public void execute() {
                DRepresentation rep3 = DialectManager.INSTANCE.createRepresentation("Test Rep", packageToControl, DialectManager.INSTANCE.getDescription(representationTocontrol), session,
                        new NullProgressMonitor());
                DRepresentationQuery dRepresentationQuery = new DRepresentationQuery(rep3);
                DRepresentationDescriptor descriptor = dRepresentationQuery.getRepresentationDescriptor();
                assertEquals("The new diagram should be referenced from the referenced analysis.", controlledAirdUri, descriptor.eResource().getURI());
                assertTrue("The new diagram should be in its own resource.", rep3.eResource().getContents().contains(rep3));
            };
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(crc);

        // Check cross reference are still the same
        checkCrossReferences(packageToControl, 3);
    }

    private void checkCrossReferences(EObject ctxt, int expected) {
        Integer evaluationResult = Integer.valueOf(-1);
        try {
            evaluationResult = session.getInterpreter().evaluateInteger(ctxt, "[self.eInverse()->filter(viewpoint::DSemanticDecorator)->size()/]");
        } catch (Exception e) {
            fail("Check test data and test expression. " + e.getMessage() + " " + e.getStackTrace());
        }

        assertEquals("Something is wrong with the cross referencer.", expected, evaluationResult.intValue());
    }
}

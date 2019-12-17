/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.session;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.SessionService;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.impl.DFeatureExtensionImpl;

/**
 * Test for {@link SessionService}.
 * 
 * @author mchauvin
 */
public class SessionServiceTest extends SiriusDiagramTestCase implements EcoreModeler {

    boolean backedUpValue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        TestsUtil.emptyEventsFromUIThread();
        backedUpValue = TestsUtil.isCreateRepresentationInSeparateResource();

    }

    public void testPutCustomDataInAird() throws Exception {
        testPutCustomData(false);
    }

    public void testPutCustomDataInSrm() throws Exception {
        testPutCustomData(true);
    }

    public void testGetCustomDataInSrm() throws Exception {
        testGetCustomData(true);
    }

    public void testGetCustomDataInAird() throws Exception {
        testGetCustomData(false);
    }

    public void testPutCustomData(boolean createRepresentationInSeparateResource) throws Exception {
        TestsUtil.setCreateRepresentationInSeparateResource(createRepresentationInSeparateResource);

        final DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        DDiagram diagram2 = (DDiagram) createRepresentation(ENTITIES_DESC_NAME);

        assertFalse(diagram.eResource().getContents().contains(eClass));
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                session.getServices().putCustomData(CustomDataConstants.DFEATUREEXTENSION, diagram2, eClass);
            }
        };
        executeCommand(cmd);
        assertTrue(diagram2.eResource().getContents().contains(eClass));

    }

    public void testGetCustomData(boolean createRepresentationInSeparateResource) throws Exception {
        TestsUtil.setCreateRepresentationInSeparateResource(createRepresentationInSeparateResource);

        DDiagram diagram2 = (DDiagram) createRepresentation(ENTITIES_DESC_NAME);

        final DFeatureExtension eClass = createExtension();
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                session.getServices().putCustomData(CustomDataConstants.DFEATUREEXTENSION, diagram2, eClass);
                session.save(defaultProgress);
            }
        };
        executeCommand(cmd);
        Collection<EObject> collected;

        collected = session.getServices().getCustomData(CustomDataConstants.DFEATUREEXTENSION, null);
        assertEquals(1, collected.size());
        assertTrue(collected.contains(eClass));

        collected = session.getServices().getCustomData(CustomDataConstants.DFEATUREEXTENSION, diagram2);
        assertEquals(1, collected.size());
        assertTrue(collected.contains(eClass));
    }

    public void testClearGMFData() throws Exception {
        final Collection<DRepresentationDescriptor> allRepDescriptor = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                for (final DRepresentationDescriptor descriptor : allRepDescriptor) {
                    DialectManager.INSTANCE.deleteRepresentation(descriptor, session);
                }
            }
        };
        executeCommand(cmd);
        /* diagram has been deleted */
        assertTrue(DialectManager.INSTANCE.getAllRepresentations(session).isEmpty());
        /* resource should not contain GMF dust */
        if (session instanceof DAnalysisSession) {
            final Collection<Resource> resources = ((DAnalysisSession) session).getAllSessionResources();
            for (final Resource resource : resources) {
                for (final EObject content : resource.getContents()) {
                    if (content instanceof Diagram)
                        fail();
                }
            }
        }
    }

    private DFeatureExtension createExtension() throws Exception {
        return new DFeatureExtensionImpl() {};
    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.setCreateRepresentationInSeparateResource(backedUpValue);
    }
}

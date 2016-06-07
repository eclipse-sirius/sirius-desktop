/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.session;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.SessionService;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;

/**
 * Test for {@link SessionService}.
 * 
 * @author nlepine
 */
public class SessionServiceGMFDiagramTest extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);

        TestsUtil.emptyEventsFromUIThread();

    }

    public void testGetCustomData() throws Exception {

        final DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        final Diagram gmfDiag = getGmfDiagram(diagram);

        Collection<EObject> collected = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram);
        assertEquals(1, collected.size());
        assertTrue(collected.contains(gmfDiag));

        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                session.getServices().putCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram, gmfDiag);
            }
        };
        executeCommand(cmd);
        assertEquals(1, collected.size());
        assertTrue(collected.contains(gmfDiag));

    }

    public void testClearGMFData() throws Exception {
        final Collection<DRepresentationDescriptor> allRepDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                for (final DRepresentationDescriptor descriptor : allRepDescriptors) {
                    DialectManager.INSTANCE.deleteRepresentation(descriptor, session);
                }
            }
        };
        executeCommand(cmd);
        /* diagram has been deleted */
        assertTrue(DialectManager.INSTANCE.getAllRepresentations(session).isEmpty());
        /* resource should not contain GMF dust */
        final Resource resource = session.getSessionResource();
        for (final EObject content : resource.getContents()) {
            if (content instanceof Diagram)
                fail();
        }

        for (DRepresentation dRepresentation : allRepresentations) {
            for (AnnotationEntry annotation : dRepresentation.getOwnedAnnotationEntries()) {
                if (annotation.getSource().equals(CustomDataConstants.GMF_DIAGRAMS)) {
                    fail();
                }
            }
        }
    }
}

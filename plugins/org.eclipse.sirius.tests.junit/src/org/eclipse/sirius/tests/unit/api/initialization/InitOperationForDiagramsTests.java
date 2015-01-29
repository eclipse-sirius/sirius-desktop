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
package org.eclipse.sirius.tests.unit.api.initialization;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.common.AbstractEcoreSynchronizerTest;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Styles customizations tests for Entities diagram of ecore modeler.
 * 
 * @author cbrun
 */
public class InitOperationForDiagramsTests extends AbstractEcoreSynchronizerTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        URI modelerResourceURI = URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/testInitOperation.odesign", true);
        Group group = (Group) ModelUtils.load(modelerResourceURI, session.getTransactionalEditingDomain().getResourceSet());

        viewpoints.addAll(group.getOwnedViewpoints());

        final Viewpoint vp = group.getOwnedViewpoints().iterator().next();
        activateViewpoint(vp.getName());
    }

    public void testInitialOperationLaunchingWhenSessionGetsCreated() throws Exception {
        EClass autocreatedClass = (EClass) ((EPackage) semanticModel).getEClassifier("AutoCreated");
        assertNotNull("We should get an auto-created class", autocreatedClass);
        assertEquals("We should not have more than one class", 1, ((EPackage) semanticModel).getEClassifiers().size());
    }

    public void testInitialOperationLaunchingWhenCreatingANewDiagram() throws Exception {

        assertEquals("We should not have more than one class", 1, ((EPackage) semanticModel).getEClassifiers().size());
        final DiagramDescription desc = findDiagramDescription("DiagramWithEffect");

        assertNotNull("Junit test data is invalid (the .odesign file probably", desc);
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.createRepresentation("new diag", ((EPackage) semanticModel), desc, session, new NullProgressMonitor());
            }
        });

        assertEquals("We should have two classes now we created the new diag", 2, ((EPackage) semanticModel).getEClassifiers().size());
    }

}

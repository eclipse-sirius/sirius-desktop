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
package org.eclipse.sirius.tests.unit.diagram.layers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Test that the activation-deactivation of the optional layer does not generate platformProblemsListener.getErrors().
 * 
 * @author jpequery
 * 
 */
public class ActivateLayerOnInvalidDiagramTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layers/My.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layers/My.odesign";

    private static final String TEST_CLASS_DIAGRAM = "rep0";

    private static final String VIEWPOINT_NAME = "bug_root";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        platformProblemsListener.setErrorCatchActive(true);
    }

    /**
     * Test that there is no error when activate-deactivate the layer.
     */
    public void testActivateLayerOnInvalidDiagram() {
        assertTrue("The error Catch should be activate", platformProblemsListener.isErrorCatchActive());
        DDiagram diagram = (DDiagram) getRepresentations(TEST_CLASS_DIAGRAM).toArray()[0];
        refresh(diagram);
        // Open editor
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 2 nodes here .", 2, elements.size());
        Layer optionalLayer = getLayer(diagram, "op");
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);

        // invalidate diagram like asked in issue 553866
        RecordingCommand cmd = new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                EcoreUtil.delete(diagram);
            }
        };

        CompoundCommand cc = new CompoundCommand();
        cc.append(cmd);
        ChangeLayerActivationCommand clac = new ChangeLayerActivationCommand(domain, diagram, optionalLayer, new NullProgressMonitor());
        cc.append(clac);

        domain.getCommandStack().execute(cc);

        assertNull("Invalid tearUp : not in DOREMI-4198 case", TransactionUtil.getEditingDomain(diagram));
        // activateLayer(optionalLayer, diagram, domain);

        Collection<?> result = clac.getResult();
        assertEquals("Result of layer command is not empty", 0, result.size());

        // When the suite is called with the skipUnreliable option, the error catch is not checked.
        assertFalse("There shoud be no error. " + platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }
}

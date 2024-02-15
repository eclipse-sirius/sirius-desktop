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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.PlatformUI;

/**
 * Test that the activation-deactivation of the optional layer does not generate platformProblemsListener.getErrors().
 * 
 * @author jdupont
 * 
 */
public class ActivateDeactivateOptionalLayersTest extends SiriusDiagramTestCase {

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
    public void testActivateDeactivateOptionalLayer() {
        assertTrue("The error Catch should be activate", platformProblemsListener.isErrorCatchActive());
        DDiagram diagram = (DDiagram) getRepresentations(TEST_CLASS_DIAGRAM).toArray()[0];
        refresh(diagram);
        // Open editor
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 2 nodes here .", 2, elements.size());
        Layer optionalLayer = getLayer(diagram, "op");

        activateLayer(optionalLayer, diagram);
        deactivateLayer(optionalLayer, diagram);
        activateLayer(optionalLayer, diagram);
        deactivateLayer(optionalLayer, diagram);
        activateLayer(optionalLayer, diagram);
        deactivateLayer(optionalLayer, diagram);
        activateLayer(optionalLayer, diagram);
        deactivateLayer(optionalLayer, diagram);

        // When the suite is called with the skipUnreliable option, the error catch is not checked.
        assertFalse("There shoud be no error. " + platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Deactivate the layer and check that the edge is no visible after this action.
     * 
     * @param layer
     *            the Layer to deactivate
     * @param diagram
     *            the diagram to apply the layer
     */
    private void deactivateLayer(Layer layer, DDiagram diagram) {
        deactivateLayer(diagram, layer.getName());
        refresh(diagram);
        DEdge edge = (DEdge) diagram.getOwnedDiagramElements().get(2);
        assertEquals("The edge should not be visible", false, edge.isVisible());
    }

    /**
     * Activate the layer and check that the edge is visible after this action.
     * 
     * @param layer
     *            the layer to activate
     * @param diagram
     *            the diagram to applied the layer
     */
    private void activateLayer(final Layer layer, final DDiagram diagram) {
        // The test ActivateDeactivateOptionalLayers, test that there is no
        // Invalid Thread Access. So to test this we should use an other thread
        // to launch the ActivateLayer command.
        if (layer != null && !diagram.getActivatedLayers().contains(layer)) {
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
                    Command changeActivatedLayersCmd = new ChangeLayerActivationCommand(domain, diagram, layer, monitor);
                    domain.getCommandStack().execute(changeActivatedLayersCmd);
                }
            };
            try {
                new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, false, runnable);
            } catch (InvocationTargetException e) {
                fail(e.getMessage());
            } catch (InterruptedException e) {
                fail(e.getMessage());
            }
            refresh(diagram);
            DEdge edge = (DEdge) diagram.getOwnedDiagramElements().get(2);
            assertEquals("The edge should be visible", true, edge.isVisible());
        }
    }

}

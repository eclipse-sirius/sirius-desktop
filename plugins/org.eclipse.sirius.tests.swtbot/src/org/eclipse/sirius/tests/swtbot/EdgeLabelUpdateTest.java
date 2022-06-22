/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class dedicated to updates of the edge labels.
 * 
 * @author smonnier
 */
public class EdgeLabelUpdateTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new 580225"; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME = "580225"; //$NON-NLS-1$

    private static final String MODEL = "580225.ecore"; //$NON-NLS-1$

    private static final String SESSION_FILE = "representations.aird"; //$NON-NLS-1$

    private static final String VSM_FILE = "580225.odesign"; //$NON-NLS-1$

    private static final String DATA_UNIT_DIR = "data/unit/edgeLabelUpdate/"; //$NON-NLS-1$

    private static final String FILE_DIR = "/"; //$NON-NLS-1$

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Creates an edge with no initial label (the tool creates an EReference but does not set the "name" feature). Then
     * the String feature ("name" field) used for the label is updated and the test verify that it is displayed.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeLabelCreatedEmptyAndThenUpdated() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotGefEditPart c1EditPart = editor.getEditPart("C1", DNodeContainerEditPart.class); //$NON-NLS-1$
        SWTBotGefEditPart c2EditPart = editor.getEditPart("C2", DNodeContainerEditPart.class); //$NON-NLS-1$

        // Create a reference between C1 and C2
        editor.activateTool("EReference"); //$NON-NLS-1$
        editor.click(c1EditPart);
        editor.click(c2EditPart);

        // Check the reference was created without displayed label
        List<SWTBotGefConnectionEditPart> connections = editor.getConnectionEditPart(c1EditPart, c2EditPart);
        assertEquals("The reference has not been created as expected", 1, connections.size()); //$NON-NLS-1$

        // Change the 'name' field of the eReference as it was created empty.
        SWTBotGefConnectionEditPart connectionEditPart = connections.get(0);
        EReference eRef = (EReference) ((DEdge)((Edge) connectionEditPart.part().getModel()).getElement()).getTarget();
        String newRefName = "ref to C2"; //$NON-NLS-1$
        localSession.getOpenedSession().getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(localSession.getOpenedSession().getTransactionalEditingDomain()) {
            
            @Override
            protected void doExecute() {
                eRef.setName(newRefName);
            }
        });
        SWTBotUtils.waitAllUiEvents();

        // Check that the label is set and visible
        assertEquals("The reference has not be named as expected", newRefName, eRef.getName()); //$NON-NLS-1$
        Optional<DEdgeNameEditPart> findFirst = connectionEditPart.part().getChildren().stream().filter(DEdgeNameEditPart.class::isInstance).map(DEdgeNameEditPart.class::cast).findFirst();
        assertFalse("The reference should have a center label", findFirst.isEmpty()); //$NON-NLS-1$
        DEdgeNameEditPart dEdgeNameEditPart = findFirst.get();
        assertEquals("The label of the edge should be :" + newRefName, newRefName, dEdgeNameEditPart.getLabelText()); //$NON-NLS-1$
        assertTrue("The label should be visible", dEdgeNameEditPart.getFigure().isVisible()); //$NON-NLS-1$
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagram = null;
        super.tearDown();
    }
}

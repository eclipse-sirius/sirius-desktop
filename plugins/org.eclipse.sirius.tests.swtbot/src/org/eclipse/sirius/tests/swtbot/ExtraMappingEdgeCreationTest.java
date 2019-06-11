/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test that the edge type is available in Extra Source mapping or in Extra
 * Target mapping in VSM and that the edge creation is ok in representation.
 * 
 * @author jdupont
 */
public class ExtraMappingEdgeCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new Diagram";

    private static final String REPRESENTATION_NAME = "Diagram";

    private static final String VSM_FILE = "3216.odesign";

    private static final String MODEL = "3216.ecore";

    private static final String SESSION_FILE = "3216.aird";

    private static final String DATA_UNIT_DIR = "data/unit/edgeCreation/extraMapping/";

    private static final String FILE_DIR = "/";

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // Check that edge type is available on source and target extra mapping
        assertEquals("", DescriptionPackage.eINSTANCE.getDiagramElementMapping(), ToolPackage.eINSTANCE.getEdgeCreationDescription_ExtraSourceMappings().getEReferenceType());
        assertEquals("", DescriptionPackage.eINSTANCE.getDiagramElementMapping(), ToolPackage.eINSTANCE.getEdgeCreationDescription_ExtraTargetMappings().getEReferenceType());

    }

    /**
     * Test that the creation edge with edge source is ok in representation.
     */
    public void testExtraMappingEdgeSourceOnEdgeCreation() {
        // Create a contains relation
        ICondition done = new OperationDoneCondition();
        done = new CheckToolIsActivated(editor, "contain");
        editor.activateTool("contain");
        bot.waitUntil(done);
        // Retrieve edge for source
        DEdgeEditPart edgeContain = getSingleDEdgeFrom("aroot1");
        // Retrieve node for target
        SWTBotGefEditPart nodeToConnect = editor.getEditPart("p1", AbstractDiagramNodeEditPart.class);
        // Check that the edge must be connect between edge and node
        done = new OperationDoneCondition();
        editor.click(((ViewEdgeFigure) edgeContain.getFigure()).getPoints().getMidpoint());
        editor.click(((NodeFigure) ((GraphicalEditPart) nodeToConnect.part()).getFigure()).getBounds().getCenter().getCopy().x,
                ((NodeFigure) ((GraphicalEditPart) nodeToConnect.part()).getFigure()).getBounds().getCenter().getCopy().y);
        // Check that creation of 'contains' relation is ok
        edgeContain = getSingleDEdgeFrom("aroot1");
        assertEquals("The relation 'contains' should be connect on'P1' node", edgeContain, nodeToConnect.targetConnections().get(1).part());
    }

    /**
     * Test that the creation edge with edge source and edge target (define by
     * Extra Mapping) is ok in representation.
     */
    public void testExtraMappingEdgeSourceTargetOnEdgeCreation() {
        // On opening representation there is 2 package node (p1, root2) and
        // there is 2 annotation node (arrot1, aroot2). The reference show
        // relationship container. The node p1 contains arrot2 and the node
        // root2 contains aroot1. The test is to make a reference between the 2
        // existed references. This action will do that the aroot2 is found in
        // arrot1.
        // Retrieve node corresponding to package root2
        SWTBotGefEditPart targetRoot2 = editor.getEditPart("root2", AbstractDiagramNodeEditPart.class);
        // Retrieve node corresponding to package p1
        SWTBotGefEditPart nodeToConnect = editor.getEditPart("p1", AbstractDiagramNodeEditPart.class);
        // Retrieve relation between arrot2 and p1
        DEdgeEditPart edgeContainSource = getSingleDEdgeFrom("aroot2");
        // Retrieve relation between arrot1 and p2
        DEdgeEditPart edgeContainTarget = getSingleDEdgeFrom("aroot1");
        // Check that the 2 relations existed in representation
        assertNotNull(edgeContainSource);
        assertNotNull(edgeContainTarget);
        // Create a contains relation between 2 relations existing
        ICondition done = new CheckToolIsActivated(editor, "contain");
        editor.activateTool("contain");
        bot.waitUntil(done);
        editor.click(((ViewEdgeFigure) edgeContainSource.getFigure()).getPoints().getMidpoint());
        editor.click(((ViewEdgeFigure) edgeContainTarget.getFigure()).getPoints().getMidpoint());
        // The relation between 'p1' and 'aroot2' should be disappear
        assertEquals("It should not be have a relation 'contains' between 'p1' node and 'aroot2' node", 0, nodeToConnect.targetConnections().size());
        editor.save();
        // Check in semantic model that 'aroot2' was containing by 'aroot1'
        assertEquals("The Eannotation should be child of 'arrot1'", "aroot2",
                ((EPackage) ((DNode) ((Node) targetRoot2.part().getModel()).getElement()).getTarget()).getEAnnotations().get(0).getEAnnotations().get(0).getSource());
    }

    private DEdgeEditPart getSingleDEdgeFrom(String sourceName) {
        SWTBotGefEditPart sourcePart = editor.getEditPart(sourceName).parent();
        assertEquals("Bad number of edge", 1, sourcePart.sourceConnections().size());
        SWTBotGefConnectionEditPart edge = sourcePart.sourceConnections().get(0);
        assertTrue(edge.part() instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge.part();
    }

}

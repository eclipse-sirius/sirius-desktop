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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Check VP-3257 Replacing a node style by a smaller or greater on a model
 * modification lead to a style size problem.
 * 
 * @author fbarbin
 */
public class StyleSizeChangeRefreshTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/VP-3257/";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-3257.interactions";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "VP-3257.odesign";

    private static final String VIEWPOINT_NAME = "prod00088637";

    private static final String REPRESENTATION_NAME = "vp3257";

    private static final int EXPECTED_NODE_SIZE = 3;

    private static final int EXPECTED_COND_NODE_SIZE = 10;

    private static final int EXPECTED_DBORDERED_COND_NODE_SIZE = 2;

    private static final int EXPECTED_DBORDERED_NODE_SIZE = 6;

    private DDiagram diagram;

    public void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) createRepresentation(REPRESENTATION_NAME, semanticModel);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    public void testSizeRefresh() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        checkBefore();
        changeParticipantName();
        checkAfter();

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    private void changeParticipantName() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Interaction interaction = (Interaction) semanticModel;
        Participant participant = interaction.getParticipants().get(0);
        Command changeNameCmd = SetCommand.create(domain, participant, InteractionsPackage.Literals.PARTICIPANT__NAME, "b");
        domain.getCommandStack().execute(changeNameCmd);
    }

    private void checkBefore() {
        DDiagramElement currentElement = diagram.getDiagramElements().get(0);

        int expectedSize = EXPECTED_COND_NODE_SIZE * LayoutUtils.SCALE;
        Node gmfNode = getGmfNode(currentElement);
        LayoutConstraint layoutConstraint = gmfNode.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            assertEquals(expectedSize, ((Bounds) layoutConstraint).getHeight());
            assertEquals(expectedSize, ((Bounds) layoutConstraint).getWidth());
        }

        int expectedBNSize = EXPECTED_DBORDERED_COND_NODE_SIZE * LayoutUtils.SCALE;
        DNode dnode = ((DNode) currentElement).getOwnedBorderedNodes().get(0);
        Node borderedNode = getGmfNode(dnode);
        LayoutConstraint bnLayoutConstraint = borderedNode.getLayoutConstraint();
        if (bnLayoutConstraint instanceof Bounds) {
            assertEquals(expectedBNSize, ((Bounds) bnLayoutConstraint).getHeight());
            assertEquals(expectedBNSize, ((Bounds) bnLayoutConstraint).getWidth());
        }
    }

    private void checkAfter() {
        DDiagramElement currentElement = diagram.getDiagramElements().get(0);

        int expectedSize = EXPECTED_NODE_SIZE * LayoutUtils.SCALE;
        Node gmfNode = getGmfNode(currentElement);
        LayoutConstraint layoutConstraint = gmfNode.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            assertEquals(expectedSize, ((Bounds) layoutConstraint).getHeight());
            assertEquals(expectedSize, ((Bounds) layoutConstraint).getWidth());
        }

        int expectedBNSize = EXPECTED_DBORDERED_NODE_SIZE * LayoutUtils.SCALE;
        DNode dnode = ((DNode) currentElement).getOwnedBorderedNodes().get(0);
        Node borderedNode = getGmfNode(dnode);
        LayoutConstraint bnLayoutConstraint = borderedNode.getLayoutConstraint();
        if (bnLayoutConstraint instanceof Bounds) {
            assertEquals(expectedBNSize, ((Bounds) bnLayoutConstraint).getHeight());
            assertEquals(expectedBNSize, ((Bounds) bnLayoutConstraint).getWidth());
        }
    }

    public void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

}

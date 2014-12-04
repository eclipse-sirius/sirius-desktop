/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Ensure that the property view contains all semantic elements associated to a
 * selected element. See https://bugs.eclipse.org/bugs/show_bug.cgi?id=449060
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class AssociatedElementsOnPropertyViewTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String DATA_UNIT_DIR = "data/unit/associatedElements/";

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE = "test.aird";

    private static final String VSM_FILE = "test.odesign";

    private static final String SEMANTIC_FILE = "test.ecore";

    private static final String PROPERTIES = "Properties";

    private static final String ELEMENT_NAME = "EClass1";

    private static final String EDIT_TABLE_DESCRIPTION_NAME = "edit_table";

    private static final String EDIT_TABLE_NAME = "edit_table";

    private static final String CROSS_TABLE_DESCRIPTION_NAME = "cross_table";

    private static final String CROSS_TABLE_NAME = "cross_table";

    private static final String TREE_DESCRIPTION_NAME = "tree";

    private static final String TREE_NAME = "tree";

    private static final String DIAGRAM_DESCRIPTION_NAME = "diagram";

    private static final String DIAGRAM_NAME = "diagram";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM_FILE, SESSION_FILE, SEMANTIC_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Check the property view of an edit table
     */
    public void testEditTable() {
        doTestTableOrTree(EDIT_TABLE_DESCRIPTION_NAME, EDIT_TABLE_NAME, DTable.class);
    }

    /**
     * Check the property view of a cross table
     */
    public void testCrossTable() {
        doTestTableOrTree(CROSS_TABLE_DESCRIPTION_NAME, CROSS_TABLE_NAME, DTable.class);
    }

    /**
     * Check the property view of a tree
     */
    public void testTree() {
        doTestTableOrTree(TREE_DESCRIPTION_NAME, TREE_NAME, DTree.class);
    }

    /**
     * Check the property view of a diagram
     */
    public void testDiagram() {
        SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, DIAGRAM_NAME, DDiagram.class);
        SWTBotGefEditPart editPart = editor.getEditPart(ELEMENT_NAME, AbstractDiagramContainerEditPart.class);
        editPart.select();
        editPart.focus();
        checkPropertyViewContent();
        editor.close();
    }

    /**
     * Check the property view of a table or a tree.
     * 
     * @param representationDescription
     *            representation description name
     * @param representation
     *            representation name
     * @param expectedRepresentationClass
     *            expected representation class
     */
    private void doTestTableOrTree(String representionDescription, String representation, Class<? extends DRepresentation> expectedRepresentationClass) {
        SWTBotEditor editor = openRepresentation(localSession.getOpenedSession(), representionDescription, representation, expectedRepresentationClass);
        SWTBotTree tree = editor.bot().tree();
        tree.select(0);
        checkPropertyViewContent();
        editor.close();
    }

    /**
     * Check the content of the property view
     */
    private void checkPropertyViewContent() {
        // Select the property view
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();

        // Check that "p1" and "p2" package are listed in the property view
        SWTBotTree semanticTree = propertiesBot.bot().tree();
        int nbLines = semanticTree.rowCount();
        assertEquals("Two elements should be in the list of associated semantic element", nbLines, 2);
        String p1 = semanticTree.cell(0, 0);
        assertEquals("The package 'p1' should be in the list of associated semantic element", "p1", p1);
        String p2 = semanticTree.cell(1, 0);
        assertEquals("The package 'p2' should be in the list of associated semantic element", "p2", p2);
    }

}

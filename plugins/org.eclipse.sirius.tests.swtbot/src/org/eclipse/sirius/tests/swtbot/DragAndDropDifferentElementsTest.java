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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests that drag and drop an element toward an other node with a different
 * mapping works properly.
 * 
 * @author Florian Barbin
 */
public class DragAndDropDifferentElementsTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REF1 = "ref1";

    private static final String A = "a";

    private static final String PATH = "/data/unit/dragAndDrop/bug428079/";

    private static final String SEMANTIC_MODEL = "bug428079.ecore";

    private static final String REPRESENTATION_MODEL = "bug428079.aird";

    private static final String MODELER = "bug428079.odesign";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "bug428079";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL, REPRESENTATION_MODEL, MODELER);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATION_MODEL);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
    }

    /**
     * Tests different drag and drop cases.
     */
    public void testDragNDropOfDifferentDDiagramElements() {
        // list element => container sub-node
        dragNDropToEClass2Container(AbstractDiagramNameEditPart.class, A, false);

        // container sub-node => border node of node
        dragNDropToEClass3Node(IAbstractDiagramNodeEditPart.class, A);

        // border node of node => list element
        dragNDropToEClass1List(IAbstractDiagramNodeEditPart.class, A, false);

        // list element => border node of node
        dragNDropToEClass3Node(AbstractDiagramNameEditPart.class, A);

        // border node of node => container sub-node
        dragNDropToEClass2Container(IAbstractDiagramNodeEditPart.class, A, false);

        // container sub-node => list element
        dragNDropToEClass1List(IAbstractDiagramNodeEditPart.class, A, false);

        // container list border-node => container border-node
        dragNDropToEClass2Container(IAbstractDiagramNodeEditPart.class, REF1, true);

        // container border-node=> node border-node
        dragNDropToEClass3Node(IAbstractDiagramNodeEditPart.class, REF1);

        // node border-node => list border-node
        dragNDropToEClass1List(IAbstractDiagramNodeEditPart.class, REF1, true);

        // list border-node => node border-node
        dragNDropToEClass3Node(IAbstractDiagramNodeEditPart.class, REF1);

        // node border-node => container border-node
        dragNDropToEClass2Container(IAbstractDiagramNodeEditPart.class, REF1, true);

        // container border-node => list border-node
        dragNDropToEClass1List(IAbstractDiagramNodeEditPart.class, REF1, true);
    }

    private void dragNDropToEClass1List(Class<? extends EditPart> editPartType, String name, boolean isBorder) {
        SWTBotGefEditPart eClassEditPart = editor.getEditPart("NewEClass1", IAbstractDiagramNodeEditPart.class);
        dragAndDropFromTo(editor.getEditPart(name, editPartType), eClassEditPart);
        EditPart editPart = eClassEditPart.part();
        DNodeList dnodeList = (DNodeList) ((Node) editPart.getModel()).getElement();
        String message = " element should be dropped into NewEClass1 as a ";
        if (isBorder) {
            assertEquals("The " + name + message + "border node", 1, dnodeList.getOwnedBorderedNodes().size());
        } else {
            assertEquals("The " + name + message + "list element", 1, dnodeList.getOwnedElements().size());
        }
    }

    private void dragNDropToEClass3Node(Class<? extends EditPart> editPartType, String name) {
        SWTBotGefEditPart eClassEditPart = editor.getEditPart("NewEClass3", IAbstractDiagramNodeEditPart.class);
        dragAndDropFromTo(editor.getEditPart(name, editPartType), eClassEditPart);
        EditPart editPart = eClassEditPart.part();
        DNode dnode = (DNode) ((Node) editPart.getModel()).getElement();
        assertEquals("The " + name + " element should be dropped into NewEClass3 as a border-node", 1, dnode.getOwnedBorderedNodes().size());
    }

    private void dragNDropToEClass2Container(Class<? extends EditPart> editPartType, String name, boolean isBorder) {
        SWTBotGefEditPart eClassEditPart = editor.getEditPart("NewEClass2", IAbstractDiagramNodeEditPart.class);
        dragAndDropFromTo(editor.getEditPart(name, editPartType), eClassEditPart);

        // Check that the container where the list element has been dropped
        // contains a sub-node.
        EditPart editPart = eClassEditPart.part();
        DNodeContainer container = (DNodeContainer) ((Node) editPart.getModel()).getElement();
        String message = " element should be dropped into NewEClass2 as a ";
        if (isBorder) {
            assertEquals("The " + name + message + "border-node", 1, container.getOwnedBorderedNodes().size());
        } else {
            assertEquals("The " + name + message + "sub-node", 1, container.getElements().size());
        }
    }

    private void dragAndDropFromTo(SWTBotGefEditPart dragged, SWTBotGefEditPart target) {
        dragged.click();

        Rectangle endBounds = ((GraphicalEditPart) target.part()).getFigure().getBounds();
        ((GraphicalEditPart) target.part()).getFigure().translateToAbsolute(endBounds);
        Point endLocation = endBounds.getCenter();

        editor.drag(dragged, endLocation);
        SWTBotUtils.waitAllUiEvents();
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        super.tearDown();
    }
}

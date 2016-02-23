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
package org.eclipse.sirius.tests.swtbot;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.google.common.collect.Lists;

/**
 * Tests
 * 
 * @author nlepine
 */
public class NodeCreationPositionTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Specific {@link RecordingCommand} that allows to initialize the semantic
     * {@link #root}. The method {@link #setRootPackage(EPackage)} must be
     * called before executing this command.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    abstract class RecordingCommandWithModelInitializer extends RecordingCommand {
        EPackage root;

        /**
         * Initializes me with the editing domain in which I am to be executed.
         * 
         * @param domain
         *            my domain
         */
        public RecordingCommandWithModelInitializer(TransactionalEditingDomain domain) {
            super(domain);
        }

        public void setRootPackage(EPackage root) {
            this.root = root;
        }

        public EPackage getRootPackage() {
            return root;
        }
    }

    private static final String REFRESH_DIAGRAM_ON_OPENING = "Refresh diagram on opening";

    /**
     * The label of the command that launch the arrange.
     */
    private static final String ARRANGE_CMD_LABEL = "Arrange Created views";

    private static final String REFRESH_REPRESENTATION_CMD_LABEL = "Refresh representation";

    private static final String P1 = "p1";

    private static final String P2 = "p2";

    private static final String P3 = "p3";

    private static final Point ORIGIN_POSITION = new Point(0, 0);

    private static final Point FIRST_ELEMENT_IN_CONTAINER_POSITION = new Point(30, 29);

    private static final Point FIRST_CONTAINER_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION = new Point(-200, 19);

    private static final Point SECOND_CONTAINER_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION = new Point(10, 19);

    private static final Point SECOND_LIST_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION = new Point(130, 29);

    private static final Point SECOND_LIST_IN_CONTAINER_POSITION = new Point(100, 29);

    private static final Point SECOND_LIST_IN_DIAGRAM_POSITION = new Point(100, 0);

    private static final Point SECOND_CONTAINER_IN_CONTAINER_POSITION = new Point(210, 29);

    private static final Point SECOND_CONTAINER_IN_DIAGRAM_POSITION = new Point(210, 0);

    private static final Point SECOND_NODE_IN_CONTAINER_POSITION = new Point(90, 29);

    private static final Point SECOND_NODE_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION = new Point(120, 29);

    private static final Point SECOND_NODE_IN_DIAGRAM_POSITION_TEST = new Point(90, 0);

    private static final String C6 = "c6";

    private static final String C5 = "c5";

    private static final String C4 = "c4";

    private static final String C3 = "c3";

    private static final String C2 = "c2";

    private static final String C1 = "c1";

    private static final String REPRESENTATION_NAME = "p1 package entities";

    private static final String REPRESENTATION_NAME_BIS = "p1 package entities bis";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities_For_VP-1633";

    private static final String REPRESENTATION_INSTANCE_NAME_TER = "new EntitiesWithEClassAsBorderedNodeMapping";

    private static final String REPRESENTATION_NAME_TER = "EntitiesWithEClassAsBorderedNodeMapping";

    private static final String MODEL = "1633.ecore";

    private static final String SESSION_FILE = "1633.aird";

    private static final String DATA_UNIT_DIR = "data/unit/nodeCreation/";

    private static final String FILE_DIR = "/";

    /**
     * Boolean to activate to reveal the bug of VP-2424.
     */
    private static final boolean CHECK_VP_2424 = false;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private EObject semanticModel;

    private TransactionalEditingDomain otherTED;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        // Get the semantic model root
        Iterator<Resource> resourcesIterator = localSession.getOpenedSession().getSemanticResources().iterator();
        if (resourcesIterator.hasNext()) {
            Resource semanticResource = resourcesIterator.next();
            if (!semanticResource.getContents().isEmpty()) {
                semanticModel = semanticResource.getContents().get(0);
            }
        }
        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(new UIResource(designerProject, MODEL));
        semanticResourceNode.expandNode(P1).click();
        otherTED = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        /*
         * Force the addition of a dependency to the sample ecore editor
         * otherwise the interpreter has no way to retrieve the service class
         * hence any call to "render()" will fail.
         */
        localSession.getOpenedSession().getInterpreter().setProperty(IInterpreter.FILES, Collections.singleton("/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign"));

    }

    private void openDiagram() {
        editor = openDiagram(REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME);
        editor.setSnapToGrid(false);
    }

    private SWTBotSiriusDiagramEditor openDiagram(String representationDescriptionName, String representationName) {
        SWTBotUtils.waitAllUiEvents();
        SWTBotSiriusDiagramEditor swtBotEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationDescriptionName, representationName, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();
        return swtBotEditor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        otherTED.dispose();
        otherTED = null;
        if (editor != null) {
            editor.close();
        }
        editor = null;
        sessionAirdResource = null;
        localSession = null;
        semanticModel = null;
        super.tearDown();
    }

    /**
     * Test for the single EClass creation with a standard tool to check that
     * created element are selected after creation.
     */
    public void testNodePrimarySelectionAfterSingleCreationByStandardTool() {
        // Create one EClass
        openDiagram();
        editor.activateTool("Class");
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Point creationLocation = editor.getBounds(p3Bot).getCenter();
        editor.click(creationLocation);

        // Checks its selection
        SWTBotGefEditPart createdNewEClass1Bot = editor.getEditPart("new EClass 1").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, createdNewEClass1Bot.part().getSelected());
        Point currentLocation = editor.getBounds(createdNewEClass1Bot).getLocation();
        GraphicTestsSupportHelp.assertEquals("the created view should be positioned at the requested location", creationLocation, currentLocation, 0, 0);
    }

    /**
     * Create 2 classes in an editor and check that the positions of this two
     * new nodes are not the same in another editor.
     * 
     * see VP-2418 (comment 04/Nov/11).
     */
    public void testPositionsOfNewNodesCreatedInAnotherEditor() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        // Open a editor on a diagram (a specific one to have the refresh on
        // it)
        SWTBotSiriusDiagramEditor editor2 = openDiagram(REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME_BIS);
        // Open a editor on a diagram (the one used to make the
        // modifications)
        openDiagram();
        // Create two EClass in first diagram
        editor.activateTool("Class");
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Point creationLocation = editor.getBounds(p3Bot).getCenter();
        editor.click(creationLocation.getTranslated(SiriusLayoutDataManager.PADDING, SiriusLayoutDataManager.PADDING));
        editor.activateTool("Class");
        editor.click(creationLocation);
        // Go to the second editor and check the positions of the new
        // classes
        editor2.show();
        editor2.click(0, 0);
        SWTBotGefEditPart c1EP = editor2.getEditPart("new EClass 1", DNodeList2EditPart.class);
        SWTBotGefEditPart c2EP = editor2.getEditPart("new EClass 2", DNodeList2EditPart.class);
        assertFalse("The location (top-left corner) of the first created figure should not overlaped the location (top-left corner) of the second created figure.",
                ((GraphicalEditPart) c1EP.part()).getFigure().getBounds().getLocation().equals(((GraphicalEditPart) c2EP.part()).getFigure().getBounds().getLocation()));
    }

    /**
     * Test for the multiple EClass creation with a standard tool to check that
     * created elements are selected after creation.
     */
    public void testNodeMultipleSelectionAfterMultipleCreationByStandardTool() {
        // Create three EClass
        openDiagram();
        editor.activateTool("ThreeClass");
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Point creationLocation = editor.getBounds(p3Bot).getCenter();
        editor.click(creationLocation);

        // Checks their selection
        SWTBotGefEditPart createdNewEClass1Bot = editor.getEditPart("new EClass 1").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, createdNewEClass1Bot.part().getSelected());
        Point currentLocation = editor.getBounds(createdNewEClass1Bot).getLocation();
        GraphicTestsSupportHelp.assertEquals("the created view should be positioned at the requested location", creationLocation, currentLocation, 0, 0);

        SWTBotGefEditPart createdNewEClass2Bot = editor.getEditPart("new EClass 2").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, createdNewEClass2Bot.part().getSelected());

        SWTBotGefEditPart createdNewEClass3Bot = editor.getEditPart("new EClass 3").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, createdNewEClass3Bot.part().getSelected());
    }

    /**
     * Test for the single EReference creation with a standard tool to check
     * that created element are selected after creation.
     */
    public void testEdgePrimarySelectionAfterSingleCreationByStandardTool() {

        openDiagram();
        // Create two EClass
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Rectangle p3Bounds = editor.getBounds(p3Bot);
        Point firstClassCreationLocation = p3Bounds.getCenter().translate(-p3Bounds.width / 3, 0);
        editor.activateTool("Class");
        editor.click(firstClassCreationLocation);
        Point secondClassCreationLocation = firstClassCreationLocation.getTranslated(p3Bounds.width / 2, 0);
        editor.activateTool("Class");
        editor.click(secondClassCreationLocation);

        SWTBotGefEditPart newEClass1Bot = editor.getEditPart("new EClass 1").parent();
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1Bot);
        SWTBotGefEditPart newEClass2Bot = editor.getEditPart("new EClass 2").parent();
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2Bot);

        // Create one EReference between created EClass
        editor.activateTool("Reference");
        editor.click(newEClass1Bounds.getCenter());
        editor.click(newEClass2Bounds.getCenter());

        // Checks their selection
        SWTBotGefEditPart newEReference1Bot = editor.getEditPart("[0..1] newEReference1").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, newEReference1Bot.part().getSelected());
    }

    /**
     * Test for the multiple EReference creation with a standard tool to check
     * that created elements are selected after creation.
     */
    public void testEdgeMultipleSelectionAfterMultipleCreationByStandardTool() {
        openDiagram();
        // Create two EClass
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Rectangle p3Bounds = editor.getBounds(p3Bot);
        Point firstClassCreationLocation = p3Bounds.getCenter().translate(-p3Bounds.width / 3, 0);
        editor.activateTool("Class");
        editor.click(firstClassCreationLocation);
        Point secondClassCreationLocation = firstClassCreationLocation.getTranslated(p3Bounds.width / 2, 0);
        editor.activateTool("Class");
        editor.click(secondClassCreationLocation);

        SWTBotGefEditPart newEClass1Bot = editor.getEditPart("new EClass 1").parent();
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1Bot);
        SWTBotGefEditPart newEClass2Bot = editor.getEditPart("new EClass 2").parent();
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2Bot);

        // Create one EReference between created EClass
        editor.activateTool("ThreeReference");
        editor.click(newEClass1Bounds.getCenter());
        editor.click(newEClass2Bounds.getCenter());

        // Checks their selection
        SWTBotGefEditPart newEReference1Bot = editor.getEditPart("[0..1] newEReference1").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, newEReference1Bot.part().getSelected());

        SWTBotGefEditPart newEReference2Bot = editor.getEditPart("[0..1] newEReference2").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, newEReference2Bot.part().getSelected());

        SWTBotGefEditPart newEReference3Bot = editor.getEditPart("[0..1] newEReference3").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, newEReference3Bot.part().getSelected());
    }

    /**
     * Test for the single EClass creation with a ExternalJavaAction to check
     * that created element are selected after creation.
     */
    public void testNodePrimarySelectionAfterSingleCreationByExternalJavaAction() {
        openDiagram();

        // Create one EClass
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Point creationLocation = editor.getBounds(p3Bot).getCenter();
        editor.activateTool("CreateOneClassWithExternalJavaAction");
        editor.click(creationLocation);

        // Checks its selection
        SWTBotGefEditPart createdEClass0Bot = editor.getEditPart("newEClass0").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, createdEClass0Bot.part().getSelected());
        Point currentLocation = editor.getBounds(createdEClass0Bot).getLocation();
        GraphicTestsSupportHelp.assertEquals("the created view should be positioned at the requested location", creationLocation, currentLocation, 0, 0);
    }

    /**
     * Test for the multiple EClass creation with a ExternalJavaAction to check
     * that created elements are selected after creation.
     */
    public void testNodeMultipleSelectionAfterMultipleCreationByExternalJavaAction() {
        openDiagram();

        // Create three EClass
        editor.activateTool("CreateThreeClassWithExternalJavaAction");
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Point creationLocation = editor.getBounds(p3Bot).getCenter();
        editor.click(creationLocation);

        // Checks their selection
        SWTBotGefEditPart createdEClass0Bot = editor.getEditPart("newEClass0").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, createdEClass0Bot.part().getSelected());
        Point currentLocation = editor.getBounds(createdEClass0Bot).getLocation();
        GraphicTestsSupportHelp.assertEquals("the created view should be positioned at the requested location", creationLocation, currentLocation, 0, 0);

        SWTBotGefEditPart createdEClass1Bot = editor.getEditPart("newEClass1").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, createdEClass1Bot.part().getSelected());

        SWTBotGefEditPart createdEClass2Bot = editor.getEditPart("newEClass2").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, createdEClass2Bot.part().getSelected());
    }

    /**
     * Test for the single EReference creation with a ExternalJavaAction to
     * check that created element are selected after creation.
     */
    public void testEdgePrimarySelectionAfterSingleCreationByExternalJavaAction() {
        openDiagram();

        // Create two EClass
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Rectangle p3Bounds = editor.getBounds(p3Bot);
        Point firstClassCreationLocation = p3Bounds.getCenter().translate(-p3Bounds.width / 3, 0);
        editor.activateTool("Class");
        editor.click(firstClassCreationLocation);
        Point secondClassCreationLocation = firstClassCreationLocation.getTranslated(p3Bounds.width / 2, 0);
        editor.activateTool("Class");
        editor.click(secondClassCreationLocation);

        SWTBotGefEditPart newEClass1Bot = editor.getEditPart("new EClass 1").parent();
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1Bot);
        SWTBotGefEditPart newEClass2Bot = editor.getEditPart("new EClass 2").parent();
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2Bot);

        // Create one EReference between created EClass
        editor.activateTool("CreateOneReferenceWithExternalJavaAction");
        editor.click(newEClass1Bounds.getCenter());
        editor.click(newEClass2Bounds.getCenter());

        // Checks their selection
        SWTBotGefEditPart newEReference0Bot = editor.getEditPart("[0..1] newEReference0").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, newEReference0Bot.part().getSelected());
    }

    /**
     * Test for the multiple EReference creation with a ExternalJavaAction to
     * check that created elements are selected after creation.
     */
    public void testEReferenceMultipleSelectionAfterMultipleCreationByExternalJavaAction() {
        openDiagram();

        // Create two EClass
        SWTBotGefEditPart p3Bot = editor.getEditPart("p3").parent();
        Rectangle p3Bounds = editor.getBounds(p3Bot);
        Point firstClassCreationLocation = p3Bounds.getCenter().translate(-p3Bounds.width / 3, 0);
        editor.activateTool("Class");
        editor.click(firstClassCreationLocation);
        Point secondClassCreationLocation = firstClassCreationLocation.getTranslated(p3Bounds.width / 2, 0);
        editor.activateTool("Class");
        editor.click(secondClassCreationLocation);

        SWTBotGefEditPart newEClass1Bot = editor.getEditPart("new EClass 1").parent();
        Rectangle newEClass1Bounds = editor.getBounds(newEClass1Bot);
        SWTBotGefEditPart newEClass2Bot = editor.getEditPart("new EClass 2").parent();
        Rectangle newEClass2Bounds = editor.getBounds(newEClass2Bot);

        // Create one EReference between created EClass
        editor.activateTool("CreateThreeReferenceWithExternalJavaAction");
        editor.click(newEClass1Bounds.getCenter());
        editor.click(newEClass2Bounds.getCenter());

        // Checks their selection
        SWTBotGefEditPart newEReference0Bot = editor.getEditPart("[0..1] newEReference0").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, newEReference0Bot.part().getSelected());

        SWTBotGefEditPart newEReference1Bot = editor.getEditPart("[0..1] newEReference1").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED, newEReference1Bot.part().getSelected());

        SWTBotGefEditPart newEReference2Bot = editor.getEditPart("[0..1] newEReference2").parent();
        assertEquals("the created view should be selected as primary view", EditPart.SELECTED_PRIMARY, newEReference2Bot.part().getSelected());
    }

    /**
     * Test the node list creation
     */
    public void testNodeListCreationOnDiagram() {
        openDiagram();
        updateSemanticModel();

        editor.click(0, 0);
        manualRefresh();

        SWTBotUtils.waitAllUiEvents();
        // check that the new classes are in the right positions
        checkListCreationPosition();

        // undo / redo
        undo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkListNoCreatedViews();
        redo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkListCreationPosition();

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkListCreationPosition();
    }

    /**
     * Test the node list creation
     */
    public void testNodeListCreationOnOpeningDiagram() {
        updateSemanticModel();
        openDiagram();
        // check that the new classes are in the right positions
        checkListCreationPosition();
        // check that adapters are disposed
        checkAdapters();

        // undo / redo
        undo(ARRANGE_CMD_LABEL);
        undo(REFRESH_DIAGRAM_ON_OPENING);
        SWTBotUtils.waitAllUiEvents();

        checkListNoCreatedViews();
        redo(REFRESH_DIAGRAM_ON_OPENING);
        redo(ARRANGE_CMD_LABEL);
        SWTBotUtils.waitAllUiEvents();

        checkListCreationPosition();

        // close an reopen the diagram
        saveCloseReopenDiagram();
        SWTBotUtils.waitAllUiEvents();

        checkListCreationPosition();
    }

    /**
     * Test the node list creation
     */
    public void testOneNodeListCreationOnDiagram() {
        openDiagram();
        updateOneSemanticModel();

        editor.click(0, 0);
        manualRefresh();

        // check that the new classes are in the right positions
        checkOneListCreationPosition(false);

        // undo / redo
        undo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkListNoCreatedViews();
        redo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkOneListCreationPosition(false);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkOneListCreationPosition(false);
    }

    /**
     * Test the node list creation
     */
    public void testOneNodeListCreationOnOpeningDiagram() {
        updateOneSemanticModel();
        openDiagram();
        // check that the new classes are in the right positions
        checkOneListCreationPosition(false);
        // check that adapters are disposed
        checkAdapters();

        // undo / redo
        undo(ARRANGE_CMD_LABEL);
        undo(REFRESH_DIAGRAM_ON_OPENING);
        checkListNoCreatedViews();
        redo(REFRESH_DIAGRAM_ON_OPENING);
        redo(ARRANGE_CMD_LABEL);
        checkOneListCreationPosition(false);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkOneListCreationPosition(false);
    }

    /**
     * 
     */
    private void checkAdapters() {
        List<View> result = Lists.newArrayList();
        View diagramView = (View) editor.mainEditPart().part().getModel();
        for (Object view : diagramView.getChildren()) {
            if (view instanceof View && hasAdapter(((View) view))) {
                result.add((View) view);
            }
            if (!result.isEmpty()) {
                result.addAll(checkAdaptersRec((View) view));
            }
        }
        assertTrue("Adapters are not disposed", result.isEmpty());
    }

    /**
     * @param view
     */
    private List<View> checkAdaptersRec(View containerView) {
        List<View> result = Lists.newArrayList();
        for (Object view : containerView.getChildren()) {
            if (view instanceof View && hasAdapter(((View) view))) {
                result.add((View) view);
            }
            if (!result.isEmpty()) {
                result.addAll(checkAdaptersRec((View) view));
            }
        }
        return result;
    }

    private boolean hasAdapter(View view) {
        for (Adapter adapter : view.eAdapters()) {
            if (adapter.isAdapterForType(SiriusLayoutDataManager.INSTANCE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the node creation
     */
    public void testNodeCreationOnDiagram() {
        openDiagram();
        updateNodeSemanticModel();

        editor.click(0, 0);
        manualRefresh();

        // check that the new classes are in the right positions
        checkNodeCreationPosition();

        // undo / redo
        undo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkNodeNoCreatedViews();
        redo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkNodeCreationPosition();

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkNodeCreationPosition();
    }

    /**
     * Test the node creation
     */
    public void testNodeCreationOnOpeningDiagram() {
        updateNodeSemanticModel();
        openDiagram();
        // check that the new classes are in the right positions
        checkNodeCreationPosition();
        // check that adapters are disposed
        checkAdapters();

        // undo / redo
        undo(ARRANGE_CMD_LABEL);
        undo(REFRESH_DIAGRAM_ON_OPENING);
        checkNodeNoCreatedViews();
        redo(REFRESH_DIAGRAM_ON_OPENING);
        redo(ARRANGE_CMD_LABEL);
        checkNodeCreationPosition();

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkNodeCreationPosition();
    }

    /**
     * Test the node creation
     */
    public void testOneNodeCreationOnDiagram() {
        openDiagram();
        updateOneNodeSemanticModel();

        editor.click(0, 0);
        manualRefresh();

        // check that the new classes are in the right positions
        checkOneNodeCreationPosition(false);

        // undo / redo
        undo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkNodeNoCreatedViews();
        redo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkOneNodeCreationPosition(false);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkOneNodeCreationPosition(false);
    }

    /**
     * Test the node creation
     */
    public void testOneNodeCreationOnOpeningDiagram() {
        updateOneNodeSemanticModel();
        openDiagram();
        // check that the new classes are in the right positions
        checkOneNodeCreationPosition(false);
        // check that adapters are disposed
        checkAdapters();

        // undo / redo
        undo(ARRANGE_CMD_LABEL);
        undo(REFRESH_DIAGRAM_ON_OPENING);
        checkNodeNoCreatedViews();
        redo(REFRESH_DIAGRAM_ON_OPENING);
        redo(ARRANGE_CMD_LABEL);
        checkOneNodeCreationPosition(false);

        // check that adapters are disposed
        checkAdapters();
        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkOneNodeCreationPosition(false);
    }

    /**
     * Test the container creation
     */
    public void testContainerCreationOnDiagram() {
        openDiagram();
        updateContainerSemanticModel();

        editor.click(0, 0);
        manualRefresh();

        // check that the new classes are in the right positions
        checkContainerCreationPosition();

        // undo / redo
        undo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkContainerNoCreatedViews();
        redo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkContainerCreationPosition();

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkContainerCreationPosition();
    }

    /**
     * Test the container creation
     */
    public void testContainerCreationOnOpeningDiagram() {
        updateContainerSemanticModel();
        openDiagram();
        // check that the new classes are in the right positions
        checkContainerCreationPosition();
        // check that adapters are disposed
        checkAdapters();

        // undo / redo
        undo(ARRANGE_CMD_LABEL);
        undo(REFRESH_DIAGRAM_ON_OPENING);
        checkContainerNoCreatedViews();
        redo(REFRESH_DIAGRAM_ON_OPENING);
        redo(ARRANGE_CMD_LABEL);
        checkContainerCreationPosition();

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkContainerCreationPosition();
    }

    /**
     * Test one container creation
     */
    public void testOneContainerCreationOnDiagram() {
        openDiagram();
        updateOneContainerSemanticModel();

        editor.click(0, 0);
        manualRefresh();

        // check that the new classes are in the right positions
        checkOneContainerCreationPosition(true);

        // undo / redo
        undo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkContainerNoCreatedViews();
        redo(REFRESH_REPRESENTATION_CMD_LABEL);
        checkOneContainerCreationPosition(true);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkOneContainerCreationPosition(true);
    }

    /**
     * Test the container creation
     */
    public void testOneContainerCreationOnOpeningDiagram() {
        updateOneContainerSemanticModel();
        openDiagram();
        // check that the new classes are in the right positions
        checkOneContainerCreationPosition(true);
        // check that adapters are disposed
        checkAdapters();

        // undo / redo
        undo(ARRANGE_CMD_LABEL);
        undo(REFRESH_DIAGRAM_ON_OPENING);
        checkContainerNoCreatedViews();
        redo(REFRESH_DIAGRAM_ON_OPENING);
        redo(ARRANGE_CMD_LABEL);
        checkOneContainerCreationPosition(true);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkOneContainerCreationPosition(true);
    }

    /**
     * Test the bordered node creation, with already existing bordered node, to
     * check that only newly created element is layouted. See VP-2902.
     */
    public void _testBorderedNodeCreationAndThenOpenDiagram() {
        editor = openDiagram(REPRESENTATION_NAME_TER, REPRESENTATION_INSTANCE_NAME_TER);

        SWTBotGefEditPart p2EP = editor.getEditPart(P2, DNodeContainerEditPart.class);
        SWTBotGefEditPart p3EP = editor.getEditPart(P3, DNodeContainer2EditPart.class);
        Point p2EPInitialAbsoluteLocation = ((GraphicalEditPart) p2EP.part()).getFigure().getBounds().getLocation();
        Point p3EPInitialAbsoluteLocation = ((GraphicalEditPart) p3EP.part()).getFigure().getBounds().getLocation();

        editor.close();

        updateOneSemanticModel();

        editor = openDiagram(REPRESENTATION_NAME_TER, REPRESENTATION_INSTANCE_NAME_TER);

        p2EP = editor.getEditPart(P2, DNodeContainerEditPart.class);
        p3EP = editor.getEditPart(P3, DNodeContainer2EditPart.class);

        // Layout of newly created bordered nodes at opening shouldn't layout
        // other views
        checkPosition(p2EP, p2EPInitialAbsoluteLocation);
        checkPosition(p3EP, p3EPInitialAbsoluteLocation);

        editor.save();
        editor.close();

        updateOneSemanticModel();

        editor = openDiagram(REPRESENTATION_NAME_TER, REPRESENTATION_INSTANCE_NAME_TER);

        p2EP = editor.getEditPart(P2, DNodeContainerEditPart.class);
        p3EP = editor.getEditPart(P3, DNodeContainer2EditPart.class);

        // Layout of newly created bordered nodes at opening shouldn't layout
        // other views
        checkPosition(p2EP, p2EPInitialAbsoluteLocation);
        checkPosition(p3EP, p3EPInitialAbsoluteLocation);

        editor.save();
        editor.close();
    }

    private void updateSemanticModelInAnotherResourceSet(RecordingCommandWithModelInitializer commandToExecute) {
        // Load the semantic resource in another resource set, add a new class
        // in package p1 and save the resource.
        ResourceSet set = otherTED.getResourceSet();
        try {
            EPackage ePackage = (EPackage) semanticModel;
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(ePackage.eResource().getURI(), set);
            assertFalse("The editing domain of each root semantic must be different.", otherTED.equals(TransactionUtil.getEditingDomain(ePackage)));

            commandToExecute.setRootPackage(ePackageInAnotherResourceSet);
            otherTED.getCommandStack().execute(commandToExecute);
            ePackageInAnotherResourceSet.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
        SWTBotUtils.waitAllUiEvents();
    }

    private void updateSemanticModel() {
        updateSemanticModelInAnotherResourceSet(new RecordingCommandWithModelInitializer(otherTED) {
            @Override
            protected void doExecute() {
                EClass class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C1);
                getRootPackage().getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C2);
                getRootPackage().getEClassifiers().add(class1);
                EPackage package2 = getRootPackage().getESubpackages().get(0);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C3);
                package2.getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C4);
                package2.getEClassifiers().add(class1);
                EPackage package3 = package2.getESubpackages().get(0);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C5);
                package3.getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C6);
                package3.getEClassifiers().add(class1);
            }
        });
    }

    /**
     * Add new class in package p1
     */
    private void updateOneSemanticModel() {
        updateSemanticModelInAnotherResourceSet(new RecordingCommandWithModelInitializer(otherTED) {
            @Override
            protected void doExecute() {
                EClass class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C1);
                getRootPackage().getEClassifiers().add(class1);
                EPackage package2 = getRootPackage().getESubpackages().get(0);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C3);
                package2.getEClassifiers().add(class1);
                EPackage package3 = package2.getESubpackages().get(0);
                class1 = EcoreFactory.eINSTANCE.createEClass();
                class1.setName(C5);
                package3.getEClassifiers().add(class1);
            }
        });
    }

    private void saveCloseReopenDiagram() {
        editor.saveAndClose();
        SWTBotUtils.waitAllUiEvents();
        openDiagram();
    }

    /**
     * Add classes in packages
     */
    private void updateContainerSemanticModel() {
        updateSemanticModelInAnotherResourceSet(new RecordingCommandWithModelInitializer(otherTED) {
            @Override
            protected void doExecute() {
                EPackage package2 = getRootPackage().getESubpackages().get(0);
                EPackage package3 = package2.getESubpackages().get(0);

                EPackage class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C1);
                getRootPackage().getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C2);
                getRootPackage().getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C3);
                package2.getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C4);
                package2.getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C5);
                package3.getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C6);
                package3.getESubpackages().add(class1);
            }
        });
    }

    private void updateOneContainerSemanticModel() {
        updateSemanticModelInAnotherResourceSet(new RecordingCommandWithModelInitializer(otherTED) {
            @Override
            protected void doExecute() {
                EPackage package2 = getRootPackage().getESubpackages().get(0);
                EPackage package3 = package2.getESubpackages().get(0);

                EPackage class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C1);
                getRootPackage().getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C3);
                package2.getESubpackages().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEPackage();
                class1.setName(C5);
                package3.getESubpackages().add(class1);
            }
        });
    }

    private void updateNodeSemanticModel() {
        updateSemanticModelInAnotherResourceSet(new RecordingCommandWithModelInitializer(otherTED) {
            @Override
            protected void doExecute() {
                EPackage package2 = getRootPackage().getESubpackages().get(0);
                EPackage package3 = package2.getESubpackages().get(0);

                EEnum class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C1);
                getRootPackage().getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C2);
                getRootPackage().getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C3);
                package2.getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C4);
                package2.getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C5);
                package3.getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C6);
                package3.getEClassifiers().add(class1);
            }
        });
    }

    private void updateOneNodeSemanticModel() {
        updateSemanticModelInAnotherResourceSet(new RecordingCommandWithModelInitializer(otherTED) {
            @Override
            protected void doExecute() {
                EPackage package2 = getRootPackage().getESubpackages().get(0);
                EPackage package3 = package2.getESubpackages().get(0);

                EEnum class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C1);
                getRootPackage().getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C3);
                package2.getEClassifiers().add(class1);
                class1 = EcoreFactory.eINSTANCE.createEEnum();
                class1.setName(C5);
                package3.getEClassifiers().add(class1);
            }
        });
    }

    /**
     * Get the data associated with this widget.
     * 
     * @param widget
     *            The concern widget
     * @return The associated data
     */
    public Object getData(final TreeItem widget) {
        return UIThreadRunnable.syncExec(new Result<Object>() {
            @Override
            public Object run() {
                return widget.getData();
            }
        });
    }

    /**
     * Check the position of the edit part.
     * 
     * @param ep
     * @param point
     */
    private void checkPosition(SWTBotGefEditPart ep, Point expectedPoint) {
        assertEquals("Wrong x creation position", expectedPoint.x, ((GraphicalEditPart) ep.part()).getFigure().getBounds().x, 6);
        assertEquals("Wrong y creation position", expectedPoint.y, ((GraphicalEditPart) ep.part()).getFigure().getBounds().y, 6);
    }

    /**
     * 
     */
    private void checkOneNodeCreationPosition(boolean withLayout) {
        checkOneNodeCreationPosition(DNodeEditPart.class, DNode3EditPart.class);
    }

    /**
     * 
     */
    private void checkOneContainerCreationPosition(boolean onlyOneElement) {
        SWTBotGefEditPart c1EP = editor.getEditPart(C1, DNodeContainerEditPart.class);
        checkPosition(c1EP, ORIGIN_POSITION);
        SWTBotGefEditPart c3EP = editor.getEditPart(C3, DNodeContainer2EditPart.class);
        // There is a specific case for this element (it can be shifted more for
        // other views)
        if (onlyOneElement) {
            checkPosition(c3EP, SECOND_CONTAINER_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION);
        } else {
            checkPosition(c3EP, FIRST_CONTAINER_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION);
        }
        SWTBotGefEditPart c5EP = editor.getEditPart(C5, DNodeContainer2EditPart.class);
        checkPosition(c5EP, FIRST_ELEMENT_IN_CONTAINER_POSITION);
    }

    /**
     * 
     */
    private void checkOneListCreationPosition(boolean withLayout) {
        checkOneCreationPosition(DNodeListEditPart.class, DNodeList2EditPart.class);
    }

    /**
     * 
     */
    private void checkNodeCreationPosition() {
        checkCreationPosition(DNodeEditPart.class, SECOND_NODE_IN_DIAGRAM_POSITION_TEST, DNode3EditPart.class, SECOND_NODE_IN_CONTAINER_POSITION,
                SECOND_NODE_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION);
    }

    /**
     * 
     */
    private void checkContainerCreationPosition() {
        checkOneContainerCreationPosition(false);

        SWTBotGefEditPart c2EP = editor.getEditPart(C2, DNodeContainerEditPart.class);
        checkPosition(c2EP, SECOND_CONTAINER_IN_DIAGRAM_POSITION);
        SWTBotGefEditPart c4EP = editor.getEditPart(C4, DNodeContainer2EditPart.class);
        checkPosition(c4EP, SECOND_CONTAINER_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION);
        if (CHECK_VP_2424) {
            SWTBotGefEditPart c6EP = editor.getEditPart(C6, DNodeContainer2EditPart.class);
            checkPosition(c6EP, SECOND_CONTAINER_IN_CONTAINER_POSITION);
        }
    }

    /**
     * 
     */
    private void checkListCreationPosition() {
        checkCreationPosition(DNodeListEditPart.class, SECOND_LIST_IN_DIAGRAM_POSITION, DNodeList2EditPart.class, SECOND_LIST_IN_CONTAINER_POSITION,
                SECOND_LIST_IN_CONTAINER_WITH_OTHER_PINNED_ELEMENT_POSITION);
    }

    /**
     * 
     */
    private void checkOneCreationPosition(Class<? extends EditPart> inDiagramType, Class<? extends EditPart> inContainerType) {
        SWTBotGefEditPart c1EP = editor.getEditPart(C1, inDiagramType);
        checkPosition(c1EP, ORIGIN_POSITION);
        SWTBotGefEditPart c3EP = editor.getEditPart(C3, inContainerType);
        checkPosition(c3EP, FIRST_ELEMENT_IN_CONTAINER_POSITION);
        SWTBotGefEditPart c5EP = editor.getEditPart(C5, inContainerType);
        checkPosition(c5EP, FIRST_ELEMENT_IN_CONTAINER_POSITION);
    }

    /**
     * 
     */
    private void checkOneNodeCreationPosition(Class<? extends EditPart> inDiagramType, Class<? extends EditPart> inContainerType) {
        SWTBotGefEditPart c1EP = editor.getEditPart(C1, inDiagramType);
        checkPosition(c1EP, ORIGIN_POSITION);
        SWTBotGefEditPart c3EP = editor.getEditPart(C3, inContainerType);
        checkPosition(c3EP, FIRST_ELEMENT_IN_CONTAINER_POSITION);
        SWTBotGefEditPart c5EP = editor.getEditPart(C5, inContainerType);
        checkPosition(c5EP, FIRST_ELEMENT_IN_CONTAINER_POSITION);
    }

    /**
     * 
     */
    private void checkCreationPosition(Class<? extends EditPart> inDiagramType, Point diagramPosition, Class<? extends EditPart> inContainerType, Point containerPosition,
            Point containerWithPinnedElementPosition) {
        checkOneCreationPosition(inDiagramType, inContainerType);
        SWTBotGefEditPart c2EP = editor.getEditPart(C2, inDiagramType);
        checkPosition(c2EP, diagramPosition);
        SWTBotGefEditPart c4EP = editor.getEditPart(C4, inContainerType);
        checkPosition(c4EP, containerWithPinnedElementPosition);
        SWTBotGefEditPart c6EP = editor.getEditPart(C6, inContainerType);
        checkPosition(c6EP, containerPosition);
    }

    /**
     * 
     */
    private void checkNodeNoCreatedViews() {
        checkNoCreatedViews(DNodeEditPart.class, DNode3EditPart.class);
    }

    /**
     * 
     */
    private void checkContainerNoCreatedViews() {
        checkNoCreatedViews(DNodeContainerEditPart.class, DNodeContainer2EditPart.class);
    }

    /**
     * 
     */
    private void checkListNoCreatedViews() {
        checkNoCreatedViews(DNodeListEditPart.class, DNodeList2EditPart.class);
    }

    /**
     * 
     */
    private void checkOneNoCreatedViews(Class<? extends EditPart> inDiagramType, Class<? extends EditPart> inContainerType) {
        checkNoEditPart(C1, inDiagramType);
        checkNoEditPart(C3, inContainerType);
        checkNoEditPart(C5, inContainerType);
    }

    /**
     * 
     */
    private void checkNoCreatedViews(Class<? extends EditPart> inDiagramType, Class<? extends EditPart> inContainerType) {
        checkOneNoCreatedViews(inDiagramType, inContainerType);
        checkNoEditPart(C2, inDiagramType);
        checkNoEditPart(C6, inContainerType);
        checkNoEditPart(C4, inContainerType);
    }

    /**
     * @param class1
     * @param name
     * 
     */
    private void checkNoEditPart(String name, Class<? extends EditPart> class1) {
        boolean found = true;
        try {
            editor.getEditPart(name, class1);
            found = true;
        } catch (WidgetNotFoundException e) {
            found = false;
        } finally {
            assertFalse("Edit part should not exist", found);
        }
    }
}

/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;

/**
 * In a diagram representation, ensure that when we change container children
 * presentation (from List to FreeForm or from FreeForm to List), the
 * representation is correctly refreshed and that there is no error message in
 * the Error Log.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class ContainerChildrenPresentationChangeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "462927.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "462927.odesign";

    private static final String FILE_DIR = "/";

    private static final String UNSYNCHRONIZED_REPRESENTATION_INSTANCE_NAME = "new 462927 unsynchronized";

    private static final String SYNCHRONIZED_REPRESENTATION_INSTANCE_NAME = "new 462927 synchronized";

    private static final String REPRESENTATION_NAME = "462927";

    private static final String DATA_UNIT_DIR = "data/unit/containerChildrenPresentationChange/";

    private static final String CONTAINER_NAME = "c";

    private static final String DEFAULT = "Default";

    private static final String CONTAINER = "462927";

    private static final String PROPERTIES = "Properties";

    private static final String GENERAL = "General";

    private DDiagram dDiagram;

    /**
     * Sirius Diagram Editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     * 
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
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    /**
     * Test container children presentation change when vsm and diagram are
     * synchronized.
     */
    public void testSychronizedContainerChildrenPresentation() {
        // Check that the VSM and representation are synchronized
        checkSynchronizationLevel(true);
        openDiagramRepresentation(SYNCHRONIZED_REPRESENTATION_INSTANCE_NAME);

        // Test container children presentation change with opened and
        // synchronized diagram editor.
        testContainerChildrenPresentation(true, true);

        // Test container children presentation change with closed and
        // synchronized diagram editor.
        testContainerChildrenPresentation(false, true);
    }

    /**
     * Test container children presentation change when vsm and diagram are
     * unsynchronized.
     */
    public void testUnsychronizedContainerChildrenPresentation() {
        // Check that the VSM and representation are unsynchronized
        checkSynchronizationLevel(false);
        openDiagramRepresentation(UNSYNCHRONIZED_REPRESENTATION_INSTANCE_NAME);

        // Test container children presentation change with opened and
        // unsynchronized diagram editor.
        testContainerChildrenPresentation(true, false);

        // Test container children presentation change with closed and
        // unsynchronized diagram editor.
        testContainerChildrenPresentation(false, false);
    }

    /**
     * Test container children presentation change with opened/closed diagram.
     */
    private void testContainerChildrenPresentation(boolean openedDiagram, boolean isSynchronized) {
        // Change children presentation change from list to freeform
        changeChildrenPresentation(ContainerLayout.FREE_FORM);
        String representationName;
        if (isSynchronized) {
            representationName = SYNCHRONIZED_REPRESENTATION_INSTANCE_NAME;
        } else {
            representationName = UNSYNCHRONIZED_REPRESENTATION_INSTANCE_NAME;
        }
        if (openedDiagram) {
            bot.editorByTitle(representationName).show();
        } else {
            openDiagramRepresentation(representationName);
        }
        checkContainerPresentationInDiagram(ContainerLayout.FREE_FORM);

        // Change children presentation change from freeform to list
        changeChildrenPresentation(ContainerLayout.LIST);
        if (openedDiagram) {
            bot.editorByTitle(representationName).show();
        } else {
            openDiagramRepresentation(representationName);
        }
        checkContainerPresentationInDiagram(ContainerLayout.LIST);
        closeDiagramRepresentation();
    }

    /**
     * Check diagram and VSM synchronizations.
     * 
     * @param isSynchronized
     *            synchronization level
     */
    private void checkSynchronizationLevel(boolean isSynchronized) {
        if (isSynchronized) {
            dDiagram = (DDiagram) getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_NAME, SYNCHRONIZED_REPRESENTATION_INSTANCE_NAME);
        } else if (!isSynchronized) {
            dDiagram = (DDiagram) getRepresentationWithName(localSession.getOpenedSession(), REPRESENTATION_NAME, UNSYNCHRONIZED_REPRESENTATION_INSTANCE_NAME);
        }
        String containerName = dDiagram.getContainers().get(0).getName();
        ContainerMapping containerMapping = dDiagram.getContainers().get(0).getActualMapping();
        DiagramElementMappingQuery mappingQuery = new DiagramElementMappingQuery(containerMapping);
        assertTrue("The mapping of '" + containerName + " element should be a ContainerMapping'", containerMapping instanceof ContainerMapping);
        if (isSynchronized) {
            assertTrue("Both 'DDiagram' and 'DiagramElementMapping' attributes of '" + containerName + "' container should be synchronized", mappingQuery.isSynchronizedAndCreateElement(dDiagram));
        } else {
            assertFalse("Both 'DDiagram' and 'DiagramElementMapping' attributes of '" + containerName + "' container should be unsynchronized", mappingQuery.isSynchronizedAndCreateElement(dDiagram));
        }
    }

    /**
     * Change container children presentation from FreeForm to List or from List
     * to FreeForm.
     */
    private void changeChildrenPresentation(ContainerLayout childrenPresentation) {
        openVSM();
        SWTBotEditor vsmEditor = selectContainerNodeMapping();
        modifyVSM(childrenPresentation);
        // save the VSM
        vsmEditor.setFocus();
        vsmEditor.save();
    }

    /**
     * Select the container node mapping.
     * 
     * @return the VSM editor
     */
    private SWTBotEditor selectContainerNodeMapping() {
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();
        activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(REPRESENTATION_NAME).expandNode(REPRESENTATION_NAME).expandNode(REPRESENTATION_NAME)
                .expandNode(DEFAULT).select(CONTAINER);
        return activeEditor;
    }

    /**
     * Modify children presentation in the VSM.
     */
    private void modifyVSM(ContainerLayout childrenPresentation) {
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL);
        SWTBotUtils.waitAllUiEvents();
        SWTBotRadio radioBox = propertiesBot.bot().radio(childrenPresentation.getName());
        radioBox.click().setFocus();
    }

    /**
     * Check that the container displays correctly it's children, as list
     * elements or as shapes.
     */
    private void checkContainerPresentationInDiagram(ContainerLayout childrePresentation) {
        if (childrePresentation == ContainerLayout.LIST) {
            assertTrue("The container should display it's children as list elements", editor.getEditPart(CONTAINER_NAME).part().getParent() instanceof DNodeListEditPart);
        } else if (childrePresentation == ContainerLayout.FREE_FORM) {
            assertTrue("The container should display it's children as shapes", editor.getEditPart(CONTAINER_NAME).part().getParent() instanceof DNodeContainerEditPart);
        }
    }

    /**
     * Open the VSM.
     */
    private void openVSM() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();
    }

    /**
     * Open diagram representation.
     * 
     * @param representationInstanceName
     *            the representation instance name
     */
    private void openDiagramRepresentation(String representationInstanceName) {
        this.editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, representationInstanceName, DDiagram.class);
    }

    /**
     * Save changes and close the diagram representation.
     */
    private void closeDiagramRepresentation() {
        editor.setFocus();
        editor.save();
        editor.close();
    }

}

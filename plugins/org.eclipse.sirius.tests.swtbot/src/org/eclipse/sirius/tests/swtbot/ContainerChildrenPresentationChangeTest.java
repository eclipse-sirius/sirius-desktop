/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;

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

    private DDiagram dDiagram;

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
        // get the container mapping
        ContainerMapping containerMapping = getContainerMapping(dDiagram);

        // Change children presentation change from list to freeform
        changeChildrenPresentation(containerMapping, ContainerLayout.FREE_FORM);
        String representationName;
        if (isSynchronized) {
            representationName = SYNCHRONIZED_REPRESENTATION_INSTANCE_NAME;
        } else {
            representationName = UNSYNCHRONIZED_REPRESENTATION_INSTANCE_NAME;
        }
        if (!openedDiagram) {
            openDiagramRepresentation(representationName);
        }
        checkContainerPresentationInDiagram(ContainerLayout.FREE_FORM);

        // Change children presentation change from freeform to list
        changeChildrenPresentation(containerMapping, ContainerLayout.LIST);
        if (!openedDiagram) {
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
     * Change container children presentation mapping.
     */
    private void changeChildrenPresentation(final ContainerMapping containerMapping, final ContainerLayout containerLayout) {
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerMapping.setChildrenPresentation(containerLayout);
            }
        });
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
     * Get the container mapping.
     * 
     * @param representation
     *            the given diagram representation
     * @return a container mapping
     */
    private ContainerMapping getContainerMapping(DDiagram representation) {
        DDiagramElement dDiagramElement = representation.getContainers().get(0);
        DiagramElementMapping diagramElementMapping = dDiagramElement.getDiagramElementMapping();

        assertTrue("The '" + diagramElementMapping.getLabel() + "' mapping should be a ContainerMapping",
                diagramElementMapping instanceof ContainerMapping);
        ContainerMapping containerMapping = (ContainerMapping) diagramElementMapping;
        return containerMapping;
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

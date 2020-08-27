/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.componentization;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ToolGroup;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

public class DiagramComponentizationManagerTest extends AbstractComponentizationTest {

    /**
     * Test layers componentization without contributed layers.
     * 
     * @throws Exception
     *             if the test fails
     */
    public void testLayers() throws Exception {
        final Viewpoint vpTemp = findViewpoint("Componentization test 1").get();
        selectViewpoint(vpTemp, session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 1));

        final DiagramDescription ddTemp = (DiagramDescription) vpTemp.getOwnedRepresentations().get(0);
        List<Layer> layers = DiagramComponentizationTestSupport.getAllLayers(session, ddTemp);

        assertEquals(1, session.getSelectedViewpoints(false).size());
        assertEquals(3, layers.size());

        deselectViewpoint(vpTemp, session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 0));

        assertEquals(0, session.getSelectedViewpoints(false).size());
        layers = DiagramComponentizationTestSupport.getAllLayers(session, ddTemp);
        assertEquals(0, layers.size());
    }

    /**
     * Test layers componentization with contributed layers.
     * 
     * @throws Exception
     *             if the test fails
     */
    public void testContributedLayers() throws Exception {
        final Viewpoint vpTemp = findViewpoint("Componentization test 1").get();
        selectViewpoint(vpTemp, session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 1));

        final DiagramDescription ddTemp = (DiagramDescription) vpTemp.getOwnedRepresentations().get(0);
        List<Layer> layers = DiagramComponentizationTestSupport.getAllLayers(session, ddTemp);
        assertEquals(3, layers.size());

        selectViewpoint(findViewpoint("Componentization test 2").get(), session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 2));

        selectViewpoint(findViewpoint("Componentization test 3").get(), session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 3));

        selectViewpoint(findViewpoint("Componentization test 4").get(), session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 4));

        layers = DiagramComponentizationTestSupport.getAllLayers(session, ddTemp);
        assertEquals(8, layers.size());
    }

    public void testSections() throws Exception {
        final Viewpoint toolSirius = findViewpoint("Tools").get();
        selectViewpoint(toolSirius, session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 1));

        final DiagramDescription diagram = findDiagramDescription(toolSirius, "Diagram");
        final List<Layer> layers = DiagramComponentizationTestSupport.getAllLayers(session, diagram);
        assertEquals(3, layers.size());

        final List<ToolSection> sections = DiagramComponentizationTestSupport.getRootPaletteSections(session, diagram);
        assertEquals(4, sections.size());

        final ToolSection section1 = findSection(sections, "section1");
        final ToolSection section2 = findSection(sections, "section2");
        final ToolSection section3 = findSection(sections, "section3");

        List<ToolEntry> toolEntries1 = DiagramComponentizationTestSupport.getToolEntries(session, section1);
        List<ToolEntry> allToolEntries1 = DiagramComponentizationTestSupport.getAllToolEntries(session, section1);
        assertEquals(4, toolEntries1.size());
        assertEquals(5, allToolEntries1.size());

        List<ToolEntry> toolEntries2 = DiagramComponentizationTestSupport.getToolEntries(session, section2);
        List<ToolEntry> allToolEntries2 = DiagramComponentizationTestSupport.getAllToolEntries(session, section2);
        assertEquals(toolEntries2, allToolEntries2);
        assertEquals(1, toolEntries2.size());

        List<ToolEntry> toolEntries3 = DiagramComponentizationTestSupport.getToolEntries(session, section3);
        List<ToolEntry> allToolEntries3 = DiagramComponentizationTestSupport.getAllToolEntries(session, section3);

        assertEquals(0, toolEntries3.size());
        assertEquals(1, allToolEntries3.size());

    }

    public void testToolGroups() throws Exception {
        final Viewpoint toolSirius = findViewpoint("Tools").get();
        selectViewpoint(toolSirius, session);
        TestsUtil.waitUntil(new SelectedViewpointsCondition(session, 1));

        final DiagramDescription diagram = findDiagramDescription(toolSirius, "Diagram");
        final List<ToolSection> sections = DiagramComponentizationTestSupport.getRootPaletteSections(session, diagram);
        final ToolSection section1 = findSection(sections, "section1");
        final ToolGroup toolGroup = findToolGroup(section1, "toolGroup");

        final List<AbstractToolDescription> tools = DiagramComponentizationTestSupport.getTools(session, toolGroup);
        assertEquals(2, tools.size());
    }

    private void selectViewpoint(final Viewpoint viewpoint, final Session session) {
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command changeViewpointsSelectionCmd = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(viewpoint), Collections.<Viewpoint> emptySet(),
                new NullProgressMonitor());
        domain.getCommandStack().execute(changeViewpointsSelectionCmd);
        waitSaveSessionJob();
    }

    private void deselectViewpoint(final Viewpoint viewpoint, final Session session) {
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command changeViewpointsSelectionCmd = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.<Viewpoint> emptySet(), Collections.singleton(viewpoint),
                new NullProgressMonitor());
        domain.getCommandStack().execute(changeViewpointsSelectionCmd);
        waitSaveSessionJob();
    }

    private static class SelectedViewpointsCondition implements ICondition {
        private Session session;

        private int nbSelectedViewpoints;

        public SelectedViewpointsCondition(Session session, int nbSelectedViewpoints) {
            this.session = session;
            this.nbSelectedViewpoints = nbSelectedViewpoints;
        }

        @Override
        public boolean test() throws Exception {
            return session.getSelectedViewpoints(false).size() == nbSelectedViewpoints;
        }

        @Override
        public String getFailureMessage() {
            return "The session does not have the expected number of selected viewpoints: expected " + nbSelectedViewpoints + " but was " + session.getSelectedViewpoints(false) + ".";
        }
    }
}

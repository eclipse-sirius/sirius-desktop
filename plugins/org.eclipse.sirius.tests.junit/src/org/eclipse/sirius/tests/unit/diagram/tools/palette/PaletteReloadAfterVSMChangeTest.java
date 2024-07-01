/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SectionPaletteDrawer;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * A test ensuring that when the VSM changes, the palette gets updated.
 * 
 * <p>
 * Related issues :
 * <ul>
 * <li>VP-3609: Filter blocking tools on several uses of tools</li>
 * <li>VP-3718: After a VSM change, palette contains entry with tool proxy.
 * Tools are not up to date and are not executables.</li>
 * </ul>
 * </p>
 * 
 * @author mporhel
 * 
 */
public class PaletteReloadAfterVSMChangeTest extends AbstractPaletteManagerTest {

    private static final String SEMANTIC_MODEL_FILENAME = "VP-3600.ecore";

    private static final String MODELER_MODEL_FILENAME = "VP-3600.odesign";

    private static final String SESSION_MODEL_FILENAME = "VP-3600.aird";

    private static final String REPRESENTATION_DESC_NAME = "BlankDiagram";

    private static final String REPRESENTATION_INSTANCE_NAME = "new BlankDiagram";

    private PaletteRoot paletteRoot;

    private ToolSection sectionToModify;

    private Group group;

    private DDiagramEditorImpl editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_MODEL_FILENAME, MODELER_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        SessionUIManager.INSTANCE.createUISession(session);

        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(getRepresentationDescriptionName());
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getName().equals(getRepresentationDescriptionInstanceName())) {
                dDiagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }

        editor = (DDiagramEditorImpl) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        paletteRoot = ((DiagramEditDomain) editor.getDiagramEditDomain()).getPaletteViewer().getPaletteRoot();

        // Load modeler, to be able to modify it and trigger
        // SessionListener.VSM_UPDATED notifications and then palette reloading.
        ResourceSet rs = new ResourceSetImpl();
        URI uri = dDiagram.getDescription().eResource().getURI();
        group = (Group) ModelUtils.load(uri, rs);
        sectionToModify = ((DiagramDescription) group.getOwnedViewpoints().iterator().next().getOwnedRepresentations().iterator().next()).getDefaultLayer().getToolSections().iterator().next();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        // Close the opened editor.
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // And clean all variables.
        editor = null;
        paletteRoot = null;
        group = null;
        sectionToModify = null;

        super.tearDown();
    }

    /**
     * Modify the VSM with an opened described diagram. The palette should be
     * reloaded, to avoid entries referencing proxy tool descriptions.
     * 
     * @throws Exception
     *             if exception thrown
     */
    public void testVSMUpdateImpliesCompletePaletteReload() throws Exception {
        SectionPaletteDrawer sectionEntry = getSectionEntry();
        int initialVisiblePaletteEntries = getAllVisiblePaletteEntries(paletteRoot).size();

        // This dummy modification should just trigger the reload of the
        // palette.
        group.setDocumentation("test");
        group.eResource().save(Collections.emptyMap());

        TestsUtil.synchronizationWithUIThread();

        SectionPaletteDrawer sectionEntryAfterReload = getSectionEntry();
        assertEquals("Please review the test, the equals method has been overriden, the following equality test is not sufficient", Object.class,
                SectionPaletteDrawer.class.getMethod("equals", Object.class).getDeclaringClass());
        assertNotSame("The palette should be cleaned and reloaded after a vsm change.", sectionEntry, sectionEntryAfterReload);
        assertEquals("This dummy reload should not change the visible entries count.", initialVisiblePaletteEntries, getAllVisiblePaletteEntries(paletteRoot).size());

        int initialEContentsCount = semanticModel.eContents().size();

        // VP-3718, after VSM modifications, pre-existing tools were not
        // executable.
        boolean canExecute = applyNodeCreationTool("createPackage", dDiagram, dDiagram);
        assertTrue("The tool should be executable.", canExecute);
        assertEquals("The tool should create a new semantic element.", initialEContentsCount + 1, semanticModel.eContents().size());

    }

    /**
     * Modify the VSM with an opened described diagram.
     * 
     * Add, Change and remove a tool, the palette should be reloaded, and must
     * reflect the VSM.
     * 
     * @throws Exception
     *             if exception thrown
     */
    public void testPaletteStateAfterVsmToolAndSectionModifications() throws Exception {
        int initialVisiblePaletteEntries = getAllVisiblePaletteEntries(paletteRoot).size();

        // Create a new tool.
        ToolDescription td = ToolFactory.eINSTANCE.createToolDescription();
        td.setName("TestTool");
        sectionToModify.getOwnedTools().add(td);
        group.eResource().save(Collections.emptyMap());

        TestsUtil.synchronizationWithUIThread();

        assertEquals("The tool named " + td.getName() + " should appear in the palette.", initialVisiblePaletteEntries + 1, getAllVisiblePaletteEntries(paletteRoot).size());

        // Modify the tool id/label
        td.setName("TestTool2");
        group.eResource().save(Collections.emptyMap());

        TestsUtil.synchronizationWithUIThread();

        // VP-3718, after VSM modifications, changed tools were not
        // updated/removed.
        assertEquals("The tool named " + td.getName() + " should replace the previous tool.", initialVisiblePaletteEntries + 1, getAllVisiblePaletteEntries(paletteRoot).size());

        // Remove a tool.
        sectionToModify.getOwnedTools().remove(td);
        group.eResource().save(Collections.emptyMap());

        TestsUtil.synchronizationWithUIThread();

        // VP-3718, after VSM modifications, deleted tools were not removed.
        assertEquals("The tool named " + td.getName() + " should disappear from the palette.", initialVisiblePaletteEntries, getAllVisiblePaletteEntries(paletteRoot).size());
    }

    private SectionPaletteDrawer getSectionEntry() {
        List<SectionPaletteDrawer> sectionPaletteDrawers = paletteRoot.getChildren().stream()
                .filter(SectionPaletteDrawer.class::isInstance)
                .map(SectionPaletteDrawer.class::cast)
                .filter(entry -> sectionToModify != null && sectionToModify.getName().equals(((SectionPaletteDrawer) entry).getLabel()))
                .toList();

        if (sectionPaletteDrawers.size() != 1) {
            throw new IllegalArgumentException("Palette should have only one section to modify.");
        }
        return sectionPaletteDrawers.get(0);
    }

    @Override
    protected String getRepresentationDescriptionInstanceName() {
        return REPRESENTATION_INSTANCE_NAME;
    }

    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_DESC_NAME;
    }
}

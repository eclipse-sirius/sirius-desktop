/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * A test ensuring that when the palette gets updated (e.g. when a filter has been installed and enables/disables
 * tools), the Palette tools that were shown before and are shown after are not re-created. If it is not the case,
 * maintaining 'Ctrl' while applying tools will not work as soon as palette gets updated.
 * 
 * <p>
 * Related issues :
 * <ul>
 * <li>VP-3600: Filter blocking tools on several uses of tools</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class PaletteUpdateDoesNotDeleteAllToolsTest extends AbstractPaletteManagerTest {

    private static final String ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED = "The palette entries should not have been deleted and then re-created: same instances should be used";

    static final String SEMANTIC_MODEL_FILENAME = "VP-3600.ecore";

    static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME;

    static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "VP-3600.odesign";

    static final String SESSION_MODEL_FILENAME = "VP-3600.aird";

    static final String SESSION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_MODEL_FILENAME;

    static final String REPRESENTATION_DESC_NAME = "BlankDiagram";

    private DDiagramEditorImpl editor;

    private PaletteRoot paletteRoot;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_MODEL_PATH);
        SessionUIManager.INSTANCE.createUISession(session);
    }

    /**
     * Opens a {@link DDiagramEditor} on the representation which description is equal to the given
     * representationDescriptionName.
     * 
     * @param representationDescriptionName
     *            the name of the {@link RepresentationDescription} of the representation to open
     */
    protected void doOpenEditorOnRepresentation(String representationDescriptionName) {
        // Open an editor on the tested diagram
        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(representationDescriptionName);
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getName().equals("new " + representationDescriptionName)) {
                dDiagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }
        editor = (DDiagramEditorImpl) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        paletteRoot = ((DiagramEditDomain) editor.getDiagramEditDomain()).getPaletteViewer().getPaletteRoot();
        diagram = (Diagram) Iterables.get(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, dDiagram), 0);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        // Closing the editor opened during setUp
        if (editor != null) {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
            editor = null;
            paletteRoot = null;
        }
        super.tearDown();
    }

    /**
     * Ensures that, when creating new elements through a creation tool, if the newly created elements triggers a filter
     * enablement/disablement (and hence tools are displayed/hidden on the palette), the tools previously displayed are
     * not re-created (the same tools instances should be used - this includes note attachement tools).
     * <p>
     * <b>Note:</b> this test is performed on a representation without layer
     * </p>
     */
    public void testPaletteUpdateOnFilterDisablementAndEnablementWithoutLayerTest() {
        doOpenEditorOnRepresentation("BlankDiagramWithoutLayer");
        doTestPaletteUpdateOnFilterDisablementAndEnablement();
    }

    /**
     * Ensures that, when creating new elements through a creation tool, if the newly created elements triggers a filter
     * enablement/disablement (and hence tools are displayed/hidden on the palette), the tools previously displayed are
     * not re-created (the same tools instances should be used - this includes note attachement tools).
     * <p>
     * <b>Note:</b> this test is performed on a representation with tool groups
     * </p>
     */
    public void testPaletteUpdateOnFilterDisablementAndEnablementWithToolGroupsTest() {
        doOpenEditorOnRepresentation("BlankDiagramWithToolGroups");
        doTestPaletteUpdateOnFilterDisablementAndEnablement();
    }

    /**
     * Ensures that, when creating new elements through a creation tool, if the newly created elements triggers a filter
     * enablement/disablement (and hence tools are displayed/hidden on the palette), the tools previously displayed are
     * not re-created (the same tools instances should be used - this includes note attachement tools).
     * <p>
     * <b>Note:</b> this test is performed on a representation with layers
     * </p>
     */
    public void testPaletteUpdateOnFilterDisablementAndEnablementTest() {
        doOpenEditorOnRepresentation(getRepresentationDescriptionName());
        doTestPaletteUpdateOnFilterDisablementAndEnablement();
    }

    /**
     * Ensures that, when creating new elements through a creation tool, if the newly created elements triggers a filter
     * enablement/disablement (and hence tools are displayed/hidden on the palette), the tools previously displayed are
     * not re-created (the same tools instances should be used - this includes note attachement tools).
     */
    private void doTestPaletteUpdateOnFilterDisablementAndEnablement() {
        Set<PaletteEntry> initialPaletteEntries = getAllVisiblePaletteEntries(paletteRoot);

        // Step 1: we apply a creation tool
        applyNodeCreationTool("createPackage", dDiagram, dDiagram);

        // this will disable the filter defined on the "Create EClass" tool,
        // that should appear on the tabbar
        Set<PaletteEntry> paletteEntriesAfterFilterDisablement = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("As the applied creation tool disabled a filter, a new Tool should be displayed in the palette", initialPaletteEntries.size() + 1, paletteEntriesAfterFilterDisablement.size());

        Set<PaletteEntry> differencesWithPreviousPalette = Sets.difference(paletteEntriesAfterFilterDisablement, initialPaletteEntries);
        // All other tools should have been re-used (an not re-created)
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED + differencesWithPreviousPalette, 1, differencesWithPreviousPalette.size());
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED, initialPaletteEntries.size() + 1, paletteEntriesAfterFilterDisablement.size());

        // Step 2: we apply the same creation tool a second time
        applyNodeCreationTool("createPackage", dDiagram, dDiagram);

        // this will disable the filter defined on the "Create EClass" tool,
        // that should appear on the tabbar
        Set<PaletteEntry> paletteEntriesAfterSecondCreation = getAllVisiblePaletteEntries(paletteRoot);
        differencesWithPreviousPalette = Sets.difference(paletteEntriesAfterSecondCreation, paletteEntriesAfterFilterDisablement);
        assertEquals("As the applied creation tool did not changed filters state, the palette should be the same", new LinkedHashSet<>(), differencesWithPreviousPalette);
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED, paletteEntriesAfterSecondCreation.size(), paletteEntriesAfterFilterDisablement.size());

        // Step 3: we delete all nodes
        for (DDiagramElement diagramElement : Sets.newLinkedHashSet(dDiagram.getOwnedDiagramElements())) {
            Command deleteDiagramElementCommand = getCommandFactory().buildDeleteFromDiagramCommand(diagramElement);
            session.getTransactionalEditingDomain().getCommandStack().execute(deleteDiagramElementCommand);
        }
        // this will enable the filter again, so the "Create EClass" tool should
        // disappear
        Set<PaletteEntry> paletteEntriesAfterDeletion = getAllVisiblePaletteEntries(paletteRoot);

        assertEquals("Deleting all elements should have re-enabled a filter, so the 'createClass' tool should not be displayed", initialPaletteEntries.size(), paletteEntriesAfterDeletion.size());

        // All other tools should have been re-used (an not re-created)
        differencesWithPreviousPalette = Sets.difference(paletteEntriesAfterDeletion, initialPaletteEntries);
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED, new LinkedHashSet<>(), differencesWithPreviousPalette);
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED, initialPaletteEntries.size(), paletteEntriesAfterDeletion.size());
    }

    /**
     * Tests that when undoing/redoing changes, the palette is correctly updated.
     * 
     * @throws Exception
     */
    public void testPaletteUpdateOnFilterDisablementAndEnablementWithUndoRedoTest() throws Exception {
        doOpenEditorOnRepresentation(getRepresentationDescriptionName());
        Set<PaletteEntry> initialPaletteEntries = getAllVisiblePaletteEntries(paletteRoot);

        // Step 1: we apply a creation tool
        applyNodeCreationTool("createPackage", dDiagram, dDiagram);

        // this will disable the filter defined on the "Create EClass" tool,
        // that should appear on the tabbar
        Set<PaletteEntry> paletteEntriesAfterFilterDisablement = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("As the applied creation tool disabled a filter, a new Tool should be displayed in the palette", initialPaletteEntries.size() + 1, paletteEntriesAfterFilterDisablement.size());

        Set<PaletteEntry> differencesWithPreviousPalette = Sets.difference(paletteEntriesAfterFilterDisablement, initialPaletteEntries);
        // All other tools should have been re-used (an not re-created)
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED + differencesWithPreviousPalette, 1, differencesWithPreviousPalette.size());
        assertEquals(ERROR_MESSAGE_WHEN_PALETTE_ELEMENTS_ARE_RE_CREATED, initialPaletteEntries.size() + 1, paletteEntriesAfterFilterDisablement.size());

        // Step 2: we undo the previous creation
        undo();

        // this will enable the filters defined on the "Create EClass" tool
        // and hence hide the "Create EClass" tool
        Set<PaletteEntry> paletteEntriesAfterUndo = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("After undo, we should be back to initial state", initialPaletteEntries, paletteEntriesAfterUndo);

        // Step 3: we redo the creation
        // As the "CreateEClass" tool has been disabled by undo and then
        // re-enable, it will be recreated
        redo();
        Set<PaletteEntry> paletteEntriesAfterRedo = getAllVisiblePaletteEntries(paletteRoot);
        SetView<PaletteEntry> differences = Sets.difference(paletteEntriesAfterFilterDisablement, paletteEntriesAfterRedo);
        assertEquals("After redo, we should be back to the post-creation state but the following differences were found : " + differences, 1, differences.size());
    }

    /**
     * Ensures that when the palette is refreshed and a new tool is available with the same name as a tool that was
     * previously available (but is now disabled), the palette contains the expected tools.
     * 
     * @throws Exception
     */
    public void testPaletteUpdateWhithEclusiveLayerActivationAndHideReveal() throws Exception {
        doOpenEditorOnRepresentation(getRepresentationDescriptionName());

        // Initial state: we activate the "ConflictingLayer", that defines a
        // tool with the same name and located in a section with the same name
        // as a tool from the default layer
        activateLayer(dDiagram, "ExclusiveLayer");
        updateTools(diagram);
        editor.getPaletteManager().update(dDiagram);

        Set<PaletteEntry> initialPaletteEntries = getAllVisiblePaletteEntries(paletteRoot);

        // Step 1: we apply a creation tool
        applyNodeCreationTool("createPackage", dDiagram, dDiagram);

        // this will disable the filters defined on the "Create EClass" tool
        // from the default layer but enable the filters on the "Create EClass"
        // tool from the "ConflictingLayer" layer
        Set<PaletteEntry> paletteEntriesAfterFilterDisablement = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("As the applied creation tool disabled a filter and enabled another one, the same number of tools should be displayed", initialPaletteEntries.size(),
                paletteEntriesAfterFilterDisablement.size());

        Set<PaletteEntry> differencesWithPreviousPalette = Sets.difference(paletteEntriesAfterFilterDisablement, initialPaletteEntries);
        assertEquals("As the applied creation tool disabled a filter and enabled another one, differences should exist between the 2 palette states" + differencesWithPreviousPalette, 1,
                differencesWithPreviousPalette.size());

        // Step 2: undo and hide the exclusive layer: the tool should not be
        // displayed
        undo();

        Set<PaletteEntry> afterHideLayer = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("Layer is hidden: tools should not be displayed", 1, Sets.difference(initialPaletteEntries, afterHideLayer).size());

        redo();
        // Step 3: reveal layer: the tool should be displayed
        Set<PaletteEntry> afterReveal = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("Layer is hidden: tools should be displayed", 1, Sets.difference(afterReveal, afterHideLayer).size());
    }

    /**
     * Tests the palette behavior when it's refreshed and several tools with the same name are available under the same
     * categories.
     * 
     * @throws Exception
     */
    public void testPaletteUpdateWhitConflictingLayerActivation() throws Exception {
        doOpenEditorOnRepresentation(getRepresentationDescriptionName());

        // Initial state: we activate the "ConflictingLayer", that defines a
        // tool with the same name and located in a section with the same name
        // as a tool from the default layer
        activateLayer(dDiagram, "ConflictingLayer");

        updateTools(diagram);
        editor.getPaletteManager().update(dDiagram);

        Set<PaletteEntry> initialPaletteEntries = getAllVisiblePaletteEntries(paletteRoot);

        // Step 1: we apply a creation tool
        applyNodeCreationTool("createPackage", dDiagram, dDiagram);

        Set<PaletteEntry> paletteEntriesAfterFilterDisablement = getAllVisiblePaletteEntries(paletteRoot);
        Set<PaletteEntry> differencesWithPreviousPalette = Sets.difference(paletteEntriesAfterFilterDisablement, initialPaletteEntries);
        assertEquals("Both new tools should be displayed in the palette" + differencesWithPreviousPalette, 2, differencesWithPreviousPalette.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_DESC_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionInstanceName() {
        return "new " + REPRESENTATION_DESC_NAME;
    }

}

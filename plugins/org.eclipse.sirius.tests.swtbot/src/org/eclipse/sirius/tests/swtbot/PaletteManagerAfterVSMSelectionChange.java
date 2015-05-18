/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.Arrays;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SectionPaletteDrawer;
import org.eclipse.sirius.diagram.ui.tools.internal.palette.SiriusPaletteViewer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ExportAsImageHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Tests palette update after viewpoint selection change.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class PaletteManagerAfterVSMSelectionChange extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/tools/palette/extension/";

    private static final String FILE_DIR = "/";

    private static final String MODEL_NAME = "My.basicfamily";

    private static final String MODELER_NAME = "family.odesign";

    private static final String MODELER_EXTENSION_NAME = "extended.odesign";

    private static final String SESSION_NAME = "representations.aird";

    private static final String REPRESENTATION_DESC_NAME = "personsDiagram";

    private static final String REPRESENTATION_INST_DESC_NAME = "new personsDiagram";

    private static final String EXTENSION_VIEWPOINT_NAME = "others";

    private static final String PERSON_LAYER_1 = "Person_Layer_1";

    private static final String OTHERS_LAYER_1 = "Others_Layer_1";

    private static final SortedSet<String> EXPECTED_ENTRIES_LAYER_PERSON1_SHOWN = Sets.newTreeSet(Arrays.asList("createMan_1"));

    private static final SortedSet<String> EXPECTED_ENTRIES_LAYER_PERSON1_OTHERS1_SHOWN = Sets.newTreeSet(Arrays.asList("createMan_1", "createWoman_1"));

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR, MODEL_NAME, SESSION_NAME, MODELER_NAME, MODELER_EXTENSION_NAME);
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        localSession.changeViewpointSelection(Sets.newHashSet(EXTENSION_VIEWPOINT_NAME), Sets.<String> newHashSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        localSession = null;
        super.tearDown();
        new ExportAsImageHelper().resetDialogSettings();
    }

    /**
     * Ensure that only selected viewpoint tools still available from the
     * palette after a viewpoint selection change.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testHideShowLayersAfterDiagramCreation() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESC_NAME, REPRESENTATION_INST_DESC_NAME, DDiagram.class);
        editor.changeLayerActivation(PERSON_LAYER_1);
        editor.changeLayerActivation(OTHERS_LAYER_1);
        assertEquals("Palette entries content is wrong", EXPECTED_ENTRIES_LAYER_PERSON1_OTHERS1_SHOWN, getVisiblePaletteEntries());
        localSession.changeViewpointSelection(Sets.<String> newHashSet(), Collections.singleton(EXTENSION_VIEWPOINT_NAME));
        assertEquals("Palette entries content is wrong", EXPECTED_ENTRIES_LAYER_PERSON1_SHOWN, getVisiblePaletteEntries());
    }

    private TreeSet<String> getVisiblePaletteEntries() {
        Iterable<SectionPaletteDrawer> filtered = Iterables.filter(((SiriusPaletteViewer) editor.getSiriusPaletteGroupEditPartBot().part().getViewer()).getPaletteRoot().getChildren(),
                SectionPaletteDrawer.class);
        TreeSet<String> result = Sets.newTreeSet();
        for (PaletteEntry paletteEntry : filtered) {
            Iterable<PaletteEntry> visibleEntries = Iterables.filter(((SectionPaletteDrawer) paletteEntry).getChildren(), VISIBLE_ENTRY);
            for (PaletteEntry visiblePaletteEntry : visibleEntries) {
                result.add(visiblePaletteEntry.getLabel());
            }
        }
        return result;
    }

    private static final Predicate<PaletteEntry> VISIBLE_ENTRY = new Predicate<PaletteEntry>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(PaletteEntry input) {
            return input.isVisible();
        }
    };

}

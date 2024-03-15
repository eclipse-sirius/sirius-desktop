/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.style;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IEditorPart;

/**
 * Test class to check the behavior of {@link ColorCategoryManager} and {@link ColorCategoryManagerProvider}
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class ColorCategoryManagerTest extends SiriusDiagramTestCase {

    private static final String FILL_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getFillStyle_FillColor());

    private static final String LINE_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getLineStyle_LineColor());

    private static final String FONT_COLOR_PROPERTY_ID = PackageUtil.getID(NotationPackage.eINSTANCE.getFontStyle_FontColor());

    private static final RGB[] BASIC_COLORS = { new RGB(0, 0, 0), new RGB(209, 209, 209), new RGB(255, 255, 255), new RGB(239, 41, 41), new RGB(252, 175, 62), new RGB(252, 233, 79),
            new RGB(138, 226, 52), new RGB(102, 204, 255), new RGB(0, 51, 128), new RGB(128, 0, 128) };

    private static final RGB[] RAINBOW = { new RGB(156, 12, 12), new RGB(239, 41, 41), new RGB(246, 139, 139), new RGB(154, 103, 23), new RGB(233, 185, 110), new RGB(238, 201, 142),
            new RGB(224, 133, 3), new RGB(252, 175, 62), new RGB(253, 206, 137), new RGB(214, 197, 66), new RGB(252, 233, 79), new RGB(255, 245, 181), new RGB(77, 137, 20), new RGB(138, 226, 52),
            new RGB(204, 242, 166), new RGB(39, 76, 114), new RGB(114, 159, 207), new RGB(194, 239, 255), new RGB(114, 73, 110), new RGB(173, 127, 168), new RGB(217, 196, 215) };

    private static final RGB[] VSM_COLORS = { new RGB(252, 82, 82), new RGB(47, 198, 255), new RGB(49, 217, 0), new RGB(96, 96, 96), new RGB(180, 180, 180), new RGB(255, 134, 13),
            new RGB(145, 56, 241), new RGB(60, 255, 250), new RGB(255, 66, 255), new RGB(255, 255, 0), new RGB(128, 64, 0), new RGB(0, 0, 0), new RGB(255, 255, 255), new RGB(247, 51, 85),
            new RGB(0, 202, 101) };

    private static final String PATH = "/data/unit/colors/github-152/";

    private static final String MODELER_RESOURCE_NAME = "github-152.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "github-152.ecore";

    private static final String SESSION_RESOURCE_NAME = "github-152.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, MODELER_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME),
                TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#getBasicColors()} for all
     * propertyId.
     */
    public void testGetBasicColors() {
        checkGetBasicColors(FILL_COLOR_PROPERTY_ID);
        checkGetBasicColors(FONT_COLOR_PROPERTY_ID);
        checkGetBasicColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkGetBasicColors(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        List<RGB> basicColors = colorCategoryManager.getBasicColors();
        assertArrayEquals(BASIC_COLORS, basicColors.toArray());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#getSuggestedColors()} for all
     * propertyId.
     */
    public void testGetSuggestedColors() {
        checkGetSuggestedColors(FILL_COLOR_PROPERTY_ID);
        checkGetSuggestedColors(FONT_COLOR_PROPERTY_ID);
        checkGetSuggestedColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkGetSuggestedColors(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        List<RGB> suggestedColors = colorCategoryManager.getSuggestedColors();
        assertTrue(suggestedColors.isEmpty());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#getCustomColors()} for all
     * propertyId.
     */
    public void testGetCustomColors() {
        checkGetCustomColors(FILL_COLOR_PROPERTY_ID);
        checkGetCustomColors(FONT_COLOR_PROPERTY_ID);
        checkGetCustomColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkGetCustomColors(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        List<RGB> customColors = colorCategoryManager.getCustomColors();
        assertTrue(customColors.isEmpty());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#getLastUsedColors()} for all
     * propertyId.
     */
    public void testGetLastUsedColors() {
        checkGetLastUsedColors(FILL_COLOR_PROPERTY_ID);
        checkGetLastUsedColors(FONT_COLOR_PROPERTY_ID);
        checkGetLastUsedColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkGetLastUsedColors(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        List<RGB> lastUsedColors = colorCategoryManager.getLastUsedColors();
        assertTrue(lastUsedColors.isEmpty());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#addLastUsedColor(RGB)} for all
     * propertyId.
     */
    public void testAddLastUsedColors() {
        checkAddLastUsedColors(FILL_COLOR_PROPERTY_ID);
        checkAddLastUsedColors(FONT_COLOR_PROPERTY_ID);
        checkAddLastUsedColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkAddLastUsedColors(String propertyId) {
        RGB black = new RGB(0, 0, 0);
        RGB white = new RGB(255, 255, 255);

        // Adding a color.
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        colorCategoryManager.addLastUsedColor(black);
        List<RGB> lastUsedColors = colorCategoryManager.getLastUsedColors();
        assertArrayEquals(new RGB[] { black }, lastUsedColors.toArray());

        // Adding another color.
        colorCategoryManager.addLastUsedColor(white);
        lastUsedColors = colorCategoryManager.getLastUsedColors();
        assertArrayEquals(new RGB[] { white, black }, lastUsedColors.toArray());

        // Adding an existing color.
        colorCategoryManager.addLastUsedColor(black);
        lastUsedColors = colorCategoryManager.getLastUsedColors();
        assertArrayEquals(new RGB[] { black, white }, lastUsedColors.toArray());

        // Adding a null color.
        colorCategoryManager.addLastUsedColor(null);
        lastUsedColors = colorCategoryManager.getLastUsedColors();
        assertArrayEquals(new RGB[] { black, white }, lastUsedColors.toArray());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#setCustomColors(List)} for all
     * propertyId.
     */
    public void testSetCustomColors() {
        checkSetCustomColors(FILL_COLOR_PROPERTY_ID);
        checkSetCustomColors(FONT_COLOR_PROPERTY_ID);
        checkSetCustomColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkSetCustomColors(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        // Set a list of colors.
        colorCategoryManager.setCustomColors(Arrays.asList(RAINBOW));
        assertArrayEquals(RAINBOW, colorCategoryManager.getCustomColors().toArray());

        // Set a null list.
        colorCategoryManager.setCustomColors(null);
        assertArrayEquals(RAINBOW, colorCategoryManager.getCustomColors().toArray());

        // Set empty list.
        colorCategoryManager.setCustomColors(Collections.EMPTY_LIST);
        assertTrue(colorCategoryManager.getCustomColors().isEmpty());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#setSuggestedColors(List)} for all
     * propertyId.
     */
    public void testSetSuggestedColors() {
        checkSetSuggestedColors(FILL_COLOR_PROPERTY_ID);
        checkSetSuggestedColors(FONT_COLOR_PROPERTY_ID);
        checkSetSuggestedColors(LINE_COLOR_PROPERTY_ID);
    }

    private void checkSetSuggestedColors(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        RGB[] newSuggestedColors = { VSM_COLORS[0], VSM_COLORS[1] };
        colorCategoryManager.setSuggestedColors(Arrays.asList(newSuggestedColors));
        assertEquals(2, colorCategoryManager.getSuggestedColors().size());
        assertArrayEquals(newSuggestedColors, colorCategoryManager.getSuggestedColors().toArray());

        // Set more than 10 elements is not possible: the ten first element are added to the "Suggested" colors.
        colorCategoryManager.setSuggestedColors(Arrays.asList(VSM_COLORS));
        List<RGB> subVSMColors = Arrays.asList(VSM_COLORS).subList(0, 10);
        assertEquals(10, colorCategoryManager.getSuggestedColors().size());
        assertArrayEquals(subVSMColors.toArray(), colorCategoryManager.getSuggestedColors().toArray());

        // Check null parameter has no effect
        colorCategoryManager.setSuggestedColors(null);
        assertEquals(10, colorCategoryManager.getSuggestedColors().size());
        assertArrayEquals(subVSMColors.toArray(), colorCategoryManager.getSuggestedColors().toArray());

        colorCategoryManager.setSuggestedColors(Collections.EMPTY_LIST);
        assertTrue(colorCategoryManager.getSuggestedColors().isEmpty());
    }

    /**
     * Test {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager#getSelectedColorsByPropertyId()}
     * for all propertyId.
     */
    public void testGetSelectedColorsByPropertyId() {
        checkGetSelectedColorsByPropertyId(FILL_COLOR_PROPERTY_ID);
        checkGetSelectedColorsByPropertyId(FONT_COLOR_PROPERTY_ID);
        checkGetSelectedColorsByPropertyId(LINE_COLOR_PROPERTY_ID);
    }

    private void checkGetSelectedColorsByPropertyId(String propertyId) {
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, Collections.EMPTY_LIST, propertyId);
        assertTrue(colorCategoryManager.getSelectedColorsByPropertyId().isEmpty());

        colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, null, propertyId);
        assertTrue(colorCategoryManager.getSelectedColorsByPropertyId().isEmpty());

        List<IGraphicalEditPart> editParts = List.of(getFirstEditPart());
        colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, editParts, propertyId);
        assertFalse(colorCategoryManager.getSelectedColorsByPropertyId().isEmpty());
    }

    private IGraphicalEditPart getFirstEditPart() {
        final EPackage root = (EPackage) semanticModel;
        final DDiagram rootdiagram = (DDiagram) getRepresentations("github-152", root).iterator().next();
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, rootdiagram, new NullProgressMonitor());
        assertTrue("Editor is not of the right type.", editor instanceof DDiagramEditorImpl);
        DDiagramEditorImpl diagramEditor = (DDiagramEditorImpl) editor;
        diagramEditor.setFocus();
        DDiagramElement element = rootdiagram.getDiagramElements().get(0);
        IGraphicalEditPart editPart = getEditPart(element);
        return editPart;
    }

    /**
     * Test
     * {@link org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManagerProvider#getColorCategoryManager(org.eclipse.sirius.business.api.session.Session, List, String)}
     * for all propertyId.
     */
    public void testGetColorCategoryManager() {
        checkGetColorCategoryManager(FILL_COLOR_PROPERTY_ID);
        checkGetColorCategoryManager(FONT_COLOR_PROPERTY_ID);
        checkGetColorCategoryManager(LINE_COLOR_PROPERTY_ID);
    }

    private void checkGetColorCategoryManager(String propertyId) {
        List<IGraphicalEditPart> editParts = List.of(getFirstEditPart());
        ColorCategoryManager colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(null, editParts, propertyId);
        assertNull(colorCategoryManager);

        colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, editParts, null);
        assertNull(colorCategoryManager);

        colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, null, propertyId);
        assertNotNull(colorCategoryManager);

        colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, editParts, "");
        assertNull(colorCategoryManager);
    }
}

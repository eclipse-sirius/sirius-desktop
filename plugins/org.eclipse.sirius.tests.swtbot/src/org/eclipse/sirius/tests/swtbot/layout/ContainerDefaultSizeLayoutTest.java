/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests that the VSM width and height computation expressions on container are
 * correctly applied. (see VP-3018 for more details)
 * 
 * @author fbarbin
 */
public class ContainerDefaultSizeLayoutTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_NAME = "vp3018";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "vp3018";

    private static final String MODEL = "My.ecore";

    private static final String VSM = "My.odesign";

    private static final String SESSION_FILE = "representations.aird";

    private static final String DATA_UNIT_DIR = "data/unit/layout/VP-3018/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Tests that the specified default size for shape is correctly applied when
     * a view is created due to an external change of the semantic resource.
     * <ul>
     * <li>Open the representation</li>
     * <li>Create a new package in semantic resource (Corresponding to a shape
     * style in VSM)</li>
     * <li>Check that the size is correct after refresh</li>
     * <li>Arrange all</li>
     * <li>Check that auto-size is correctly applied</li>
     * </ul>
     */
    public void testDefaultSizeWithExternalCreationOnShapeWithPrefAutoSize() {
        testDefaultSizeWithExternalCreationOnShape(true);
    }

    /**
     * Tests that the specified default size for shape is correctly applied when a view is created due to an external
     * change of the semantic resource.
     * <ul>
     * <li>Open the representation</li>
     * <li>Create a new package in semantic resource (Corresponding to a shape style in VSM)</li>
     * <li>Check that the size is correct after refresh</li>
     * <li>Arrange all</li>
     * <li>Check that auto-size is correctly applied</li>
     * </ul>
     */
    public void testDefaultSizeWithExternalCreationOnShapeWithoutPrefAutosize() {
        testDefaultSizeWithExternalCreationOnShape(false);
    }

    /**
     * Tests that the specified default size for shape is correctly applied when a view is created due to an external
     * change of the semantic resource.
     * <ul>
     * <li>Open the representation</li>
     * <li>Create a new package in semantic resource (Corresponding to a shape style in VSM)</li>
     * <li>Check that the size is correct after refresh</li>
     * <li>Arrange all</li>
     * <li>Check that auto-size is correctly applied</li>
     * </ul>
     * 
     * @param autosize
     *            true if the preference "" value must be true, false otherwise.
     */
    protected void testDefaultSizeWithExternalCreationOnShape(boolean autosize) {
        // Change preference to disable the auto size at arrange.
        changeDiagramUIPreference(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTOSIZE_ON_ARRANGE.name(), autosize);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class, true, true);
        EPackage root = loadRootPackage();
        createNewPackage(root, "Pkg1");

        SWTBotUtils.waitAllUiEvents();

        // Launching manual refresh
        editor.click(0, 0);
        editor.save();
        manualRefresh();
        // SWTBotUtils.waitAllUiEvents();
        checkPkgBounds("Pkg1", false);

        arrangeAll();

        SWTBotUtils.waitAllUiEvents();

        if (autosize) {
            checkGMFAutoSize("Pkg1");
        }
    }
    /**
     * Tests that the specified default size for shape is correctly applied when
     * using a creation tool.
     * <ul>
     * <li>Open the representation</li>
     * <li>Create a new package with the creating tool(Corresponding to a shape
     * style in VSM)</li>
     * <li>Check that the size is correct</li>
     * <li>Arrange all</li>
     * <li>Check that auto-size is correctly applied</li>
     * </ul>
     */
    public void testDefaultSizeWithToolCreationOnShape() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class, true, true);
        // EPackage root = loadRootPackage();
        // createNewPackage(root, "Pkg1");

        editor.activateTool("Create pkg");
        editor.click(new Point(100, 100));

        SWTBotUtils.waitAllUiEvents();

        // SWTBotUtils.waitProgressMonitorClose("Progress Information");
        // SWTBotUtils.waitAllUiEvents();
        checkPkgBounds("pkg", false);

        arrangeAll();

        SWTBotUtils.waitAllUiEvents();

        checkGMFAutoSize("pkg");
    }

    /**
     * Tests that the specified default size for flat is correctly applied when
     * using a creation tool.
     * <ul>
     * <li>Open the representation</li>
     * <li>Create a new eenum with the creating tool(Corresponding to a Flat
     * style in VSM)</li>
     * <li>Check that the size is correct</li>
     * <li>Arrange all</li>
     * <li>Check that auto-size is correctly applied</li>
     * </ul>
     */
    public void testDefaultSizeWithToolCreationOnFlat() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class, true, true);

        editor.activateTool("Create eenum");
        editor.click(new Point(100, 100));

        SWTBotUtils.waitAllUiEvents();

        checkEEnumBounds("eenum");

        arrangeAll();

        SWTBotUtils.waitAllUiEvents();

        checkGMFAutoSize("eenum");
    }

    /**
     * Tests that the specified default size for flat is correctly applied when
     * a view is created due to an external change of the semantic resource.
     * <ul>
     * <li>Open the representation</li>
     * <li>Create a new eenum in semantic resource (Corresponding to a Flat
     * style in VSM)</li>
     * <li>Check that the size is correct after refresh</li>
     * <li>Arrange all</li>
     * <li>Check that auto-size is correctly applied</li>
     * </ul>
     */
    public void testDefaultSizeWithExternalCreationOnFlat() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class, true, true);
        EPackage root = loadRootPackage();
        createNewEEnum(root, "eenum1");

        SWTBotUtils.waitAllUiEvents();

        // Launching manual refresh
        editor.click(0, 0);
        editor.save();
        manualRefresh();
        // SWTBotUtils.waitAllUiEvents();
        checkEEnumBounds("eenum1");

        arrangeAll();

        SWTBotUtils.waitAllUiEvents();

        checkGMFAutoSize("eenum1");
    }

    private void checkGMFAutoSize(String name) {
        SWTBotGefEditPart bot = editor.getEditPart(name, AbstractDiagramElementContainerEditPart.class);
        // check gmf auto-size
        Bounds bounds = getGMFBounds(bot);
        assertEquals("Unexpected gmf width for " + name, -1, bounds.getWidth());
        assertEquals("Unexpected gmf height for " + name, -1, bounds.getHeight());
    }

    private void createNewEEnum(final EPackage root, final String name) {
        EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
        eEnum.setName(name);
        root.getEClassifiers().add(eEnum);

        try {
            root.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
    }

    private void checkPkgBounds(String name, boolean autosize) {
        SWTBotGefEditPart bot = editor.getEditPart(name, AbstractDiagramContainerEditPart.class);
        // check gmf size
        Bounds bounds = getGMFBounds(bot);
        if (autosize) {
            assertEquals("Unexpected gmf width for " + name, -1, bounds.getWidth());
            assertEquals("Unexpected gmf height for " + name, -1, bounds.getHeight());
        } else {
            assertEquals("Unexpected gmf width for " + name, 50, bounds.getWidth());
            assertEquals("Unexpected gmf height for " + name, 30, bounds.getHeight());
        }

        // check draw2D size
        Dimension dimension = editor.getBounds(bot).getSize();
        Dimension expectedDimension = new Dimension(50, 30);
        assertEquals("Unexpected size for " + name, expectedDimension, dimension);

    }

    private Bounds getGMFBounds(SWTBotGefEditPart bot) {
        EditPart editPart = bot.part();
        assertTrue("Wrong expected edit part type", editPart instanceof IGraphicalEditPart);
        View view = ((IGraphicalEditPart) editPart).getNotationView();
        assertTrue("Wrong expected gmf view type", view instanceof Node);
        LayoutConstraint constraint = ((Node) view).getLayoutConstraint();
        assertTrue("Wrong expected LayoutConstraint type", constraint instanceof Bounds);
        return (Bounds) constraint;
    }

    private void checkEEnumBounds(String name) {
        SWTBotGefEditPart bot = editor.getEditPart(name, AbstractDiagramElementContainerEditPart.class);

        // check gmf size
        Bounds bounds = getGMFBounds(bot);
        assertEquals("Unexpected gmf width for " + name, -1, bounds.getWidth());
        assertEquals("Unexpected gmf height for " + name, 70, bounds.getHeight());

        // check draw2D size
        Dimension dimension = editor.getBounds(bot).getSize();
        // The width is unpredictable since it depends of label size
        Dimension expectedDimension = new Dimension(dimension.width, 70);
        assertEquals("Unexpected draw2D size for " + name, expectedDimension, dimension);
    }

    private EPackage loadRootPackage() {
        ResourceSet set = new ResourceSetImpl();
        try {
            final EPackage ePackageInAnotherResourceSet = (EPackage) ModelUtils.load(localSession.getOpenedSession().getSemanticResources().iterator().next().getURI(), set);
            return ePackageInAnotherResourceSet;
        } catch (IOException e) {
            fail("Pb when loading the resource in another resourceSet : " + e.getMessage());
        }
        return null;
    }

    private void createNewPackage(final EPackage root, final String name) {
        EPackage newPackage = EcoreFactory.eINSTANCE.createEPackage();
        newPackage.setName(name);
        root.getESubpackages().add(newPackage);
        try {
            root.eResource().save(Collections.EMPTY_MAP);
        } catch (IOException e) {
            fail("Pb when saving the resource in another resourceSet : " + e.getMessage());
        }
    }
}

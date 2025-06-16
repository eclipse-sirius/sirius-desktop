/*******************************************************************************
 * Copyright (c) 2018, 2023 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;

/**
 * Refresh diagram background color tests for Entities diagram of ecore modeler.
 * 
 * WARNING: The test assumes the default background color is pure white, but this can actually depend on the current Eclipse theme (e.g. when using the Dark theme);
 * 
 * @author jmallet
 */
public class EntitiesDiagramBackgroundTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID
        + "/data/unit/modelers/ecore/directEdit/testOperation.ecore"; //$NON-NLS-1$

    private static final String REPRESENTATIONS_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID
        + "/data/unit/modelers/ecore/directEdit/testOperation.aird"; //$NON-NLS-1$

    private DDiagram diagram;

    IEditorPart diagramEditor;

    private DDiagramEditPart dDiagramEditPart;

    /** White color */
    private final Color WHITE_COLOR = new Color(null, 255, 255, 255);

    /** Green color */
    private final Color GREEN_COLOR = new Color(null, 0, 255, 0);

    /** Red color */
    private final Color RED_COLOR = new Color(null, 255, 0, 0);

    /** Intense blue color */
    private final Color INTENSE_BLUE_COLOR = new Color(null, 0, 255, 255);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_PATH);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, semanticModel).toArray()[0];
        diagramEditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        dDiagramEditPart = getDiagramEditPart();

        // check diagram description has none color defined in background
        ColorDescription initBackgroundColor = diagram.getDescription().getBackgroundColor();
        assertNull("None color is set for diagram background.", initBackgroundColor); //$NON-NLS-1$
        // check background diagram color is white by default
        checkBackgroundColor(WHITE_COLOR);
    }

    /**
     * Tests that when the background color of the diagram description change with fixed color, the background color of the diagram
     * is also changed.
     */
    public void testDiagramBackgroundColorWithFixedColor() {
        // update background color with red color
        FixedColor redColor = createFixedColor(RED_COLOR);
        setDescriptionBackground(redColor);
        dDiagramEditPart.refresh();

        checkBackgroundColor(RED_COLOR);

        // update background color with green color
        FixedColor greenColor = createFixedColor(GREEN_COLOR);
        setDescriptionBackground(greenColor);
        dDiagramEditPart.refresh();

        checkBackgroundColor(GREEN_COLOR);

        // update background color with white color
        FixedColor whiteColor = createFixedColor(WHITE_COLOR);
        setDescriptionBackground(whiteColor);
        dDiagramEditPart.refresh();

        checkBackgroundColor(WHITE_COLOR);
    }

    /**
     * Tests that when the background color of the diagram description change with computedColor, the background color of the diagram
     * is also changed.
     * If rootPackage name begins with "Green", the background color should be green.
     */
    public void testDiagramBackgroundColorWithComputedColor() {
        // update background color
        ComputedColor computedColor = createComputedColor();
        setDescriptionBackground(computedColor);
        dDiagramEditPart.refresh();

        checkBackgroundColor(RED_COLOR);

        // change root Package Name
        EPackage semanticRoot = (EPackage) semanticModel;
        updatePackageName(semanticRoot, "GreenPackage"); //$NON-NLS-1$
        dDiagramEditPart.refresh();

        checkBackgroundColor(GREEN_COLOR);
    }

    /**
     * Tests that when the background color of the diagram description change with a computedColor using diagram color,
     * the background color of the diagram is also changed. The background color changes according to the number of
     * classes contained in the package (from white, 0 class, to intense blue, 16 classes or more).
     */
    public void testDiagramBackgroundColorWithComputedColorUsingDiagramVariable() {
        // update background color
        ComputedColor computedColor = createComputedColorUsingDiagramVariable();
        setDescriptionBackground(computedColor);
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        EPackage semanticRoot = ((EPackage) semanticModel).getESubpackages().get(0);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, semanticRoot).toArray()[0];
        diagramEditor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        dDiagramEditPart = getDiagramEditPart();
        // White color is expected as the package contains no class, so the computed color should be {255, 255, 255}.
        checkBackgroundColor(WHITE_COLOR);

        // Add 1 class in package
        addClassesInPackage(semanticRoot, 1); // $NON-NLS-1$
        refresh(diagram, true);
        dDiagramEditPart.refresh();
        // As soon as the package contains at least one class, the color should be different than White.
        checkBackgroundNotColor(WHITE_COLOR);

        // Add 15 classes in package
        addClassesInPackage(semanticRoot, 15); // $NON-NLS-1$
        refresh(diagram, true);
        dDiagramEditPart.refresh();
        // From 16 to more classes, the color is INTENSE_BLUE_COLOR
        checkBackgroundColor(INTENSE_BLUE_COLOR);
    }


    /**
     * Create {@link ComputedColor} with color depending on the number of classes contained in the package (from white,
     * 0 class, to intense blue, 16 classes or more).
     * 
     * @return the new computed color.
     */
    private ComputedColor createComputedColorUsingDiagramVariable() {
        ComputedColor color = DescriptionFactory.eINSTANCE.createComputedColor();
        color.setName("myComputedColor"); //$NON-NLS-1$
        color.setRed("aql:255-diagram.ownedDiagramElements->size()*16"); //$NON-NLS-1$
        color.setGreen("aql:255"); //$NON-NLS-1$
        color.setBlue("aql:255"); //$NON-NLS-1$
        return color;
    }

    /**
     * Get {@link DDiagramEditPart} from the diagram editor.
     * 
     * @return {@link DDiagramEditPart} from the diagram editor.
     */
    private DDiagramEditPart getDiagramEditPart() {
        DDiagramEditPart dDiagramEditPart = null;
        if (diagramEditor instanceof DiagramEditor) {
            DiagramEditPart diagramEditPart = ((DiagramEditor) diagramEditor).getDiagramEditPart();
            if (diagramEditPart instanceof DDiagramEditPart) {
                dDiagramEditPart = (DDiagramEditPart) diagramEditPart;
            }
        }
        return dDiagramEditPart;
    }

    /**
     * Update background color of the diagram from a given {@link ColorDescription}.
     * 
     * @param colorUpdate
     *            the new color to set on the background
     */
    private void setDescriptionBackground(final ColorDescription newColor) {
        session.getTransactionalEditingDomain().getCommandStack()
                .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    /** {@inheritDoc} */
                    @Override
                    protected void doExecute() {
                        diagram.getDescription().setBackgroundColor(newColor);
                    }
                });
    }

    /**
     * Create fixed color from RGB values.
     * 
     * @param colorSample
     *            color used to create fixed color
     * @return fixed color from RGB values.
     */
    private FixedColor createFixedColor(Color colorSample) {
        UserFixedColor color = DescriptionFactory.eINSTANCE.createUserFixedColor();
        color.setName("myFixedColor"); //$NON-NLS-1$
        color.setRed(colorSample.getRed());
        color.setGreen(colorSample.getGreen());
        color.setBlue(colorSample.getBlue());
        return color;
    }

    /**
     * Create {@link ComputedColor} with color depending on root package name.
     * 
     * @return the new computed color.
     */
    private ComputedColor createComputedColor() {
        ComputedColor color = DescriptionFactory.eINSTANCE.createComputedColor();
        color.setName("myComputedColor"); //$NON-NLS-1$
        color.setRed("aql:if self.name.startsWith('ecore') then 255 else 0 endif"); //$NON-NLS-1$
        color.setGreen("aql:if self.name.startsWith('Green') then 255 else 0 endif"); //$NON-NLS-1$
        color.setBlue("aql:if self.name.startsWith('Blue') then 255 else 0 endif"); //$NON-NLS-1$
        return color;
    }

    /**
     * Update the package name of the model with the given name.
     * 
     * @param myPackage
     *            package to update
     * @param newName
     *            new name of the package
     */
    private void updatePackageName(final EPackage myPackage, final String newName) {
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                myPackage.setName(newName);
            }

        };
        executeCommand(cmd);
    }

    private void addClassesInPackage(EPackage myPackage, int nbClassesToAdd) {
        Command cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                for (int i = 0; i < nbClassesToAdd; i++) {
                    myPackage.getEClassifiers().add(EcoreFactory.eINSTANCE.createEClass());
                }
            }
        };
        executeCommand(cmd);
    }

    /**
     * Check if the diagram color has been updated.
     * 
     * @param updateColor
     *            the new color of the diagram background
     */
    private void checkBackgroundColor(Color updateColor) {
        Color backgroundColorEditPart = dDiagramEditPart.getFigure().getBackgroundColor();
        assertEquals("The diagram background color has not been updated.", updateColor, backgroundColorEditPart); //$NON-NLS-1$
    }

    /**
     * Check if the diagram color has been updated.
     * 
     * @param updateColor
     *            the color that must not be the diagram background
     */
    private void checkBackgroundNotColor(Color updateColor) {
        Color backgroundColorEditPart = dDiagramEditPart.getFigure().getBackgroundColor();
        if (updateColor.equals(backgroundColorEditPart)) {
            fail("The diagram background color has not been updated, expected something different than " + updateColor); //$NON-NLS-1$
        }
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}

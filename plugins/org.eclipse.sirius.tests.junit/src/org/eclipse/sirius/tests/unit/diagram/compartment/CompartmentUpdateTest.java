/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.compartment;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * <p>
 * Verify update of layout, after adding a region, for a region container with:
 * <ul>
 * <li>width or height equals to -1,</li>
 * <li>width and height equals to -1,</li>
 * <li>width and height different from -1.</li>
 * </p>
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy MALLET</a>
 * 
 */
public class CompartmentUpdateTest extends SiriusDiagramTestCase {

    protected static final String FOLDER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/compartments/updateSize/";

    private static final String DEFAULT_SEMANTIC_MODEL_PATH = FOLDER_PATH + "TestRegionContainerSize.ecore";

    private static final String DEFAULT_SESSION_FILE_PATH = FOLDER_PATH + "representations.aird";

    private static final String DEFAULT_MODELER_PATH = FOLDER_PATH + "compartments.odesign";

    protected static final String REPRESENTATION_DECRIPTION_NAME = "DiagWithStack";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(DEFAULT_SEMANTIC_MODEL_PATH, DEFAULT_MODELER_PATH, DEFAULT_SESSION_FILE_PATH);
        assertEquals("Just one representation must be present in session", 4, getRepresentations(REPRESENTATION_DECRIPTION_NAME).size());
    }

    /**
     * Test creation of compartment in region container with width fixed and height equals to -1 is done without any
     * problem of update.
     */
    public void testAddCompartmentInRegionContainerWithWidthFixed() {
        testAddCompartmentInRegionContainer("DiagRegionContainerWidthFixed");
    }

    /**
     * Test creation of compartment in region container with height fixed and width equals to -1 is done without any
     * problem of update.
     */
    public void testAddCompartmentInRegionContainerWithHeightFixed() {
        testAddCompartmentInRegionContainer("DiagRegionContainerHeightFixed");
    }

    /**
     * Test creation of compartment in region container with height and width fixed is done without any problem of
     * update.
     */
    public void testAddCompartmentInRegionContainerWithWidthAndHeightFixed() {
        testAddCompartmentInRegionContainer("DiagRegionContainerWidthAndHeightFixed");
    }

    /**
     * Test creation of compartment in region container with height and width equals to -1 is done without any problem
     * of update.
     */
    public void testAddCompartmentInRegionContainerWithoutWidthOrHeightFixed() {
        testAddCompartmentInRegionContainer("DiagRegionContainerWithoutWidthAndHeightFixed");
    }

    /**
     * Create a compartment into P3 region container and check its creation. If the new compartment exists, none update
     * problem have been reached.
     * 
     * @param representationName
     *            name of the representation to modify.
     */
    private void testAddCompartmentInRegionContainer(String representationName) {
        DDiagram diagram = (DDiagram) getRepresentationsByName(representationName).toArray()[0];
        assertNotNull(diagram);
        DDiagramElement p3Element = diagram.getDiagramElements().stream().filter(e -> "P3".equals(e.getName())).findFirst().orElse(null);;
        if (p3Element != null && p3Element instanceof DNodeContainer) {
            DNodeContainer p3RegionContainer = (DNodeContainer) p3Element;
            int initRegionChildren = 2;
            assertEquals("P3 region Container should contained only two class region children.", initRegionChildren, p3RegionContainer.getOwnedDiagramElements().size());
            assertTrue("Tool \'Insert Class\' on regionContainer should be done.", applyContainerCreationTool("Insert Class", diagram, p3Element));
            assertEquals("P3 region Container should contained a new class region child.", initRegionChildren + 1, p3RegionContainer.getOwnedDiagramElements().size());
        }
    }
}

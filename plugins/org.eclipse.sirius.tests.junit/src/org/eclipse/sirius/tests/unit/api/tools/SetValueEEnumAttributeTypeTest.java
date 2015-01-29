/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.tools;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.sirius.business.internal.helper.task.operations.SetValueTask;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;

/**
 * Test case for {@link SetValue} and {@link SetValueTask} when setting an
 * {@link EAttribute} typed with a {@link EEnum}.
 * 
 * It checks the capability to set this kind of {@link EAttribute} from several
 * ways: Integer, String, Enumerator or to let the default value.
 * 
 * @author mporhel
 */
public class SetValueEEnumAttributeTypeTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/setValue/EEnumAttributeType.migrationmodeler";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/setValue/EEnumAttributeType.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/setValue/EEnumAttributeType.odesign";

    private static final String REPRESENTATION_DESC_NAME = "EEnumAttributeType";

    private static final String DEFAULT_LABEL_ALIGNMENT = "defaultLabelAlignment";

    private static final String RIGHT_LABEL_ALIGNMENT_FROM_LITERAL = "RightLabelAlignmentFromLiteral";

    private static final String LEFT_LABELALIGNMENT_FROM_INTEGER = "LeftLabelAlignmentFromInteger";

    private static final String RIGHT_LABELALIGNMENT_FROM_ENUMERATOR = "RightLabelAlignmentFromEnumerator";

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        assertTrue(diagram.getOwnedDiagramElements().isEmpty());
    }

    /**
     * Test that element created without {@link SetValue} has the default value
     * for its {@link EEnum} typed attribute.
     * 
     * @throws Exception
     */
    public void testSetDefaultValueEEnumAttributeType() throws Exception {
        checkToolApplication(DEFAULT_LABEL_ALIGNMENT, LabelAlignment.CENTER);
    }

    /**
     * Test a {@link SetValue} on an {@link EEnum} typed attribute from a
     * literal String value.
     * 
     * @throws Exception
     */
    public void testSetValueEEnumAttributeTypeFromLiteral() throws Exception {
        checkToolApplication(RIGHT_LABEL_ALIGNMENT_FROM_LITERAL, LabelAlignment.RIGHT);
    }

    /**
     * Test a {@link SetValue} on an {@link EEnum} typed attribute from a
     * {@link Integer} value.
     * 
     * @throws Exception
     */
    public void testSetValueEEnumAttributeTypeFromInteger() throws Exception {
        checkToolApplication(LEFT_LABELALIGNMENT_FROM_INTEGER, LabelAlignment.LEFT);
    }

    /**
     * Test a {@link SetValue} on an {@link EEnum} typed attribute from a
     * {@link Enumerator} value.
     * 
     * @throws Exception
     */
    public void testSetValueEEnumAttributeTypeFromEnumerator() throws Exception {
        checkToolApplication(RIGHT_LABELALIGNMENT_FROM_ENUMERATOR, LabelAlignment.RIGHT);
    }

    private void checkToolApplication(String toolName, LabelAlignment expectedValue) {
        applyNodeCreationTool(toolName, diagram, diagram);
        assertFalse(diagram.getOwnedDiagramElements().isEmpty());

        LabelStyle created = (LabelStyle) diagram.getOwnedDiagramElements().get(0).getTarget();
        assertEquals("The tool " + toolName + " did not set the labelAlignment to the expected value.", expectedValue, created.getLabelAlignment());
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }
}

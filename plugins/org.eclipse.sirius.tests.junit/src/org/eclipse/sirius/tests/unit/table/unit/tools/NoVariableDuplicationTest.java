/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.tools;

import static org.eclipse.sirius.tests.unit.table.unit.vsm.editor.DefaultVariablesOnToolsTest.AXIS_TOOL_VARIABLES;
import static org.eclipse.sirius.tests.unit.table.unit.vsm.editor.DefaultVariablesOnToolsTest.CELL_CREATION_VARIABLES;
import static org.eclipse.sirius.tests.unit.table.unit.vsm.editor.DefaultVariablesOnToolsTest.CELL_EDITION_VARIABLES;
import static org.eclipse.sirius.tests.unit.table.unit.vsm.editor.DefaultVariablesOnToolsTest.CELL_LABEL_VARIABLES;
import static org.eclipse.sirius.tests.unit.table.unit.vsm.editor.DefaultVariablesOnToolsTest.assertVariables;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.table.business.internal.dialect.description.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;

import junit.framework.TestCase;

/**
 * Test copy paste tool not duplicates variables
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class NoVariableDuplicationTest extends TestCase {

	private static final DescriptionFactory TFCT = DescriptionFactory.eINSTANCE;
	
    /**
     * Test that the renaming and copy of createLineTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateLineTool() {
        assertNoDuplications(TFCT.createCreateLineTool(), AXIS_TOOL_VARIABLES);

    }

    /**
     * Test that the renaming and copy of deleteLineTool not duplicates variables.
     */
    public void testNoVariableDuplicationInDeleteLineTool() {
        assertNoDuplications(TFCT.createDeleteLineTool(), AXIS_TOOL_VARIABLES);

    }

    /**
     * Test that the renaming and copy of createCellTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateCellTool() {
        assertNoDuplications(TFCT.createCreateCellTool(), CELL_CREATION_VARIABLES);
    }

    /**
     * Test that the renaming and copy of createColumnTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateColumnTool() {
        assertNoDuplications(TFCT.createDeleteColumnTool(), AXIS_TOOL_VARIABLES);
    }

    /**
     * Test that the renaming and copy of createCrossColumnTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateCrossColumnTool() {
        assertNoDuplications(TFCT.createDeleteColumnTool(), AXIS_TOOL_VARIABLES);
    }

    /**
     * Test that the renaming and copy of deleteColumnTool not duplicates variables.
     */
    public void testNoVariableDuplicationInDeleteColumnTool() {
        assertNoDuplications(TFCT.createDeleteColumnTool(), AXIS_TOOL_VARIABLES);
    }

    /**
     * Test that the renaming and copy of LabelEditTool not duplicates variables.
     */
    public void testNoVariableDuplicationInLabelEditTool() {
        assertNoDuplications(TFCT.createLabelEditTool(), CELL_LABEL_VARIABLES);
    }

    /**
     * Test that the renaming and copy of CellEditorTool do not duplicate variables.
     */
    public void testNoVariableDuplicationInCellEditorTool() {
    	assertNoDuplications(TFCT.createCellEditorTool(), CELL_EDITION_VARIABLES);
    }

    private void assertNoDuplications(TableTool tool, Set<String> expectedVariableNames) {

        // Add variables.
        new TableToolVariables().doSwitch(tool);
        
        assertVariables(tool.eClass().getName(), 
        		expectedVariableNames, 
        		tool.getVariables());
        
        // Name customization. (No need)
        // Changing variable name will only disable it.
        
        // checkNoModifOncopy(tool)
        EObject copy = SiriusCopierHelper.copyWithNoUidDuplication(tool);
        assertTrue("The copy must be same that origin", EcoreUtil.equals(tool, copy));
    }

}

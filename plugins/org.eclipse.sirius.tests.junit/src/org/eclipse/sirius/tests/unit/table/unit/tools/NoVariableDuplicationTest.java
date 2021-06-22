/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.table.business.internal.dialect.description.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;

import junit.framework.TestCase;

/**
 * Test copy paste tool not duplicates variables
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class NoVariableDuplicationTest extends TestCase {

    /**
     * Test that the renaming and copy of createLineTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateLineTool() {
        // Create createLineTool
        CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        assertEquals("No variable must be present in the createLineTool", 0, createLineTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(createLineTool);
        assertEquals("Wrong number of variables in createLineTool.", 3, createLineTool.getVariables().size());
        for (TableVariable variable : createLineTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName()) && !"container".equals(variable.getName())) {
                fail("The createLineTool variables must be naming element, root and container, not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : createLineTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName()) && !"container1".equals(variable.getName())) {
                fail("The createLineTool variables must be naming element1, root1 and container1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(createLineTool);
    }

    /**
     * Test that the renaming and copy of deleteLineTool not duplicates variables.
     */
    public void testNoVariableDuplicationInDeleteLineTool() {
        // Create deleteLineTool
        DeleteLineTool deleteLineTool = DescriptionFactory.eINSTANCE.createDeleteLineTool();
        assertEquals("No variable must be present in the deleteLineTool", 0, deleteLineTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(deleteLineTool);
        assertEquals("Wrong number of variables in deleteLineTool.", 2, deleteLineTool.getVariables().size());
        for (TableVariable variable : deleteLineTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName())) {
                fail("The deleteLineTool variables must be naming element and root, not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : deleteLineTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName())) {
                fail("The deleteLineTool variables must be naming element1, and root1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(deleteLineTool);
    }

    /**
     * Test that the renaming and copy of createCellTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateCellTool() {
        // Create createCellTool
        CreateCellTool createCellTool = DescriptionFactory.eINSTANCE.createCreateCellTool();
        assertEquals("No variable must be present in the createCellTool", 0, createCellTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(createCellTool);
        assertEquals("Wrong number of variables in createCellTool.", 3, createCellTool.getVariables().size());
        for (TableVariable variable : createCellTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"lineSemantic".equals(variable.getName()) && !"columnSemantic".equals(variable.getName())) {
                fail("The createCellTool variables must be naming root, lineSemantic and columnSemantic not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : createCellTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"lineSemantic1".equals(variable.getName()) && !"columnSemantic1".equals(variable.getName())) {
                fail("The createLineTool variables must be naming root1, lineSemantic1 and columnSemantic1 not " + variable.getName());
            }
        }

        checkNoModifOncopy(createCellTool);
    }

    /**
     * Test that the renaming and copy of createColumnTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateColumnTool() {
        // Create createColumnTool
        CreateColumnTool createColumnTool = DescriptionFactory.eINSTANCE.createCreateColumnTool();
        assertEquals("No variable must be present in the createColumnTool", 0, createColumnTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(createColumnTool);
        assertEquals("Wrong number of variables in createColumnTool.", 3, createColumnTool.getVariables().size());
        for (TableVariable variable : createColumnTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName()) && !"container".equals(variable.getName())) {
                fail("The createColumnTool variables must be naming element, root and container, not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : createColumnTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName()) && !"container1".equals(variable.getName())) {
                fail("The createColumnTool variables must be naming element1, root1 and container1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(createColumnTool);
    }

    /**
     * Test that the renaming and copy of createCrossColumnTool not duplicates variables.
     */
    public void testNoVariableDuplicationInCreateCrossColumnTool() {
        // Create createColumnTool
        CreateCrossColumnTool createCrossColumnTool = DescriptionFactory.eINSTANCE.createCreateCrossColumnTool();
        assertEquals("No variable must be present in the createCrossColumnTool", 0, createCrossColumnTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(createCrossColumnTool);
        assertEquals("Wrong number of variables in createCrossColumnTool.", 3, createCrossColumnTool.getVariables().size());
        for (TableVariable variable : createCrossColumnTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName()) && !"container".equals(variable.getName())) {
                fail("The createCrossColumnTool variables must be naming element, root and container, not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : createCrossColumnTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName()) && !"container1".equals(variable.getName())) {
                fail("The createCrossColumnTool variables must be naming element1, root1 and container1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(createCrossColumnTool);
    }

    /**
     * Test that the renaming and copy of deleteColumnTool not duplicates variables.
     */
    public void testNoVariableDuplicationInDeleteColumnTool() {
        // Create deleteColumnTool
        DeleteColumnTool deleteColumnTool = DescriptionFactory.eINSTANCE.createDeleteColumnTool();
        assertEquals("No variable must be present in the deleteLineTool", 0, deleteColumnTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(deleteColumnTool);
        assertEquals("Wrong number of variables in deleteColumnTool.", 2, deleteColumnTool.getVariables().size());
        for (TableVariable variable : deleteColumnTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName())) {
                fail("The deleteColumnTool variables must be naming element and root, not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : deleteColumnTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName())) {
                fail("The deleteColumnTool variables must be naming element1, and root1, not " + variable.getName());
            }
        }

        checkNoModifOncopy(deleteColumnTool);
    }

    /**
     * Test that the renaming and copy of LabelEditTool not duplicates variables.
     */
    public void testNoVariableDuplicationInLabelEditTool() {
        // Create labelEditTool
        LabelEditTool labelEditTool = DescriptionFactory.eINSTANCE.createLabelEditTool();
        assertEquals("No variable must be present in the labelEditTool", 0, labelEditTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(labelEditTool);
        assertEquals("Wrong number of variables in labelEditTool.", 6, labelEditTool.getVariables().size());
        for (TableVariable variable : labelEditTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"lineSemantic".equals(variable.getName()) && !"columnSemantic".equals(variable.getName()) && !"element".equals(variable.getName())
                    && !"line".equals(variable.getName()) && !"table".equals(variable.getName())) {
                fail("The labelEditTool variables must be naming root, element, lineSemantic and columnSemantic not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : labelEditTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"lineSemantic1".equals(variable.getName()) && !"columnSemantic1".equals(variable.getName()) && !"line1".equals(variable.getName())
                    && !"table1".equals(variable.getName()) && !"element1".equals(variable.getName())) {
                fail("The labelEditTool variables must be naming root1, element1, lineSemantic1 and columnSemantic1 not " + variable.getName());
            }
        }

        checkNoModifOncopy(labelEditTool);
    }

    /**
     * Test that the renaming and copy of CellEditorTool do not duplicate variables.
     */
    public void testNoVariableDuplicationInCellEditorTool() {
        // Create cellEditorTool
        CellEditorTool cellEditorTool = DescriptionFactory.eINSTANCE.createCellEditorTool();
        assertEquals("No variable must be present in the cellEditorTool", 0, cellEditorTool.getVariables().size());

        // Add variables.
        new TableToolVariables().doSwitch(cellEditorTool);

        assertEquals("Wrong number of variables in cellEditorTool.", 6, cellEditorTool.getVariables().size());
        for (TableVariable variable : cellEditorTool.getVariables()) {
            if (!"root".equals(variable.getName()) && !"element".equals(variable.getName()) && !"lineSemantic".equals(variable.getName()) && !"line".equals(variable.getName())
                    && !"table".equals(variable.getName()) && !"cellEditorResult".equals(variable.getName())) {
                fail("The cellEditorTool variables must be naming root, element, lineSemantic, line, table and cellEditorResult not " + variable.getName());
            }
        }

        // Name customization.
        for (TableVariable variable : cellEditorTool.getVariables()) {
            variable.setName(variable.getName() + "1");
            if (!"root1".equals(variable.getName()) && !"element1".equals(variable.getName()) && !"lineSemantic1".equals(variable.getName()) && !"line1".equals(variable.getName())
                    && !"table1".equals(variable.getName()) && !"cellEditorResult1".equals(variable.getName())) {
                fail("The cellEditorTool variables must be naming root1, element1, lineSemantic1, line1, table1 and cellEditorResult1 not " + variable.getName());
            }
        }

        checkNoModifOncopy(cellEditorTool);
    }

    private void checkNoModifOncopy(EObject tool) {
        // Copy createLineTool
        EObject copy = SiriusCopierHelper.copyWithNoUidDuplication(tool);

        assertTrue("The copy must be same that origin", EcoreUtil.equals(tool, copy));
    }

}

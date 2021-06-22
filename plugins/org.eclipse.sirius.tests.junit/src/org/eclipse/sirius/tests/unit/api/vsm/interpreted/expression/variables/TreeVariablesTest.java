/*******************************************************************************
 * Copyright (c) 2015, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.vsm.interpreted.expression.variables;

import java.util.Map;
import java.util.Set;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.tree.business.internal.dialect.description.TreeToolVariables;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Tests of interpreted expressions.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class TreeVariablesTest extends AbstractInterpretedExpressionTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(TreePackage.eINSTANCE);
        super.setUp();
    }

    public void testInterpreterContextForAbstractToolElementsToSelectVariables() {
        // 1 - Setup
        TreeDescription treeDescription = DescriptionFactory.eINSTANCE.createTreeDescription();
        TreeItemMapping treeItemMapping = DescriptionFactory.eINSTANCE.createTreeItemMapping();
        treeItemMapping.setDomainClass("ecore.EClass");
        treeDescription.getSubItemMappings().add(treeItemMapping);
        TreeItemCreationTool treeItemCreationTool = DescriptionFactory.eINSTANCE.createTreeItemCreationTool();
        new TreeToolVariables().doSwitch(treeItemCreationTool);
        treeItemMapping.getCreate().add(treeItemCreationTool);

        // 2 - Code to test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(treeItemCreationTool, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

        // 3 - Test
        Map<String, VariableType> variablesToType = context.getVariables();
        Set<String> variables = variablesToType.keySet();

        // check tool variables
        assertVariableExistenceAndType(treeItemCreationTool, "container", "ecore.EObject", variables, variablesToType);
        assertVariableExistenceAndType(treeItemCreationTool, "element", "ecore.EObject", variables, variablesToType);
        assertVariableExistenceAndType(treeItemCreationTool, "root", "ecore.EObject", variables, variablesToType);
    }

    public void testInterpreterContextForContainerDropTool() {
        // 1 - Setup
        TreeDescription treeDescription = DescriptionFactory.eINSTANCE.createTreeDescription();
        TreeItemMapping treeItemMapping = DescriptionFactory.eINSTANCE.createTreeItemMapping();
        treeItemMapping.setDomainClass("ecore.EClass");
        treeDescription.getSubItemMappings().add(treeItemMapping);
        TreeItemContainerDropTool treeItemContainerDropTool = DescriptionFactory.eINSTANCE
                .createTreeItemContainerDropTool();
        new TreeToolVariables().doSwitch(treeItemContainerDropTool);
        treeItemMapping.getDropTools().add(treeItemContainerDropTool);

        // 2 - Code to test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(
                treeItemContainerDropTool, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

        // 3 - Test
        Map<String, VariableType> variablesToType = context.getVariables();
        Set<String> variables = variablesToType.keySet();

        // check tool variables
        assertVariableExistenceAndType(treeItemContainerDropTool, "newContainer", "ecore.EObject", variables,
                variablesToType);
    }
}

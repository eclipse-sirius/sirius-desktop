/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.vsm.interpreted.expression.variables;

import java.util.Map;
import java.util.Set;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Tests of interpreted expressions.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class TableVariablesTest extends AbstractInterpretedExpressionTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(TablePackage.eINSTANCE);
        super.setUp();
    }

    public void testInterpreterContextForAbstractToolElementsToSelectVariables() {
        // 1 - Setup
        TableDescription tableDescription = DescriptionFactory.eINSTANCE.createEditionTableDescription();
        LineMapping lineMapping = DescriptionFactory.eINSTANCE.createLineMapping();
        lineMapping.setDomainClass("ecore.EClass");
        tableDescription.getOwnedLineMappings().add(lineMapping);
        CreateLineTool createLineTool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        createLineTool.setName("CreateLineTool");
        new TableToolVariables().doSwitch(createLineTool);
        lineMapping.getCreate().add(createLineTool);

        // 2 - Code to test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(createLineTool, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

        // 3 - Test
        Map<String, VariableType> variablesToType = context.getVariables();
        Set<String> variables = variablesToType.keySet();

        // check tool variables
        assertVariableExistenceAndType(createLineTool, "root", "ecore.EObject", variables, variablesToType);
        assertVariableExistenceAndType(createLineTool, "element", "ecore.EClass", variables, variablesToType);
        assertVariableExistenceAndType(createLineTool, "container", "ecore.EClass", variables, variablesToType);
    }
}

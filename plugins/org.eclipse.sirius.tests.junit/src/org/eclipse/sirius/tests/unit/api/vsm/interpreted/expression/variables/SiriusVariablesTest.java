/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Test documentation of interpreted expressions.
 * 
 * @author mporhel
 */
public class SiriusVariablesTest extends AbstractInterpretedExpressionTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(ViewpointPackage.eINSTANCE);
        super.setUp();
    }

    @Override
    protected EPackage getDialectPackage() {
        // Required until Bug 450473 correction: viewpoint.ecore contains
        // variables typed with diagram.Diagram
        return DiagramPackage.eINSTANCE;
    }

    public void testGenericToolVariables() {
        ToolDescription vsmElement = createGenericTool();
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(vsmElement, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);
        Set<String> keySet = context.getVariables().keySet();
        assertTrue("The interpreter context should contains the variable containerView", keySet.contains("containerView"));
    }

    public void testGenericToolVariablesInChildren() {
        ToolDescription vsmElement = createGenericTool();

        ChangeContext chgCtx = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createChangeContext();
        chgCtx.setBrowseExpression("var:containerView");

        InitialOperation initOp = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        initOp.setFirstModelOperations(chgCtx);
        vsmElement.setInitialOperation(initOp);

        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(chgCtx, ToolPackage.Literals.CHANGE_CONTEXT__BROWSE_EXPRESSION);
        Set<String> keySet = context.getVariables().keySet();
        assertTrue("The interpreter context should contains the variable containerView", keySet.contains("containerView"));
    }

    private ToolDescription createGenericTool() {
        ToolDescription vsmElement = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createToolDescription();
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        ToolSection createToolSection = ToolFactory.eINSTANCE.createToolSection();
        diagramDescription.setToolSection(createToolSection);
        createToolSection.getOwnedTools().add(vsmElement);
        return vsmElement;
    }

}

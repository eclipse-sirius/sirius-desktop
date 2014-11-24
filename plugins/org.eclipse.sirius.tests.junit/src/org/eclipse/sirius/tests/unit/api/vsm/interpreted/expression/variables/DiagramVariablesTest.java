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
package org.eclipse.sirius.tests.unit.api.vsm.interpreted.expression.variables;

import java.util.Set;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Test documentation of interpreted expressions.
 * 
 * @author mporhel
 */
public class DiagramVariablesTest extends AbstractInterpretedExpressionTestCase {

    @Override
    protected void setUp() throws Exception {
        setBasePackage(DiagramPackage.eINSTANCE);
        super.setUp();
    }

    public void testInterpreterContextForAbstractVariable() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        ToolSection createToolSection = ToolFactory.eINSTANCE.createToolSection();
        EdgeCreationDescription edgeCreationDescription = ToolFactory.eINSTANCE.createEdgeCreationDescription();
        diagramDescription.setToolSection(createToolSection);
        createToolSection.getOwnedTools().add(edgeCreationDescription);

        // Test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(edgeCreationDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);

        // Check
        Set<String> keySet = context.getVariables().keySet();

        assertTrue("The interpreter context should contains the variable preSourceView", keySet.contains("preSourceView"));
        assertTrue("The interpreter context should contains the variable preTargetView", keySet.contains("preTargetView"));
        assertTrue("The interpreter context should contains the variable preSource", keySet.contains("preSource"));
        assertTrue("The interpreter context should contains the variable preTarget", keySet.contains("preTarget"));
        assertTrue("The interpreter context should contains the variable diagram", keySet.contains("diagram"));
        assertTrue("The interpreter context should contains the variable container", keySet.contains("container"));

    }

}

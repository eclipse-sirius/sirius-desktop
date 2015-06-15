/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.vsm.interpreted.expression.variables;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapterImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.tests.support.api.AbstractInterpretedExpressionTestCase;
import org.eclipse.sirius.tools.api.interpreter.context.SiriusInterpreterContextFactory;
import org.eclipse.sirius.tools.internal.resource.InMemoryResourceImpl;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
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

    public void testInterpreterContextForAbstractToolPreconditionVariables() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        ToolSection createToolSection = ToolFactory.eINSTANCE.createToolSection();
        EdgeCreationDescription edgeCreationDescription = ToolFactory.eINSTANCE.createEdgeCreationDescription();
        edgeCreationDescription.setName("EdgeCreationDescription");
        diagramDescription.setToolSection(createToolSection);
        createToolSection.getOwnedTools().add(edgeCreationDescription);

        // Test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(edgeCreationDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);

        // Check
        Set<String> variables = context.getVariables().keySet();

        assertVariableExistence(edgeCreationDescription, "preSourceView", variables);
        assertVariableExistence(edgeCreationDescription, "preTargetView", variables);
        assertVariableExistence(edgeCreationDescription, "preSource", variables);
        assertVariableExistence(edgeCreationDescription, "preTarget", variables);
        assertVariableExistence(edgeCreationDescription, "diagram", variables);
        assertVariableExistence(edgeCreationDescription, "container", variables);
    }

    public void testInterpreterContextForAbstractToolElementsToSelectVariables() {
        // 1 - Setup
        // Need to create a resource with SiriusCrossReferenceAdapterImpl
        // adapter on it because some code to test needs it
        URI uri = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + "ProjectPipo/NotToBeSave.pipo");
        InMemoryResourceImpl resource = new InMemoryResourceImpl(uri);
        resource.eAdapters().add(new SiriusCrossReferenceAdapterImpl());

        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        resource.getContents().add(diagramDescription);
        ToolSection createToolSection = ToolFactory.eINSTANCE.createToolSection();
        diagramDescription.setToolSection(createToolSection);
        testEdgeCreationDescriptionWithCreateOperations(createToolSection.getOwnedTools());

        testTableCreationDescription(diagramDescription, createToolSection.getOwnedTools());

        testReconnectEdgeDescription(createToolSection.getOwnedTools());
    }

    private void testTableCreationDescription(DiagramDescription diagramDescription, Collection<ToolEntry> ownedTools) {
        // 1 - Init data
        TableCreationDescription tableCreationDescription = org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory.eINSTANCE.createTableCreationDescription();
        tableCreationDescription.setName("TableCreationDescription");
        new TableToolVariables().doSwitch(tableCreationDescription);
        ownedTools.add(tableCreationDescription);

        NodeMapping createNodeMapping = org.eclipse.sirius.diagram.description.DescriptionFactory.eINSTANCE.createNodeMapping();
        diagramDescription.setDefaultLayer(DescriptionFactory.eINSTANCE.createLayer());
        diagramDescription.getDefaultLayer().getNodeMappings().add(createNodeMapping);
        createNodeMapping.setDomainClass("ecore.EClass");
        createNodeMapping.getDetailDescriptions().add(tableCreationDescription);
        tableCreationDescription.getMappings().add(createNodeMapping);

        // 2 - Code to test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(tableCreationDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

        // 3 - Test
        Map<String, VariableType> variablesToType = context.getVariables();
        Set<String> variables = variablesToType.keySet();

        // check tool variables
        assertVariableExistence(tableCreationDescription, "containerView", variables);
        assertVariableExistence(tableCreationDescription, "tableName", variables);
    }

    /**
     * @param ownedTools
     */
    private void testEdgeCreationDescriptionWithCreateOperations(Collection<ToolEntry> ownedTools) {
        // 1 - Init data
        org.eclipse.sirius.viewpoint.description.tool.ToolFactory vpToolFactory = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE;
        EdgeCreationDescription edgeCreationDescription = ToolFactory.eINSTANCE.createEdgeCreationDescription();
        edgeCreationDescription.setName("EdgeCreationDescription");
        ownedTools.add(edgeCreationDescription);
        InitEdgeCreationOperation createInitialOperation = vpToolFactory.createInitEdgeCreationOperation();
        CreateInstance createInstance = vpToolFactory.createCreateInstance();
        createInstance.setTypeName("EObject");
        CreateView createView = ToolFactory.eINSTANCE.createCreateView();
        CreateEdgeView createEdgeView = ToolFactory.eINSTANCE.createCreateEdgeView();
        createEdgeView.setVariableName("createdEdgeView");
        edgeCreationDescription.setInitialOperation(createInitialOperation);
        createInitialOperation.setFirstModelOperations(createInstance);
        createInstance.getSubModelOperations().add(createView);
        createView.getSubModelOperations().add(createEdgeView);

        // 2 - Code to test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(edgeCreationDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

        // 3 - Test
        Map<String, VariableType> variablesToType = context.getVariables();
        Set<String> variables = variablesToType.keySet();

        // check tool variables
        assertVariableExistence(edgeCreationDescription, "sourceView", variables);
        assertVariableExistence(edgeCreationDescription, "targetView", variables);
        assertVariableExistence(edgeCreationDescription, "source", variables);
        assertVariableExistence(edgeCreationDescription, "target", variables);

        // check variables from create operation
        assertVariableExistenceAndType(edgeCreationDescription, "instance", "ecore.EObject", variables, variablesToType);
        assertVariableExistenceAndType(edgeCreationDescription, "createdView", "viewpoint.DView", variables, variablesToType);
        assertVariableExistenceAndType(edgeCreationDescription, "createdEdgeView", "diagram.DEdge", variables, variablesToType);
    }

    private void testReconnectEdgeDescription(Collection<ToolEntry> ownedTools) {
        // 1 - Init data
        ReconnectEdgeDescription createReconnectEdgeDescription = ToolFactory.eINSTANCE.createReconnectEdgeDescription();
        createReconnectEdgeDescription.setName("ReconnectEdgeDescription");
        ownedTools.add(createReconnectEdgeDescription);

        // 2 - Code to test
        IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(createReconnectEdgeDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

        // 3 - Test
        Map<String, VariableType> variablesToType = context.getVariables();
        Set<String> variables = variablesToType.keySet();

        // check tool variables
        assertVariableExistence(createReconnectEdgeDescription, "sourceView", variables);
        assertVariableExistence(createReconnectEdgeDescription, "targetView", variables);
        assertVariableExistence(createReconnectEdgeDescription, "source", variables);
        assertVariableExistence(createReconnectEdgeDescription, "target", variables);
        assertVariableExistence(createReconnectEdgeDescription, "element", variables);

        // check variables from create operation
        assertVariableExistenceAndType(createReconnectEdgeDescription, "otherEnd", "diagram.EdgeTarget", variables, variablesToType);
        assertVariableExistenceAndType(createReconnectEdgeDescription, "edgeView", "diagram.DEdge", variables, variablesToType);
    }
}

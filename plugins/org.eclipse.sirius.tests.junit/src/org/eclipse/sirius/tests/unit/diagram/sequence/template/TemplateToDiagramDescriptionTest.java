/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.sequence.template;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceDiagramDescriptionQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.TSequenceDiagramQuery;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.template.TBasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.template.TExecutionMapping;
import org.eclipse.sirius.diagram.sequence.template.TLifelineMapping;
import org.eclipse.sirius.diagram.sequence.template.TSequenceDiagram;
import org.eclipse.sirius.diagram.sequence.template.TemplateFactory;
import org.eclipse.sirius.diagram.sequence.tool.internal.template.TemplateToDiagramDescriptionTransformer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

import junit.framework.TestCase;

public class TemplateToDiagramDescriptionTest extends TestCase {

    public void testDiagramKeepingCustomData() throws Exception {
        TSequenceDiagram template = TemplateFactory.eINSTANCE.createTSequenceDiagram();
        template.setName("MyTemplate");

        SequenceDiagramDescription description = refresh(template);
        assertEquals("MyTemplate", description.getName());

        SequenceDiagramDescription againDescription = refresh(template);

        assertSame(description, againDescription);
        /*
         * Let's customize the sequence diagram.
         */
        assertFalse(description.isEnablePopupBars());
        description.setEnablePopupBars(true);

        refresh(template);

        assertTrue(description.isEnablePopupBars());

    }

    public void testDiagramKeepingCustomChilds() throws Exception {
        TSequenceDiagram template = TemplateFactory.eINSTANCE.createTSequenceDiagram();
        template.setName("MyTemplate");

        SequenceDiagramDescription description = refresh(template);

        assertTrue(description.getAdditionalLayers().isEmpty());

        description.getAdditionalLayers().add(DescriptionFactory.eINSTANCE.createAdditionalLayer());

        refresh(template);

        assertFalse(description.getAdditionalLayers().isEmpty());
    }

    public void testDiagramIsRecreatedWhenRemoved() throws Exception {
        TSequenceDiagram template = TemplateFactory.eINSTANCE.createTSequenceDiagram();
        template.setName("MyTemplate");

        SequenceDiagramDescription description = refresh(template);
        assertEquals("MyTemplate", description.getName());

        assertEquals("We should have the sequence diagramm", 1, template.getOwnedRepresentations().size());

        template.getOwnedRepresentations().clear();

        refresh(template);

        assertEquals(1, template.getOwnedRepresentations().size());

    }

    public void testChildElementIsDeletedWhenSourceIsDeleted() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");

        SequenceDiagramDescription description = refresh(template);
        assertEquals("We should have an instance role mapping (nodemapping) created in the sequence diagram", 1, description.getNodeMappings().size());

        template.getLifelineMappings().clear();

        refresh(template);

        assertEquals("The instance role mapping should now be deleted", 0, description.getNodeMappings().size());
    }

    public void testTemplateToDiagramRule() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");
        SequenceDiagramDescription diag = refresh(template);
        assertEquals(template.getName(), diag.getName());
        assertEquals(template.getDomainClass(), diag.getDomainClass());
        assertEquals(template.getEndsOrdering(), diag.getEndsOrdering());
        assertEquals(template.getLifelineMappings().size(), diag.getNodeMappings().size());
        assertEquals(template.getMessageMappings().size(), diag.getEdgeMappings().size());
    }

    public void testLifelineToNodeMappingRules() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");
        SequenceDiagramDescription diag = refresh(template);

        TSequenceDiagramQuery templateQuery = new TSequenceDiagramQuery(template);
        SequenceDiagramDescriptionQuery diagQuery = new SequenceDiagramDescriptionQuery(diag);

        TLifelineMapping lflMapping = templateQuery.getLifeLineMappings("Participant").next();
        assertTrue("No instance role mapping has been created", diagQuery.getInstanceRoleMappings("Participant Instance Role").hasNext());

        InstanceRoleMapping irMapping = diagQuery.getInstanceRoleMappings("Participant Instance Role").next();

        assertEquals(lflMapping.getDomainClass(), irMapping.getDomainClass());
        assertEquals(lflMapping.getSemanticCandidatesExpression(), irMapping.getSemanticCandidatesExpression());

        // assertEquals(irMapping.getStyle().getResizeKind(), ResizeKind.NSEW);

        assertTrue("Can't find the generated ExecutionMapping", diagQuery.getExecutionMappings("Participant Execution").hasNext());

        ExecutionMapping exMapping = diagQuery.getExecutionMappings("Participant Execution").next();

        assertTrue("It should be a bordered node", exMapping.eContainingFeature() == DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings());
        assertEquals(lflMapping.getDomainClass(), exMapping.getDomainClass());

        assertTrue(exMapping.isCreateElements());
        assertEquals("var:self", exMapping.getSemanticCandidatesExpression());

        assertTrue("Can't find the generated EndOfLifeMapping", diagQuery.getEndOfLifeMappings("Participant EOL").hasNext());

        EndOfLifeMapping eolMapping = diagQuery.getEndOfLifeMappings("Participant EOL").next();
        assertEquals(eolMapping.getDomainClass(), eolMapping.getDomainClass());
        assertEquals("var:self", eolMapping.getSemanticCandidatesExpression());
        assertEquals(lflMapping.getEolVisibleExpression(), eolMapping.getPreconditionExpression());

        assertTrue("It should be a bordered node", eolMapping.eContainingFeature() == DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings());
        assertSame("The end of life mapping should be contained in the execution mapping", exMapping, eolMapping.eContainer());
    }

    public void testExecutionToExecutionMappingRules() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");
        SequenceDiagramDescription diag = refresh(template);

        TSequenceDiagramQuery templateQuery = new TSequenceDiagramQuery(template);
        SequenceDiagramDescriptionQuery diagQuery = new SequenceDiagramDescriptionQuery(diag);

        TExecutionMapping tExMapping = templateQuery.getExecutionMappings("Execution").next();
        ExecutionMapping exMapping = diagQuery.getExecutionMappings(tExMapping.getName()).next();

        assertEquals(tExMapping.getDomainClass(), exMapping.getDomainClass());
        assertEquals(tExMapping.getName(), exMapping.getName());
        assertEquals(tExMapping.getSemanticCandidatesExpression(), exMapping.getSemanticCandidatesExpression());
        assertEquals(tExMapping.getStartingEndFinderExpression(), exMapping.getStartingEndFinderExpression());
        assertEquals(tExMapping.getFinishingEndFinderExpression(), exMapping.getFinishingEndFinderExpression());
        assertTrue("As it's recursive it should  import itself.", exMapping.getReusedBorderedNodeMappings().contains(exMapping));
    }

    public void testExecutionToExecutionMappingRulesRecursivity() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");
        SequenceDiagramDescription diag = refresh(template);

        TSequenceDiagramQuery templateQuery = new TSequenceDiagramQuery(template);
        SequenceDiagramDescriptionQuery diagQuery = new SequenceDiagramDescriptionQuery(diag);

        TExecutionMapping tExMapping = templateQuery.getExecutionMappings("NonRecursiveExecution").next();
        ExecutionMapping exMapping = diagQuery.getExecutionMappings("NonRecursiveExecution").next();
        assertFalse("Invalid Test data, we should have a recursive execution mapping here", tExMapping.isRecursive());
        assertTrue("As it's recursive it should  import nothing.", exMapping.getReusedBorderedNodeMappings().size() == 0);
    }

    public void testExecutionToExecutionAndSubExecutionsRules() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");
        SequenceDiagramDescription diag = refresh(template);

        TSequenceDiagramQuery templateQuery = new TSequenceDiagramQuery(template);
        SequenceDiagramDescriptionQuery diagQuery = new SequenceDiagramDescriptionQuery(diag);

        TExecutionMapping tExMapping = templateQuery.getExecutionMappings("ExecutionContainer").next();
        ExecutionMapping exMapping = diagQuery.getExecutionMappings("ExecutionContainer").next();

        assertEquals(tExMapping.getExecutionMappings().size(), exMapping.getBorderedNodeMappings().size());

        ExecutionMapping exMappingChild = diagQuery.getExecutionMappings("ExecutionChild").next();
        assertSame(exMapping, exMappingChild.eContainer());
        assertTrue("Execution Childs should be bordered nodes", exMappingChild.eContainingFeature() == DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings());
    }

    public void testBasicMessageToEdgeMappingRules() throws Exception {
        TSequenceDiagram template = loadTemplate("/data/sequence/unit/TSequenceDiagram.xmi");
        SequenceDiagramDescription diag = refresh(template);

        TSequenceDiagramQuery templateQuery = new TSequenceDiagramQuery(template);
        SequenceDiagramDescriptionQuery diagQuery = new SequenceDiagramDescriptionQuery(diag);
        TBasicMessageMapping tMessage = templateQuery.getBasicMessageMapping("Feature Access Message").next();
        BasicMessageMapping message = (BasicMessageMapping) diagQuery.getMessageMappings(tMessage.getName()).next();

        assertEquals(tMessage.getSource().size(), message.getSourceMapping().size());
        assertEquals(tMessage.getTarget().size(), message.getTargetMapping().size());

        /*
         * Let's remove one of the source/target mappings to make sure the refresh keep everything in sync.
         */

        EcoreUtil.delete(templateQuery.getExecutionMappings("Execution").next());
        refresh(template);

        assertEquals(tMessage.getSource().size(), message.getSourceMapping().size());
        assertEquals(tMessage.getTarget().size(), message.getTargetMapping().size());

    }

    protected SequenceDiagramDescription refresh(TSequenceDiagram template) {
        return new TemplateToDiagramDescriptionTransformer(template).refresh();
    }

    private TSequenceDiagram loadTemplate(String path) throws IOException {
        Resource res = new XMIResourceImpl(URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + path, true));
        res.load(Collections.EMPTY_MAP);
        return (TSequenceDiagram) res.getContents().get(0);
    }
}

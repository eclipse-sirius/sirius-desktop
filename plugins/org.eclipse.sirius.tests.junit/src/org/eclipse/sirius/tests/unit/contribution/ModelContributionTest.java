/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.contribution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Arrays;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.business.internal.contribution.ContributionBuilder;
import org.eclipse.sirius.business.internal.contribution.ContributionFinder;
import org.eclipse.sirius.business.internal.contribution.ModelContributor;
import org.eclipse.sirius.business.internal.contribution.SiriusReferenceResolver;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Unit tests for the application of full contributions on models.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ModelContributionTest {

    private SiriusReferenceResolver resolver;

    /**
     * Make sure EMF is properly initialized when we are run outside of Eclipse.
     */
    @BeforeClass
    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            @SuppressWarnings("unused")
            EPackage viewpoint = ViewpointPackage.eINSTANCE;
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("odesign", new XMIResourceFactoryImpl());
        }
    }

    @Before
    public void setup() {
        resolver = new SiriusReferenceResolver(CompoundInterpreter.INSTANCE);
    }

    @After
    public void tearDown() {
        resolver = null;
    }

    @Test
    public void reuse_semanticCandidateExpression() {
        Viewpoint vTarget = createSampleSirius("target");
        Viewpoint vSource = createSampleSirius("source");
        NodeMapping targetNode = (NodeMapping) Iterables.find(AllContents.of(vTarget), Predicates.instanceOf(NodeMapping.class));
        NodeMapping sourceNode = (NodeMapping) Iterables.find(AllContents.of(vSource), Predicates.instanceOf(NodeMapping.class));
        String expression = "aql:aComplicatedExpressionThatMakesSenseToReuse";
        sourceNode.setSemanticCandidatesExpression(expression);

        Contribution contrib = new ContributionBuilder().from(sourceNode).to(targetNode).set("semanticCandidatesExpression").build();
        ModelContributor mc = new ModelContributor(ContributionFinder.providing(contrib), resolver);

        assertNull(targetNode.getSemanticCandidatesExpression());
        mc.apply(vTarget, Arrays.asList((EObject) vSource));
        assertEquals(expression, targetNode.getSemanticCandidatesExpression());
        assertEquals(expression, sourceNode.getSemanticCandidatesExpression());
    }

    @Test
    public void reuse_borderedNodeMapping() {
        Viewpoint vTarget = createSampleSirius("target");
        Viewpoint vSource = createSampleSirius("source");
        NodeMapping targetNode = (NodeMapping) Iterables.find(AllContents.of(vTarget), Predicates.instanceOf(NodeMapping.class));
        NodeMapping sourceNode = (NodeMapping) Iterables.find(AllContents.of(vSource), Predicates.instanceOf(NodeMapping.class));
        NodeMapping border = DescriptionFactory.eINSTANCE.createNodeMapping();
        border.setName("Bordered node");
        sourceNode.getBorderedNodeMappings().add(border);

        Contribution contrib = new ContributionBuilder().from(sourceNode).to(targetNode).add("reusedBorderedNodeMappings", "borderedNodeMappings").build();
        ModelContributor mc = new ModelContributor(ContributionFinder.providing(contrib), resolver);

        assertEquals(1, sourceNode.getBorderedNodeMappings().size());
        assertSame(border, sourceNode.getBorderedNodeMappings().get(0));
        assertEquals(0, targetNode.getReusedBorderedNodeMappings().size());
        Freezer.freeze(sourceNode);
        Freezer.freeze(border);
        mc.apply(vTarget, Arrays.asList((EObject) vSource));
        assertEquals(1, sourceNode.getBorderedNodeMappings().size());
        assertSame(border, sourceNode.getBorderedNodeMappings().get(0));
        assertEquals(1, targetNode.getReusedBorderedNodeMappings().size());
        assertSame(border, targetNode.getReusedBorderedNodeMappings().get(0));
    }

    public Viewpoint createSampleSirius(String suffix) {
        Viewpoint v = org.eclipse.sirius.viewpoint.description.DescriptionFactory.eINSTANCE.createViewpoint();
        v.setName("Sirius_" + suffix);

        DiagramDescription diag = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diag.setName("Diagram_" + suffix);
        diag.setDomainClass("EPackage");
        v.getOwnedRepresentations().add(diag);

        Layer layer = DescriptionFactory.eINSTANCE.createLayer();
        layer.setName("Default");
        diag.setDefaultLayer(layer);

        NodeMapping nm = DescriptionFactory.eINSTANCE.createNodeMapping();
        nm.setName("Node_" + suffix);
        nm.setDomainClass("EClass");
        layer.getNodeMappings().add(nm);

        return v;
    }
}

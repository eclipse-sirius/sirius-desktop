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
package org.eclipse.sirius.tests.unit.contribution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.business.internal.contribution.ContributionBuilder;
import org.eclipse.sirius.business.internal.contribution.ContributionFinder;
import org.eclipse.sirius.business.internal.contribution.IncrementalModelContributor;
import org.eclipse.sirius.business.internal.contribution.SiriusReferenceResolver;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Unit tests for the application of full contributions on models.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class IncrementalModelContributionTest {
    private ResourceSetImpl rs;

    private EObject root;

    private SiriusReferenceResolver resolver;

    private Resource contribsResource;

    private Function<EObject, Object> uriFragmentId;

    /**
     * Make sure EMF is properly initialized when we are run outside of Eclipse.
     */
    @BeforeClass
    @SuppressWarnings("unused")
    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            EPackage vsm = DescriptionPackage.eINSTANCE;
            EPackage tables = org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE;
            EPackage sequence = org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE;
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("odesign", new XMIResourceFactoryImpl());
        }
    }

    @Before
    public void setup() {
        uriFragmentId = new Function<EObject, Object>() {
            public Object apply(EObject from) {
                return from.eResource().getURIFragment(from);
            }
        };
        resolver = new SiriusReferenceResolver(CompoundInterpreter.INSTANCE);
        rs = new ResourceSetImpl();
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            root = rs.getResource(URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/movida/contribution_tests.odesign", true), true).getContents().get(0);
            contribsResource = rs.createResource(URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/movida/contributions.odesign", true));
        } else {
            root = rs.getResource(URI.createFileURI("data/unit/movida/contribution_tests.odesign"), true).getContents().get(0);
            contribsResource = rs.createResource(URI.createFileURI("data/unit/movida/contributions.odesign"));
        }
        Freezer.freeze(AllContents.of(root, true));
    }

    @After
    public void tearDown() {
        Freezer.thaw(AllContents.of(root, true));
        uriFragmentId = null;
        resolver = null;
        root = null;
        rs = null;
    }

    @Test
    public void incremental_contribution() {
        final List<Contribution> firstContribs = createFirstTestContributions(root);
        contribsResource.getContents().addAll(firstContribs);
        
        IncrementalModelContributor imc = new IncrementalModelContributor(ContributionFinder.intrinsic(), resolver, uriFragmentId);
        ArrayList<EObject> allSources = Lists.<EObject>newArrayList(firstContribs);
        allSources.add(root);
        EObject result = imc.apply(root, allSources);

        assertNotNull(result);
        assertFalse(root == result);
        
        List<EObject> newDiagrams = Lists.newArrayList(Iterables.filter(AllContents.of(result), Predicates.instanceOf(DiagramDescription.class)));
        DiagramDescription newDDiag = (DiagramDescription) Iterables.get(newDiagrams, 0);
        assertEquals(3, newDDiag.getDefaultLayer().getNodeMappings().size());

        final List<Contribution> secondContribs = createSecondTestContributions(root);
        contribsResource.getContents().clear();
        contribsResource.getContents().addAll(secondContribs);
        allSources.clear();
        allSources.add(root);
        allSources.addAll(secondContribs);
        EObject secondResult = imc.apply(root, allSources);

        assertNotNull(secondResult);
        assertFalse(root == secondResult);
        assertSame(result, secondResult);
        assertEquals(2, newDDiag.getDefaultLayer().getNodeMappings().size());
        assertEquals(1, newDDiag.getDefaultLayer().getEdgeMappings().size());
    }

    private List<Contribution> createFirstTestContributions(EObject root) {
        ArrayList<EObject> diagrams = Lists.newArrayList(Iterables.filter(AllContents.of(root), Predicates.instanceOf(DiagramDescription.class)));
        DiagramDescription dDiag = (DiagramDescription) Iterables.get(diagrams, 0);
        DiagramDescription dextDiag = (DiagramDescription) Iterables.get(diagrams, 1);

        Contribution contrib = new ContributionBuilder().from(dextDiag.getDefaultLayer()).to(dDiag.getDefaultLayer()).add("nodeMappings").build();
        return Collections.singletonList(contrib);
    }
    
    private List<Contribution> createSecondTestContributions(EObject root) {
        ArrayList<EObject> diagrams = Lists.newArrayList(Iterables.filter(AllContents.of(root), Predicates.instanceOf(DiagramDescription.class)));
        DiagramDescription dDiag = (DiagramDescription) Iterables.get(diagrams, 0);
        DiagramDescription dextDiag = (DiagramDescription) Iterables.get(diagrams, 1);

        Contribution contrib = new ContributionBuilder().from(dextDiag.getDefaultLayer()).to(dDiag.getDefaultLayer()).add("edgeMappings").build();
        return Collections.singletonList(contrib);
    }
}

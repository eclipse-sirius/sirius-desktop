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
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.contribution.ContributionFinder;
import org.eclipse.sirius.business.internal.contribution.ModelContributor;
import org.eclipse.sirius.business.internal.contribution.SiriusReferenceResolver;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Unit tests for the use of the contribution mechanism on table descriptions.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class TableContributionTest {
    private Resource vsm;

    private SiriusReferenceResolver resolver;

    @BeforeClass
    public static void initializeEMF() {
        ModelContributionTest.initializeEMF();
        @SuppressWarnings("unused")
        EPackage tables = DescriptionPackage.eINSTANCE;
    }

    @Before
    public void setUp() {
        ResourceSet rs = new ResourceSetImpl();
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            vsm = rs.getResource(URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/movida/tables.odesign", true), true);
        } else {
            vsm = rs.getResource(URI.createFileURI("data/unit/movida/tables.odesign"), true);
        }
        resolver = new SiriusReferenceResolver(CompoundInterpreter.INSTANCE);
    }

    @Test
    public void vsm_loaded_properly() {
        assertNotNull(vsm);
        assertEquals(1, vsm.getContents().size());
        assertTrue(vsm.getContents().get(0) instanceof Group);
    }

    @Test
    public void simple_semantic_candidate_expression_customization() {
        Group group = (Group) vsm.getContents().get(0);

        Viewpoint vp1 = group.getOwnedViewpoints().get(0);
        EditionTableDescription targetTable = (EditionTableDescription) vp1.getOwnedRepresentations().get(0);
        LineMapping targetMapping = targetTable.getAllLineMappings().get(0);

        Viewpoint vp2 = group.getOwnedViewpoints().get(1);
        EditionTableDescription sourceTable = (EditionTableDescription) vp2.getOwnedRepresentationExtensions().get(0);
        LineMapping sourceMapping = sourceTable.getOwnedLineMappings().get(0);

        Contribution contrib = findFirst(vp2, Contribution.class);
        EStructuralFeature feature = DescriptionPackage.eINSTANCE.getLineMapping_SemanticCandidatesExpression();
        contrib.getFeatureMask().get(0).setSourceFeature(feature);
        contrib.getFeatureMask().get(0).setTargetFeature(feature);

        assertFalse(Objects.equal(sourceMapping.getSemanticCandidatesExpression(), targetMapping.getSemanticCandidatesExpression()));
        ModelContributor mc = new ModelContributor(ContributionFinder.intrinsic(), resolver);
        Freezer.freeze(AllContents.of(vp2));

        mc.apply(vp1, Collections.singletonList((EObject) vp2));
        assertTrue(Objects.equal(sourceMapping.getSemanticCandidatesExpression(), targetMapping.getSemanticCandidatesExpression()));
        Freezer.thaw(AllContents.of(vp2));
    }

    private <T> T findFirst(EObject root, Class<T> klass) {
        return klass.cast(Iterables.find(AllContents.of(root), Predicates.instanceOf(klass)));
    }
}

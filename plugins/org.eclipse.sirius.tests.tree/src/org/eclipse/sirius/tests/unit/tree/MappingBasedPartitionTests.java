/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.tree;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.MappingBasedPartition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MappingBasedPartitionTests {

    private GlobalContext ctx;

    @Before
    public void setUp() throws Exception {

        ResourceSet set = new ResourceSetImpl();

        ModelAccessor accessor = new ModelAccessor();
        accessor.addExtender(new EcoreIntrinsicExtender(), ExtenderConstants.HIGHEST_PRIORITY);
        accessor.init(set);

        IInterpreter interpreter = new FeatureInterpreter();

        ctx = new GlobalContext(accessor, interpreter, null);

    }

    @Test
    public void orderIsKept() {
        MappingBasedPartition partition = new MappingBasedPartition(ctx, "EObject", Options.newSome("feature:eClassifiers"), Options.<EObject> newNone());
        EPackage semanticModel = (EPackage) EcoreUtil.copy(EcorePackage.eINSTANCE);

        Iterator<EObject> itTree = partition.evaluate(semanticModel, null).elements();
        Iterator<EClassifier> itSemContent = semanticModel.getEClassifiers().iterator();

        Assert.assertTrue(itTree.hasNext());
        Assert.assertTrue(itSemContent.hasNext());

        while (itSemContent.hasNext()) {
            EObject semantic = itSemContent.next();
            Assert.assertSame(semantic, itTree.next());
        }
    }

    @Test
    public void orderIsKeptWithTypeFiltering() {
        MappingBasedPartition partition = new MappingBasedPartition(ctx, "EClass", Options.newSome("feature:eClassifiers"), Options.<EObject> newNone());
        EPackage semanticModel = (EPackage) EcoreUtil.copy(EcorePackage.eINSTANCE);

        Iterator<EObject> itTree = partition.evaluate(semanticModel, null).elements();
        Iterator<EClassifier> itSemContent = semanticModel.getEClassifiers().iterator();

        Assert.assertTrue(itTree.hasNext());
        Assert.assertTrue(itSemContent.hasNext());

        while (itSemContent.hasNext()) {
            EObject semantic = itSemContent.next();
            if (semantic instanceof EClass) {
                Assert.assertSame(semantic, itTree.next());
            }
        }
    }
}

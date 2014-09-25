/**
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Test documentation of interpreted expressions.
 * 
 * @author mporhel
 */
public abstract class AbstractInterpretedExpressionTestCase extends TestCase {

    private static final String RETURN_TYPE = "http://www.eclipse.org/sirius/interpreted/expression/returnType";

    private static final String VARIABLES = "http://www.eclipse.org/sirius/interpreted/expression/variables";

    private Collection<EAttribute> interpretedExpressions;

    private EPackage basePackage;

    private final Predicate<EAttribute> isInterpretedExpression = new Predicate<EAttribute>() {
        @Override
        public boolean apply(EAttribute input) {
            return DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(input.getEAttributeType());
        }
    };

    public EPackage getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(EPackage basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpretedExpressions = Sets.newLinkedHashSet();
        Assert.assertNotNull("Base package should not be null.", basePackage);
        handleEPackage(basePackage);
    }

    private void handleEPackage(EPackage pkg) {
        for (EClass eclass : Iterables.filter(pkg.getEClassifiers(), EClass.class)) {
            handleEClass(eclass);
        }
        for (EPackage subPkg : pkg.getESubpackages()) {
            handleEPackage(subPkg);
        }
    }

    private void handleEClass(EClass eclass) {
        Iterables.addAll(interpretedExpressions, Iterables.filter(eclass.getEAttributes(), isInterpretedExpression));
    }

    /**
     * Test that all interpreted expression has a variable documentation.
     */
    public void testVariablesInterpretedExpressionEAnnotation() {
        List<EAttribute> nonDocumented = Lists.newArrayList();

        Predicate<EAttribute> needsDocumentation = new Predicate<EAttribute>() {
            @Override
            public boolean apply(EAttribute input) {
                EAnnotation eAnnotation = input.getEAnnotation(AbstractInterpretedExpressionTestCase.VARIABLES);
                return eAnnotation == null;
            }
        };

        Iterables.addAll(nonDocumented, Iterables.filter(interpretedExpressions, needsDocumentation));
        Assert.assertTrue(getMessage(nonDocumented, AbstractInterpretedExpressionTestCase.VARIABLES), nonDocumented.isEmpty());
    }

    /**
     * Test that all interpreted expression has a result type documentation.
     */
    public void testReturnTypeInterpretedExpressionEAnnotation() {
        List<EAttribute> nonDocumented = Lists.newArrayList();

        Predicate<EAttribute> needsReturnType = new Predicate<EAttribute>() {
            @Override
            public boolean apply(EAttribute input) {
                EAnnotation eAnnotation = input.getEAnnotation(AbstractInterpretedExpressionTestCase.RETURN_TYPE);
                return eAnnotation == null || eAnnotation.getDetails().isEmpty();
            }
        };

        Iterables.addAll(nonDocumented, Iterables.filter(interpretedExpressions, needsReturnType));
        Assert.assertTrue(getMessage(nonDocumented, AbstractInterpretedExpressionTestCase.RETURN_TYPE), nonDocumented.isEmpty());
    }

    private String getMessage(List<EAttribute> nonDocumented, String source) {
        StringBuilder sb = new StringBuilder();
        sb.append(nonDocumented.size());
        sb.append(" interpreted expression(s) needs variable EAnnotation ");
        sb.append(source);
        sb.append(": ");
        for (EAttribute attr : nonDocumented) {
            sb.append("\n . ");
            sb.append(attr.eResource().getURIFragment(attr));
        }
        return sb.toString();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        interpretedExpressions.clear();
    }
}

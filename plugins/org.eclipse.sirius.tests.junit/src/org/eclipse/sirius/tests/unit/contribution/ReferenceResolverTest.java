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

import static org.eclipse.sirius.tests.unit.contribution.OptionAssert.assertHasExactValue;
import static org.eclipse.sirius.tests.unit.contribution.OptionAssert.assertHasNoValue;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.business.internal.contribution.ReferenceResolver;
import org.eclipse.sirius.business.internal.contribution.SiriusReferenceResolver;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.description.contribution.ComputedEObjectReference;
import org.eclipse.sirius.description.contribution.ContributionFactory;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.description.contribution.EObjectReference;
import org.eclipse.sirius.ext.base.Option;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Maps;

/**
 * Tests for {@link SiriusReferenceResolver}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ReferenceResolverTest {
    private static final Map<String, Object> NO_CONTEXT = Maps.newHashMap();

    private static ContributionFactory factory;

    private IInterpreter interpreter;

    private ReferenceResolver resolver;

    @BeforeClass
    public static void init() {
        factory = ContributionFactory.eINSTANCE;
    }

    @Before
    public void setUp() {
        this.interpreter = CompoundInterpreter.INSTANCE;
        this.resolver = new SiriusReferenceResolver(interpreter);
    }

    @Test
    public void direct_reference_with_null_value_resolves_to_empty_option() {
        EObjectReference ref = factory.createDirectEObjectReference();
        Option<EObject> result = resolver.resolve(ref, NO_CONTEXT);
        assertHasNoValue(result);
    }

    @Test
    public void direct_reference_with_value_resolves_to_value() {
        EObject value = EcoreFactory.eINSTANCE.createEObject();
        DirectEObjectReference ref = factory.createDirectEObjectReference();
        ref.setValue(value);
        Option<EObject> result = resolver.resolve(ref, NO_CONTEXT);
        assertHasExactValue(value, result);
    }
    
    @Test
    public void computed_reference_with_no_expression_resolves_to_empty_option() {
        ComputedEObjectReference ref = factory.createComputedEObjectReference();
        Option<EObject> result = resolver.resolve(ref, NO_CONTEXT);
        assertHasNoValue(result);
    }
    
    @Test
    public void computed_reference_with_invalid_expression_resolves_to_empty_option() {
        ComputedEObjectReference ref = factory.createComputedEObjectReference();
        ref.setValueExpression("not a valid expression");
        Option<EObject> result = resolver.resolve(ref, NO_CONTEXT);
        assertHasNoValue(result);
    }
    
    @Test
    public void computed_reference_with_valid_expression_but_not_context_resolves_to_empty_option() {
        ComputedEObjectReference ref = factory.createComputedEObjectReference();
        ref.setValueExpression("aql:self");
        Option<EObject> result = resolver.resolve(ref, NO_CONTEXT);
        assertHasNoValue(result);
    }
    
    @Test
    public void computed_reference_with_self_expression_resolves_to_context() {
        EObject self = EcoreFactory.eINSTANCE.createEObject();
        ComputedEObjectReference ref = factory.createComputedEObjectReference();
        ref.setValueExpression("aql:self");
        Map<String, Object> context = Maps.newHashMap();
        context.put("self", self);
        Option<EObject> result = resolver.resolve(ref, context);
        assertHasExactValue(self, result);
    }
}

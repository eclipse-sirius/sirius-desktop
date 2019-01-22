/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.tests.unit.tree.vsm.edit;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.dialect.description.MultiLanguagesValidator;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test checking the variable types are correctly infered when validating a VSM.
 * 
 * @author jmallet
 */
@RunWith(value = Parameterized.class)
public class VSMVariableTypesValidationTest {
    /**
     * This VSM is conform to some specific conventions to make it easy to test the type analysis of variables in a VSM.
     */
    private static final String ORG_ECLIPSE_SIRIUS_TESTS_JUNIT_DATA_UNIT_VSM_VALIDATE_VARIABLE_TYPES_ODESIGN = "/org.eclipse.sirius.tests.tree/data/unit/vsm/validateVariableTypeTree.odesign";

    private InterpretedExpression interpretedExpToTest;

    public VSMVariableTypesValidationTest(InterpretedExpression expression) {
        this.interpretedExpToTest = expression;
    }

    @Parameters(name = "org.eclipse.sirius.tests.unit.tree.vsm.edit.VSMVariableTypesValidationTest.{index}: ({0})")
    public static Collection<Object[]> data() throws IOException {
        URI uri = URI.createPlatformPluginURI(ORG_ECLIPSE_SIRIUS_TESTS_JUNIT_DATA_UNIT_VSM_VALIDATE_VARIABLE_TYPES_ODESIGN, true);

        List<Object[]> parameters = new ArrayList<>();
        Group group = loadVSM(uri);
        if (group != null) {
            for (Viewpoint vp : group.getOwnedViewpoints()) {
                Iterator<EObject> it = vp.eAllContents();
                while (it.hasNext()) {
                    EObject underTest = it.next();
                    for (EAttribute attr : underTest.eClass().getEAllAttributes()) {
                        if (attr.getEType() == DescriptionPackage.eINSTANCE.getInterpretedExpression()) {
                            Object expr = underTest.eGet(attr);
                            if (expr instanceof String && ((String) expr).length() > 0) {
                                parameters.add(new Object[] { new InterpretedExpression((String) expr, underTest, attr) });
                            }

                        }
                    }
                }
            }
        }
        return parameters;
    }

    private static Group loadVSM(URI uri) {
        ResourceSet set = new ResourceSetImpl();
        /*
         * we need an ECrossReferenceAdapter as some of the VSM implementation classes are needing it.
         */
        ECrossReferenceAdapter crossReferencer = new ECrossReferenceAdapter();
        set.eAdapters().add(crossReferencer);
        Resource res = set.getResource(uri, true);
        for (EObject root : res.getContents()) {
            if (root instanceof Group) {
                return (Group) root;
            }
        }
        return null;
    }

    /**
     * This test triggers the validation on the current expression and checks the expectations regarding the reported
     * statuses (there is an error, or not) are fulfilled.
     */
    @Test
    public void matchesExpectationsRegardingValidation() {
        String expression = this.interpretedExpToTest.getExpression();
        IInterpreter interpreterForExpression = CompoundInterpreter.INSTANCE.getInterpreterForExpression(expression);

        Collection<IInterpreterStatus> errors = new LinkedHashSet<>();
        if (interpreterForExpression.supportsValidation()) {
            IInterpreterContext context = SiriusInterpreterContextFactory.createInterpreterContext(this.interpretedExpToTest.getDeclaration(), this.interpretedExpToTest.getFeature());
            errors = MultiLanguagesValidator.getInstance().validateExpression(context, expression).getStatuses();
            if (errors.size() > 0) {
                String message = interpretedExpToTest.toString() + "triggers unexpected errors \n" + Joiner.on('\n').join(Iterables.transform(errors, new Function<IInterpreterStatus, String>() {

                    @Override
                    public String apply(IInterpreterStatus input) {
                        return input.getSeverity() + " : " + input.getMessage();
                    }
                }));

                fail(message);
            }
        }

    }

}

class InterpretedExpression {

    private String expression;

    private EObject declaration;

    private EAttribute feature;

    public InterpretedExpression(String expression, EObject declaration, EAttribute feature) {
        super();
        this.expression = expression;
        this.declaration = declaration;
        this.feature = feature;
    }

    public String getExpression() {
        return expression;
    }

    public EObject getDeclaration() {
        return declaration;
    }

    public EAttribute getFeature() {
        return feature;
    }

    @Override
    public String toString() {
        return getFeature().getName() + " : " + expression + " in " + qualifiedName(getDeclaration());
    }

    private String qualifiedName(EObject declaration) {
        List<String> segments = new ArrayList<>();

        EObject cur = declaration;
        while (cur != null) {

            Object label = getIfThere(cur, "label");
            if (label instanceof String) {
                segments.add((String) label);
            } else {
                Object name = getIfThere(cur, "name");
                if (name instanceof String) {
                    segments.add((String) name);
                } else {
                    segments.add(cur.eClass().getName());
                }

            }
            cur = cur.eContainer();
        }
        return Joiner.on(':').join(Lists.reverse(segments));
    }

    private Object getIfThere(EObject cur, String name) {
        EStructuralFeature feature = cur.eClass().getEStructuralFeature(name);
        if (feature != null) {
            return cur.eGet(feature);
        }
        return null;
    }

}

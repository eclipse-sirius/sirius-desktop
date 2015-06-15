/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
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
    public void testVariableTypesInterpretedExpressionEAnnotation() {
        Multimap<EAttribute, String> wrongTypes = ArrayListMultimap.create();

        for (EAttribute attr : interpretedExpressions) {
            EAnnotation varAnnotations = attr.getEAnnotation(AbstractInterpretedExpressionTestCase.VARIABLES);
            if (varAnnotations != null) {
                for (String varName : varAnnotations.getDetails().keySet()) {
                    String doc = varAnnotations.getDetails().get(varName);
                    if (doc != null && doc.indexOf("|") != -1) {
                        String typeName = doc.substring(0, doc.indexOf("|")).trim();
                        String errorMessage = validateVariableType(typeName);
                        if (errorMessage != null) {
                            wrongTypes.put(attr, varName + ":" + typeName + " > " + errorMessage);
                        }
                    }
                }
            }
        }

        Assert.assertTrue(getMessage(wrongTypes), wrongTypes.isEmpty());
    }

    private String getMessage(Multimap<EAttribute, String> wrongTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append(wrongTypes.size());
        sb.append(" variable(s) available in interpreted expressions need type correction:");
        for (EAttribute attr : wrongTypes.keySet()) {
            sb.append("\n . ");
            sb.append(attr.eResource().getURIFragment(attr));
            for (String v : wrongTypes.get(attr)) {
                sb.append("\n   . " + v);
            }
        }
        return sb.toString();
    }

    private String validateVariableType(String typeName) {
        String errorMessage = "cannot be checked, the test method must be improved";
        if ("ecore.EObject".equals(typeName) || "EObject".equals(typeName)) {
            errorMessage = null;
        } else {
            int indexOf = typeName.indexOf(".");
            String pName = typeName.substring(0, indexOf).trim();
            String cName = typeName.substring(indexOf + 1).trim();

            EPackage ePackage = getEPackage(pName, getAvailablePackages());
            if (ePackage != null) {
                EClassifier eClassifier = ePackage.getEClassifier(cName);
                if (eClassifier == null) {
                    errorMessage = "the EClass " + cName + " has not been found in the indicated EPackage.";
                } else {
                    // Valid type.
                    errorMessage = null;
                }
            } else {
                errorMessage = "the EPackage" + pName + " might not be accessible for the expression.";
            }
        }
        return errorMessage;
    }

    private Collection<EPackage> getAvailablePackages() {
        LinkedHashSet<EPackage> availablePackages = Sets.newLinkedHashSet();
        availablePackages.add(basePackage);
        availablePackages.add(getDialectPackage());
        availablePackages.add(ViewpointPackage.eINSTANCE);
        return availablePackages;
    }

    /**
     * Allow to indicate which is the dialect package (in case of sur diagram
     * types or dialect extension).
     * 
     * @return the dialect package (or core base package)
     */
    protected EPackage getDialectPackage() {
        return getBasePackage();
    }

    private EPackage getEPackage(String pName, Collection<EPackage> packages) {
        EPackage found = null;
        for (EPackage p : packages) {
            if (p.getName().equals(pName)) {
                found = p;
            } else {
                found = getEPackage(pName, p.getESubpackages());
            }

            if (found != null) {
                break;
            }
        }
        return found;

    }

    /**
     * Test that all interpreted expression variables have a known type.
     */
    public void testVariablesInInterpretedExpressionEAnnotation() {
        List<EAttribute> nonDocumented = Lists.newArrayList();

        Predicate<EAttribute> needsDocumentation = new Predicate<EAttribute>() {
            @Override
            public boolean apply(EAttribute input) {
                EAnnotation eAnnotation = input.getEAnnotation(AbstractInterpretedExpressionTestCase.VARIABLES);
                return eAnnotation == null;
            }
        };

        Iterables.addAll(nonDocumented, Iterables.filter(interpretedExpressions, needsDocumentation));

        // Exceptions
        nonDocumented.remove(ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT);

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

    /**
     * Assert variable existence.
     * 
     * @param tool
     *            the tool owning the variable
     * @param expectedVariable
     *            the expected variable
     * @param variables
     *            the variables
     */
    protected void assertVariableExistence(AbstractToolDescription tool, String expectedVariable, Set<String> variables) {
        assertTrue("The interpreter context for " + tool.getName() + " should contains the variable " + expectedVariable, variables.contains(expectedVariable));
    }

    /**
     * Assert variable existence and test type.
     * 
     * @param tool
     *            the tool owning the variable
     * @param expectedVariable
     *            the expected variable
     * @param expectedType
     *            the expected type
     * @param variables
     *            the variables
     * @param variablesToType
     *            the variable types
     */
    protected void assertVariableExistenceAndType(AbstractToolDescription tool, String expectedVariable, String expectedType, Set<String> variables, Map<String, VariableType> variablesToType) {
        assertVariableExistence(tool, expectedVariable, variables);
        assertEquals("The interpreter context for " + tool.eClass().getName() + " has a bad variable type for variable " + expectedVariable, VariableType.fromString(expectedType).toString(),
                variablesToType.get(expectedVariable).toString());
    }
}

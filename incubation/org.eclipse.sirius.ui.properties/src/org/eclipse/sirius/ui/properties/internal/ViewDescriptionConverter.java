/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.eef.EEFContainerDescription;
import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Interprets the high-level property views description defined in a Sirius VSM
 * into a lower-level EEFViewDescription suitable for the EEF runtime.
 */
public class ViewDescriptionConverter {
    private static class DomainClassTester implements Predicate<EObject> {
        private static final Pattern SEPARATOR = Pattern.compile("(::?|\\.)");

        private final String packageName;

        private final String className;

        public DomainClassTester(String domainClassName) {
            Matcher m = Pattern.compile("(::?|\\.)").matcher(domainClassName);
            if (m.find()) {
                packageName = domainClassName.substring(0, m.start());
                className = domainClassName.substring(m.end());
            } else {
                packageName = null;
                className = domainClassName;
            }

        }

        @Override
        public boolean apply(EObject input) {
            if (input != null) {
                EClass klass = input.eClass();
                boolean packageMatch = packageName == null || packageName.equals(klass.getEPackage().getName());
                return packageMatch && (className == null || className.equals(klass.getName()));
            } else {
                return false;
            }
        }

    }

    private final IInterpreter itp;

    private final EObject viewTarget;

    private final List<PageDescription> pageDescriptions;

    public ViewDescriptionConverter(Session session, EObject viewTarget, List<PageDescription> pageDescriptions) {
        this.itp = new SiriusInterpreter(session);
        this.viewTarget = viewTarget;
        this.pageDescriptions = pageDescriptions;
    }

    public EEFViewDescription convert() {
        EEFViewDescription wrappingView = EefFactory.eINSTANCE.createEEFViewDescription();
        for (PageDescription pageDescription : pageDescriptions) {
            instantiatePages(pageDescription, wrappingView);
        }

        return wrappingView;
    }

    /**
     * Instanciates a PageDescription for a concrete semantic target (the
     * top-level input int the page's case), and put the resulting EEF pages &
     * groups in the specified view.
     */
    private void instantiatePages(PageDescription pageDescription, EEFViewDescription view) {
        List<EObject> candidates = computeCandidates(viewTarget, pageDescription.getSemanticCandidateExpression(), pageDescription.getDomainClass());
        for (EObject pageTarget : candidates) {
            createPage(pageDescription, pageTarget, view);
        }
    }

    /**
     * Creates a concrete page instance bound to a specific semantic target.
     */
    private void createPage(PageDescription pageDescription, EObject pageTarget, EEFViewDescription view) {
        EEFPageDescription page = EefFactory.eINSTANCE.createEEFPageDescription();
        page.setIdentifier(pageDescription.getIdentifier());
        page.setLabelExpression(computeString(pageTarget, pageDescription.getLabelExpression()));

        for (GroupDescription groupDescription : pageDescription.getGroups()) {
            instantiateGroups(groupDescription, page, pageTarget, view);
        }

        view.getPages().add(page);
    }

    /**
     * Instanciates a GroupDescription for a concrete semantic target, and put
     * the resulting EEF pages & groups in the specified view.
     */
    private void instantiateGroups(GroupDescription groupDescription, EEFPageDescription page, EObject pageTarget, EEFViewDescription view) {
        List<EObject> candidates = computeCandidates(pageTarget, groupDescription.getSemanticCandidateExpression(), groupDescription.getDomainClass());
        for (EObject groupTarget : candidates) {
            page.getGroups().add(createGroup(groupDescription, groupTarget, view));
        }
    }

    /**
     * Creates a concrete group instance bound to a specific semantic target.
     */
    private EEFGroupDescription createGroup(GroupDescription groupDescription, EObject groupTarget, EEFViewDescription view) {
        EEFGroupDescription group = EefFactory.eINSTANCE.createEEFGroupDescription();
         group.setIdentifier(groupDescription.getIdentifier());
         // TODO: should be setLabel()
         group.setLabelExpression(computeString(groupTarget, groupDescription.getLabelExpression()));
        
         convertGroupContents(groupDescription, group);
         view.getGroups().add(group);
         return group;
    }

    private void convertGroupContents(GroupDescription groupDescription, EEFGroupDescription group) {
        EEFContainerDescription containerDesc = EefFactory.eINSTANCE.createEEFContainerDescription();

        for (WidgetDescription widgetDescription : groupDescription.getContainer().getWidgets()) {
            if (widgetDescription instanceof TextDescription) {
                containerDesc.getWidgets().add(createEEFTextDescription((TextDescription) widgetDescription));
            }
        }

        group.setContainer(containerDesc);
    }

    private EEFTextDescription createEEFTextDescription(TextDescription textDescription) {
        EEFTextDescription eefTextDescription = EefFactory.eINSTANCE.createEEFTextDescription();

        eefTextDescription.setIdentifier(textDescription.getIdentifier());
        eefTextDescription.setLabelExpression(textDescription.getLabelExpression());
        eefTextDescription.setValueExpression(textDescription.getValueExpression());

        InitialOperation initialOperation = textDescription.getInitialOperation();
        eefTextDescription.setEditExpression("aql:self.executeOperation('" + EcoreUtil.getURI(initialOperation).toString() + "')");
        return eefTextDescription;
    }

    private static Map<String, Object> singletonEnv(String name, Object value) {
        Map<String, Object> env = Maps.newHashMap();
        env.put(name, value);
        return env;
    }

    private String computeString(EObject self, String expression) {
        IEvaluationResult result = itp.evaluateExpression(singletonEnv("self", self), expression);
        if (result.success()) {
            return result.asString();
        } else {
            log(result.getDiagnostic());
            return "";
        }
    }

    private List<EObject> computeCandidates(EObject self, String semanticCandidatesExpression, String domainClass) {
        IEvaluationResult result = itp.evaluateExpression(singletonEnv("self", self), isBlank(semanticCandidatesExpression) ? "var:self" : semanticCandidatesExpression);
        if (result.success()) {
            return Lists.newArrayList(Iterables.filter(result.asEObjects(), new DomainClassTester(domainClass)));
        } else {
            log(result.getDiagnostic());
            return Collections.emptyList();
        }
    }
    
    /**
     * Tests if a string is blank (i.e. null, empty, or containing only whitespace).
     *
     * @param s
     *            the string to test.
     * @return <code>true</code> iff the string is blank.
     */
    private boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

    private void log(Diagnostic diagnostic) {
        // CHECKSTYLE:OFF
        System.err.println(diagnostic);
        // CHECKSTYLE:ON
    }
}

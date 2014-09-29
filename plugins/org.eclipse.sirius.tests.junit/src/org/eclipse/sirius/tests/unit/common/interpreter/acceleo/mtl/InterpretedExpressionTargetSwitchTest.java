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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.internal.helper.ModelInitializer;
import org.eclipse.sirius.tests.support.internal.helper.ModelInitializer.Scope;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

/**
 * A test ensuring that the {@link IInterpretedExpressionTargetSwitch} defined
 * for each package (tree, sequence...) are complete (e.g. they allow to
 * retrieve a domain class for any interpreted expression).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class InterpretedExpressionTargetSwitchTest extends SiriusTestCase {

    private static final String NEW_LINE = "\n";

    private static final String PUCE = " . ";

    private static final String DOMAIN_CLASS_FOR_TEST = "DomainClass";

    private Group group;

    private Multiset<EAttribute> test_expression_issue = HashMultiset.create();

    private Multiset<EAttribute> test_expression_ok = HashMultiset.create();

    private Set<EAttribute> data_defined_expressions = Sets.newHashSet();

    private int data_expression_without_instance;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        group = DescriptionFactory.eINSTANCE.createGroup();

        // Step 1: Create a resource set
        ResourceSet resourceSet = new ResourceSetImpl();

        // Step 2: Get the URI of the model file.
        URI vsmURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/test.odesign", true);

        // Step 3: Create a resource for this file.
        Resource resource = resourceSet.createResource(vsmURI);

        // Step 4: Add the initial model object to the contents.
        resource.getContents().add(group);

        // Step 5: Add cross referencer to be able to retrieve some references
        // (old EOperations and bi-directional references replacements)
        resource.eAdapters().add(new ECrossReferenceAdapter());

        // Step 6: Save the contents of the resource to the file system.
        resource.save(Collections.emptyMap());

    }

    public void testInterpretedExpressionTargetOnGroup() {
        doTestInterpretedExpressionSwitchOnRoot(group, getGlobalScope());
    }

    private Scope getGlobalScope() {
        List<EPackage> scope = Lists.newArrayList();
        scope.add(org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE);
        scope.add(DescriptionPackage.eINSTANCE);
        scope.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
        scope.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE);
        scope.add(org.eclipse.sirius.tree.description.DescriptionPackage.eINSTANCE);

        // Look for descriptions sub packages.
        for (EPackage pkg : Lists.newArrayList(scope)) {
            scope.addAll(pkg.getESubpackages());
        }

        List<EClass> doNotInstanciate = Lists.newArrayList();
        // doNotInstanciate.add(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE.getEditionTableExtensionDescription());

        return new Scope(scope, doNotInstanciate);
    }

    public void testInterpretedExpressionTargetOnTree() {
        Viewpoint vp = DescriptionFactory.eINSTANCE.createViewpoint();
        group.getOwnedViewpoints().add(vp);

        Scope scope = new Scope(Collections.singleton(org.eclipse.sirius.tree.description.DescriptionPackage.eINSTANCE));

        doTestInterpretedExpressionSwitchOnRoot(vp, scope);
    }

    public void testInterpretedExpressionTargetOnTable() {
        Viewpoint vp = DescriptionFactory.eINSTANCE.createViewpoint();
        group.getOwnedViewpoints().add(vp);

        Scope scope = new Scope(Collections.singleton(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE), Collections.<EClass> emptySet());

        doTestInterpretedExpressionSwitchOnRoot(vp, scope);
    }

    private void doTestInterpretedExpressionSwitchOnRoot(EObject root, Scope scope) {
        // Step 1: Init test data.
        List<EObject> createdElements = initVSM(root, scope);

        // Step 1.1: Save the vsm.
        try {
            root.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            fail("The generated odesign cannot be saved");
        }

        // Step 2: Check test data.
        StringBuilder dataIssues = checkTestedData(scope, createdElements);

        // Step 3: Check Interpreted Expression Switches.
        Collection<String> targetIssues = Sets.newLinkedHashSet();
        doTestInterpretedExpressionTargetSwitch(root, targetIssues);

        // Step 4: Report error if needed.
        assertTrue(getErrorReport(dataIssues, targetIssues), targetIssues.isEmpty() && dataIssues.length() == 0);
    }

    private List<EObject> initVSM(EObject root, Scope scope) {
        List<EObject> allCreatedElements = Lists.newArrayList(Iterators.filter(EcoreUtil.getAllContents(root.eResource(), true), EObject.class));

        // Step 1: setting domain class feature if possible
        ModelInitializer modelInitializer = new VSMTestModelInitializer(scope);
        List<EObject> createdElements = modelInitializer.initializeContents(root);
        allCreatedElements.addAll(createdElements);

        // Step 2: Init all references with and between created elements when
        // possible.
        modelInitializer.linkElements(allCreatedElements);

        return allCreatedElements;
    }

    private void doTestInterpretedExpressionTargetSwitch(EObject element, Collection<String> expressionWithoutTargetDomainClass) {

        // Step 1: testing all interpreted expressions of the given element
        for (EAttribute attribute : element.eClass().getEAllAttributes()) {
            if (DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(attribute.getEAttributeType())) {
                checkTargetDomainClass(element, attribute, expressionWithoutTargetDomainClass);
            }
        }

        // Step 2: test its children
        for (EObject child : element.eContents()) {
            doTestInterpretedExpressionTargetSwitch(child, expressionWithoutTargetDomainClass);
        }
    }

    private void checkTargetDomainClass(EObject element, EAttribute attribute, Collection<String> expressionWithoutTargetDomainClass) {
        IInterpretedExpressionQuery query = DialectManager.INSTANCE.createInterpretedExpressionQuery(element, attribute);

        // Step 1 : getting the DomainClass of the target
        Option<Collection<String>> targetDomainClassesOption = null;
        try {
            targetDomainClassesOption = query.getTargetDomainClasses();
        } catch (Exception e) {

            System.out.println(e.getStackTrace());
            // An exception occurs when looking for target class.
        }

        // Step 2 : analyze the result
        if (targetDomainClassesOption != null) {
            if (targetDomainClassesOption.some()) {
                if (targetDomainClassesOption.get().contains(DOMAIN_CLASS_FOR_TEST)) {
                    test_expression_ok.add(attribute);
                } else if (element instanceof ViewValidationRule || (element instanceof ValidationFix && element.eContainer() instanceof ViewValidationRule)
                        && targetDomainClassesOption.get().contains("diagram.DDiagramElement")) {
                    test_expression_ok.add(attribute);
                } else {
                    // The entered domain class was not found.
                    expressionWithoutTargetDomainClass.add(getDisplay(element.eClass(), attribute));
                    test_expression_issue.add(attribute);
                }
            } else {
                // See IInterpretedExpressionTargetSwitch#doSwitch :
                // Option<Collection<String>> javadoc
                // Feature which do not need target type.
                test_expression_ok.add(attribute);
            }
        } else {
            expressionWithoutTargetDomainClass.add(getDisplay(element.eClass(), attribute));
            test_expression_issue.add(attribute);
        }
    }

    private String getDisplay(EClass eClass, EAttribute attribute) {
        StringBuilder sb = new StringBuilder();
        EObject tmp = eClass.eContainer();
        while (tmp != null) {
            if (tmp instanceof EPackage) {
                sb.insert(0, ((EPackage) tmp).getName() + ".");
            }
            tmp = tmp.eContainer();
        }

        return sb.toString() + eClass.getName() + "#" + attribute.getName();
    }

    private StringBuilder checkTestedData(Scope scope, List<EObject> createdElements) {
        StringBuilder sb = new StringBuilder();

        final Collection<EClass> instanciatedEClasses = Sets.newHashSet();
        for (EObject instance : createdElements) {
            instanciatedEClasses.add(instance.eClass());
        }

        final Collection<EAttribute> abstractIntExp = Sets.newLinkedHashSet();
        final Collection<EAttribute> instanciatedIntExp = Sets.newLinkedHashSet();
        final Collection<EAttribute> nonDirectlyIntExp = Lists.newArrayList();

        // Check all classes of the scope.
        inspectTestedData(scope, instanciatedEClasses, abstractIntExp, instanciatedIntExp, nonDirectlyIntExp);

        analyzeResult(sb, abstractIntExp, instanciatedIntExp, nonDirectlyIntExp);

        return sb;
    }

    private void analyzeResult(StringBuilder sb, Collection<EAttribute> abstractIntExp, Collection<EAttribute> instanciatedIntExp, Collection<EAttribute> nonDirectlyInstanciatedIntExp) {
        if (!nonDirectlyInstanciatedIntExp.isEmpty()) {
            Set<EAttribute> neverInstanciatedExpressionOfConcreteTypes = Sets.newHashSet(nonDirectlyInstanciatedIntExp);
            neverInstanciatedExpressionOfConcreteTypes.removeAll(instanciatedIntExp);
            data_expression_without_instance = neverInstanciatedExpressionOfConcreteTypes.size();
            if (!neverInstanciatedExpressionOfConcreteTypes.isEmpty()) {
                sb.append("Some interpreted expressions from non instantiated classes will not be directly be tested:\n");
                for (EAttribute attr : neverInstanciatedExpressionOfConcreteTypes) {
                    if (DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(attr.getEAttributeType())) {
                        sb.append(PUCE + getDisplay((EClass) attr.eContainer(), attr) + NEW_LINE);
                    }
                }
                sb.append(NEW_LINE);
            }
        }

        // Remove the instanciated through inheritance expressions.
        Iterables.removeAll(abstractIntExp, instanciatedIntExp);
        if (!abstractIntExp.isEmpty()) {
            sb.append("Some interpreted expressions from abstract or interface will not be tested:\n");
            for (EAttribute attr : abstractIntExp) {
                sb.append(PUCE + getDisplay((EClass) attr.eContainer(), attr) + NEW_LINE);
            }
            sb.append(NEW_LINE);
        }
    }

    private void inspectTestedData(Scope scope, Collection<EClass> instanciatedEClasses, Collection<EAttribute> abstractIntExp, Collection<EAttribute> instanciatedIntExp,
            Collection<EAttribute> nonDirectlyInstanciatedIntExp) {
        for (EPackage pkg : scope.getScope()) {
            for (EClass eClass : Iterables.filter(Iterables.filter(pkg.getEClassifiers(), EClass.class), Predicates.not(Predicates.in(scope.getEclassesToAvoid())))) {
                boolean concrete = !(eClass.isAbstract() || eClass.isInterface());
                boolean instanciated = concrete && instanciatedEClasses.contains(eClass);
                if (instanciated) {
                    // EClass with created instances.
                    for (EAttribute attribute : eClass.getEAllAttributes()) {
                        if (DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(attribute.getEAttributeType())) {
                            instanciatedIntExp.add(attribute);
                            data_defined_expressions.add(attribute);
                        }
                    }
                } else {
                    // EClass without created instances.
                    for (EAttribute attribute : eClass.getEAttributes()) {
                        if (DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(attribute.getEAttributeType())) {
                            data_defined_expressions.add(attribute);
                            if (!concrete) {
                                abstractIntExp.add(attribute);
                            } else {
                                nonDirectlyInstanciatedIntExp.add(attribute);
                            }
                        }
                    }

                    if (!concrete) {
                        // Look for uncreated possibilities.
                        for (EAttribute attribute : Iterables.filter(eClass.getEAllAttributes(), Predicates.not(Predicates.in(eClass.getEAttributes())))) {
                            if (DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(attribute.getEAttributeType())) {
                                data_defined_expressions.add(attribute);
                            }
                        }
                    }
                }
            }
        }
    }

    private String getErrorReport(StringBuilder dataIssues, Collection<String> targetIssues) {
        StringBuilder sb = new StringBuilder();

        sb.append("Errors occur with Interpreter target switches, please read the report:" + NEW_LINE + NEW_LINE);

        sb.append("Test Data:" + NEW_LINE);
        sb.append(" . Defined InterpretedExpression: " + data_defined_expressions.size() + NEW_LINE);
        sb.append(" . Non instanciated expressions: " + data_expression_without_instance + NEW_LINE);

        Set<EAttribute> tested = Sets.newHashSet();
        tested.addAll(test_expression_ok.elementSet());
        tested.addAll(test_expression_issue.elementSet());

        List<EAttribute> nonTested = Lists.newArrayList(data_defined_expressions);
        nonTested.removeAll(tested);

        sb.append("Error Report:" + NEW_LINE);
        sb.append(" . Non Tested expression: " + nonTested.size() + NEW_LINE);
        sb.append(" . Tested expression: " + tested.size() + NEW_LINE);
        sb.append("   . Success: " + test_expression_ok.elementSet().size() + " (total occurences: " + test_expression_ok.size() + ")" + NEW_LINE);
        sb.append("   . Error: " + test_expression_issue.elementSet().size() + " (total occurences: " + test_expression_issue.size() + ")" + NEW_LINE);
        Set<EAttribute> intersection = Sets.intersection(test_expression_ok.elementSet(), test_expression_issue.elementSet());
        sb.append("   . Intersection: " + intersection.size() + NEW_LINE);
        sb.append(NEW_LINE);
        sb.append(getErrorMessage(targetIssues));
        sb.append(dataIssues.toString());

        if (!intersection.isEmpty()) {
            sb.append("Intersection: Ok and Errors: \n");
            for (EAttribute attr : intersection) {
                sb.append(PUCE + getDisplay(attr.getEContainingClass(), attr) + NEW_LINE);
            }
        }

        if (!nonTested.isEmpty()) {
            sb.append("Non tested references: \n");
            for (EAttribute attr : nonTested) {
                sb.append(PUCE + getDisplay(attr.getEContainingClass(), attr) + NEW_LINE);
            }
        }

        return sb.toString();
    }

    private String getErrorMessage(Collection<String> issues) {
        StringBuilder sb = new StringBuilder();
        if (!issues.isEmpty()) {
            sb.append("Failed to get the target domain Class for the interpreted expressions: \n");
            List<String> sortedIssues = Lists.newArrayList(issues);
            Collections.sort(sortedIssues);
            for (String interpretedExpressionMessage : sortedIssues) {
                sb.append(PUCE + interpretedExpressionMessage + NEW_LINE);
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    /**
     * A {@link ModelInitializer} customized to generate a complete VSM example.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    public static class VSMTestModelInitializer extends ModelInitializer {

        private int count = 0;

        public VSMTestModelInitializer(Scope scope) {
            super(scope);
        }

        /**
         * Return the first candidates.
         * 
         * {@inheritDoc}
         */
        protected EObject multiCandidateSingleRef(EObject root, EReference ref, Collection<EObject> instances) {
            // Resolve the ambiguity by adding the first found element
            // Sub classes can do something else
            return instances.iterator().next();
        }

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.tests.support.internal.helper.ModelInitializer#customizeCreatedElement(org.eclipse.emf.ecore.EObject)
         */
        @Override
        protected void customizeCreatedElement(EObject createdElement) {
            // Set the domain class of all elements (when possible)
            EStructuralFeature domainClassFeature = createdElement.eClass().getEStructuralFeature("domainClass");
            if (domainClassFeature != null) {
                createdElement.eSet(domainClassFeature, DOMAIN_CLASS_FOR_TEST);
            }

            if (createdElement instanceof SemanticValidationRule) {
                ((SemanticValidationRule) createdElement).setTargetClass(DOMAIN_CLASS_FOR_TEST);
            }

            // Set id if possible (to allow the save of the odesign)
            EStructuralFeature nameFeature = createdElement.eClass().getEStructuralFeature("name");
            if (nameFeature != null) {
                createdElement.eSet(nameFeature, String.valueOf(count++));
            }
        }

        /**
         * 
         * {@inheritDoc}
         * 
         */
        @Override
        protected boolean needsInitialization(EObject element, EReference ref) {
            return ref.isContainment() && !element.eIsSet(ref);
        }

        /**
         * 
         * {@inheritDoc}
         * 
         */
        @Override
        protected Collection<? extends EClass> findCompatibleCandidates(EObject element, EReference reference, EPackage currentScope) {
            Collection<? extends EClass> compatibleCandidates = super.findCompatibleCandidates(element, reference, currentScope);

            EObjectQuery query = new EObjectQuery(element);
            Option<EObject> parentRepDesc = query.getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getRepresentationDescription());
            boolean fromTableOrTreePackage = org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE == currentScope
                    || org.eclipse.sirius.tree.description.DescriptionPackage.eINSTANCE == currentScope;

            if (fromTableOrTreePackage && parentRepDesc.some() && parentRepDesc.get() instanceof DiagramDescription) {
                // Some table/tree tools could be created by the MM initializer
                // in diagrams tools sections (type compatibility).
                Predicate<EClass> toRemove = new Predicate<EClass>() {
                    public boolean apply(EClass input) {
                        boolean abstratToolDesc = ToolPackage.eINSTANCE.getAbstractToolDescription().isSuperTypeOf(input);
                        boolean popup = org.eclipse.sirius.tree.description.DescriptionPackage.eINSTANCE.getTreePopupMenu() == input;
                        boolean interDialect = ToolPackage.eINSTANCE.getRepresentationCreationDescription().isSuperTypeOf(input)
                                || ToolPackage.eINSTANCE.getRepresentationNavigationDescription().isSuperTypeOf(input);
                        return !interDialect && (abstratToolDesc || popup);
                    }
                };
                Iterables.removeIf(compatibleCandidates, toRemove);
            }
            return compatibleCandidates;
        }

        @Override
        public void linkElements(List<EObject> allCreatedElements) {
            super.linkElements(allCreatedElements);

            // Specific Init for old eopposite, today eoperation with xref

            Iterable<DiagramElementMapping> dem = Iterables.filter(allCreatedElements, DiagramElementMapping.class);
            if (!Iterables.isEmpty(dem)) {
                Iterables.addAll(dem.iterator().next().getDetailDescriptions(), Iterables.filter(allCreatedElements, RepresentationCreationDescription.class));
                Iterables.addAll(dem.iterator().next().getNavigationDescriptions(), Iterables.filter(allCreatedElements, RepresentationNavigationDescription.class));
            }
            Iterable<TableMapping> tm = Iterables.filter(allCreatedElements, TableMapping.class);
            if (!Iterables.isEmpty(tm)) {
                Iterables.addAll(tm.iterator().next().getDetailDescriptions(), Iterables.filter(allCreatedElements, RepresentationCreationDescription.class));
                Iterables.addAll(tm.iterator().next().getNavigationDescriptions(), Iterables.filter(allCreatedElements, RepresentationNavigationDescription.class));
            }
            Iterable<TreeMapping> treem = Iterables.filter(allCreatedElements, TreeMapping.class);
            if (!Iterables.isEmpty(treem)) {
                Iterables.addAll(treem.iterator().next().getDetailDescriptions(), Iterables.filter(allCreatedElements, RepresentationCreationDescription.class));
                Iterables.addAll(treem.iterator().next().getNavigationDescriptions(), Iterables.filter(allCreatedElements, RepresentationNavigationDescription.class));
            }
            Iterable<PasteTargetDescription> ptd = Iterables.filter(allCreatedElements, PasteTargetDescription.class);
            if (!Iterables.isEmpty(ptd)) {
                Iterables.addAll(ptd.iterator().next().getPasteDescriptions(), Iterables.filter(allCreatedElements, PasteDescription.class));
            }

            Set<DiagramElementMapping> linkedElements = Sets.newHashSet();
            for (DeleteElementDescription ded : Iterables.filter(allCreatedElements, DeleteElementDescription.class)) {
                if (ded.getMappings().isEmpty()) {
                    for (DiagramElementMapping mapping : dem) {
                        if (!linkedElements.contains(mapping)) {
                            mapping.setDeletionDescription(ded);
                            linkedElements.add(mapping);
                            break;
                        }
                    }
                }
            }
            linkedElements.clear();
            for (DirectEditLabel del : Iterables.filter(allCreatedElements, DirectEditLabel.class)) {
                if (del.getMapping().isEmpty()) {
                    for (DiagramElementMapping mapping : dem) {
                        if (!linkedElements.contains(mapping)) {
                            mapping.setLabelDirectEdit(del);
                            linkedElements.add(mapping);
                            break;
                        }
                    }
                }
            }
            linkedElements.clear();
            for (DoubleClickDescription dcd : Iterables.filter(allCreatedElements, DoubleClickDescription.class)) {
                if (dcd.getMappings().isEmpty()) {
                    for (DiagramElementMapping mapping : dem) {
                        if (!linkedElements.contains(mapping)) {
                            mapping.setDoubleClickDescription(dcd);
                            linkedElements.add(mapping);
                            break;
                        }
                    }
                }
            }
            linkedElements.clear();
            for (ReconnectEdgeDescription red : Iterables.filter(allCreatedElements, ReconnectEdgeDescription.class)) {
                if (red.getMappings().isEmpty()) {
                    for (EdgeMapping mapping : Iterables.filter(dem, EdgeMapping.class)) {
                        if (!linkedElements.contains(mapping)) {
                            mapping.getReconnections().add(red);
                            linkedElements.add(mapping);
                            break;
                        }
                    }
                }
            }
        }
    }
}

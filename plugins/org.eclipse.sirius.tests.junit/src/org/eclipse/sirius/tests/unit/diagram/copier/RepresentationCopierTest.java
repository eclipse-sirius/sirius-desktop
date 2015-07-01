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
package org.eclipse.sirius.tests.unit.diagram.copier;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.tests.unit.diagram.modelers.uml.UML2ModelerConstants;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

/**
 * Tests for the copy functionality.
 * 
 * @author mchauvin
 */
public class RepresentationCopierTest extends GenericTestCase implements UML2ModelerConstants {

    private static final String PLUGIN = "/" + SiriusTestsPlugin.PLUGIN_ID;

    private DDiagram originalDiagram;

    private DDiagram originalUseCaseDiagram;

    private Diagram originalGMFDiagram;

    private Diagram originalUseCaseGMFDiagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(PLUGIN + SEMANTIC_MODEL_PATH, PLUGIN + MODELER_PATH);

        final Viewpoint vp = viewpoints.iterator().next();
        activateViewpoint(vp.getName());

        launchRefreshCommand();

        DiagramDescription desc = findDiagramDescription("Class Diagram");
        DiagramDescription useCaseDesc = findDiagramDescription("Use Case Diagram");

        originalDiagram = getFirstDiagram(desc, session);
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER, getNumberOfDiagramElements(originalDiagram));
        assertEquals(INITIAL_DNODE_NUMBER, getNumberOfNodes(originalDiagram));

        originalUseCaseDiagram = getFirstDiagram(useCaseDesc, session);
        assertEquals(INITIAL_USECASE_DNODE_NUMBER, getNumberOfDiagramElements(originalUseCaseDiagram));

        final IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, originalDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(part, true);
        TestsUtil.synchronizationWithUIThread();

        final IEditorPart part2 = DialectUIManager.INSTANCE.openEditor(session, originalUseCaseDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(part2, true);
        TestsUtil.synchronizationWithUIThread();

        originalGMFDiagram = getGMFDiagram(originalDiagram, session);
        assertEquals(INITIAL_NODE_NUMBER, numberOfNodes(originalGMFDiagram));

        originalUseCaseGMFDiagram = getGMFDiagram(originalUseCaseDiagram, session);
        assertEquals(INITIAL_USECASE_NODE_NUMBER, numberOfNodes(originalUseCaseGMFDiagram));
    }

    public void testDiagramCreation() throws Exception {
        DiagramDescription desc = originalDiagram.getDescription();
        int beforeCopyNumberOfDiagrams = getNumberOfDiagrams(desc);
        copyDiagram("test");
        int afterCopyNumberOfDiagrams = getNumberOfDiagrams(desc);
        assertEquals(beforeCopyNumberOfDiagrams, afterCopyNumberOfDiagrams - 1);

        desc = originalUseCaseDiagram.getDescription();
        beforeCopyNumberOfDiagrams = getNumberOfDiagrams(desc);
        copyUseCaseDiagram("test");
        afterCopyNumberOfDiagrams = getNumberOfDiagrams(desc);
        assertEquals(beforeCopyNumberOfDiagrams, afterCopyNumberOfDiagrams - 1);

    }

    public void testSameNumberOfDiagramElements() throws Exception {
        DDiagram copy = copyDiagram("test");
        /* check that copy has the same number */
        assertTrue(originalDiagram != copy);
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER, getNumberOfDiagramElements(copy));

        copy = copyUseCaseDiagram("test");
        /* check that copy has the same number */
        assertTrue(originalUseCaseDiagram != copy);
        assertEquals(INITIAL_USECASE_DIAGRAM_ELEMENT_NUMBER, getNumberOfDiagramElements(copy));
    }

    public void testSameNumberOfGMFViews() throws Exception {
        DDiagram copy = copyDiagram("test");
        Diagram gmfCopy = getGMFDiagram(copy, session);
        /* check that copy has the same number of GMF views */
        assertTrue(originalGMFDiagram != gmfCopy);
        assertEquals(INITIAL_NODE_NUMBER, numberOfNodes(gmfCopy));

        copy = copyUseCaseDiagram("test");
        gmfCopy = getGMFDiagram(copy, session);
        /* check that copy has the same number of GMF views */
        assertTrue(originalUseCaseGMFDiagram != gmfCopy);
        assertEquals(INITIAL_USECASE_NODE_NUMBER, numberOfNodes(gmfCopy));
    }

    public void testNoDuplicationOfSemanticElements() throws Exception {
        EObject semanticRoot = EcoreUtil.getRootContainer(((DSemanticDiagram) originalDiagram).getTarget());
        int numberOfSemanticElementsBeforeCopy = 1 + numberOfChildren(semanticRoot);
        copyDiagram("test");
        int numberOfSemanticElementsAfterCopy = 1 + numberOfChildren(semanticRoot);
        assertEquals(numberOfSemanticElementsBeforeCopy, numberOfSemanticElementsAfterCopy);

        semanticRoot = EcoreUtil.getRootContainer(((DSemanticDiagram) originalUseCaseDiagram).getTarget());
        numberOfSemanticElementsBeforeCopy = 1 + numberOfChildren(semanticRoot);
        copyUseCaseDiagram("test");
        numberOfSemanticElementsAfterCopy = 1 + numberOfChildren(semanticRoot);
        assertEquals(numberOfSemanticElementsBeforeCopy, numberOfSemanticElementsAfterCopy);
    }

    public void testCopiedRepresentationName() throws Exception {
        final String newRepresentationName = "New Representation Space Name ";
        DDiagram copy = copyDiagram(newRepresentationName);
        /* check that copy has the name asked */
        assertTrue(originalDiagram != copy);
        assertEquals(newRepresentationName, copy.getName());

        copy = copyUseCaseDiagram(newRepresentationName);
        /* check that copy has the name asked */
        assertTrue(originalUseCaseDiagram != copy);
        assertEquals(newRepresentationName, copy.getName());
    }

    public void testReferencesFromGMFViewsToDiagramElements() throws Exception {
        final DDiagram copy = copyDiagram("test");
        final Diagram gmfCopy = getGMFDiagram(copy, session);

        Collection<DDiagramElement> orginalDiagramElements = new HashSet<DDiagramElement>();
        final Iterator<EObject> i = originalDiagram.eAllContents();
        while (i.hasNext()) {
            final EObject current = i.next();
            if (current instanceof DDiagramElement)
                orginalDiagramElements.add((DDiagramElement) current);
        }

        final Iterator<EObject> gmfDiagramIter = gmfCopy.eAllContents();
        while (gmfDiagramIter.hasNext()) {
            final EObject current = gmfDiagramIter.next();
            if (current instanceof View) {
                final View view = (View) current;
                final EObject element = view.getElement();
                if (orginalDiagramElements.contains(element))
                    fail();
            }
        }

        final DDiagram useCaseCopy = copyUseCaseDiagram("test");
        final Diagram useCaseGmfCopy = getGMFDiagram(useCaseCopy, session);

        Collection<DDiagramElement> orginalUseCaseDiagramElements = new HashSet<DDiagramElement>();
        final Iterator<EObject> k = originalUseCaseDiagram.eAllContents();
        while (k.hasNext()) {
            final EObject current = k.next();
            if (current instanceof DDiagramElement)
                orginalUseCaseDiagramElements.add((DDiagramElement) current);
        }

        final Iterator<EObject> useCaseGmfDiagramIter = useCaseGmfCopy.eAllContents();
        while (useCaseGmfDiagramIter.hasNext()) {
            final EObject current = useCaseGmfDiagramIter.next();
            if (current instanceof View) {
                final View view = (View) current;
                final EObject element = view.getElement();
                if (orginalUseCaseDiagramElements.contains(element))
                    fail();
            }
        }
    }

    public void testGMFViewsIsSetProperties() throws Exception {
        final DDiagram copy = copyDiagram("test");
        final Diagram gmfCopy = getGMFDiagram(copy, session);

        final Predicate<View> elementIsUnsetPredicate = new Predicate<View>() {
            @Override
            public boolean apply(final View input) {
                if (!input.isSetElement())
                    return true;
                return false;
            }
        };

        final int originalElementIsUnsetNumber = numberOfNodesWithPredicateTrue(originalGMFDiagram, elementIsUnsetPredicate);
        final int copyElementIsUnsetNumber = numberOfNodesWithPredicateTrue(gmfCopy, elementIsUnsetPredicate);
        assertEquals(originalElementIsUnsetNumber, copyElementIsUnsetNumber);
    }

    private DDiagram copyDiagram(final String name) {
        return copyDiagram(name, originalDiagram);
    }

    private DDiagram copyUseCaseDiagram(final String name) {
        return copyDiagram(name, originalUseCaseDiagram);
    }

    private DDiagram copyDiagram(final String name, final DDiagram diagramToCopy) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagramToCopy);
        RecordingCommand copyCommand = new RecordingCommand(domain) {

            private DRepresentation representation;

            @Override
            protected void doExecute() {
                representation = DialectManager.INSTANCE.copyRepresentation(diagramToCopy, name, session, null);
            }

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
             */
            @Override
            public Collection<?> getResult() {
                Collection<DDiagram> result = new HashSet<DDiagram>();
                result.add((DDiagram) representation);
                return result;
            }
        };
        domain.getCommandStack().execute(copyCommand);
        DDiagram copy = (DDiagram) copyCommand.getResult().iterator().next();
        return copy;
    }

    private int numberOfNodes(final View view) {
        return numberOfNodesWithPredicateTrue(view, null);
    }

    private int numberOfNodesWithPredicateTrue(final View view, final Predicate<View> predicate) {
        int sum = 0;
        for (final View childView : (List<View>) view.getChildren()) {
            if (predicate == null || predicate.apply(childView))
                sum += 1;
            sum += numberOfNodesWithPredicateTrue(childView, predicate);
        }
        return sum;
    }

    private int numberOfChildren(final EObject root) {
        int sum = 0;
        for (Iterator<EObject> i = root.eAllContents(); i.hasNext(); i.next()) {
            sum++;
        }
        return sum;
    }

    private static interface Predicate<T> {
        boolean apply(T input);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}

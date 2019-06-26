/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.layout.pinning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.tools.internal.commands.UnpinElementsCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.DiagramLayoutCustomization;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Tests for the "pinned" attribute on diagram elements and its effect on
 * layouts.
 * 
 * @author pcdavid
 */
public class PinnedElementsTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layout/pinning/model/tc1825.ecore";

    private static final String VSM_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layout/pinning/description/tc1825.odesign";

    private static final String REPRESENTATIONS_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/layout/pinning/model/tc1825.aird";

    private static final String REPRESENTATION_DESC_NAME = "Nodes, Containers and Lists (flat)";

    /*
     * Do not consider DEdges as pinnable for now, even though they can have the
     * attribute, because it is not handled by the layout algorithms.
     */
    private static final Predicate<Object> isPinnable = Predicates.instanceOf(AbstractDNode.class);

    private DDiagram diagram;

    private Set<DDiagramElement> allPinnableElements;

    private IEditorPart editorPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, VSM_PATH, REPRESENTATIONS_MODEL_PATH);

        SessionUIManager.INSTANCE.createUISession(session);
        Collection<DRepresentationDescriptor> flatDiagrams = getRepresentationDescriptors(REPRESENTATION_DESC_NAME);
        for (DRepresentationDescriptor representationDescriptor : flatDiagrams) {
            if (representationDescriptor.getName().equals("Flat_All_Unpinned")) {
                diagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        allPinnableElements = Sets.newHashSet(Iterables.filter(diagram.getDiagramElements(), isPinnable));
    }

    public void testPinElements() throws Exception {
        pin(allPinnableElements);
        for (DDiagramElement element : allPinnableElements) {
            assertTrue("Element should be pinned down.", isPinned(element));
        }
    }

    public void testUnpinElements() throws Exception {
        pin(allPinnableElements);
        for (DDiagramElement element : allPinnableElements) {
            assertTrue("Element should be pinned down.", isPinned(element));
        }
        unpin(allPinnableElements);
        for (DDiagramElement element : allPinnableElements) {
            assertFalse("Element should be un-pinned.", isPinned(element));
        }
    }

    public void testNoChangeIfAllElementsPinned() throws Exception {
        final Map<DDiagramElement, Rectangle> initialBounds = saveBounds(allPinnableElements);
        pin(allPinnableElements);
        arrangeAll((DiagramEditor) editorPart);
        final Map<DDiagramElement, Rectangle> finalBounds = saveBounds(allPinnableElements);
        assertNoBoundChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    public void _testSomethingChangesIfAllElementsUnpinned() throws Exception {
        final Map<DDiagramElement, Rectangle> initialBounds = saveBounds(allPinnableElements);
        unpin(allPinnableElements);
        arrangeAll((DiagramEditor) editorPart);
        final Map<DDiagramElement, Rectangle> finalBounds = saveBounds(allPinnableElements);
        assertSomeBoundsChanged(initialBounds, finalBounds);
        assertNoOverlapsOnPinnedElements(finalBounds);
    }

    private void pin(Collection<? extends DDiagramElement> elements) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new PinElementsCommand(elements));
    }

    private void unpin(Collection<? extends DDiagramElement> elements) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new UnpinElementsCommand(elements));
    }

    private void arrangeAll(final DiagramEditor editorPart) {
        ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
        arrangeRequest.setPartsToArrange(Collections.singletonList(editorPart));
        editorPart.getDiagramEditPart().performRequest(arrangeRequest);
    }

    private void assertNoBoundChanged(final Map<DDiagramElement, Rectangle> initialBounds, final Map<DDiagramElement, Rectangle> finalBounds) {
        for (DDiagramElement dde : initialBounds.keySet()) {
            assertEquals(initialBounds.get(dde), finalBounds.get(dde));
        }
    }

    private void assertSomeBoundsChanged(final Map<DDiagramElement, Rectangle> initialBounds, final Map<DDiagramElement, Rectangle> finalBounds) {
        int changes = 0;
        for (DDiagramElement dde : initialBounds.keySet()) {
            if (!initialBounds.get(dde).equals(finalBounds.get(dde))) {
                changes += 1;
            }
        }
        assertTrue(changes >= 1);
    }

    public void assertNoOverlapsOnPinnedElements(final Map<DDiagramElement, Rectangle> bounds) {
        final DiagramLayoutCustomization layoutCustomization = new DiagramLayoutCustomization();
        layoutCustomization.initializePaddingWithEditParts(new ArrayList<DDiagramElement>(bounds.keySet()));
        for (DDiagramElement elt : bounds.keySet()) {
            if (isPinned(elt)) {
                for (DDiagramElement other : bounds.keySet()) {
                    if (other != elt && !isPinned(other)) {
                        final Rectangle expandedBounds1 = bounds.get(elt).getExpanded(layoutCustomization.getNodePadding(getEditPart(elt)));
                        final Rectangle expandedBounds2 = bounds.get(other).getExpanded(layoutCustomization.getNodePadding(getEditPart(other)));
                        assertFalse(expandedBounds1.intersects(expandedBounds2));
                    }
                }
            }
        }
    }

    private Map<DDiagramElement, Rectangle> saveBounds(final Collection<? extends DDiagramElement> elements) {
        final Map<DDiagramElement, Rectangle> bounds = new HashMap<>();
        for (DDiagramElement dde : elements) {
            final IGraphicalEditPart part = getEditPart(dde);
            if (part != null) {
                final Rectangle b = part.getFigure().getBounds();
                bounds.put(dde, b);
            }
        }
        return bounds;
    }

    public static boolean isPinned(final IDiagramElementEditPart self) {
        final EObject diagramElement = self.resolveDiagramElement();
        final List<ArrangeConstraint> constraints = getArrangeConstraints(diagramElement);
        return constraints != null && hasAllPinnedConstraints(constraints);
    }

    public static boolean isPinned(final DDiagramElement diagramElement) {
        final List<ArrangeConstraint> constraints = getArrangeConstraints(diagramElement);
        return constraints != null && hasAllPinnedConstraints(constraints);
    }

    private static List<ArrangeConstraint> getArrangeConstraints(final EObject diagramElement) {
        final List<ArrangeConstraint> constraints;
        if (diagramElement instanceof AbstractDNode) {
            final AbstractDNode node = (AbstractDNode) diagramElement;
            constraints = node.getArrangeConstraints();
        } else if (diagramElement instanceof DEdge) {
            final DEdge edge = (DEdge) diagramElement;
            constraints = edge.getArrangeConstraints();
        } else {
            constraints = Collections.emptyList();
        }
        return constraints;
    }

    private static boolean hasAllPinnedConstraints(final List<ArrangeConstraint> constraints) {
        return constraints.contains(ArrangeConstraint.KEEP_LOCATION) && constraints.contains(ArrangeConstraint.KEEP_SIZE) && constraints.contains(ArrangeConstraint.KEEP_RATIO);
    }

    @Override
    protected void tearDown() throws Exception {
        SessionUIManager.INSTANCE.getUISession(session).closeEditors(false, Collections.singleton((DDiagramEditor) editorPart));
        TestsUtil.emptyEventsFromUIThread();
        super.tearDown();
    }
}

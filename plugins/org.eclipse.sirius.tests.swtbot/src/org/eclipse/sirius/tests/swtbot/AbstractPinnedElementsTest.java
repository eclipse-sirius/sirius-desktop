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
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.DiagramLayoutCustomization;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.hamcrest.core.IsAnything;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Tests for the "pin elements" feature.
 * 
 * @author pcdavid
 */
public class AbstractPinnedElementsTest extends AbstractSiriusSwtBotGefTestCase {

    protected static class IsPinnedMatcher extends BaseMatcher<IDiagramElementEditPart> {

        /**
         * {@inheritDoc}
         */
        public boolean matches(Object item) {
            if (item instanceof IDiagramElementEditPart) {
                return isDiagramElementEditPartPinned((IDiagramElementEditPart) item);
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        public void describeTo(Description description) {
            String message = "diagram element is not pinned";
            description.appendText(message);
        }
    }

    protected static class WaitForPinnedStatus extends DefaultCondition {

        private final Matcher<IDiagramElementEditPart> matcher;

        private final IDiagramElementEditPart editPart;

        /**
         * Constuctor.
         * 
         * @param editPart
         *            Edit part to match.
         * 
         * @param matcher
         *            Matcher.
         */
        public WaitForPinnedStatus(IDiagramElementEditPart editPart, Matcher<IDiagramElementEditPart> matcher) {
            this.editPart = editPart;
            this.matcher = matcher;
        }

        /**
         * {@inheritDoc}
         */
        public String getFailureMessage() {
            StringDescription ds = new StringDescription();
            matcher.describeTo(ds);
            return ds.toString();
        }

        /**
         * {@inheritDoc}
         */
        public boolean test() throws Exception {
            return matcher.matches(editPart);
        }

    }

    protected static final String DATA_UNIT_DIR = "data/unit/layout/pinning/";

    protected static final String FILE_DIR = "model/";

    protected UIResource sessionAirdResource;

    protected UILocalSession localSession;

    protected void openDiagram(final String viewpointName, final String representationDescriptionName, final String diagramName) {
        editor = openDiagram(localSession.getOpenedSession(), representationDescriptionName, diagramName, DDiagram.class);
        editor.setSnapToGrid(false);
        SWTBotUtils.waitAllUiEvents();
    }

    protected Map<IGraphicalEditPart, Rectangle> saveBounds() {
        final Map<IGraphicalEditPart, Rectangle> bounds = Maps.newHashMap();

        @SuppressWarnings("unchecked")
        final List<SWTBotGefEditPart> parts = editor.editParts((Matcher<? extends EditPart>) IsAnything.anything());

        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                for (final SWTBotGefEditPart partBot : parts) {
                    final EditPart rawPart = partBot.part();
                    rawPart.refresh();
                    if (rawPart instanceof IDiagramElementEditPart && !(rawPart instanceof DiagramEditPart)) {
                        final IGraphicalEditPart gep = (IGraphicalEditPart) rawPart;
                        final Rectangle b = gep.getFigure().getBounds().getCopy();
                        bounds.put(gep, b);
                    }
                }
            }
        });

        SWTBotUtils.waitAllUiEvents();
        return bounds;
    }

    protected void assertNoBoundChanged(final Map<IGraphicalEditPart, Rectangle> initialBounds, final Map<IGraphicalEditPart, Rectangle> finalBounds) {
        for (final IGraphicalEditPart part : initialBounds.keySet()) {
            assertEquals(initialBounds.get(part), finalBounds.get(part));
        }
    }

    protected void assertSomeBoundsChanged(final Map<IGraphicalEditPart, Rectangle> initialBounds, final Map<IGraphicalEditPart, Rectangle> finalBounds) {
        int changes = 0;
        for (final IGraphicalEditPart gep : initialBounds.keySet()) {
            if (!initialBounds.get(gep).equals(finalBounds.get(gep))) {
                changes += 1;
            }
        }
        assertThat("Some bounds should have changed.", changes, greaterThan(0));
    }

    protected void assertNoOverlapsOnPinnedElements(final Map<IGraphicalEditPart, Rectangle> bounds) {
        final DiagramLayoutCustomization layoutCustomization = new DiagramLayoutCustomization();
        layoutCustomization.initializePaddingWithEditParts(Lists.newArrayList(bounds.keySet()));
        for (final IGraphicalEditPart elt : bounds.keySet()) {
            if (isEditPartPinned(elt)) {
                for (final IGraphicalEditPart other : bounds.keySet()) {
                    if (other != elt && !isEditPartPinned(other) && other.getParent() == elt.getParent()) {
                        final Rectangle bounds1 = bounds.get(elt);
                        final Rectangle bounds2 = bounds.get(other);
                        final Insets padding1 = layoutCustomization.getNodePadding(elt);
                        final Insets padding2 = layoutCustomization.getNodePadding(other);
                        final Rectangle expandedBounds1 = bounds1.getExpanded(padding1);
                        final Rectangle expandedBounds2 = bounds2.getExpanded(padding2);
                        final boolean overlap = bounds1.intersects(expandedBounds2) || bounds2.intersects(expandedBounds1);
                        assertFalse("Overlap detected between pinned element " + description(elt, bounds1, padding1) + " and unpinned element " + description(other, bounds2, padding2), overlap);
                    }
                }
            }
        }
    }

    private String description(final IGraphicalEditPart part, final Rectangle bounds, final Insets padding) {
        final String suffix = " { bounds: " + bounds + "; padding: " + padding + "}";
        if (part instanceof IDiagramElementEditPart) {
            final IDiagramElementEditPart deep = (IDiagramElementEditPart) part;
            return deep.resolveDiagramElement().getName() + suffix;
        } else {
            return part.toString() + suffix;
        }
    }

    private static boolean isEditPartPinned(final IGraphicalEditPart self) {
        if (self instanceof IDiagramElementEditPart) {
            return isDiagramElementEditPartPinned((IDiagramElementEditPart) self);
        } else {
            return false;
        }
    }

    private static boolean isDiagramElementEditPartPinned(final IDiagramElementEditPart self) {
        final EObject diagramElement = self.resolveDiagramElement();
        final List<ArrangeConstraint> constraints = getArrangeConstraints(diagramElement);
        return constraints != null && hasAllPinnedConstraints(constraints);
    }

    protected static IsPinnedMatcher isPinnedMatcher() {
        return new IsPinnedMatcher();
    }

    protected static WaitForPinnedStatus waitForPinned(IDiagramElementEditPart editPart) {
        return new WaitForPinnedStatus(editPart, isPinnedMatcher());
    }

    protected static WaitForPinnedStatus waitForNotPinned(IDiagramElementEditPart editPart) {
        return new WaitForPinnedStatus(editPart, not(isPinnedMatcher()));
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
}

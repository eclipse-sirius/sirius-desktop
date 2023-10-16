/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.CompoundLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.GridLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;

/**
 * A tree ordered LayoutProvider, used for diagrams which have configured a
 * {@link OrderedTreeLayout}.
 * <p>
 * Uses a properly configured {@link GridLayoutProvider} internally, a tree
 * being seen as a partially filled grid.
 * 
 * @author mchauvin
 */
public class OrderedTreeLayoutProvider implements LayoutProvider {
    /**
     * Simple flag to easily switch between the old/new behaviors during
     * development.
     */
    private static final boolean PIN_MANAGEMENT_ON_TREE_LAYOUT = true;

    /**
     * The underlying grid provider.
     */
    private GridLayoutProvider airGridLayoutProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractLayoutEditPartProvider getLayoutNodeProvider(final IGraphicalEditPart container) {
        AbstractLayoutProvider layoutProvider = null;
        if (isDDiagramWithConfiguredOrderedTreeLayout(container)) {
            if (PIN_MANAGEMENT_ON_TREE_LAYOUT) {
                final CompoundLayoutProvider clp = new CompoundLayoutProvider();
                GridLayoutProvider layoutNodeProvider = getGridLayoutProvider();
                clp.addProvider(layoutNodeProvider);
                clp.addProvider(new PinnedElementsLayoutProvider(layoutNodeProvider));
                // We return an ArrangeAllOnlyLayoutProvider as no
                // "Arrange Selection" should be done on such layout.
                if (ENABLE_BORDERED_NODES_ARRANGE_ALL) {
                    AbstractLayoutProvider abstractLayoutProvider = new BorderItemAwareLayoutProvider(clp, true);
                    layoutProvider = new ArrangeAllOnlyLayoutProvider(abstractLayoutProvider);
                } else {
                    layoutProvider = new ArrangeAllOnlyLayoutProvider(clp);
                }
            } else {
                return getGridLayoutProvider();
            }
        }
        return layoutProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean provides(final IGraphicalEditPart container) {
        return isDDiagramWithConfiguredOrderedTreeLayout(container);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDiagramLayoutProvider() {
        return false;
    }

    /**
     * Tests whether the specified edit part is a {@link DDiagram} which has
     * been configured to use an {@link OrderedTreeLayout}.
     */
    private boolean isDDiagramWithConfiguredOrderedTreeLayout(final IGraphicalEditPart container) {
        final View view = container.getNotationView();
        final EObject modelElement = view.getElement();
        if (view instanceof Diagram && modelElement instanceof DDiagram) {
            final DDiagram vp = (DDiagram) modelElement;
            final DiagramDescription desc = vp.getDescription();
            if (desc != null) {
                final Layout layout = desc.getLayout();
                if (layout instanceof OrderedTreeLayout) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a properly configured {@link GridLayoutProvider} suitable to
     * layout a tree.
     */
    private GridLayoutProvider getGridLayoutProvider() {
        if (airGridLayoutProvider == null) {
            airGridLayoutProvider = new AdjustedGridLayout();
            airGridLayoutProvider.setColumnSizeMode(GridLayoutProvider.DIMENSION_BY_LINE_OR_COLUMN);
            airGridLayoutProvider.setLineSizeMode(GridLayoutProvider.DIMENSION_BY_LINE_OR_COLUMN);
            airGridLayoutProvider.getPadding().left = LayoutUtils.SCALE;
            airGridLayoutProvider.getPadding().right = LayoutUtils.SCALE;
        }
        return airGridLayoutProvider;
    }
}

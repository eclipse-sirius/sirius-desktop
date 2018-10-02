/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.helper.display;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;

/**
 * Implementation of {@link DisplayService} to use for creation of elements. It
 * looks for visibility of containers (AbstractDNode) or source and target
 * (Edge) whitout re-computation to calculate the current element visibility.
 * 
 * @author mporhel
 */
public final class CreationDisplayServiceImpl implements DisplayService {

    private Map<DDiagramElement, Boolean> cache;

    /**
     * Avoid instantiation.
     */
    private CreationDisplayServiceImpl() {
    }

    /**
     * Initialize a new {@link DisplayService}.
     * 
     * @return a new created instance
     */
    public static DisplayService init() {
        return new CreationDisplayServiceImpl();
    }

    /**
     * {@inheritDoc}
     * 
     * This service do nothing.
     */
    public void refreshAllElementsVisibility(final DDiagram diagram) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#isDisplayed(DDiagram,
     *      DDiagramElement)
     */
    public boolean isDisplayed(final DDiagram diagram, final DDiagramElement element) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.IS_VISIBLE_KEY);
        final boolean result = element.isVisible();
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.IS_VISIBLE_KEY);
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#activateCache()
     */
    public void activateCache() {
        if (cache == null) {
            cache = new HashMap<DDiagramElement, Boolean>();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#deactivateCache()
     */
    public void deactivateCache() {
        cache = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#computeVisibility(DiagramMappingsManager,
     *      DDiagram, DDiagramElement)
     */
    public boolean computeVisibility(DiagramMappingsManager session, final DDiagram diagram, final DDiagramElement element) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.IS_VISIBLE_KEY);
        final boolean result = doIsVisible(session, diagram, element);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.IS_VISIBLE_KEY);
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#computeLabelVisibility(DDiagram,
     *      DDiagramElement)
     */
    public boolean computeLabelVisibility(DDiagram diagram, DDiagramElement element) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.IS_VISIBLE_KEY);
        final boolean result = doIsLabelVisible(diagram, element);
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.IS_VISIBLE_KEY);
        return result;
    }

    /**
     * Tell whether an element is displayed for its creation.
     * 
     * @param session
     * 
     * @param diagram
     *            the diagram.
     * @param element
     *            an element.
     * @return true if the element is in the viewpoint, false otherwise.
     */
    private boolean doIsVisible(DiagramMappingsManager session, final DDiagram diagram, final DDiagramElement element) {
        boolean isVisible = true;

        DDiagramElementQuery ddeQuery = new DDiagramElementQuery(element);
        if (ddeQuery.isIndirectlyHidden() || ddeQuery.isIndirectlyFiltered()) {
            isVisible = false;
        }

        if (isVisible && element instanceof DEdge) {
            isVisible = isDEdgeVisible(diagram, (DEdge) element);
        }

        /* if element seems to be visible, check its parent layer */
        if (isVisible) {
            isVisible = LayerHelper.isInActivatedLayer(session, element, diagram);
        }

        return isVisible;
    }

    /**
     * Tell whether the label of an element is displayed for its creation.
     * 
     * @param diagram
     *            the diagram.
     * @param element
     *            an element.
     * @return true if the property hideByDefault is set to true, false
     *         otherwise
     */
    private boolean doIsLabelVisible(final DDiagram diagram, final DDiagramElement element) {
        DDiagramElementQuery ddeQuery = new DDiagramElementQuery(element);
        return ddeQuery.isLabelVisibleByDefault();
    }

    private boolean isDEdgeVisible(final DDiagram vp, final DEdge edge) {
        boolean isVisible = true;
        if (edge.getSourceNode() instanceof DDiagramElement) {
            isVisible = isVisible && ((DDiagramElement) edge.getSourceNode()).isVisible();
        }
        if (edge.getTargetNode() instanceof DDiagramElement) {
            isVisible = isVisible && ((DDiagramElement) edge.getTargetNode()).isVisible();
        }
        return isVisible;
    }
}

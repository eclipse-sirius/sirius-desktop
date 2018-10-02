/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Class to handle lost element data.
 * 
 * @author dlecan
 */
public class LostElementDataWithMapping extends AbstractLostElementDataWithTarget implements ISimilarLostElementData, ILostElementDataWithMapping {

    private RepresentationElementMapping mapping;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMapping(final RepresentationElementMapping mapping) {
        this.mapping = mapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RepresentationElementMapping getMapping() {
        return mapping;
    }

    /**
     * Re create lost element in specified diagram.
     * 
     * @param designerDiagram
     *            Diagram where to re create element.
     * @return {@link LostElementDataState#EXISTING} if it exists or delegates
     *         creation to
     *         {@link #doRecreateNonExistingLostElement(DSemanticDiagram)}
     *         method.
     */
    public final LostElementDataState reCreateLostElement(final DSemanticDiagram designerDiagram) {
        LostElementDataState result;

        final DDiagramElement foundDesignerDiagramElement = LostElementDataUtil.findDesignerDiagramElement(designerDiagram, this);

        if (foundDesignerDiagramElement == null) {
            // Element not found, create it
            result = doRecreateNonExistingLostElement(designerDiagram);
        } else {
            result = LostElementDataState.EXISTING;
        }

        return result;
    }

    /**
     * Recreate lost element in specified diagram. Needs to be overridden.
     * 
     * @param designerDiagram
     *            Diagram where to re create element.
     * @return {@link LostElementDataState#NOT_CREATED} by default.
     */
    protected LostElementDataState doRecreateNonExistingLostElement(final DSemanticDiagram designerDiagram) {
        // Nothing, needs to be overridden
        return LostElementDataState.NOT_CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + SEPARATOR + "Mapping name: " + mapping.getName(); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isSimilarTo(final DSemanticDecorator semanticDecorator) {
        boolean result;
        if (semanticDecorator instanceof DDiagramElement) {
            final DDiagramElement diagramElement = (DDiagramElement) semanticDecorator;
            final Option<? extends RepresentationElementMapping> extractedMapping = new DDiagramElementQuery(diagramElement).getMapping();
            result = extractedMapping != null && extractedMapping.some();
            result = result && doIsSimilarTo(diagramElement, extractedMapping.get());
        } else {
            // this object doesn't manage DDiagram. See LostDiagramData class
            result = false;
        }
        return result;
    }

    /**
     * Same method as {@link #isSimilarTo(DDiagramElement)}, but adds mapping
     * extracted from diagramElement in order to avoid to extract it again
     * later.
     * 
     * @param diagramElement
     *            Diagram element where to extrat data to check.
     * @param extractedMapping
     *            Mapping extracted from diagram element. Must not be
     *            <code>null</code>.
     * @return <code>true</code> if current data is similar to specified
     *         diagram.
     */
    protected boolean doIsSimilarTo(final DDiagramElement diagramElement, final RepresentationElementMapping extractedMapping) {
        return getTarget() == diagramElement.getTarget() && mapping.getName().equals(extractedMapping.getName());
    }
}

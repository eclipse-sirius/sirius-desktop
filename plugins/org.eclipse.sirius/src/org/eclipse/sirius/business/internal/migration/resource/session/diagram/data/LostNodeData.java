/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.resource.session.diagram.data;

import org.eclipse.sirius.AbstractDNode;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.DNodeListElement;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.business.api.helper.SiriusHelper;
import org.eclipse.sirius.description.AbstractNodeMapping;
import org.eclipse.sirius.description.RepresentationElementMapping;

/**
 * Class to handle lost node data.
 * 
 * @author dlecan
 */
public class LostNodeData extends LostElementDataWithMapping implements ILostElementDataContainer {
    private ILostElementDataContainer parentData;

    /**
     * Sets the value of parentData to parentData.
     * 
     * @param parentData
     *            The parentData to set.
     */
    public void setParentData(final ILostElementDataContainer parentData) {
        this.parentData = parentData;
    }

    /**
     * Returns the parentData.
     * 
     * @return The parentData.
     */
    public ILostElementDataContainer getParentData() {
        return parentData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LostElementDataState doRecreateNonExistingLostElement(final DSemanticDiagram designerDiagram) {
        LostElementDataState created = super.doRecreateNonExistingLostElement(designerDiagram);

        final DDiagramElement createdElement = SiriusHelper.createElement(getMapping(), designerDiagram, getTarget());
        if (createdElement != null) {
            if (parentData == null) {
                if (designerDiagram.getOwnedDiagramElements().add(createdElement)) {
                    created = LostElementDataState.CREATED;
                }
            } else {
                created = parentData.addDiagramElementInCorrespondingParentContainer(designerDiagram, createdElement);
            }
        }
        return created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String result = super.toString() + SEPARATOR;
        if (parentData == null) {
            result += "no parent data";
        } else {
            result += "Parent data: " + parentData.toString();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doIsSimilarTo(final DDiagramElement diagramElement, final RepresentationElementMapping extractedMapping) {
        boolean result = false;

        if (super.doIsSimilarTo(diagramElement, extractedMapping)) {
            if (parentData == null) {
                result = true;
            } else if (diagramElement.eContainer() instanceof DSemanticDecorator) {
                result = parentData.isSimilarTo((DSemanticDecorator) diagramElement.eContainer());
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public LostElementDataState addDiagramElementInCorrespondingParentContainer(final DDiagram designerDiagram, final DDiagramElement createdElement) {
        LostElementDataState result = LostElementDataState.NOT_CREATED;

        final DDiagramElement parentVPContainer = LostElementDataUtil.findDesignerDiagramElement(designerDiagram, this);

        if (parentVPContainer instanceof AbstractDNode) {
            final AbstractDNode otherAbstractNode = (AbstractDNode) parentVPContainer;
            boolean created = false;

            if (createdElement instanceof DNode && otherAbstractNode.getMapping() instanceof AbstractNodeMapping
                    && ((AbstractNodeMapping) otherAbstractNode.getMapping()).getAllBorderedNodeMappings().contains(createdElement.getMapping())) {

                created = otherAbstractNode.getOwnedBorderedNodes().add((DNode) createdElement);

            } else if (parentVPContainer instanceof DNodeContainer) {

                created = ((DNodeContainer) parentVPContainer).getOwnedDiagramElements().add(createdElement);

            } else if (parentVPContainer instanceof DNodeList) {

                created = ((DNodeList) parentVPContainer).getOwnedElements().add((DNodeListElement) createdElement);
            }

            if (created) {
                result = LostElementDataState.CREATED;
            }
        }

        return result;
    }
}

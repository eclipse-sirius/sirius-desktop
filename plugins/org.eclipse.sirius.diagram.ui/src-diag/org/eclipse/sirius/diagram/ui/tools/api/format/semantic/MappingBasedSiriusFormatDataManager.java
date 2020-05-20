/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.api.format.semantic;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticEdgeFormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticNodeFormatDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * An extension of {@link AbstractSiriusFormatDataManager} using an explicit <EObject,EObject> mapping function.
 * 
 * @author adieumegard
 *
 */
public class MappingBasedSiriusFormatDataManager extends SiriusFormatDataManagerForSemanticElements implements AdvancedSiriusFormatDataManager {

    /**
     * The source to target semantic elements map.
     */
    private Map<EObject, EObject> semanticMap;

    /**
     * The source DDiagram.
     */
    private DDiagram sourceDiagram;

    /**
     * Constructor from a map.
     * 
     * @param map
     *            The map used for semantic objects mapping
     */
    public MappingBasedSiriusFormatDataManager(Map<EObject, EObject> map) {
        super();
        setSemanticMap(map);
    }

    /**
     * Constructor from a source set to a target set through a function mapping.
     * 
     * @param set
     *            The source set of the mapping function
     * @param function
     *            The mapping function
     */
    public MappingBasedSiriusFormatDataManager(Set<EObject> set, Function<EObject, EObject> function) {
        super();
        setSemanticMap(set.stream().collect(Collectors.toMap(x -> x, x -> function.apply(x))));
    }

    /**
     * Stores FormatData for a whole diagram.
     * 
     * @param rootEditPart
     *            The GraphicalEditPart for a DDiagram element.
     */
    @Override
    public void storeFormatData(IGraphicalEditPart rootEditPart) {
        // Store DDiagram
        final EObject semanticElement = rootEditPart.resolveSemanticElement();
        if (semanticElement instanceof DDiagram) {
            sourceDiagram = (DDiagram) semanticElement;
        } else if (semanticElement instanceof DDiagramElement) {
            sourceDiagram = ((DDiagramElement) semanticElement).getParentDiagram();
        }
        super.storeFormatData(rootEditPart);
    }

    /**
     * Resolve the key for the element of the target diagram.
     * 
     * @param semanticDecorator
     *            The {@link DSemanticDecorator} for which to create a key.
     * @return The key of the mapping-function-corresponding source diagram element.
     */
    @Override
    public FormatDataKey createKey(final DSemanticDecorator semanticDecorator) {
        FormatDataKey result = null;

        boolean isApplyFormat = false;
        // Check if we are storing or applying
        if (semanticDecorator instanceof DDiagramElement) {
            DDiagram parentDiagram = ((DDiagramElement) semanticDecorator).getParentDiagram();
            isApplyFormat = !parentDiagram.equals(sourceDiagram);
        }

        EObject targetEObject = semanticDecorator.getTarget();
        EObject usedAsKeySemanticElement = null;
        // If we are applying and we have a match in the map
        if (!isApplyFormat && getSemanticMap().containsKey(targetEObject)) {
            usedAsKeySemanticElement = getSemanticMap().get(targetEObject);
        } else {
            // If we are storing or if there is no match in the map (we default to the target value; the key will not be
            // used anyways)
            usedAsKeySemanticElement = targetEObject;
        }

        if (semanticDecorator instanceof DEdge) {
            result = new SemanticEdgeFormatDataKey(usedAsKeySemanticElement);
        } else if (semanticDecorator instanceof AbstractDNode || semanticDecorator instanceof DDiagram) {
            result = new SemanticNodeFormatDataKey(usedAsKeySemanticElement);
        }
        return result;
    }

    @Override
    public void applyFormat(IGraphicalEditPart rootEditPart) {
        super.applyFormat(rootEditPart);
    }

    @Override
    public void applyLayout(IGraphicalEditPart rootEditPart) {
        super.applyLayout(rootEditPart);
    }

    @Override
    public void applyStyle(IGraphicalEditPart rootEditPart) {
        super.applyStyle(rootEditPart);
    }

    private void setSemanticMap(Map<EObject, EObject> asMap) {
        semanticMap = asMap;
    }

    public Map<EObject, EObject> getSemanticMap() {
        return semanticMap;
    }

}

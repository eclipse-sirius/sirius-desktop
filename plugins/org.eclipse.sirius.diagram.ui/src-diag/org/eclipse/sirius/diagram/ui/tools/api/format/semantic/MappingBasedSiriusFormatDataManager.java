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

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.provider.Messages;
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
     *            The decorator for the target diagram
     * @return The key of the mapping-function-corresponding source diagram element.
     */
    @Override
    public FormatDataKey createKey(final DSemanticDecorator semanticDecorator) {
        if (semanticDecorator instanceof DDiagramElement) {
            DDiagram parentDiagram = ((DDiagramElement) semanticDecorator).getParentDiagram();
            if (!parentDiagram.equals(sourceDiagram)) {
                return resolveSourceKey(semanticDecorator);
            }
        }
        return super.createKey(semanticDecorator);
    }

    /**
     * Resolve the {@link FormatDataKey} element that have been stored for the EObject mapped (through the
     * {@code semanticMap}) to the target value of {@code semanticDecorator}.
     * 
     * @param semanticDecorator
     *            The semantic decorator for the destination diagram element for which we want to compute the format
     *            data.
     * @return The {@link FormatDataKey} from the source mappings if it exists
     */
    private FormatDataKey resolveSourceKey(final DSemanticDecorator semanticDecorator) {
        FormatDataKey result = null;
        final EObject realSemanticElement = semanticDecorator.getTarget();
        if (getSemanticMap().containsValue(realSemanticElement)) {
            List<Entry<EObject, EObject>> mappedElements = getSemanticMap().entrySet().stream().filter(entry -> entry.getValue().equals(realSemanticElement)).collect(Collectors.toList());
            if (mappedElements.size() != 1) {
                throw new IllegalStateException(MessageFormat.format(Messages.MappingBasedSiriusFormatDataManager_invalidSemanticMapping, semanticDecorator.getClass().getSimpleName()));
            }
            final EObject realSourceSemanticElement = mappedElements.get(0).getKey();
            if (semanticDecorator instanceof DEdge) {
                result = new SemanticEdgeFormatDataKey(realSourceSemanticElement);
            } else if (semanticDecorator instanceof AbstractDNode || semanticDecorator instanceof DDiagram) {
                result = new SemanticNodeFormatDataKey(realSourceSemanticElement);
            }
        }
        return result;
    }

    /**
     * In this FormatDataManager, must be called after a proper call to {@code init}.
     */
    @Override
    public void applyFormat(IGraphicalEditPart rootEditPart) {
        super.applyFormat(rootEditPart);
    }

    /**
     * In this FormatDataManager, must be called after a proper call to {@code init}.
     */
    @Override
    public void applyLayout(IGraphicalEditPart rootEditPart) {
        super.applyLayout(rootEditPart);
    }

    /**
     * In this FormatDataManager, must be called after a proper call to {@code init}.
     */
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

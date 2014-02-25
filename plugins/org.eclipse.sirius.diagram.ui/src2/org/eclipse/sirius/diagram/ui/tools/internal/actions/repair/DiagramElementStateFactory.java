/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.collect.Maps;

/**
 * Factory for {@link IDiagramElementState}.
 * 
 * @author dlecan
 */
public class DiagramElementStateFactory {

    private static final Map<Class<? extends DDiagramElement>, Class<? extends IDiagramElementState<? extends DDiagramElement>>> ASSOCIATION = Maps.newLinkedHashMap();

    static {
        ASSOCIATION.put(DEdge.class, DEdgeDiagramElementState.class);
        ASSOCIATION.put(DNode.class, DNodeDiagramElementState.class);
        ASSOCIATION.put(DNodeListElement.class, DNodeListDiagramElementState.class);
        ASSOCIATION.put(DDiagramElementContainer.class, DDiagramElementContainerDiagramElementState.class);
        ASSOCIATION.put(AbstractDNode.class, AbstractDNodeDiagramElementState.class);
    }

    private static final Map<Class<? extends DDiagramElement>, Constructor<? extends IDiagramElementState<? extends DDiagramElement>>> CACHE = Maps.newLinkedHashMap();

    private static final Class<DefaultDiagramElementState> DEFAULT_DIAGRAM_ELT_STATE = DefaultDiagramElementState.class;

    /**
     * Constructor.
     */
    protected DiagramElementStateFactory() {
        // Nothing
    }

    /**
     * Build a new factory instance.
     * 
     * @return New instance.
     */
    public static DiagramElementStateFactory newInstance() {
        return new DiagramElementStateFactory();
    }

    /**
     * Method to validate a diagram element. If <code>true</code>,
     * {@link #buildDiagramElementState(DDiagramElement)} can be safely called.
     * 
     * @param diagramElement
     *            Diagram element to validate.
     * @return <code>true</code> if this element is valid.
     *         {@link #buildDiagramElementState(DDiagramElement)} can be safely
     *         called.
     */
    public boolean isValid(final DDiagramElement diagramElement) {
        boolean result = false;

        final EObject target = diagramElement.getTarget();
        final Option<? extends RepresentationElementMapping> mapping = new DDiagramElementQuery(diagramElement).getMapping();

        if (target != null && mapping != null && mapping.some()) {
            if (diagramElement instanceof DEdge) {
                DEdge edge = (DEdge) diagramElement;
                if (edge.getSourceNode() instanceof DDiagramElement) {
                    result = isValid((DDiagramElement) edge.getSourceNode());
                }
                if (result && edge.getTargetNode() instanceof DDiagramElement) {
                    result = isValid((DDiagramElement) edge.getTargetNode());
                }
            } else if (diagramElement.eContainer() instanceof DDiagramElement) {
                result = isValid((DDiagramElement) diagramElement.eContainer());
            } else {
                result = true;
            }
        }

        return result;
    }

    /**
     * Build a diagram element state.
     * <p>
     * Provided diagram element must have been validated firstly with
     * {@link #isValid(DDiagramElement)}.
     * </p>
     * 
     * @param id
     *            Id of current diagram element
     * @param diagramElement
     *            Diagram element
     * @param crossReferencer
     *            Current cross-referencer
     * @return A diagram element state. Cannot be <code>null</code>.
     */
    public IDiagramElementState<? extends DDiagramElement> buildDiagramElementState(final Identifier id, final DDiagramElement diagramElement, final DiagramCrossReferencer crossReferencer) {

        final EObject target = diagramElement.getTarget();
        final DiagramElementMapping mapping = diagramElement.getDiagramElementMapping();

        IDiagramElementState<DDiagramElement> diagramElementState = createDiagramElementStateInstance(id, diagramElement, crossReferencer);
        diagramElementState.storeElementState(target, mapping, diagramElement);
        return diagramElementState;
    }

    @SuppressWarnings("unchecked")
    private IDiagramElementState<DDiagramElement> createDiagramElementStateInstance(final Identifier id, final DDiagramElement diagramElement, final DiagramCrossReferencer crossReferencer) {
        Constructor<? extends IDiagramElementState<? extends DDiagramElement>> cachedConstructor = CACHE.get(diagramElement.getClass());

        // Cache found constructor in order to improve performances
        if (cachedConstructor == null) {
            Class<? extends IDiagramElementState<? extends DDiagramElement>> clazz = getDiagramElementStateClass(diagramElement);

            try {
                cachedConstructor = clazz.getConstructor(Identifier.class, DiagramCrossReferencer.class);
                CACHE.put(diagramElement.getClass(), cachedConstructor);
            } catch (SecurityException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        try {
            return (IDiagramElementState<DDiagramElement>) cachedConstructor.newInstance(id, crossReferencer);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause().getMessage(), e.getCause());
        }

    }

    private Class<? extends IDiagramElementState<? extends DDiagramElement>> getDiagramElementStateClass(final DDiagramElement diagramElement) {
        // Use default diagram element state type
        Class<? extends IDiagramElementState<? extends DDiagramElement>> classFound = DEFAULT_DIAGRAM_ELT_STATE;

        for (Entry<Class<? extends DDiagramElement>, Class<? extends IDiagramElementState<? extends DDiagramElement>>> entry : ASSOCIATION.entrySet()) {
            Class<? extends DDiagramElement> clazz = entry.getKey();

            if (clazz.isAssignableFrom(diagramElement.getClass())) {
                classFound = entry.getValue();
                break;
            }
        }

        return classFound;
    }

    /**
     * Dispose the local resource.
     */
    public void dispose() {
        CACHE.clear();
    }

}

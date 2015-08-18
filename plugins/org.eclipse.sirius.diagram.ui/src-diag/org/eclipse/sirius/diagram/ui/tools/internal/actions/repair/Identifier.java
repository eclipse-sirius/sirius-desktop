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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.api.migration.IDiagramIdentifierProvider;
import org.eclipse.sirius.viewpoint.Customizable;

/**
 * Identifier for
 * {@link org.eclipse.sirius.diagram.tools.internal.actions.migration.state.IDiagramElementState}
 * .
 * <p>
 * Just a wrapper around a {@link String}.
 * </p>
 * 
 * @author dlecan
 */
public final class Identifier {

    /**
     * Empty identifier.
     */
    public static final Identifier EMPTY_ID = new Identifier(StringUtil.EMPTY_STRING);

    /** The extension point ID. */
    private static final String PROVIDERS_ID = "org.eclipse.sirius.diagram.ui.diagramIdentifierProvider"; //$NON-NLS-1$

    /** The class attribute. */
    private static final String PROVIDERS_ATTRIBUTE = "providerClass"; //$NON-NLS-1$

    /** The string target. */
    private static final String TARGET = "target"; //$NON-NLS-1$

    /** All providers. */
    private static List<IDiagramIdentifierProvider> allProviders;

    private String identifier;

    private Identifier(String identifier) {
        this.identifier = identifier;
    }

    private Identifier(StringBuilder identifier) {
        this(identifier.toString());
    }

    /**
     * Creates the identifier of the element.
     * 
     * @param element
     *            the diagram element.
     * @return Identifier.
     */
    public static Identifier createIdentifier(final DDiagramElement element) {
        Identifier result = EMPTY_ID;
        String id = getId(element);
        if (id != null) {
            result = new Identifier(id);
        }
        return result;
    }

    private static String getId(DDiagramElement element) {
        String id = null;
        if (element instanceof DEdge) {
            id = Identifier.getEdgeId((DEdge) element);
        } else if (element instanceof AbstractDNode) {
            id = Identifier.getNodeId((AbstractDNode) element);
        }
        return id;
    }

    /**
     * Get a id for a edge.
     * 
     * @param edge
     *            the edge.
     * @return id
     */
    private static String getEdgeId(final DEdge edge) {
        StringBuilder buffer = new StringBuilder();

        if (edge != null) {
            if (!Identifier.identifierFromExtensions(edge, buffer)) {
                Identifier.createDiagramIdentifier(edge.getParentDiagram(), buffer);

                if (edge.getSourceNode() instanceof AbstractDNode) {
                    Identifier.createNodeIdentifier((AbstractDNode) edge.getSourceNode(), buffer);
                } else if (edge.getSourceNode() instanceof DEdge) {
                    Identifier.createEdgeIdentifier((DEdge) edge.getSourceNode(), buffer);
                }

                if (edge.getTargetNode() instanceof AbstractDNode) {
                    Identifier.createNodeIdentifier((AbstractDNode) edge.getTargetNode(), buffer);
                } else if (edge.getTargetNode() instanceof DEdge) {
                    Identifier.createEdgeIdentifier((DEdge) edge.getTargetNode(), buffer);
                }
                Identifier.checkNotNull(edge.getTarget(), TARGET);
                buffer.append(EcoreUtil.getURI(edge.getTarget()));

                Identifier.checkNotNull(edge.getActualMapping(), "actualMapping"); //$NON-NLS-1$
                buffer.append(EcoreUtil.getURI(edge.getActualMapping()));
            }
        }
        return buffer.toString();
    }

    /**
     * Get a id for a node.
     * 
     * @param node
     *            the node.
     * @return id
     */
    private static String getNodeId(final AbstractDNode node) {
        StringBuilder sb = new StringBuilder();

        if (node != null) {
            Identifier.createDiagramIdentifier(node.getParentDiagram(), sb);
            Identifier.createNodeIdentifier(node, sb);
        }
        return sb.toString();
    }

    /**
     * Computes the identifier of the node.
     * 
     * @param node
     *            the node.
     * @param buffer
     *            the buffer.
     */
    private static void createNodeIdentifier(final AbstractDNode node, final StringBuilder buffer) {
        if (node != null) {
            if (!Identifier.identifierFromExtensions(node, buffer)) {
                if (node.eContainer() instanceof AbstractDNode) {
                    Identifier.createNodeIdentifier((AbstractDNode) node.eContainer(), buffer);
                }

                Identifier.checkNotNull(node.getTarget(), TARGET);
                buffer.append(EcoreUtil.getURI(node.getTarget()));

                Identifier.checkNotNull(node.getMapping(), "mapping"); //$NON-NLS-1$
                buffer.append(EcoreUtil.getURI(node.getMapping()));
            }
        }
    }

    /**
     * Computes the identifier of the edge.
     * 
     * @param edge
     *            the edge.
     * @param buffer
     *            the buffer.
     */
    private static void createEdgeIdentifier(final DEdge edge, final StringBuilder buffer) {
        if (edge != null) {
            if (!Identifier.identifierFromExtensions(edge, buffer)) {
                Identifier.checkNotNull(edge.getTarget(), TARGET);
                buffer.append(EcoreUtil.getURI(edge.getTarget()));

                Identifier.checkNotNull(edge.getMapping(), "mapping"); //$NON-NLS-1$
                buffer.append(EcoreUtil.getURI(edge.getMapping()));
            }
        }
    }

    private static void createDiagramIdentifier(final DDiagram diagram, final StringBuilder buffer) {
        buffer.append(EcoreUtil.getURI(diagram));
    }

    /**
     * Get a {@link Identifier} for the specified {@link Customizable}.
     * 
     * @param customizable
     *            the specified {@link Customizable}
     * @return a {@link Identifier}
     */
    public static Identifier createCustomizableIdentifier(Customizable customizable) {
        StringBuilder sb = new StringBuilder();
        DDiagramElement dDiagramElement = getDDiagramElement(customizable);
        String id = getId(dDiagramElement);
        if (id != null) {
            sb.append(id);
        }
        if (!(customizable.eContainer() instanceof DDiagramElement)) {
            EObject eContainer = customizable.eContainer();
            sb.append("." + eContainer.eContainingFeature().getName() + "."); //$NON-NLS-1$ //$NON-NLS-2$
        }
        sb.append(customizable.eContainingFeature().getName());
        if (customizable.eContainingFeature().isMany()) {
            Object list = customizable.eContainer().eGet(customizable.eContainingFeature());
            if (list instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<?> values = (List<Object>) list;
                sb.append("[" + values.indexOf(customizable) + "]"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        return new Identifier(sb);
    }

    private static DDiagramElement getDDiagramElement(Customizable customizable) {
        DDiagramElement dDiagramElement = null;
        EObject container = customizable.eContainer();
        while (!(container instanceof DDiagramElement) && container != null) {
            container = container.eContainer();
        }
        if (container instanceof DDiagramElement) {
            dDiagramElement = (DDiagramElement) container;
        }
        return dDiagramElement;
    }

    private static void checkNotNull(Object obj, String propertyName) {
        if (obj == null) {
            throw new IllegalArgumentException(propertyName + " cannot be null");
        }
    }

    private static boolean identifierFromExtensions(final DDiagramElement element, final StringBuilder buffer) {
        for (final IDiagramIdentifierProvider identifierProvider : Identifier.getAllProviders()) {
            if (identifierProvider.provides(element)) {
                final String string = identifierProvider.computeIdentifier(element);
                buffer.append(string);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all providers.
     * 
     * @return all providers.
     */
    private static List<IDiagramIdentifierProvider> getAllProviders() {
        if (allProviders == null) {
            allProviders = EclipseUtil.getExtensionPlugins(IDiagramIdentifierProvider.class, PROVIDERS_ID, PROVIDERS_ATTRIBUTE);
        }
        return allProviders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // Eclipse generated code
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    // CHECKSTYLE:OFF
    @Override
    // Eclipse generated code
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Identifier)) {
            return false;
        }
        Identifier other = (Identifier) obj;
        if (identifier == null) {
            if (other.identifier != null) {
                return false;
            }
        } else if (!identifier.equals(other.identifier)) {
            return false;
        }
        return true;
    }

    // CHECKSTYLE:ON

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "id: " + identifier; //$NON-NLS-1$
    }

}

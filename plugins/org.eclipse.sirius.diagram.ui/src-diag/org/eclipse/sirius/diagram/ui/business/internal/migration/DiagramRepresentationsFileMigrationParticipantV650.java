/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.helper.SiriusHelper;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * The migration code of Sirius 6.5.0.
 * 
 * @author lredor
 * 
 */
public class DiagramRepresentationsFileMigrationParticipantV650 {
    /**
     * The VP version for this migration.
     */
    public static final Version MIGRATION_VERSION = new Version("6.5.0.201209041254"); //$NON-NLS-1$

    /**
     * Predicate that checks that :
     * <UL>
     * <LI>The input is a GMF Node,</LI>
     * <LI>This Node has a DNode style description that forbidden to resize
     * DNode,</LI>
     * <LI>and the size of DNode is not the same of the size of the GMF Node.</LI>
     * </UL>
     */
    private Predicate<EObject> nonResizableNodeWithDifferentSizePredicate = new Predicate<EObject>() {
        public boolean apply(EObject input) {
            boolean apply = false;
            if (input instanceof Node) {
                Node node = (Node) input;
                if (node.getElement() instanceof DNode) {
                    DNode dNode = (DNode) node.getElement();
                    StyleDescription styleDescription = dNode.getStyle().getDescription();
                    if (styleDescription instanceof NodeStyleDescription) {
                        NodeStyleDescription nodeStyleDescription = (NodeStyleDescription) styleDescription;
                        apply = nodeStyleDescription.getResizeKind() == ResizeKind.NONE_LITERAL && hasDifferentSizeOfGMFNodeSize(dNode, node);
                    }
                }
            }
            return apply;
        }

        private boolean hasDifferentSizeOfGMFNodeSize(DNode dNode, Node node) {
            boolean hasDifferentSizeOfGMFNodeSize = false;
            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Size) {
                Size size = (Size) layoutConstraint;
                hasDifferentSizeOfGMFNodeSize = size.getWidth() != dNode.getWidth() || size.getHeight() != dNode.getHeight();
            }
            return hasDifferentSizeOfGMFNodeSize;
        }
    };

    /**
     * Predicate to keep only GFM Edge with tree routing style.
     */
    private Predicate<EObject> edgeWithTreeRoutingPredicate = new Predicate<EObject>() {
        public boolean apply(EObject input) {
            boolean apply = false;
            if (input instanceof Edge) {
                apply = new EdgeQuery((Edge) input).isEdgeWithTreeRoutingStyle();
            }
            return apply;
        }
    };

    /**
     * Default constructor.
     */
    public DiagramRepresentationsFileMigrationParticipantV650() {
        // Do nothing.
    }

    /**
     * Return a list of GMF Diagram that are on the root of the resource of the
     * DAnalysis.
     * 
     * @param dAnalysis
     *            Current analysis
     * @return list of GMF Diagram that are on the root of the resource of the
     *         DAnalysis.
     * 
     */
    public static List<Diagram> getDiagramsAtRoot(DAnalysis dAnalysis) {
        List<Diagram> diagrams = Lists.newArrayList();
        for (final EObject object : dAnalysis.eResource().getContents()) {
            if (object instanceof Diagram) {
                diagrams.add((Diagram) object);
            }
        }
        return diagrams;
    }

    /**
     * Move GMF diagrams from root of the resource to eAnnotation in
     * corresponding DDiagram, and then launch old migrations that haven't been
     * launched on this diagrams.
     * 
     * @param dAnalysis
     *            The analysis to migrate.
     * @param diagrams
     *            list of GMF Diagram to move from the root of the resource to
     *            the concerned {@link org.eclipse.sirius.diagram.DDiagram}.
     */
    public void moveGMFDiagramsFromRoot(DAnalysis dAnalysis, List<Diagram> diagrams) {
        for (Diagram diagram : diagrams) {
            if (diagram != null) {
                if (diagram.getElement() instanceof DRepresentation) {
                    SiriusHelper.getOrCreateAnnotation(CustomDataConstants.GMF_DIAGRAMS, (DRepresentation) diagram.getElement(), diagram);
                } else if (diagram.getElement() == null || diagram.getElement().eIsProxy()) {
                    EcoreUtil.remove(diagram);
                }
            }
        }
        // Launch migration that potentially needs to be call again (adaptation
        // of previous participants
        // GMFViewSizeForDNodeNotResizableAutomaticMigrationParticipant and
        // GMFTreeEdgeFixAnchorsAutomaticMigrationParticipant)
        for (final DView view : dAnalysis.getOwnedViews()) {
            // Call equivalent migration of
            // GMFViewSizeForDNodeNotResizableAutomaticMigrationParticipant.
            migrationForGMFViewSizeForDNodeNotResizable(view);

            // Call equivalent migration of
            // GMFTreeEdgeFixAnchorsAutomaticMigrationParticipant
            fixAnchorsOfGMFTreeEdge(view);
        }
    }

    /**
     * In case of resizeKind == NONE and that DNode.width/height is different
     * this of the GMF Node, before the fix changing the resizeKind to something
     * different of NONE has the effect to resize the bounds of concerned
     * figures to the DNode.width/height and not the GMF Node bounds.
     * 
     * @param view
     *            The view to migrate.
     */
    private void migrationForGMFViewSizeForDNodeNotResizable(final DView view) {
        Iterator<EObject> viewIterator = Iterators.filter(view.eAllContents(), nonResizableNodeWithDifferentSizePredicate);
        while (viewIterator.hasNext()) {
            final EObject next = viewIterator.next();
            if (next instanceof Node) {
                Node node = (Node) next;
                DNode dNode = (DNode) node.getElement();
                Size size = (Size) node.getLayoutConstraint();
                Dimension dNodeSize = new DNodeQuery(dNode).getDefaultDimension();
                size.setWidth(dNodeSize.width);
                size.setHeight(dNodeSize.height);
            }
        }
    }

    /**
     * The existing diagram may have incorrect coordinates on their anchors,
     * resulting in an inconsistent display now. The objective of this automatic
     * migration is to ensure that egdes of the same tree goes through the same
     * branches (ie they have the same anchor on source side or target side
     * depending on the direction of the tree). If this is not the case the
     * command modifies the GMF anchors to {0.5, 0.5}, if all anchors are
     * different or to the unique id if there is only one defined anchor in the
     * tree (other anchors are null).
     * 
     * @param view
     *            The view to migrate.
     */
    private void fixAnchorsOfGMFTreeEdge(final DView view) {
        // Sort the edges per common source or common target
        Map<View, List<Edge>> edgesSortPerSource = Maps.newHashMap();
        Map<View, List<Edge>> edgesSortPerTarget = Maps.newHashMap();
        Iterator<EObject> viewIterator = Iterators.filter(view.eAllContents(), edgeWithTreeRoutingPredicate);
        while (viewIterator.hasNext()) {
            final EObject next = viewIterator.next();
            if (next instanceof Edge) {
                Edge edge = (Edge) next;
                List<Edge> edgesWithSameSource = edgesSortPerSource.get(edge.getSource());
                if (edgesWithSameSource == null) {
                    edgesWithSameSource = Lists.newArrayList();
                }
                edgesWithSameSource.add(edge);
                edgesSortPerSource.put(edge.getSource(), edgesWithSameSource);
                List<Edge> edgesWithSameTarget = edgesSortPerTarget.get(edge.getTarget());
                if (edgesWithSameTarget == null) {
                    edgesWithSameTarget = Lists.newArrayList();
                }
                edgesWithSameTarget.add(edge);
                edgesSortPerTarget.put(edge.getTarget(), edgesWithSameTarget);
            }
        }
        // Add the set anchor command if needed (ie if the anchor is
        // different for edges with same source or same target)
        setSameAnchor(edgesSortPerSource, true);
        setSameAnchor(edgesSortPerTarget, false);
    }

    /**
     * Add the set anchor command to <code>ccmd</code> if needed (ie if the
     * anchor is different for edges with same source or same target).
     * 
     * @param edgesSortPerCommonAnchor
     *            The map that group the edges per comon anchor
     * @param sourceAnchor
     *            true if the groups corresponds to source side, false if the
     *            groups corresponds to target side
     */
    protected void setSameAnchor(Map<View, List<Edge>> edgesSortPerCommonAnchor, boolean sourceAnchor) {
        for (Entry<View, List<Edge>> entry : edgesSortPerCommonAnchor.entrySet()) {
            List<Edge> edgesForAnchor = entry.getValue();
            if (edgesForAnchor.size() > 1) {
                Option<String> replacementId = getReplacementId(edgesForAnchor, sourceAnchor);
                if (replacementId.some()) {
                    for (Edge edge : edgesForAnchor) {
                        Anchor anchor = getAnchor(edge, sourceAnchor);
                        if (anchor instanceof IdentityAnchor) {
                            ((IdentityAnchor) anchor).setId(replacementId.get());
                        } else {
                            IdentityAnchor identityAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                            identityAnchor.setId(replacementId.get());
                            edge.setTargetAnchor(identityAnchor);
                        }
                    }
                }
            }
        }
    }

    /**
     * Compute the new id to use for all edges.
     * 
     * @param edges
     *            a list of edges with common anchor.
     * @param sourceAnchor
     *            true if the common anchor is the source one, false, if it is
     *            the target one.
     * @return an optional String, null is there is no replacement id.
     */
    private Option<String> getReplacementId(List<Edge> edges, boolean sourceAnchor) {
        String id = null;
        // This boolean is set to true if all the branches of the tree
        // have the same id.
        boolean sameId = true;
        // This boolean is set to true if there is only one anchor with
        // id in all the tree. If it is the case, this id is used for
        // all the branches of the tree.
        boolean onlyOneIdInTheTree = false;
        // This boolean is set to true if at least one edge has a null
        // anchor. A null anchor is considered as different from an
        // anchor with an id.
        boolean atLeastOneNullAnchor = false;
        for (Edge edge : edges) {
            Anchor anchor = getAnchor(edge, sourceAnchor);
            if (anchor instanceof IdentityAnchor) {
                if (id == null) {
                    id = ((IdentityAnchor) anchor).getId();
                    onlyOneIdInTheTree = true;
                    if (atLeastOneNullAnchor) {
                        sameId = false;
                    }
                } else {
                    onlyOneIdInTheTree = false;
                    if (!id.equals(((IdentityAnchor) anchor).getId())) {
                        sameId = false;
                    }
                }
            } else {
                atLeastOneNullAnchor = true;
                if (id != null) {
                    // We have already find a not null anchor so a null
                    // anchor is considered as different
                    sameId = false;
                }
            }
        }
        if (!sameId) {
            String defaultTerminalString = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);
            if (onlyOneIdInTheTree) {
                defaultTerminalString = id;
            }
            return Options.newSome(defaultTerminalString);
        }
        return Options.newNone();
    }

    /**
     * Get the source anchor or the target anchor of the <code>edge</code>
     * according to boolean <code>sourceAnchor</code>.
     * 
     * @param edge
     *            The corresponding edge
     * @param sourceAnchor
     *            true if we want to get the source anchor, false if we want the
     *            target anchor
     * @return the corresponding anchor
     */
    protected Anchor getAnchor(Edge edge, boolean sourceAnchor) {
        if (sourceAnchor) {
            return edge.getSourceAnchor();
        } else {
            return edge.getTargetAnchor();
        }
    }
}

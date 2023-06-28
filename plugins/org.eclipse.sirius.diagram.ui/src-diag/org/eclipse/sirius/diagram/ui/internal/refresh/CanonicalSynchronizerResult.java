/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.DDiagramCanonicalSynchronizer;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EMFUtil;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * <p>
 * This class is used by the GMF refresh (see {@link DDiagramCanonicalSynchronizer}) and contains its result. This class
 * contains a list of created nodes, edges, and list of orphan nodes and edges. This class provides methods to process
 * created and orphan nodes and edges (and their associated pure graphical elements: {@link Note}, {@link Text} and
 * RepresentationLink).
 * </p>
 * 
 * We call purely graphic elements (abbreviated PGE) GMF nodes that have no Sirius associated elements:
 * <ul>
 * <li>{@link Note}</li>
 * <li>RepresentationLink, which is a {@link Note} with a link to a representation</li>
 * <li>{@link Text}</li>
 * </ul>
 * These elements can be attached using a NoteAttachment.
 * 
 * @author Laurent Redor
 */
public class CanonicalSynchronizerResult {

    /**
     * The created Node {@link View}s during the canonical refresh of the diagram.
     */
    Set<View> createdNodes;

    /**
     * The created Edge {@link View}s during the canonical refresh of the diagram.
     */
    List<Edge> createdEdges;

    /**
     * The orphan Node {@link View}s detected during the canonical refresh of the diagram and to delete after a
     * potential reconciliation of pure graphical elements ({@link Note}, {@link Text}, NoteAttachment or
     * RepresentationLink).
     */
    List<View> orphanNodes;

    /**
     * The orphan Edge {@link View}s detected during the canonical refresh of the diagram and to delete after a
     * potential reconciliation of pure graphical elements ({@link Note}, {@link Text}, NoteAttachment or
     * RepresentationLink).
     */
    Set<Edge> orphanEdges;

    /**
     * Purely graphic elements (PGE) attached to an orphan view
     */
    Set<Node> partialOrphanPGE;

    /**
     * The value of the preference "Remove/Hide note when annotated element is hidden or remove"
     */
    private final boolean prefRemoveAttachedPGE;

    /**
     * Default constructor.
     * 
     * <p>
     * To ensure that the GMF refresh works properly, this class must be instantiated for each GMF refresh.
     * </p>
     */
    public CanonicalSynchronizerResult() {
        createdNodes = new LinkedHashSet<View>();
        createdEdges = new ArrayList<Edge>();
        orphanNodes = new ArrayList<View>();
        orphanEdges = new HashSet<Edge>();
        partialOrphanPGE = new HashSet<Node>();
        prefRemoveAttachedPGE = DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name());
    }

    /**
     * Adds nodes to set of created nodes.
     * 
     * @param createdNodesToAdd
     *            set of nodes
     */
    public void addCreatedNodes(Set<View> createdNodesToAdd) {
        createdNodes.addAll(createdNodesToAdd);
    }

    /**
     * Adds edges to list of created edges.
     * 
     * @param createdEdgesToAdd
     *            list of edges
     */
    public void addCreatedEdges(List<Edge> createdEdgesToAdd) {
        createdEdges.addAll(createdEdgesToAdd);
    }

    /**
     * Adds nodes to the list of nodes to be deleted.
     * 
     * @param orphanNodesToAdd
     *            list of nodes
     */
    public void addOrphanNodes(List<View> orphanNodesToAdd) {
        orphanNodes.addAll(orphanNodesToAdd);
    }

    /**
     * Adds edges to the list of edges to be deleted.
     * 
     * @param orphanEdgesToAdd
     *            list of edges
     */
    public void addOrphanEdges(List<Edge> orphanEdgesToAdd) {
        orphanEdges.addAll(orphanEdgesToAdd);
    }

    /**
     * Return the set of created nodes.
     * 
     * @return the set of created nodes
     */
    public Set<View> getCreatedNodes() {
        return createdNodes;
    }

    /**
     * Return the set of created edges.
     * 
     * @return the set of created edges
     */
    public List<Edge> getCreatedEdges() {
        return createdEdges;
    }

    /**
     * Return All created nodes and edges (note: need computation).
     * 
     * @return All created nodes and edges
     */
    public Set<View> getCreatedViews() {
        var allViews = new LinkedHashSet<View>();
        allViews.addAll(createdNodes);
        allViews.addAll(createdEdges);
        return allViews;
    }

    /**
     * Return the list of orphan nodes.
     * 
     * @return the list of orphan nodes
     */
    public List<View> getOrphanNodes() {
        return orphanNodes;
    }

    /**
     * Return the list of orphan edges.
     * 
     * @return the list of orphan edges
     */
    public Set<Edge> getOrphanEdges() {
        return orphanEdges;
    }

    /**
     * Move all Notes Attachment of fromView (source or target is fromView) to toView.
     * 
     * Note: views can be either nodes or edges.
     * 
     * @param fromView
     *            The GMF view that has source and target note attachments
     * @param toView
     *            The GMF view that will have source and target note attachments of fromView
     */
    private void reconnectNoteAttachment(View fromView, View toView) {
        // copy List to avoid problem (read/write conflict)
        var sourceEdges = new ArrayList<Edge>(fromView.getSourceEdges());
        var targetEdges = new ArrayList<Edge>(fromView.getTargetEdges());

        sourceEdges.stream() //
                .filter(GMFNotationHelper::isNoteAttachment) //
                .forEach(edge -> edge.setSource(toView));
        targetEdges.stream() //
                .filter(GMFNotationHelper::isNoteAttachment) //
                .forEach(edge -> edge.setTarget(toView));
    }

    /**
     * Move all notes or texts (i.e. PGE: pure graphical elements) from GMF view fromView to view toView.
     * 
     * <p>
     * It moves notes which are the children of fromView but not children of children.
     * </p>
     * <p>
     * This method is executed in a GMF context, i.e. it does not consider GMF view as Sirius elements. If you want move
     * note of Sirius container use {@link moveNotesOfContainer}.
     * </p>
     */
    private void moveNotesOfView(View fromView, View toView) {
        var children = new ArrayList<View>(fromView.getChildren());
        children.stream().filter(view -> { // filter notes and texts
            if (view instanceof Node node) {
                return GMFNotationHelper.isNote(node) || GMFNotationHelper.isTextNote(node);
            } else {
                return false;
            }
        }).map(Node.class::cast).forEach(note -> {
            toView.getPersistedChildren().add(note); // move
        });
    }

    /**
     * Return the part of container node that can contains notes/texts.
     * 
     * Depending of layout container returns:
     * <ul>
     * <li>Free From: the intern compartment</li>
     * <li>Horizontal/Vertical stack: the container node itself</li>
     * <li>List: nothing, list cannot contain notes or texts</li>
     * </ul>
     */
    private Optional<Node> getNoteContainer(Node node) {
        if (new ViewQuery(node).isRegionContainer()) {
            return Optional.of(node);
        } else {
            return new NodeQuery(node).getFreeFormContainerCompartment();
        }
    }

    /**
     * <p>
     * Move all notes/texts/representation links (i.e. PGE: pure graphical elements) from container fromView to
     * container toView.
     * </p>
     * 
     * <p>
     * It moves notes which are the children of fromView but not children of children.
     * </p>
     * 
     * <p>
     * This method is executed in a Sirius context. i.e. fromView and toView is considered as Sirius container.
     * </p>
     * 
     * <p>
     * If fromView or toView is not Sirius container, does nothing.
     * </p>
     * 
     * @param fromView
     *            The GMF view of the container with PGE.
     * @param toView
     *            The GMF view of the container that will have PGE after the operation.
     */
    private void moveNotesOfContainer(View fromView, View toView) {
        if (fromView instanceof Node fromNode && toView instanceof Node toNode) {
            // if fromNode have compartment and get it
            getNoteContainer(fromNode).ifPresent(fromCompartment -> {

                // if toNode have compartment and get it
                getNoteContainer(toNode).ifPresent(toCompartment -> {

                    moveNotesOfView(fromCompartment, toCompartment);
                });
            });
        }
    }

    /**
     * This method reconciles the orphan node. If a new node matches to <code>orphanNode</code>:
     * <ul>
     * <li>The note attachment linked to the orphan node are linked to the corresponding new node,</li>
     * <li>The pure graphical elements contained in the orphan node are moved to the corresponding new node.</li>
     * </ul>
     */
    private void reconciliateOrphanNode(View orphanNode) {
        if (orphanNode.getElement() instanceof DSemanticDecorator dDiagramElement) {
            // Search for the corresponding new view if it has been moved
            getCorrespondingView(dDiagramElement).ifPresent(correspondingView -> {
                // Change note attachments and contained notes from old view to new view
                reconnectNoteAttachment(orphanNode, correspondingView);
                moveNotesOfContainer(orphanNode, correspondingView);
            });
        }
    }

    /**
     * This method reconciles all orphan nodes. If new nodes matches to orphan nodes:
     * <ul>
     * <li>The note attachment linked to the orphan node are linked to the corresponding new node,</li>
     * <li>The pure graphical elements (PGE) contained in the orphan node are moved to the corresponding new node.</li>
     * </ul>
     * 
     * Orphan views will be deleted and if there are PGEs inside or note attachment, they will be deleted. So if you
     * move an element (i.e. 2 operations: "remove element" and "create element"), you need to reconnect note attachment
     * to the corresponding new view or move PGEs inside new view.
     */
    public void reconciliateOrphanNodes() {
        for (View orphanNode : orphanNodes) {
            // root
            reconciliateOrphanNode(orphanNode);

            // All children recursively
            EMFUtil.getTreeStream(orphanNode, view -> {
                return new ArrayList<View>(view.getChildren());
            }).filter(view -> { // filter on DNode and DContainer
                var query = new ViewQuery(view);
                return query.isNode() || query.isContainer();
            }).forEach(this::reconciliateOrphanNode);
        }
    }

    /**
     * This method reconciles all orphan edges. If new edges matches to orphan edges: The note attachment linked to the
     * orphan edge are linked to the corresponding new edge,</li>
     * 
     * Orphan edges will be deleted and if there are note attachments, they will be deleted. So if you reconnect an edge
     * (i.e. 2 operations: "remove edge" and "create edge"), you need to reconnect note attachment to the corresponding
     * new edge.
     */
    public void reconciliateOrphanEdges() {
        for (Edge orphanEdge : orphanEdges) {
            if (orphanEdge.getElement() instanceof DSemanticDecorator dDiagramElement) {
                // Search for the corresponding new view if it has been moved
                getCorrespondingView(dDiagramElement).ifPresent(correspondingView -> {
                    // Change note attachments from old view to new view
                    reconnectNoteAttachment(orphanEdge, correspondingView);
                });
            }
        }
    }

    /**
     * Returns associated gmf view of Sirius Element `dDiagramElement` or target of Sirius Element `dDiagramElement` in
     * created views. `Optional.empty` is returned if no corresponding view is found.
     */
    private Optional<View> getCorrespondingView(DSemanticDecorator dDiagramElement) {
        return getUniqueCreatedViewForDDiagramElement(dDiagramElement) //
                .or(() -> getUniqueCreatedViewForSemanticElement(dDiagramElement.getTarget()));
    }

    /**
     * Returns associated gmf view of Sirius Element `dDiagramElement` in created views. `Optional.empty` is returned if
     * no corresponding view is found.
     */
    private Optional<View> getUniqueCreatedViewForDDiagramElement(DSemanticDecorator dDiagramElement) {
        List<View> candidates = new ArrayList<>();
        for (View view : this.getCreatedViews()) {
            if (dDiagramElement.equals(view.getElement())) {
                candidates.add(view);
                if (candidates.size() > 1) { // more than 1 view is ambiguous for reconciliation
                    break;
                }
            }
        }
        if (candidates.size() == 1) {
            return Optional.of(candidates.get(0));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns associated gmf view of real semantic element (not sirius element) in created views. `Optional.empty` is
     * returned if no corresponding view is found.
     */
    private Optional<View> getUniqueCreatedViewForSemanticElement(EObject semanticElement) {
        List<View> candidates = new ArrayList<>();
        for (View view : this.getCreatedViews()) {
            if (view.getElement() instanceof DSemanticDecorator dDiagramElement && semanticElement.equals(dDiagramElement.getTarget())) {
                candidates.add(view);
                if (candidates.size() > 1) { // more than 1 view is ambiguous for reconciliation
                    break;
                }
            }
        }
        if (candidates.size() == 1) {
            return Optional.of(candidates.get(0));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Deletes all orphan nodes (removed or moved nodes).
     */
    public void deleteOrphanNodes() {
        orphanNodes.stream().forEach(EcoreUtil::remove);
        orphanNodes.clear();
    }

    /**
     * Deletes all orphan edges (removed or moved edges).
     */
    public void deleteOrphanEdges() {
        orphanEdges.stream().forEach(EcoreUtil::remove);
        orphanEdges.clear();
    }

    /**
     * Compute all edges adjacent to the nodes to be deleted and adds them to the list.
     */
    public void collectAttachedEdgeToNodes() {
        collectAttachedEdges(orphanNodes);
    }

    /**
     * Compute all edges adjacent to the edges to be deleted and adds them to the list (and compute attached edge).
     */
    public void collectAttachedEdgeToEdges() {
        collectAttachedEdges(orphanEdges);
    }

    /**
     * Add all incoming and outgoing edges of all views to orphan edges list :
     * <ul>
     * <li>If view is container collect also edge of all children recursively</li>
     * <li>If view is edge collect recursively attached edges</li>
     * </ul>
     * 
     * @param <T>
     *            type of collection (View, Edge or Node in general)
     * @param views
     *            collection of views for which we want all incoming and outgoing edges
     */
    private <T extends View> void collectAttachedEdges(Collection<T> views) {
        var removed = new HashSet<T>();
        while (!views.isEmpty()) {
            // Here we take view (take = remove from list), we put it aside (in removed).
            // Then, if the view has not already been processed:
            // we collect incoming and outgoing edges and we add these edges to the orphan edges list.
            T view = views.stream().findAny().orElseThrow(); // get an element
            if (removed.add(view)) {
                // Before removing this view, we must identify incoming or outgoing
                // edges of this view or of one of its children to delete them just
                // after. Indeed, an Edge without source (or target) must not exist.
                List<Edge> edgesToDelete = getIncomingOutgoingEdges(view);
                this.addOrphanEdges(edgesToDelete);
            }
            views.removeIf(v -> view == v);
        }
        views.addAll(removed);
    }

    /**
     * Get all incoming and outgoing edges of this <code>view</code> or of all of its children.
     * 
     * @param view
     *            the concern view
     * @return list of edges
     */
    private List<Edge> getIncomingOutgoingEdges(View view) {
        List<Edge> edgesToDelete = new ArrayList<>();
        edgesToDelete.addAll(view.getSourceEdges());
        edgesToDelete.addAll(view.getTargetEdges());

        List<View> children = view.getChildren();
        for (View child : children) {
            edgesToDelete.addAll(getIncomingOutgoingEdges(child));
        }
        return edgesToDelete;
    }

    /**
     * Collect all attached notes/texts/representation links (i.e. PGE: pure graphical elements).
     */
    private void collectAttachedPGE(View view) {
        // get all attached notes
        List<Edge> noteAttachments = getIncomingOutgoingEdges(view).stream() //
                .filter(GMFNotationHelper::isNoteAttachment).toList();

        noteAttachments.stream().flatMap(edge -> {
            return Stream.of(edge.getSource(), edge.getTarget());
        }).filter(attachedView -> { // all nodes linked to note attachment: filter notes/texts
            if (attachedView instanceof Node attachedNode) {
                return GMFNotationHelper.isNote(attachedNode) || GMFNotationHelper.isTextNote(attachedNode);
            } else {
                return false;
            }
        }).map(Node.class::cast).forEach(attachedNote -> { // for each note
            partialOrphanPGE.add(attachedNote);
        });
    }

    /**
     * <p>
     * Collect all notes/texts/representation links (i.e. PGE: pure graphical elements) attached to all orphan nodes.
     * </p>
     * 
     * <p>
     * This method collect attached PGE which could be alone and which could be remove according preference. This method
     * does not remove any views, it simply collects them. To remove collected elements see {@link deleteOrphanNotes}.
     * </p>
     * 
     * <p>
     * Does nothing if preference for remove PGE when remove attached element is disabled.
     * </p>
     */
    public void collectDetachedPGEFromNode() {
        if (prefRemoveAttachedPGE) {
            // collect possibly detached PGE <=> collect PGE attached to orphan view
            for (View orphanNode : orphanNodes) {
                collectAttachedPGE(orphanNode);
            }
        }
    }

    /**
     * <p>
     * Collect all notes/texts/representation links (i.e. PGE: pure graphical elements) attached to all orphan edges.
     * </p>
     * 
     * <p>
     * This method collect attached PGE which could be alone and which could be remove according preference. This method
     * does not remove any views, it simply collects them. To remove collected elements see {@link deleteOrphanNotes}.
     * </p>
     * 
     * <p>
     * Does nothing if preference for remove PGE when remove attached element is disabled.
     * </p>
     */
    public void collectDetachedPGEFromEdge() {
        if (prefRemoveAttachedPGE) {
            // collect possibly detached PGE <=> collect PGE attached to orphan view
            for (Edge orphanEdge : orphanEdges) {
                collectAttachedPGE(orphanEdge);
            }
        }
    }

    /**
     * Mark to remove all notes/texts/representation links (i.e. PGE: pure graphical elements) attached to nodes that
     * will be removed.
     * 
     * Before calling this function, you need to collect all PGE attached to view that will be removed (using method
     * collectDetachedPGEFromNode and collectDetachedPGEFromEdge)
     * 
     * Only if preference is enabled for remove PGE when remove attached element is enabled, otherwise this method does
     * nothing
     */
    public void deleteDetachedPGE() {
        if (prefRemoveAttachedPGE) {
            for (Node pureGraphicalElement : partialOrphanPGE) {
                List<Edge> sourceEdges = pureGraphicalElement.getSourceEdges();
                List<Edge> targetEdges = pureGraphicalElement.getTargetEdges();

                List<Edge> validEdges = Stream.concat(sourceEdges.stream(), targetEdges.stream()) //
                        .filter(edge -> edge.eContainer() != null).toList();

                // remove unattached notes/texts
                if (validEdges.size() == 0) {
                    EcoreUtil.remove(pureGraphicalElement);
                } else {
                    Stream<Edge> visibleEdges = validEdges.stream().filter(edge -> edge.isVisible());

                    // hide notes/texts attached to invisible element
                    if (visibleEdges.count() == 0) {
                        pureGraphicalElement.setVisible(false);
                    }
                }
            }
            partialOrphanPGE.clear();
        }
    }
}

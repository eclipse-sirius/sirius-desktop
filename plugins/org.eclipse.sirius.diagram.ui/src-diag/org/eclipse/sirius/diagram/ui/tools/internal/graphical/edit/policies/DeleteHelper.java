/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.NoNullResourceCommand;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Delete helper to execute specific operations on delete request.
 *
 * @author mchauvin
 */
public final class DeleteHelper {

    private DeleteHelper() {

    }

    /**
     * Delete all notes attached to view. If a note is attached to an other
     * view, the note is not destroyed.
     *
     * @param command
     *            the command
     * @param deletedView
     *            the view
     */
    public static void addDeleteLinkedNotesTask(Command command, final View deletedView) {
        List<Command> wrappedCommands = DeleteHelper.getWrappedCommands(command);
        if (deletedView.getDiagram().getElement() instanceof DSemanticDiagram) {
            for (Command wrappedCommand : wrappedCommands) {
                if (wrappedCommand instanceof DCommand && command.canExecute()) {
                    final DCommand cmd = (DCommand) wrappedCommand;
                    cmd.getTasks().add(new DeleteRelatedNotesTask(deletedView));
                    return;
                }
            }
        }
    }

    /**
     * Delete all note attachments attached to view.
     *
     * @param command
     *            the command
     * @param deletedView
     *            the view
     */
    public static void addDeleteLinkedNoteAttachmentsTask(Command command, final View deletedView) {
        List<Command> wrappedCommands = DeleteHelper.getWrappedCommands(command);
        if (deletedView.getDiagram().getElement() instanceof DSemanticDiagram) {
            for (Command wrappedCommand : wrappedCommands) {
                if (wrappedCommand instanceof DCommand && command.canExecute()) {
                    final DCommand cmd = (DCommand) wrappedCommand;
                    cmd.getTasks().add(new DeleteRelatedNoteAttachmentsTask(deletedView));
                    return;
                }
            }
        }
    }

    private static List<Command> getWrappedCommands(Command command) {
        List<Command> result = Lists.newArrayList();
        if (command instanceof NoNullResourceCommand) {
            Object adapter = ((NoNullResourceCommand) command).getAdapter(DCommand.class);
            if (adapter instanceof Command) {
                result.addAll(DeleteHelper.getWrappedCommands((Command) adapter));
            }
        } else if (command instanceof CompoundCommand) {
            CompoundCommand cc = (CompoundCommand) command;
            for (Command cmd : cc.getCommandList()) {
                result.addAll(DeleteHelper.getWrappedCommands(cmd));
            }
        }
        if (result.isEmpty()) {
            result.add(command);
        }
        return result;
    }

    /**
     * Delete note if this is associated to node deleted. If a note is
     * associated at two node, the note is not deleted if the two node are not
     * deleted.
     *
     * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
     *
     */
    private static final class DeleteRelatedNotesTask extends AbstractCommandTask {

        private View view;

        private Set<View> noteToHide = Sets.newHashSet();

        DeleteRelatedNotesTask(final View deletedView) {
            super();
            this.view = deletedView;
        }

        @Override
        public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
            Set<View> allViewsToDelete = Sets.newHashSet();

            allContentsViewsToDelete(view, allViewsToDelete);
            final Iterator<View> allContents = Iterators.filter(view.eAllContents(), View.class);
            while (allContents.hasNext()) {
                final Object next = allContents.next();
                allContentsViewsToDelete((View) next, allViewsToDelete);
            }

            for (final View v : allViewsToDelete) {
                EcoreUtil.remove(v);
            }

            // Hide notes associated to element hide in model.
            for (final View v : noteToHide) {
                v.setVisible(false);
            }
        }

        @Override
        public String getLabel() {
            return Messages.DeleteRelatedNotesTask_label;
        }

        private Set<View> getAllRelatedNotesToDelete(View viewToDelete) {
            Set<View> linkedViews = Sets.newHashSet();

            for (Edge sourceEdge : Iterables.filter(viewToDelete.getSourceEdges(), Edge.class)) {
                View target = sourceEdge.getTarget();
                if (GMFNotationHelper.isNoteAttachment(sourceEdge)) {
                    Set<View> linked = Sets.newHashSet();
                    linked.add(viewToDelete);
                    collectLinkedViews(target, linked);
                    linked.remove(viewToDelete);

                    linkedViews.addAll(getSafe(linked));

                }
            }

            for (Edge targetEdge : Iterables.filter(viewToDelete.getTargetEdges(), Edge.class)) {
                View source = targetEdge.getSource();
                if (GMFNotationHelper.isNoteAttachment(targetEdge)) {
                    Set<View> linked = Sets.newHashSet();
                    linked.add(viewToDelete);
                    collectLinkedViews(source, linked);
                    linked.remove(viewToDelete);

                    linkedViews.addAll(getSafe(linked));

                }
            }

            return linkedViews;

        }

        /**
         * Remove all contains set if the set contains an element that is not a
         * note. It the element is hide, hide notes associated.
         *
         * @param linked
         *            set of notes to delete.
         * @return a collection of notes
         */
        private Collection<? extends View> getSafe(Set<View> linked) {
            for (View linkedView : linked) {
                if (!(linkedView instanceof Node && GMFNotationHelper.isNote((Node) linkedView))) {
                    if (!((Node) linkedView).isVisible()) {
                        for (View linkedV : linked) {
                            if (linkedV instanceof Node && GMFNotationHelper.isNote((Node) linkedV)) {
                                noteToHide.add(linkedV);
                            }
                        }
                    }
                    return Collections.emptySet();
                }
            }

            return linked;
        }

        private void collectLinkedViews(final View v, Set<View> linkedViews) {
            linkedViews.add(v);
            for (Edge sourceEdge : Iterables.filter(v.getSourceEdges(), Edge.class)) {
                View target = sourceEdge.getTarget();
                if (GMFNotationHelper.isNoteAttachment(sourceEdge)) {
                    if (!linkedViews.contains(target) && target instanceof Node && GMFNotationHelper.isNote((Node) target)) {
                        collectLinkedViews(target, linkedViews);
                    } else {
                        linkedViews.add(target);
                    }
                }
            }

            for (Edge targetEdge : Iterables.filter(v.getTargetEdges(), Edge.class)) {
                View source = targetEdge.getSource();
                if (GMFNotationHelper.isNoteAttachment(targetEdge)) {
                    if (!linkedViews.contains(source) && source instanceof Node && GMFNotationHelper.isNote((Node) source)) {
                        collectLinkedViews(source, linkedViews);
                    } else {
                        linkedViews.add(source);
                    }
                }
            }
        }

        @SuppressWarnings("unchecked")
        private void allContentsViewsToDelete(View viewToDelete, Set<View> allViewsToDelete) {
            allViewsToDelete.addAll(getAllRelatedNotesToDelete(viewToDelete));

            for (Edge edge : Iterables.filter(Iterables.concat(viewToDelete.getSourceEdges(), viewToDelete.getTargetEdges()), Edge.class)) {
                if (GMFNotationHelper.isNoteAttachment(edge)) {
                    allViewsToDelete.add(edge);
                }
            }
        }

    }

    /**
     * Delete note attachment if this is associated to node deleted.
     *
     * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
     *
     */
    private static final class DeleteRelatedNoteAttachmentsTask extends AbstractCommandTask {

        private View view;

        DeleteRelatedNoteAttachmentsTask(final View deletedView) {
            this.view = deletedView;
        }

        @Override
        public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
            List<View> linkedViews = Lists.newArrayList();

            for (Edge sourceEdge : Iterables.filter(view.getSourceEdges(), Edge.class)) {
                if (GMFNotationHelper.isNoteAttachment(sourceEdge)) {
                    linkedViews.add(sourceEdge);
                }
            }

            for (Edge targetEdge : Iterables.filter(view.getTargetEdges(), Edge.class)) {
                if (GMFNotationHelper.isNoteAttachment(targetEdge)) {
                    linkedViews.add(targetEdge);
                }
            }

            for (final View v : linkedViews) {
                EcoreUtil.remove(v);
            }
        }

        @Override
        public String getLabel() {
            return Messages.DeleteRelatedNoteAttachmentsTask_label;
        }
    }

}

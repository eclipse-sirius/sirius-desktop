/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.diagram.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.common.tools.api.util.CommandStackUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * This class might be helpful if you want to easily "browse" a diagram editor instance to retrieve, for instance, edit
 * parts.
 * 
 * @author cbrun
 * 
 */
public class DiagramEditorBrowser {

    private final DiagramDocumentEditor editor;

    /**
     * Create a new browser for the given editor.
     * 
     * @param editor
     *            editor to browse.
     */
    public DiagramEditorBrowser(final DiagramDocumentEditor editor) {
        this.editor = editor;
    }

    /**
     * return all the edit parts matching the predicate.
     * 
     * @param match
     *            the predicate to check.
     * @return all the edit parts matching the predicate.
     */
    public Iterator<IGraphicalEditPart> findEditPart(final Predicate<IGraphicalEditPart> match) {
        return filterMatchingEditParts(match, allEditParts());
    }

    private Iterator<IGraphicalEditPart> filterMatchingEditParts(final Predicate<IGraphicalEditPart> predicate, final Iterator<IGraphicalEditPart> parts) {
        return Iterators.filter(parts, predicate);
    }

    /**
     * return an iterator browsing through all (recursively adding children) the edit parts of the diagram.
     * 
     * @return an iterator browsing through all the edit parts of the diagram.
     */
    public Iterator<IGraphicalEditPart> allEditParts() {
        @SuppressWarnings("serial")
        final AbstractTreeIterator<IGraphicalEditPart> it = new AbstractTreeIterator<IGraphicalEditPart>(editor.getDiagramEditPart()) {

            @Override
            protected Iterator<? extends IGraphicalEditPart> getChildren(final Object object) {
                if (object instanceof IGraphicalEditPart gep) {
                    return gep.getChildren().stream().filter(IGraphicalEditPart.class::isInstance).map(IGraphicalEditPart.class::cast).iterator();
                }
                return Collections.emptyIterator();
            }
        };
        return it;

    }

    /**
     * Launch an arrange all request on the diagram.
     * 
     * @throws InterruptedException
     *             when the thread computing the arrange all is interrupted.
     */
    public void arrangeAll() throws InterruptedException {

        EclipseUIUtil.synchronizeWithUIThread();

        final ArrangeRequest request = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
        request.setPartsToArrange(Arrays.asList(editor.getDiagramEditPart()));
        editor.getDiagramEditPart().refresh();
        editor.getDiagramEditPart().performRequest(request);
        CommandStackUtil.flushOperations(editor.getEditingDomain().getCommandStack());
        editor.getDiagramEditPart().getRoot().getViewer().flush();

        EclipseUIUtil.synchronizeWithUIThread();
        Thread.sleep(30000);
        EclipseUIUtil.synchronizeWithUIThread();
    }

}

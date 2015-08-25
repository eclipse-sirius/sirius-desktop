/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.action;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * This class might be helpful if you want to easily "browse" a diagram editor
 * instance to retrieve, for instance, edit parts.
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
        final Iterator<IGraphicalEditPart> mines = Iterators.filter(parts, new Predicate<IGraphicalEditPart>() {

            public boolean apply(final IGraphicalEditPart input) {
                return predicate.apply(input);

            }
        });
        return mines;

    }

    /**
     * return an iterator browsing through all (recursively adding children) the
     * edit parts of the diagram.
     * 
     * @return an iterator browsing through all the edit parts of the diagram.
     */
    public Iterator<IGraphicalEditPart> allEditParts() {
        @SuppressWarnings("serial")
        final AbstractTreeIterator<IGraphicalEditPart> it = new AbstractTreeIterator<IGraphicalEditPart>(editor.getDiagramEditPart()) {

            @SuppressWarnings("unchecked")
            @Override
            protected Iterator<? extends IGraphicalEditPart> getChildren(final Object object) {
                if (object instanceof IGraphicalEditPart) {
                    return ((IGraphicalEditPart) object).getChildren().iterator();
                }
                return Collections.EMPTY_LIST.iterator();
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
        request.setPartsToArrange(Lists.newArrayList(editor.getDiagramEditPart()));
        editor.getDiagramEditPart().refresh();
        editor.getDiagramEditPart().performRequest(request);
        editor.getEditingDomain().getCommandStack().flush();
        editor.getDiagramEditPart().getRoot().getViewer().flush();

        EclipseUIUtil.synchronizeWithUIThread();
        Thread.sleep(30000);
        EclipseUIUtil.synchronizeWithUIThread();
    }

}

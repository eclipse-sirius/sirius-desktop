/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.api.pages;

import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * This component provides instances of {@link AbstractSessionEditorPage} that
 * are pages identified by the same id used by a {@link PageProvider} instance.
 * This pages created by this component can be used in any different
 * {@link MultiPageEditorPart}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public abstract class PageProvider {
    /**
     * Returns a map of page id to the @link Supplier} initializing the
     * corresponding page {@link AbstractSessionEditorPage} to display in a
     * multi-page editor.
     * 
     * @param editor
     *            the aird editor's session from which page request is done.
     * 
     * @return a map of page id to the @link Supplier} initializing the
     *         corresponding page {@link AbstractSessionEditorPage} to display
     *         in a multi-page editor.
     */
    public abstract Map<String, Supplier<AbstractSessionEditorPage>> getPages(SessionEditor editor);

    /**
     * Returns a filter that will reduce the call to the method
     * {@link PageProvider#getPages(SessionEditor)} for better performances.
     * Without the filter, the method is called when any resource set event
     * occurs on the editor's session.
     * 
     * By default, the {@link NotificationFilter#NOT_TOUCH} filter is provided.
     * 
     * @return a filter that will reduce the call to the method
     *         {@link PageProvider#getPages(SessionEditor)} for better
     *         performances.
     */
    public NotificationFilter getFilterForPageRequesting() {
        return NotificationFilter.NOT_TOUCH;
    }

    /**
     * Returns true if this provider provides pages with the given id.
     * 
     * @param pageId
     *            the page's id from which we want to know if this provider
     *            provides pages with this id.
     * @param editor 
     *            the aird editor's session from which page request is done.
     * @return true if this provider provides pages with the given id. False
     *         otherwise.
     */
    public abstract boolean provides(String pageId, SessionEditor editor);
}

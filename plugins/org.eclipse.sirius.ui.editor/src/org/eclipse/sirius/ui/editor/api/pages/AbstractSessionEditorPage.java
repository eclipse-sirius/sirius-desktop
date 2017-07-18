/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.ui.editor.api.pages;

import java.util.Optional;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;
import org.eclipse.sirius.ui.editor.api.pages.PageUpdateCommandBuilder.PageUpdateCommand;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

/**
 * Custom page provided to session editor must extends this class.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public abstract class AbstractSessionEditorPage extends FormPage {

    /**
     * A constructor that creates the page and initializes it with the editor.
     *
     * @param editor
     *            the parent editor
     * @param id
     *            the unique identifier
     * @param title
     *            the page title
     */
    public AbstractSessionEditorPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
    }

    /**
     * The constructor. The parent editor need to be passed in the
     * <code>initialize</code> method if this constructor is used.
     *
     * @param id
     *            a unique page identifier
     * @param title
     *            a user-friendly page title
     */
    public AbstractSessionEditorPage(String id, String title) {
        super(id, title);
    }

    /**
     * Returns the id of the page to take in consideration when positioning this
     * page.
     * 
     * @return the id of the page to take in consideration when positioning this
     *         page. {@link Optional#empty()} if no positioning is provided.
     */
    public abstract Optional<String> getLocationId();

    /**
     * Returns the kind of positioning to apply regarding the target page
     * returned by {@link AbstractSessionEditorPage#getLocationId()}.
     * 
     * @return the kind of positioning to apply regarding the target page
     *         returned by
     *         {@link AbstractSessionEditorPage#getLocationId()}.{@link Optional#empty()}
     *         if no positioning is provided.
     */
    public abstract Optional<PositioningKind> getPositioning();

    /**
     * This method notifies you when a {@link ResourceSetChangeEvent} occurs on
     * the session's resource set of the session editor containing your page. If
     * some changes must be done to the page at editor's level, you can return a
     * {@link PageUpdateCommand} built with {@link PageUpdateCommandBuilder} to
     * do so.
     * 
     * @param resourceSetChangeEvent
     *            the event that occurred.
     * @return an {@link PageUpdateCommand} the editor should execute.
     *         {@link Optional#empty()} if no update should be done.
     */
    public abstract Optional<PageUpdateCommand> resourceSetChanged(ResourceSetChangeEvent resourceSetChangeEvent);

    /**
     * Notifies the page that its visibility status has changed. You can return
     * a {@link PageUpdateCommand} built with {@link PageUpdateCommandBuilder}
     * if a page update must be done from session editor owning this page.
     * 
     * The method is call with the parameter isVisible to true when the page tab
     * is selected.
     * 
     * The method is call with the parameter isVisible to false when any page
     * tab except the one of this page is selected.
     * 
     * 
     * @param isVisible
     *            true if the page has just been made visible. I.e page tab has
     *            been clicked. False if the page has just been made invisible.
     *            I.e another page tab has been clicked.
     * @return a {@link PageUpdateCommand} built with
     *         {@link PageUpdateCommandBuilder} if a page update must be done
     *         from session editor owning this page. {@link Optional#empty()} if
     *         no update must be done.
     */
    public abstract Optional<PageUpdateCommand> pageChanged(boolean isVisible);

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
     *         performances. {@link Optional#empty()} if no filtering should be
     *         done.
     */
    public Optional<NotificationFilter> getFilterForPageRequesting() {
        return Optional.of(NotificationFilter.NOT_TOUCH);
    }

}

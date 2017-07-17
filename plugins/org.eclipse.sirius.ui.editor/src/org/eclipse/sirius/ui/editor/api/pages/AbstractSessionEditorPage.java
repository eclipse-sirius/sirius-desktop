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

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
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
     *         page.
     */
    public abstract String getLocationId();

    /**
     * Returns the kind of positioning to apply regarding the target page
     * returned by {@link AbstractSessionEditorPage#getLocationId()}.
     * 
     * @return the kind of positioning to apply regarding the target page
     *         returned by {@link AbstractSessionEditorPage#getLocationId()}.
     */
    public abstract PositioningKind getPositioning();

    /**
     * This method notifies when a {@link ResourceSetChangeEvent} occurs on
     * the*session's resource set of the session editor containing your page. If
     * some* change must be done to the page at editor'slevel, you can return a
     * list*of {@link PageUpdateCommand}to do so. The command must be provided
     * by* using the factory {@link PageUpdateCommand }.**
     * 
     * @param resourceSetChangeEvent
     *            the event that occurred.*@return an optional
     *            {@link PageUpdateCommand} the editor should execute.* It can
     *            be null.
     */
    public abstract PageUpdateCommand notifyAndGetUpdateCommands(ResourceSetChangeEvent resourceSetChangeEvent);

}

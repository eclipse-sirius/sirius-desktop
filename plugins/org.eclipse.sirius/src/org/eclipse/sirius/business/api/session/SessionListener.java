/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session;

/**
 * Listener on Session changes.
 * 
 * @author mchauvin
 */
public interface SessionListener {

    /**
     * when the selected views change.
     */
    int SELECTED_VIEWS_CHANGE_KIND = 0;

    /**
     * when something else change, views are not mandatory to update but it is
     * recommended. Please be specific and use any other notification code if
     * you can.
     */
    int OTHER = 1;

    /**
     * when a session is in dirty state.
     * 
     * @since 0.9.0
     */
    int DIRTY = 2;

    /**
     * When a session is in sync with the resources state.
     * 
     * @since 0.9.0
     */
    int SYNC = 3;

    /**
     * When a resource content is about to be replaced.
     * 
     * @since 0.9.0
     */
    int ABOUT_TO_BE_REPLACED = 4;

    /**
     * When a resource content is replaced.
     * 
     * @since 0.9.0
     */
    int REPLACED = 5;

    /**
     * when the session is opening.
     * 
     * @since 0.9.0
     */
    int OPENING = 6;

    /**
     * When the session is opened.
     * 
     * @since 0.9.0
     */
    int OPENED = 7;

    /**
     * When the session is closing.
     * 
     * @since 0.9.0
     */
    int CLOSING = 8;

    /**
     * When the session is closed.
     * 
     * @since 0.9.0
     */
    int CLOSED = 9;

    /**
     * Representations changes.
     * 
     * @since 0.9.0
     */
    int REPRESENTATION_CHANGE = 10;

    /**
     * Semantic changes.
     * 
     * @since 0.9.0
     */
    int SEMANTIC_CHANGE = 11;

    /**
     * Permission on representation edition granted only to the current user and
     * denied for others.
     * 
     * @since 0.9.0
     */
    int REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY = 12;

    /**
     * Permission on representation edition granted for the current user.
     * 
     * @since 0.9.0
     */
    int REPRESENTATION_EDITION_PERMISSION_GRANTED = 13;

    /**
     * Permission on representation edition denied for the current user.
     * 
     * @since 0.9.0
     */
    int REPRESENTATION_EDITION_PERMISSION_DENIED = 14;

    /**
     * Value to notify that a DRepresentation is frozen, i.e. that opened
     * DialectEditor on this DRepresentation mustn't allows edition and this
     * DialectEditor must be reopened to do edition.
     * 
     * @since 0.9.0
     */
    int REPRESENTATION_FROZEN = 15;

    /**
     * Value to notify that some Viewpoint Specification Model were updated. For
     * example, a workspace .odesign file used in the current session was
     * modified or deleted.
     */
    int VSM_UPDATED = 16;

    /**
     * notify when a change occurs in session.
     * 
     * @param changeKind
     *            the kind of change
     */
    void notify(int changeKind);

}

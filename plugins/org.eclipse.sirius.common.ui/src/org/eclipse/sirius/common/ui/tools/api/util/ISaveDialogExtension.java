/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.util;

import java.util.Map;

/**
 * An extension that allows to extends or redefine the save dialogs used in
 * {@link SWTUtil#showSaveDialog(Object, String, boolean)}.
 * 
 * Allows to :
 * <ul>
 * <li>Customize the choices given to the end-user when a save dialog opens (for
 * example default is "Save" and "Discard")</li>
 * <li>Add additional behavior according to the choice made by user.</li>
 * </ul>
 * 
 * @since 0.9.0
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public interface ISaveDialogExtension {

    /**
     * Indicates if this {@link ISaveDialogExtension} must be use when saving
     * the given object.
     * 
     * @param objectToSave
     *            the object that is about to be saved
     * @return true if this {@link ISaveDialogExtension} must be use when saving
     *         the given object, false otherwise
     */
    boolean isSaveDialogFor(Object objectToSave);

    /**
     * Returns a {@link Map} of String/Integer corresponding to the available
     * choices for a save dialog (do no override CANCEL, this will be added
     * automatically if possible).
     * 
     * @param stillOpenElsewhere
     *            indicates if another editor is opened on the same session
     * 
     * @return a {@link Map} of String/Integer corresponding to the available
     *         choices for a save dialog (e.g. "Save and Commit",
     *         "Save and keep local", "Discard").
     */
    Map<String, Integer> getButtons(boolean stillOpenElsewhere);

    /**
     * Defines the behavior to apply when faced to the given user choice, and
     * returns one of the standard return value for a save dialog (
     * {@link org.eclipse.ui.ISaveablePart2#YES},
     * {@link org.eclipse.ui.ISaveablePart2#NO} or
     * {@link org.eclipse.ui.ISaveablePart2#CANCEL}).
     * 
     * @param objectToSave
     *            the object to save
     * @param userChoice
     *            the choice made by user (0 for the first choice returned by
     *            {@link ISaveDialogExtension#getButtons(boolean)}, 1 for second...)
     * @param stillOpenElsewhere
     *            indicates if another editor is opened on the session
     * @return one of the standard return value for a save dialog (
     *         {@link org.eclipse.ui.ISaveablePart2#YES},
     *         {@link org.eclipse.ui.ISaveablePart2#NO} or
     *         {@link org.eclipse.ui.ISaveablePart2#CANCEL}
     */
    int reactToValue(Object objectToSave, int userChoice, boolean stillOpenElsewhere);

}

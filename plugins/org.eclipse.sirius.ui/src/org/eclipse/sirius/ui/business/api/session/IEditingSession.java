/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import java.util.Collection;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;

/**
 * An editing session is responsible of link between graphical resources
 * modifications.
 * 
 * @author cbrun
 */
public interface IEditingSession extends DeprecatedIEditingSession {
    
    /**
     * Get the wrapped {@link Session}.
     * 
     * @return the session.
     * @since 2.8
     */
    Session getSession();
    
    /**
     * Attach an editor to the current {@link IEditingSession}.
     * 
     * @param editor
     *            editor to attach.
     */
    void attachEditor(DialectEditor editor);

    /**
     * Check if the editing session handles this editor.
     * 
     * @param editor
     *            editor to check
     * @return <code>true</code> if this ui session handles the editor given as
     *         parameter, <code>false</code> otherwise
     */
    boolean handleEditor(IEditorPart editor);

    /**
     * Detach an editor from the current Session.
     * 
     * @param editor
     *            editor to detach.
     */
    void detachEditor(DialectEditor editor);

    /**
     * Returns editors.
     * 
     * @return editors
     * @since 2.4
     */
    Collection<DialectEditor> getEditors();

    /**
     * Close editors from this ui session.
     * 
     * @param editors
     *            the editors to close
     * @param save
     *            <code>true</code> to save the session contents if required
     *            (recommended), and <code>false</code> to discard any unsaved
     *            changes
     */
    void closeEditors(boolean save, Collection<? extends DialectEditor> editors);

    /**
     * Close editors from this ui session.
     * 
     * @param editors
     *            the editors to close
     * @param save
     *            <code>true</code> to save the session contents if required
     *            (recommended), and <code>false</code> to discard any unsaved
     *            changes
     * @since 2.1
     */
    void closeEditors(boolean save, DialectEditor... editors);

    /**
     * return true if no more editor is opened for the current
     * {@link IEditingSession}.
     * 
     * @return true if no more editor is opened for the current
     *         {@link IEditingSession}.
     */
    boolean isEmpty();

    /**
     * Tell whether the editing session only has one opened editor.
     * 
     * @return true if only one editor is opened for this session.
     */
    boolean isLastOpenedEditor();

    /**
     * Defines when an editor must be saved when it is closed. This is managed
     * by two rules :
     * <ol>
     * <li>On closing one editor, if the preference
     * IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN has a false
     * value, it must be saved if it remains only one editor in the session</li>
     * <li>On closing all editors, only one must be saved</li>
     * </ol>
     * 
     * @since 2.1
     * @param editor
     *            the editor that we closing
     * @return true if only one editor is opened for this session.
     */
    boolean needToBeSavedOnClose(IEditorPart editor);

    /**
     * 
     * Prompts the user for input on what to do with unsaved session.
     * 
     * @see org.eclipse.ui.ISaveablePart2#promptToSaveOnClose()
     * 
     * @since 4.1
     * 
     * @return one of the standard return value for a save dialog
     *         {@link org.eclipse.ui.ISaveablePart2#YES},
     *         {@link org.eclipse.ui.ISaveablePart2#NO},
     *         {@link org.eclipse.ui.ISaveablePart2#CANCEL} or
     *         {@link org.eclipse.ui.ISaveablePart2#DEFAULT},
     */
    int promptToSaveOnClose();

    /**
     * Open the ui session.
     */
    void open();

    /**
     * Check if an editing session is opened.
     * 
     * @return <code>true</code> if the {@link IEditingSession} is opened,
     *         <code>false</code> otherwise
     */
    boolean isOpen();

    /**
     * close the ui session without saving.
     */
    void close();

    /**
     * Close the ui session.
     * 
     * @param save
     *            <code>true</code> to save the session contents if required
     *            (recommended), and <code>false</code> to discard any unsaved
     *            changes
     */
    void close(boolean save);

    /**
     * Returns <code>true</code> if this session is the session for the given
     * editor input.
     * 
     * @param editorInput
     *            the editor input.
     * @return <code>true</code> if this session is the session for the given
     *         editor input.
     */
    boolean isSessionFor(IEditorInput editorInput);

    /**
     * Check if one of the editors of this session corresponds to this
     * representation and return it if true.
     * 
     * @param representation
     *            The representation to search
     * @return The corresponding editor or null
     */
    DialectEditor getEditor(DRepresentation representation);

    
    /**
     * Notify the editing session about the event given as parameter.
     * @param event the event
     */
    void notify(EditingSessionEvent event);
    
}

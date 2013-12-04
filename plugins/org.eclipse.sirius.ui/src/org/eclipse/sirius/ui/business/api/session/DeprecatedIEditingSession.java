/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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

import org.eclipse.ui.IEditorPart;
import org.eclipse.sirius.viewpoint.DRepresentation;

public interface DeprecatedIEditingSession {

    /**
     * Attach an editor to the current {@link IEditingSession}.
     * 
     * @param editor
     *            editor to attach.
     */
    @Deprecated
    void attachEditor(IEditorPart editor);

    /**
     * Detach an editor from the current Session.
     * 
     * @param editor
     *            editor to detach.
     */
    @Deprecated
    void detachEditor(IEditorPart editor);

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
    @Deprecated
    void closeEditors(Collection<? extends IEditorPart> editors, boolean save);

    /**
     * Close editors from this ui session.
     * 
     * @param editors
     *            the editors to close
     * @param save
     *            <code>true</code> to save the session contents if required
     *            (recommended), and <code>false</code> to discard any unsaved
     *            changes
     * @since 0.9.0
     */
    @Deprecated
    void closeEditors(boolean save, IEditorPart... editors);

    /**
     * Check if one of the editors of this session corresponds to this
     * representation and return it if true.
     * 
     * @param representation
     *            The representation to search
     * @return The corresponding editor or null
     */
    @Deprecated
    IEditorPart getEditor(DRepresentation representation);

}

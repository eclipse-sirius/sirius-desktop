/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.api.dialect;

import org.eclipse.sirius.ui.business.api.editor.ISiriusEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * An interface to implement for all dialect editors.
 * 
 * @author mchauvin
 */
public interface DialectEditor extends ISiriusEditor {

    /** The property id for refresh event. */
    int PROP_REFRESH = 0x102;

    /** The property id forcing refresh event. */
    int PROP_FORCE_REFRESH = 0x103;

    /**
     * Ask if this dialectEditor must be refreshed.
     * 
     * @param propId
     *            the property id of the event
     * @return true if this editor must be refresh, false otherwise.
     */
    boolean needsRefresh(int propId);

    /**
     * Launch the representation validation.
     */
    void validateRepresentation();

    /**
     * Get the representation displayed by this editor if there is one, <code>null</code> otherwise.
     * 
     * @return the representation displayed
     */
    DRepresentation getRepresentation();

    /**
     * Set the dialog factory to use for this editor.
     * 
     * @param dialogFactory
     *            the dialog factory to use
     */
    void setDialogFactory(DialectEditorDialogFactory dialogFactory);

    /**
     * Returns the {@link DialectEditorDialogFactory} associated to this editor.
     * 
     * @return the {@link DialectEditorDialogFactory} associated to this editor
     * 
     * @since 0.9.0
     */
    DialectEditorDialogFactory getDialogFactory();
    
    /**
     * Method that is called in sync when the editor is asking to be closed (the real close is done in async). This
     * allows to dispose actions, for example, as soos as the close is requested.
     */
    default void preClose() {
        // Do nothing by default;
    }
}

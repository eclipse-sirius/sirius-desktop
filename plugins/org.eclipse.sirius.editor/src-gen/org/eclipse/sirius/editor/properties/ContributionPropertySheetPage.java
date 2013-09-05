/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties;

// Start of user code imports

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;

// End of user code imports

/**
 * The Contribution's property sheet page.
 */
public class ContributionPropertySheetPage extends TabbedPropertySheetPage {

    /*
     * Start of user code attributes
     */

    /*
     * End of user code attributes
     */

    /** The Contribution EMF editor. */
    protected SiriusEditor editor;

    /** The Label provider for this property sheet page. */
    protected ContributionLabelProvider labelProvider;

    /**
     * Contructor for this property sheet page.
     * 
     * @param editor
     *            The EMF editor contributor of the property sheet page.
     */
    public ContributionPropertySheetPage(SiriusEditor editor) {
        super(editor);
        this.editor = editor;
    }

    /**
     * Returns the EMF editor of this property sheet page.
     * 
     * @return The EMF editor of this property sheet page.
     */
    public SiriusEditor getEditor() {
        return editor;
    }

    /**
     * Get the EMF AdapterFactory for this editor.
     * 
     * @return The EMF AdapterFactory for this editor.
     */
    public AdapterFactory getAdapterFactory() {
        return editor.getAdapterFactory();
    }

    /*
     * Start of user code methods
     */

    /*
     * End of user code methods
     */

}

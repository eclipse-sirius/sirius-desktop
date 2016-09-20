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
package org.eclipse.sirius.ui.tools.api.properties;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * The DTable's property sheet page.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class DTablePropertySheetpage extends TabbedPropertySheetPage {
    /** The DTable EMF editor. */
    protected AbstractDTreeEditor editor;

    /**
     * Constructor for this property sheet page.
     * 
     * @param editor
     *            The EMF editor contributor of the property sheet page.
     */
    public DTablePropertySheetpage(final AbstractDTreeEditor editor) {
        super(editor);
        this.editor = editor;
    }

    /**
     * Returns the EMF editor of this property sheet page.
     * 
     * @return The EMF editor of this property sheet page.
     */
    public AbstractDTreeEditor getEditor() {
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

    /**
     * Get the update status.
     * 
     * @return the update status
     */
    public boolean isUpdateEnabled() {
        return editor.isPropertiesUpdateEnabled();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage#refresh()
     */
    @Override
    public void refresh() {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_PROPERTIES_VIEW_KEY);
        if (getCurrentTab() != null) {
            super.refresh();
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_PROPERTIES_VIEW_KEY);
    }
}

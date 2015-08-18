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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        boolean isCurrentTabNull = true;
        try {
            try {
                Class.forName("org.eclipse.ui.internal.views.properties.tabbed.view.Tab"); //$NON-NLS-1$
                // We are in eclipse 3.3
                final Method searchMethod = this.getClass().getMethod("getCurrentTab", (Class<?>[]) null); //$NON-NLS-1$
                isCurrentTabNull = searchMethod.invoke(this) == null;
            } catch (final ClassNotFoundException cnfe) {
                // We are in eclipse 3.4
                final Method searchMethod = this.getClass().getMethod("getCurrentTab", (Class<?>[]) null); //$NON-NLS-1$
                isCurrentTabNull = searchMethod.invoke(this) == null;
            }
        } catch (final SecurityException e) {
            // Do nothing
        } catch (final NoSuchMethodException e) {
            // Do nothing
        } catch (final IllegalArgumentException e) {
            // Do nothing
        } catch (final IllegalAccessException e) {
            // Do nothing
        } catch (final InvocationTargetException e) {
            // Do nothing
        }

        if (!isCurrentTabNull) {
            super.refresh();
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_PROPERTIES_VIEW_KEY);
    }
}

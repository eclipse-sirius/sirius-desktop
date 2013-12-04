/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.views.modelexplorerview;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;

/**
 * An extension that allows to provide a tab to the model explorer view.
 * 
 * @since 0.9.0
 * @author mporhel
 * 
 */
public interface IModelExplorerTabExtension {

    /**
     * 
     * Initializes this tab extension with the given view site.
     * <p>
     * This method is automatically called by the workbench shortly after the
     * part is instantiated. It marks the start of the views's lifecycle.
     * Clients must not call this method.
     * </p>
     * 
     * @see org.eclipse.ui.IViewPart#init(IViewSite)
     * 
     * 
     * 
     * @param site
     *            the view site
     */
    void init(IViewSite site);
	
	/**
     * Create the provided tab control the control used to fill the client area
     * of the tab folder when the user selects the tab item corresponding to
     * this extension.
     * 
     * @param tabFolder
     *            the parent composite {@link CTabFolder}.
     * @return the created control.
     */
    Control createTabControl(CTabFolder tabFolder);

    /**
     * ToolTipText for the tab item corresponding to this extension.
     * 
     * @return a tooltip / name for the tab item.
     */
    String getToolTipText();

    /**
     * Get the actions to display for this tab.
     * @return actions, never <code>null</code> should be returned
     */
	Iterable<IAction> getActions();
    
    /**
     * Note: This method is for internal use only. Clients should not call this
     * method.
     * 
     * This method will be invoked when the DisposeListener is notified of the
     * disposal of the extended Eclipse view part.
     */
    void dispose();

}

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
package org.eclipse.sirius.ui.tools.internal.views.sessionview;

import java.util.Collections;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerTabExtension;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

public class OpenedSessionsTab implements IModelExplorerTabExtension {

    private DesignerSessionView view;

    /**
     * {@inheritDoc}
     */
    public void init(IViewSite site) {
        view = new DesignerSessionView();
        
        try {
            view.init(site);
        } catch (PartInitException e) {
            /* do nothing this should not happen */
            SiriusPlugin.getDefault().warning("not unable to init the old desiner session view", e); ////$NON-NLS-1$
        }
    }

    /**
     * {@inheritDoc}
     */
    public Control createTabControl(CTabFolder tabFolder) {
        view.createPartControl(tabFolder);
        return view.getControl();
    }

    /**
     * {@inheritDoc}
     */
    public String getToolTipText() {
        return "Model Content";
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IAction> getActions() {
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        view.dispose();
        view = null;
    }

}

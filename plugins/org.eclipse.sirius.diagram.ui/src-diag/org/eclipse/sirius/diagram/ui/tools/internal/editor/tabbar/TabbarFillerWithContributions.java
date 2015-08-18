/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Fill the toolbar when a diagram is selected.
 * 
 * @author mchauvin
 */
public class TabbarFillerWithContributions extends AbstractTabbarFiller {

    private static final String ARRANGE_SELECTION = "org.eclipse.sirius.diagram.ui.tabbar.arrangeselection"; //$NON-NLS-1$

    private static final String REFRESH = "org.eclipse.sirius.diagram.ui.tabbar.refresh"; //$NON-NLS-1$

    private static final String LAYER_FILTER = "org.eclipse.sirius.diagram.ui.tabbar.layerfilter"; //$NON-NLS-1$

    private static final String HIDE_PIN = "org.eclipse.sirius.diagram.ui.tabbar.hidepin"; //$NON-NLS-1$

    private static final String PAST = "org.eclipse.sirius.diagram.ui.tabbar.past"; //$NON-NLS-1$

    private static final String HIDE_DELETE = "org.eclipse.sirius.diagram.ui.tabbar.hidedelete"; //$NON-NLS-1$

    private static final String ZOOM = "org.eclipse.sirius.diagram.ui.tabbar.zoom"; //$NON-NLS-1$

    private static final String EXPORT = "org.eclipse.sirius.diagram.ui.tabbar.export"; //$NON-NLS-1$

    private static final String FONT = "org.eclipse.sirius.diagram.ui.tabbar.font"; //$NON-NLS-1$

    private static final String STYLE = "org.eclipse.sirius.diagram.ui.tabbar.style"; //$NON-NLS-1$

    private static final String SIZE = "org.eclipse.sirius.diagram.ui.tabbar.size"; //$NON-NLS-1$

    /**
     * Construct a new instance.
     * 
     * @param manager
     *            the toolbar manager
     * @param page
     *            the workbench page
     */
    public TabbarFillerWithContributions(ToolBarManager manager, IWorkbenchPage page) {
        super(manager, page);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarFiller#doFill()
     */
    @Override
    protected void doFill() {

        configureGroupSeparators();

        addTabbarContributions();
    }

    @Override
    public void dispose() {
        releaseTabbarContributions();

        super.dispose();
    }

    private void configureGroupSeparators() {
        addSeparator(ARRANGE_SELECTION);
        addSeparator(REFRESH);
        addSeparator(LAYER_FILTER);
        addSeparator(HIDE_PIN);
        addSeparator(PAST);
        addSeparator(HIDE_DELETE);
        addSeparator(ZOOM);
        addSeparator(EXPORT);
        addSeparator(FONT);
        addSeparator(STYLE);
        addSeparator(SIZE);

    }
}

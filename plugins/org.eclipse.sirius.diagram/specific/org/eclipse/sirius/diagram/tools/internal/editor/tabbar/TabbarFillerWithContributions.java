/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.editor.tabbar;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Fill the toolbar when a diagram is selected.
 * 
 * @author mchauvin
 */
public class TabbarFillerWithContributions extends AbstractTabbarFiller {

    private static final String ARRANGE_SELECTION = "org.eclipse.sirius.diagram.tabbar.arrangeselection";

    private static final String REFRESH = "org.eclipse.sirius.diagram.tabbar.refresh";

    private static final String LAYER_FILTER = "org.eclipse.sirius.diagram.tabbar.layerfilter";

    private static final String HIDE_PIN = "org.eclipse.sirius.diagram.tabbar.hidepin";

    private static final String PAST = "org.eclipse.sirius.diagram.tabbar.past";

    private static final String HIDE_DELETE = "org.eclipse.sirius.diagram.tabbar.hidedelete";

    private static final String ZOOM = "org.eclipse.sirius.diagram.tabbar.zoom";

    private static final String EXPORT = "org.eclipse.sirius.diagram.tabbar.export";

    private static final String FONT = "org.eclipse.sirius.diagram.tabbar.font";

    private static final String STYLE = "org.eclipse.sirius.diagram.tabbar.style";

    private static final String SIZE = "org.eclipse.sirius.diagram.tabbar.size";

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

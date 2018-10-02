/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.view;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;

/**
 * The viewpoint outline view wrapper. it allows one to activate/deactivate
 * layers, filters and play with reveal/hide commands.
 * 
 * @author mchauvin
 */
public class SiriusOutlineView {

    private static final int LAYER_TEXT_COLUMN_INDEX = 2;

    private static final int FILTER_TEXT_COLUMN_INDEX = 1;

    private final SWTBotView view;

    private final SWTWorkbenchBot bot;

    /**
     * Create a new instance.
     * 
     * @param bot
     *            the workbench bot
     * @param view
     *            the outline view
     */
    public SiriusOutlineView(final SWTWorkbenchBot bot, final SWTBotView view) {
        this.view = view;
        this.bot = bot;
    }

    /**
     * Activate the layer page.
     * 
     * @return the layers page
     */
    public SiriusOutlineView layers() {
        view.toolbarToggleButton("Layers").click();
        return this;
    }

    /**
     * Activate or deactivate a layer.
     * 
     * @param layer
     *            the layer name
     */
    public void activateLayer(final String layer) {
        activate(layer, SiriusOutlineView.LAYER_TEXT_COLUMN_INDEX);
    }

    /**
     * Activate the filters page.
     * 
     * @return the filters page
     */
    public SiriusOutlineView filters() {
        view.toolbarToggleButton("Filters").click();
        return this;
    }

    /**
     * Activate or deactivate a filter.
     * 
     * @param filter
     *            the filter name
     */
    public void activateFilter(final String filter) {
        activate(filter, SiriusOutlineView.FILTER_TEXT_COLUMN_INDEX);
    }

    private void activate(final String id, final int textColumn) {
        final SWTBot tableBot = view.bot();
        final SWTBotTable table = tableBot.table();
        for (int i = 0; i < table.rowCount(); i++) {
            final int rowPosition = i;
            final SWTBotTableItem item = table.getTableItem(rowPosition);
            final String text = item.getText(textColumn);
            if (id.equals(text)) {
                table.click(rowPosition, 0);
            }
        }
    }

}

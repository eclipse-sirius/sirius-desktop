/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Object "browse" local session view.
 * 
 * @author dlecan
 */
public class UILocalSessionBrowser extends AbstractUIElementWithNextTreeItem {

    /**
     * Label.
     */
    public static final String PER_RESOURCE_LABEL = "Representations per resource";

    /**
     * Label.
     */
    public static final String PER_CATEGORY_LABEL = "Representations per category";

    /**
     * Constructor.
     * 
     * @param rootTreeItem
     *            Root item
     */
    public UILocalSessionBrowser(final SWTBotTreeItem rootTreeItem) {
        super(rootTreeItem);
    }

    /**
     * (Optional) Per resource browse. Can be null if there is only one session
     * resource.
     * 
     * @return Per resource browser
     */
    public UILSResourceBrowser perResource() {
        return new UILSResourceBrowser(getNextNode(UILocalSessionBrowser.PER_RESOURCE_LABEL));
    }

    /**
     * Per category browse.
     * 
     * @return Per category browser
     */
    public UILSCategoryBrowser perCategory() {
        return new UILSCategoryBrowser(getNextNode(UILocalSessionBrowser.PER_CATEGORY_LABEL));
    }

    /**
     * Select viewpoint in local session.
     * 
     * @param viewpointName
     *            Viewpoint name to select.
     * @return viewpoint browser.
     */
    public UILSViewpointBrowser selectViewpoint(final String viewpointName) {
        return new UILSViewpointBrowser(getNextNode(viewpointName));
    }

    /**
     * Per semantic browse.
     * 
     * @return Per semantic browse node
     */
    public SWTBotTreeItem perSemantic() {

        for (SWTBotTreeItem currentTreeItem : getTreeItem().getItems()) {
            if ((!currentTreeItem.getText().equals(UILocalSessionBrowser.PER_CATEGORY_LABEL)) && (!currentTreeItem.getText().equals(UILocalSessionBrowser.PER_RESOURCE_LABEL))) {
                return currentTreeItem.expand();
            }
        }
        return null;
    }

}

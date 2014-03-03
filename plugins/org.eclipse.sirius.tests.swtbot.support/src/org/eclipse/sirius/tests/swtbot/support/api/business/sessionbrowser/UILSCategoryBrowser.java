/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Category browser.
 * 
 * @author dlecan
 */
public class UILSCategoryBrowser extends AbstractUIElementWithNextTreeItem {

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item for this element.
     */
    public UILSCategoryBrowser(final SWTBotTreeItem treeItem) {
        super(treeItem);
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

}

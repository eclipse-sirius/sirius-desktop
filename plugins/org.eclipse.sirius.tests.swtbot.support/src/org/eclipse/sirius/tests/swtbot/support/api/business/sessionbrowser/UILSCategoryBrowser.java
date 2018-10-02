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

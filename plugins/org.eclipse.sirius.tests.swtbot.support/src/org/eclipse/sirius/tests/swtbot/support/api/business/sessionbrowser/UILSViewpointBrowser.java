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
 * Viewpoint browser.
 * 
 * @author dlecan
 */
public class UILSViewpointBrowser extends AbstractUIElementWithNextTreeItem {

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item for this element.
     */
    public UILSViewpointBrowser(final SWTBotTreeItem treeItem) {
        super(treeItem);
    }

    /**
     * Selection representation by its name.
     * 
     * @param representationLabelName
     *            Representation label name.
     * @return Representation browser.
     */
    public UILSRepresentationBrowser selectRepresentation(final String representationLabelName) {
        return new UILSRepresentationBrowser(getNextNode(representationLabelName));
    }

}

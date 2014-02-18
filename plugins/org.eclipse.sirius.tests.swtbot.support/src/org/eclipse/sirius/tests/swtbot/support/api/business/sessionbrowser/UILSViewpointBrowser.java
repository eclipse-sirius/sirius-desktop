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

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

import org.eclipse.sirius.tests.swtbot.support.api.business.AbstractUIRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.business.UIRepresentationUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Representation browser.
 * 
 * @author dlecan
 */
public class UILSRepresentationBrowser extends AbstractUIElementWithNextTreeItem {

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item for this element.
     */
    public UILSRepresentationBrowser(final SWTBotTreeItem treeItem) {
        super(treeItem);
    }

    /**
     * Select an instance of representation, <i>ie</i> a diagram or a table.
     * 
     * @param <R>
     *            Type of representation.
     * @param representationInstanceLabelName
     *            Representation instance label name.
     * @param representationType
     *            Representation type.
     * @return A representation.
     */
    public <R extends AbstractUIRepresentation<?>> R selectRepresentationInstance(final String representationInstanceLabelName, final Class<R> representationType) {
        return UIRepresentationUtils.buildRepresentation(getNextNode(representationInstanceLabelName), representationInstanceLabelName, representationType);
    }

    /**
     * Select an instance of diagram.
     * 
     * @param diagramInstanceLabelName
     *            Diagram instance label name.
     * @return A diagram.
     */
    public UIDiagramRepresentation selectDiagramInstance(final String diagramInstanceLabelName) {
        return selectRepresentationInstance(diagramInstanceLabelName, UIDiagramRepresentation.class);
    }

    /**
     * Select an instance of table.
     * 
     * @param tableInstanceLabelName
     *            Table instance label name.
     * @return A table.
     */
    public UITableRepresentation selectTableInstance(final String tableInstanceLabelName) {
        return selectRepresentationInstance(tableInstanceLabelName, UITableRepresentation.class);
    }

    /**
     * Select an instance of tree.
     * 
     * @param treeInstanceLabelName
     *            Tree instance label name.
     * @return A tree.
     */
    public UITreeRepresentation selectTreeInstance(final String treeInstanceLabelName) {
        return selectRepresentationInstance(treeInstanceLabelName, UITreeRepresentation.class);
    }

}

/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutExtender;

/**
 * This interface is the lowest common denominator a layout provider needs to
 * respect to be able to leverage the layout extensions.
 * 
 * @author cbrun
 * 
 */
public interface ExtendableLayoutProvider {
    /**
     * return true if the layout handle connectable list items.
     * 
     * @return true if the layout handle connectable list items.
     */
    boolean handleConnectableListItems();

    /**
     * should return the node metric.
     * 
     * @param node
     *            a node.
     * @return the node metric
     */
    Rectangle provideNodeMetrics(Node node);

    /**
     * return the layout extender used.
     * 
     * @return the layout extender used.
     */
    LayoutExtender getExtender();
}

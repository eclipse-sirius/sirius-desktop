/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * Common interface for all the elements of a sequence diagram which represent
 * an event associated to a (logical) time interval and thus a range of vertical
 * coordinates. This includes lifelines (considered as a special case of
 * executions), executions and messages.
 * 
 * @author mporhel
 */
public interface ISequenceNode extends ISequenceElement {
    /**
     * Convenience method to return the underlying GMF View as a Node.
     * 
     * @return the GMF Node representing this element.
     */
    Node getNotationNode();

    /**
     * .
     * 
     * @return .
     */
    Rectangle getBounds();
}

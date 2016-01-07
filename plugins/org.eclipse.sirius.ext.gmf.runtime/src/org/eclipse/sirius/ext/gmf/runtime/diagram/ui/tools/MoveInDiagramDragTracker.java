/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools;

import org.eclipse.gef.DragTracker;

/**
 * A specific {@link DragTracker} which declares move in diagram specific
 * states.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
// CHECKSTYLE:OFF
public interface MoveInDiagramDragTracker extends DragTracker {
    // CHECKSTYLE:ON
    /**
     * The state indicating that the middle button is pressed, but the user has
     * not moved past the scroll threshold. Many tools will do nothing during
     * this state but wait until {@link #STATE_SCROLL_DIAGRAM_IN_PROGRESS} is
     * entered.
     */
    int STATE_SCROLL_DIAGRAM = 64;

    /**
     * The state indicating that the scroll detection threshold has been passed,
     * and a scroll is in progress.
     */
    int STATE_SCROLL_DIAGRAM_IN_PROGRESS = 128;

}

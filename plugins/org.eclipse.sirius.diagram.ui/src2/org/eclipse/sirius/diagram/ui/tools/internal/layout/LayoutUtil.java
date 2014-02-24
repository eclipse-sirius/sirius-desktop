/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;

/**
 * Some utility methods for layout.
 * 
 * @author mchauvin
 */
public final class LayoutUtil {

    /**
     * Avoid instantiation.
     */
    private LayoutUtil() {

    }

    /**
     * Launch an arrange all on the diagram.
     * 
     * @param diagramEditPart
     *            diagramEditPart;
     */
    public static void arrange(final DiagramEditPart diagramEditPart) {
        final ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
        final List<Object> partsToArrange = new ArrayList<Object>(1);
        partsToArrange.add(diagramEditPart);
        arrangeRequest.setPartsToArrange(partsToArrange);
        diagramEditPart.performRequest(arrangeRequest);
    }

}

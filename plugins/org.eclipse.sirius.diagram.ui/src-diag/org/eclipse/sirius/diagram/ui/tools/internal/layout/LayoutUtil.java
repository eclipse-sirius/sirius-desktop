/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
     * The name of the system property to disable the new behavior added in Sirius 7.0 (false by default).
     * 
     * @deprecated
     */
    private static final String DISABLE_ARRANGE_AT_OPENING_CHANGES = "org.eclipse.sirius.diagram.ui.disableArrangeAtOpeningChanges"; //$NON-NLS-1$

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

    /**
     * In Sirius 7.0, some fixes have been done through bugzilla 577676 concerning arrange done at opening of a diagram.
     * Maybe some modelers rely on this "buggy behavior". This system property has been added to allow to "revert" the
     * changes and retrieve the previous behavior. It is a temporary method during one or two versions, waiting a
     * potential feedback.
     * 
     * @return true if the new behavior must be disabled, to retrieve previous one, or false to have the new behavior
     *         (the correct one).
     * @deprecated
     */
    public static boolean isArrangeAtOpeningChangesDisabled() {
        return Boolean.valueOf(System.getProperty(DISABLE_ARRANGE_AT_OPENING_CHANGES, "false")); //$NON-NLS-1$
    }
}

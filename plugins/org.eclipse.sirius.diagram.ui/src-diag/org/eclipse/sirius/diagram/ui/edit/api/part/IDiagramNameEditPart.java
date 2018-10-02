/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;

/**
 * An interface for edit part which can have a label.
 * 
 * @author ymortier
 */
public interface IDiagramNameEditPart extends IDiagramElementEditPart, ITextAwareEditPart {

    /**
     * Sets the label of the figure.
     * 
     * @param label
     *            the label.
     */
    void setLabel(IFigure label);

    /**
     * Sets the text of the element's tooltip.
     * 
     * @param text
     *            the text to show in the tooltip. If <code>null</code> or the
     *            empty string, the element's tooltip is disabled.
     * @since 0.9.0
     */
    void setTooltipText(String text);
}

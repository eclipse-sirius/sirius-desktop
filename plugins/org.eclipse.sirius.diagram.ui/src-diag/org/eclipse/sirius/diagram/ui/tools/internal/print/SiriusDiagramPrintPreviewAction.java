/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.print;

import org.eclipse.gmf.runtime.common.ui.action.actions.IPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.actions.PrintPreviewAction;

/**
 * This is this action for print preview of Sirius diagrams.
 * 
 * @author mPorhel
 * 
 */
public class SiriusDiagramPrintPreviewAction extends PrintPreviewAction {

    /**
     * Creates a new instance.
     * 
     * @param printActionHelper
     *            the helper class used to show the print settings dialog and
     *            perform the actual printing if the user were to print from
     *            within the print preview dialog.
     */
    public SiriusDiagramPrintPreviewAction(final IPrintActionHelper printActionHelper) {
        super(printActionHelper, new SiriusDiagramPrintPreviewHelper());
    }
}

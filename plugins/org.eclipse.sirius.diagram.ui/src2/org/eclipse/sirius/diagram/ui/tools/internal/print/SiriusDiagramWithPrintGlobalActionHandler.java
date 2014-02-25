/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.ui.printing.providers.DiagramWithPrintGlobalActionHandler;

/**
 * A specialized <code>DiagramWithPrintGlobalActionHandler</code> that supports
 * printing of viewpoint diagrams with
 * <code>SiriusEnhancedPrintActionHelper</code>.
 * 
 * @author mporhel
 */
public class SiriusDiagramWithPrintGlobalActionHandler extends DiagramWithPrintGlobalActionHandler {

    /**
     * {@inheritDoc}
     * 
     * @see DiagramWithPrintGlobalActionHandler;
     */
    @Override
    protected void doPrint(final IGlobalActionContext cntxt) {
        final IPrintActionHelper helper = new SiriusEnhancedPrintActionHelper();
        helper.doPrint(cntxt.getActivePart());
    }
}

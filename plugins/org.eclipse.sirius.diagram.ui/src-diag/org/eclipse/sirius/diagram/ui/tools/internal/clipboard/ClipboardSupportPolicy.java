/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.clipboard;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupportPolicy;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DiagramPackage;

/**
 * Policy to restrict the Sirius clipboard support to Sirius elements.
 * 
 * @author mporhel
 */
public class ClipboardSupportPolicy implements IClipboardSupportPolicy {

    @Override
    public boolean provides(IAdaptable adaptable) {
        if (adaptable != null) {
            View view = (View) adaptable.getAdapter(View.class);
            if (view != null) {
                Diagram diagram = view.getDiagram();
                return diagram != null && DiagramPackage.Literals.DDIAGRAM.isInstance(diagram.getElement());
            }
        }
        return false;
    }
}

/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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
            View view = adaptable.getAdapter(View.class);
            if (view != null) {
                Diagram diagram = view.getDiagram();
                return diagram != null && DiagramPackage.Literals.DDIAGRAM.isInstance(diagram.getElement());
            }
        }
        return false;
    }
}

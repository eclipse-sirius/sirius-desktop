/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupportFactory;

/**
 * Factory to create {@link ProxyClipboardSupport}s.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class ClipboardSupportFactory implements IClipboardSupportFactory {

    private final IClipboardSupport support = SiriusDefaultClipboardSupport.getInstance();

    /**
     * Constructor.
     */
    public ClipboardSupportFactory() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupportFactory#newClipboardSupport(org.eclipse.emf.ecore.EPackage)
     */
    public IClipboardSupport newClipboardSupport(final EPackage package1) {
        return support;
    }

}

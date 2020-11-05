/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * This class is used to wrap invalid {@link DRepresentationDescriptor}.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class RepresentationInvalidItemImpl extends RepresentationItemImpl {

    /**
     * Construct a new resource item wrapper.
     * 
     * @param rep
     *            the represented {@link DRepresentationDescriptor}.
     * @param parent
     *            Parent tree item
     */
    public RepresentationInvalidItemImpl(final DRepresentationDescriptor rep, final Object parent) {
        super(rep, parent);
    }
}

/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.properties;

import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * Interface to provide an {@link IPropertySheetPage} for a Sirius editor or
 * view.
 * 
 * @author pcdavid
 */
public interface ISiriusPropertySheetPageProvider {
    /**
     * Provides a {@link IPropertySheetPage} for the specified source and
     * contributorId. May return <code>null</code> if the given source is not
     * handled by this provider.
     * 
     * @param source
     *            the source editor or view.
     * @param contributorId
     *            the contributor id.
     * @return a IPropertySheetPage, or <code>null</code>
     */
    IPropertySheetPage getPropertySheetPage(Object source, String contributorId);
}

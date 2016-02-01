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
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage;
import org.eclipse.sirius.ui.tools.api.properties.ISiriusPropertySheetPageProvider;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * Provides an EEFTabbedPropertySheetPage for a given source part.
 * 
 * @author pcdavid
 */
public class EEFPropertySheetPageProvider implements ISiriusPropertySheetPageProvider {
    @Override
    public IPropertySheetPage getPropertySheetPage(Object source, String contributorId) {
        return new EEFTabbedPropertySheetPage(source, contributorId);
    }
}

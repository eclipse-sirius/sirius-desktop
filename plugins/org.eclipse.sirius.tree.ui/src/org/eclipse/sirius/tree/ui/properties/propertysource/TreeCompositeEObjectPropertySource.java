/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.properties.propertysource;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.tools.api.properties.AbstractEObjectPropertySource;
import org.eclipse.ui.IEditorPart;

public class TreeCompositeEObjectPropertySource extends AbstractEObjectPropertySource {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.properties.AbstractEObjectPropertySource#getItemProvidersAdapterFactory()
     */
    @Override
    protected AdapterFactory getItemProvidersAdapterFactory() {
        AdapterFactory adapterFactory = null;
        final IEditorPart part = EclipseUIUtil.getActiveEditor();
        if (part instanceof DTreeEditor) {
            adapterFactory = ((DTreeEditor) part).getAdapterFactory();
        } else {
            adapterFactory = TreeUIPlugin.getPlugin().getItemProvidersAdapterFactory();
        }
        return adapterFactory;
    }

}

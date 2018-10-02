/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.properties.propertysource;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.ui.tools.api.properties.AbstractEObjectPropertySource;
import org.eclipse.ui.IEditorPart;

public class TableCompositeEObjectPropertySource extends AbstractEObjectPropertySource {

    @Override
    protected AdapterFactory getItemProvidersAdapterFactory() {
        AdapterFactory adapterFactory = null;
        final IEditorPart part = EclipseUIUtil.getActiveEditor();
        if (part instanceof DTableEditor) {
            adapterFactory = ((DTableEditor) part).getAdapterFactory();
        } else {
            adapterFactory = TableUIPlugin.getPlugin().getItemProvidersAdapterFactory();
        }
        return adapterFactory;
    }

}

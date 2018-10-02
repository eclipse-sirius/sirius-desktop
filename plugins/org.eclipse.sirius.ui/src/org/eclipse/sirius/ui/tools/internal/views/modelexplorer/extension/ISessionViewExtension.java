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
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension;

import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * This extension help to add custom elements to the model content. This
 * interface is not API.
 * 
 * @author mchauvin
 */
public interface ISessionViewExtension {
    /**
     * Get the content provider to use for the extension. Should not return
     * <code>null</code>; The children your content provider returns should
     * implement [@link
     * org.eclipse.sirius.ui.tools.api.views.sessionview.item.ItemDecorator]
     * to be correctly displayed.
     * 
     * @return content provider to use
     */
    ITreeContentProvider getContentProvider();

    /**
     * Get the context menu action provider to use for the extension. Should not
     * return <code>null</code>;
     * 
     * @return context menu actions provider to use
     */
    IContextMenuActionProvider getContextMenuActionProvider();

}

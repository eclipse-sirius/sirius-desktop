/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;

/**
 * A specific {@link NavigatorDecoratingLabelProvider} to manage tooltip.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class TooltipAwareNavigatorDecoratingLabelProvider extends NavigatorDecoratingLabelProvider {

    /**
     * Default constructor.
     * 
     * @param commonLabelProvider
     *            a common {@link ILabelProvider}
     */
    public TooltipAwareNavigatorDecoratingLabelProvider(ILabelProvider commonLabelProvider) {
        super(commonLabelProvider);
    }

    @Override
    public String getToolTipText(Object element) {
        String tooltip = null;
        IToolTipProvider tooltipProvider = (IToolTipProvider) Platform.getAdapterManager().getAdapter(element, IToolTipProvider.class);
        if (tooltipProvider != null) {
            tooltip = tooltipProvider.getToolTipText(element);
        }
        return tooltip;
    }

}

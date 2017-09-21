/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
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
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;

/**
 * A specific {@link NavigatorDecoratingLabelProvider} to manage tooltip.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class TooltipAwareNavigatorDecoratingLabelProvider extends NavigatorDecoratingLabelProvider {

    private ILabelProvider commonLabelProvider;

    /**
     * Default constructor.
     * 
     * @param commonLabelProvider
     *            a common {@link ILabelProvider}
     */
    public TooltipAwareNavigatorDecoratingLabelProvider(ILabelProvider commonLabelProvider) {
        super(commonLabelProvider);
        this.commonLabelProvider = commonLabelProvider;
    }

    @Override
    public String getToolTipText(Object element) {
        String tooltip = null;
        IToolTipProvider tooltipProvider = Platform.getAdapterManager().getAdapter(element, IToolTipProvider.class);
        if (tooltipProvider != null) {
            tooltip = tooltipProvider.getToolTipText(element);
        } else if (commonLabelProvider instanceof IToolTipProvider) {
            tooltip = ((IToolTipProvider) commonLabelProvider).getToolTipText(element);
        }
        return tooltip;
    }

}

/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed;

import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;

/**
 * A {@link LabelProviderProviderDescriptor} for standalone use.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StandaloneLabelProviderProviderDescriptor extends AbstractLabelProviderProviderDescriptor implements LabelProviderProviderDescriptor {

    /**
     * Default constructor.
     * 
     * @param id
     *            id of this contribution
     * @param labelProviderProvider
     *            {@link ILabelProviderProvider} of this contribution
     * 
     */
    public StandaloneLabelProviderProviderDescriptor(String id, ILabelProviderProvider labelProviderProvider) {
        super();
        this.id = id;
        this.labelProviderProvider = labelProviderProvider;
    }

}

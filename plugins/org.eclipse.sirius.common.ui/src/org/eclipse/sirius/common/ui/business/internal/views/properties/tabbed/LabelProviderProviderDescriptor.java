/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed;

import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;

/**
 * Descriptor for {@link LabelProviderProvider} contribution.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface LabelProviderProviderDescriptor {

    /**
     * Id of the propertyContributorLabelProviderDelegate extension point's tag
     * "id" attribute.
     */
    String LABEL_PROVIDER_PROVIDER_ID_ATTRIBUTE = "id"; //$NON-NLS-1$

    /**
     * Name of the propertyContributorLabelProviderDelegate extension point's
     * tag "class" attribute.
     */
    String LABEL_PROVIDER_PROVIDER_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * The unique identifier of the extension {@link ILabelProviderProvider}
     * extension.
     * 
     * @return the unique identifier of the extension
     *         {@link ILabelProviderProvider} extension
     */
    String getId();

    /**
     * The concrete implementation (i.e. ILabelProviderProvider) of the
     * extension.
     * 
     * @return the concrete implementation (i.e. ILabelProviderProvider) of the
     *         extension
     */
    ILabelProviderProvider getLabelProviderProvider();

}

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
 * A abstract {@link LabelProviderProviderDescriptor} with id and {@link class}
 * attribute.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractLabelProviderProviderDescriptor implements LabelProviderProviderDescriptor {

    /** Id of this contribution. */
    protected String id;

    /** {@link ILabelProviderProvider} of this contribution. */
    protected ILabelProviderProvider labelProviderProvider;

    /** The override attribute value . */
    protected String overrideValue;

    public String getId() {
        return id;
    }

    public ILabelProviderProvider getLabelProviderProvider() {
        return labelProviderProvider;
    }

}

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
package org.eclipse.sirius.ui.business.internal.session.factory;

import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;


/**
 * A abstract {@link UISessionFactoryDescriptor} with id and
 * {@link UISessionFactoryDescriptor} attribute.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractUISessionFactoryDescriptor implements UISessionFactoryDescriptor {

    /** Id of this contribution. */
    protected String id;

    /** {@link UISessionFactory} of this contribution. */
    protected UISessionFactory uiSessionFactory;

    /** The override attribute value . */
    protected String overrideValue;

    public String getId() {
        return id;
    }

    public UISessionFactory getUISessionFactory() {
        return uiSessionFactory;
    }

    public String getOverrideValue() {
        return overrideValue;
    }

}

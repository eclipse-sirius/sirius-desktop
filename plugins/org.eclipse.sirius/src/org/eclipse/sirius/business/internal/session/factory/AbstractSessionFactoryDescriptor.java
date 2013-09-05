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
package org.eclipse.sirius.business.internal.session.factory;

import org.eclipse.sirius.business.api.session.factory.SessionFactory;

/**
 * A abstract {@link SessionFactoryDescriptor} with id and
 * {@link SessionFactoryDescriptor} attribute.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractSessionFactoryDescriptor implements SessionFactoryDescriptor {

    /** Id of this contribution. */
    protected String id;

    /** {@link SessionFactory} of this contribution. */
    protected SessionFactory sessionFactory;

    /** The override attribute value . */
    protected String overrideValue;

    public String getId() {
        return id;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public String getOverrideValue() {
        return overrideValue;
    }

}

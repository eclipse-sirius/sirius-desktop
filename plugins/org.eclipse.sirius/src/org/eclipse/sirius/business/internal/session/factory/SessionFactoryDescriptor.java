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
 * Descriptor for {@link SessionFactory} contribution.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface SessionFactoryDescriptor {

    /**
     * Id of the sessionFactory extension point's tag "id" attribute.
     */
    String SESSION_FACTORY_ID_ATTRIBUTE = "id"; //$NON-NLS-1$

    /**
     * Name of the sessionFactory extension point's tag "class" attribute.
     */
    String SESSION_FACTORY_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Name of the sessionFactory extension point's tag "override" attribute.
     */
    String SESSION_FACTORY_OVERRIDE_ATTRIBUTE = "override"; //$NON-NLS-1$

    /**
     * The unique identifier of the extension {@link SessionFactory} extension.
     * 
     * @return the unique identifier of the extension {@link SessionFactory}
     *         extension
     */
    String getId();

    /**
     * The concrete implementation (i.e. SessionFactory) of the extension.
     * 
     * @return the concrete implementation (i.e. SessionFactory) of the
     *         extension
     */
    SessionFactory getSessionFactory();

    /**
     * Get the override attribute value of this contribution.
     * 
     * @return the override attribute value of this contribution
     */
    String getOverrideValue();
}

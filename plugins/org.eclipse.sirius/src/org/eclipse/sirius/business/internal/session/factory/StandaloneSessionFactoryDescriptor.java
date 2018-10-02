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
package org.eclipse.sirius.business.internal.session.factory;

import org.eclipse.sirius.business.api.session.factory.SessionFactory;

/**
 * A {@link SessionFactoryDescriptor} for standalone use.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StandaloneSessionFactoryDescriptor extends AbstractSessionFactoryDescriptor implements SessionFactoryDescriptor {

    /**
     * Default constructor.
     * 
     * @param id
     *            id of this contribution
     * @param sessionFactory
     *            {@link SessionFactory} of this contribution
     * @param overrideValue
     *            override attribute value
     * 
     */
    public StandaloneSessionFactoryDescriptor(String id, SessionFactory sessionFactory, String overrideValue) {
        super();
        this.id = id;
        this.sessionFactory = sessionFactory;
        this.overrideValue = overrideValue;
    }
}

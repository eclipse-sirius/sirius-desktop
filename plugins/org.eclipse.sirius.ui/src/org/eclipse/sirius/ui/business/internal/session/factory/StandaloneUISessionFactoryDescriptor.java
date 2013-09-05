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
 * A {@link UISessionFactoryDescriptor} for standalone use.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StandaloneUISessionFactoryDescriptor extends AbstractUISessionFactoryDescriptor implements UISessionFactoryDescriptor {

    /**
     * Default constructor.
     * 
     * @param id
     *            id of this contribution
     * @param uiSessionFactory
     *            {@link UISessionFactory} of this contribution
     * @param overrideValue
     *            override attribute value
     * 
     */
    public StandaloneUISessionFactoryDescriptor(String id, UISessionFactory uiSessionFactory, String overrideValue) {
        super();
        this.id = id;
        this.uiSessionFactory = uiSessionFactory;
        this.overrideValue = overrideValue;
    }
}

/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.eef.core.api.IEEFDomainClassTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;

/**
 * The domain class tester based on the model accessor of the Sirius session.
 * 
 * @author sbegaudeau
 */
public class SiriusDomainClassTester implements IEEFDomainClassTester {

    /**
     * The Sirius session.
     */
    private Session session;

    /**
     * The constructor.
     * 
     * @param session
     *            The Sirius session
     */
    public SiriusDomainClassTester(Session session) {
        this.session = session;
    }

    @Override
    public boolean eInstanceOf(EObject eObject, String domainClass) {
        /*
         * The EEF runtime interprets a blank domainClass as a wildcard, and expects eInstance(anything, null) to return
         * 'true', but the Sirius ModelAcessor returns 'false' when no domainClass is specified.
         */
        return Util.isBlank(domainClass) || this.session.getModelAccessor().eInstanceOf(eObject, domainClass);
    }

}

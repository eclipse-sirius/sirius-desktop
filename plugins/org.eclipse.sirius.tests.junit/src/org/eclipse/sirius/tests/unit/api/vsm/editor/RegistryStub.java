/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.vsm.editor;

import java.util.HashMap;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;

/**
 * A Stub of {@link Registry} for standalone tests.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RegistryStub extends HashMap<String, Object> implements Registry {

    private static final long serialVersionUID = -6543542780952654086L;

    public EPackage getEPackage(String nsURI) {
        return (EPackage) get(nsURI);
    }

    public EFactory getEFactory(String nsURI) {
        EFactory eFactory = null;
        EPackage ePackage = getEPackage(nsURI);
        if (ePackage != null) {
            eFactory = ePackage.getEFactoryInstance();
        }
        return eFactory;
    }
}

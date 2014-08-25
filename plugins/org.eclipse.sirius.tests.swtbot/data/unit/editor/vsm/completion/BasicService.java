/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.test.design;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class BasicService {
    /**
     * A very basic service method, just to test A3 completion.
     * 
     * @param ctx
     *            the service's target.
     * @return a fixed string.
     */
    public String sampleService(EObject ctx) {
        return "sampleService";
    }

    /**
     * A very basic service method, just to test A3 completion.
     * 
     * @param ctx
     *            the service's target.
     * @return a fixed string.
     */
    public String sampleServiceOnEClass(EClass ctx) {
        return "sampleService";
    }

    /**
     * A very basic service method, just to test A3 completion.
     * 
     * @param ctx
     *            the service's target.
     * @return a fixed string.
     */
    public String sampleServiceOnEPackage(EPackage ctx) {
        return "sampleService";
    }
}

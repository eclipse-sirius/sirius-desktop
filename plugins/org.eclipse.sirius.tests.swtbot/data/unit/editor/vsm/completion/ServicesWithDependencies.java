/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.test.design;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.sample.interactions.CallMessage;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;

/**
 * Class containing services referencing the {@link Interaction} metamodel. 
 */
public class ServicesWithDependencies {

    /**
     * Service using a class from interactions model that is referenced by this
     * plugin by a bundle dependency. The service is called from the "service:"
     * keyword.
     * 
     * @param any
     *            eObject source of the service call
     * @return a string
     */
    public CallMessage testInteractionsService(EObject any) {
        CallMessage createCallMessage = InteractionsFactory.eINSTANCE.createCallMessage();
        createCallMessage.setName("testInteractionsWithServiceServiceCallOk");
        return createCallMessage;
    }
}

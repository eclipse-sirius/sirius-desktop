/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import org.eclipse.emf.ecore.EObject;

/**
 * This class is responsible to hook the evaluation of a semantic partition if
 * it is able to retrieve the expected result sooner.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class SemanticPartitionInvalidator {

    public Maybe<EvaluatedSemanticPartition> hasFastResult(EObject sourceElement, SemanticPartition semanticPartition) {
        return MaybeFactory.newNone();
    }

}

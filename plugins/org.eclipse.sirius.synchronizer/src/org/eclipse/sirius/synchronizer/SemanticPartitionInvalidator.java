/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * This class is responsible to hook the evaluation of a semantic partition if
 * it is able to retrieve the expected result sooner.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class SemanticPartitionInvalidator {

    public Option<EvaluatedSemanticPartition> hasFastResult(EObject sourceElement, SemanticPartition semanticPartition, CreatedOutput parentElement) {
        return Options.newNone();
    }

}

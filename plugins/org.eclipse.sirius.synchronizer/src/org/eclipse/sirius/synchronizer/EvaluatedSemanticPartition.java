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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

/**
 * A semantic partition which have been evaluated on a given context.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface EvaluatedSemanticPartition {

    /**
     * Indicates if the given semantic element is part of the SemanticPartition.
     * 
     * @param sem
     *            the semantic element
     * @return true if the given semantic element is part of the
     *         SemanticPartition, false otherwise
     */
    boolean isElementOf(EObject sem);

    /**
     * Gets the elements of the SemanticPartition.
     * 
     * @return the elements of the SemanticPartition
     */
    Iterator<EObject> elements();

}

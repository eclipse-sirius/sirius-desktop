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

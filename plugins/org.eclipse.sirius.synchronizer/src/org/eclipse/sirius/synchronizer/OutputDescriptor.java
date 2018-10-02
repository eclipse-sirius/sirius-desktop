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

/**
 * Contract for a class representing an element which "might" get created in the
 * output model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface OutputDescriptor {

    int getIndex();

    EObject getSourceElement();

    Mapping getMapping();
}

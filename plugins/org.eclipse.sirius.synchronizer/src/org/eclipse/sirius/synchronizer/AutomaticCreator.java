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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

/**
 * Contract to respect when a targeted element might contain children.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface AutomaticCreator {

    Collection<? extends OutputDescriptor> computeDescriptors(CreatedOutput container, Iterator<EObject> elements);

}

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

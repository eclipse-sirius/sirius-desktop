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

/**
 * Contract to respect when a mapping have the ability to create, reorder or
 * delete childs.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public interface ChildCreationSupport {

    public void reorderChilds(Iterable<CreatedOutput> iterable);

    void deleteChild(CreatedOutput outDesc);

    CreatedOutput createChild(OutputDescriptor outDesc);

}

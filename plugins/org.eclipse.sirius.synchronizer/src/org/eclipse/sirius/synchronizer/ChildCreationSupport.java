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

/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.command.listener;

import java.util.Collection;

/**
 * Interface to implement if you want to provide a trigger based on semantic
 * modifications in {@link IChangeListener}.
 * 
 * @author mchauvin
 * @since 2.6
 */
public interface TriggerOperation {

    /**
     * Run a trigger operation based on created, modified and deleted elements.
     * 
     * @param createdElements
     *            the created elements
     * @param modifiedElements
     *            the modified elements
     * @param deletedElements
     *            the deleted elements
     */
    void run(Collection<Object> createdElements, Collection<Object> modifiedElements, Collection<Object> deletedElements);

}

/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.task;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * An interface to implement for task which create semantic elements.
 * 
 * @author mchauvin
 */
public interface ICreationTask extends IModificationTask {

    /**
     * Returns all the semantic objects created by this task.
     * 
     * @return the semantic objects created by this task.
     */
    Collection<EObject> getCreatedElements();

    /**
     * Returns all the representation elements created by this task.
     * 
     * @return the representation elements created by this task.
     */
    Collection<DRepresentationElement> getCreatedRepresentationElements();
}

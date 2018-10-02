/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

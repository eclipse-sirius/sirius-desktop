/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.ui;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for an action that is contributed into an contextual menu or in a
 * tool.
 * 
 * @author mporhel
 */
public interface IExternalJavaAction {

    /**
     * Executes the action on the selected objects.
     * 
     * @param selections
     *            a collection of
     *            {@link org.eclipse.sirius.viewpoint.DSemanticDecorator
     *            DSemanticDecorator} or of
     *            {@link org.eclipse.emf.ecore.EObject semantic elements} on
     *            which the action should be executed
     * @param parameters
     *            map of parameters (String-->Object)
     */
    void execute(Collection<? extends EObject> selections, Map<String, Object> parameters);

    /**
     * Checks if this action can be executed on the selected objects.
     * 
     * @param selections
     *            a collection of
     *            {@link org.eclipse.sirius.viewpoint.DSemanticDecorator
     *            DSemanticDecorator} or of
     *            {@link org.eclipse.emf.ecore.EObject semantic elements} on
     *            which the action should be executed
     * 
     * @return true if the action can be executed on the selected elements.
     */
    boolean canExecute(Collection<? extends EObject> selections);

}

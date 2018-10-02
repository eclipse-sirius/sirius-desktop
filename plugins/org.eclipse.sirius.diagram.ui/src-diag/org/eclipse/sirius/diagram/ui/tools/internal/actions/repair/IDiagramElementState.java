/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;

/**
 * Contract for all diagram element states.
 * 
 * @author dlecan
 */
public interface IDiagramElementState<D extends DDiagramElement> {

    /**
     * Cleans references that have been stored within this instance.
     */
    void dispose();

    /**
     * Sets the state of the given element to the values saved within this
     * instance.
     * 
     * @param target
     *            Target semantic element of the saved element.
     * @param mapping
     *            Mapping of the saved element.
     * @param element
     *            Element which state is to be saved
     */
    void storeElementState(EObject target, DiagramElementMapping mapping, D element);

    /**
     * Sets the state of the given element to the values saved within this
     * instance.
     * 
     * @param element
     *            Element which state is to be restored.
     */
    void restoreElementState(D element);

    /**
     * Returns the identifier of this state.
     * 
     * @return the identifier of this state.
     */
    Identifier getIdentifier();

}

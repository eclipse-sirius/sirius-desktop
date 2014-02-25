/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.part;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

/**
 * An interface which specify how register edit parts for semantic element.
 * 
 * @author mchauvin
 */
public interface IDiagramDialectGraphicalViewer {

    /**
     * Registers an editpart for an element in the element/editpart registry.
     * 
     * @param element
     *            the semantic element
     * @param ep
     *            the edit part to register for this element
     */
    void registerEditPartForSemanticElement(EObject element, EditPart ep);

    /**
     * Unregisters an editpart for a element in the element/editpart registry.
     * 
     * @param element
     *            the semantic element
     * @param ep
     *            the edit part to register for this element
     */
    void unregisterEditPartForSemanticElement(EObject element, EditPart ep);

    /**
     * Finds all editparts of a specific class type on the diagram that have
     * been registered for the given element.
     * 
     * @param <T>
     *            a class which extends {@link EditPart}
     * @param element
     *            the semantic element
     * @param editPartClass
     *            the class of the editparts to be returned
     * @return a List of editparts, if none exist an empty list is returned
     */
    <T extends EditPart> List<T> findEditPartsForElement(EObject element, Class<T> editPartClass);

    /**
     * Unregisters an editpart from the element/editpart registry when the
     * relation to the semantic element no longer exists.
     * 
     * @param ep
     *            the edit part to unregister
     */
    void unregisterEditPart(EditPart ep);
}

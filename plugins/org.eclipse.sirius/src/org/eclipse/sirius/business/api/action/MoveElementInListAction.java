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
package org.eclipse.sirius.business.api.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Preconditions;

/**
 * An external Java action which can move an element to a different position
 * inside a many-valued EReference.
 * <p>
 * Action parameters:
 * <ul>
 * <li><code>referenceOwner</code>: the owner of the EReference to edit.</li>
 * <li><code>referenceName</code>: the name of the EReference of the owner
 * object to edit. This must be a many-valued, changeable reference.</li>
 * <li><code>element</code>: the element to move inside the list. It is an error
 * if the specified element is not in the list.
 * <li>
 * <li><code>predecessor</code>: the element of the list right after which
 * <code>element</code> must be moved, i.e. after the operation,
 * <code>predecessor</code> will be the direct predecessor of
 * <code>element</code> in the list. May be <code>null</code> to indicate that
 * <code>element</code> should be moved at the front of the list. If not
 * <code>null</code>, it is an error if <code>predecessor</code> is not in the
 * list.</li>
 * </ul>
 * An {@link IllegalArgumentException} is thrown if any of the parameters is
 * missing, not of the right type or inconsistent.
 * 
 * @author pcdavid
 */
public class MoveElementInListAction extends AbstractExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return selections.size() == 1;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        EObject element = getParameter(parameters, "element", EObject.class); //$NON-NLS-1$
        EObject owner = getParameter(parameters, "referenceOwner", EObject.class); //$NON-NLS-1$
        String refName = getParameter(parameters, "referenceName", String.class); //$NON-NLS-1$
        EObject pred = getOptionalParameter(parameters, "predecessor", EObject.class); //$NON-NLS-1$
        if (pred != null) {
            Preconditions.checkArgument(element != pred, "'element' and 'predecessor' must be different.");
        }

        EStructuralFeature feature = owner.eClass().getEStructuralFeature(refName);
        Preconditions.checkArgument(feature != null, "Owner element type " + owner.eClass().getName() + " does not have a feature named " + refName);
        String qName = feature.getEContainingClass().getName() + "." + feature.getName();
        Preconditions.checkArgument(feature instanceof EReference, "Feature " + qName + " is not an EReference");
        Preconditions.checkArgument(feature.isMany(), "Reference " + qName + " is not multi-valued");
        Preconditions.checkArgument(feature.isChangeable(), "Reference " + qName + " is not changeable");

        @SuppressWarnings("unchecked")
        EList<EObject> list = (EList<EObject>) owner.eGet(feature);
        Preconditions.checkArgument(pred == null || list.contains(pred), "'predecessor' parameter is not a member of the designated list.");
        Preconditions.checkArgument(list.contains(element), "'element' parameter is not a member of the designated list.");

        moveElementAfter(element, pred, list);
    }

    /**
     * Moves an element inside a list to another position, relative to a second
     * element of the list.
     * 
     * @param element
     *            the element to move. Must be present in the list.
     * @param pred
     *            the element which should become the new predecessor of
     *            <code>element</code> in the list. May be <code>null</code> to
     *            indicate that <code>element</code> should be moved at the
     *            front.
     * @param list
     *            the list of elements to alter.
     */
    public void moveElementAfter(EObject element, EObject pred, EList<EObject> list) {
        if (pred == null) {
            list.move(0, element);
        } else {
            int thisIndex = list.indexOf(element);
            int predIndex = list.indexOf(pred);
            if (thisIndex > predIndex) {
                list.move(predIndex + 1, element);
            } else {
                list.move(predIndex, element);
            }
        }
    }

}

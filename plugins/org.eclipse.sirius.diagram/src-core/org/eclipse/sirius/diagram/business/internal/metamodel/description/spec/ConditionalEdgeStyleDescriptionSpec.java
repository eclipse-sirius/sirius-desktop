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
package org.eclipse.sirius.diagram.business.internal.metamodel.description.spec;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.ConditionalStyleSpecOperations;
import org.eclipse.sirius.diagram.description.impl.ConditionalEdgeStyleDescriptionImpl;

/**
 * Implementation of ConditionalEdgeStyleDescription.
 * 
 * @author cbrun
 */
public class ConditionalEdgeStyleDescriptionSpec extends ConditionalEdgeStyleDescriptionImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.ConditionalStyleDescriptionImpl#checkPredicate(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean checkPredicate(final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        return ConditionalStyleSpecOperations.checkPredicate(this, modelElement, viewVariable, containerVariable);
    }

}

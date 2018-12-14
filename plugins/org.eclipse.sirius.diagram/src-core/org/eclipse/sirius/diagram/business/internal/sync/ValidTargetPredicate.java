/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.sync;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicate;

/**
 * A predicate that validate that if the EdgeTarget of a DSemanticDecorator, its
 * target must not be null and must be in a resource.
 * 
 * @author lredor
 */
public class ValidTargetPredicate implements Predicate<EdgeTarget> {

    /**
     * Applies this predicate to the given object.
     * 
     * @param input
     *            the input that the predicate should act on
     * @return the value of this predicate when applied to the input {@code t}
     */
    public boolean apply(EdgeTarget input) {
        if (input instanceof DSemanticDecorator) {
            // The target can be null or without resource in case of
            // modification outside the editor and manual refresh.
            EObject target = ((DSemanticDecorator) input).getTarget();
            return target != null && target.eResource() != null;
        }
        return true;
    }
}

/*******************************************************************************
 * Copyright (c) 2014, 2021 THALES GLOBAL SERVICES. All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which accompanies this distribution, and is
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.query.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A Query on {@link DSemanticDecorator}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DSemanticDecoratorQuery {

    private DSemanticDecorator dSemanticDecorator;

    /**
     * Default constructor.
     * 
     * @param dSemanticDecorator
     *            the {@link DSemanticDecorator} to query
     */
    public DSemanticDecoratorQuery(DSemanticDecorator dSemanticDecorator) {
        this.dSemanticDecorator = dSemanticDecorator;
    }

    /**
     * Tell if the target of specified view is detached.
     * 
     * @return true if the target of specified view is detached, false otherwise
     */
    public boolean hasDetachedTarget() {
        EObject target = dSemanticDecorator.getTarget();
        return target == null || target.eResource() == null;
    }

}

/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.contribution;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.DirectEObjectReference;
import org.eclipse.sirius.ext.emf.AllContents;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class ReuseHelper {

    /**
     * Tests whether an object contains any contribution which use elements from
     * another as source and contribute to the target (or any of its children).
     * 
     * @param target
     *            the reuse target.
     * @param potentialSource
     *            the potential source of reuse.
     * @return <code>true</code> if the target contains any contribution which
     *         get values from the source (or any of its descendant) and put
     *         them in the target (or any of its descendants).
     */
    public boolean reuses(final EObject target, final EObject potentialSource) {
        return Iterables.any(Iterables.filter(AllContents.of(target, true), Contribution.class), new Predicate<Contribution>() {
            public boolean apply(Contribution input) {
                if (input.getSource() instanceof DirectEObjectReference && input.getTarget() instanceof DirectEObjectReference) {
                    EObject sourceValue = ((DirectEObjectReference) input.getSource()).getValue();
                    EObject targetValue = ((DirectEObjectReference) input.getTarget()).getValue();
                    boolean fromSource = sourceValue == potentialSource || EcoreUtil.isAncestor(potentialSource, sourceValue);
                    boolean toTarget = targetValue == target || EcoreUtil.isAncestor(target, targetValue);
                    return fromSource && toTarget;
                } else {
                    return false;
                }
            }
        });
    }
}

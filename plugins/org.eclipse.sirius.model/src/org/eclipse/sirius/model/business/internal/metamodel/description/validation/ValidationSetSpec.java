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
package org.eclipse.sirius.model.business.internal.metamodel.description.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl;

/**
 * Implementation of
 * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet}.
 * 
 * @author ymortier, mchauvin, cbrun.
 */
public class ValidationSetSpec extends ValidationSetImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl#getAllRules()
     */
    @Override
    public EList<ValidationRule> getAllRules() {
        final Collection<ValidationRule> result = new ArrayList<ValidationRule>();
        result.addAll(getOwnedRules());
        result.addAll(getReusedRules());
        return new EcoreEList.UnmodifiableEList<ValidationRule>(eInternalContainer(), ValidationPackage.eINSTANCE.getValidationSet_AllRules(), result.size(), result.toArray());
    }

}

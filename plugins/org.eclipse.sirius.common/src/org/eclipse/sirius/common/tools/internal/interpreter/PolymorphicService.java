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
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * A service which corresponds to more than one Java method. Which of the
 * methods will actually be invoked will depend on the target object.
 * 
 * @author pcdavid
 */
class PolymorphicService implements IService {
    private final String name;

    private final Set<MonomorphicService> implementers = Sets.newLinkedHashSet();

    public PolymorphicService(String name) {
        this.name = Preconditions.checkNotNull(name);
    }

    public String getName() {
        return name;
    }

    public void addImplementer(MonomorphicService svc) {
        Preconditions.checkNotNull(svc);
        Preconditions.checkArgument(name.equals(svc.getName()));
        implementers.add(svc);
    }

    public boolean appliesTo(Object target) {
        return Iterables.any(implementers, getCompatibilityChecker(target));
    }

    public Object call(Object target) throws EvaluationException {
        List<MonomorphicService> candidates = Lists.newArrayList(Iterables.filter(implementers, getCompatibilityChecker(target)));
        if (!candidates.isEmpty()) {
            return candidates.get(0).call(target);
        } else {
            throw new EvaluationException("No compatible implementation of service " + getName() + " found for " + target);
        }
    }

    private Predicate<MonomorphicService> getCompatibilityChecker(final Object target) {
        Predicate<MonomorphicService> isCompatible = new Predicate<MonomorphicService>() {
            public boolean apply(MonomorphicService svc) {
                return svc.appliesTo(target);
            }
        };
        return isCompatible;
    }

    @Override
    public String toString() {
        return "Polymorphic service " + getName() + " (" + implementers.size() + " implementations).";
    }
}

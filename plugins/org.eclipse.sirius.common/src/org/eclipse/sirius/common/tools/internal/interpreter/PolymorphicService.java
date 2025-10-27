/*******************************************************************************
 * Copyright (c) 2013, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A service which corresponds to more than one Java method. Which of the methods will actually be invoked will depend
 * on the target object.
 * 
 * @author pcdavid
 */
class PolymorphicService implements IPolymorphicService {
    private final String name;

    private final Set<IMonomorphicService> implementers = new LinkedHashSet<>();

    PolymorphicService(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addImplementer(MonomorphicService svc) {
        Objects.requireNonNull(svc);
        Preconditions.checkArgument(name.equals(svc.getName()));
        implementers.add(svc);
    }

    @Override
    public boolean appliesTo(Object[] target) {
        return Iterables.any(implementers, getCompatibilityChecker(target));
    }

    @Override
    public Object call(Object[] target) throws EvaluationException {
        List<IMonomorphicService> candidates = Lists.newArrayList(Iterables.filter(implementers, getCompatibilityChecker(target)));
        if (!candidates.isEmpty()) {
            return candidates.get(0).call(target);
        } else {
            throw new EvaluationException(MessageFormat.format(Messages.PolymorphicService_noCompatibleImplem, getName(), String.valueOf(target)));
        }
    }

    private Predicate<IMonomorphicService> getCompatibilityChecker(final Object[] target) {
        Predicate<IMonomorphicService> isCompatible = new Predicate<IMonomorphicService>() {
            @Override
            public boolean apply(IMonomorphicService svc) {
                return svc.appliesTo(target);
            }
        };
        return isCompatible;
    }

    @Override
    public String toString() {
        return MessageFormat.format(Messages.PolymorphicService_toString, getName(), implementers.size());
    }

    @Override
    public Set<IMonomorphicService> getImplementers() {
        return implementers;
    }

    @Override
    public Collection<Method> getImplementations() {
        Collection<Method> result = new ArrayList<Method>();
        for (IMonomorphicService svc : this.implementers) {
            result.addAll(svc.getImplementations());
        }
        return result;
    }
}

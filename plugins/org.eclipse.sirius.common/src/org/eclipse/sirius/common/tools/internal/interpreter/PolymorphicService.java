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

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.common.tools.Messages;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A service which corresponds to more than one Java method. Which of the
 * methods will actually be invoked will depend on the target object.
 * 
 * @author pcdavid
 */
class PolymorphicService implements IPolymorphicService {
    private final String name;

    private final Set<IMonomorphicService> implementers = Sets.newLinkedHashSet();

    PolymorphicService(String name) {
        this.name = Preconditions.checkNotNull(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addImplementer(MonomorphicService svc) {
        Preconditions.checkNotNull(svc);
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
}

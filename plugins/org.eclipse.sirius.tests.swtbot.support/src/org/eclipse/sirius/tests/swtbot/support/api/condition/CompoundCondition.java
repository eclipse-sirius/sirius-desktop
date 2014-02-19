/**
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import java.util.Collection;
import java.util.Set;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Sets;

/**
 * An aggregation of multiple {@link ICondition}.
 * 
 * The test method of an empty CompoundCondition will always return true.
 * 
 * @author mporhel
 */
public class CompoundCondition extends DefaultCondition {

    private final Collection<ICondition> conditions = Sets.newLinkedHashSet();

    private final Set<ICondition> failures = Sets.newHashSet();

    /**
     * Constructor.
     * 
     * @param conditions
     *            Several conditions to test.
     */
    public CompoundCondition(Collection<ICondition> conditions) {
        this.conditions.addAll(conditions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        failures.clear();

        // To get a significant failure message, each sub condition has to be
        // tested.
        for (ICondition condition : conditions) {
            try {
                if (!condition.test()) {
                    failures.add(condition);
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                failures.add(condition);
            }
        }
        return failures.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        StringBuilder sb = new StringBuilder();
        if (failures.size() == 1) {
            sb.append("The following condition has not been met: ");
            sb.append(failures.iterator().next().getFailureMessage());
        } else {
            sb.append("Several conditions have not been met:\n");
            for (ICondition condition : failures) {
                sb.append(".").append(condition.getFailureMessage()).append("\n");
            }
        }
        return sb.toString();
    }
}

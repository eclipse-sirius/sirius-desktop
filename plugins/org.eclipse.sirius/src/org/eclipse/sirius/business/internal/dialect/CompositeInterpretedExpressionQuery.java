/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.dialect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * An {@link IInterpretedExpressionQuery} used to aggregate the results of multiple queries.
 * 
 * @since 7.1.0
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class CompositeInterpretedExpressionQuery implements IInterpretedExpressionQuery {

    private final List<IInterpretedExpressionQuery> children = new ArrayList<>();

    /**
     * Add an IInterpretedExpressionQuery to the list.
     * 
     * @param query
     *            the query to add
     * @return <code>true</code> if the query as been added
     */
    public boolean add(IInterpretedExpressionQuery query) {
        return children.add(query);
    }

    /**
     * Returns <code>true</code> if this list contains no elements.
     * 
     * @return <code>true</code> if this list contains no elements
     */
    public boolean isEmpty() {
        return children.isEmpty();
    }

    /**
     * To avoid regressions, it returns the only existing query if any. Otherwise, it returns the composite.
     * 
     * @return the unwrapped {@link IInterpretedExpressionQuery}
     */
    public IInterpretedExpressionQuery unwrap() {
        if (children.size() == 1) {
            return children.get(0);
        } else {
            return this;
        }
    }

    @Override
    public Option<Collection<String>> getTargetDomainClasses() {
        Collection<String> targetTypes = new LinkedHashSet<>();
        for (IInterpretedExpressionQuery query : children) {
            Option<Collection<String>> targetDomainClasses = query.getTargetDomainClasses();
            if (targetDomainClasses.some()) {
                targetTypes.addAll(targetDomainClasses.get());
            }
        }
        Option<Collection<String>> expressionTarget;
        if (targetTypes.isEmpty()) {
            expressionTarget = Options.newNone();
        } else {
            expressionTarget = Options.newSome(targetTypes);
        }
        return expressionTarget;
    }

    @Override
    public Collection<EPackage> getPackagesToImport() {
        Collection<EPackage> packagesToImport = new LinkedHashSet<>();
        for (IInterpretedExpressionQuery query : children) {
            packagesToImport.addAll(query.getPackagesToImport());
        }
        return packagesToImport;
    }

    @Override
    public Collection<String> getDependencies() {
        Collection<String> dependencies = new LinkedHashSet<>();
        for (IInterpretedExpressionQuery query : children) {
            dependencies.addAll(query.getDependencies());
        }
        return dependencies;
    }

    @Override
    public Map<String, VariableType> getAvailableVariables() {
        Map<String, VariableType> availableVariables = new LinkedHashMap<>();
        for (IInterpretedExpressionQuery query : children) {
            Map<String, VariableType> innerAvailableVariables = query.getAvailableVariables();
            for (Entry<String, VariableType> entry : innerAvailableVariables.entrySet()) {
                availableVariables.merge(entry.getKey(), entry.getValue(), (existingType, newSimilarType) -> VariableType.fromVariableTypes(existingType, newSimilarType));
            }
        }
        return availableVariables;
    }
}

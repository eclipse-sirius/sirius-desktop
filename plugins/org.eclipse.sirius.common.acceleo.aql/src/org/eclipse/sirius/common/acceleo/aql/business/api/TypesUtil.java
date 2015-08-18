/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.aql.business.api;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;

/**
 * An utility class to convert types denotations.
 * 
 * @author cedric
 */
public final class TypesUtil {

    private TypesUtil() {

    }

    /**
     * Create a map of IType suitable for the AQL engine from a sirius
     * {@link IInterpreterContext}.
     * 
     * @param context
     *            the sirius interpreter context.
     * @param queryEnvironment
     *            the environment to use to retrieve types from their name.
     * @return a map populated with variable descriptions matching the
     *         {@link IInterpreterContext} variables.
     */
    public static Map<String, Set<IType>> createAQLVariableTypesFromInterpreterContext(IInterpreterContext context, IQueryEnvironment queryEnvironment) {
        Map<String, Set<IType>> variableTypes = new LinkedHashMap<String, Set<IType>>();
        final Set<IType> selfTyping = new LinkedHashSet<IType>(2);
        VariableType targetTypeName = context.getTargetType();
        for (TypeName possibleType : targetTypeName.getPossibleTypes()) {
            EClassifierType found = searchEClassifierType(queryEnvironment, possibleType);
            if (found != null) {
                selfTyping.add(found);
            }
        }

        if (selfTyping.size() == 0) {
            selfTyping.add(new ClassType(queryEnvironment, EObject.class));
        }
        variableTypes.put("self", selfTyping); //$NON-NLS-1$

        for (Entry<String, VariableType> varDef : context.getVariables().entrySet()) {
            VariableType typeName = varDef.getValue();
            final Set<IType> potentialTypes = new LinkedHashSet<IType>(2);
            for (TypeName possibleVariableTypes : typeName.getPossibleTypes()) {
                EClassifierType found = searchEClassifierType(queryEnvironment, possibleVariableTypes);
                if (found != null) {
                    potentialTypes.add(found);
                }
            }
            if (potentialTypes.size() == 0) {
                potentialTypes.add(new ClassType(queryEnvironment, EObject.class));
            }
            variableTypes.put(varDef.getKey(), potentialTypes);
        }
        return variableTypes;
    }

    private static EClassifierType searchEClassifierType(IQueryEnvironment queryEnvironment, TypeName targetTypeName) {
        EClassifier found = null;
        if (targetTypeName.getPackagePrefix().some()) {
            String typeName = targetTypeName.getClassifierName();
            String name = targetTypeName.getPackagePrefix().get();
            found = queryEnvironment.getEPackageProvider().getType(name, typeName);
        } else {
            found = queryEnvironment.getEPackageProvider().getType(targetTypeName.getClassifierName());
        }
        if (found != null) {
            return new EClassifierType(queryEnvironment, found);
        }
        return null;
    }
}

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
        for (String targetTypeName : context.getTargetTypes()) {
            EClassifierType found = searchEClassifierType(queryEnvironment, targetTypeName);
            if (found != null) {
                selfTyping.add(found);
            }

        }
        if (selfTyping.size() == 0) {
            selfTyping.add(new ClassType(queryEnvironment, EObject.class));
        }
        variableTypes.put("self", selfTyping);
        for (Entry<String, String> varDef : context.getVariables().entrySet()) {
            String typeName = varDef.getValue();
            if (typeName != null) {
                EClassifierType found = searchEClassifierType(queryEnvironment, typeName);
                if (found != null) {
                    final Set<IType> potentialTypes = new LinkedHashSet<IType>(2);
                    potentialTypes.add(found);
                    variableTypes.put(varDef.getKey(), potentialTypes);
                }
            }
        }
        return variableTypes;
    }

    private static EClassifierType searchEClassifierType(IQueryEnvironment queryEnvironment, String targetTypeName) {
        EClassifier found = null;
        int separatorPosition = targetTypeName.indexOf('.');
        if (separatorPosition > -1) {
            String typeName = targetTypeName.substring(separatorPosition + 1);
            String nsPrefix = targetTypeName.substring(0, separatorPosition);
            found = queryEnvironment.getEPackageProvider().getType(nsPrefix, typeName);
        } else {
            found = queryEnvironment.getEPackageProvider().getType(targetTypeName);
        }
        if (found != null) {
            return new EClassifierType(queryEnvironment, found);
        }
        return null;
    }
}

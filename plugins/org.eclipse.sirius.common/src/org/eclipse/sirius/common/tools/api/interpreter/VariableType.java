/*******************************************************************************
 * Copyright (c) 2015, 2022 Obeo.
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
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A class representing the type of a variable in a Viewpoint Specification
 * model.
 * 
 * @author cedric
 * @since 3.0
 *
 */
public final class VariableType {

    /**
     * The type to use for variables for which nothing more specific could be
     * found.
     */
    public static final VariableType ANY_EOBJECT = fromString(TypeName.EOBJECT_TYPENAME.getCompleteName());

    private List<TypeName> types = Lists.newArrayListWithExpectedSize(2);

    private VariableType() {

    }

    /**
     * Create a new {@link VariableType} from a unique type name.
     * 
     * @param typeName
     *            the type name to use.
     * @return the newly created instance.
     */
    public static VariableType fromString(String typeName) {
        VariableType result = new VariableType();
        result.types.add(TypeName.fromString(typeName));
        return result;
    }

    /**
     * Create a new {@link VariableType} from a collection of types.
     * 
     * @param typeNames
     *            a collection of type names.
     * @return the newly created instance.
     */
    public static VariableType fromStrings(Collection<String> typeNames) {
        VariableType result = new VariableType();
        for (String domainClass : typeNames) {
            result.types.add(TypeName.fromString(domainClass));
        }
        return result;
    }

    /**
     * Create a new {@link VariableType} from a collection of EClassifiers.
     * 
     * @param types
     *            a collection of EClassifiers
     * @return the newly created instance.
     */
    public static VariableType fromEClassifiers(Collection<EClassifier> types) {
        VariableType result = new VariableType();
        for (EClassifier domainClass : types) {
            result.types.add(TypeName.fromEClassifier(domainClass));
        }
        return result;
    }

    /**
     * Create a new {@link VariableType} from a collection of EClassifiers and
     * Java classes.
     * 
     * @param types
     *            a collection of EClassifiers.
     * @param classes
     *            a collection of Java classes.
     * @return the newly created instance.
     */
    public static VariableType fromEClassifiersAndClasses(Collection<EClassifier> types, Collection<Class<?>> classes) {
        VariableType result = new VariableType();
        for (EClassifier domainClass : types) {
            result.types.add(TypeName.fromEClassifier(domainClass));
        }
        for (Class<?> javaType : classes) {
            result.types.add(TypeName.fromJavaClass(javaType));
        }
        return result;
    }

    /**
     * Create a new {@link VariableType} from a collection of EClassifiers.
     * 
     * @param type
     *            a Java class.
     * @return the newly created instance.
     */
    public static VariableType fromJavaClass(Class<?> type) {
        VariableType result = new VariableType();
        result.types.add(TypeName.fromJavaClass(type));
        return result;
    }

    /**
     * Create a new {@link VariableType} from a list of other {@link VariableType}s (concatenation without duplicates).
     * 
     * @param variableType
     *            a collection of VariableType.
     * @return the newly created instance.
     */
    public static VariableType fromVariableTypes(VariableType... variableTypes) {
        VariableType result = new VariableType();
        for (VariableType variableType : variableTypes) {
            for (TypeName typeName : variableType.getPossibleTypes()) {
                if (!containsType(result, typeName.getCompleteName())) {
                    result.types.add(typeName);
                }
            }
        }
        return result;
    }

    /**
     * Check if {@link VariableType} already contains a {@link TypeName} with this <code>typeName</code>.
     * 
     * @param variableType
     *            The variable type to check
     * @param typeName
     *            The type name to check
     * @return true if the VariableType contains a TypeName with this name, false otherwise.
     */
    private static boolean containsType(VariableType variableType, String typeName) {
        for (TypeName existingTypeName : variableType.getPossibleTypes()) {
            if (typeName.equals(existingTypeName.getCompleteName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a {@link TypeName} representation of a common type matching all the definitions. This should only be used
     * by interpreters or code which can't take into account the fact that a Variable can have multiple types which no
     * common super type in a Viewpoint Specification model.
     * 
     * When a {@link VariableType} is defined by several typeNames common super types will be searched for but if
     * several common types are found, then one will be arbitrarly choosen.
     * 
     * @param availableEPackages
     *            the available EPackages to use.
     * 
     * @return A TypeName of a type which matches all the definitions.
     */
    public TypeName getCommonType(Collection<EPackage> availableEPackages) {
        TypeName result = TypeName.ANY_TYPENAME;
        if (types.size() == 1) {
            result = types.get(0);
        }
        if (types.size() > 1) {

            Iterator<TypeName> typeNameIt = types.iterator();
            Set<EClass> commonSuperTypes = new LinkedHashSet<>();
            if (typeNameIt.hasNext()) {
                TypeName first = typeNameIt.next();
                commonSuperTypes = getAllSuperTypes(first, availableEPackages);
            }
            while (typeNameIt.hasNext() && commonSuperTypes.size() > 0) {
                TypeName type = typeNameIt.next();
                Set<EClass> nextTypeSuperTypes = getAllSuperTypes(type, availableEPackages);
                commonSuperTypes = Sets.intersection(commonSuperTypes, nextTypeSuperTypes);
            }

            if (commonSuperTypes.size() > 0) {
                EClass firstCommonSuperType = commonSuperTypes.iterator().next();
                result = TypeName.fromEClassifier(firstCommonSuperType);
            } else {
                result = TypeName.EOBJECT_TYPENAME;
            }
        }
        return result;
    }

    private Set<EClass> getAllSuperTypes(TypeName type, Collection<EPackage> collection) {
        Set<EClass> allSuperTypes = new LinkedHashSet<>();
        Iterator<EClass> it = Iterators.filter(type.search(collection).iterator(), EClass.class);
        while (it.hasNext()) {
            EClass curEClass = it.next();
            allSuperTypes.add(curEClass);
            allSuperTypes.addAll(curEClass.getEAllSuperTypes());
        }
        return allSuperTypes;
    }

    /**
     * return true if the type has an actual definition, false if the variable
     * is completely untyped.
     * 
     * @return true if the type has an actual definition.
     */
    public boolean hasDefinition() {
        return types.size() > 0;
    }

    /**
     * return all the possible types this Variable is defined with.
     * 
     * @return all the possible types this Variable is defined with.
     */
    public Collection<TypeName> getPossibleTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "[" + Joiner.on(',').join(this.types) + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }
}

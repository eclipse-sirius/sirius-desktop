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
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Iterator;
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
     * Return a {@link TypeName} representation of a common type matching all
     * the definitions. This should only be used by interpreters or code which
     * can't take into account the fact that a Variable can have multiple types
     * which no common super type in a Viewpoint Specification model.
     * 
     * When a {@link VariableType} is defined by several typeNames common super
     * types will be searched for but if several common types are found, then
     * one will be arbitrarly choosen.
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
            Set<EClass> commonSuperTypes = Sets.newLinkedHashSet();
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
        Set<EClass> allSuperTypes = Sets.newLinkedHashSet();
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

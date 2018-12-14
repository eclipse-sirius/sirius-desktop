/*******************************************************************************
 * Copyright (c) 2015-2019 Obeo.
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
import java.util.Optional;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EMFUtil;

/**
 * A class representing a type name.
 * 
 * 
 * @author cedric
 * @since 3.0
 *
 */
public final class TypeName {

    /**
     * A singleton TypeName for any java.lang.Object.
     */
    public static final TypeName ANY_TYPENAME = new TypeName("ecore.EJavaObject"); //$NON-NLS-1$

    /**
     * A singleton TypeName for any EObject.
     */
    public static final TypeName EOBJECT_TYPENAME = new TypeName("ecore.EObject"); //$NON-NLS-1$

    private static final String SEPARATOR = "."; //$NON-NLS-1$

    private final String typeName;

    private Class<?> javaType;

    /**
     * Create a new TypeName from a String representation. This constructor is
     * not visible in order to provide the guarantee EObject or EJavaObject will
     * always return the singleton instances.
     * 
     * @param typeName
     *            a string representation of a type, can be 'SomeType', or
     *            'ePackageName.SomeType' for instance.
     */
    private TypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Create a new TypeName from a Java Class.
     * 
     * @param type
     *            any Java class.
     */
    public TypeName(Class<?> type) {
        this.javaType = type;
        this.typeName = type.getName();
    }

    /**
     * return the string representation of the current {@link TypeName}.
     * 
     * @return the string representation of the current {@link TypeName}.
     */
    public String getCompleteName() {
        return typeName;
    }

    /**
     * return the string representation of the current {@link TypeName} using
     * the given separator in between package prefix (if specified in the
     * typename) and the classifier name.
     * 
     * @param separator
     *            the separator to use.
     * @return the string representation of the current {@link TypeName} using
     *         the given separator.
     */
    public String getCompleteName(String separator) {
        Optional<String> packagePrefix = getPackagePrefix();
        if (packagePrefix.isPresent()) {
            return packagePrefix.get() + separator + getClassifierName();
        } else {
            return getClassifierName();
        }
    }

    /**
     * Search for all the EClassifiers matching the current typename in a set of
     * EPackages.
     * 
     * @param availableEPackages
     *            a set of EPackages.
     * @return all the EClassifiers matching the typename.
     */
    public Collection<EClassifier> search(Collection<EPackage> availableEPackages) {
        Collection<EClassifier> matches = new LinkedHashSet<>();
        Iterator<EPackage> it = availableEPackages.iterator();
        while (it.hasNext()) {
            EPackage pak = it.next();
            EClassifier found = null;
            if (typeName != null && typeName.contains(SEPARATOR)) {
                if (pak.getName() != null && typeName.startsWith(pak.getName() + SEPARATOR)) {
                    String eClassName = typeName.substring(typeName.indexOf(SEPARATOR) + 1);
                    found = pak.getEClassifier(eClassName);
                }
            } else {
                found = pak.getEClassifier(typeName);
            }
            if (found != null) {
                matches.add(found);
            }
        }

        return matches;
    }

    @Override
    public String toString() {
        return typeName;
    }

    /**
     * Create a new TypeName from a String. returning one of the singleton
     * instances if needed.
     * 
     * @param typeName
     *            a string representation of a type, can be 'SomeType', or
     *            'ePackageName.SomeType' for instance. If null or an empty
     *            string is passed then ANY_TYPENAME is returned.
     * @return a TypeName instance.
     */
    public static TypeName fromString(String typeName) {
        TypeName result = ANY_TYPENAME;
        if (!StringUtil.isEmpty(typeName)) {
            String normalized = typeName.replace(EMFUtil.QUALIFIED_NAME_SEPARATOR, ".").trim(); //$NON-NLS-1$
            if ("ecore.EJavaObject".equals(normalized) || "EJavaObject".equals(normalized)) { //$NON-NLS-1$ //$NON-NLS-2$
                result = ANY_TYPENAME;
            } else if ("ecore.EObject".equals(normalized) || "EObject".equals(normalized)) { //$NON-NLS-1$ //$NON-NLS-2$
                result = EOBJECT_TYPENAME;
            } else {
                result = new TypeName(normalized);
            }
        }
        return result;
    }

    /**
     * Create a new TypeName from a EClassifier. returning one of the singleton
     * instances if needed.
     * 
     * @param type
     *            the EClassifier instance.
     * @return a TypeName instance.
     */
    public static TypeName fromEClassifier(EClassifier type) {
        TypeName result = null;
        if (type == EcorePackage.Literals.EJAVA_OBJECT) {
            result = ANY_TYPENAME;
        } else if (type == EcorePackage.Literals.EOBJECT) {
            result = EOBJECT_TYPENAME;
        } else {
            if (type.getEPackage() != null && !StringUtil.isEmpty(type.getEPackage().getName())) {
                result = new TypeName(type.getEPackage().getName() + SEPARATOR + type.getName());
            } else {
                result = new TypeName(type.getName());
            }
        }
        return result;
    }

    /**
     * Create a new TypeName from a Java class.
     * 
     * @param type
     *            a Java class
     * @return a TypeName instance.
     */
    public static TypeName fromJavaClass(Class<?> type) {
        TypeName result = new TypeName(type);
        return result;
    }

    /**
     * Return the Java class associated to this type if there is one.
     * 
     * @return the Java class associated to this type if there is one.
     */
    public Optional<Class<?>> getJavaClass() {
        return Optional.ofNullable(this.javaType);
    }

    /**
     * return only the classifier name stripped down of any package prefix even
     * if it was specified.
     * 
     * @return only the classifier name stripped down of any package prefix even
     *         if it was specified.
     */
    public String getClassifierName() {
        int indexOfSeparator = typeName.indexOf(SEPARATOR);
        if (indexOfSeparator != -1) {
            return typeName.substring(indexOfSeparator + 1);
        } else {
            return typeName;
        }
    }

    /**
     * return the type name package prefix if it was specified.
     * 
     * @return the type name package prefix if it was specified.
     */
    public Optional<String> getPackagePrefix() {
        int indexOfSeparator = typeName.indexOf(SEPARATOR);
        if (indexOfSeparator != -1) {
            return Optional.of(typeName.substring(0, indexOfSeparator));
        } else {
            return Optional.empty();
        }
    }
}

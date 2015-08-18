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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Sets;

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
        Option<String> packagePrefix = getPackagePrefix();
        if (packagePrefix.some()) {
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
        Collection<EClassifier> matches = Sets.newLinkedHashSet();
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
            if ("ecore.EJavaObject".equals(typeName) || "EJavaObject".equals(typeName)) { //$NON-NLS-1$ //$NON-NLS-2$
                result = ANY_TYPENAME;
            } else if ("ecore.EObject".equals(typeName) || "EObject".equals(typeName)) { //$NON-NLS-1$ //$NON-NLS-2$
                result = EOBJECT_TYPENAME;
            } else {
                result = new TypeName(typeName.trim());
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
    public Option<String> getPackagePrefix() {
        int indexOfSeparator = typeName.indexOf(SEPARATOR);
        if (indexOfSeparator != -1) {
            return Options.newSome(typeName.substring(0, indexOfSeparator));
        } else {
            return Options.newNone();
        }
    }
}

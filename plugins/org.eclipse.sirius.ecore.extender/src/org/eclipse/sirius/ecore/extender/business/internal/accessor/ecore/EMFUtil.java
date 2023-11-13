/******************************************************************************
 * Copyright (c) 2006, 2023 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 ****************************************************************************/

package org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class comes from org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil.
 *
 * @author mchauvin
 *
 */
//CHECKSTYLE:OFF
public class EMFUtil {

    /**
     * Mapping of {@link EClass} ==&gt; name {@link EAttribute}. Use a weak map
     * to allow reclamation of dynamically-generated Ecore models; in order for
     * this to work, the keys are also weak refs.
     */
    private static final Map NAME_ATTRIBUTES = new java.util.WeakHashMap();

    /**
     * Mapping of {@link EClass} ==&gt; qualified name {@link EAttribute}. Use a
     * weak map to allow reclamation of dynamically-generated Ecore models; in
     * order for this to work, the keys are also weak refs.
     */
    private static final Map QNAME_ATTRIBUTES = new java.util.WeakHashMap();

    public final static String EMPTY_STRING = ""; //$NON-NLS-1$

    public final static char META_CLASS_BEGIN = '<';

    public final static char META_CLASS_END = '>';

    public final static String QUALIFIED_NAME_SEPARATOR = "::"; //$NON-NLS-1$

    /**
     * Gets the name attribute of an <code>EClass</code>.
     *
     * @param eClass
     *            The <code>EClass</code>.
     * @return The name attribute.
     */
    public static EAttribute getNameAttribute(EClass eClass) {
        EAttribute nameAttribute = null;

        // first, try to get the cached attribute
        WeakReference ref = (WeakReference) NAME_ATTRIBUTES.get(eClass);
        if (ref != null) {
            nameAttribute = (EAttribute) ref.get();
        } else {
            EStructuralFeature feature = eClass.getEStructuralFeature("name"); //$NON-NLS-1$
            if (feature != null) {
                if (feature instanceof EAttribute) {
                    EClassifier type = feature.getEType();
                    if (type != null) {
                        if (type.getInstanceClass() == String.class) {
                            nameAttribute = (EAttribute) feature;
                        }
                    }
                }
            }

            // cache the result, whatever it is. As long as the key (EClass)
            // isn't GCed, then the value (WeakRef) will remain to indicate
            // that we have at least cached a null
            NAME_ATTRIBUTES.put(eClass, new WeakReference(nameAttribute));
        }

        return nameAttribute;
    }

    /**
     * Gets the qualified name attribute of an <code>EClass</code>.
     *
     * @param eClass
     *            The <code>EClass</code>.
     * @return The qualified name attribute.
     */
    public static EAttribute getQualifiedNameAttribute(EClass eClass) {
        EAttribute nameAttribute = null;

        // first, try to get the cached attribute
        WeakReference ref = (WeakReference) QNAME_ATTRIBUTES.get(eClass);
        if (ref != null) {
            nameAttribute = (EAttribute) ref.get();
        } else {
            EStructuralFeature feature = eClass.getEStructuralFeature("qualifiedName"); //$NON-NLS-1$
            if (feature != null) {
                if ((feature instanceof EAttribute) && (feature.getEType().getInstanceClass() == String.class)) {
                    nameAttribute = (EAttribute) feature;
                }
            }

            // cache the result, whatever it is. As long as the key (EClass)
            // isn't GCed, then the value (WeakRef) will remain to indicate
            // that we have at least cached a null
            QNAME_ATTRIBUTES.put(eClass, new WeakReference(nameAttribute));
        }

        return nameAttribute;
    }

    /**
     * Gets the fully qualified name of an object.
     *
     * @param eObject
     *            The object.
     * @param formatted
     *            if True, unnamed parents will be listed using their meta-class
     *            name.
     * @return The object's qualified name.
     */
    public static String getQualifiedName(EObject eObject, boolean formatted) {
        if (eObject.eIsProxy()) {
            return getProxyQualifiedName(eObject);
        }

        if (!formatted) {
            EAttribute qNameAttribute = getQualifiedNameAttribute(eObject.eClass());
            if (qNameAttribute != null) {
                String qualifiedName = (String) eObject.eGet(qNameAttribute);
                if (qualifiedName != null) {
                    return qualifiedName;
                } else {
                    return EMPTY_STRING;
                }
            }
        }

        String prefix = EMPTY_STRING;
        EObject eContainer = eObject.eContainer();
        while (eContainer instanceof EAnnotation) {
            eContainer = eContainer.eContainer();
        }
        if (eContainer != null) {
            prefix = getQualifiedName(eContainer, formatted);
        }
        String name = getName(eObject);
        if ((formatted) && (name.equals(EMPTY_STRING))) {
            name = META_CLASS_BEGIN + getLocalizedName(eObject.eClass()) + META_CLASS_END;
        }
        return (prefix.length() == 0) ? name : (prefix + ((name.equals(EMPTY_STRING)) ? EMPTY_STRING : (QUALIFIED_NAME_SEPARATOR + name)));
    }

    private static String getProxyQualifiedName(EObject proxy) {
        String result = EMPTY_STRING;
        return result;
    }

    /**
     * Gets the localized name of a meta-model element. The name will not
     * contain spaces.
     *
     * @param element
     *            The meta-model element.
     * @return The localized name of the meta-model element.
     */
    public static String getLocalizedName(ENamedElement element) {
        return element.getName();
    }

    /**
     * Gets the name of an object if the object has name, returns an empty
     * string otherwise.
     *
     * @param eObject
     *            The object.
     * @return The object's name.
     */
    public static String getName(EObject eObject) {
        if (eObject == null) {
            return EMPTY_STRING;
        }

        if (eObject.eIsProxy()) {
            return getProxyName(eObject);
        }

        EAttribute nameAttribute = getNameAttribute(eObject.eClass());
        if (nameAttribute != null) {
            String name = (String) eObject.eGet(nameAttribute);
            if (name != null) {
                return name;
            }
        }

        return EMPTY_STRING;
    }

    private static String getProxyName(EObject proxy) {
        String result = EMPTY_STRING;
        return result;
    }

    /**
     * Create stream on a tree to visit each node (excluding the root element)
     *
     * @param <T>
     *            Type of elements
     * @param root
     *            The root element of the tree
     * @param childrenFunc
     *            A function that takes a node and returns an iterator on all direct children
     * @return The stream of all elements of tree
     */
    public static <T> Stream<T> getTreeStream(T root, Function<T, Iterable<T>> childrenFunc) {
        AbstractTreeIterator<T> iterator = new AbstractTreeIterator<T>(root, false) {
            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
            @Override
            public Iterator<T> getChildren(Object parent) {
                return childrenFunc.apply((T) parent).iterator();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }
}
//CHECKSTYLE:ON

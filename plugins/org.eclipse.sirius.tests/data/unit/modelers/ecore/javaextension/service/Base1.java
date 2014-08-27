/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.service;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Services for Base1.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
// CHECKSTYLE:OFF
public class Base1 {

    /**
     * Check if the {@link EModelElement} is documented (with an annotation with
     * Base1 as key).
     * 
     * @param element
     *            the element to check
     * @return true if the element is documented as explains, false otherwise
     */
    public boolean isDocumented(final EModelElement element) {
        final EList<EAnnotation> annotations = element.getEAnnotations();
        int nbDoc = 0;
        for (EAnnotation annotation : annotations) {
            final EMap<String, String> details = annotation.getDetails();
            for (Map.Entry<String, String> entry : details) {
                if (entry instanceof EStringToStringMapEntryImpl) {
                    if ("Base1".equals(((EStringToStringMapEntryImpl) entry).getKey())) {
                        nbDoc++;
                    }
                }
            }
        }
        return nbDoc > 0;
    }

    /**
     * Check if the element is documented (with an annotation with
     * documentationBase as key).
     * 
     * @param element
     *            the element to check
     * @return true if the element is documented
     */
    public boolean isDocumentedOnlyInBase(final EModelElement element) {
        final EList<EAnnotation> annotations = element.getEAnnotations();
        int nbDoc = 0;
        for (EAnnotation annotation : annotations) {
            final EMap<String, String> details = annotation.getDetails();
            for (Map.Entry<String, String> entry : details) {
                if (entry instanceof EStringToStringMapEntryImpl) {
                    if ("Base1".equals(((EStringToStringMapEntryImpl) entry).getKey())) {
                        nbDoc++;
                    }
                }
            }
        }
        return nbDoc > 0;
    }

    private boolean isOk(final EDataType type, final String name) {
        return type.getName().trim().toUpperCase().equals(name.trim().toUpperCase());
    }

    /**
     * Return a datatype with the given name, if it is available in the root
     * childs.
     * 
     * @param root
     *            starts browsing there.
     * @param name
     *            name of the Datatype to find
     * @return the named DataType or null if not found.
     */
    public EDataType subGetTypeFromName(final EObject root, final String name) {
        final Iterator it = root.eAllContents();
        while (it.hasNext()) {
            final EObject eObj = (EObject) it.next();
            if (eObj instanceof EDataType) {

                final EDataType type = (EDataType) eObj;
                if (type.getName() != null && name != null) {
                    if (isOk(type, name))
                        return type;
                }

            }
        }
        return null;
    }

    public EDataType getTypeFromName(final EAttribute receiver, final String name) {
        final EObject root = EcoreUtil.getRootContainer(receiver);
        EDataType result = subGetTypeFromName(root, name);
        if (result == null) {
            result = subGetTypeFromName(EcorePackage.eINSTANCE, name);
        }
        return result;
    }

    /*
     * EClasses name
     */

    public String getEClassName(final EClass clazz) {
        if (clazz.isAbstract())
            return "<< " + clazz.getName() + " >>";
        return clazz.getName();

    }

    /*
     * EReference label's
     * 
     */

    /**
     * Return the displayed label for an EReference.
     */
    public String getEReferenceName(final EReference ref) {
        String result = "";
        result += "[" + getStringFromCard(ref.getLowerBound()) + ".." + getStringFromCard(ref.getUpperBound()) + "] ";
        result += ref.getName();
        return result;
    }

    private String getStringFromCard(final int bound) {
        if (bound == -1)
            return "*";
        return new Integer(bound).toString();
    }

    public String getReferenceNameFromString(final EReference ref, final String name) {
        final int end = name.indexOf("]");
        if (end != -1 && end < name.length())
            return name.substring(end + 1).trim();
        return name;
    }

    public int getLowerBoundFromString(final EReference ref, final String cardinality) {
        int result = ref.getLowerBound();
        final String cardString = getCardinalityFromReferenceName(cardinality);
        if (cardString != null) {
            final Integer lowerBound = getLowerBoundFromCardString(cardString);
            if (lowerBound != null)
                result = lowerBound.intValue();
            System.err.println(result);
        }
        return result;
    }

    public int getUpperBoundFromString(final EReference ref, final String cardinality) {
        int result = ref.getUpperBound();
        final String cardString = getCardinalityFromReferenceName(cardinality);
        if (cardString != null) {
            final Integer upperBound = getUpperBoundFromCardString(cardString);
            if (upperBound != null)
                result = upperBound.intValue();
        }
        return result;
    }

    public boolean getContainmentFromString(final EReference ref, final String name) {
        if (name.toLowerCase().indexOf("owned") != -1)
            return true;
        return false;
    }

    private Integer getLowerBoundFromCardString(final String cardString) {
        final int end = cardString.indexOf("..");
        final int start = cardString.indexOf("[");
        if (end != -1 && start != -1 && start < end) {
            final String lowerBound = cardString.substring(start + 1, end);
            System.out.println("lower : " + lowerBound);
            try {
                if (lowerBound != null && Integer.decode(lowerBound) != null)
                    return Integer.decode(lowerBound);
            } catch (NumberFormatException e) {
                // do nothing on exception, return null
            }
            if (lowerBound != null && lowerBound.trim().equals("*"))
                return new Integer(-1);
        }
        return null;
    }

    private Integer getUpperBoundFromCardString(final String cardString) {
        final int start = cardString.indexOf("..");
        if (start != -1 && start + 2 < cardString.length()) {
            final String upperBound = cardString.substring(start + 2);
            try {
                if (upperBound != null && Integer.decode(upperBound) != null)
                    return Integer.decode(upperBound);
            } catch (final NumberFormatException e) {
                // do nothing on exception, return null
            }
            if (upperBound != null && upperBound.trim().equals("*"))
                return new Integer(-1);
        }
        return null;
    }

    private String getCardinalityFromReferenceName(final String cardinality) {
        final int start = cardinality.indexOf("[");
        final int end = cardinality.indexOf("]");
        if (start != -1 && end != -1 && start < end && end < cardinality.length())
            return cardinality.substring(start, end);
        return null;
    }
}

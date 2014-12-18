/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.design.service;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcoreFactory;

/**
 * Services on EAttributes usable from a VSM.
 */
public class EAttributeServices {
    private static final String DERIVED_ATTRIBUTE_PREFIX = "/";

    private static final String TYPE_SEPARATOR = ":";

    private static final String DEFAULT_VALUE_SEPARATOR = "=";

    /**
     * Renders the specified attribute into a string to display to the end-user.
     * 
     * @param attr
     *            the attribute to render.
     * @return a string representation of the attribute suitable to display to
     *         the end-user.
     */
    public String render(EAttribute attr) {
        StringBuilder sb = new StringBuilder();
        renderName(attr, sb);
        renderType(attr, sb);
        renderDefaultValue(attr, sb);
        return sb.toString();
    }

    /**
     * Update the specified attribute according the the edit string entered by
     * the user.
     * 
     * @param editString
     *            the edit string entered by the user.
     */
    public EAttribute performEdit(EAttribute attr, String editString) {
        int typeStart = editString.indexOf(TYPE_SEPARATOR);
        boolean setType = typeStart != -1;

        int defaultStart = editString.indexOf(DEFAULT_VALUE_SEPARATOR);
        boolean setDefault = defaultStart != -1;

        int nameEnd = editString.length();
        if (setType) {
            nameEnd = Math.min(nameEnd, typeStart);
        }
        if (setDefault) {
            nameEnd = Math.min(nameEnd, defaultStart);
        }
        String namePart = editString.substring(0, nameEnd).trim();
        boolean setName = namePart.length() > 0;

        String typePart = null;
        if (setType) {
            int typeEnd = setDefault ? defaultStart : editString.length();
            typePart = editString.substring(typeStart + TYPE_SEPARATOR.length(), typeEnd).trim();
        }

        String defaultPart = null;
        if (setDefault) {
            defaultPart = editString.substring(defaultStart + DEFAULT_VALUE_SEPARATOR.length()).trim();
        }

        if (setName) {
            if (namePart.startsWith(DERIVED_ATTRIBUTE_PREFIX)) {
                attr.setDerived(true);
                namePart = namePart.substring(DERIVED_ATTRIBUTE_PREFIX.length());
            } else {
                attr.setDerived(false);
            }
            attr.setName(namePart);
        }
        if (setType) {
            EClassifier type = new EcoreService().findTypeByName(attr, typePart);
            if (type != null) {
                attr.setEType(type);
            }
        }
        if (setDefault && defaultPart.length() > 0) {
            attr.setDefaultValueLiteral(defaultPart);
        }
        return attr;
    }

    private void renderName(EAttribute attr, StringBuilder sb) {
        if (attr.getName() != null) {
            if (attr.isDerived()) {
                sb.append(DERIVED_ATTRIBUTE_PREFIX);
            }
            sb.append(attr.getName());
        }
    }

    private void renderType(EAttribute attr, StringBuilder sb) {
        boolean hasNamedType = attr.getEType() != null && attr.getEType().getName() != null;
        if (hasNamedType) {
            if (attr.getName() != null) {
                sb.append(" ");
            }
            sb.append(TYPE_SEPARATOR).append(" ").append(attr.getEType().getName());
        }
    }

    private void renderDefaultValue(EAttribute attr, StringBuilder sb) {
        if (attr.getDefaultValueLiteral() != null) {
            if (attr.getDefaultValueLiteral().length() > 0) {
                sb.append(" ").append(DEFAULT_VALUE_SEPARATOR).append(" ").append(attr.getDefaultValueLiteral());
            }
        } else if (attr.getDefaultValue() != null) {
            sb.append(" ").append(DEFAULT_VALUE_SEPARATOR).append(" ");
            String serializable = EcoreFactory.eINSTANCE.convertToString(attr.getEAttributeType(), attr.getDefaultValue());
            if (!"0".equals(serializable)) {
                // Ignore this default value and consider it as blank default
                // value. This is the result of the '\u0000' (default value for
                // EChar data type) to string.
                sb.append(serializable);
            }
        }
    }
}

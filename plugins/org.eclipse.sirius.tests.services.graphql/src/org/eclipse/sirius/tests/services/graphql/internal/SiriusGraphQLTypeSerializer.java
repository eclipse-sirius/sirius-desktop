/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.services.graphql.internal;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class used to transform the result of the introspection query into the standard definition language.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLTypeSerializer {

    /**
     * The name of the kind field.
     */
    private static final String KIND = "kind"; //$NON-NLS-1$

    /**
     * The value of the kind field used to identify types.
     */
    private static final String OBJECT = "OBJECT"; //$NON-NLS-1$

    /**
     * The value of the kind field used to identify interfaces.
     */
    private static final String INTERFACE = "INTERFACE"; //$NON-NLS-1$

    /**
     * The value of the kind field used to identify input types.
     */
    private static final String INPUT_OBJECT = "INPUT_OBJECT"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    private static final String NAME = "name"; //$NON-NLS-1$

    /**
     * The name of the fields field.
     */
    private static final String FIELDS = "fields"; //$NON-NLS-1$

    /**
     * The name of the type field.
     */
    private static final String TYPE = "type"; //$NON-NLS-1$

    /**
     * The name of the args field.
     */
    private static final String ARGS = "args"; //$NON-NLS-1$

    /**
     * The name of the ofType field.
     */
    private static final String OFTYPE = "ofType"; //$NON-NLS-1$

    /**
     * The name of the interfaces field.
     */
    private static final String INTERFACES = "interfaces"; //$NON-NLS-1$

    /**
     * The name of the inputFields field.
     */
    private static final String INPUT_FIELDS = "inputFields"; //$NON-NLS-1$

    /**
     * The value used to indicate that an element is not null.
     */
    private static final String NON_NULL = "NON_NULL"; //$NON-NLS-1$

    /**
     * The value used to indicate that an element is a list.
     */
    private static final String LIST = "LIST"; //$NON-NLS-1$

    /**
     * Returns a standard definition language representation of the given type.
     * 
     * @param type
     *            The type
     * @return The SDL representation of the type
     */
    @SuppressWarnings("unchecked")
    public String typeToString(Map<String, Object> type) {
        StringBuilder stringBuilder = new StringBuilder();

        String kind = (String) type.get(KIND);
        if (OBJECT.equals(kind)) {
            stringBuilder.append("type "); //$NON-NLS-1$
        } else if (INTERFACE.equals(kind)) {
            stringBuilder.append("interface "); //$NON-NLS-1$
        } else if (INPUT_OBJECT.equals(kind)) {
            stringBuilder.append("input "); //$NON-NLS-1$
        }

        String name = (String) type.get(NAME);
        stringBuilder.append(name);

        List<Map<String, Object>> interfaces = (List<Map<String, Object>>) type.get(INTERFACES);
        if (interfaces != null) {
            // @formatter:off
            List<String> interfacesString = interfaces.stream()
                    .map(anInterface -> anInterface.get(NAME))
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .collect(Collectors.toList());
            // @formatter:on

            if (!interfacesString.isEmpty()) {
                stringBuilder.append(" implements "); //$NON-NLS-1$
                stringBuilder.append(interfacesString.stream().collect(Collectors.joining(", "))); //$NON-NLS-1$
            }
        }
        stringBuilder.append(" {\n"); //$NON-NLS-1$

        List<Map<String, Object>> fields = (List<Map<String, Object>>) type.get(FIELDS);
        if (fields != null) {
            fields.stream().map(this::fieldToString).forEach(fieldString -> {
                stringBuilder.append(fieldString + "\n"); //$NON-NLS-1$
            });
        }

        List<Map<String, Object>> inputFields = (List<Map<String, Object>>) type.get(INPUT_FIELDS);
        if (inputFields != null) {
            inputFields.stream().map(this::fieldToString).forEach(fieldString -> {
                stringBuilder.append(fieldString + "\n"); //$NON-NLS-1$
            });
        }

        stringBuilder.append("}"); //$NON-NLS-1$

        return stringBuilder.toString();
    }

    /**
     * Returns a standard definition language representation of the given field.
     * 
     * @param field
     *            The field
     * @return The SDL representation of the field
     */
    @SuppressWarnings("unchecked")
    private String fieldToString(Map<String, Object> field) {
        StringBuilder stringBuilder = new StringBuilder();

        String name = (String) field.get(NAME);
        stringBuilder.append(name);

        List<Map<String, Object>> args = (List<Map<String, Object>>) field.get(ARGS);
        if (args != null) {
            List<String> stringArgs = args.stream().map(this::argToString).collect(Collectors.toList());
            if (!stringArgs.isEmpty()) {
                stringBuilder.append("("); //$NON-NLS-1$

                stringBuilder.append(stringArgs.stream().collect(Collectors.joining(", "))); //$NON-NLS-1$

                stringBuilder.append(")"); //$NON-NLS-1$
            }
        }
        stringBuilder.append(": "); //$NON-NLS-1$

        Map<String, Object> type = (Map<String, Object>) field.get(TYPE);
        stringBuilder.append(this.typeRefToString(type));

        return stringBuilder.toString();
    }

    /**
     * Returns a standard definition language representation of the given argument.
     * 
     * @param arg
     *            The argument
     * @return The SDL representation of the argument
     */
    @SuppressWarnings("unchecked")
    private String argToString(Map<String, Object> arg) {
        StringBuilder stringBuilder = new StringBuilder();

        String name = (String) arg.get(NAME);
        stringBuilder.append(name + ": "); //$NON-NLS-1$

        Map<String, Object> type = (Map<String, Object>) arg.get(TYPE);
        stringBuilder.append(this.typeRefToString(type));

        return stringBuilder.toString();
    }

    /**
     * Returns a standard definition language representation of the given type reference.
     * 
     * @param type
     *            The type
     * @return The SDL representation of the type
     */
    @SuppressWarnings("unchecked")
    private String typeRefToString(Map<String, Object> type) {
        String typeName = (String) type.get(NAME);
        String typeKind = (String) type.get(KIND);

        Map<String, Object> ofType = (Map<String, Object>) type.get(OFTYPE);
        if (typeName == null && ofType != null) {
            typeName = this.typeRefToString(ofType);
        }
        if (NON_NULL.equals(typeKind)) {
            typeName = typeName + "!"; //$NON-NLS-1$
        } else if (LIST.equals(typeKind)) {
            typeName = "[" + typeName + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        return typeName;
    }

}

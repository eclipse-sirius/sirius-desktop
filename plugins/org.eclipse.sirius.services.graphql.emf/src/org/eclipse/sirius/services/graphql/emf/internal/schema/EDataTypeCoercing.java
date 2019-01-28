/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

/**
 * Utility class used to parse and serialize a custom GraphQL scalar based on an EMF EDataType.
 * 
 * @author sbegaudeau
 */
public class EDataTypeCoercing implements Coercing<Object, Object> {

    /**
     * The EDataType.
     */
    private final EDataType eDataType;

    /**
     * The eFactory instance.
     */
    private final EFactory eFactoryInstance;

    /**
     * The constructor.
     * 
     * @param eDataType
     *            The EDataType
     */
    public EDataTypeCoercing(EDataType eDataType) {
        this.eDataType = eDataType;
        this.eFactoryInstance = this.eDataType.getEPackage().getEFactoryInstance();
    }

    @Override
    public Object parseLiteral(Object literal) throws CoercingParseLiteralException {
        if (literal instanceof StringValue) {
            StringValue stringValue = (StringValue) literal;
            String value = stringValue.getValue();
            Object result = this.eFactoryInstance.createFromString(this.eDataType, value);
            return result;
        }
        throw new CoercingParseValueException();
    }

    @Override
    public Object parseValue(Object value) throws CoercingParseValueException {
        if (value instanceof String) {
            String stringValue = (String) value;
            Object result = this.eFactoryInstance.createFromString(this.eDataType, stringValue);
            return result;
        }
        throw new CoercingParseValueException();
    }

    @Override
    public Object serialize(Object value) throws CoercingSerializeException {
        return this.eFactoryInstance.convertToString(this.eDataType, value);
    }

}

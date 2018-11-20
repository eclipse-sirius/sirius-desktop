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
package org.eclipse.sirius.services.graphql.internal.schema.query.emf;

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
public class SiriusGraphQLEDataTypeCoercing implements Coercing<Object, Object> {

    /**
     * The EDataType.
     */
    private EDataType eDataType;

    /**
     * The constructor.
     * 
     * @param eDataType
     *            The EDataType
     */
    public SiriusGraphQLEDataTypeCoercing(EDataType eDataType) {
        this.eDataType = eDataType;
    }

    @Override
    public Object parseLiteral(Object arg0) throws CoercingParseLiteralException {
        if (arg0 instanceof StringValue) {
            StringValue stringValue = (StringValue) arg0;
            String value = stringValue.getValue();
            EFactory eFactoryInstance = this.eDataType.getEPackage().getEFactoryInstance();
            Object result = eFactoryInstance.createFromString(this.eDataType, value);
            return result;
        }
        throw new CoercingParseValueException();
    }

    @Override
    public Object parseValue(Object arg0) throws CoercingParseValueException {
        if (arg0 instanceof String) {
            String value = (String) arg0;
            EFactory eFactoryInstance = this.eDataType.getEPackage().getEFactoryInstance();
            Object result = eFactoryInstance.createFromString(this.eDataType, value);
            return result;
        }
        throw new CoercingParseValueException();
    }

    @Override
    public Object serialize(Object arg0) throws CoercingSerializeException {
        EFactory eFactoryInstance = this.eDataType.getEPackage().getEFactoryInstance();
        String result = eFactoryInstance.convertToString(this.eDataType, arg0);
        return result;
    }

}

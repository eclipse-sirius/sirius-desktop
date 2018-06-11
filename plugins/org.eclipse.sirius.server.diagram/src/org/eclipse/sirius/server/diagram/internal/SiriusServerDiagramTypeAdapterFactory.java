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
package org.eclipse.sirius.server.diagram.internal;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramExecuteNodeCreationToolAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestModelAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestToolsAction;

/**
 * The diagram type adapter factory.
 *
 * @author sbegaudeau
 */
public class SiriusServerDiagramTypeAdapterFactory implements TypeAdapterFactory {

    /**
     * The map of type for a specific kind.
     */
    private Map<String, Class<? extends AbstractSiriusDiagramAction>> kindToType = new LinkedHashMap<>();

    /**
     * The map of the kind for a specific type.
     */
    private Map<Class<? extends AbstractSiriusDiagramAction>, String> typeToKind = new LinkedHashMap<>();

    /**
     * The constructor.
     */
    public SiriusServerDiagramTypeAdapterFactory() {
        this.kindToType.put(SiriusDiagramExecuteNodeCreationToolAction.KIND, SiriusDiagramExecuteNodeCreationToolAction.class);
        this.typeToKind.put(SiriusDiagramExecuteNodeCreationToolAction.class, SiriusDiagramExecuteNodeCreationToolAction.KIND);

        this.kindToType.put(SiriusDiagramRequestModelAction.KIND, SiriusDiagramRequestModelAction.class);
        this.typeToKind.put(SiriusDiagramRequestModelAction.class, SiriusDiagramRequestModelAction.KIND);

        this.kindToType.put(SiriusDiagramRequestToolsAction.KIND, SiriusDiagramRequestToolsAction.class);
        this.typeToKind.put(SiriusDiagramRequestToolsAction.class, SiriusDiagramRequestToolsAction.KIND);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.google.gson.TypeAdapterFactory#create(com.google.gson.Gson,
     *      com.google.gson.reflect.TypeToken)
     */
    @Override
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        Map<String, TypeAdapter<AbstractSiriusDiagramAction>> kindToTypeAdapter = new LinkedHashMap<>();
        Map<Class<? extends AbstractSiriusDiagramAction>, TypeAdapter<AbstractSiriusDiagramAction>> typeToTypeAdapter = new LinkedHashMap<>();

        this.kindToType.entrySet().forEach(entry -> {
            @SuppressWarnings("unchecked")
            TypeAdapter<AbstractSiriusDiagramAction> delegateTypeAdapter = (TypeAdapter<AbstractSiriusDiagramAction>) gson.getDelegateAdapter(this, TypeToken.get(entry.getValue()));
            kindToTypeAdapter.put(entry.getKey(), delegateTypeAdapter);
            typeToTypeAdapter.put(entry.getValue(), delegateTypeAdapter);
        });

        TypeAdapter<AbstractSiriusDiagramAction> typeAdapter = new SiriusServerDiagramTypeAdapter(kindToTypeAdapter, this.typeToKind, typeToTypeAdapter).nullSafe();

        Class<? super R> rawType = type.getRawType();
        if (AbstractSiriusDiagramAction.class.isAssignableFrom(rawType)) {
            @SuppressWarnings("unchecked")
            TypeAdapter<R> result = (TypeAdapter<R>) typeAdapter;
            return result;
        }
        return null;
    }

}

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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;

/**
 * The type adapter used to deserialize the sirius diagram actions.
 *
 * @author sbegaudeau
 */
@SuppressWarnings("restriction")
public class SiriusServerDiagramTypeAdapter extends TypeAdapter<AbstractSiriusDiagramAction> {

    /**
     * The name of the field used to determine the class to be used for the
     * deserialization.
     */
    private static final String KIND = "kind"; //$NON-NLS-1$

    /**
     * Map of the type adapter to be used for the deserialization for each kind.
     */
    private Map<String, TypeAdapter<AbstractSiriusDiagramAction>> kindToTypeAdapter = new LinkedHashMap<>();

    /**
     * Map of the kind for a specific class to instantiate.
     */
    private Map<Class<? extends AbstractSiriusDiagramAction>, String> typeToKind = new LinkedHashMap<>();

    /**
     * Map of the type adapter for a specific class.
     */
    private Map<Class<? extends AbstractSiriusDiagramAction>, TypeAdapter<AbstractSiriusDiagramAction>> typeToTypeAdapter = new LinkedHashMap<>();

    /**
     * The constructor.
     * 
     * @param kindToTypeAdapter
     *            The kind to type adapter map
     * @param typeToKind
     *            The type to kind map
     * @param typeToTypeAdapter
     *            The type to type adapter map
     */
    public SiriusServerDiagramTypeAdapter(Map<String, TypeAdapter<AbstractSiriusDiagramAction>> kindToTypeAdapter, Map<Class<? extends AbstractSiriusDiagramAction>, String> typeToKind,
            Map<Class<? extends AbstractSiriusDiagramAction>, TypeAdapter<AbstractSiriusDiagramAction>> typeToTypeAdapter) {
        this.kindToTypeAdapter = kindToTypeAdapter;
        this.typeToKind = typeToKind;
        this.typeToTypeAdapter = typeToTypeAdapter;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.google.gson.TypeAdapter#read(com.google.gson.stream.JsonReader)
     */
    @Override
    public AbstractSiriusDiagramAction read(JsonReader in) throws IOException {
        JsonElement jsonElement = Streams.parse(in);
        JsonElement kindJsonElement = jsonElement.getAsJsonObject().remove(KIND);

        String kind = kindJsonElement.getAsString();
        TypeAdapter<?> typeAdapter = this.kindToTypeAdapter.get(kind);
        Object object = typeAdapter.fromJsonTree(jsonElement);
        if (object instanceof AbstractSiriusDiagramAction) {
            return (AbstractSiriusDiagramAction) object;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.google.gson.TypeAdapter#write(com.google.gson.stream.JsonWriter,
     *      java.lang.Object)
     */
    @Override
    public void write(JsonWriter out, AbstractSiriusDiagramAction value) throws IOException {
        Class<? extends AbstractSiriusDiagramAction> type = value.getClass();
        String kind = this.typeToKind.get(type);
        TypeAdapter<AbstractSiriusDiagramAction> delegate = this.typeToTypeAdapter.get(type);

        JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();
        JsonObject clone = new JsonObject();
        clone.add(KIND, new JsonPrimitive(kind));

        jsonObject.entrySet().forEach(entry -> clone.add(entry.getKey(), entry.getValue()));

        Streams.write(clone, out);
    }

}

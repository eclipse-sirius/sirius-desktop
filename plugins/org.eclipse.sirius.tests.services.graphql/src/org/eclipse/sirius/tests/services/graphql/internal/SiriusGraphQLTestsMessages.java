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

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Utility class containing the translated messages.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLTestsMessages {
    static {
        I18N.initializeMessages(SiriusGraphQLTestsMessages.class, SiriusGraphQLTestsPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String introspectionQuery;

    @TranslatableMessage
    public static String query;

    @TranslatableMessage
    public static String pageInfo;

    @TranslatableMessage
    public static String mutation;

    @TranslatableMessage
    public static String user;

    @TranslatableMessage
    public static String resource;

    @TranslatableMessage
    public static String container;

    @TranslatableMessage
    public static String project;

    @TranslatableMessage
    public static String folder;

    @TranslatableMessage
    public static String file;

    @TranslatableMessage
    public static String textFileCreationDescription;

    @TranslatableMessage
    public static String semanticFileCreationDescription;

    @TranslatableMessage
    public static String representationCreationDescription;

    @TranslatableMessage
    public static String viewpoint;

    @TranslatableMessage
    public static String representationDescription;

    @TranslatableMessage
    public static String diagramDescription;

    @TranslatableMessage
    public static String representation;

    @TranslatableMessage
    public static String diagram;

    @TranslatableMessage
    public static String eObject;

    @TranslatableMessage
    public static String eModelElement;

    @TranslatableMessage
    public static String eNamedElement;

    @TranslatableMessage
    public static String eClassifier;

    @TranslatableMessage
    public static String eClass;

    @TranslatableMessage
    public static String eTypedElement;

    @TranslatableMessage
    public static String eStructuralFeature;

    @TranslatableMessage
    public static String eAttribute;

    @TranslatableMessage
    public static String eReference;

    // CHECKSTYLE:ON

    private SiriusGraphQLTestsMessages() {
        // Prevents instantiation.
    }
}

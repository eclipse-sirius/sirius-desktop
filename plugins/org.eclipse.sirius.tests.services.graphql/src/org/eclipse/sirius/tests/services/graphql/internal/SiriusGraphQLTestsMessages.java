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
    public static String findProjectNameByName;

    @TranslatableMessage
    public static String findProjectNameByName_resultSample;

    @TranslatableMessage
    public static String findProjectPathByName;

    @TranslatableMessage
    public static String findProjectPathByName_resultSample;

    @TranslatableMessage
    public static String findProjectContainerByName;

    @TranslatableMessage
    public static String findProjectContainerByName_resultSample;

    @TranslatableMessage
    public static String findProjectProjectByName;

    @TranslatableMessage
    public static String findProjectProjectByName_resultSample;

    @TranslatableMessage
    public static String findProjectResourcesByName;

    @TranslatableMessage
    public static String findProjectResourcesByName_resultSample;

    @TranslatableMessage
    public static String findProjectDescriptionByName;

    @TranslatableMessage
    public static String findProjectDescriptionByName_resultSample;

    @TranslatableMessage
    public static String findProjectResourceByPathByName;

    @TranslatableMessage
    public static String findProjectResourceByPathByName_resultSample;

    @TranslatableMessage
    public static String findProjectActivatedViewpointsByName;

    @TranslatableMessage
    public static String findProjectActivatedViewpointsByName_resultSample;

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
    public static String fileCreationDescription;

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

    @TranslatableMessage
    public static String library;

    @TranslatableMessage
    public static String book;

    // CHECKSTYLE:ON

    private SiriusGraphQLTestsMessages() {
        // Prevents instantiation.
    }
}

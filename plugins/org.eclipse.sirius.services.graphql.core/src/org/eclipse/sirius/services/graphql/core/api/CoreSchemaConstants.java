/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.core.api;

import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider.EDGE_SUFFIX;

/**
 * Constants for the Core GraphQL schema.
 * 
 * @author sbegaudeau
 */
public final class CoreSchemaConstants {
    /**
     * The identifier of the schema.
     */
    public static final String IDENTIFIER = "org.eclipse.sirius.services.graphql.core"; //$NON-NLS-1$

    /**
     * The name of the Viewpoint type.
     */
    public static final String VIEWPOINT_TYPE = "Viewpoint"; //$NON-NLS-1$

    /**
     * The name of the RepresentationDescription type.
     */
    public static final String REPRESENTATION_DESCRIPTION_TYPE = "RepresentationDescription"; //$NON-NLS-1$

    /**
     * The name of the DiagramDescription type.
     */
    public static final String DIAGRAM_DESCRIPTION_TYPE = "DiagramDescription"; //$NON-NLS-1$

    /**
     * The name of the Representation type.
     */
    public static final String REPRESENTATION_TYPE = "Representation"; //$NON-NLS-1$

    /**
     * The name of the diagram type.
     */
    public static final String DIAGRAM_TYPE = "Diagram"; //$NON-NLS-1$

    /**
     * The name of the Viewpoint to RepresentationDescription connection type.
     */
    public static final String VIEWPOINT_REPRESENTATION_DESCRIPTION_CONNECTION_TYPE = VIEWPOINT_TYPE + REPRESENTATION_DESCRIPTION_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the Viewpoint to RepresentationDescription edge type.
     */
    public static final String VIEWPOINT_REPRESENTATION_DESCRIPTION_EDGE_TYPE = VIEWPOINT_TYPE + REPRESENTATION_DESCRIPTION_TYPE + EDGE_SUFFIX;

    /**
     * The constructor.
     */
    private CoreSchemaConstants() {
        // Prevent instantiation
    }
}

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
package org.eclipse.sirius.services.graphql.internal.schema;

import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnectionTypeProvider.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLEdgeTypeProvider.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants.REPRESENTATION_DESCRIPTION_TYPE;
import static org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants.REPRESENTATION_TYPE;
import static org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants.VIEWPOINT_TYPE;
import static org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants.EOBJECT_TYPE;
import static org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants.EPACKAGE_TYPE;
import static org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants.FILE_TYPE;
import static org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants.PROJECT_TYPE;

/**
 * Constants for the Core GraphQL schema.
 * 
 * @author sbegaudeau
 */
public final class SchemaConstants {
    /**
     * The name of the RepresentationDescription to EPackage connection type.
     */
    public static final String REPRESENTATION_DESCRIPTION_EPACKAGE_CONNECTION_TYPE = REPRESENTATION_DESCRIPTION_TYPE + EPACKAGE_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the RepresentationDescription to EPackage edge type.
     */
    public static final String REPRESENTATION_DESCRIPTION_EPACKAGE_EDGE_TYPE = REPRESENTATION_DESCRIPTION_TYPE + EPACKAGE_TYPE + EDGE_SUFFIX;

    /**
     * The name of the Project to Viewpoint connection type.
     */
    public static final String PROJECT_VIEWPOINT_CONNECTION_TYPE = PROJECT_TYPE + VIEWPOINT_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the Project to Viewpoint edge type.
     */
    public static final String PROJECT_VIEWPOINT_EDGE_TYPE = PROJECT_TYPE + VIEWPOINT_TYPE + EDGE_SUFFIX;

    /**
     * The name of the File to Representation connection type.
     */
    public static final String FILE_REPRESENTATION_CONNECTION_TYPE = FILE_TYPE + REPRESENTATION_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the File to Representation edge type.
     */
    public static final String FILE_REPRESENTATION_EDGE_TYPE = FILE_TYPE + REPRESENTATION_TYPE + EDGE_SUFFIX;

    /**
     * The name of the File to EObject connection type.
     */
    public static final String FILE_EOBJECT_CONNECTION_TYPE = FILE_TYPE + EOBJECT_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the File to EObject edge type.
     */
    public static final String FILE_EOBJECT_EDGE_TYPE = FILE_TYPE + EOBJECT_TYPE + EDGE_SUFFIX;

    /**
     * The constructor.
     */
    private SchemaConstants() {
        // Prevent instantiation
    }
}

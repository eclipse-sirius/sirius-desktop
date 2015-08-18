/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.profiler;

/**
 * Profiling tasks Key sused.
 * 
 * @author cbrun
 * 
 */
public final class SiriusTasksKey {

    /**
     * DDiagram category.
     */
    public static final String DIAGRAM_CAT = "DDiagram"; //$NON-NLS-1$

    /**
     * Acceleo category.
     */
    public static final String ACCELEO_CAT = "Acceleo"; //$NON-NLS-1$

    /**
     * Draw2D category.
     */
    public static final String DRAW2D_CAT = "Draw2D"; //$NON-NLS-1$

    /**
     * GEF category.
     */
    public static final String GEF_CAT = "GEF"; //$NON-NLS-1$

    /**
     * GMF category.
     */
    public static final String GMF_CAT = "GMF"; //$NON-NLS-1$

    /**
     * EMF category.
     */
    public static final String EMF_CAT = "EMF"; //$NON-NLS-1$

    /**
     * SWT category.
     */
    public static final String SWT_CAT = "SWT"; //$NON-NLS-1$

    /**
     * DTable category.
     */
    public static final String TABLE_CAT = "DTable"; //$NON-NLS-1$

    /**
     * DTree category.
     */
    public static final String TREE_CAT = "DTree"; //$NON-NLS-1$

    /**
     * Metamodel extension category.
     */
    public static final String MM_EXTENSION_CAT = "Metamodel Extension";

    /**
     * Modeler Category.
     */
    public static final String GENERIC_MODELER_CAT = "Generic Modeler";

    /**
     * Repair/migration Category.
     */
    public static final String REPAIR_MIGRATE = "Repair/migrate";

    /**
     * Task singleton.
     */
    public static final String CLEAN_DIAGRAM_KEY = "Cleaning a diagram";

    /**
     * Task singleton.
     */
    public static final String UPDATING_DIAGRAM_KEY = "Updating a diagram";

    /**
     * Task singleton.
     */
    public static final String EVALUATING_ACCELEO_KEY = "Evaluating Acceleo expressions";

    /**
     * Task singleton.
     */
    public static final String EVALUATING_OCL_KEY = "Evaluating OCL expressions";

    /**
     * Task singleton.
     */
    public static final String CHECK_PRECONDITION_KEY = "Check precondition expressions";

    /**
     * Task singleton.
     */
    public static final String INITIALIZE_ACCELEO_KEY = "Initialize Acceleo interpreter";

    /**
     * Task singleton.
     */
    public static final String UPDATE_ALL_EDGES_KEY = "Updating all edges";

    /**
     * Task singleton.
     */
    public static final String UPDATE_ALL_CONTAINERS_KEY = "Updating all containers";

    /**
     * Task singleton.
     */
    public static final String UPDATE_ALL_NODES_KEY = "Updating all nodes";

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_NODES_KEY = "Create missing node from viewpoint";

    /**
     * Task singleton.
     */
    public static final String IS_VISIBLE_KEY = "Display Service : is the element displayed ?";

    /**
     * Task singleton.
     * 
     * @since 0.9.0
     */
    public static final String REFRESH_VISIBILITY_KEY = "Display Service : check that the element has to be displayed";

    /**
     * Task singleton.
     */
    public static final String IS_COLLAPSED_KEY = "FilterService : is the element collapsed ?";

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_NODES_CONTAINER_KEY = "Create missing node from container";

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_EDGES_KEY = "Create missing edges from viewpoint";

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_CONTAINER_KEY = "Create missing containers from viewpoint";

    /**
     * Task singleton.
     */
    public static final String REFRESH_DIAGRAM_KEY = "Refresh a diagram";

    /**
     * Task singleton.
     */
    public static final String CLEANING_REMOVEDANGLING_KEY = "Cleaning : remove dangling references";

    /**
     * Task singleton.
     */
    public static final String CLEANING_EDGES_KEY = "Cleaning all edges";

    /**
     * Task singleton.
     */
    public static final String VALIDATE_NODE_KEY = "Validating the node";

    /**
     * Task singleton.
     */
    public static final String VALIDATE_EDGE_KEY = "Validating the edge";

    /**
     * Task singleton.
     */
    public static final String GET_NODE_CANDIDATES_KEY = "Get node's candidates";

    /**
     * Task singleton.
     */
    public static final String GET_EDGE_CANDIDATES_KEY = "Get edge's candidates";

    /**
     * Task singleton.
     */
    public static final String INSTANCE_OF_KEY = "EInstance of ";

    /**
     * Task singleton.
     */
    public static final String GET_CONTAINER_CANDIDATES_KEY = "Get container candidates";

    /**
     * Task singleton.
     */
    public static final String IS_FOLD_KEY = "Is fold";

    /**
     * Task singleton.
     */
    public static final String CACHE_ACCESS_KEY = "Cache access";

    /**
     * Task singleton.
     */
    public static final String VALIDATE_VIEWPOINT_KEY = "Validate all the DDiagram elements";

    /**
     * Task singleton.
     */
    public static final String REFRESH_GEF_KEY = "Big refresh";

    /**
     * Task singleton.
     */
    public static final String CANONICAL_REFRESH_KEY = "Canonical refresh";

    /**
     * Task singleton.
     */
    public static final String LAUCNH_REFRESH_FROM_LISTENER_KEY = "Launch Refresh From Operation History Listener";

    /**
     * Task singleton.
     */
    public static final String LAUNCH_REFRESH_FROM_LAYER_CHANGE_KEY = "Launch Refresh From Layer Change";

    /**
     * Task to check if a notation view is valid.
     */
    public static final String IS_VIEW_VALID_KEY = "Is the GMF view valid ?";

    /**
     * Task EMF resolve all.
     */
    public static final String RESOLVE_ALL_KEY = "Resolve All";

    /**
     * Task finding the .air from any model element.
     */
    public static final String GET_ACCELEO_INTERPRETER_FROM_MODEL_KEY = "Find .air from model element.";

    /**
     * Task finding new candidates on the list.
     */
    public static final String GET_ADDED_NODE_CANDIDATES_KEY = "Get Added nodes candidates";

    /**
     * Task finding kept candidates on the list.
     */
    public static final String GET_KEPT_NODE_CANDIDATES_KEY = "Get kept nodes candidates";

    /**
     * Task finding removed candidates in the list.
     */
    public static final String GET_REMOVED_NODE_CANDIDATES_KEY = "Get removed nodes candidates";

    /**
     * When retrieving candidates target views.
     */
    public static final String COMPUTE_TARGET_ELEMENTS_KEY = "Compute edge source/target views";

    /**
     * When cleaning diagram.
     */
    public static final String CLEAN_ORPHANED_NODE_KEY = "Clean orphaned nodes";

    /**
     * When removing dangling reference.
     */
    public static final String REMOVE_DANGLING_REFERENCE_KEY = "Remove dangling reference";

    /**
     * When opening diagram.
     */
    public static final String OPEN_DIAGRAM_KEY = "Open diagram";

    /**
     * The action that load the aird resource and open the session.
     */
    public static final String OPEN_SESSION_ACTION_KEY = "Open Session Action";

    /**
     * When opening session.
     */
    public static final String OPEN_SESSION_KEY = "Open Session";

    /**
     * Semantic refresh.
     */
    public static final String SEMANTIC_REFRESH_KEY = "Semantic Refresh";

    /**
     * Semantic refresh.
     */
    public static final String SYNCHRONIZE_DIAGRAM_KEY = "Synchronize Diagram";

    /**
     * When refreshing the table.
     */
    public static final String REFRESH_TABLE_KEY = "Refresh a table";

    /**
     * When refreshing the tree.
     */
    public static final String REFRESH_TREE_KEY = "Refresh a tree";

    /**
     * When creating the SWT.table.
     */
    public static final String CREATE_TABLE_KEY = "Create the SWT table";

    /**
     * When refreshing the table.
     */
    public static final String REFRESH_SWT_TABLE_KEY = "Refresh the SWT table";

    /**
     * When refreshing a line of the table.
     */
    public static final String REFRESH_SWT_LINE_KEY = "Refresh a line of the SWT table";

    /**
     * When updating a line of the table.
     */
    public static final String UPDATE_SWT_LINE_KEY = "Update a line of the SWT table";

    /**
     * When collapsing or expanding a line of the table.
     */
    @Deprecated
    public static final String CHANGE_SWT_LINE_COLAPSE_STATE_KEY = "Change the collapse state of a line of the SWT table";
    
    /**
     * When collapsing or expanding a line of the table.
     */
    public static final String CHANGE_SWT_LINE_COLLAPSE_STATE_KEY = "Change the collapse state of a line of the SWT table";

    /**
     * When refreshing a line of the table.
     */
    public static final String ADD_SWT_COLUMN_KEY = "Add a column in the SWT table";

    /**
     * When setting the name of a column.
     */
    public static final String SET_COLUMN_NAME_KEY = "Set the column name in the SWT table";

    /**
     * When refreshing the properties.
     */
    public static final String REFRESH_PROPERTIES_VIEW_KEY = "Refresh the properties view";

    /**
     * When refreshing the properties.
     */
    public static final String REFRESH_PROPERTIES_VIEW_SECTION_KEY = "Refresh a section of the properties view";

    /**
     * When opening table.
     */
    public static final String OPEN_TABLE_KEY = "Open table";

    /**
     * When opening tree.
     */
    public static final String OPEN_TREE_KEY = "Open tree";

    /**
     * When showing or hiding a line of the table.
     */
    public static final String CHANGE_SWT_LINE_VISIBLE_STATE_KEY = "Change the visible state of a line of the SWT table";

    /**
     * When showing or hiding a line of the table.
     */
    public static final String CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY = "Change the visible state of a column of the SWT table";

    /**
     * When showing or hiding a line of the table.
     */
    public static final String REFRESH_LOST_ELEMENTS_KEY = "Refresh elements lost by repair/migrate first pass";

    /**
     * When loading an aird file.
     */
    public static final String LOAD_AIRD_KEY = "Load aird file";

    /**
     * avoid instanciation
     */
    private SiriusTasksKey() {

    }
}

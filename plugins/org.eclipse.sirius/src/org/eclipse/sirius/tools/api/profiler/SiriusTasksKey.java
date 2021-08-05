/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.profiler;

import org.eclipse.sirius.tools.api.Messages;

/**
 * Profiling tasks Key sused.
 * 
 * @author cbrun
 * 
 */
public final class SiriusTasksKey {
    // CHECKSTYLE:OFF
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
    public static final String MM_EXTENSION_CAT = Messages.SiriusTasksKey_mmExtension;

    /**
     * Modeler Category.
     */
    public static final String GENERIC_MODELER_CAT = Messages.SiriusTasksKey_genericModeler;

    /**
     * Repair/migration Category.
     */
    public static final String REPAIR_MIGRATE = Messages.SiriusTasksKey_repairMigrate;

    /**
     * Task singleton.
     */
    public static final String CLEAN_DIAGRAM_KEY = Messages.SiriusTasksKey_cleaningADiagram;

    /**
     * Task singleton.
     */
    public static final String UPDATING_DIAGRAM_KEY = Messages.SiriusTasksKey_updatingADiagram;

    /**
     * Task singleton.
     */
    public static final String EVALUATING_ACCELEO_KEY = Messages.SiriusTasksKey_evaluatingAcceleoExpression;

    /**
     * Task singleton.
     */
    public static final String EVALUATING_OCL_KEY = Messages.SiriusTasksKey_evaluatingOCLExpressions;

    /**
     * Task singleton.
     */
    public static final String CHECK_PRECONDITION_KEY = Messages.SiriusTasksKey_checkPreconditionExpressions;

    /**
     * Task singleton.
     */
    public static final String INITIALIZE_ACCELEO_KEY = Messages.SiriusTasksKey_InitAcceleoInterpreter;

    /**
     * Task singleton.
     */
    public static final String UPDATE_ALL_EDGES_KEY = Messages.SiriusTasksKey_updatingAllEdges;

    /**
     * Task singleton.
     */
    public static final String UPDATE_ALL_CONTAINERS_KEY = Messages.SiriusTasksKey_updateAllContainers;

    /**
     * Task singleton.
     */
    public static final String UPDATE_ALL_NODES_KEY = Messages.SiriusTasksKey_updatingAllNodes;

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_NODES_KEY = Messages.SiriusTasksKey_createMissingNodeFromViewpoint;

    /**
     * Task singleton.
     */
    public static final String IS_VISIBLE_KEY = Messages.SiriusTasksKey_isElementDisplayed;

    /**
     * Task singleton.
     * 
     * @since 0.9.0
     */
    public static final String REFRESH_VISIBILITY_KEY = Messages.SiriusTasksKey_checkThatElementHasToBeDisplayed;

    /**
     * Task singleton.
     */
    public static final String IS_COLLAPSED_KEY = Messages.SiriusTasksKey_isTheElementCollapsed;

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_NODES_CONTAINER_KEY = Messages.SiriusTasksKey_createMissingNodeFromContainer;

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_EDGES_KEY = Messages.SiriusTasksKey_createMissingEdgesFromViewpoint;

    /**
     * Task singleton.
     */
    public static final String CREATE_MISSING_CONTAINER_KEY = Messages.SiriusTasksKey_createMissingContainersFromViewpoint;

    /**
     * Task singleton.
     */
    public static final String REFRESH_DIAGRAM_KEY = Messages.SiriusTasksKey_refreshADiagram;

    /**
     * Task singleton.
     */
    public static final String CLEANING_REMOVEDANGLING_KEY = Messages.SiriusTasksKey_CleaningRemoveDanglingRef;

    /**
     * Task singleton.
     */
    public static final String CLEANING_EDGES_KEY = Messages.SiriusTasksKey_cleaningAllEdges;

    /**
     * Task singleton.
     */
    public static final String VALIDATE_NODE_KEY = Messages.SiriusTasksKey_validatingTheNode;

    /**
     * Task singleton.
     */
    public static final String VALIDATE_EDGE_KEY = Messages.SiriusTasksKey_validatingEdge;

    /**
     * Task singleton.
     */
    public static final String GET_NODE_CANDIDATES_KEY = Messages.SiriusTasksKey_getNodesCandidates;

    /**
     * Task singleton.
     */
    public static final String GET_EDGE_CANDIDATES_KEY = Messages.SiriusTasksKey_getEdgesCandidates;

    /**
     * Task singleton.
     */
    public static final String INSTANCE_OF_KEY = Messages.SiriusTasksKey_instanceOf;

    /**
     * Task singleton.
     */
    public static final String GET_CONTAINER_CANDIDATES_KEY = Messages.SiriusTasksKey_getContainerCandidates;

    /**
     * Task singleton.
     */
    public static final String IS_FOLD_KEY = Messages.SiriusTasksKey_isFold;

    /**
     * Task singleton.
     */
    public static final String CACHE_ACCESS_KEY = Messages.SiriusTasksKey_cacheAccess;

    /**
     * Task singleton.
     */
    public static final String VALIDATE_VIEWPOINT_KEY = Messages.SiriusTasksKey_validateAllDDiagramElements;

    /**
     * Task singleton.
     */
    public static final String REFRESH_GEF_KEY = Messages.SiriusTasksKey_bigRefresh;

    /**
     * Task singleton.
     */
    public static final String CANONICAL_REFRESH_KEY = Messages.SiriusTasksKey_canonicalRefresh;

    /**
     * Task singleton.
     */
    public static final String LAUCNH_REFRESH_FROM_LISTENER_KEY = Messages.SiriusTasksKey_launchRefreshFromOperationHistoryListener;

    /**
     * Task singleton.
     */
    public static final String LAUNCH_REFRESH_FROM_LAYER_CHANGE_KEY = Messages.SiriusTasksKey_launchRefreshFromLayerChange;

    /**
     * Task to check if a notation view is valid.
     */
    public static final String IS_VIEW_VALID_KEY = Messages.SiriusTasksKey_isGMFViewValid;

    /**
     * Task EMF resolve all.
     */
    public static final String RESOLVE_ALL_KEY = Messages.SiriusTasksKey_resolveAll;

    /**
     * Task finding the .air from any model element.
     */
    public static final String GET_ACCELEO_INTERPRETER_FROM_MODEL_KEY = Messages.SiriusTasksKey_findAirFromModelElement;

    /**
     * Task finding new candidates on the list.
     */
    public static final String GET_ADDED_NODE_CANDIDATES_KEY = Messages.SiriusTasksKey_getAddedNodesCandidates;

    /**
     * Task finding kept candidates on the list.
     */
    public static final String GET_KEPT_NODE_CANDIDATES_KEY = Messages.SiriusTasksKey_getKeptNodesCandidates;

    /**
     * Task finding removed candidates in the list.
     */
    public static final String GET_REMOVED_NODE_CANDIDATES_KEY = Messages.SiriusTasksKey_getRemovedNodesCandidates;

    /**
     * When retrieving candidates target views.
     */
    public static final String COMPUTE_TARGET_ELEMENTS_KEY = Messages.SiriusTasksKey_computeEdgeSrcTgtViews;

    /**
     * When cleaning diagram.
     */
    public static final String CLEAN_ORPHANED_NODE_KEY = Messages.SiriusTasksKey_cleanOrphanedNodes;

    /**
     * When removing dangling reference.
     */
    public static final String REMOVE_DANGLING_REFERENCE_KEY = Messages.SiriusTasksKey_removeDanglingRef;

    /**
     * When opening diagram.
     */
    public static final String OPEN_DIAGRAM_KEY = Messages.SiriusTasksKey_openDiagram;

    /**
     * The action that load the aird resource and open the session.
     */
    public static final String OPEN_SESSION_ACTION_KEY = Messages.SiriusTasksKey_openSessionAction;

    /**
     * When opening session.
     */
    public static final String OPEN_SESSION_KEY = Messages.SiriusTasksKey_openSession;

    /**
     * Semantic refresh.
     */
    public static final String SEMANTIC_REFRESH_KEY = Messages.SiriusTasksKey_semanticRefresh;

    /**
     * Semantic refresh.
     */
    public static final String SYNCHRONIZE_DIAGRAM_KEY = Messages.SiriusTasksKey_synchronizeDiagram;

    /**
     * When refreshing the table.
     */
    public static final String REFRESH_TABLE_KEY = Messages.SiriusTasksKey_refreshTable;

    /**
     * When refreshing the tree.
     */
    public static final String REFRESH_TREE_KEY = Messages.SiriusTasksKey_refreshATree;

    /**
     * When creating the SWT.table.
     */
    public static final String CREATE_TABLE_KEY = Messages.SiriusTasksKey_createSWTTable;

    /**
     * When refreshing the table.
     */
    public static final String REFRESH_SWT_TABLE_KEY = Messages.SiriusTasksKey_refreshSWTTable;

    /**
     * When refreshing a line of the table.
     */
    public static final String REFRESH_SWT_LINE_KEY = Messages.SiriusTasksKey_refreshSWTTableLine;

    /**
     * When updating a line of the table.
     */
    public static final String UPDATE_SWT_LINE_KEY = Messages.SiriusTasksKey_updateSWTTableLine;

    /**
     * When collapsing or expanding a line of the table.
     */
    @Deprecated
    public static final String CHANGE_SWT_LINE_COLAPSE_STATE_KEY = Messages.SiriusTasksKey_changeSWTTableCollapseState;

    /**
     * When collapsing or expanding a line of the table.
     */
    public static final String CHANGE_SWT_LINE_COLLAPSE_STATE_KEY = Messages.SiriusTasksKey_changeSWTTableCollapseState;

    /**
     * When refreshing a line of the table.
     */
    public static final String ADD_SWT_COLUMN_KEY = Messages.SiriusTasksKey_addAColumnInSWTTable;

    /**
     * When setting the name of a column.
     */
    public static final String SET_COLUMN_NAME_KEY = Messages.SiriusTasksKey_setColumnNameInSWTTable;

    /**
     * When refreshing the properties.
     */
    public static final String REFRESH_PROPERTIES_VIEW_KEY = Messages.SiriusTasksKey_refreshPropertiesView;

    /**
     * When refreshing the properties.
     */
    public static final String REFRESH_PROPERTIES_VIEW_SECTION_KEY = Messages.SiriusTasksKey_refreshPropertiesViewSection;

    /**
     * When opening table.
     */
    public static final String OPEN_TABLE_KEY = Messages.SiriusTasksKey_openTable;

    /**
     * When opening tree.
     */
    public static final String OPEN_TREE_KEY = Messages.SiriusTasksKey_openTree;

    /**
     * When showing or hiding a line of the table.
     */
    public static final String CHANGE_SWT_LINE_VISIBLE_STATE_KEY = Messages.SiriusTasksKey_changeSWTTableLineVisibleSate;

    /**
     * When showing or hiding a line of the table.
     */
    public static final String CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY = Messages.SiriusTasksKey_changeSWTTableColumnVisibleState;

    /**
     * When showing or hiding a line of the table.
     */
    public static final String REFRESH_LOST_ELEMENTS_KEY = Messages.SiriusTasksKey_refreshRepairMigrateLostElements;

    /**
     * When loading an aird file.
     */
    public static final String LOAD_AIRD_KEY = Messages.SiriusTasksKey_loadAirdFile;

    /**
     * avoid instanciation
     */
    private SiriusTasksKey() {

    }
}

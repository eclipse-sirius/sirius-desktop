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
package org.eclipse.sirius.ui.tools.api.profiler;

import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Profiling tasks used.
 * 
 * @author cbrun
 * 
 */
public final class SiriusTasks {

    /**
     * The Acceleo category image path.
     */
    public static final String IMAGES_ACCELEO_GIF = "/images/acceleo.gif"; //$NON-NLS-1$

    /**
     * The GEF category image path.
     */
    public static final String IMAGES_GEF_GIF = "/images/gef.gif"; //$NON-NLS-1$

    /**
     * The GMF category image path.
     */
    public static final String IMAGES_GMF_GIF = "/images/gmf.gif"; //$NON-NLS-1$

    /**
     * Image used to represent the viewpoint category.
     */
    public static final String IMAGES_VIEWPOINT = "/images/viewpoint.gif"; //$NON-NLS-1$

    /**
     * Image used to represent the generic modeler category.
     */
    public static final String IMAGES_MODELER = "/images/modeler.gif"; //$NON-NLS-1$

    /**
     * The SWT category image path.
     */
    public static final String IMAGES_SWT = "/images/swt.gif"; //$NON-NLS-1$

    /**
     * Task singleton.
     */
    public static final ProfilerTask CLEAN_DIAGRAM = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CLEAN_DIAGRAM_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask UPDATING_DIAGRAM = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.UPDATING_DIAGRAM_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask EVALUATING_ACCELEO = new ProfilerTask(SiriusTasksKey.ACCELEO_CAT, SiriusTasksKey.EVALUATING_ACCELEO_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask EVALUATING_OCL = new ProfilerTask(SiriusTasksKey.EMF_CAT, SiriusTasksKey.EVALUATING_OCL_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CHECK_PRECONDITION = new ProfilerTask(SiriusTasksKey.ACCELEO_CAT, SiriusTasksKey.CHECK_PRECONDITION_KEY, getImagePath(IMAGES_ACCELEO_GIF));

    /**
     * Task singleton.
     */
    public static final ProfilerTask INITIALIZE_ACCELEO = new ProfilerTask(SiriusTasksKey.ACCELEO_CAT, SiriusTasksKey.INITIALIZE_ACCELEO_KEY, getImagePath(IMAGES_ACCELEO_GIF));

    /**
     * Task singleton.
     */
    public static final ProfilerTask UPDATE_ALL_EDGES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.UPDATE_ALL_EDGES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask UPDATE_ALL_CONTAINERS = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.UPDATE_ALL_CONTAINERS_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask UPDATE_ALL_NODES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.UPDATE_ALL_NODES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CREATE_MISSING_NODES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CREATE_MISSING_NODES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask IS_VISIBLE = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.IS_VISIBLE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     * 
     * @since 0.9.0
     */
    public static final ProfilerTask REFRESH_VISIBILITY = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.REFRESH_VISIBILITY_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask IS_COLLAPSED = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.IS_COLLAPSED_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CREATE_MISSING_NODES_CONTAINER = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CREATE_MISSING_NODES_CONTAINER_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CREATE_MISSING_EDGES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CREATE_MISSING_EDGES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CREATE_MISSING_CONTAINER = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CREATE_MISSING_CONTAINER_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask REFRESH_DIAGRAM = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.REFRESH_DIAGRAM_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CLEANING_REMOVEDANGLING = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CLEANING_REMOVEDANGLING_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CLEANING_EDGES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CLEANING_EDGES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask VALIDATE_NODE = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.VALIDATE_NODE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask VALIDATE_EDGE = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.VALIDATE_EDGE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask GET_NODE_CANDIDATES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_NODE_CANDIDATES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask GET_EDGE_CANDIDATES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_EDGE_CANDIDATES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask INSTANCE_OF = new ProfilerTask(SiriusTasksKey.MM_EXTENSION_CAT, SiriusTasksKey.INSTANCE_OF_KEY, getImagePath("/images/mmext.gif")); //$NON-NLS-1$

    /**
     * Task singleton.
     */
    public static final ProfilerTask GET_CONTAINER_CANDIDATES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_CONTAINER_CANDIDATES_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask IS_FOLD = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.IS_FOLD_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CACHE_ACCESS = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.CACHE_ACCESS_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask VALIDATE_VIEWPOINT = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.VALIDATE_VIEWPOINT_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * Task singleton.
     */
    public static final ProfilerTask REFRESH_GEF = new ProfilerTask(SiriusTasksKey.GEF_CAT, SiriusTasksKey.REFRESH_GEF_KEY, getImagePath(IMAGES_GEF_GIF));

    /**
     * Task singleton.
     */
    public static final ProfilerTask CANONICAL_REFRESH = new ProfilerTask(SiriusTasksKey.GMF_CAT, SiriusTasksKey.CANONICAL_REFRESH_KEY, getImagePath(IMAGES_GMF_GIF));

    /**
     * Task singleton.
     */
    public static final ProfilerTask LAUCNH_REFRESH_FROM_LISTENER = new ProfilerTask(SiriusTasksKey.GMF_CAT, SiriusTasksKey.LAUCNH_REFRESH_FROM_LISTENER_KEY, getImagePath(IMAGES_GMF_GIF));

    /**
     * Task singleton.
     */
    public static final ProfilerTask LAUNCH_REFRESH_FROM_LAYER_CHANGE = new ProfilerTask(SiriusTasksKey.GMF_CAT, SiriusTasksKey.LAUNCH_REFRESH_FROM_LAYER_CHANGE_KEY, getImagePath(IMAGES_GMF_GIF));

    /**
     * Task to check if a notation view is valid.
     */
    public static final ProfilerTask IS_VIEW_VALID = new ProfilerTask(SiriusTasksKey.GMF_CAT, SiriusTasksKey.IS_VIEW_VALID_KEY, getImagePath(IMAGES_GMF_GIF));

    /**
     * Task EMF resolve all.
     */
    public static final ProfilerTask RESOLVE_ALL = new ProfilerTask(SiriusTasksKey.EMF_CAT, SiriusTasksKey.RESOLVE_ALL_KEY);

    /**
     * Task finding the .air from any model element.
     */
    public static final ProfilerTask GET_ACCELEO_INTERPRETER_FROM_MODEL = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_ACCELEO_INTERPRETER_FROM_MODEL_KEY);

    /**
     * Task finding new candidates on the list.
     */
    public static final ProfilerTask GET_ADDED_NODE_CANDIDATES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_ADDED_NODE_CANDIDATES_KEY);

    /**
     * Task finding kept candidates on the list.
     */
    public static final ProfilerTask GET_KEPT_NODE_CANDIDATES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_KEPT_NODE_CANDIDATES_KEY);

    /**
     * Task finding removed candidates in the list.
     */
    public static final ProfilerTask GET_REMOVED_NODE_CANDIDATES = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.GET_REMOVED_NODE_CANDIDATES_KEY);

    /**
     * When retrieving candidates target views.
     */
    public static final ProfilerTask COMPUTE_TARGET_ELEMENTS = new ProfilerTask(SiriusTasksKey.DIAGRAM_CAT, SiriusTasksKey.COMPUTE_TARGET_ELEMENTS_KEY);

    /**
     * When cleaning diagram.
     */
    public static final ProfilerTask CLEAN_ORPHANED_NODE = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.CLEAN_ORPHANED_NODE_KEY, getImagePath(IMAGES_MODELER));

    /**
     * When removing dangling reference.
     */
    public static final ProfilerTask REMOVE_DANGLING_REFERENCE = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.REMOVE_DANGLING_REFERENCE_KEY, getImagePath(IMAGES_MODELER));

    /**
     * When opening diagram.
     */
    public static final ProfilerTask OPEN_DIAGRAM = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.OPEN_DIAGRAM_KEY, getImagePath(IMAGES_MODELER));

    /**
     * When opening session.
     */
    public static final ProfilerTask OPEN_SESSION = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.OPEN_SESSION_KEY, getImagePath(IMAGES_MODELER));

    /**
     * The action that load the aird resource and open the session.
     */
    public static final ProfilerTask OPEN_SESSION_ACTION = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.OPEN_SESSION_ACTION_KEY, getImagePath(IMAGES_MODELER));

    /**
     * Semantic refresh.
     */
    public static final ProfilerTask SEMANTIC_REFRESH = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.SEMANTIC_REFRESH_KEY, getImagePath(IMAGES_MODELER));

    /**
     * Semantic refresh.
     */
    public static final ProfilerTask SYNCHRONIZE_DIAGRAM = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.SYNCHRONIZE_DIAGRAM_KEY, getImagePath(IMAGES_MODELER));

    /**
     * When refreshing the table.
     */
    public static final ProfilerTask REFRESH_TABLE = new ProfilerTask(SiriusTasksKey.TABLE_CAT, SiriusTasksKey.REFRESH_TABLE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * When refreshing the tree.
     */
    public static final ProfilerTask REFRESH_TREE = new ProfilerTask(SiriusTasksKey.TREE_CAT, SiriusTasksKey.REFRESH_TREE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * When creating the SWT.table.
     */
    public static final ProfilerTask CREATE_TABLE = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.CREATE_TABLE_KEY, getImagePath(IMAGES_SWT));

    /**
     * When refreshing the table.
     */
    public static final ProfilerTask REFRESH_SWT_TABLE = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.REFRESH_SWT_TABLE_KEY, getImagePath(IMAGES_SWT));

    /**
     * When refreshing a line of the table.
     */
    public static final ProfilerTask REFRESH_SWT_LINE = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.REFRESH_SWT_LINE_KEY, getImagePath(IMAGES_SWT));

    /**
     * When updating a line of the table.
     */
    public static final ProfilerTask UPDATE_SWT_LINE = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.UPDATE_SWT_LINE_KEY, getImagePath(IMAGES_SWT));

    /**
     * When collapsing or expanding a line of the table.
     */
    public static final ProfilerTask CHANGE_SWT_LINE_COLAPSE_STATE = new ProfilerTask(SiriusTasksKey.SWT_CAT, "Change the collapse state of a line of the SWT table", getImagePath(IMAGES_SWT));

    /**
     * When refreshing a line of the table.
     */
    public static final ProfilerTask ADD_SWT_COLUMN = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.ADD_SWT_COLUMN_KEY, getImagePath(IMAGES_SWT));

    /**
     * When setting the name of a column.
     */
    public static final ProfilerTask SET_COLUMN_NAME = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.SET_COLUMN_NAME_KEY, getImagePath(IMAGES_SWT));

    /**
     * When refreshing the properties.
     */
    public static final ProfilerTask REFRESH_PROPERTIES_VIEW = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.REFRESH_PROPERTIES_VIEW_KEY, getImagePath(IMAGES_SWT));

    /**
     * When refreshing the properties.
     */
    public static final ProfilerTask REFRESH_PROPERTIES_VIEW_SECTION = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.REFRESH_PROPERTIES_VIEW_SECTION_KEY, getImagePath(IMAGES_SWT));

    /**
     * When opening table.
     */
    public static final ProfilerTask OPEN_TABLE = new ProfilerTask(SiriusTasksKey.TABLE_CAT, SiriusTasksKey.OPEN_TABLE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * When opening tree.
     */
    public static final ProfilerTask OPEN_TREE = new ProfilerTask(SiriusTasksKey.TREE_CAT, SiriusTasksKey.OPEN_TREE_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * When showing or hiding a line of the table.
     */
    public static final ProfilerTask CHANGE_SWT_LINE_VISIBLE_STATE = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.CHANGE_SWT_LINE_VISIBLE_STATE_KEY, getImagePath(IMAGES_SWT));

    /**
     * When showing or hiding a line of the table.
     */
    public static final ProfilerTask CHANGE_SWT_COLUMN_VISIBLE_STATE = new ProfilerTask(SiriusTasksKey.SWT_CAT, SiriusTasksKey.CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY, getImagePath(IMAGES_SWT));

    /**
     * When showing or hiding a line of the table.
     */
    public static final ProfilerTask REFRESH_LOST_ELEMENTS = new ProfilerTask(SiriusTasksKey.REPAIR_MIGRATE, SiriusTasksKey.REFRESH_LOST_ELEMENTS_KEY, getImagePath(IMAGES_VIEWPOINT));

    /**
     * When loading an aird file.
     */
    public static final ProfilerTask LOAD_AIRD = new ProfilerTask(SiriusTasksKey.GENERIC_MODELER_CAT, SiriusTasksKey.LOAD_AIRD_KEY, getImagePath(IMAGES_MODELER));

    /**
     * Avoid instanciation.
     */
    private SiriusTasks() {
    }

    private static String getImagePath(String key) {
        return "/" + SiriusEditPlugin.ID + key; //$NON-NLS-1$
    }

    // CHECKSTYLE:OFF
    public static void initSiriusTasks() throws IllegalArgumentException {
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CLEAN_DIAGRAM.getName(), CLEAN_DIAGRAM);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(UPDATING_DIAGRAM.getName(), UPDATING_DIAGRAM);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(EVALUATING_ACCELEO.getName(), EVALUATING_ACCELEO);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(EVALUATING_OCL.getName(), EVALUATING_OCL);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CHECK_PRECONDITION.getName(), CHECK_PRECONDITION);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(INITIALIZE_ACCELEO.getName(), INITIALIZE_ACCELEO);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(UPDATE_ALL_EDGES.getName(), UPDATE_ALL_EDGES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(UPDATE_ALL_CONTAINERS.getName(), UPDATE_ALL_CONTAINERS);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(UPDATE_ALL_NODES.getName(), UPDATE_ALL_NODES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CREATE_MISSING_NODES.getName(), CREATE_MISSING_NODES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(IS_VISIBLE.getName(), IS_VISIBLE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_VISIBILITY.getName(), REFRESH_VISIBILITY);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(IS_COLLAPSED.getName(), IS_COLLAPSED);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CREATE_MISSING_NODES_CONTAINER.getName(), CREATE_MISSING_NODES_CONTAINER);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CREATE_MISSING_EDGES.getName(), CREATE_MISSING_EDGES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CREATE_MISSING_CONTAINER.getName(), CREATE_MISSING_CONTAINER);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_DIAGRAM.getName(), REFRESH_DIAGRAM);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CLEANING_REMOVEDANGLING.getName(), CLEANING_REMOVEDANGLING);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CLEANING_EDGES.getName(), CLEANING_EDGES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(VALIDATE_NODE.getName(), VALIDATE_NODE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(VALIDATE_EDGE.getName(), VALIDATE_EDGE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_NODE_CANDIDATES.getName(), GET_NODE_CANDIDATES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_EDGE_CANDIDATES.getName(), GET_EDGE_CANDIDATES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(INSTANCE_OF.getName(), INSTANCE_OF);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_CONTAINER_CANDIDATES.getName(), GET_CONTAINER_CANDIDATES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(IS_FOLD.getName(), IS_FOLD);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CACHE_ACCESS.getName(), CACHE_ACCESS);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(VALIDATE_VIEWPOINT.getName(), VALIDATE_VIEWPOINT);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_GEF.getName(), REFRESH_GEF);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CANONICAL_REFRESH.getName(), CANONICAL_REFRESH);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(LAUCNH_REFRESH_FROM_LISTENER.getName(), LAUCNH_REFRESH_FROM_LISTENER);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(LAUNCH_REFRESH_FROM_LAYER_CHANGE.getName(), LAUNCH_REFRESH_FROM_LAYER_CHANGE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(IS_VIEW_VALID.getName(), IS_VIEW_VALID);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(RESOLVE_ALL.getName(), RESOLVE_ALL);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_ACCELEO_INTERPRETER_FROM_MODEL.getName(), GET_ACCELEO_INTERPRETER_FROM_MODEL);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_ADDED_NODE_CANDIDATES.getName(), GET_ADDED_NODE_CANDIDATES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_KEPT_NODE_CANDIDATES.getName(), GET_KEPT_NODE_CANDIDATES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(GET_REMOVED_NODE_CANDIDATES.getName(), GET_REMOVED_NODE_CANDIDATES);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(COMPUTE_TARGET_ELEMENTS.getName(), COMPUTE_TARGET_ELEMENTS);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CLEAN_ORPHANED_NODE.getName(), CLEAN_ORPHANED_NODE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REMOVE_DANGLING_REFERENCE.getName(), REMOVE_DANGLING_REFERENCE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(OPEN_DIAGRAM.getName(), OPEN_DIAGRAM);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(OPEN_SESSION.getName(), OPEN_SESSION);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(OPEN_SESSION_ACTION.getName(), OPEN_SESSION_ACTION);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(SEMANTIC_REFRESH.getName(), SEMANTIC_REFRESH);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(SYNCHRONIZE_DIAGRAM.getName(), SYNCHRONIZE_DIAGRAM);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_TABLE.getName(), REFRESH_TABLE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_TREE.getName(), REFRESH_TREE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CREATE_TABLE.getName(), CREATE_TABLE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_SWT_TABLE.getName(), REFRESH_SWT_TABLE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_SWT_LINE.getName(), REFRESH_SWT_LINE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(UPDATE_SWT_LINE.getName(), UPDATE_SWT_LINE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CHANGE_SWT_LINE_COLAPSE_STATE.getName(), CHANGE_SWT_LINE_COLAPSE_STATE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(ADD_SWT_COLUMN.getName(), ADD_SWT_COLUMN);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(SET_COLUMN_NAME.getName(), SET_COLUMN_NAME);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_PROPERTIES_VIEW.getName(), REFRESH_PROPERTIES_VIEW);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_PROPERTIES_VIEW_SECTION.getName(), REFRESH_PROPERTIES_VIEW_SECTION);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(OPEN_TABLE.getName(), OPEN_TABLE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(OPEN_TREE.getName(), OPEN_TREE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CHANGE_SWT_LINE_VISIBLE_STATE.getName(), CHANGE_SWT_LINE_VISIBLE_STATE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(CHANGE_SWT_COLUMN_VISIBLE_STATE.getName(), CHANGE_SWT_COLUMN_VISIBLE_STATE);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(REFRESH_LOST_ELEMENTS.getName(), REFRESH_LOST_ELEMENTS);
        TimeProfiler.PROFILER_TASK_REGISTRY.put(LOAD_AIRD.getName(), LOAD_AIRD);
    }

    // CHECKSTYLE:ON
}

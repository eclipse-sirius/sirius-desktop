/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal;

/**
 * Defines the constants used throughout the interpreter plugin.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public interface IInterpreterConstants {
    /** Path to the icon for the "clear" action. */
    String CLEAR_ACTION_ICON = "clear_action.gif"; //$NON-NLS-1$

    /** Path to the icon for the "sort" action. */
    String SORT_ACTION_ICON = "lexical_sort.gif"; //$NON-NLS-1$

    /** Path to the icon for the "delete" action when disabled. */
    String DELETE_ACTION_DISABLED_ICON = "delete_action_disabled.gif"; //$NON-NLS-1$

    /** Path to the icon for the "delete" action when enabled. */
    String DELETE_ACTION_ICON = "delete_action.gif"; //$NON-NLS-1$

    /** Path to the icon for the "evaluate" action. */
    String EVALUATE_ACTION_ICON = "evaluate_action.gif"; //$NON-NLS-1$

    /** Path to the icons of the interpreter. */
    String INTERPRETER_ICONS = "icons/"; //$NON-NLS-1$

    /** Path to the default interpreter view's icon. */
    String INTERPRETER_VIEW_DEFAULT_ICON = "view_icon.gif"; //$NON-NLS-1$

    /** Path to the icon for the "link with editor" action when disabled. */
    String LINK_WITH_EDITOR_CONTEXT_ACTION_DISABLED_ICON = "link_action_disabled.gif"; //$NON-NLS-1$

    /** Path to the icon for the "link with editor" action when enabled. */
    String LINK_WITH_EDITOR_CONTEXT_ACTION_ICON = "link_action.gif"; //$NON-NLS-1$

    /** Path to the icon for the "toggle real-time compilation" action. */
    String REALTIME_TOGGLE_ICON = "realtime_action.gif"; //$NON-NLS-1$

    /** Path to the icon for the "toggle variable visibility" action. */
    String VARIABLE_VISIBILITY_TOGGLE_ICON = "variable_action.gif"; //$NON-NLS-1$

    /** Path to the icon for the "toggle step-by-step visibility" action. */
    String STEP_BY_STEP_VISIBILITY_TOGGLE_ICON = "stepbystep_action.gif"; //$NON-NLS-1$
}

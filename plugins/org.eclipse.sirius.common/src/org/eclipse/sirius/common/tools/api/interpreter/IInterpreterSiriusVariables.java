/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

/**
 * Sirius variables strings.
 * 
 * @author mchauvin
 */
// CHECKSTYLE:OFF
public interface IInterpreterSiriusVariables {

    /** "view". */
    String VIEW = "view"; //$NON-NLS-1$

    /** "viewpoint". */
    String VIEWPOINT = "viewpoint"; //$NON-NLS-1$

    // TODOMCH this should move in diagram plug-in when mm separation will be
    // done
    /** "diagram". */
    String DIAGRAM = "diagram"; //$NON-NLS-1$

    // FIXME LRE this should move in diagram plug-in when mm separation will be
    // done
    /** "diagram". */
    String TABLE = "table"; //$NON-NLS-1$

    /** "viewPoint". */
    String VIEWPOINT_2 = "viewPoint"; //$NON-NLS-1$

    /** "element". */
    String ELEMENT = "element"; //$NON-NLS-1$

    /** "source". */
    String SOURCE = "source"; //$NON-NLS-1$

    /** "preSource". */
    String SOURCE_PRE = "preSource"; //$NON-NLS-1$

    /** "sourceView". */
    String SOURCE_VIEW = "sourceView"; //$NON-NLS-1$

    /** "preSourceView". */
    String SOURCE_VIEW_PRE = "preSourceView"; //$NON-NLS-1$

    /** "target". */
    String TARGET = "target"; //$NON-NLS-1$

    /** "preTarget". */
    String TARGET_PRE = "preTarget"; //$NON-NLS-1$

    /** Target view. */
    String TARGET_VIEW = "targetView"; //$NON-NLS-1$

    /** "preTargetView". */
    String TARGET_VIEW_PRE = "preTargetView"; //$NON-NLS-1$

    /** "targetSemanticNode". */
    String TARGET_SEMANTIC_NODE = "targetSemanticNode"; //$NON-NLS-1$

    /** "container". */
    String CONTAINER = "container"; //$NON-NLS-1$

    /** "oldContainer". */
    String CONTAINER_OLD = "oldContainer"; //$NON-NLS-1$

    /** "newContainer". */
    String CONTAINER_NEW = "newContainer"; //$NON-NLS-1$

    /** "containerView". */
    String CONTAINER_VIEW = "containerView"; //$NON-NLS-1$

    /** "newViewContainer". */
    String CONTAINER_VIEW_NEW = "newViewContainer"; //$NON-NLS-1$

    /** "copiedView". */
    String COPIED_VIEW = "copiedView"; //$NON-NLS-1$

    /** "copiedElement". */
    String COPIED_ELEMENT = "copiedElement"; //$NON-NLS-1$

    /** "root" representation root element. */
    String ROOT = "root"; //$NON-NLS-1$
}

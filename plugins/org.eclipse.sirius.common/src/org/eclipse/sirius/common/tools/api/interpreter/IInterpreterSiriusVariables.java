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
    String VIEW = "view";

    /** "viewpoint". */
    String VIEWPOINT = "viewpoint";

    // TODOMCH this should move in diagram plug-in when mm separation will be
    // done
    /** "diagram". */
    String DIAGRAM = "diagram";

    // FIXME LRE this should move in diagram plug-in when mm separation will be
    // done
    /** "diagram". */
    String TABLE = "table";

    /** "viewPoint". */
    String VIEWPOINT_2 = "viewPoint";

    /** "element". */
    String ELEMENT = "element";

    /** "source". */
    String SOURCE = "source";

    /** "preSource". */
    String SOURCE_PRE = "preSource";

    /** "sourceView". */
    String SOURCE_VIEW = "sourceView";

    /** "preSourceView". */
    String SOURCE_VIEW_PRE = "preSourceView";

    /** "target". */
    String TARGET = "target";

    /** "preTarget". */
    String TARGET_PRE = "preTarget";

    /** Target view. */
    String TARGET_VIEW = "targetView";

    /** "preTargetView". */
    String TARGET_VIEW_PRE = "preTargetView";

    /** "targetSemanticNode". */
    String TARGET_SEMANTIC_NODE = "targetSemanticNode";

    /** "container". */
    String CONTAINER = "container";

    /** "oldContainer". */
    String CONTAINER_OLD = "oldContainer";

    /** "newContainer". */
    String CONTAINER_NEW = "newContainer";

    /** "containerView". */
    String CONTAINER_VIEW = "containerView";

    /** "newViewContainer". */
    String CONTAINER_VIEW_NEW = "newViewContainer";
    
    /** "copiedView". */
    String COPIED_VIEW = "copiedView";
    
    /** "copiedElement". */
    String COPIED_ELEMENT = "copiedElement";

    /** "root" representation root element. */
    String ROOT = "root";

    /**
     * the line semantic element.
     * 
     * @deprecated since 2.3.0 : Use
     *             IInterpreterSiriusTableVariables.LINE_SEMANTIC instead.
     */
    @Deprecated
    String LINE_SEMANTIC = "lineSemantic";

    /**
     * The column semantic element.
     * 
     * @deprecated since 2.3.0 : Use
     *             IInterpreterSiriusTableVariables.LINE_SEMANTIC instead.
     */
    @Deprecated
    String COLUMN_SEMANTIC = "columnSemantic";

}

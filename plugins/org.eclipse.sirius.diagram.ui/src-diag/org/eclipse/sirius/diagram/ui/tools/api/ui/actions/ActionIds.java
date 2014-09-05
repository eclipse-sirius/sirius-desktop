/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.ui.actions;

/**
 * A list of constants defining the diagram action and menu action ids.
 * <p>
 * This interface defines constants only, it is <EM>not</EM> intended to be
 * implemented by clients.
 * </p>
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
// CHECKSTYLE:OFF
public interface ActionIds {
    // CHECKSTYLE:ON

    /** Action contribution id for the copy layout. */
    String COPY_LAYOUT = "copyLayoutAction";

    /** Action contribution id for the paste layout. */
    String PASTE_LAYOUT = "pasteLayoutAction";

    /** Action id for pin elements action. */
    String PIN_ELEMENTS = "pinElementsAction";

    /** Action id for unpin elements action. */
    String UNPIN_ELEMENTS = "unpinElementsAction";

    /** Action id for arrange bordered nodes action. */
    String ARRANGE_BORDERED_NODES = "arrangeBorderedNodesAction";

    /** Action id for arrange bordered nodes action. */
    String ARRANGE_BORDERED_NODES_TOOLBAR = "arrangeBorderedNodesActionToolBar";

    /** Action for copy (export) to an image action. */
    String COPY_TO_IMAGE = "newCopyToImageAction"; //$NON-NLS-1$

    /** Arrange menu on diagram. */
    String MENU_ARRANGE = "arrangeMenu"; //$NON-NLS-1$

    /** Action for show/hide elements. */
    String SELECT_HIDDEN_ELEMENTS = "selectHiddenElementsAction";

    /** Action for show/hide elements. */
    String ROUTING_STYLE = "treeRoutingStyleAction";

    /** Action for Activating/Deactivating the Layouting mode. **/
    String SWITCH_LAYOUTING_MODE = "switchLayoutingMode";

    /** Action to deselect all elements (select diagram). **/
    String DESELECT_ALL = "deselectAll";
}

/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.image;

/**
 * This interface stores images path.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface DiagramImagesPath {

    /** path of the palette factory default image. */
    String PALETTE_FACTORY_DEFAULT_PATH = "/org.eclipse.sirius.ui/icons/full/obj16/ToolDescription.gif"; //$NON-NLS-1$

    /** path of the palette factory error image. */
    String PALETTE_FACTORY_ERROR_PATH = "/org.eclipse.sirius.ui/icons/full/obj16/Error.gif"; //$NON-NLS-1$

    /** path of the go image. */
    String GO_IMG = "images/Go.gif"; //$NON-NLS-1$

    /** path of the refresh image. */
    String REFRESH_IMG = "images/refresh.gif"; //$NON-NLS-1$

    /** path of the link to file image. */
    String LINK_TO_FILE_IMG = "/icons/LinkTofile.gif"; //$NON-NLS-1$

    /** path of an active layer icon image. */
    String ACTIVE_LAYER_ICON = "/icons/layer_active.gif"; //$NON-NLS-1$

    /** path of an inactive layer icon image. */
    String INACTIVE_LAYER_ICON = "/icons/layer_inactive.gif"; //$NON-NLS-1$

    /** path of an inactive layer icon image. */
    String LAYER_ACTIVATION_ICON = "/icons/layer_activation.gif"; //$NON-NLS-1$

    /** path of an active layer icon image. */
    String ACTIVE_FILTER_ICON = "/icons/layer_active.gif"; //$NON-NLS-1$

    /** path of an inactive layer icon image. */
    String INACTIVE_FILTER_ICON = "/icons/layer_inactive.gif"; //$NON-NLS-1$

    /** path of an inactive layer icon image. */
    String FILTER_ACTIVATION_ICON = "/icons/layer_activation.gif"; //$NON-NLS-1$

    /** path of an undo edit button image. */
    String UNDO_ICON = "icons/undo_edit.gif"; //$NON-NLS-1$

    /** path of an image button image. */
    String IMAGE_ICON = "icons/image_obj.gif"; //$NON-NLS-1$

    /** path of an underline button image. */
    String UNDERLINE_ICON = "icons/underline.gif"; //$NON-NLS-1$

    /** path of a strike through button image. */
    String STRIKE_THROUGH_ICON = "icons/strikethrough.gif"; //$NON-NLS-1$

    /** path of the hide image. */
    String HIDE_ELEMENT_IMG = "icons/categoryHidden.gif"; //$NON-NLS-1$

    /** path of the hide label image. */
    String HIDE_LABEL_ELEMENT_IMG = "icons/categoryLabelHidden.gif"; //$NON-NLS-1$

    /** path of the reveal image. */
    String REVEAL_ELEMENTS_IMG = "icons/categoryVisible.gif"; //$NON-NLS-1$

    /** path of the hide label image. */
    String REVEAL_LABEL_IMG = "icons/categoryLabelVisible.gif"; //$NON-NLS-1$

    /** path of the delete from diagram icon image. */
    String DELETE_FROM_DIAGRAM_ICON = "icons/delete.gif"; //$NON-NLS-1$

    /** path of the delete from diagram icon image. */
    String DELETE_FROM_MODEL_ICON = "icons/deleteModel.gif"; //$NON-NLS-1$

    /** path of the copy layout icon image. */
    String COPY_LAYOUT_ICON = "icons/copyLayout.gif"; //$NON-NLS-1$

    /** path of the copy layout icon image. */
    String COPY_LAYOUT_DISABLED_ICON = "icons/copyLayoutDisabled.gif"; //$NON-NLS-1$

    /** paste layout icon. */
    String PASTE_LAYOUT_ICON = "icons/pasteLayout.gif"; //$NON-NLS-1$

    /** paste layout disabled icon. */
    String PASTE_LAYOUT_DISABLED_ICON = "icons/pasteLayoutDisabled.gif"; //$NON-NLS-1$

    /** pin elements icon. */
    String PIN_ELEMENTS_ICON = "icons/pin.gif"; //$NON-NLS-1$

    /** unpin elements icon. */
    String UNPIN_ELEMENTS_ICON = "icons/unpin.gif"; //$NON-NLS-1$

    /** arrange bordered nodes icon. */
    String ARRANGE_BORDERED_NODES_ICON = "icons/arrangeBorderedNodes.gif"; //$NON-NLS-1$

    /** font wizard icon. */
    String FONT_WIZARD = "icons/fontWizard.gif"; //$NON-NLS-1$

    /** collapse all icon. */
    String COLLAPSE_ALL_ICON = "icons/collapseall.gif"; //$NON-NLS-1$

    /** uncheck all icon. */
    String UNCHECK_ALL_ICON = "icons/disabled_co.gif"; //$NON-NLS-1$

    /** check all icon. */
    String CHECK_ALL_ICON = "icons/enabled_co.gif"; //$NON-NLS-1$

    /** expand all icon. */
    String EXPAND_ALL_ICON = "icons/expandall.gif"; //$NON-NLS-1$

    /** pin elements icon. */
    String LAYOUTING_MODE_ACTIVE_ICON = "icons/layoutingMode_activate.png"; //$NON-NLS-1$

    /** path of the image displayed thanks to this decorator. */
    String HAS_DIAG_IMG = "icons/HasLink.gif"; //$NON-NLS-1$

    /** The default directory path. */
    String DEFAULT_PATH = "full/"; //$NON-NLS-1$

    /** The default decorator directory path. */
    String DEFAULT_DECORATOR_PATH = DEFAULT_PATH + "decorator/"; //$NON-NLS-1$

    /** The view edge decorator path. */
    String VIEW_EDGE_DECORATOR = DEFAULT_DECORATOR_PATH + "DEdge"; //$NON-NLS-1$

    /** The hidden decorator path. */
    String HIDDEN_DECORATOR = DEFAULT_DECORATOR_PATH + "hidden"; //$NON-NLS-1$

    /** The hidden decorator path. */
    String HIDDEN_LABEL_DECORATOR = DEFAULT_DECORATOR_PATH + "hiddenLabel"; //$NON-NLS-1$

    /** The fold decorator path. */
    String FOLD_DECORATOR = DEFAULT_DECORATOR_PATH + "fold"; //$NON-NLS-1$

    /** path to image not found. */
    String IMAGE_NOT_FOUND = "/org.eclipse.sirius.common.ui/images/NotFound.png"; //$NON-NLS-1$

    /** path of the palette edge image. */
    String PALETTE_EDGE_PATH = "/org.eclipse.sirius.diagram.ui/icons/full/obj16/DEdge.gif"; //$NON-NLS-1$

    /** deselect all icon. */
    String DESELECT_ALL_ICON = "icons/deselectAll.gif"; //$NON-NLS-1$

    /**
     * Path of the action's image to distribute centers horizontally.
     */
    String DISTRIBUTE_CENTERS_HORIZONTALLY = "icons/distributeCentersHorizontal.gif"; //$NON-NLS-1$

    /**
     * Path of the action's image to distribute with uniform gaps horizontally.
     */
    String DISTRIBUTE_WITH_UNIFORM_GAPS_HORIZONTALLY = "icons/distributeWithUniformGapHorizontal.gif"; //$NON-NLS-1$

    /**
     * Path of the action's image to distribute middle vertically.
     */
    String DISTRIBUTE_CENTERS_VERTICALLY = "icons/distributeCentersVertical.gif"; //$NON-NLS-1$

    /** Path of the action's image to distribute with uniform gaps vertically. */
    String DISTRIBUTE_WITH_UNIFORM_GAPS_VERTICALLY = "icons/distributeWithUniformGapVertical.gif"; //$NON-NLS-1$
}

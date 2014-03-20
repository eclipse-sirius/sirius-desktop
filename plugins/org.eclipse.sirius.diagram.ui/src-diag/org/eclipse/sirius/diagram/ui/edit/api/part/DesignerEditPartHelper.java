/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.sirius.diagram.ui.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.CustomStyleEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DotEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.GaugeCompositeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.WorkspaceImageEditPart;

/**
 * Useful operations for edit part.
 * 
 * @author ymortier
 */
public final class DesignerEditPartHelper {

    /**
     * Avoid instantiation
     */
    private DesignerEditPartHelper() {

    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>BundledImage</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>BundledImage</code>.
     */
    public static boolean isBundledImaged(final int visualId) {
        return BundledImageEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>CustomStyle</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>CustomStyle</code>.
     */
    public static boolean isCustomStyle(final int visualId) {
        return CustomStyleEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>Dot</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>Dot</code>.
     */
    public static boolean isDot(final int visualId) {
        return DotEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>GaugeCompositeStyle</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>GaugeCompositeStyle</code>.
     */
    public static boolean isGaugeCompositeStyle(final int visualId) {
        return GaugeCompositeEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>Note</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>Note</code>.
     */
    public static boolean isNote(final int visualId) {
        return NoteEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>Square</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>Square</code>.
     */
    public static boolean isSquare(final int visualId) {
        return SquareEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of
     * <code>WorkspaceImage</code>.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of
     *         <code>WorkspaceImage</code>.
     */
    public static boolean isWorkspaceImage(final int visualId) {
        return WorkspaceImageEditPart.VISUAL_ID == visualId;
    }

    /**
     * Returns <code>true</code> if the <code>visualId</code> is the type of a
     * Node Style.
     * 
     * @param visualId
     *            the visual id.
     * @return <code>true</code> if the <code>visualId</code> is the type of a
     *         Node Style.
     */
    public static boolean isNodeStyle(final int visualId) {

        final boolean isImage = DesignerEditPartHelper.isBundledImaged(visualId) || DesignerEditPartHelper.isWorkspaceImage(visualId);
        final boolean isStyle = DesignerEditPartHelper.isGaugeCompositeStyle(visualId) || DesignerEditPartHelper.isCustomStyle(visualId);
        final boolean isPredefinedFigure = DesignerEditPartHelper.isDot(visualId) || DesignerEditPartHelper.isNote(visualId) || DesignerEditPartHelper.isSquare(visualId);

        return isImage || isStyle || isPredefinedFigure;
    }

}

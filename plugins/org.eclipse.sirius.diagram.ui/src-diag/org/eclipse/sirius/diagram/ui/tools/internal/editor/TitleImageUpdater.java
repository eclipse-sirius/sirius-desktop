/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.swt.graphics.Image;

/**
 * A {@link Runnable} to update the title image of a {@link DDiagramEditorImpl}
 * in the ui thread.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TitleImageUpdater implements Runnable {

    private DDiagramEditorImpl dDiagramEditorImpl;

    private Image newTitleImage;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditorImpl
     *            the {@link DDiagramEditorImpl} to the title image
     * @param newTitleImage
     *            the new {@link Image} of the title bar editor
     */
    public TitleImageUpdater(DDiagramEditorImpl dDiagramEditorImpl, Image newTitleImage) {
        this.dDiagramEditorImpl = dDiagramEditorImpl;
        this.newTitleImage = newTitleImage;
    }

    /**
     * Overridden to update the title image of a {@link DDiagramEditorImpl}.
     * 
     * {@inheritDoc}
     */
    public void run() {
        if (newTitleImage != null && !newTitleImage.equals(dDiagramEditorImpl.getTitleImage())) {
            dDiagramEditorImpl.setTitleImage(newTitleImage);
        }
    }

}

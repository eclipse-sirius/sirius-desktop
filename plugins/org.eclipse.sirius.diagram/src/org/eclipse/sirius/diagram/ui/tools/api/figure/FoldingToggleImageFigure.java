/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.query.EdgeTargetQuery;
import org.eclipse.sirius.diagram.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.internal.commands.ToggleFoldingStateCommand;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Preconditions;

/**
 * A figure which displays and controls the folding state of an element.
 * 
 * @author pcdavid
 */
public class FoldingToggleImageFigure extends ActionTriggerImageFigure {

    /**
     * Width of the images used for the folding toggle.
     */
    protected static final int FOLD_ICON_WIDTH = 9;

    /**
     * Height of the images used for the folding toggle.
     */
    protected static final int FOLD_ICON_HEIGHT = FOLD_ICON_WIDTH;

    private static final Image MINUS_IMAGE = SiriusDiagramEditorPlugin.getInstance().getImage(SiriusDiagramEditorPlugin.getBundledImageDescriptor("/icons/collapse.gif"));

    private static final Image PLUS_IMAGE = SiriusDiagramEditorPlugin.getInstance().getImage(SiriusDiagramEditorPlugin.getBundledImageDescriptor("/icons/expand.gif"));

    private final IAbstractDiagramNodeEditPart part;

    /**
     * Constructor.
     * 
     * @param part
     *            the element whose folding state to display and control.
     */
    public FoldingToggleImageFigure(IAbstractDiagramNodeEditPart part) {
        this.part = Preconditions.checkNotNull(part);
        setSize(new Dimension(FOLD_ICON_WIDTH, FOLD_ICON_HEIGHT));
        show(null);
        DDiagramElement element = part.resolveDiagramElement();
        if (element instanceof EdgeTarget) {
            updateImage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics graphics) {
        updateImage();
        super.paint(graphics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void trigger() {
        DDiagramElement element = part.resolveDiagramElement();
        if (element instanceof EdgeTarget) {
            TransactionalEditingDomain domain = part.getEditingDomain();
            domain.getCommandStack().execute(new ToggleFoldingStateCommand(domain, part));
            updateImage();
        }
    }

    private void updateImage() {
        if (part.isActive()) {
            DDiagramElement element = part.resolveDiagramElement();
            if (element instanceof EdgeTarget) {
                EdgeTargetQuery query = new EdgeTargetQuery((EdgeTarget) element);
                if (!query.isFoldingPoint()) {
                    show(null);
                } else {
                    switch (query.getFoldingState()) {
                    case FOLDED:
                        show(PLUS_IMAGE);
                        break;
                    case UNFOLDED:
                    case MIXED:
                    default:
                        show(MINUS_IMAGE);
                        break;
                    }
                }
            }
        }
    }

    private void show(Image img) {
        setImageWOFocus(img);
        setImageWFocus(img);
        if (img == null) {
            setSize(0, 0);
        } else {
            setSize(FOLD_ICON_WIDTH, FOLD_ICON_HEIGHT);
        }
    }
}

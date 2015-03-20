/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.permission.EditPartAuthorityListener;
import org.eclipse.swt.graphics.Image;

/**
 * This is the top level interface of the hierarchy of all edit parts.
 * 
 * @author ymortier
 */
public interface IDiagramElementEditPart extends IGraphicalEditPart, ISiriusEditPart {

    /**
     * Returns the diagram element that is represented by this edit part.
     * 
     * @return the diagram element that is represented by this edit part.
     */
    DDiagramElement resolveDiagramElement();

    /**
     * Returns the target value of the
     * {@link org.eclipse.sirius.viewpoint.DSemanticDecorator} that is
     * represented by this edit part.
     * 
     * @return the target value of the
     *         {@link org.eclipse.sirius.viewpoint.DSemanticDecorator} that is
     *         represented by this edit part.
     */
    EObject resolveTargetSemanticElement();

    /**
     * Returns the adapter to install on the diagram element.
     * 
     * @return the adapter to install on the diagram element.
     */
    NotificationListener getEAdapterDiagramElement();

    /**
     * Returns the listener responsible for updating the edit mode.
     * 
     * @return the listener responsible for updating the edit mode
     */
    NotificationListener getEditModeListener();

    /**
     * Resolves all semantic elements that are represented by this diagram
     * element.
     * 
     * @return all semantic elements that are represented by this diagram
     *         element.
     */
    List<EObject> resolveAllSemanticElements();

    /**
     * Returns the edit part of the style of the represented diagram element.
     * 
     * @return the edit part of the style of the represented diagram element.
     */
    IStyleEditPart getStyleEditPart();

    /**
     * Returns the authority listener of the edit part.
     * 
     * @return the authority listener of the edit part.
     */
    EditPartAuthorityListener getEditPartAuthorityListener();

    /**
     * Returns the type of the diagram element.
     * 
     * @return the type of the diagram element.
     */
    Class<?> getMetamodelType();

    /**
     * Returns the icon of this diagram element.
     * 
     * @return the icon of this diagram element.
     */
    Image getLabelIcon();

}

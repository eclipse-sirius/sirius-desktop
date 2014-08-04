/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com>  - initial API and implementation 
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 438074, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.initializer;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Interface of Pattern provider.
 * 
 * @author Joao Martins
 * 
 */
public interface IPatternProvider {

    /**
     * Get Id.
     * 
     * @return Id.
     */
    String getId();

    /**
     * Get Label.
     * 
     * @return Label.
     */
    String getLabel();

    /**
     * Get Description.
     * 
     * @return Description.
     */
    String getDescription();

    /**
     * Get ImagePath.
     * 
     * @return ImagePath.
     */
    String getImagePath();

    /**
     * Get the command.
     * 
     * @param resourceSet
     *            the resoureSet.
     * @param viewpoint
     *            the selection.
     * @return Pattern Command.
     */
    Command getPatternCreationCommand(ResourceSet resourceSet, Viewpoint viewpoint);

}

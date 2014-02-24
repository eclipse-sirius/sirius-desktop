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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.IFigure;

/**
 * We changed the generated class to an Interface in order to easily switch from
 * an implementation to another during the edit part creation.
 * 
 * @author cbrun
 */
public interface ViewNodeContainerFigureDesc extends IFigure {
    /**
     * Get the figure label.
     * 
     * @return the figure label.
     */
    SiriusWrapLabel getLabelFigure();
}

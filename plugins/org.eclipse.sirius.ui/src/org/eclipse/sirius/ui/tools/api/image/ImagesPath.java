/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.image;

/**
 * This interface stores images path.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface ImagesPath {

    /** The default directory path. */
    String DEFAULT_PATH = "full/"; //$NON-NLS-1$

    /** The default decorator directory path. */
    String DEFAULT_DECORATOR_PATH = DEFAULT_PATH + "decorator/"; //$NON-NLS-1$

    /** The view edge decorator path. */
    String VIEW_EDGE_DECORATOR = DEFAULT_DECORATOR_PATH + "DEdge"; //$NON-NLS-1$

    /** The hidden decorator path. */
    String HIDDEN_DECORATOR = DEFAULT_DECORATOR_PATH + "hidden"; //$NON-NLS-1$

    /** The fold decorator path. */
    String FOLD_DECORATOR = DEFAULT_DECORATOR_PATH + "fold"; //$NON-NLS-1$

    /** path of the create view icon image. */
    String CREATE_VIEW_ICON = "/icons/CreateView.gif"; //$NON-NLS-1$

    /** path of the image displayed thanks to this decorator. */
    String HAS_DIAG_IMG = "obj16/HasLink.gif"; //$NON-NLS-1$
}

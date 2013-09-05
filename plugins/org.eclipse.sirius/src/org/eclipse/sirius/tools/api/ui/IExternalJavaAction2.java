/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.ui;

/**
 * This class allows the specifier to warn Sirius about the kind of
 * modification will be done during the execution of this
 * {@link IExternalJavaAction2}. As it is executed externally of Sirius,
 * {@link ChangeListener} are used to keep Sirius up-to-date. Depending on
 * the result of the provided method, the specifier will be able to warn
 * Sirius the kind of modification will be done on semantic models.
 * Therefore, Sirius will be able to decide if a {@link ChangeListener} will
 * be needed, which will have a positive impact on performances.<br/>
 * <br/>
 * 
 * Note : <a href=
 * "http://wiki.eclipse.org/Evolving_Java-based_APIs_3#.222.22_Convention"
 * >Naming Convention</a>
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public interface IExternalJavaAction2 extends IExternalJavaAction {

    /**
     * This method determine if the execution of this
     * {@link IExternalJavaAction2} will delete elements. This information will
     * be used to decide if a {@link ChangeListener} will be needed for element
     * deletion. Returning false will increase performances, but the execution
     * of the external java action MUST NOT delete any elements to avoid further
     * issues.
     * 
     * @return if the execution of this {@link IExternalJavaAction2} will delete
     *         any elements.
     */
    boolean mayDeleteElements();

}

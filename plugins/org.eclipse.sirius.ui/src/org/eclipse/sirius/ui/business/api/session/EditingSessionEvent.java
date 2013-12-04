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
package org.eclipse.sirius.ui.business.api.session;

/**
 * Events for editing session.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public enum EditingSessionEvent {

    /**
     * the representation created will be opened in a editor. This event should
     * be sent to the editing session when these two operations are executed in
     * the same logical operation for the end user <strong>before</strong> the creation occurs
     */
    REPRESENTATION_ABOUT_TO_BE_CREATED_BEFORE_OPENING,
    
    
    /**
     * the representation created will be opened in a editor. This event should
     * be sent to the editing session when these two operations are executed in
     * the same logical operation for the end user <strong>after</strong> the creation occurs
     */
     REPRESENTATION_CREATED_BEFORE_OPENING

}

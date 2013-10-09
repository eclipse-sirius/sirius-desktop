/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.uri;

/**
 * 
 * Exception thrown when something is going wrong in using viewpoint:/ protocol.
 * 
 * @author cbrun
 * 
 */
public class ViewpointProtocolException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new exception with a message.
     * 
     * @param string
     *            message.
     */
    public ViewpointProtocolException(final String string) {
        super(string);
    }

}

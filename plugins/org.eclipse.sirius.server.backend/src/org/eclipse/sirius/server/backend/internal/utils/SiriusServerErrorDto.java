/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.backend.internal.utils;

/**
 * The DTO used to return an error.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle::javadocmethod", "checkstyle::javadocfield" })
public class SiriusServerErrorDto {
    private String message;

    /**
     * The constructor.
     *
     * @param message
     *            The message
     */
    public SiriusServerErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

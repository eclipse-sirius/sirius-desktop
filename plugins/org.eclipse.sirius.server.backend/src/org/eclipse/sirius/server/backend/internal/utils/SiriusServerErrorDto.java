/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

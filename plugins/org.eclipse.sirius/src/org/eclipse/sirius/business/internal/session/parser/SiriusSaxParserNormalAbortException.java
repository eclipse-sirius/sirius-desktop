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
package org.eclipse.sirius.business.internal.session.parser;

import org.xml.sax.SAXException;

/**
 * An exception that will be thrown when all expected informations have been
 * reached during parsing with {@link RepresentationsFileSaxParser}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusSaxParserNormalAbortException extends SAXException {
    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new SessionSAXParserNormalAbortException.
     * 
     * @param message
     *            The error or warning message.
     */
    public SiriusSaxParserNormalAbortException(String message) {
        super(message);
    }
}

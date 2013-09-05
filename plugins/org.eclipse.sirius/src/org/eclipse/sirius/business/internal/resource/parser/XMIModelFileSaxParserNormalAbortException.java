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
package org.eclipse.sirius.business.internal.resource.parser;

import org.xml.sax.SAXException;

/**
 * An exception that will be thrown when all expected informations have been
 * reached during parsing with {@link XMIModelFileSaxParser}.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 * 
 */
public class XMIModelFileSaxParserNormalAbortException extends SAXException {
    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 546513281L;

    /**
     * Create a new SessionSAXParserNormalAbortException.
     * 
     * @param message
     *            The error or warning message.
     */
    public XMIModelFileSaxParserNormalAbortException(String message) {
        super(message);
    }
}

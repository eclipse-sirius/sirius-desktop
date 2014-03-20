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
package org.eclipse.sirius.diagram.ui.internal.parsers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

/**
 * @was-generated
 */
public class CompositeParser implements IParser {

    /**
     * @was-generated
     */
    private final IParser reader;

    /**
     * @was-generated
     */
    private final IParser writer;

    /**
     * @was-generated
     */
    public CompositeParser(IParser reader, IParser writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * @was-generated
     */
    public boolean isAffectingEvent(Object event, int flags) {
        return reader.isAffectingEvent(event, flags);
    }

    /**
     * @was-generated
     */
    public String getPrintString(IAdaptable adapter, int flags) {
        return reader.getPrintString(adapter, flags);
    }

    /**
     * @was-generated
     */
    public String getEditString(IAdaptable adapter, int flags) {
        return reader.getEditString(adapter, flags);
    }

    /**
     * @was-generated
     */
    public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
        return writer.isValidEditString(adapter, editString);
    }

    /**
     * @was-generated
     */
    public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
        return writer.getParseCommand(adapter, newString, flags);
    }

    /**
     * @was-generated
     */
    public IContentAssistProcessor getCompletionProcessor(IAdaptable adapter) {
        return writer.getCompletionProcessor(adapter);
    }
}

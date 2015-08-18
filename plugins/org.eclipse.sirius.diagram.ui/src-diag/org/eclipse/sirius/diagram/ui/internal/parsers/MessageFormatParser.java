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

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.ParsePosition;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramElementMappingHelper;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.ui.part.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * @was-generated
 */
public class MessageFormatParser extends AbstractParser {

    /**
     * @was-generated
     */
    private String defaultPattern;

    /**
     * @was-generated
     */
    private MessageFormat viewProcessor;

    /**
     * @was-generated
     */
    private MessageFormat editorProcessor;

    /**
     * @was-generated
     */
    private MessageFormat editProcessor;

    /**
     * @was-generated
     */
    public MessageFormatParser(EAttribute[] features) {
        super(features);
    }

    /**
     * @was-generated
     */
    protected String getDefaultPattern() {
        if (defaultPattern == null) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < features.length; i++) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append('{');
                sb.append(i);
                sb.append('}');
            }
            defaultPattern = sb.toString();
        }
        return defaultPattern;
    }

    /**
     * @was-generated
     */
    public String getViewPattern() {
        String pattern = super.getViewPattern();
        return pattern != null ? pattern : getDefaultPattern();
    }

    /**
     * @was-generated
     */
    public void setViewPattern(String viewPattern) {
        super.setViewPattern(viewPattern);
        viewProcessor = null;
    }

    /**
     * @was-generated
     */
    protected MessageFormat createViewProcessor(String viewPattern) {
        return new MessageFormat(viewPattern);
    }

    /**
     * @was-generated
     */
    protected MessageFormat getViewProcessor() {
        if (viewProcessor == null) {
            viewProcessor = createViewProcessor(getViewPattern());
        }
        return viewProcessor;
    }

    /**
     * @was-generated
     */
    public String getEditorPattern() {
        String pattern = super.getEditorPattern();
        return pattern != null ? pattern : getDefaultPattern();
    }

    /**
     * @was-generated
     */
    public void setEditorPattern(String editorPattern) {
        super.setEditorPattern(editorPattern);
        editorProcessor = null;
    }

    /**
     * @was-generated
     */
    protected MessageFormat createEditorProcessor(String editorPattern) {
        return new MessageFormat(editorPattern);
    }

    /**
     * @was-generated
     */
    protected MessageFormat getEditorProcessor() {
        if (editorProcessor == null) {
            editorProcessor = createEditorProcessor(getEditorPattern());
        }
        return editorProcessor;
    }

    /**
     * @was-generated
     */
    public String getEditPattern() {
        String pattern = super.getEditPattern();
        return pattern != null ? pattern : getDefaultPattern();
    }

    /**
     * @was-generated
     */
    public void setEditPattern(String editPattern) {
        super.setEditPattern(editPattern);
        editProcessor = null;
    }

    /**
     * @was-generated
     */
    protected MessageFormat createEditProcessor(String editPattern) {
        return new MessageFormat(editPattern);
    }

    /**
     * @was-generated
     */
    protected MessageFormat getEditProcessor() {
        if (editProcessor == null) {
            editProcessor = createEditProcessor(getEditPattern());
        }
        return editProcessor;
    }

    /**
     * @was-generated
     */
    public String getPrintString(IAdaptable adapter, int flags) {
        EObject element = (EObject) adapter.getAdapter(EObject.class);
        return getViewProcessor().format(getValues(element), new StringBuffer(), new FieldPosition(0)).toString();
    }

    /**
     * Return the result of the evaluation of the expression
     * directEditLabel.InputLabelExpression.
     * 
     * @not-generated
     */
    public String getEditString(IAdaptable adapter, int flags) {
        EObject element = (EObject) adapter.getAdapter(EObject.class);
        if (element instanceof DDiagramElement) {
            // Override the default behavior if a direct edit tool is available
            // with a non empty directEditLabelExpression.
            DirectEditLabel directEditLabelTool = ((DDiagramElement) element).getDiagramElementMapping().getLabelDirectEdit();
            if (directEditLabelTool != null && !StringUtil.isEmpty(directEditLabelTool.getInputLabelExpression())) {
                final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element);
                String result = DiagramElementMappingHelper.computeInputLabelOfDirectEditLabel(((DDiagramElement) element), ((DDiagramElement) element).getParentDiagram(), directEditLabelTool,
                        interpreter);
                return result == null ? "" : result; //$NON-NLS-1$
            }
        }
        return getEditorProcessor().format(getValues(element), new StringBuffer(), new FieldPosition(0)).toString();
    }

    /**
     * @was-generated
     */
    public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
        ParsePosition pos = new ParsePosition(0);
        Object[] values = getEditProcessor().parse(editString, pos);
        if (values == null) {
            return new ParserEditStatus(DiagramPlugin.ID, IParserEditStatus.UNEDITABLE, NLS.bind(Messages.MessageFormatParser_InvalidInputError, new Integer(pos.getErrorIndex())));
        }
        return validateNewValues(values);
    }

    /**
     * @was-generated
     */
    public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
        Object[] values = getEditProcessor().parse(newString, new ParsePosition(0));
        return getParseCommand(adapter, values, flags);
    }
}

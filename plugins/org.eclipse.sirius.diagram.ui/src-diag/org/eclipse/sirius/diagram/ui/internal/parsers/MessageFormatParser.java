/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.parsers;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.ParsePosition;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramElementMappingHelper;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

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
    @Override
    public String getViewPattern() {
        String pattern = super.getViewPattern();
        return pattern != null ? pattern : getDefaultPattern();
    }

    /**
     * @was-generated
     */
    @Override
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
    @Override
    public String getEditorPattern() {
        String pattern = super.getEditorPattern();
        return pattern != null ? pattern : getDefaultPattern();
    }

    /**
     * @was-generated
     */
    @Override
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
    @Override
    public String getEditPattern() {
        String pattern = super.getEditPattern();
        return pattern != null ? pattern : getDefaultPattern();
    }

    /**
     * @was-generated
     */
    @Override
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
    @Override
    public String getPrintString(IAdaptable adapter, int flags) {
        EObject element = adapter.getAdapter(EObject.class);
        return getViewProcessor().format(getValues(element), new StringBuffer(), new FieldPosition(0)).toString();
    }

    /**
     * Return the result of the evaluation of the expression directEditLabel.InputLabelExpression.
     * 
     * @not-generated
     */
    @Override
    public String getEditString(IAdaptable adapter, int flags) {
        EObject element = adapter.getAdapter(EObject.class);
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
    @Override
    public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
        Exception exception = null;
        EObject eObjectAdapter = adapter.getAdapter(EObject.class);
        if (eObjectAdapter instanceof DSemanticDecorator && !eObjectAdapter.eIsProxy()) {
            EObject target = ((DSemanticDecorator) eObjectAdapter).getTarget();
            if (target != null && !target.eIsProxy()) {
                IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(target);
                if (authority != null && LockStatus.LOCKED_BY_OTHER == authority.getLockStatus(target)) {
                    exception = new LockedInstanceException(target);
                }
            } else {
                exception = new IllegalStateException(Messages.MessageFormatParser_ProxyOrNullSemanticTargetMessage);
            }
        } else {
            exception = new IllegalStateException(Messages.MessageFormatParser_ProxyOrNullTargetMessage);
        }

        if (exception != null) {
            IStatus status = new Status(IStatus.WARNING, DiagramUIPlugin.ID, exception.getMessage(), exception);
            DiagramUIPlugin.getPlugin().getLog().log(status);
            return new ParserEditStatus(DiagramPlugin.ID, IParserEditStatus.UNEDITABLE, exception.getMessage());
        }

        ParsePosition pos = new ParsePosition(0);
        Object[] values = getEditProcessor().parse(editString, pos);
        if (values == null) {
            return new ParserEditStatus(DiagramPlugin.ID, IParserEditStatus.UNEDITABLE, MessageFormat.format(Messages.MessageFormatParser_InvalidInputError, Integer.valueOf(pos.getErrorIndex())));
        }
        return validateNewValues(values);
    }

    /**
     * @was-generated
     */
    @Override
    public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
        Object[] values = getEditProcessor().parse(newString, new ParsePosition(0));
        return getParseCommand(adapter, values, flags);
    }
}

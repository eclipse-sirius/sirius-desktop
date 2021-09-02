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

import java.text.MessageFormat;
import java.util.Arrays;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.part.Messages;

/**
 * @was-generated
 */
public abstract class AbstractParser implements IParser {

    /**
     * @was-generated
     */
    protected final EAttribute[] features;

    /**
     * @was-generated
     */
    private String viewPattern;

    /**
     * @was-generated
     */
    private String editorPattern;

    /**
     * @was-generated
     */
    private String editPattern;

    /**
     * @was-generated
     */
    public AbstractParser(EAttribute[] features) {
        if (features == null || Arrays.asList(features).contains(null)) {
            throw new IllegalArgumentException();
        }
        this.features = features;
    }

    /**
     * @was-generated
     */
    public String getViewPattern() {
        return viewPattern;
    }

    /**
     * @was-generated
     */
    public void setViewPattern(String viewPattern) {
        this.viewPattern = viewPattern;
    }

    /**
     * @was-generated
     */
    public String getEditorPattern() {
        return editorPattern;
    }

    /**
     * @was-generated
     */
    public void setEditorPattern(String editorPattern) {
        this.editorPattern = editorPattern;
    }

    /**
     * @was-generated
     */
    public String getEditPattern() {
        return editPattern;
    }

    /**
     * @was-generated
     */
    public void setEditPattern(String editPattern) {
        this.editPattern = editPattern;
    }

    /**
     * @was-generated
     */
    @Override
    public boolean isAffectingEvent(Object event, int flags) {
        if (event instanceof Notification) {
            return isAffectingFeature(((Notification) event).getFeature());
        }
        return false;
    }

    /**
     * @was-generated
     */
    protected boolean isAffectingFeature(Object feature) {
        for (int i = 0; i < features.length; i++) {
            if (features[i] == feature) {
                return true;
            }
        }
        return false;
    }

    /**
     * @was-generated
     */
    @Override
    public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
        return null;
    }

    /**
     * @was-generated
     */
    protected Object[] getValues(EObject element) {
        Object[] values = new Object[features.length];
        for (int i = 0; i < features.length; i++) {
            values[i] = getValue(element, features[i]);
        }
        return values;
    }

    /**
     * @was-generated
     */
    protected Object getValue(EObject element, EAttribute feature) {
        Object value = element.eGet(feature);
        Class<?> iClass = feature.getEAttributeType().getInstanceClass();
        if (String.class.equals(iClass)) {
            if (value == null) {
                value = ""; //$NON-NLS-1$
            }
        }
        return value;
    }

    /**
     * @was-generated
     */
    protected ICommand getParseCommand(IAdaptable adapter, Object[] values, int flags) {
        if (values == null || validateNewValues(values).getCode() != IParserEditStatus.EDITABLE) {
            return UnexecutableCommand.INSTANCE;
        }
        EObject element = adapter.getAdapter(EObject.class);
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
        if (editingDomain == null) {
            return UnexecutableCommand.INSTANCE;
        }
        CompositeTransactionalCommand command = new CompositeTransactionalCommand(editingDomain, org.eclipse.sirius.diagram.ui.provider.Messages.AbstractParser_setValuesCmdLabel);
        for (int i = 0; i < values.length; i++) {
            command.compose(getModificationCommand(element, features[i], values[i]));
        }
        return command;
    }

    /**
     * @was-generated
     */
    protected ICommand getModificationCommand(EObject element, EAttribute feature, Object value) {
        value = getValidNewValue(feature, value);
        if (value instanceof InvalidValue) {
            return UnexecutableCommand.INSTANCE;
        }
        SetRequest request = new SetRequest(element, feature, value);
        return new SetValueCommand(request);
    }

    /**
     * @was-generated
     */
    protected IParserEditStatus validateNewValues(Object[] values) {
        if (values.length != features.length) {
            return ParserEditStatus.UNEDITABLE_STATUS;
        }
        for (int i = 0; i < values.length; i++) {
            Object value = getValidNewValue(features[i], values[i]);
            if (value instanceof InvalidValue) {
                return new ParserEditStatus(DiagramPlugin.ID, IParserEditStatus.UNEDITABLE, value.toString());
            }
        }
        return ParserEditStatus.EDITABLE_STATUS;
    }

    /**
     * @was-generated
     */
    protected Object getValidNewValue(EAttribute feature, Object value) {
        EClassifier type = feature.getEType();
        if (type instanceof EDataType) {
            Class<?> iClass = type.getInstanceClass();
            if (Boolean.TYPE.equals(iClass)) {
                if (value instanceof Boolean) {
                    // ok
                } else if (value instanceof String) {
                    value = Boolean.valueOf((String) value);
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Character.TYPE.equals(iClass)) {
                if (value instanceof Character) {
                    // ok
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        value = new Character(s.charAt(0));
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Byte.TYPE.equals(iClass)) {
                if (value instanceof Byte) {
                    // ok
                } else if (value instanceof Number) {
                    value = new Byte(((Number) value).byteValue());
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        try {
                            value = Byte.valueOf(s);
                        } catch (NumberFormatException nfe) {
                            value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_WrongStringConversionMessage, iClass.getName()));
                        }
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Short.TYPE.equals(iClass)) {
                if (value instanceof Short) {
                    // ok
                } else if (value instanceof Number) {
                    value = new Short(((Number) value).shortValue());
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        try {
                            value = Short.valueOf(s);
                        } catch (NumberFormatException nfe) {
                            value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_WrongStringConversionMessage, iClass.getName()));
                        }
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Integer.TYPE.equals(iClass)) {
                if (value instanceof Integer) {
                    // ok
                } else if (value instanceof Number) {
                    value = Integer.valueOf(((Number) value).intValue());
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        try {
                            value = Integer.valueOf(s);
                        } catch (NumberFormatException nfe) {
                            value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_WrongStringConversionMessage, iClass.getName()));
                        }
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Long.TYPE.equals(iClass)) {
                if (value instanceof Long) {
                    // ok
                } else if (value instanceof Number) {
                    value = new Long(((Number) value).longValue());
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        try {
                            value = Long.valueOf(s);
                        } catch (NumberFormatException nfe) {
                            value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_WrongStringConversionMessage, iClass.getName()));
                        }
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Float.TYPE.equals(iClass)) {
                if (value instanceof Float) {
                    // ok
                } else if (value instanceof Number) {
                    value = new Float(((Number) value).floatValue());
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        try {
                            value = Float.valueOf(s);
                        } catch (NumberFormatException nfe) {
                            value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_WrongStringConversionMessage, iClass.getName()));
                        }
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (Double.TYPE.equals(iClass)) {
                if (value instanceof Double) {
                    // ok
                } else if (value instanceof Number) {
                    value = new Double(((Number) value).doubleValue());
                } else if (value instanceof String) {
                    String s = (String) value;
                    if (s.length() == 0) {
                        value = null;
                    } else {
                        try {
                            value = Double.valueOf(s);
                        } catch (NumberFormatException nfe) {
                            value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_WrongStringConversionMessage, iClass.getName()));
                        }
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, iClass.getName()));
                }
            } else if (type instanceof EEnum) {
                if (value instanceof String) {
                    EEnumLiteral literal = ((EEnum) type).getEEnumLiteralByLiteral((String) value);
                    if (literal == null) {
                        value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnknownLiteralMessage, value));
                    } else {
                        value = literal.getInstance();
                    }
                } else {
                    value = new InvalidValue(MessageFormat.format(Messages.AbstractParser_UnexpectedValueTypeMessage, String.class.getName()));
                }
            }
        }
        return value;
    }

    /**
     * @was-generated
     */
    protected class InvalidValue {

        /**
         * @was-generated
         */
        private String description;

        /**
         * @was-generated
         */
        public InvalidValue(String description) {
            this.description = description;
        }

        /**
         * @was-generated
         */
        @Override
        public String toString() {
            return description;
        }
    }
}

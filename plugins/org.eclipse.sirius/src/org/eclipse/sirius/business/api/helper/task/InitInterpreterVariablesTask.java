/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.helper.task;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EObjectCollectionWrapper;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.VariableContainer;

/**
 * Initializes variables of the acceleo interpreter.
 * 
 * @author cbrun
 */
public class InitInterpreterVariablesTask extends AbstractCommandTask {

    /** The acceleo interpreter. */
    private final IInterpreter interpreter;

    /**
     * The variables. Each key is an {@link AbstractVariable} and each value is an {@link EObject}.s
     */
    private final Map<AbstractVariable, Object> variables;

    /**
     * The string variables. Each key is an {@link AbstractVariable} and each value is a {@link String}.
     */
    private final Map<AbstractVariable, String> stringVariables;

    /**
     * The UI callback is used to ask for variable selection in some cases.
     */
    private final UICallBack uiCallBack;

    /**
     * Create a new {@link InitInterpreterVariablesTask} with the variables to initialize and the acceleo interpreter to
     * use. The {@link Map} variables is composed of {@link AbstractVariable} (keys) and {@link EObject} (values).
     * 
     * @param variables
     *            the variables to initialize.
     * @param interpreter
     *            the interpreter to use.
     * @param callback
     *            the callback
     */
    public InitInterpreterVariablesTask(final Map<AbstractVariable, Object> variables, final IInterpreter interpreter, final UICallBack callback) {
        this(variables, Collections.<AbstractVariable, String> emptyMap(), interpreter, callback);
    }

    /**
     * Create a new {@link InitInterpreterVariablesTask} with the variables to initialize and the acceleo interpreter to
     * use. The {@link Map} variables is composed of {@link AbstractVariable} (keys) and {@link EObject} (values). The
     * {@link Map} stringVariables is composed of {@link AbstractVariable} (keys) and {@link String} (values)
     * 
     * @param variables
     *            the variables to initialize.
     * @param stringVariables
     *            the string variables to initialize
     * @param interpreter
     *            the interpreter to use.
     * @param callback
     *            the callback
     */
    public InitInterpreterVariablesTask(final Map<AbstractVariable, Object> variables, final Map<AbstractVariable, String> stringVariables, final IInterpreter interpreter, final UICallBack callback) {
        this.variables = variables;
        this.interpreter = interpreter;
        this.stringVariables = stringVariables;
        this.uiCallBack = callback;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        interpreter.clearVariables();
        /*
         * last value is used to keep track of the latest variable on which we get a value, as it will be the source of
         * some possible dialog or wizard to get the candidate objects.
         */
        // EObject lastValue = null;
        final SortedMap<AbstractVariable, Object> sortedVariables = new TreeMap<AbstractVariable, Object>(new ModelVariableComparator());
        sortedVariables.putAll(this.variables);
        Iterator<Entry<AbstractVariable, Object>> iterVariables = sortedVariables.entrySet().iterator();
        while (iterVariables.hasNext()) {
            final Entry<AbstractVariable, Object> currentVariable = iterVariables.next();
            AbstractVariable variable = currentVariable.getKey();
            if (variable != null) {
                Object value = currentVariable.getValue();
                if (value instanceof EObjectCollectionWrapper) {
                    EObjectCollectionWrapper eObjectCollectionWrapper = (EObjectCollectionWrapper) value;
                    this.interpreter.setVariable(variable.getName(), eObjectCollectionWrapper.getCollection());
                } else {
                    this.interpreter.setVariable(variable.getName(), value);
                }
            }
        }

        //
        // Init acceleo variables and select variables.
        iterVariables = sortedVariables.entrySet().iterator();
        while (iterVariables.hasNext()) {
            final Entry<AbstractVariable, Object> currentVariable = iterVariables.next();
            final EObject root = getAnEObject(currentVariable.getValue());
            if (root != null) {
                if (currentVariable.getKey() instanceof VariableContainer) {
                    final VariableContainer variableContainer = (VariableContainer) currentVariable.getKey();
                    final Iterator<SubVariable> iterSubVariables = variableContainer.getSubVariables().iterator();
                    while (iterSubVariables.hasNext()) {
                        final SubVariable subVariable = iterSubVariables.next();
                        /*
                         * Acceleo Variable.
                         */
                        if (subVariable instanceof AcceleoVariable) {
                            final AcceleoVariable acceleoVariable = (AcceleoVariable) subVariable;
                            Object result = null;
                            try {
                                result = this.interpreter.evaluate(root, acceleoVariable.getComputationExpression());
                            } catch (final EvaluationException e) {
                                RuntimeLoggerManager.INSTANCE.error(acceleoVariable, ToolPackage.eINSTANCE.getAcceleoVariable_ComputationExpression(), e);
                            }
                            this.interpreter.setVariable(acceleoVariable.getName(), result);
                        }
                        /*
                         * Select Modle Variable.
                         */
                        else if (subVariable instanceof SelectModelElementVariable) {
                            final SelectModelElementVariable selectModelElementVariable = (SelectModelElementVariable) subVariable;
                            try {
                                final Collection<EObject> values = uiCallBack.askForVariableValues(root, selectModelElementVariable);
                                if (values.size() == 1 && !selectModelElementVariable.isMultiple()) {
                                    interpreter.setVariable(selectModelElementVariable.getName(), values.iterator().next());
                                } else if (values.size() >= 1 && selectModelElementVariable.isMultiple()) {
                                    final EList<EObject> eValues = new BasicEList<EObject>(values);
                                    interpreter.setVariable(selectModelElementVariable.getName(), eValues);
                                }
                            } catch (final InterruptedException e) {
                                //
                                // Cancel
                                throw new OperationCanceledException();
                            }
                        }
                    }
                }
            }
        }
        final Iterator<Entry<AbstractVariable, String>> iterStringVariables = stringVariables.entrySet().iterator();
        while (iterStringVariables.hasNext()) {
            final Entry<AbstractVariable, String> currentVariable = iterStringVariables.next();
            this.interpreter.setVariable(currentVariable.getKey().getName(), currentVariable.getValue());
        }

    }

    private EObject getAnEObject(final Object value) {
        EObject result = null;
        if (value instanceof EObject) {
            result = (EObject) value;
        } else if (value instanceof Collection) {
            if (((Collection<?>) value).size() > 0 && ((Collection<?>) value).iterator().next() instanceof EObject) {
                result = (EObject) ((Collection<?>) value).iterator().next();
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.InitInterpreterVariablesTask_label;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#canExecute()
     */
    @Override
    public boolean canExecute() {
        return true;
    }

    /**
     * Smart comparator that compares {@link AbstractVariable}s.
     * 
     * @author ymortier
     */
    private static class ModelVariableComparator implements Comparator<AbstractVariable>, Serializable {
        /**
         * Generated SUID.
         */
        private static final long serialVersionUID = -2963822840213385592L;

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(T, T)
         */
        @Override
        public int compare(final AbstractVariable variable0, final AbstractVariable variable1) {

            if (EcoreUtil.isAncestor(variable0, variable1) || EcoreUtil.isAncestor(variable1, variable0)) {
                return -1;
            }

            final EObject commonAncestor = ModelVariableComparator.getCommonAncestor(variable0, variable1);
            if (commonAncestor == null) {
                throw new RuntimeException(Messages.InitInterpreterVariablesTask_invalidModelErrorMsg);
            }
            int result = 0;
            final Iterator<EObject> iterContents = commonAncestor.eContents().iterator();
            while (iterContents.hasNext()) {
                final EObject currentObject = iterContents.next();
                if (currentObject == variable0 || EcoreUtil.isAncestor(currentObject, variable0)) {
                    // variable1 cannot be under currentObject since the
                    // common ancestor is commonAncestor.
                    // variable 0 is before variable 1.
                    result = -1;
                } else if (currentObject == variable1 || EcoreUtil.isAncestor(currentObject, variable1)) {
                    // variable0 cannot be under currentObject since the
                    // common ancestor is commonAncestor.
                    // variable 1 is before variable 0.
                    result = 1;
                }

            }
            return result;
        }

        /**
         * Returns the common ancestor of eObj1 and eObj2.
         * 
         * @param eObj1
         *            the first object.
         * @param eObj2
         *            the second object.
         * @return the common ancestor of eObj1 and eObj2.
         */
        private static EObject getCommonAncestor(final EObject eObj1, final EObject eObj2) {
            EObject commonAncestor = null;
            if (EcoreUtil.isAncestor(eObj1, eObj2)) {
                commonAncestor = eObj1.eContainer();
            } else if (EcoreUtil.isAncestor(eObj2, eObj1)) {
                commonAncestor = eObj2.eContainer();
            } else {
                EObject ancestor1 = eObj1.eContainer();
                while (ancestor1 != null && commonAncestor == null) {
                    EObject ancestor2 = eObj2.eContainer();
                    while (ancestor2 != null && commonAncestor == null) {
                        if (ancestor1 == ancestor2) {
                            commonAncestor = ancestor1;
                        }
                        ancestor2 = ancestor2.eContainer();
                    }
                    ancestor1 = ancestor1.eContainer();
                }
            }
            return commonAncestor;
        }
    }

}

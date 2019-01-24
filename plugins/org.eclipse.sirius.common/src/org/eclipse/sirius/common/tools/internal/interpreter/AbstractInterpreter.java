/*******************************************************************************
 * Copyright (c) 2013, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.api.interpreter.IConverter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.TypedValidation;
import org.eclipse.sirius.common.tools.api.interpreter.VariableManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Abstract base class for implementations of {@link IInterpreter}.
 *
 * @author pcdavid
 */
public abstract class AbstractInterpreter implements IInterpreter, TypedValidation {

    /** The separator between EPackage name and EClass name for domain class. */
    protected static final String SEPARATOR = "."; //$NON-NLS-1$

    /**
     * This map will hold the values associated to given variable names. Note that even if this is a multimap, the
     * variables are considered as a stack in order to be coherent with other interpreters : evaluation will consider
     * the value to be a Collection, but setting/unsetting will only work one object by one object.
     */
    protected VariableManager variables;

    /**
     * The converter to use to coerce raw values returned by the actual implementation into the type requested expected
     * for a given method.
     */
    private final IConverter converter = new DefaultConverter();

    /**
     * Constructor.
     */
    protected AbstractInterpreter() {
        this.variables = new VariableManager();
    }

    @Override
    public IConverter getConverter() {
        return converter;
    }

    @Override
    public boolean provides(String expression) {
        return expression != null && expression.startsWith(getPrefix());
    }

    @Override
    public void clearImports() {
        // Nothing to do.
    }

    @Override
    public void addImport(String dependency) {
        // Nothing to do.
    }

    @Override
    public void setProperty(Object key, Object value) {
        // Nothing to do.
    }

    @Override
    public void setVariable(String name, Object value) {
        variables.setVariable(name, value);
    }

    @Override
    public void unSetVariable(String name) {
        variables.unSetVariable(name);
    }

    @Override
    public Object getVariable(String name) {
        return variables.getVariable(name);
    }

    @Override
    public Map<String, Object> getVariables() {
        return variables.getVariables();
    }

    @Override
    public void clearVariables() {
        variables.clearVariables();
    }

    @Override
    public void dispose() {
        variables.clearVariables();
    }

    @Override
    public void setModelAccessor(ModelAccessor modelAccessor) {
        // Nothing to do.
    }

    @Override
    public String getVariablePrefix() {
        return ""; //$NON-NLS-1$
    }

    @Override
    public void setCrossReferencer(ECrossReferenceAdapter crossReferencer) {
        // Nothing to do.
    }

    @Override
    public Collection<String> getImports() {
        return Collections.emptySet();
    }

    @Override
    public void removeImport(String dependency) {
        // Nothing to do.
    }

    @Override
    public boolean supportsValidation() {
        return false;
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        return analyzeExpression(context, expression).getStatuses();
    }

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        // Nothing to do.
    }
}

/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.common.acceleo.aql.business.internal;

import java.util.Collection;
import java.util.Map;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.JavaExtensionsManager;
import org.eclipse.sirius.common.tools.api.interpreter.VariableManager;
import org.eclipse.sirius.common.tools.internal.interpreter.AbstractInterpreter;

/**
 * An abstract class which handles the variables and classloading status.
 *
 * @author cedric
 */
public abstract class AcceleoAbstractInterpreter extends AbstractInterpreter {
    /**
     * Instance responsible for managing the imports, dependencies and Java
     * Services.
     */
    protected JavaExtensionsManager javaExtensions;

    /**
     * This map will hold the values associated to given variable names. Note
     * that even if this is a multimap, the variables are considered as a stack
     * in order to be coherent with other interpreters : evaluation will
     * consider the value to be a Collection, but setting/unsetting will only
     * work one object by one object.
     */
    private VariableManager variables;

    /**
     * Create a new Interpreter.
     */
    public AcceleoAbstractInterpreter() {
        javaExtensions = JavaExtensionsManager.createManagerWithOverride();
        variables = new VariableManager();
    }

    @Override
    public void clearVariables() {
        variables.clearVariables();
    }

    @Override
    public void dispose() {
        variables.clearVariables();
        this.javaExtensions.dispose();
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
    public void setProperty(Object key, Object value) {
        /*
         * This is called by the framework with the FILES key in order to pass
         * us all the VSM files as a Collection.
         */
        if (IInterpreter.FILES.equals(key)) {
            javaExtensions.updateScope((Collection<String>) value);
        }
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
    public void addImport(String dependency) {
        javaExtensions.addImport(dependency);

    }

    @Override
    public void removeImport(String dependency) {
        javaExtensions.removeImport(dependency);
    }

    @Override
    public Collection<String> getImports() {
        return javaExtensions.getImports();

    }

    @Override
    public void clearImports() {
        javaExtensions.clearImports();
    }
}

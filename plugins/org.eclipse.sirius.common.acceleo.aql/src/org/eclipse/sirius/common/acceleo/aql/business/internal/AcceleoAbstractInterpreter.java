/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.aql.business.internal;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener;

/**
 * An abstract class which handles the variables and classloading status.
 *
 * @author cedric
 */
public abstract class AcceleoAbstractInterpreter implements IInterpreter {

    /**
     * This will be updated with the list of accessible viewpoint plugins, if
     * any.
     */
    protected final Set<String> viewpointPlugins = Sets.newLinkedHashSet();

    /**
     * This will be updated with the list of accessible viewpoint projects
     * present in the workspace, if any.
     */
    protected final Set<String> viewpointProjects = Sets.newLinkedHashSet();

    /**
     * This map will hold the values associated to given variable names. Note
     * that even if this is a multimap, the variables are considered as a stack
     * in order to be coherent with other interpreters : evaluation will
     * consider the value to be a Collection, but setting/unsetting will only
     * work one object by one object.
     */
    private ListMultimap<String, Object> variables = ArrayListMultimap.create();

    /** This will contain the listeners interested in our variables' status. */
    private final Set<IVariableStatusListener> variableStatusListeners = Sets.newHashSet();

    /**
     * Checks whether the given path exists in the plugins.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing plugin
     *         resource, <code>false</code> otherwise.
     */
    private static boolean existsInPlugins(String path) {
        try {
            URL url = new URL(path);
            return FileLocator.find(url) != null;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Checks whether the given path exists in the workspace.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing workspace
     *         resource, <code>false</code> otherwise.
     */
    private static boolean existsInWorkspace(String path) {
        if (path == null || path.length() == 0 || EcorePlugin.getWorkspaceRoot() == null) {
            return false;
        }
        return ResourcesPlugin.getWorkspace().getRoot().exists(new Path(path));
    }

    /**
     * Returns the last value of the given list.
     * <p>
     * Makes no effort to try and check whether the argument is valid.
     * </p>
     *
     * @param values
     *            List from which we need the last value.
     * @param <V>
     *            Type of the list's values.
     * @return The last value of the given list.
     */
    private static <V> V getLast(List<V> values) {
        final ListIterator<V> iterator = values.listIterator(values.size());
        return iterator.previous();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#addVariableStatusListener(org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener)
     */
    @Override
    public void addVariableStatusListener(IVariableStatusListener newListener) {
        variableStatusListeners.add(newListener);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#clearVariables()
     */
    @Override
    public void clearVariables() {
        variables.clear();
        notifyVariableListeners();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#dispose()
     */
    @Override
    public void dispose() {
        variables.clear();
        variableStatusListeners.clear();
        viewpointPlugins.clear();
        viewpointProjects.clear();

    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#getVariable(java.lang.String)
     */
    @Override
    public Object getVariable(String name) {
        if (variables.containsKey(name)) {
            final List<Object> values = variables.get(name);
            if (!values.isEmpty()) {
                return AcceleoAbstractInterpreter.getLast(values);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#getVariables()
     */
    @Override
    public Map<String, ?> getVariables() {
        Map<String, Object> topMostValues = Maps.newHashMap();
        for (String varName : variables.keySet()) {
            topMostValues.put(varName, getVariable(varName));
        }
        return topMostValues;
    }

    /**
     * Notifies all of the registered variable status listener of our current
     * variable status. This will be called internally whenever we change the
     * variable map.
     */
    private void notifyVariableListeners() {
        for (IVariableStatusListener variableStatusListener : variableStatusListeners) {
            variableStatusListener.notifyChanged(getVariables());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#removeVariableStatusListener(org.eclipse.sirius.common.tools.api.interpreter.IVariableStatusListener)
     */
    @Override
    public void removeVariableStatusListener(IVariableStatusListener listener) {
        variableStatusListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#setProperty(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void setProperty(Object key, Object value) {
        /*
         * This is called by the framework with the FILES key in order to pass
         * us all the VSM files as a Collection.
         */
        if (IInterpreter.FILES.equals(key)) {
            if (value == null) {
                viewpointProjects.clear();
                viewpointPlugins.clear();
            } else if (value instanceof Collection<?>) {
                for (final String odesignPath : Iterables.filter((Collection<?>) value, String.class)) {
                    final URI workspaceCandidate = URI.createPlatformResourceURI(odesignPath, true);
                    final URI pluginCandidate = URI.createPlatformPluginURI(odesignPath, true);
                    if (AcceleoAbstractInterpreter.existsInWorkspace(workspaceCandidate.toPlatformString(true))) {
                        viewpointProjects.add(workspaceCandidate.segment(1));
                    } else if (AcceleoAbstractInterpreter.existsInPlugins(URI.decode(pluginCandidate.toString()))) {
                        viewpointPlugins.add(pluginCandidate.segment(1));
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#setVariable(java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void setVariable(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#unSetVariable(java.lang.String)
     */
    @Override
    public void unSetVariable(String name) {
        if (variables.containsKey(name)) {
            final List<Object> values = variables.get(name);
            if (!values.isEmpty()) {
                final ListIterator<?> iterator = values.listIterator(values.size());
                iterator.previous();
                iterator.remove();
                notifyVariableListeners();
            }
        }
    }

}

/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.Set;

/**
 * An instance which is responsible for finding classes given a qualified name
 * and a set of project names.
 * 
 * When something changes in the data which has been used for doing the class
 * loading, this instance has to notify the {@link ClasspathChangeCallback}
 * instance which could have been set using the method
 * setClasspathChangeCallback.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public interface ClassLoading {
    /**
     * Search a class using the given project names and the Java qualified name.
     * 
     * @param projects
     *            the projects to search in.
     * @param plugins
     *            the plugins to search in.
     * @param qualifiedName
     *            the qualified name of the class to find.
     * @return the class if found, null if not found.
     */
    Class<?> findClass(Set<String> projects, Set<String> plugins, String qualifiedName);

    /**
     * Invoked by the callee when this class loading utility is no longer
     * needed.
     */
    void dispose();

    /**
     * Set a {@link ClasspathChangeCallback} which should be notified by the
     * instance if something in the environment changed and means the loaded
     * classes might not be valid anymore.
     * 
     * @param listener
     *            the listener to notify when a change is detected.
     */
    void setClasspathChangeCallback(ClasspathChangeCallback listener);

    /**
     * Return the list of Ecore models which are accessibles in the classpath of
     * the given projects and plugins.
     * 
     * @param projects
     *            a set of project IDs.
     * @param plugins
     *            a set of plugin IDs
     * @return a list of descriptors for each Ecore package which is declared in
     *         the project/plugins or their dependencies.
     */
    Collection<EPackageLoadingCallback.EPackageDeclarationSource> findEcoreDeclarations(Set<String> projects, Set<String> plugins);

}

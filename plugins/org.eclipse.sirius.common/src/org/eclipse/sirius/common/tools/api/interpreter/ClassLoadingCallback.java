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

/**
 * Callback one can use to trigger some behavior when a Java extension is being
 * searched.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 */
public interface ClassLoadingCallback {
    /**
     * Called at the end of a search or update of class for the corresponding
     * qualified name.
     * 
     * This method will be called:
     * <ul>
     * <li>when the {@link JavaExtensionsManager} instance initialize a new
     * {@link Class} (a new import has been added for instance)</li>
     * <li>when the {@link JavaExtensionsManager} instances did detect that an
     * update is necessary (the class has been modified for instance) and did
     * reload it.</li>
     * </ul>
     * If the clazz has not been found, then the method "notFound" is called.
     * 
     * 
     * @param qualifiedName
     *            the Java qualified name if the class which was searched and/or
     *            reloaded.
     * @param clazz
     *            the corresponding clazz instance if it has been found.
     */
    void loaded(String qualifiedName, Class<?> clazz);

    /**
     * Called when the manager did search for a class based on a qualified name
     * but found nothing.
     * 
     * @param qualifiedName
     *            the Java qualified name which has not been found in the
     *            current scope.
     */
    void notFound(String qualifiedName);

    /**
     * Called by the manager when a class is being unloaded. This might happens:
     * <ul>
     * <li>When something in the current scope (plugin, project) has changed its
     * content, hence the class will be unloaded and loaded again.</li>
     * <li>When a name which was previously imported is not anymore.</li>
     * </ul>
     * 
     * @param qualifiedName
     *            the Java qualified name which has been unloaded.
     * @param clazz
     *            the class which has been unloaded.
     */
    void unloaded(String qualifiedName, Class<?> clazz);

}

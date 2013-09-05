/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.business.api.extension;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.acceleo.parser.interpreter.ModuleDescriptor;

/**
 * This can be used to handle additional imports for Acceleo.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public abstract class AbstractImportHandler {
    /**
     * This will be called by the Acceleo interpreter in order to determine
     * whether this import handler can handle the given dependency.
     * 
     * @param viewpointPlugins
     *            Set of available viewpoint plugins, if any.
     * @param viewpointProjects
     *            Set of available viewpoint projects in the workspace, if any.
     * @param dependency
     *            The dependency for which we are searching an handler.
     * @return <code>true</code> if this extension can handle the given
     *         dependency, <code>false</code> otherwise.
     */
    public boolean canImport(Set<String> viewpointPlugins, Set<String> viewpointProjects, String dependency) {
        return false;
    }

    /**
     * Acceleo imports can only be other Acceleo modules. This will be called by
     * the interpreter in order to get this handler to create the module
     * corresponding to the given dependency.
     * <p>
     * Note that this will only be called if {@link #canImport(String)} returns
     * <code>true</code>.
     * </p>
     * <p>
     * The nsURIs accessible to this module will be fed to it later on if the
     * created module's signature has the room for it. The import handler should
     * leave a "{0}" in the module signature to this intent.
     * </p>
     * 
     * @param viewpointPlugins
     *            Set of available viewpoint plugins, if any.
     * @param viewpointProjects
     *            Set of available viewpoint projects in the workspace, if any.
     * @param dependency
     *            The dependency for which we need to create a module.
     * @return The created module.
     */
    public ModuleDescriptor createImport(Set<String> viewpointPlugins, Set<String> viewpointProjects, String dependency) {
        return null;
    }

    /**
     * Extract the name of a module from the given String representation.
     * 
     * @param moduleString
     *            The String representation of an Acceleo module.
     * @return The name of the module represented by the given String.
     *         <code>null</code> if none.
     */
    protected static String extractModuleName(String moduleString) {
        final String modulePattern = "\\[module ([^(]+)\\("; //$NON-NLS-1$
        final Pattern pattern = Pattern.compile(modulePattern);
        final Matcher matcher = pattern.matcher(moduleString);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}

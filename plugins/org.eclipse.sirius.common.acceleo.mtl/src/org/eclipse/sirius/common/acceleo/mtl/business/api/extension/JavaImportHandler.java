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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.parser.interpreter.ModuleDescriptor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;

import com.google.common.collect.Lists;

/**
 * This import handler will try and import a dependency as a Java class
 * accessible from the classpath.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class JavaImportHandler extends AbstractImportHandler {
    
    /**
     * This will be used to "remember" the last bundle in which we found the
     * import sought by a call to {@link #canImport(Set, Set, String)} so that
     * we don't have to look it up again in
     * {@link #createImport(Set, Set, String)}.
     * <p>
     * If not <code>null</code>, this will always be a two-element array. The
     * first element of this pair is the qualified name of the sought class, the
     * second element is the bundle in which we found it.
     * </p>
     */
    private String[] lastFoundService;

    /**
     * This will return the list of all public method signatures that can be
     * retrieved from the given Class.
     * 
     * @param clazz
     *            The Class we are to check for public methods.
     * @return The list of all public method signatures that can be retrieved
     *         from the given Class.
     */
    private static List<String> getPublicSignaturesFrom(Class<?> clazz) {
        final Method[] methods = clazz.getDeclaredMethods();
        List<String> signatures = Lists.newArrayListWithCapacity(methods.length);

        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            if (Modifier.isPublic(method.getModifiers())) {
                final StringBuilder signature = new StringBuilder();
                signature.append(getTypeName(method.getReturnType())).append(' ');
                signature.append(method.getName()).append('(');
                final Class<?>[] params = method.getParameterTypes();
                for (int j = 0; j < params.length; j++) {
                    signature.append(getTypeName(params[j]));
                    if (j < (params.length - 1)) {
                        signature.append(',');
                    }
                }
                signature.append(')');
                signatures.add(signature.toString());
            }
        }

        return signatures;
    }

    private static String getTypeName(Class type) {
        String typeName = null;
        // If type is array:
        // - getTypeName from first non-array enclosing type
        // - add arrays to signature
        if (type.isArray()) {
            Class enclosingContainer = type.getComponentType();
            String arrays = "[]"; //$NON-NLS-1$
            while (enclosingContainer.isArray()) {
                arrays += "[]"; //$NON-NLS-1$
                enclosingContainer = enclosingContainer.getComponentType();
            }
            typeName = getTypeName(enclosingContainer) + arrays;
        } else {
            // If type is an inner type
            Class enclosingClass = type.getEnclosingClass();
            if (enclosingClass != null) {
                // get enclosing class type name
                typeName = getTypeName(enclosingClass) + type.getName().substring(enclosingClass.getName().length());
            } else {
                // Not an inner class: get canonical name
                typeName = type.getCanonicalName();
            }
        }
        return typeName;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler#canImport(java.util.Set,
     *      java.util.Set, java.lang.String)
     */
    @Override
    public boolean canImport(Set<String> viewpointPlugins, Set<String> viewpointProjects, String dependency) {
        if (lastFoundService != null && lastFoundService[0].equals(dependency)) {
            return true;
        }
        lastFoundService = null;

        // Can we find this in the classpath?
        boolean canImport = false;
        try {
            Class.forName(dependency);
            canImport = true;
        } catch (ClassNotFoundException e) {
            // Ignore this, simply return false
        }

        // Can we find it in the accessible plugins?
        if (!canImport) {
            for (String acessiblePlugin : viewpointPlugins) {
                final Bundle bundle = Platform.getBundle(acessiblePlugin);
                if (bundle != null) {
                    try {
                        bundle.loadClass(dependency);
                        canImport = true;
                        lastFoundService = new String[] { dependency, acessiblePlugin, };
                    } catch (ClassNotFoundException e) {
                        // Ignore this, simply return false
                    }
                }
            }
        }

        return canImport;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler#createImport(java.util.Set,
     *      java.util.Set, java.lang.String)
     */
    @Override
    public ModuleDescriptor createImport(Set<String> viewpointPlugins, Set<String> viewpointProjects, String dependency) {
        Class<?> clazz = null;
        Bundle bundle = null;
        if (lastFoundService != null && lastFoundService[0].equals(dependency)) {
            bundle = Platform.getBundle(lastFoundService[1]);
            try {
                clazz = bundle.loadClass(dependency);
            } catch (ClassNotFoundException e) {
                // Swallow and return null
            }
        } else {
            // Can we find a class going by that name in the classpath?
            try {
                clazz = Class.forName(dependency);
            } catch (ClassNotFoundException e) {
                // Swallow and return null
            }
        }

        if (clazz == null) {
            // We Should never be here : canImport() should have returned false
            return null;
        }

        final List<String> publicSignatures = getPublicSignaturesFrom(clazz);
        ModuleDescriptor module = null;
        if (!publicSignatures.isEmpty()) {
            final String moduleContent = DynamicJavaModuleCreator.createModule(dependency, publicSignatures);
            final String moduleName = extractModuleName(moduleContent);
            final URI moduleURI;
            if (bundle == null) {
                // We don't need a "resolvable" URI : the class is in the
                // classpath
                moduleURI = URI.createURI("http://acceleo.eclipse.org/" //$NON-NLS-1$
                        + moduleName + '.' + IAcceleoConstants.EMTL_FILE_EXTENSION);
            } else {
                moduleURI = URI.createPlatformPluginURI(bundle.getSymbolicName() + '/' + moduleName + '.' + IAcceleoConstants.EMTL_FILE_EXTENSION, true);
            }
            module = new ModuleDescriptor(moduleURI, dependency.replace(".", IAcceleoConstants.NAMESPACE_SEPARATOR), moduleContent); //$NON-NLS-1$
        }

        return module;
    }
}

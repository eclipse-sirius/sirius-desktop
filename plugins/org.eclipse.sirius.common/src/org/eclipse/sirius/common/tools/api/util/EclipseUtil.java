/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.common.tools.DslCommonPlugin;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

/**
 * This class is should contains useful static functions related to Eclipse
 * platform.
 * 
 * @author mchauvin
 */
public final class EclipseUtil {

    /**
     * avoid instantiation
     */
    private EclipseUtil() {
    }

    /**
     * Get the list of extensions which contribute to an extension ID.
     * 
     * @param extensionId
     *            the extension point id
     * @return a List of extensions instance
     */
    private static IExtension[] getExtensions(final String extensionId) {
        final IExtensionRegistry reg = Platform.getExtensionRegistry();
        final IExtensionPoint ep = reg.getExtensionPoint(extensionId);
        return ep.getExtensions();
    }

    /**
     * Get the list of plug-ins which contribute to an extension ID.
     * 
     * @param clazz
     *            the class that plug-ins need to extend or implement
     * @param extensionId
     *            the extension point id
     * @param attribute
     *            the attribute . Most often "class" is used
     * @param <T>
     *            the class to implements for contributors
     * @return a List of clazz instance
     */
    public static <T> List<T> getExtensionPlugins(final Class<T> clazz, final String extensionId, final String attribute) {
        return EclipseUtil.getExtensionPlugins(clazz, extensionId, attribute, null, Predicates.<String> alwaysTrue());
    }

    /**
     * Get the list of plug-ins which contribute to an extension ID, and with a
     * specific attribute value.
     * 
     * @param clazz
     *            the class that plug-ins need to extend or implement
     * @param extensionId
     *            the extension point id
     * @param executableAttribute
     *            the executable attribute . Most often "class" is used
     * @param attributeName
     *            a
     * @param exceptedAttributeValue
     *            .
     * @param <T>
     *            the class to implements for contributors
     * @return a List of clazz instance
     * @since 0.9.0
     */
    public static <T> List<T> getExtensionPlugins(final Class<T> clazz, final String extensionId, final String executableAttribute, final String attributeName, final String exceptedAttributeValue) {
        return EclipseUtil.getExtensionPlugins(clazz, extensionId, executableAttribute, attributeName, Predicates.equalTo(exceptedAttributeValue));
    }

    /**
     * Get the list of plug-ins which contribute to an extension ID, and with an
     * attribute value which corresponds to the given predicate.
     * 
     * @param clazz
     *            the class that plug-ins need to extend or implement
     * @param extensionId
     *            the extension point id
     * @param executableAttribute
     *            the executable attribute . Most often "class" is used
     * @param attributeName
     *            a
     * @param attributeValuePredicate
     *            a predicate to filter the extensions to load.
     * @param <T>
     *            the class to implements for contributors
     * @return a List of clazz instance
     * @since 0.9.0
     */
    public static <T> List<T> getExtensionPlugins(final Class<T> clazz, final String extensionId, final String executableAttribute, final String attributeName,
            final Predicate<String> attributeValuePredicate) {

        final IExtension[] extensions = EclipseUtil.getExtensions(extensionId);
        final List<T> contributors = new ArrayList<T>(extensions.length);

        for (final IExtension ext : extensions) {
            final IConfigurationElement[] ce = ext.getConfigurationElements();
            for (IConfigurationElement element : ce) {

                if (EclipseUtil.checkAttribute(element, attributeName, attributeValuePredicate)) {
                    Object obj;
                    try {
                        obj = element.createExecutableExtension(executableAttribute);
                        if (clazz.isInstance(obj)) {
                            contributors.add(clazz.cast(obj));
                        }
                    } catch (final CoreException e) {
                        DslCommonPlugin.getDefault().error("Impossible to load the extension " + ext.getLabel(), e);
                        DslCommonPlugin.getDefault().getLog().log(e.getStatus());
                    }
                }

            }
        }
        return contributors;
    }

    /**
     * Check if an attribute is correct.
     * 
     * @param element
     *            the configuration element
     * @param attributeName
     *            the name of the attribute
     * @param exceptedAttributeValue
     *            the attribute value excepted
     * @return <code>true</code> if attribute is <code>null</code> or if
     *         attribute value of configuration element is equal to the excepted
     *         one
     */
    private static boolean checkAttribute(final IConfigurationElement element, final String attributeName, final Predicate<String> exceptedAttributeValue) {
        if (attributeName != null) {
            final String namedAttribute = element.getAttribute(attributeName);
            return namedAttribute != null && (exceptedAttributeValue == null || exceptedAttributeValue.apply(namedAttribute));
        }
        return true;
    }

    /**
     * Get the files in the workspace. You may filter the returned files with
     * start and end prefixes.
     * 
     * @param prefix
     *            the prefix if needed, <code>null</code> otherwise
     * @param suffix
     *            the suffix if needed, <code>null</code> otherwise
     * @return the files in the workspace which start and end with the given
     *         prefixes
     * 
     *         examples :
     * 
     *         getFilesFromWorkspace (null, null) : return all files from the
     *         workspace getFilesFromWorkspaces (null, ".java" : return all java
     *         sources files from the workspace
     */
    public static List<IFile> getFilesFromWorkspace(final String prefix, final String suffix) {
        final List<IFile> matches = Lists.newArrayList();
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IWorkspaceRoot root = workspace.getRoot();

        try {
            root.accept(new IResourceVisitor() {
                public boolean visit(IResource resource) throws CoreException {
                    if (resource.isAccessible() && resource instanceof IFile) {
                        IFile file = (IFile) resource;
                        boolean okForPrefix = StringUtil.isEmpty(prefix) || file.getName().startsWith(prefix);
                        boolean okForSuffix = StringUtil.isEmpty(suffix) || file.getName().endsWith(suffix);
                        if (okForPrefix && okForSuffix) {
                            matches.add(file);
                        }
                    }
                    return true;
                }
            });
        } catch (final CoreException e1) {
            // do nothing -- fail silently
        }

        return matches;
    }
}

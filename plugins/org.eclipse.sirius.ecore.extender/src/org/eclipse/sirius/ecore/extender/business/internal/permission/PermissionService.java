/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.EclipsePermissionProviderDescriptor;

/**
 * This class provides activated model extensions based on the declared
 * providers.
 * 
 * @author cbrun
 * 
 */
public final class PermissionService {

    private static SortedSet<PermissionProviderDescriptor> extenderProviders = new TreeSet<PermissionProviderDescriptor>();

    static {
        PermissionService.parseExtensionMetadata();
    }

    /** Name of the extension point to parse for extender providers. */
    private static final String EXTENDER_PROVIDER_EXTENSION_POINT = "org.eclipse.sirius.ecore.extender.PermissionProvider"; //$NON-NLS-1$

    /** Externalized here to avoid too many distinct usages. */
    private static final String TAG_ENGINE = "permissionprovider"; //$NON-NLS-1$

    private PermissionService() {

    }

    /**
     * Create a new default permission authority.
     * 
     * @return a new default permission authority able to tell if yes or not an
     *         element is locked.
     */
    public static IPermissionAuthority createDefaultPermissionAuthority() {
        return new ReadOnlyPermissionAuthority();
    }

    /**
     * Wrap a permission authority to handle special rights.
     * 
     * @param authority
     *            the authority to wrap
     * @return a new wrapped permission authority
     */
    public static IPermissionAuthority wrapPermissionAuthority(final IPermissionAuthority authority) {
        return new ReadOnlyWrapperPermissionAuthority(authority);
    }

    /**
     * Create a permission authority for the given model.
     * 
     * @param resourceSet
     *            any model.
     * @return a new permission authority able to tell if yes or not an element
     *         is locked.
     */
    public static IPermissionAuthority createPermissionAuthority(final ResourceSet resourceSet) {
        IPermissionAuthority result = PermissionService.createDefaultPermissionAuthority();
        boolean foundSpecific = false;
        final Iterator<PermissionProviderDescriptor> it = PermissionService.extenderProviders.iterator();
        while (it.hasNext() && !foundSpecific) {
            PermissionProviderDescriptor permissionProviderDescriptor = it.next();
            IPermissionProvider permissionProvider = permissionProviderDescriptor.getPermissionProvider();
            if (permissionProvider.provides(resourceSet)) {
                result = PermissionService.wrapPermissionAuthority(permissionProvider.getAuthority(resourceSet));
                foundSpecific = true;
            }
        }
        return result;
    }

    /**
     * Adds a extension to contribute.
     * 
     * @param permissionProviderDescriptor
     *            The extension to contribute
     */
    public static void addExtension(PermissionProviderDescriptor permissionProviderDescriptor) {
        extenderProviders.add(permissionProviderDescriptor);
    }

    /**
     * Remove a contributed extension.
     * 
     * @param permissionProviderDescriptor
     *            The contributed extension to be removed.
     */
    public static void removeExtension(PermissionProviderDescriptor permissionProviderDescriptor) {
        extenderProviders.remove(permissionProviderDescriptor);
    }

    /**
     * This will parse the currently running platform for extensions and store
     * all the match engines that can be found.
     */
    private static void parseExtensionMetadata() {
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            final IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(PermissionService.EXTENDER_PROVIDER_EXTENSION_POINT).getExtensions();
            for (IExtension extension : extensions) {
                final IConfigurationElement[] configElements = extension.getConfigurationElements();
                for (IConfigurationElement configElement : configElements) {
                    final PermissionProviderDescriptor permissionProviderDescriptor = PermissionService.parseEngine(configElement);
                    if (permissionProviderDescriptor != null) {
                        PermissionService.extenderProviders.add(permissionProviderDescriptor);
                    }
                }
            }
        }
    }

    private static PermissionProviderDescriptor parseEngine(final IConfigurationElement configElement) {
        if (!configElement.getName().equals(PermissionService.TAG_ENGINE)) {
            return null;
        }
        String id = configElement.getDeclaringExtension().getUniqueIdentifier();
        int priority = getPriorityValue(configElement.getAttribute("priority")); //$NON-NLS-1$
        PermissionProviderDescriptor permissionProviderDescriptor = new EclipsePermissionProviderDescriptor(id, priority, configElement);
        return permissionProviderDescriptor;
    }

    /**
     * Returns the value of the priority described by the given {@link String}.<br/>
     * Returned values according to <code>priorityString</code> value :
     * <ul>
     * <li>&quot;lowest&quot; =&gt; {@link ExtenderConstants#PRIORITY_LOWEST}</li>
     * <li>&quot;low&quot; =&gt; {@link ExtenderConstants#PRIORITY_LOW}</li>
     * <li>&quot;high&quot; =&gt; {@link ExtenderConstants#PRIORITY_HIGH}</li>
     * <li>&quot;highest&quot; =&gt; {@link ExtenderConstants#PRIORITY_HIGHEST}</li>
     * <li>anything else =&gt; {@link ExtenderConstants#PRIORITY_NORMAL}</li>
     * </ul>
     * 
     * @param priorityString
     *            {@link String} value of the priority we seek.
     * @return <code>int</code> corresponding to the given priority
     *         {@link String}.
     */
    public static int getPriorityValue(final String priorityString) {
        int priorityValue = ExtenderConstants.NORMAL_PRIORITY;
        if ("lowest".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = ExtenderConstants.LOWEST_PRIORITY;
        } else if ("low".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = ExtenderConstants.LOW_PRIORITY;
        } else if ("high".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = ExtenderConstants.HIGH_PRIORITY;
        } else if ("highest".equals(priorityString)) { //$NON-NLS-1$
            priorityValue = ExtenderConstants.HIGHEST_PRIORITY;
        }
        return priorityValue;
    }

}

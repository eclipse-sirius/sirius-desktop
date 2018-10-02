/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;

/**
 * Describes a extension as contributed to the IPermissionAuthorityRegistry.
 * Contributions can comes from
 * "org.eclipse.sirius.ecore.extender.org.eclipse.sirius.ecore.extender.PermissionProvider"
 * extension point or using the StandalonePermissionProviderDescriptor.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface PermissionProviderDescriptor extends Comparable<PermissionProviderDescriptor> {

    /**
     * Get the id of the extension.
     * 
     * @return the id of the extension
     */
    String getId();

    /**
     * Get the priority of the {@link IPermissionProvider}.
     * 
     * @return the priority of the {@link IPermissionProvider}
     */
    int getPriority();

    /**
     * The concrete implementation (i.e. IPermissionProvider) of the extension.
     * 
     * @return the concrete implementation (i.e. IPermissionProvider) of the
     *         extension
     */
    IPermissionProvider getPermissionProvider();

}

/*******************************************************************************
 * Copyright (c) 2008, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.session;

/**
 * Constants for custom data.
 * 
 * @author cbrun
 */
public interface CustomDataConstants {

    /**
     * custom data key for GMF diagrams instances.
     */
    String GMF_DIAGRAMS = "GMF_DIAGRAMS"; //$NON-NLS-1$

    /**
     * custom data key for feature extensions..
     */
    String DFEATUREEXTENSION = "DFEATUREEXTENSION"; //$NON-NLS-1$

    /**
     * custom data key for representations from description. This key should be
     * used only to get data, it's a virtual key.
     */
    String DREPRESENTATION_FROM_DESCRIPTION = "DREPRESENTATION_FROM_DESCRIPTION"; //$NON-NLS-1$

    /**
     * custom data key for representation descriptors from description. This key
     * should be used only to get data, it's a virtual key.
     */
    String DREPRESENTATION_DESCRIPTOR_FROM_DESCRIPTION = "DREPRESENTATION_DESCRIPTOR_FROM_DESCRIPTION"; //$NON-NLS-1$
}

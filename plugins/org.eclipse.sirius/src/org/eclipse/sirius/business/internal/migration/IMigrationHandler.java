/*******************************************************************************
 * Copyright (c) 2018, 2021 Obeo.
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
package org.eclipse.sirius.business.internal.migration;

/**
 * The interface used to call migration process.
 * 
 * @author jmallet
 */
public interface IMigrationHandler extends org.eclipse.sirius.model.business.internal.migration.IMigrationHandler {

    /** The extension point id. */
    String ID = "org.eclipse.sirius.migrationHandler"; //$NON-NLS-1$

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

}

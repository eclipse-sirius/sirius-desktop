/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.componentization;

import org.eclipse.core.runtime.Plugin;

/**
 * The interface which list constants for org.eclipse.sirius.componentization
 * extension point.
 * 
 * This interface is not intended to be implemented
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface ISiriusComponent {

    /** The extension point ID. */
    String ID = "org.eclipse.sirius.componentization"; //$NON-NLS-1$

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /** The class. */
    Class<Plugin> CLASS_TO_EXTEND = Plugin.class;
}

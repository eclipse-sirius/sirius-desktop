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
package org.eclipse.sirius.common.tools.api.constant;

/**
 * Constants for preferences.
 * 
 * @author edugueperoux
 * 
 * @since 0.9.0
 */
public interface CommonPreferencesConstants {

    /**
     * Says if the file edit validation should be done as soon as a command is
     * executed or not.
     */
    String PREF_DEFENSIVE_EDIT_VALIDATION = "DEFENSIVE_VALIDATE_EDIT"; //$NON-NLS-1$

    /**
     * Says if the profiling in the Acceleo console should be activated or not.
     */
    String PREF_TRACE_ON = "REFRESH_PROFILING"; //$NON-NLS-1$

    /**
     * Defines the enable state of grouping content.
     */
    String PREF_GROUP_ENABLE = "GROUP_ENABLE"; //$NON-NLS-1$

    /**
     * Says if the grouping strategy uses the containing feature instead of the
     * basic hierarchy.
     */
    String PREF_GROUP_BY_CONTAINING_FEATURE = "GROUP_BY_CONTAINING_FEATURE"; //$NON-NLS-1$

    /**
     * Defines the size of children that triggers the group in sub block.
     */
    String PREF_GROUP_TRIGGER = "GROUP_TRIGGER"; //$NON-NLS-1$

    /**
     * Defines the size of elements contained in a group.
     */
    String PREF_GROUP_SIZE = "GROUP_SIZE"; //$NON-NLS-1$

}

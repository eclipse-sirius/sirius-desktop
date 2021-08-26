/*******************************************************************************
 * Copyright (c) 2013, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.preferences;

import org.eclipse.gmf.runtime.notation.JumpLinkStatus;
import org.eclipse.gmf.runtime.notation.JumpLinkType;
import org.eclipse.sirius.diagram.EdgeRouting;

/**
 * This interface declares constants corresponding to default routing style. This constant is set via the UI preference
 * page of fr.obeo.dsl.viewpoint.diagram but declared here to allow access outside UI plug-in.
 * 
 * @author lredor
 */
public interface SiriusDiagramCorePreferences {
    /**
     * Id of the preference that says if the override of connection line style is enabled or not.
     */
    String PREF_ENABLE_OVERRIDE = "Viewpoint.Connectors.enableOverride"; //$NON-NLS-1$

    /** Default value for override preference. */
    boolean PREF_ENABLE_OVERRIDE_DEFAULT_VALUE = false;

    /**
     * Id of the preference used to know the default routing style used in style of DEdge. Unlike
     * org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants .PREF_LINE_STYLE that is currently used in
     * Viewpoint as the routing style used for the creation feedback.
     */
    String PREF_LINE_STYLE = "Viewpoint.Connectors.lineStyle"; //$NON-NLS-1$

    /**
     * Default value equals to org.eclipse.gmf.runtime.notation.Routing.MANUAL or {@link EdgeRouting#STRAIGHT}.
     */
    int PREF_LINE_STYLE_DEFAULT_VALUE = EdgeRouting.STRAIGHT;

    /**
     * Id of the preference that says if the override of jump links preferences is enabled or not.
     */
    String PREF_JUMP_LINK_ENABLE_OVERRIDE = "Sirius.Connectors.JumpLink.enableOverride"; //$NON-NLS-1$

    /**
     * Default value for override of jump link preference, equals to @false@.
     */
    boolean PREF_JUMP_LINK_ENABLE_OVERRIDE_DEFAULT_VALUE = false;

    /**
     * Id of the preference used to know the default jump link status if the override of connection jump links
     * preferences is enabled.
     */
    String PREF_JUMP_LINK_STATUS = "Sirius.Connectors.JumpLink.status"; //$NON-NLS-1$

    /**
     * Default value equals to {@link JumpLinkStatus#NONE}.
     */
    int PREF_JUMP_LINK_STATUS_DEFAULT_VALUE = JumpLinkStatus.NONE;

    /**
     * Id of the preference used to know the default jump link type if the override of connection jump links preferences
     * is enabled.
     */
    String PREF_JUMP_LINK_TYPE = "Sirius.Connectors.JumpLink.type"; //$NON-NLS-1$

    /**
     * Default value equals to {@link JumpLinkType#SEMICIRCLE}.
     */
    int PREF_JUMP_LINK_TYPE_DEFAULT_VALUE = JumpLinkType.SEMICIRCLE;

    /**
     * Id of the preference used to know if reverse of the jump link is enabled or not, if the override of connection
     * jump links preferences is enabled.
     */
    String PREF_REVERSE_JUMP_LINK = "Sirius.Connectors.JumpLink.reverse"; //$NON-NLS-1$

    /**
     * Default value for reverse jump link preference.
     */
    boolean PREF_REVERSE_JUMP_LINK_DEFAULT_VALUE = false;

}

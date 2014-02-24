/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.preferences;

import org.eclipse.sirius.diagram.EdgeRouting;

/**
 * This interface declares constants corresponding to default routing style.
 * This constant is set via the UI preference page of
 * fr.obeo.dsl.viewpoint.diagram but declared here to allow access outside UI
 * plug-in.
 * 
 * @author lredor
 */
public interface SiriusDiagramCorePreferences {
    /**
     * Id of the preference that says if the override of connection line style
     * is enabled or not.
     */
    String PREF_ENABLE_OVERRIDE = "Viewpoint.Connectors.enableOverride"; //$NON-NLS-1$

    /** Default value for override preference. */
    boolean PREF_ENABLE_OVERRIDE_DEFAULT_VALUE = false;

    /**
     * Id of the preference used to know the default routing style used in style
     * of DEdge. Unlike
     * org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants
     * .PREF_LINE_STYLE that is currently used in Viewpoint as the routing style
     * used for the creation feedback.
     */
    String PREF_LINE_STYLE = "Viewpoint.Connectors.lineStyle"; //$NON-NLS-1$

    /**
     * Default value equals to org.eclipse.gmf.runtime.notation.Routing.MANUAL
     * or {@link EdgeRouting#STRAIGHT}.
     */
    int PREF_LINE_STYLE_DEFAULT_VALUE = EdgeRouting.STRAIGHT;
}

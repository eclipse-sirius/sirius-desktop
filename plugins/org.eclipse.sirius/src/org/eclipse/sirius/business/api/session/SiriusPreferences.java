/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
 * This API provides a set of preferences used in the context of a Sirius {@link Session}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public interface SiriusPreferences {

    /**
     * Tells if the refresh should automatically be done. The result is based on Eclipse Preference API.The lookup order
     * is the Scope of the Project, then the default Eclipse preference look-up order.
     * 
     * @return the value
     */
    boolean isAutoRefresh();

    /**
     * Set this preference in the Project scope and associates it to the Session.
     * 
     * @param value
     *            the value
     */
    void setAutoRefresh(boolean value);

    /**
     * Unset this preference from the Project scope.
     */
    void unsetAutoRefresh();

    /**
     * Returns whether the session has a specific setting for the automatic refresh.
     * 
     * @return the value
     */
    boolean hasSpecificSettingAutoRefresh();

    /**
     * Tells if the refresh should automatically be done on representation opening.<br/>
     * The result is based on Eclipse Preference API.The lookup order is the Scope of the Project, then the default
     * Eclipse preference look-up order.
     * 
     * @return the value
     */
    boolean isRefreshOnRepresentationOpening();

    /**
     * Set this preference in the Project scope and associates it to the Session.
     * 
     * @param value
     *            the value
     */
    void setRefreshOnRepresentationOpening(boolean value);

    /**
     * Unset this preference from the Project scope.
     */
    void unsetRefreshOnRepresentationOpening();

    /**
     * Returns whether the session has a specific setting for the refresh on representation opening.
     * 
     * @return the value
     */
    boolean hasSpecificSettingRefreshOnRepresentationOpening();

}

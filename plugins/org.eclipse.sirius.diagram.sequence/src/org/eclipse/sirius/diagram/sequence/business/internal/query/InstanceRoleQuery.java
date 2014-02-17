/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import org.eclipse.sirius.diagram.business.api.diagramtype.HeaderData;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.RGBValues;

import com.google.common.base.Preconditions;

/**
 * General queries on {@link InstanceRole}s.
 * 
 * @author lredor
 */
public class InstanceRoleQuery {
    /**
     * The Instance role to query.
     */
    protected final InstanceRole instanceRole;

    /**
     * Constructor.
     * 
     * @param instanceRole
     *            the instance role to query.
     */
    public InstanceRoleQuery(InstanceRole instanceRole) {
        this.instanceRole = Preconditions.checkNotNull(instanceRole);
    }

    /**
     * Get the header data corresponding to this instance role.
     * 
     * @return the header data corresponding to this instance role.
     */
    public HeaderData getHeaderData() {
        Option<RGBValues> optionalBackgroundColor = instanceRole.getBackgroundColor();
        Option<RGBValues> optionalLabelColor = instanceRole.getLabelColor();
        return new HeaderData(instanceRole.getName(), instanceRole.getBounds().x, instanceRole.getBounds().width, optionalBackgroundColor.get(), optionalLabelColor.get());
    }
}

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
package org.eclipse.sirius.business.internal.session.danalysis;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * An {@link Adapter} adding a link from a {@link DRepresentation} to its {@link DRepresentationDescriptor}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 *
 */
public class DRepresentationDescriptorAdapter extends AdapterImpl {

    private DRepresentationDescriptor dRepresentationDescriptor;

    /**
     * Default constructor.
     * 
     * @param dRepresentationDescriptor
     *            the {@link DRepresentationDescriptor} to reference
     */
    public DRepresentationDescriptorAdapter(DRepresentationDescriptor dRepresentationDescriptor) {
        this.dRepresentationDescriptor = dRepresentationDescriptor;
    }

    /**
     * Access the referenced {@link DRepresentationDescriptor}.
     * 
     * @return the referenced {@link DRepresentationDescriptor}
     */
    public DRepresentationDescriptor getdRepresentationDescriptor() {
        return dRepresentationDescriptor;
    }
}
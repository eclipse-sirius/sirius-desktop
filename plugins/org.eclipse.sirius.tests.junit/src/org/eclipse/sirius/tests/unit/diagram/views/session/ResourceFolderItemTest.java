/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.views.session;

import org.eclipse.sirius.ui.tools.internal.views.common.item.ResourcesFolderItemImpl;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class ResourceFolderItemTest extends AbstractFolderItemTest<ResourcesFolderItemImpl> {
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourcesFolderItemImpl createTestedItem(Object parent) {
        return new ResourcesFolderItemImpl(null, parent);
    }
}

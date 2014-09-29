/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.views.session;

import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointsFolderItemImpl;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class ViewpointsFolderItemTest extends AbstractFolderItemTest<ViewpointsFolderItemImpl> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected ViewpointsFolderItemImpl createTestedItem(Object parent) {
        return new ViewpointsFolderItemImpl(null, parent);
    }

}

/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.layout.data;

import java.util.Iterator;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.ConfigurationFactory;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class LayoutHelperImplEdgeLayoutDataTest extends AbstractLayoutHelperImplTest<EdgeLayoutData> {

    private final class EdgeLayoutDataWrapper extends LayoutDataWrapper {
        /**
         * @param layoutData
         */
        private EdgeLayoutDataWrapper(final EdgeLayoutData layoutData) {
            super(layoutData);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean doEquals(final EdgeLayoutData otherLayoutData) {
            return LayoutHelper.INSTANCE.haveSameLayout(getThisLayoutData(), otherLayoutData, ConfigurationFactory.buildConfiguration());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractLayoutHelperImplTest<EdgeLayoutData>.LayoutDataWrapper createWrappedInstance(final EdgeLayoutData from) throws Exception {
        // Do not use SiriusCopier here as we want to copy the id in the layout data tree.
        final EdgeLayoutData nodeLayoutData = (EdgeLayoutData) EcoreUtil.copy(from);
        return new EdgeLayoutDataWrapper(nodeLayoutData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractLayoutHelperImplTest<EdgeLayoutData>.LayoutDataWrapper createWrappedNotEqualInstance() throws Exception {
        final Iterator<EdgeLayoutData> iterator = getManager().getEdgeLayoutData().values().iterator();
        iterator.next();
        return new EdgeLayoutDataWrapper(iterator.next());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EdgeLayoutData getReferenceLayoutData() {
        return getManager().getEdgeLayoutData().values().iterator().next();
    }

}

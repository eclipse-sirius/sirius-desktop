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
package org.eclipse.sirius.tests.unit.diagram.layout.data;

import java.util.Iterator;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.ConfigurationFactory;

import com.google.common.collect.Iterables;

/**
 * Test class.
 * 
 * @author dlecan
 */
public abstract class AbstractLayoutHelperImplNodeLayoutDataTest extends AbstractLayoutHelperImplTest<NodeLayoutData> {

    private final class NodeLayoutDataWrapper extends LayoutDataWrapper {
        /**
         * @param layoutData
         */
        private NodeLayoutDataWrapper(final NodeLayoutData layoutData) {
            super(layoutData);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean doEquals(final NodeLayoutData otherLayoutData) {
            return LayoutHelper.INSTANCE.haveSameLayout(getThisLayoutData(), otherLayoutData, ConfigurationFactory.buildConfiguration());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractLayoutHelperImplTest<NodeLayoutData>.LayoutDataWrapper createWrappedInstance(final NodeLayoutData from) throws Exception {
        final NodeLayoutData nodeLayoutData = (NodeLayoutData) EcoreUtil.copy(from);
        return new NodeLayoutDataWrapper(nodeLayoutData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractLayoutHelperImplTest<NodeLayoutData>.LayoutDataWrapper createWrappedNotEqualInstance() throws Exception {
        final Iterator<? extends NodeLayoutData> iterator = getManager().getRootNodeLayoutData().values().iterator();
        iterator.next();
        return new NodeLayoutDataWrapper(iterator.next());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeLayoutData getReferenceLayoutData() {
        return Iterables.get(getManager().getRootNodeLayoutData().values(), getIndexOfReferenceLayoutData());
    }

    /**
     * Get position of reference layout data.
     * 
     * @return Position of reference layout data.
     */
    protected abstract int getIndexOfReferenceLayoutData();

}

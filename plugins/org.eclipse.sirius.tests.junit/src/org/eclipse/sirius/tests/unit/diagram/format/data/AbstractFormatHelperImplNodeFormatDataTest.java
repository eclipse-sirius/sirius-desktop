/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.Iterator;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;

import com.google.common.collect.Iterables;

/**
 * Test class.
 * 
 * @author dlecan
 */
public abstract class AbstractFormatHelperImplNodeFormatDataTest extends AbstractFormatHelperImplTest<NodeFormatData> {

    private final class NodeFormatDataWrapper extends FormatDataWrapper {
        /**
         * @param formatData
         */
        private NodeFormatDataWrapper(final NodeFormatData formatData) {
            super(formatData);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean doEquals(final NodeFormatData otherFormatData) {
            return FormatHelper.INSTANCE.haveSameLayout(getThisFormatData(), otherFormatData, ConfigurationFactory.buildConfiguration());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractFormatHelperImplTest<NodeFormatData>.FormatDataWrapper createWrappedInstance(final NodeFormatData from) throws Exception {
        final NodeFormatData nodeFormatData = (NodeFormatData) EcoreUtil.copy(from);
        return new NodeFormatDataWrapper(nodeFormatData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractFormatHelperImplTest<NodeFormatData>.FormatDataWrapper createWrappedNotEqualInstance() throws Exception {
        final Iterator<? extends NodeFormatData> iterator = getManager().getRootNodeFormatData().values().iterator();
        iterator.next();
        return new NodeFormatDataWrapper(iterator.next());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NodeFormatData getReferenceFormatData() {
        return Iterables.get(getManager().getRootNodeFormatData().values(), getIndexOfReferenceFormatData());
    }

    /**
     * Get position of reference format data.
     * 
     * @return Position of reference format data.
     */
    protected abstract int getIndexOfReferenceFormatData();

}

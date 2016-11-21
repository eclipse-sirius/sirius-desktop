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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class FormatHelperImplEdgeFormatDataTest extends AbstractFormatHelperImplTest<EdgeFormatData> {

    private final class EdgeFormatDataWrapper extends FormatDataWrapper {
        /**
         * @param formatData
         */
        private EdgeFormatDataWrapper(final EdgeFormatData formatData) {
            super(formatData);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean doEquals(final EdgeFormatData otherFormatData) {
            return FormatHelper.INSTANCE.haveSameLayout(getThisFormatData(), otherFormatData, ConfigurationFactory.buildConfiguration());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractFormatHelperImplTest<EdgeFormatData>.FormatDataWrapper createWrappedInstance(final EdgeFormatData from) throws Exception {
        final EdgeFormatData nodeFormatData = (EdgeFormatData) EcoreUtil.copy(from);
        return new EdgeFormatDataWrapper(nodeFormatData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractFormatHelperImplTest<EdgeFormatData>.FormatDataWrapper createWrappedNotEqualInstance() throws Exception {
        List<EdgeFormatData> formatDataList = new ArrayList<EdgeFormatData>();
        Collection<Map<String, EdgeFormatData>> formatDataMap = getManager().getEdgeFormatData().values();
        for (Map<String, EdgeFormatData> valueMap : formatDataMap) {
            formatDataList.addAll(valueMap.values());
        }
        return new EdgeFormatDataWrapper(formatDataList.get(1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EdgeFormatData getReferenceFormatData() {
        List<EdgeFormatData> formatDataList = new ArrayList<EdgeFormatData>();
        Collection<Map<String, EdgeFormatData>> formatDataMap = getManager().getEdgeFormatData().values();
        for (Map<String, EdgeFormatData> valueMap : formatDataMap) {
            formatDataList.addAll(valueMap.values());
        }
        return formatDataList.get(0);
    }

}

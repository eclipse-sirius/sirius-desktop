/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.internal.session.analysis;

import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorProvider;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.api.session.analysis.SmartDialogAnalysisSelector;

/**
 * Default provided {@link DAnalysisSelectorProvider} to provide the {@link SmartDialogAnalysisSelector}.
 * 
 * @author edugueperoux
 */
public class SmartDialogAnalysisSelectorProvider implements DAnalysisSelectorProvider {

    /**
     * Provides for all {@link DAnalysisSession}.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean provides(DAnalysisSession session) {
        return true;
    }

    /**
     * provides the {@link SmartDialogAnalysisSelector}.
     * 
     * {@inheritDoc}
     */
    @Override
    public DAnalysisSelector getSelector(DAnalysisSession session) {
        return new SmartDialogAnalysisSelector();
    }

    @Override
    public int getPriority() {
        // Lowest priority except but higher than DefaultAnalysisSelectorProvider.
        return 1;
    }
}

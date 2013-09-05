/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * Default provided {@link DAnalysisSelectorProvider} to provide the
 * {@link SmartDialogAnalysisSelector}.
 * 
 * @author edugueperoux
 */
public class SmartDialogAnalysisSelectorProvider implements DAnalysisSelectorProvider {

    /**
     * Provides for all {@link DAnalysisSession}.
     * 
     * {@inheritDoc}
     */
    public boolean provides(DAnalysisSession session) {
        return true;
    }

    /**
     * provides the {@link SmartDialogAnalysisSelector}.
     * 
     * {@inheritDoc}
     */
    public DAnalysisSelector getSelector(DAnalysisSession session) {
        return new SmartDialogAnalysisSelector();
    }

}

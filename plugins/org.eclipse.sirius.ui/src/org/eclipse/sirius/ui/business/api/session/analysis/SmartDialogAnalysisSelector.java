/*******************************************************************************
 * Copyright (c) 2008-2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.api.session.analysis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.tools.api.dialogs.AnalysisSelectorFilteredItemsSelectionDialog;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A dialog which select smartly analysis.
 *
 * @author mchauvin
 */
public class SmartDialogAnalysisSelector implements DAnalysisSelector {
    
    /**
     * The dialog that displays the analysis where the representation will be placed.
     */
    protected AnalysisSelectorFilteredItemsSelectionDialog dialog;

    @Override
    public DAnalysis selectSmartlyAnalysisForAddedResource(final Resource resource, final Collection<DAnalysis> allAnalysis) {
        return selectSmartlyAnalysis(allAnalysis, null);
    }

    @Override
    public DAnalysis selectSmartlyAnalysisForAddedRepresentation(final DRepresentation representation, final Collection<DAnalysis> allAnalysis) {
        DAnalysis selectSmartlyAnalysis = selectSmartlyAnalysis(allAnalysis, representation.getName());
        if (selectSmartlyAnalysis == null) {
            DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();
            selectSmartlyAnalysis = (DAnalysis) new EObjectQuery(representationDescriptor).getFirstAncestorOfType(ViewpointPackage.eINSTANCE.getDAnalysis()).get();
        }
        return selectSmartlyAnalysis;
    }

    /**
     * Asks the user to select the analysis.
     *
     * @param allAnalysis
     *            all available analysis
     * @return selected analysis
     */
    private DAnalysis selectSmartlyAnalysis(final Collection<DAnalysis> allAnalysis, String representationName) {


        RunnableWithResult<Object> runnable = new RunnableWithResult.Impl<Object>() {

            @Override
            public void run() {
                
                dialog = createAnalysisSelectorDialog(Display.getDefault().getActiveShell(), allAnalysis.iterator().next(), allAnalysis, new ArrayList<>(allAnalysis));
                dialog.setSeparatorLabel(Messages.SmartDialogAnalysisSelector_otherFragments);
                if (representationName != null && !representationName.isEmpty()) {
                    dialog.setTitle(MessageFormat.format(Messages.SmartDialogAnalysisSelector_titleWithRepresentationName, representationName));
                    dialog.setMessage(MessageFormat.format(Messages.SmartDialogAnalysisSelector_messageWithRepresentationName, representationName));
                } else {
                    dialog.setTitle(Messages.SmartDialogAnalysisSelector_titleWithoutRepresentationName);
                    dialog.setMessage(Messages.SmartDialogAnalysisSelector_messageWithoutRepresentationName);
                }

                if (allAnalysis.iterator().next() != null) {
                    dialog.setInitialElementSelections(Collections.singletonList(allAnalysis.iterator().next()));
                }

                if (dialog.open() == Window.OK) {
                    if (dialog.getFirstResult() != null) {
                        setResult(dialog.getFirstResult());
                    }
                } else {
                    // Box closed by cancel, ESC key ...
                    setResult(null);
                }
            }
        };
        /* synch execution as the user need to choose before we can get further */
        EclipseUIUtil.displaySyncExec(runnable);
        if (runnable.getResult() instanceof DAnalysis) {
            return (DAnalysis) runnable.getResult();
        }
        return null;
    }

    /**
     * Initializes the dialog that will be used for selecting the targeted {@link DAnalysis}.
     * 
     * @param shell
     *            shell to parent the dialog on
     * @param bestCandidate
     *            the best candidate that will be selected by default
     * @param allAnalysis
     *            all the analysis available
     * @param bestCandidates
     *            list of best candidates
     * @return the dialog that will be used for selecting the targeted {@link DAnalysis}
     */
    protected AnalysisSelectorFilteredItemsSelectionDialog createAnalysisSelectorDialog(Shell shell, DAnalysis bestCandidate, Collection<DAnalysis> allAnalysis, List<DAnalysis> bestCandidates) {
        return new AnalysisSelectorFilteredItemsSelectionDialog(Display.getDefault().getActiveShell(), allAnalysis.iterator().next(), allAnalysis, new ArrayList<>(allAnalysis));
    }

}

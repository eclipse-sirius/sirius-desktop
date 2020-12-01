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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.tools.api.dialogs.AnalysisSelectorFilteredItemsSelectionDialog;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
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
        DAnalysis selectSmartlyAnalysis = selectSmartlyAnalysis(allAnalysis, representation);
        // The dialog may be canceled with Esc key but Sirius does not support a null DAnalysis
        if (selectSmartlyAnalysis == null) {
            selectSmartlyAnalysis = allAnalysis.iterator().next();
        }
        return selectSmartlyAnalysis;
    }

    /**
     * Asks the user to select the analysis.
     *
     * @param allAnalysis
     *            all available analysis
     * @param representation
     *            the representation
     * @return selected analysis
     */
    protected DAnalysis selectSmartlyAnalysis(final Collection<DAnalysis> allAnalysis, DRepresentation representation) {

        RunnableWithResult<Object> runnable = new RunnableWithResult.Impl<Object>() {

            @Override
            public void run() {

                dialog = createAnalysisSelectorDialog(Display.getDefault().getActiveShell(), getDefaultDAnalysis(allAnalysis, representation), allAnalysis, new ArrayList<>(allAnalysis),
                        representation);
                dialog.setSeparatorLabel(Messages.SmartDialogAnalysisSelector_otherFragments);
                String representationName = representation.getName();
                if (representationName != null && !representationName.isEmpty()) {
                    dialog.setTitle(MessageFormat.format(Messages.SmartDialogAnalysisSelector_titleWithRepresentationName, representationName));
                    dialog.setMessage(MessageFormat.format(Messages.SmartDialogAnalysisSelector_messageWithRepresentationName, representationName));
                    dialog.setMoveRepresentation(true);
                } else {
                    dialog.setTitle(Messages.SmartDialogAnalysisSelector_titleWithoutRepresentationName);
                    dialog.setMessage(Messages.SmartDialogAnalysisSelector_messageWithoutRepresentationName);
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
        return (DAnalysis) runnable.getResult();
    }

    /**
     * Get the default DAnalysis.
     * 
     * @param allAnalysis
     *            All candidate DAnalysis.
     * @param representation
     *            The representation.
     * @return The default DAnalysis.
     */
    protected DAnalysis getDefaultDAnalysis(Collection<DAnalysis> allAnalysis, DRepresentation representation) {
        DAnalysis defaultDAnalysis = null;
        if (allAnalysis.size() > 0) {
            EObject targetElement = ((DSemanticDecorator) representation).getTarget();
            Resource targetResource = targetElement.eResource();

            // If different analysis candidates are available, sort them smartly to ease the end-user selection.

            // Loop over analysis candidates.
            for (DAnalysis candidateAnalysis : allAnalysis) {
                for (EObject semanticElement : candidateAnalysis.getModels()) {
                    // Is semantic element contains in the same resource ?
                    if (targetResource.equals(semanticElement.eResource())) {
                        // Yes it is :)
                        return candidateAnalysis;
                    }
                }
            }
            defaultDAnalysis = allAnalysis.iterator().next();
        }
        return defaultDAnalysis;
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
     * @param representation
     *            the representation for which to select the DAnalysis
     * @return the dialog that will be used for selecting the targeted {@link DAnalysis}
     */
    protected AnalysisSelectorFilteredItemsSelectionDialog createAnalysisSelectorDialog(Shell shell, DAnalysis bestCandidate, Collection<DAnalysis> allAnalysis, List<DAnalysis> bestCandidates,
            DRepresentation representation) {
        return new AnalysisSelectorFilteredItemsSelectionDialog(Display.getDefault().getActiveShell(), allAnalysis.iterator().next(), allAnalysis, new ArrayList<>(allAnalysis), false);
    }

}

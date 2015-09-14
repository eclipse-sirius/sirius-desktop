/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.dialect;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.HeaderData;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.ui.provider.Messages;

import com.google.common.collect.Lists;

/**
 * Command to refresh the graphical layout of the whole diagram.
 * 
 * @author edugueperoux
 */
public class SetBestHeightHeaderCommand extends RecordingCommand {

    private Diagram diagram;

    /**
     * Default constructor.
     * 
     * @param diagram
     *            {@link Diagram} to refresh, used also to access
     *            {@link SequenceDDiagram} & {@link SequenceDiagram} to refresh
     * 
     * 
     *            {@inheritDoc}
     */
    public SetBestHeightHeaderCommand(TransactionalEditingDomain domain, Diagram diagram) {
        super(domain, Messages.SetBestHeightHeaderCommand_label);
        this.diagram = diagram;
    }

    /**
     * Overridden to refresh the sequence layout.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        LinkedList<HeaderData> headerDatas = Lists.newLinkedList();
        if (diagram.getElement() instanceof DDiagram) {
            DDiagram dDiagram = (DDiagram) diagram.getElement();
            for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(dDiagram.getDescription().eClass().getEPackage())) {
                    headerDatas = diagramTypeDescriptor.getDiagramDescriptionProvider().getHeaderData(dDiagram);
                    break;
                }
            }
            int nbLines = getNbLinesNeeded(headerDatas);
            dDiagram.setHeaderHeight(nbLines);
        }
    }

    /**
     * Get number of lines that is needed to display entirely all the header
     * labels according to the current widths.
     * 
     * @return the number of lines needed to display entirely all labels.
     */
    private int getNbLinesNeeded(List<HeaderData> headerDatas) {
        int maxNbLines = 1;
        for (HeaderData headerData : headerDatas) {
            int nbLines = SWTUtil.getNbLines(headerData.getName(), headerData.getWidth());
            if (maxNbLines < nbLines) {
                maxNbLines = nbLines;
            }
        }
        return maxNbLines;
    }
}

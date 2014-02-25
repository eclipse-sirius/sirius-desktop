/******************************************************************************
 * Copyright (c) 2004, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Laurent Redor    (Obeo) <laurent.redor@obeo.fr>      - Trac bug #1182 : OBEO_0420 : Import aird dans desc
 *    Mariot Chauvin   (Obeo) <mariot.chauvin@obeo.fr>  - Trac task #1044 : Refactor "repair / migrate model" action
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.part;

import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IDiagramPreferenceSupport;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility class to generate editpart containment offscreen without creating an
 * editor. <br>
 * This class is override for a problem in the default
 * {@link DiagramGraphicalViewer} so we therefore use our
 * {@link SiriusDiagramGraphicalViewer} in place
 * 
 * @author sshaw
 */
public class OffscreenEditPartFactory extends org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory {

    private static final OffscreenEditPartFactory INSTANCE = new OffscreenEditPartFactory();

    /**
     * gives access to the singleton instance of
     * <code>OffscreenEditPartFactory</code>.
     * 
     * @return the singleton instance
     */
    public static OffscreenEditPartFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a <code>DiagramEditPart</code> given the <code>Diagram</code>
     * without opening an editor.
     * 
     * @param diagram
     *            the <code>Diagram</code>
     * @param shell
     *            the shell
     * @param preferencesHint
     *            the preferences hint to be used when creating the diagram; if
     *            null, the preferences hint from the root editpart will be
     *            used.
     * @return the new populated <code>DiagramEditPart</code>
     */
    @Override
    public DiagramEditPart createDiagramEditPart(final Diagram diagram, final Shell shell, final PreferencesHint preferencesHint) {

        final DiagramGraphicalViewer customViewer = new SiriusDiagramGraphicalViewer();
        customViewer.createControl(shell);

        final DiagramEditDomain editDomain = new DiagramEditDomain(null);
        editDomain.setCommandStack(new DiagramCommandStack(editDomain));

        customViewer.setEditDomain(editDomain);

        // hook in preferences
        final RootEditPart rootEP = EditPartService.getInstance().createRootEditPart(diagram);
        if (rootEP instanceof IDiagramPreferenceSupport) {
            final PreferencesHint hint = handlePreferencesHint((IDiagramPreferenceSupport) rootEP, preferencesHint);
            customViewer.hookWorkspacePreferenceStore((IPreferenceStore) hint.getPreferenceStore());
        }

        customViewer.setRootEditPart(rootEP);

        customViewer.setEditPartFactory(EditPartService.getInstance());

        DiagramEventBroker.startListening(TransactionUtil.getEditingDomain(diagram));

        customViewer.setContents(diagram);
        customViewer.flush();

        return (DiagramEditPart) customViewer.getContents();

    }

    private PreferencesHint handlePreferencesHint(final IDiagramPreferenceSupport support, final PreferencesHint hint) {
        PreferencesHint prefs = hint;
        if (prefs == null) {
            prefs = support.getPreferencesHint();
        } else {
            support.setPreferencesHint(prefs);
        }
        return prefs;
    }

}

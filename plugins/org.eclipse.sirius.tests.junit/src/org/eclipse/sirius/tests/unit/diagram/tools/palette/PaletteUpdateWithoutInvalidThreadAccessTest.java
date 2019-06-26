/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools.palette;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * A test ensuring that when the palette gets updated (e.g. when a filter has
 * been installed and enables/disables tools), there is no
 * "invalid thread access" problem.
 * 
 * @author lredor</a>
 * 
 */
public class PaletteUpdateWithoutInvalidThreadAccessTest extends AbstractPaletteManagerTest {

    static final String SEMANTIC_MODEL_FILENAME = "VP-4519.ecore";

    static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_MODEL_FILENAME;

    static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "VP-4519.odesign";

    static final String SESSION_MODEL_FILENAME = "VP-4519.aird";

    static final String SESSION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_MODEL_FILENAME;

    static final String REPRESENTATION_DESC_NAME = "rep0";

    private DDiagramEditorImpl editor;

    private PaletteRoot paletteRoot;

    /**
     * Ensures that tools are changed when the result of a filter is changed
     * (caused by a layer deactivation).
     */
    public void testPaletteUpdateOnLayerActivationWithToolFilter() {
        doOpenEditorOnRepresentation(getRepresentationDescriptionName());
        Set<PaletteEntry> initialPaletteEntries = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("The last tool should be \"t1\" before optional layer deactivation.", "t1", Iterators.getLast(initialPaletteEntries.iterator()).getLabel());
        // Disable the optional layer: Call as is in
        // org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.LayersActivationAction.run()
        final Layer firstOptionalLayer = DiagramComponentizationTestSupport.getAllLayers(session, dDiagram.getDescription()).get(1);
        try {
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
                    Command changeActivatedLayersCmd = new ChangeLayerActivationCommand(domain, dDiagram, firstOptionalLayer, monitor);
                    domain.getCommandStack().execute(changeActivatedLayersCmd);
                }
            };
            new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(true, false, runnable);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e.getCause());
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (doesAnErrorOccurs()) {
            fail("There is problem after layer deactivation:" + getErrorLoggersMessage());
        }

        Set<PaletteEntry> paletteEntriesAfterFilterDisablement = getAllVisiblePaletteEntries(paletteRoot);
        assertEquals("As the layer deactivation changes the filter result, the last tool should be \"t0\" now.", "t0", Iterators.getLast(paletteEntriesAfterFilterDisablement.iterator()).getLabel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_MODEL_PATH);
        SessionUIManager.INSTANCE.createUISession(session);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.ViewpointTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        // Closing the editor opened during setUp
        if (editor != null) {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
            editor = null;
            paletteRoot = null;
        }
        super.tearDown();
    }

    /**
     * Opens a {@link DDiagramEditor} on the representation which description is
     * equal to the given representationDescriptionName.
     * 
     * @param representationDescriptionName
     *            the name of the {@link RepresentationDescription} of the
     *            representation to open
     */
    protected void doOpenEditorOnRepresentation(String representationDescriptionName) {
        // Open an editor on the tested diagram
        Collection<DRepresentationDescriptor> representationDescriptors = getRepresentationDescriptors(getRepresentationDescriptionName());
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (representationDescriptor.getName().equals(getRepresentationDescriptionInstanceName())) {
                dDiagram = (DDiagram) representationDescriptor.getRepresentation();
                break;
            }
        }
        editor = (DDiagramEditorImpl) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        paletteRoot = ((DiagramEditDomain) editor.getDiagramEditDomain()).getPaletteViewer().getPaletteRoot();
        diagram = (Diagram) Iterables.get(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, dDiagram), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_DESC_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionInstanceName() {
        return "new " + REPRESENTATION_DESC_NAME;
    }

}

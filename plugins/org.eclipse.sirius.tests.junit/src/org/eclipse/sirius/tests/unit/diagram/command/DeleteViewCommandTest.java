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
package org.eclipse.sirius.tests.unit.diagram.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

public class DeleteViewCommandTest extends SiriusDiagramTestCase implements EcoreModeler {

    private final ArrayList<View> viewsHavingDeleteCommand = new ArrayList<View>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
    }

    /**
     * Check that there is only one command for each view.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testDeleteViewCommand() throws Exception {
        DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, "Child");

        for (DDiagramElement dDiagramElement : diagramElementsFromLabel) {

            Command command = getEditPart(dDiagramElement).getCommand(new GroupRequest(RequestConstants.REQ_DELETE));

            if (command instanceof CompoundCommand) {
                CompoundCommand cc = (CompoundCommand) command;
                findViewsHavingDeleteCommand(cc);
            }
        }
        assertTrue("There are duplicate DeleteCommand on same View element", checkNoDuplicateDeleteCommandOnViews());
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Check that there is only one command for each view.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testDeleteViewFromDiagramCommand() throws Exception {
        DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, "Child");

        for (DDiagramElement dDiagramElement : diagramElementsFromLabel) {
            Command command = getEditPart(dDiagramElement).getCommand(new GroupRequest(RequestConstants.REQ_DELETE_FROM_DIAGRAM));

            if (command instanceof CompoundCommand) {
                CompoundCommand cc = (CompoundCommand) command;
                findViewsHavingDeleteCommand(cc);
            }
        }
        assertTrue("There are duplicate DeleteCommand on same View element", checkNoDuplicateDeleteCommandOnViews());
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Analyze CompoundCommand to find any DeleteCommand.
     * 
     * @param compoundCommand
     */
    private void findViewsHavingDeleteCommand(CompoundCommand compoundCommand) {
        Iterator<?> commandsIterator = compoundCommand.getCommands().iterator();
        while (commandsIterator.hasNext()) {
            Object object = commandsIterator.next();
            if (object instanceof DeleteCommand) {
                DeleteCommand deleteCommand = (DeleteCommand) object;
                findViewsHavingDeleteCommand(deleteCommand);
            } else if (object instanceof CompoundCommand) {
                CompoundCommand cc = (CompoundCommand) object;
                findViewsHavingDeleteCommand(cc);
            } else if (object instanceof ICommandProxy) {
                ICommandProxy commandProxy = (ICommandProxy) object;
                ICommand iCommand = commandProxy.getICommand();
                if (iCommand instanceof CompositeCommand) {
                    findViewsHavingDeleteCommand((CompositeCommand) iCommand);
                }
            }
        }
    }

    private void findViewsHavingDeleteCommand(CompositeCommand command) {
        Iterator<?> compositeCommandIterator = command.iterator();
        while (compositeCommandIterator.hasNext()) {
            findViewsHavingDeleteCommand(compositeCommandIterator.next());
        }
    }

    /**
     * Analyze CompositeTransactionalCommand to find any DeleteCommand.
     * 
     * @param compositeTransactionalCommand
     */
    private void findViewsHavingDeleteCommand(CompositeTransactionalCommand compositeTransactionalCommand) {
        Iterator<?> ctcIterator = compositeTransactionalCommand.iterator();
        while (ctcIterator.hasNext()) {
            findViewsHavingDeleteCommand(ctcIterator.next());
        }
    }

    private void findViewsHavingDeleteCommand(Object command) {
        if (command instanceof CompositeTransactionalCommand) {
            findViewsHavingDeleteCommand((CompositeTransactionalCommand) command);
        } else if (command instanceof CompositeCommand) {
            findViewsHavingDeleteCommand((CompositeCommand) command);
        } else if (command instanceof DeleteCommand) {
            findViewsHavingDeleteCommand((DeleteCommand) command);
        }
    }

    /**
     * Analyze DeleteCommand to add its View item to viewsHavingDeleteCommand
     * array list.
     * 
     * @param deleteCommand
     */
    private void findViewsHavingDeleteCommand(DeleteCommand deleteCommand) {
        try {
            Field declaredField = deleteCommand.getClass().getDeclaredField("view");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                Object object = declaredField.get(deleteCommand);
                if (object instanceof View) {
                    View view = (View) object;
                    viewsHavingDeleteCommand.add(view);
                }
                declaredField.setAccessible(false);
            }
        } catch (SecurityException e) {
            SiriusTestsPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusTestsPlugin.PLUGIN_ID, "SecurityException while accessing view item of a DeleteCommand", e));
        } catch (NoSuchFieldException e) {
            SiriusTestsPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusTestsPlugin.PLUGIN_ID, "NoSuchFieldException while accessing view item of a DeleteCommand", e));
        } catch (IllegalArgumentException e) {
            SiriusTestsPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusTestsPlugin.PLUGIN_ID, "IllegalArgumentException while accessing view item of a DeleteCommand", e));
        } catch (IllegalAccessException e) {
            SiriusTestsPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusTestsPlugin.PLUGIN_ID, "IllegalAccessException while accessing view item of a DeleteCommand", e));
        }
    }

    /**
     * Check for duplicates View element entries.
     * 
     * @return if there is no duplicates
     */
    private boolean checkNoDuplicateDeleteCommandOnViews() {
        if (viewsHavingDeleteCommand != null && !viewsHavingDeleteCommand.isEmpty()) {
            Collection<View> viewSet = new HashSet<View>(viewsHavingDeleteCommand);
            return viewSet.size() == viewsHavingDeleteCommand.size();
        }
        return true;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}

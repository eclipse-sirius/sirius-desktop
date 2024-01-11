/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.support.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.command.TreeCommandFactoryService;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.DTreeItemEditingSupport;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * The main test case for tree unit tests.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien Dupont</a>
 */
public abstract class TreeTestCase extends SiriusTestCase {

    /**
     * Create view message.
     */
    protected static final String CREATE_VIEW_MESSAGE = "create view from testcase";

    /**
     * Incorrect data message.
     */
    protected static final String THE_UNIT_TEST_DATA_SEEMS_INCORRECT = "The unit test data seems incorrect";

    /**
     * Error message for test initialization.
     */
    protected static final String INIT_ERROR_MSG = "An error occurs during tests initialization";

    /**
     * Tree command factory.
     */
    private ITreeCommandFactory treeCommandFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Setting no UI callback
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());

    }

    @Override
    protected ITreeCommandFactory getCommandFactory() {
        if (treeCommandFactory == null) {
            treeCommandFactory = TreeCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
            treeCommandFactory.setModelAccessor(accessor);
        }
        return treeCommandFactory;
    }

    /**
     * Apply a deletion tool on a diagram element.
     * 
     * @param element
     *            the diagram element
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyDeletionTool(final DTreeElement element) {
        boolean result = false;
        Command cmd = getCommandFactory().buildDeleteTreeElement(element);
        result = cmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        return result;
    }

    protected final boolean applyCreationTool(final DTreeItemContainer lineContainer, final EObject semanticCurrentElement, final TreeItemCreationTool tool) {
        boolean result = false;
        Command cmd = getCommandFactory().buildCreateLineCommandFromTool(lineContainer, semanticCurrentElement, tool);
        result = cmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        return result;
    }

    protected final boolean applyOperationAction(final OperationAction operationAction, final DTreeItem treeItem) {
        boolean result = false;
        Command cmd = getCommandFactory().buildOperationActionFromTool(operationAction, treeItem);
        result = cmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        return result;
    }

    /**
     * Apply a creation description tool on a diagram element.
     * 
     * @param element
     *            the diagram element
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyCreationDescriptionTool(final RepresentationCreationDescription creationDescription, final DTreeElement element, final String representationName) {
        boolean result = false;
        Command cmd = getCommandFactory().buildCreateRepresentationFromDescription(creationDescription, element, representationName);
        result = cmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        return result;
    }

    protected final boolean applyRefresh(DRepresentation representation) {
        boolean result = false;
        Command command = new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, representation);
        result = command.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        return result;
    }

    /**
     * Apply an undo on command.
     */
    protected void applyUndo() {
        session.getTransactionalEditingDomain().getCommandStack().undo();
    }

    /**
     * Apply a redo on command
     */
    protected void applyRedo() {
        session.getTransactionalEditingDomain().getCommandStack().redo();
    }

    /**
     * Return the searched treeDescription.
     * 
     * @param group
     *            A group
     * @param name
     *            The name of the searched tree description
     * @return The searched TreeDescription of null
     */
    protected TreeDescription find(final String name) {
        final Iterator<RepresentationDescription> it = (viewpoints.iterator().next()).getOwnedRepresentations().iterator();
        while (it.hasNext()) {
            final RepresentationDescription next = it.next();
            if (next instanceof TreeDescription) {
                if (name.equals(((TreeDescription) next).getName())) {
                    return (TreeDescription) next;
                }
            }
        }
        return null;
    }

    /**
     * Apply a direct edit tool on a DTreeItem.
     * 
     * @param tree
     *            the tree
     * @param element
     *            the tree item * @param value the new value to set
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyDirectEditTool(final DTree tree, final DTreeItem element, final String value) {
        // We use reflexion to access to the DTreeItemEditingSupport
        // which contain all logic of Direct Edit tools (especially
        // preconditions)
        AbstractDTreeEditor editor = (AbstractDTreeEditor) EclipseUIUtil.getActiveEditor();
        TreeViewer viewer = (TreeViewer) editor.getViewer();
        DTreeItemEditingSupport support = new DTreeItemEditingSupport(viewer, session.getTransactionalEditingDomain(), this.accessor, getCommandFactory(), editor);

        Class<?> supportClass = DTreeItemEditingSupport.class;
        try {
            Method canEdit = supportClass.getDeclaredMethod("canEdit", Object.class);
            Method setValue = supportClass.getDeclaredMethod("setValue", Object.class, Object.class);
            canEdit.setAccessible(true);
            setValue.setAccessible(true);

            if ((Boolean) canEdit.invoke(support, element)) {
                setValue.invoke(support, element, value);
                return true;
            }
            return false;

        } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("No Edition tool defined on TreeItem '" + element.getName() + "'");
        }    
    }

    /**
     * Returns the representation element from the semantic one, having the
     * given name.
     * 
     * @param representation
     *            the representation
     * @param semanticElement
     *            the semantic element
     * @param representationElementName
     *            the name of the searched representation element
     * @return the representation element from the semantic one, having the
     *         given name
     */
    protected final DRepresentationElement getRepresentationElementWithName(final DRepresentation representation, String representationElementName) {
        for (final DRepresentationElement element : representation.getRepresentationElements()) {
            if (representationElementName.equals(element.getName())) {
                return element;
            }
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        treeCommandFactory = null;

        super.tearDown();
    }
}

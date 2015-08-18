/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.clipboard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.common.ui.util.CustomData;
import org.eclipse.gmf.runtime.common.ui.util.ICustomData;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.providers.ImageSupportGlobalActionHandler;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.query.PasteTargetQuery;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.tools.internal.command.builders.PasteCommandBuilder;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.CopyToSiriusClipboardCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.PasteFromSiriusClipboardCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.delete.DeleteWrapperHookExecutorCommand;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Handle for clipboard action.
 * 
 * It allows Sirius to make a semantic copy/cut/paste for semantic elements and
 * to let GFM handle notes.
 * 
 * @author glefur
 * 
 */
public class SiriusClipboardGlobalActionHandler extends ImageSupportGlobalActionHandler {

    /**
     * Start of the error message when more than one valid paste description.
     */
    private static final String MORE_THAN_ONE_PASTE_DESCRIPTION_ERROR_MSG = "There are more than one paste description that match the target container";

    /**
     * Keep trace of the paste command computed for canPaste : avoid duplicated
     * command buildong operation and mouse location change between.
     */
    private ICommand pasteCommand;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.AbstractGlobalActionHandler#canHandle(IGlobalActionContext)
     */
    public boolean canHandle(IGlobalActionContext cntxt) {

        /*
         * Check if the active part is a IDiagramWorkbenchPart and if the
         * selection is a structured selection
         */
        IWorkbenchPart part = cntxt.getActivePart();
        if (!(part instanceof IDiagramWorkbenchPart) || !(cntxt.getSelection() instanceof IStructuredSelection)) {
            return false;
        }

        /* Check the action id */
        boolean canHandle = false;
        String actionId = cntxt.getActionId();
        if (actionId.equals(GlobalActionId.COPY)) {
            canHandle = canCopy(cntxt);
        } else if (actionId.equals(GlobalActionId.CUT)) {
            canHandle = canCut(cntxt);
        } else if (actionId.equals(GlobalActionId.PASTE)) {
            canHandle = canPaste(cntxt);
        }
        return canHandle;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.render.internal.providers.ImageSupportGlobalActionHandler
     *      #canPaste(org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext)
     */
    @Override
    public boolean canPaste(IGlobalActionContext cntxt) {
        boolean canPaste = false;
        pasteCommand = null;
        Set<DSemanticDecorator> dSelection = extractDSelection(cntxt);
        if (!dSelection.isEmpty() && cntxt.getActivePart() instanceof IDiagramWorkbenchPart) {
            IDiagramWorkbenchPart activePart = (IDiagramWorkbenchPart) cntxt.getActivePart();
            canPaste = SiriusClipboardManager.getInstance().hasPasteData() || haveNoteToPaste(cntxt);
            if (canPaste) {
                pasteCommand = getPasteCommand(cntxt, activePart);
                canPaste = pasteCommand != null && pasteCommand.canExecute();
            }
        }

        return canPaste;
    }

    private boolean haveNoteToPaste(IGlobalActionContext cntxt) {
        if (super.canPaste(cntxt)) {
            Request pasteReq = createPasteViewRequest();

            // CreateViewRequest is internal in 3.3 and api in 3.5
            Method method = null;
            try {
                method = pasteReq.getClass().getMethod("getData"); //$NON-NLS-1$
            } catch (SecurityException e) {
                // No method, no data
            } catch (NoSuchMethodException e) {
                // No method, no data
            }

            if (method != null) {
                try {
                    Object data = method.invoke(pasteReq);
                    boolean safeData = data != null;
                    if (data instanceof ICustomData[]) {
                        ICustomData[] datas = (ICustomData[]) data;
                        for (ICustomData cusData : datas) {
                            safeData = safeData && cusData != null;

                            if (safeData && cusData instanceof CustomData) {
                                // safeData = safeData && ((CustomData)
                                // cusData).getData() == null;
                                byte[] noteData = ((CustomData) cusData).getData();
                                safeData = safeData && noteData != null && noteData.length != 0;
                            }
                        }
                    }
                    return safeData;
                } catch (IllegalArgumentException e) {
                    // No access, no data
                } catch (IllegalAccessException e) {
                    // No access, no data
                } catch (InvocationTargetException e) {
                    // No access, no data
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.providers.DiagramGlobalActionHandler
     *      #canCopy(org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext)
     */
    @Override
    protected boolean canCopy(IGlobalActionContext cntxt) {
        boolean canCopy = false;

        Set<DSemanticDecorator> dselection = extractDSelection(cntxt);
        canCopy = !dselection.isEmpty() || hasSelectedNotes(cntxt);

        return canCopy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICommand getCommand(IGlobalActionContext cntxt) {
        ICommand command = null;
        if (GlobalActionId.PASTE.equals(cntxt.getActionId()) && cntxt.getActivePart() instanceof IDiagramWorkbenchPart) {
            command = getPasteCommand(cntxt, (IDiagramWorkbenchPart) cntxt.getActivePart());
        } else if (GlobalActionId.CUT.equals(cntxt.getActionId()) && cntxt.getActivePart() instanceof IDiagramWorkbenchPart) {
            command = getCutCommand(cntxt, (IDiagramWorkbenchPart) cntxt.getActivePart());
        } else {
            command = super.getCommand(cntxt);
        }
        return command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ICommand getCopyCommand(IGlobalActionContext cntxt, IDiagramWorkbenchPart diagramPart, boolean isUndoable) {
        // GMF Command to handle notes and image copy for windows.
        ICommand gmfCommand = super.getCopyCommand(cntxt, diagramPart, isUndoable);

        Session session = getSession(diagramPart);
        if (session != null) {
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            Set<DSemanticDecorator> dSelection = extractDSelection(cntxt);
            Set<EObject> semanticSelection = extractSemanticSelection(dSelection);

            minimizeSelection(semanticSelection);

            ICommand cmd = UnexecutableCommand.INSTANCE;
            if (gmfCommand != null) {
                cmd = new CompositeTransactionalCommand(domain, gmfCommand.getLabel());
                cmd.compose(gmfCommand);
                cmd.compose(new GMFCommandWrapper(domain, new CopyToSiriusClipboardCommand(domain, dSelection, semanticSelection)));
            }
            return cmd;
        }

        return gmfCommand;
    }

    /**
     * Overridden to hook deleteHooks on cut command.
     * 
     * {@inheritDoc}
     */
    @Override
    protected ICommand getCutCommand(IGlobalActionContext cntxt, IDiagramWorkbenchPart diagramPart) {
        ICommand cutCommand = super.getCutCommand(cntxt, diagramPart);
        if (cutCommand != null && cutCommand.canExecute()) {
            Session session = getSession(diagramPart);
            if (session != null) {
                TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                Set<DSemanticDecorator> dSelection = extractDSelection(cntxt);
                cutCommand = new DeleteWrapperHookExecutorCommand(domain, dSelection, cutCommand);
            }
        }
        return cutCommand;
    }

    private void minimizeSelection(Set<? extends EObject> selection) {
        for (EObject selected : Sets.newLinkedHashSet(selection)) {
            EObject cont = selected.eContainer();
            while (cont != null) {
                if (selection.contains(cont)) {
                    selection.remove(selected);
                    cont = null;
                } else {
                    cont = cont.eContainer();
                }
            }
        }
    }

    /**
     * Specific paste command for viewpoint.
     */
    private ICommand getPasteCommand(IGlobalActionContext cntxt, IDiagramWorkbenchPart activePart) {
        if (pasteCommand != null) {
            return pasteCommand;
        }
        ICommand result = UnexecutableCommand.INSTANCE;
        Session session = getSession(activePart);
        DDiagram diag = getDiagram(activePart);
        Set<DSemanticDecorator> dSelection = extractDSelection(cntxt);
        if (session != null && diag != null && !dSelection.isEmpty()) {
            boolean valid = true;
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            CompositeTransactionalCommand cc = new CompositeTransactionalCommand(domain, "Paste ...");
            valid = fillCompositePasteCommand(cc, domain, dSelection, cntxt, activePart);

            // Append GMF Not paste command
            appendNotePasteCommand(cc, cntxt);

            if (valid) {
                result = cc;
                pasteCommand = cc;
            }
        }
        return result;
    }

    private void appendNotePasteCommand(CompositeTransactionalCommand cc, IGlobalActionContext cntxt) {
        if (haveNoteToPaste(cntxt)) {
            Request pasteReq = createPasteViewRequest();

            /* Get the selected edit parts */
            for (IGraphicalEditPart part : Iterables.filter(((IStructuredSelection) cntxt.getSelection()).toList(), IGraphicalEditPart.class)) {
                /*
                 * Send the request to the target edit part of the paste command
                 * for the currently selected part
                 */
                EditPart targetEP = part.getTargetEditPart(pasteReq);
                if (targetEP != null) {
                    Command paste = targetEP.getCommand(pasteReq);
                    if (paste != null) {
                        cc.compose(new CommandProxy(paste));
                    }
                }
            }
        }
    }

    private boolean hasSelectedNotes(IGlobalActionContext cntxt) {
        if (cntxt.getSelection() instanceof IStructuredSelection) {
            for (IGraphicalEditPart ideep : Iterables.filter(((IStructuredSelection) cntxt.getSelection()).toList(), IGraphicalEditPart.class)) {
                Object model = ideep.getModel();
                if (model instanceof Node && GMFNotationHelper.isNote((Node) model)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean fillCompositePasteCommand(CompositeTransactionalCommand cc, TransactionalEditingDomain domain, Set<DSemanticDecorator> dSelection, IGlobalActionContext cntxt,
            IDiagramWorkbenchPart activePart) {
        boolean valid = true;
        Iterator<DSemanticDecorator> dSelIt = dSelection.iterator();
        while (valid && dSelIt.hasNext()) {
            DSemanticDecorator pasteTarget = dSelIt.next();
            valid = fillCompositePasteCommand(pasteTarget, cc, domain, cntxt, activePart);
        }
        return valid;
    }

    private boolean fillCompositePasteCommand(DSemanticDecorator pasteTarget, CompositeTransactionalCommand cc, TransactionalEditingDomain domain, IGlobalActionContext cntxt,
            IDiagramWorkbenchPart activePart) {
        boolean valid = true;

        if (SiriusClipboardManager.getInstance().hasPasteData()) {
            Collection<PasteDescription> tools = new PasteTargetQuery(pasteTarget).getAvailablePasteTools();
            if (tools == null || tools.isEmpty()) {
                valid = appendGenericPasteCommand(pasteTarget, cc, domain);
            } else {
                valid = appendToolPasteCommands(pasteTarget, cc, domain, activePart, tools);
            }
        }

        addLayoutDataCommand(cc, activePart, cntxt);

        return valid;
    }

    private boolean appendToolPasteCommands(DSemanticDecorator pasteTarget, CompositeTransactionalCommand cc, TransactionalEditingDomain domain, IDiagramWorkbenchPart activePart,
            Collection<PasteDescription> tools) {
        boolean valid = true;

        // Fill current domain clipboard.
        SiriusClipboardManager.getInstance().setDomainClipboard(domain);

        // Init context
        Collection<DSemanticDecorator> dCopies = Sets.newLinkedHashSet();
        Collection<EObject> dTargetcopies = Sets.newLinkedHashSet();
        Collection<EObject> strictSemanticCopies = Sets.newLinkedHashSet();
        fillPasteContext(domain.getClipboard(), dCopies, dTargetcopies, strictSemanticCopies);

        final IDiagramCommandFactory factory = getDiagramCommandFactory(activePart, domain);

        valid = appendStrictSemanticToolPasteCommands(pasteTarget, cc, domain, tools, strictSemanticCopies, factory);
        valid = valid && appendStandardToolPasteCommands(pasteTarget, cc, domain, tools, dCopies, factory);

        domain.setClipboard(null);
        return valid;
    }

    private boolean appendStandardToolPasteCommands(DSemanticDecorator pasteTarget, CompositeTransactionalCommand cc, TransactionalEditingDomain domain, Collection<PasteDescription> tools,
            Collection<DSemanticDecorator> dCopies, final IDiagramCommandFactory factory) {
        for (DSemanticDecorator semDecCopy : dCopies) {
            PasteDescription tool = getBestPasteTool(pasteTarget, semDecCopy, semDecCopy.getTarget(), tools);
            if (tool != null) {
                cc.compose(new GMFCommandWrapper(domain, factory.buildPasteCommandFromTool(pasteTarget, semDecCopy, tool)));
            } else {
                // No valid tool -> no paste
                return false;
            }
        }
        return true;
    }

    private boolean appendStrictSemanticToolPasteCommands(DSemanticDecorator pasteTarget, CompositeTransactionalCommand cc, TransactionalEditingDomain domain, Collection<PasteDescription> tools,
            Collection<EObject> strictSemanticCopies, final IDiagramCommandFactory factory) {
        for (EObject strictSemanticCopy : strictSemanticCopies) {
            PasteDescription tool = getBestPasteTool(pasteTarget, null, strictSemanticCopy, tools);
            if (tool != null) {
                cc.compose(new GMFCommandWrapper(domain, factory.buildPasteCommandFromTool(pasteTarget, strictSemanticCopy, tool)));
            } else {
                // No valid tool -> no paste
                return false;
            }
        }
        return true;
    }

    private void fillPasteContext(Collection<Object> clipboard, Collection<DSemanticDecorator> dCopies, Collection<EObject> dTargetcopies, Collection<EObject> strictSemanticCopies) {
        if (clipboard == null) {
            // No viewpoint data in clipboard.
            return;
        }

        for (EObject obj : Iterables.filter(clipboard, EObject.class)) {
            if (obj instanceof DSemanticDecorator) {
                DSemanticDecorator semDecCopy = (DSemanticDecorator) obj;
                if (semDecCopy.getTarget() != null && isTargetInClipboard(clipboard, semDecCopy)) {
                    dCopies.add(semDecCopy);
                    dTargetcopies.add(semDecCopy.getTarget());
                }
            } else {
                strictSemanticCopies.add(obj);
            }
        }
        Iterables.removeAll(strictSemanticCopies, dTargetcopies);
    }

    private boolean isTargetInClipboard(Collection<Object> clipboard, DSemanticDecorator semDecCopy) {
        EObject target = semDecCopy.getTarget();
        boolean inClipboard = clipboard.contains(target);

        // Minimized semantic selection for copy : check parents.
        if (!inClipboard) {
            EObject targetContainer = EcoreUtil.getRootContainer(target);
            inClipboard = clipboard.contains(targetContainer);
        }

        return inClipboard;
    }

    private boolean appendGenericPasteCommand(DSemanticDecorator pasteTarget, CompositeTransactionalCommand cc, TransactionalEditingDomain domain) {
        // Generic behavior for all clipboard
        boolean valid = true;
        if (safeDecorator(pasteTarget)) {
            org.eclipse.emf.common.command.Command pasteFromClipboard = new PasteFromSiriusClipboardCommand(domain, pasteTarget.getTarget());
            cc.setLabel(pasteFromClipboard.getLabel());
            cc.compose(new GMFCommandWrapper(domain, pasteFromClipboard));
        } else {
            valid = false;
        }
        return valid;
    }

    private PasteDescription getBestPasteTool(DSemanticDecorator pasteTarget, DSemanticDecorator semDecCopy, EObject copy, Collection<PasteDescription> tools) {
        final Collection<PasteDescription> candidates = Sets.newLinkedHashSet();
        for (PasteDescription tool : tools) {
            if (PasteCommandBuilder.checkPastePrecondition(tool, copy, pasteTarget.getTarget(), pasteTarget, semDecCopy)) {
                candidates.add(tool);
            }
        }
        PasteDescription bestTool = null;
        for (final PasteDescription pasteTool : candidates) {
            if (bestTool == null) {
                bestTool = pasteTool;
            } else {
                SiriusPlugin.getDefault().warning(MORE_THAN_ONE_PASTE_DESCRIPTION_ERROR_MSG + " : " + pasteTarget.getTarget() + " (" + bestTool.getName() + " and " + pasteTool.getName() + ").",
                        new RuntimeException());
            }
        }

        return bestTool;
    }

    private IDiagramCommandFactory getDiagramCommandFactory(IDiagramWorkbenchPart activePart, TransactionalEditingDomain domain) {
        DDiagramEditor diagramEditor = (DDiagramEditor) activePart.getDiagramGraphicalViewer().getProperty(DDiagramEditor.EDITOR_ID);
        Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        return cmdFactoryProvider.getCommandFactory(domain);
    }

    private Set<EObject> extractSemanticSelection(Collection<DSemanticDecorator> selection) {
        Set<EObject> selectedSemanticElements = new LinkedHashSet<EObject>();
        for (DSemanticDecorator dec : selection) {
            if (safeDecorator(dec)) {
                selectedSemanticElements.add(dec.getTarget());
            }
        }
        return selectedSemanticElements;
    }

    private Set<DSemanticDecorator> extractDSelection(IGlobalActionContext cntxt) {
        Set<DSemanticDecorator> dSelection = new LinkedHashSet<DSemanticDecorator>();
        if (cntxt.getSelection() instanceof IStructuredSelection) {
            for (IGraphicalEditPart ideep : Iterables.filter(((IStructuredSelection) cntxt.getSelection()).toList(), IGraphicalEditPart.class)) {
                Object model = ideep.getModel();
                if (model instanceof View && ((View) model).getElement() instanceof DSemanticDecorator) {
                    dSelection.add((DSemanticDecorator) ((View) model).getElement());
                }
            }
        }
        return dSelection;
    }

    private boolean safeDecorator(DSemanticDecorator dec) {
        boolean safe = dec.getTarget() != null;
        if (dec instanceof DEdge) {
            Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(((DEdge) dec).getActualMapping()).getEdgeMapping();
            if (!(edgeMapping.some() && edgeMapping.get().isUseDomainElement())) {
                safe = false;
            }
        }
        return safe;
    }

    private Session getSession(IDiagramWorkbenchPart diagramPart) {
        Session session = null;
        if (diagramPart != null && diagramPart.getDiagram() != null && diagramPart.getDiagram().getElement() instanceof DDiagram) {
            DSemanticDecorator diagram = (DSemanticDecorator) diagramPart.getDiagram().getElement();
            session = SessionManager.INSTANCE.getSession(diagram.getTarget());
        }

        return session;
    }

    private DDiagram getDiagram(IDiagramWorkbenchPart diagramPart) {
        DDiagram diag = null;
        if (diagramPart != null && diagramPart.getDiagram() != null && diagramPart.getDiagram().getElement() instanceof DDiagram) {
            diag = (DDiagram) diagramPart.getDiagram().getElement();
        }

        return diag;
    }

    private void addLayoutDataCommand(CompositeTransactionalCommand cc, IDiagramWorkbenchPart activePart, final IGlobalActionContext cntxt) {
        final Set<DSemanticDecorator> dSelection = extractDSelection(cntxt);
        if (dSelection.size() == 1 && cntxt.getSelection() instanceof IStructuredSelection) {
            final Point cursorLocation = getCursorLocation(activePart, ((IStructuredSelection) cntxt.getSelection()).iterator().next());
            cc.compose(new AbstractCommand("Add layout data") {

                @Override
                protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
                    return CommandResult.newOKCommandResult();
                }

                @Override
                protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
                    return CommandResult.newOKCommandResult();
                }

                @Override
                protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
                    DSemanticDecorator target = dSelection.iterator().next();
                    if (target instanceof DEdge) {
                        DDiagram parentDiagram = ((DEdge) target).getParentDiagram();
                        if (parentDiagram instanceof DSemanticDecorator) {
                            target = (DSemanticDecorator) parentDiagram;
                        }
                    }
                    SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(target, cursorLocation, new Dimension(-1, -1)));
                    return CommandResult.newOKCommandResult();
                }
            });
        }
    }

    /**
     * @param object
     * 
     */
    private Point getCursorLocation(IDiagramWorkbenchPart activePart, Object selectedPart) {
        Point loc = null;
        Display current = Display.getCurrent();
        IDiagramGraphicalViewer viewer = activePart.getDiagramGraphicalViewer();
        if (viewer != null && viewer.getControl() instanceof FigureCanvas && current != null) {
            final org.eclipse.swt.graphics.Point cursorLocation = current.getCursorLocation();
            final FigureCanvas control = (FigureCanvas) viewer.getControl();
            final org.eclipse.swt.graphics.Point relativeSWTPoint = control.toControl(cursorLocation);

            final Point point = new Point(relativeSWTPoint.x, relativeSWTPoint.y);
            Point after = point.getCopy();
            control.getViewport().translateFromParent(after);

            final double zoom = ((ScalableFreeformRootEditPart) viewer.getRootEditPart()).getZoomManager().getZoom();
            loc = after.getScaled(1 / zoom);

            if (!(selectedPart instanceof IDDiagramEditPart)) {
                IGraphicalEditPart part = (IGraphicalEditPart) selectedPart;
                // useLocalCoordinate == false : translate will do nothing
                Point parentLoc = part.getFigure().getBounds().getLocation().getCopy();
                GraphicalHelper.logical2screen(parentLoc, part);
                control.getViewport().translateFromParent(parentLoc);
                loc = loc.getTranslated(parentLoc.getNegated());
            }
        }
        return loc;
    }

}

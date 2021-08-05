/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.part;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ITraversalStrategy;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusMarkerNavigationProvider;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusValidationProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.marker.SiriusMarkerNavigationProviderSpec;
import org.eclipse.sirius.diagram.ui.tools.internal.part.OffscreenEditPartFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tools.api.validation.constraint.RuleWrappingStatus;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;

/**
 * @was-generated
 */
public class ValidateAction extends Action {

    /**
     * @was-generated
     */
    public static final String VALIDATE_ACTION_KEY = "validateAction"; //$NON-NLS-1$

    /**
     * @was-generated
     */
    private IWorkbenchPartDescriptor workbenchPartDescriptor;

    /**
     * @was-generated
     */
    public ValidateAction(IWorkbenchPartDescriptor workbenchPartDescriptor) {
        setId(VALIDATE_ACTION_KEY);
        setText(Messages.ValidateActionMessage);
        this.workbenchPartDescriptor = workbenchPartDescriptor;
    }

    /**
     * @was-generated
     */
    @Override
    public void run() {
        IWorkbenchPart workbenchPart = workbenchPartDescriptor.getPartPage().getActivePart();
        if (workbenchPart instanceof IDiagramWorkbenchPart) {
            final IDiagramWorkbenchPart part = (IDiagramWorkbenchPart) workbenchPart;
            try {
                new WorkspaceModifyDelegatingOperation(new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
                        runValidation(part.getDiagramEditPart(), part.getDiagram());
                    }
                }).run(new NullProgressMonitor());
            } catch (Exception e) {
                DiagramPlugin.getDefault().logError(org.eclipse.sirius.diagram.ui.provider.Messages.ValidateAction_failureMessage, e);
            }
        }
    }

    /**
     * @was-generated NOT
     */
    public static void runValidation(View view) {
        try {
            if (SiriusDiagramEditorUtil.openDiagram(view.eResource())) {
                IEditorPart editorPart = EclipseUIUtil.getActiveEditor();
                if (editorPart instanceof IDiagramWorkbenchPart) {
                    runValidation(((IDiagramWorkbenchPart) editorPart).getDiagramEditPart(), view);
                } else {
                    runNonUIValidation(view);
                }
            }
        } catch (Exception e) {
            DiagramPlugin.getDefault().logError(org.eclipse.sirius.diagram.ui.provider.Messages.ValidateAction_failureMessage, e);
        }
    }

    /**
     * @was-generated
     */
    public static void runNonUIValidation(View view) {
        Shell shell = new Shell();
        DiagramEditPart diagramEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(view.getDiagram(), shell);
        runValidation(diagramEditPart, view);
        shell.dispose();
    }

    /**
     * @was-generated
     */
    public static void runValidation(DiagramEditPart diagramEditPart, View view) {
        final DiagramEditPart fpart = diagramEditPart;
        final View fview = view;
        SiriusValidationProvider.runWithConstraints(view, new Runnable() {

            @Override
            public void run() {
                validate(fpart, fview);
            }
        });
    }

    private static Diagnostic runEMFValidator(final EObject eObject) {
        return new Diagnostician() {

            @Override
            public String getObjectLabel(EObject eObject) {
                return EMFCoreUtil.getQualifiedName(eObject, true);
            }
        }.validate(eObject);
    }

    /**
     * @was-generated NOT it is not necessary to call the createMarkers operation since the markers are automatically
     *                added for EMF validation. <br/>
     *                Validation is run on view elements and its children.<br/>
     */
    private static void validate(DiagramEditPart diagramEditPart, View view) {
        IFile target = getFileToMark(diagramEditPart, view);
        if (target != null) {
            SiriusMarkerNavigationProvider.deleteMarkers(target);
        }

        if (view.isSetElement()) {
            final EObject element = view.getElement();
            if (element != null) {

                IBatchValidator validator = createBatchValidator();
                IStatus status = validator.validate(element);
                createMarkers(target, status, diagramEditPart);

                if (element instanceof DSemanticDecorator) {
                    List<EObject> elementsToValidate = new ArrayList<>();
                    final EObject semanticElement = ((DSemanticDecorator) element).getTarget();

                    elementsToValidate.add(semanticElement);

                    createMarkers(target, runEMFValidator(semanticElement), diagramEditPart);

                    for (final EObject representationElement : AllContents.of(view.getElement(), ViewpointPackage.eINSTANCE.getDRepresentationElement())) {
                        elementsToValidate.addAll(((DRepresentationElement) representationElement).getSemanticElements());
                    }

                    validator.setTraversalStrategy(new ITraversalStrategy.Flat());
                    IStatus status2 = validator.validate(elementsToValidate);
                    createMarkers(target, status2, diagramEditPart);
                }
            }
        }
    }

    /*
     * The marker should reference the main aird file, to be sure to load all the user session when clicking on the
     * marker if the corresponding session is closed (referenced analysis cases or CDO for which the main aird is local
     * file with information concerning the server).
     * @was-generated NOT
     */
    private static IFile getFileToMark(DiagramEditPart diagramEditPart, View view) {
        Resource targetResource = view.eResource();
        if (diagramEditPart instanceof IDDiagramEditPart) {
            Option<DDiagram> ddiagram = ((IDDiagramEditPart) diagramEditPart).resolveDDiagram();
            Session session = ddiagram.some() && ddiagram.get() instanceof DSemanticDiagram ? SessionManager.INSTANCE.getSession(((DSemanticDiagram) ddiagram.get()).getTarget()) : null;
            if (session != null) {
                targetResource = session.getSessionResource();
            }
        }
        IFile target = targetResource != null ? WorkspaceSynchronizer.getFile(targetResource) : null;
        return target;
    }

    private static IBatchValidator createBatchValidator() {
        IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
        validator.setIncludeLiveConstraints(true);
        return validator;

    }

    /**
     * @was-generated-NOT
     */
    @SuppressWarnings("rawtypes")
    private static void createMarkers(IFile target, IStatus validationStatus, DiagramEditPart diagramEditPart) {
        if (validationStatus.isOK()) {
            return;
        }
        final IStatus rootStatus = validationStatus;
        List allStatuses = new ArrayList();
        collectTargetElements(rootStatus, new HashSet(), allStatuses);
        for (Iterator it = allStatuses.iterator(); it.hasNext();) {
            IConstraintStatus nextStatus = (IConstraintStatus) it.next();
            View view = getCorrespondingView(nextStatus.getTarget(), diagramEditPart);
            String qualifiedName = EMFCoreUtil.getQualifiedName(nextStatus.getTarget(), true);
            if (nextStatus instanceof RuleWrappingStatus) {
                createValidationRuleMarker((RuleWrappingStatus) nextStatus, diagramEditPart.getViewer(), target, view, qualifiedName, nextStatus.getMessage(), nextStatus.getSeverity());
            } else {
                addMarker(diagramEditPart.getViewer(), target, view, qualifiedName, nextStatus.getMessage(), nextStatus.getSeverity());
            }
        }
    }

    private static View getCorrespondingView(EObject element, DiagramEditPart diagramEditPart) {
        DSemanticDecorator dSemanticDecorator = getDSemanticDecorator(element, diagramEditPart);
        View view = null;
        if (dSemanticDecorator != null) {
            view = SiriusGMFHelper.getGmfView(dSemanticDecorator);
        }
        if (view == null) {
            view = diagramEditPart.getDiagramView();
        }
        return view;
    }

    private static void createValidationRuleMarker(RuleWrappingStatus nextStatus, EditPartViewer viewer, IFile target, View view, String location, String message, int statusSeverity) {
        if (target == null) {
            return;
        }
        String elementId = view.eResource().getURIFragment(view);
        // Search semantic URI
        String semanticURI = null;
        EObject ddiagramElement = view.getElement();
        if (ddiagramElement instanceof DSemanticDecorator) {
            EObject semanticElement = ((DSemanticDecorator) ddiagramElement).getTarget();
            semanticURI = EcoreUtil.getURI(semanticElement).toString();
        }
        // Search diagram URI
        Object object = viewer.getFocusEditPart().getModel();
        String diagramDescriptorUri = getDRepresentationDescriptorURIFromDiagram(object);
        SiriusMarkerNavigationProviderSpec.addValidationRuleMarker(nextStatus.getOriginRule(), target, elementId, diagramDescriptorUri, semanticURI, location, message, statusSeverity);
    }

    /**
     * @was-generated-NOT
     */
    @SuppressWarnings("rawtypes")
    private static void createMarkers(IFile target, Diagnostic emfValidationStatus, DiagramEditPart diagramEditPart) {
        if (emfValidationStatus.getSeverity() == Diagnostic.OK) {
            return;
        }
        final Diagnostic rootStatus = emfValidationStatus;
        List allDiagnostics = new ArrayList();
        collectTargetElements(rootStatus, new HashSet(), allDiagnostics);
        for (Iterator it = emfValidationStatus.getChildren().iterator(); it.hasNext();) {
            Diagnostic nextDiagnostic = (Diagnostic) it.next();
            List data = nextDiagnostic.getData();
            if (data != null && !data.isEmpty() && data.get(0) instanceof EObject) {
                EObject element = (EObject) data.get(0);
                View view = getCorrespondingView(element, diagramEditPart);
                addMarker(diagramEditPart.getViewer(), target, view, EMFCoreUtil.getQualifiedName(element, true), nextDiagnostic.getMessage(),
                        diagnosticToStatusSeverity(nextDiagnostic.getSeverity()));
            }
        }
    }

    private static DSemanticDecorator getDSemanticDecorator(EObject element, DiagramEditPart diagramEditPart) {
        DSemanticDecorator dSemanticDecorator = null;
        if (!(element instanceof DSemanticDecorator)) {
            Collection<EObject> xrefs = new EObjectQuery(element).getInverseReferences(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET);
            DDiagram dDiagram = (DDiagram) diagramEditPart.getDiagramView().getElement();
            for (EObject eObject : xrefs) {
                if (eObject == dDiagram || eObject instanceof DSemanticDecorator && EcoreUtil.isAncestor(dDiagram, eObject)) {
                    dSemanticDecorator = (DSemanticDecorator) eObject;
                    break;
                }
            }
        } else {
            dSemanticDecorator = (DSemanticDecorator) element;
        }
        return dSemanticDecorator;
    }

    private static void addMarker(EditPartViewer viewer, IFile target, View view, String location, String message, int statusSeverity) {
        if (target == null) {
            return;
        }
        String elementId = view.eResource().getURIFragment(view);
        // Search semantic URI
        String semanticURI = null;
        EObject ddiagramElement = view.getElement();
        if (ddiagramElement instanceof DSemanticDecorator) {
            EObject semanticElement = ((DSemanticDecorator) ddiagramElement).getTarget();
            semanticURI = EcoreUtil.getURI(semanticElement).toString();
        }
        // Search diagram URI
        Object object = viewer.getFocusEditPart().getModel();
        String diagramDescriptorUri = getDRepresentationDescriptorURIFromDiagram(object);
        SiriusMarkerNavigationProviderSpec.addMarker(target, elementId, diagramDescriptorUri, semanticURI, location, message, statusSeverity);
    }

    private static String getDRepresentationDescriptorURIFromDiagram(Object object) {
        String diagramDescriptorUri = null;
        if (object instanceof Diagram) {
            Optional<DRepresentationDescriptor> optional = Optional.of((Diagram) object).map(View::getElement).filter(DDiagram.class::isInstance).map(d -> {
                DRepresentationQuery query = new DRepresentationQuery((DDiagram) d);
                return query.getRepresentationDescriptor();
            });
            if (optional.isPresent()) {
                final URI uri = EcoreUtil.getURI(optional.get());
                diagramDescriptorUri = uri.toString();
            }
        }
        return diagramDescriptorUri;
    }

    /**
     * @was-generated
     */
    private static int diagnosticToStatusSeverity(int diagnosticSeverity) {
        if (diagnosticSeverity == Diagnostic.OK) {
            return IStatus.OK;
        } else if (diagnosticSeverity == Diagnostic.INFO) {
            return IStatus.INFO;
        } else if (diagnosticSeverity == Diagnostic.WARNING) {
            return IStatus.WARNING;
        } else if (diagnosticSeverity == Diagnostic.ERROR || diagnosticSeverity == Diagnostic.CANCEL) {
            return IStatus.ERROR;
        }
        return IStatus.INFO;
    }

    /**
     * @was-generated
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Set collectTargetElements(IStatus status, Set targetElementCollector, List allConstraintStatuses) {
        if (status instanceof IConstraintStatus) {
            targetElementCollector.add(((IConstraintStatus) status).getTarget());
            allConstraintStatuses.add(status);
        }
        if (status.isMultiStatus()) {
            IStatus[] children = status.getChildren();
            for (int i = 0; i < children.length; i++) {
                collectTargetElements(children[i], targetElementCollector, allConstraintStatuses);
            }
        }
        return targetElementCollector;
    }

    /**
     * @was-generated
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Set collectTargetElements(Diagnostic diagnostic, Set targetElementCollector, List allDiagnostics) {
        List data = diagnostic.getData();
        EObject target = null;
        if (data != null && !data.isEmpty() && data.get(0) instanceof EObject) {
            target = (EObject) data.get(0);
            targetElementCollector.add(target);
            allDiagnostics.add(diagnostic);
        }
        if (diagnostic.getChildren() != null && !diagnostic.getChildren().isEmpty()) {
            for (Iterator it = diagnostic.getChildren().iterator(); it.hasNext();) {
                collectTargetElements((Diagnostic) it.next(), targetElementCollector, allDiagnostics);
            }
        }
        return targetElementCollector;
    }
}

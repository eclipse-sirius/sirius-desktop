/*******************************************************************************
 * Copyright (c) 2017, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager;
import org.eclipse.gmf.runtime.common.ui.resources.IFileObserver;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.AbstractSiriusDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor.DisplayPriority;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.SiriusDecorationDescriptorProvider;
import org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * A {@link SiriusDecorationDescriptorProvider} that corresponds to EMF validation decorations.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ValidationDecorationDescriptorProvider extends AbstractSiriusDecorationDescriptorProvider implements SiriusDecorationDescriptorProvider {
    private static final String NAME = "validationStatus"; //$NON-NLS-1$

    private static final String MARKER_TYPE = DiagramUIPlugin.ID + ".diagnostic"; //$NON-NLS-1$

    private static MarkerObserver fileObserver;

    private static Map<String, IDecorator> viewIdToDecorator = new HashMap<>();

    private static Map<org.eclipse.gef.GraphicalEditPart, String> editPartToViewId = new HashMap<>();

    @Override
    public boolean provides(IDiagramElementEditPart editPart) {
        boolean provides = false;

        if (editPart instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart || editPart instanceof AbstractConnectionEditPart) {
            Object model = editPart.getModel();
            if (model instanceof View) {
                View view = (View) model;
                try {
                    if ((view instanceof Edge || view.isSetElement()) && view.eResource() != null) {
                        EditDomain ed = editPart.getViewer().getEditDomain();
                        if (ed instanceof DiagramEditDomain) {
                            provides = DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(view));
                        }
                    }
                } catch (IllegalStateException e) {
                    // Nothing to log here, this can happen if the resource is not accessible anymore (distant
                    // resource).
                }
            }
        }
        return provides;
    }

    @Override
    public List<DecorationDescriptor> createDecorationDescriptors(IDiagramElementEditPart editPart, Session session) {

        View view = (View) editPart.getModel();
        // query for all the validation markers of the current resource
        String elementId = SiriusGMFHelper.getViewId(view);
        if (elementId != null) {

            // Directly retrieve the main Session resource
            // (session.getSessionResource()) as we know we put the marker on
            // it.
            Resource markedResource = session == null ? null : session.getSessionResource();
            IResource resource = WorkspaceSynchronizer.getFile(markedResource);

            if (resource != null && resource.exists()) {
                IMarker[] markers = null;
                try {
                    markers = resource.findMarkers(MARKER_TYPE, true, IResource.DEPTH_INFINITE);
                } catch (CoreException e) {
                    DiagramPlugin.getDefault().logError(Messages.StatusDecorator_validationMarkersFailureMsg, e);
                }
                if (markers != null && markers.length > 0) {
                    return doCreateDecorationDescriptors(markers, elementId);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<DecorationDescriptor> doCreateDecorationDescriptors(IMarker[] markers, String elementId) {
        int severity = IMarker.SEVERITY_INFO;
        IMarker foundMarker = null;
        Label toolTip = null;
        for (int i = 0; i < markers.length; i++) {
            IMarker marker = markers[i];
            String attribute = marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, ""); //$NON-NLS-1$
            if (attribute.equals(elementId)) {
                int nextSeverity = marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
                Image nextImage = getImage(nextSeverity);
                if (foundMarker == null) {
                    foundMarker = marker;
                    toolTip = new Label(marker.getAttribute(IMarker.MESSAGE, ""), //$NON-NLS-1$
                            nextImage);
                } else {
                    if (toolTip.getChildren().isEmpty()) {
                        Label comositeLabel = new Label();
                        FlowLayout fl = new FlowLayout(false);
                        fl.setMinorSpacing(0);
                        comositeLabel.setLayoutManager(fl);
                        comositeLabel.add(toolTip);
                        toolTip = comositeLabel;
                    }
                    toolTip.add(new Label(marker.getAttribute(IMarker.MESSAGE, ""), //$NON-NLS-1$
                            nextImage));
                }
                severity = (nextSeverity > severity) ? nextSeverity : severity;
            }
        }
        if (foundMarker == null) {
            return new ArrayList<>();
        }

        // add decoration
        DecorationDescriptor decoDesc = new DecorationDescriptor();
        decoDesc.setName(NAME);
        decoDesc.setPosition(Position.NORTH_EAST_LITERAL);
        decoDesc.setDistributionDirection(DecorationDistributionDirection.HORIZONTAL);
        decoDesc.setDisplayPriority(DisplayPriority.HIGH_PRIORITY.getValue());
        decoDesc.setDecorationAsImage(getImage(severity));
        decoDesc.setTooltipAsFigure(toolTip);

        return Arrays.asList(decoDesc);
    }

    private Image getImage(int severity) {
        String imageName = ISharedImages.IMG_OBJS_ERROR_TSK;
        switch (severity) {
        case IMarker.SEVERITY_ERROR:
            imageName = ISharedImages.IMG_OBJS_ERROR_TSK;
            break;
        case IMarker.SEVERITY_WARNING:
            imageName = ISharedImages.IMG_OBJS_WARN_TSK;
            break;
        default:
            imageName = ISharedImages.IMG_OBJS_INFO_TSK;
        }
        return PlatformUI.getWorkbench().getSharedImages().getImage(imageName);
    }

    @Override
    public void activate(IDecoratorTarget decoratorTarget, IDecorator decorator, org.eclipse.gef.GraphicalEditPart editPart) {
        View view = decoratorTarget.getAdapter(View.class);
        if (view != null) {
            String viewId = getViewId(view);
            if (viewId != null) {
                editPartToViewId.put(editPart, viewId);
                viewIdToDecorator.put(viewId, decorator);

                // start listening to changes in resources
                Diagram diagramView = view.getDiagram();
                if (diagramView == null) {
                    return;
                }
                if (fileObserver == null) {
                    // fileObserver is created once with a diagramView that may be deleted. So this code is wrong
                    // diagramView is only used to get TED so that cuold be easily changed
                    // No change for nom to facilitate the review
                    fileObserver = new MarkerObserver(diagramView);
                    FileChangeManager.getInstance().addFileObserver(fileObserver);
                }
            }
        }
    }

    @Override
    public void deactivate(IDecorator decorator, org.eclipse.gef.GraphicalEditPart editPart) {
        String viewId = editPartToViewId.get(editPart);
        editPartToViewId.remove(editPart);
        if (viewId != null) {
            viewIdToDecorator.remove(viewId);

            // stop listening to changes in resources if there are no more
            // decorators
            if (fileObserver != null && viewIdToDecorator.isEmpty()) {
                FileChangeManager.getInstance().removeFileObserver(fileObserver);
                fileObserver = null;
            }
        }
    }

    private String getViewId(View view) {
        List<String> listString = new ArrayList<>();
        try {
            TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(view);
            if (ted == null) {
                listString.add(null);
            } else {
                ted.runExclusive(new Runnable() {
                    @Override
                    public void run() {
                        listString.add(view != null ? SiriusGMFHelper.getViewId(view) : null);
                    }
                });
            }
        } catch (InterruptedException e) {
            DiagramPlugin.getDefault().logError(Messages.StatusDecorator_viewIdAccessFailureMsg, e);
        }
        return listString.get(0);
    }

    static final class MarkerObserver implements IFileObserver {

        private Diagram diagram;

        private MarkerObserver(Diagram diagram) {
            this.diagram = diagram;
        }

        @Override
        public void handleFileRenamed(IFile oldFile, IFile file) {
        }

        @Override
        public void handleFileMoved(IFile oldFile, IFile file) {
        }

        @Override
        public void handleFileDeleted(IFile file) {
        }

        @Override
        public void handleFileChanged(IFile file) {
        }

        @Override
        public void handleMarkerAdded(IMarker marker) {
            if (marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, null) != null) {
                handleMarkerChanged(marker);
            }
        }

        @Override
        public void handleMarkerDeleted(IMarker marker, Map attributes) {
            if (attributes != null) {
                String viewId = (String) attributes.get(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID);
                refreshDecorators(viewId, diagram);
            }
        }

        @Override
        public void handleMarkerChanged(IMarker marker) {
            if (!MARKER_TYPE.equals(getType(marker))) {
                return;
            }
            String viewId = marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, ""); //$NON-NLS-1$
            refreshDecorators(viewId, diagram);
        }

        private String getType(IMarker marker) {
            try {
                return marker.getType();
            } catch (CoreException e) {
                DiagramPlugin.getDefault().logError(Messages.MarkerObserver_validationMarkerFailureMsg, e);
                return ""; //$NON-NLS-1$
            }
        }

        private static void refreshDecorators(String viewId, Diagram diagram) {
            final IDecorator decorator = viewId != null ? viewIdToDecorator.get(viewId) : null;
            if (decorator == null || diagram == null) {
                return;
            }
            final Diagram fdiagram = diagram;
            EclipseUIUtil.displayAsyncExec(new Runnable() {

                @Override
                public void run() {
                    try {
                        TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(fdiagram);
                        if (ted != null) {
                            TransactionUtil.getEditingDomain(fdiagram).runExclusive(new Runnable() {
                                @Override
                                public void run() {
                                    decorator.refresh();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        DiagramPlugin.getDefault().logError(Messages.SiriusValidationDecoratorProvider_refreshFailureMsg, e);
                    }
                }
            });
        }
    }
}

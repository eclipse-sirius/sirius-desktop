/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.debug;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.api.debug.AbstractDebugView;
import org.eclipse.sirius.api.debug.TabularReport;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.SiriusReferenceFinder;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalPositionFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansionOrReduction;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.api.SequenceDiagramLayout;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ToggleFoldingStateCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.editor.tools.internal.presentation.ViewpoitnDependenciesSelectionDialog;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tests.sample.component.util.PayloadMarkerAdapter;
import org.eclipse.sirius.tests.sample.component.util.PayloadMarkerAdapter.FeatureAccess;
import org.eclipse.sirius.ui.debug.ResourceSetTopologyAnalyzer.Reference;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.team.internal.core.streams.ProgressMonitorInputStream;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;

/**
 * A simple view synchronized with the Eclipse selection, used to show arbitrary computed information from the currently
 * selected edit part of a diagram and launch actions on them. This view is targeted to developers to help debugging, it
 * should not be packaged in the final product and should not be available to end users.
 * 
 * @author pcdavid
 */
@SuppressWarnings({ "restriction", "unused", "nls" })
public class SiriusDebugView extends AbstractDebugView {
    /**
     * The global ID for the Eclipse View.
     */
    public static final String ID = "org.eclipse.sirius.ui.debug.DebugView";

    /**
     * Memento used by the Store Positions/Show position changes actions.
     */
    protected Map<EObject, Integer> storedPositions;

    /**
     * Returns the text to show in the main text area for the specified object.
     */
    @Override
    protected String getTextFor(Object obj) {
        if (obj instanceof IDiagramElementEditPart) {
            return getTextFor((IDiagramElementEditPart) obj);
        } else if (obj instanceof DDiagramEditPart) {
            return getTextForDiagram((DDiagramEditPart) obj);
        } else if (obj instanceof ConnectionEditPart) {
            return getTextForConnection((ConnectionEditPart) obj);
        } else if (getRepresentationDescriptor(obj) != null) {
            return obj + "\n\n" + getTextForRepDescriptor(getRepresentationDescriptor(obj));
        } else if (obj instanceof EObject) {
            return getEObjectDetails((EObject) obj);
        } else {
            return "Selection type not supported: " + obj;
        }
    }

    private String getEObjectDetails(EObject obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("Proxy: ").append(obj.eIsProxy()).append("\n");
        sb.append("Proxy URI: ").append(((InternalEObject) obj).eProxyURI()).append("\n");
        return sb.toString();
    }

    private DRepresentationDescriptor getRepresentationDescriptor(Object obj) {
        DRepresentationDescriptor repDesc = null;
        if (obj instanceof DRepresentationDescriptor) {
            repDesc = (DRepresentationDescriptor) obj;
        } else if (obj instanceof IAdaptable) {
            EObject repDescObj = ((IAdaptable) obj).getAdapter(EObject.class);
            if (repDescObj instanceof DRepresentationDescriptor) {
                repDesc = (DRepresentationDescriptor) repDescObj;
            }
        }
        return repDesc;
    }

    private String getTextForRepDescriptor(DRepresentationDescriptor repDesc) {
        StringBuilder sb = new StringBuilder();
        sb.append(repDesc + "\n");
        sb.append("isLoadedRepresentation : " + repDesc.isLoadedRepresentation());
        return sb.toString();
    }

    private String getTextForDiagram(DDiagramEditPart sdep) {
        StringBuilder sb = new StringBuilder();
        sb.append(getCurrentEObject() + "\n");
        sb.append("Direct diagram children:\n");
        for (IGraphicalEditPart child : Iterables.filter(sdep.getChildren(), IGraphicalEditPart.class)) {
            sb.append("- " + toString(child));
            sb.append("\n  - sources: ");
            for (IGraphicalEditPart sourceConn : Iterables.filter(child.getSourceConnections(), IGraphicalEditPart.class)) {
                sb.append("\n      ").append(toString(sourceConn));
            }
            sb.append("\n  - targets: ");
            for (IGraphicalEditPart targetConn : Iterables.filter(child.getTargetConnections(), IGraphicalEditPart.class)) {
                sb.append("\n      ").append(toString(targetConn));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String toString(IGraphicalEditPart part) {
        return String.valueOf(part).replace("org.eclipse.gmf.runtime.notation.impl.", "") + " #" + Long.toHexString(part.hashCode());
    }

    private String getTextFor(IDiagramElementEditPart part) {
        StringBuilder sb = new StringBuilder();
        sb.append(part.toString() + "\n");
        if (part instanceof ShapeEditPart) {
            appendSequenceEventInfo(part, sb);
            appendBoundsDetails(part, sb);

            if (part instanceof AbstractDiagramListEditPart) {
                part.getChildren().stream().filter(IGraphicalEditPart.class::isInstance).map(IGraphicalEditPart.class::cast).forEach(child -> {
                    sb.append("Children bounds (" + child.getClass().getSimpleName() + "):\n");
                    appendBoundsDetails(child, sb);
                });
            } else if (part instanceof AbstractDiagramContainerEditPart && ((AbstractDiagramContainerEditPart) part).isRegionContainer()) {
                AtomicReference<IGraphicalEditPart> compartment = new AtomicReference<>();
                part.getChildren().stream().filter(IGraphicalEditPart.class::isInstance).map(IGraphicalEditPart.class::cast).forEach(child -> {
                    sb.append("Children bounds (" + child.getClass().getSimpleName() + "):\n");
                    appendBoundsDetails(child, sb);
                    compartment.set(child);
                });

                if (compartment.get() != null) {
                    compartment.get().getChildren().stream().filter(IGraphicalEditPart.class::isInstance).map(IGraphicalEditPart.class::cast).forEach(child2 -> {
                        sb.append("Region bounds (" + child2.getClass().getSimpleName() + "):\n");
                        appendBoundsDetails(child2, sb);
                    });
                }
            } else if (part instanceof AbstractDiagramElementContainerEditPart && ((AbstractDiagramElementContainerEditPart) part).isRegion()) {
                IGraphicalEditPart parent = (IGraphicalEditPart) part.getParent();
                sb.append("Compartment bounds (" + parent.getClass().getSimpleName() + "):\n");
                appendBoundsDetails(parent, sb);
                parent = (IGraphicalEditPart) parent.getParent();
                sb.append("Region Container bounds (" + parent.getClass().getSimpleName() + "):\n");
                appendBoundsDetails(parent, sb);
            } else if (part instanceof InstanceRoleEditPart) {
                LifelineEditPart lep = Iterables.filter(part.getChildren(), LifelineEditPart.class).iterator().next();
                appendSequenceEventInfo(lep, sb);
            }
        } else if (part instanceof ConnectionEditPart) {
            ConnectionEditPart conn = (ConnectionEditPart) part;
            sb.append(getTextForConnection(conn));
        } else if (part instanceof AbstractDiagramNameEditPart) {
            appendBoundsDetails(part, sb);
        } else {
            sb.append("unknown");
        }
        return sb.toString();
    }

    private void appendBoundsDetails(IGraphicalEditPart part, StringBuilder sb) {
        Node node = (Node) part.getNotationView();
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds bounds = (Bounds) layoutConstraint;
            sb.append("Bounds (GMF):                      Rectangle(" + bounds.getX() + ", " + bounds.getY() + ", " + bounds.getWidth() + ", " + bounds.getHeight() + ")\n");
        } else if (layoutConstraint instanceof Location) {
            Location location = (Location) layoutConstraint;
            sb.append("Location (GMF):                    Location(" + location.getX() + ", " + location.getY() + ")\n");
        }

        Rectangle absoluteBounds = GMFHelper.getAbsoluteBounds(node, true, false, false, false);
        sb.append("Bounds (GMF absolute - insets):    " + absoluteBounds + "\n");

        absoluteBounds = GMFHelper.getAbsoluteBounds(node, false, false, false, false);
        sb.append("Bounds (GMF absolute - no insets): " + absoluteBounds + "\n");

        Option<ISequenceElement> elt = ISequenceElementAccessor.getISequenceElement(part.getNotationView());
        if (elt.some()) {
            sb.append("Bounds (logical):              " + elt.get().getProperLogicalBounds()).append("\n");
        }
        Rectangle bounds = part.getFigure().getBounds().getCopy();
        sb.append("Bounds (Draw2D):                   " + bounds.toString() + " --> center: " + bounds.getCenter() + "\n");
        part.getFigure().translateToAbsolute(bounds);
        sb.append("Bounds (Draw2D absolute):          " + bounds.toString() + " --> center: " + bounds.getCenter() + "\n");
        sb.append("\n");
    }

    private String getTextForConnection(ConnectionEditPart conn) {
        Edge edge = (Edge) conn.getNotationView();
        StringBuilder sb = new StringBuilder();
        sb.append(conn.toString());
        sb.append("\n");

        if (conn instanceof IDiagramElementEditPart) {
            appendSequenceEventInfo((IDiagramElementEditPart) conn, sb);
        }

        if (conn instanceof IDiagramEdgeEditPart) {
            PolylineConnectionEx polyline = ((IDiagramEdgeEditPart) conn).getPolylineConnectionFigure();
            sb.append("Line width = " + polyline.getLineWidth() + " (float: " + polyline.getLineWidthFloat() + ")\n");
        }

        sb.append("GMF Notation (Edge):\n");

        Point srcAnchorLoc = appendAnchorDetails(conn, (IdentityAnchor) edge.getSourceAnchor(), ((Connection) conn.getFigure()).getSourceAnchor(), "  . sourceAnchor", sb);
        Point tgtAnchorLoc = appendAnchorDetails(conn, (IdentityAnchor) edge.getTargetAnchor(), ((Connection) conn.getFigure()).getTargetAnchor(), "  . targetAnchor", sb);
        appendRelativeBenpointsDetails(edge, conn, sb);

        sb.append("\nDraw2D Connection:\n");
        Connection connFigure = conn.getConnectionFigure();

        sb.append("  . routing constraint:\n");
        @SuppressWarnings("unchecked")
        List<Bendpoint> bendpoints = (List<Bendpoint>) connFigure.getRoutingConstraint();
        if (bendpoints != null) {
            for (int i = 0; i < bendpoints.size(); i++) {
                Point location = bendpoints.get(i).getLocation();
                sb.append("     " + i + ":");
                sb.append(location);
                sb.append("\n");
            }
        }

        sb.append("  . sourceAnchor: ");
        if (connFigure.getSourceAnchor() instanceof SlidableAnchor) {
            sb.append(((SlidableAnchor) connFigure.getSourceAnchor()).getTerminal() + " ==> ");
        }
        sb.append(connFigure.getSourceAnchor().getReferencePoint()).append("\n");
        sb.append("  . targetAnchor: ");
        if (connFigure.getTargetAnchor() instanceof SlidableAnchor) {
            sb.append(((SlidableAnchor) connFigure.getTargetAnchor()).getTerminal() + " ==> ");
        }
        sb.append(connFigure.getTargetAnchor().getReferencePoint()).append("\n");

        sb.append("  . points:\n");
        for (int i = 0; i < connFigure.getPoints().size(); i++) {
            sb.append("     " + i + ": " + connFigure.getPoints().getPoint(i).toString()).append("\n");
        }

        appendGMFEdgeLabels(edge, sb);
        return sb.toString();
    }

    private void appendGMFEdgeLabels(Edge edge, StringBuilder sb) {
        sb.append("\nLabels of edge:\n");
        for (Object child : edge.getChildren()) {
            if (child instanceof Node) {
                Node node = (Node) child;
                if (new ViewQuery(node).isForEdgeNameEditPart() && node.getLayoutConstraint() instanceof Location) {
                    Location location = (Location) (node).getLayoutConstraint();
                    int type = SiriusVisualIDRegistry.getVisualID(node.getType());
                    if (type == DEdgeBeginNameEditPart.VISUAL_ID) {
                        appendLabelLocation(sb, "Begin label", ((DEdge) node.getElement()).getBeginLabel(), location);
                    } else if (type == DEdgeNameEditPart.VISUAL_ID) {
                        appendLabelLocation(sb, "Center label", ((DEdge) node.getElement()).getName(), location);
                    } else if (type == DEdgeEndNameEditPart.VISUAL_ID) {
                        appendLabelLocation(sb, "End label", ((DEdge) node.getElement()).getEndLabel(), location);
                    }
                }
            }
        }
    }

    private void appendLabelLocation(StringBuilder sb, String labelKind, String labelName, Location location) {
        sb.append("\n").append(labelKind).append(": ").append(labelName).append(", ");
        sb.append(" (x: ");
        sb.append(location.getX());
        sb.append(", y: ");
        sb.append(location.getY());
        sb.append(')');
    }

    private void appendSequenceEventInfo(IDiagramElementEditPart part, StringBuilder sb) {
        if (part instanceof ISequenceEventEditPart) {
            ISequenceEvent iSequenceEvent = ((ISequenceEventEditPart) part).getISequenceEvent();
            Range range = iSequenceEvent.getVerticalRange();
            sb.append("Range: " + range.toString() + " (height = " + range.width() + ")\n");

            Rectangle properLogicalBounds = iSequenceEvent.getProperLogicalBounds();
            sb.append("Absolute Bounds: " + properLogicalBounds + ")\n");

            DDiagramElement dde = (DDiagramElement) iSequenceEvent.getNotationView().getElement();

            if (!Lifeline.viewpointElementPredicate().apply(dde) && !Iterables.isEmpty(Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class))) {
                AbsoluteBoundsFilter flag = Iterables.getOnlyElement(Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class));
                sb.append("Bounds flag: (" + flag.getX() + ", " + flag.getY() + ", " + flag.getWidth() + ", " + flag.getHeight() + ")\n\n");
            }
        }
    }

    private void appendRelativeBenpointsDetails(Edge edge, ConnectionEditPart conn, StringBuilder sb) {
        sb.append("  . bendpoints =");
        StringBuilder fromSourceSB = new StringBuilder();
        List<Point> pointsFromSource = GMFHelper.getPointsFromSource(conn);
        for (Point point : pointsFromSource) {
            fromSourceSB.append(point);
            fromSourceSB.append(" ");
        }
        StringBuilder fromTargetSB = new StringBuilder();
        List<Point> pointsFromTarget = GMFHelper.getPointsFromTarget(conn);
        for (Point point : pointsFromTarget) {
            fromTargetSB.append(point);
            fromTargetSB.append(" ");
        }
        if (fromSourceSB.toString().equals(fromTargetSB.toString())) {
            sb.append("\n     . Computed points: ");
            sb.append(fromSourceSB);
        } else {
            sb.append(" **** WARNING: points computed from source != points computed from target ****");
            sb.append("\n     . Points computed from source: ");
            sb.append(fromSourceSB);
            sb.append("\n     . Points computed from target: ");
            sb.append(fromTargetSB);
        }
        appendRelativeBenpointsDetails(edge, sb);
    }

    private void appendRelativeBenpointsDetails(Edge edge, StringBuilder sb) {
        sb.append("\n     . Source vectors: ");
        RelativeBendpoints bp = (RelativeBendpoints) edge.getBendpoints();
        for (int i = 0; i < bp.getPoints().size(); i++) {
            RelativeBendpoint rbp = (RelativeBendpoint) bp.getPoints().get(i);
            sb.append("[" + rbp.getSourceX() + ", " + rbp.getSourceY() + "] ");
        }
        sb.append("\n     . Target vectors: ");
        for (int i = 0; i < bp.getPoints().size(); i++) {
            RelativeBendpoint rbp = (RelativeBendpoint) bp.getPoints().get(i);
            sb.append("[" + rbp.getTargetX() + ", " + rbp.getTargetY() + "] ");
        }
    }

    private Point appendAnchorDetails(ConnectionEditPart origin, IdentityAnchor anchor, ConnectionAnchor connectionAnchor, String name, StringBuilder sb) {
        sb.append(name).append(" = ");
        if (anchor != null) {
            sb.append(anchor.getId());
        } else {
            sb.append("null     ");
        }
        Point location = connectionAnchor.getReferencePoint();
        origin.getFigure().translateToRelative(location);
        sb.append(" => ").append(location).append("\n");
        return location;
    }

    /**
     * Creates the actions available to users.
     */
    @Override
    protected void createActionButtons() {
        addFoldingToggleAction();
        addShowOrderingsAction();
        addStorePositionsAction();
        addShowPositionChangesAction();
        addShowFiguresHierarchyAction();
        addShowEditPartsHierarchyAction();
        addRefreshBenpointsAction();
        addResetBendpointsAction();
        addExpandAction();
        addRefreshCoverageAction();
        addRefreshDiagramAction();
        addSelectReusedSiriussAction();
        addShowCommandStackAction();
        addExtractExpressionsAction();
        addLoadResourceWithProgressAction();
        addShowPayloadAccessLogAction();
        addClearPayloadAccessLogAction();
        addShowResourceSetTopologyAction();
        addShowAdaptersAction();
        addShowSessionStructureAction();
        addShowResourceInformationAction();
        addShowSiriusInverseReferences();
        addShowCrossReferencerMap();
        addDeferredChangeAction();
        addDeferredUnrelatedChangeAction();
        addListProxiesAction();
        addLoadResourceAction();
        addLoadAllRepresentationsAction();
        addEMFResourcesStatisticsAction();
        addVSMStatisticsAction();
    }

    private void addVSMStatisticsAction() {
        addAction("Show VSM statistics", () -> {
            EObject current = getCurrentEObject();
            if (current instanceof Group) {
                IInterpreter itr = CompoundInterpreter.INSTANCE;
                String result;
                try {
                    result = new VSMAnalyzer((Group) current, itr).computeStatistics();
                } catch (EvaluationException e) {
                    result = e.getMessage();
                }
                setText(result);
            }
        });
    }

    private void addEMFResourcesStatisticsAction() {
        addAction("EMF Resource Statistics", () -> {
            IFile input = Adapters.adapt(getSelection(), IFile.class);
            if (input != null) {
                URI uri = URI.createPlatformResourceURI(input.getFullPath().toString(), true);
                ResourceSet rs = new ResourceSetImpl();
                Resource res = rs.getResource(uri, true);
                AtomicLong nbElements = new AtomicLong();
                AtomicLong nbReferences = new AtomicLong();
                res.getAllContents().forEachRemaining(o -> {
                    nbElements.incrementAndGet();
                    nbReferences.addAndGet(o.eCrossReferences().size());
                });
                StringBuilder sb = new StringBuilder();
                sb.append("Resource ").append(uri.toString()).append("\n");
                sb.append("Number of elements: ").append(nbElements).append("\n");
                sb.append("Number of cross-references: ").append(nbReferences).append("\n");
                setText(sb.toString());
            }
        });
    }

    private void addListProxiesAction() {
        addAction("List Proxies", () -> {
            StringBuilder out = new StringBuilder();
            Session.of(getCurrentEObject()).ifPresent(session -> {
                Multimap<URI, EStructuralFeature.Setting> proxies = LinkedHashMultimap.create();
                for (Resource res : session.getTransactionalEditingDomain().getResourceSet().getResources()) {
                    URI resURI = res.getURI();
                    TreeIterator<EObject> iter = res.getAllContents();
                    while (iter.hasNext()) {
                        EObject source = iter.next();
                        for (EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) source.eCrossReferences().iterator(); featureIterator.hasNext();) {
                            EObject target = featureIterator.next();
                            EReference eReference = (EReference) featureIterator.feature();
                            getProxyURI(target).ifPresent(uri -> {
                                Setting setting = ((InternalEObject) source).eSetting(eReference);
                                proxies.put(resURI, setting);
                            });
                        }
                    }
                }
                for (URI resURI : proxies.keySet()) {
                    out.append("Proxies in ").append(resURI.toString()).append(":\n");
                    for (Setting setting : proxies.get(resURI)) {
                        out.append("* ").append(EcoreUtil.getURI(setting.getEObject())).append(" --(").append(setting.getEStructuralFeature().getName()).append(")--> ")
                                .append(getProxyURI((EObject) setting.get(false)).get()).append("\n");
                    }
                    out.append("\n");
                }
            });
            setText(out.toString());
        });
    }

    private Optional<URI> getProxyURI(EObject obj) {
        InternalEObject ieo = (InternalEObject) obj;
        if (ieo.eIsProxy()) {
            return Optional.of(ieo.eProxyURI());
        } else {
            return Optional.empty();
        }
    }

    private void addLoadResourceAction() {
        addAction("Load EMF resource", () -> {
            IFile input = Adapters.adapt(getSelection(), IFile.class);
            if (input != null) {
                URI uri = URI.createPlatformResourceURI(input.getFullPath().toString(), true);
                ResourceSet rs = new ResourceSetImpl();
                Resource res = rs.createResource(uri);
                long loadTime = time(() -> {
                    try {
                        res.load(Collections.emptyMap());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                long copyTime = time(() -> {
                    for (EObject root : res.getContents()) {
                        EcoreUtil.copy(root);
                    }
                });
                long sizeBytes = new File(input.getRawLocation().toString()).length();
                AtomicLong sizeElements = new AtomicLong();
                res.getAllContents().forEachRemaining(o -> sizeElements.incrementAndGet());
                StringBuilder sb = new StringBuilder();
                sb.append("File: ").append(input.getFullPath().toString()).append("\n");
                sb.append("File size: ").append((sizeBytes / 1024) + "kiB\n");
                sb.append("Number of EObjects: ").append(sizeElements).append("\n");
                sb.append("EMF Resource load time: ").append(TimeUnit.NANOSECONDS.toMillis(loadTime)).append("ms\n");
                sb.append("EMF copy time: ").append(TimeUnit.NANOSECONDS.toMillis(copyTime)).append("ms\n");
                setText(sb.toString());
            }
        });
    }

    private void addLoadAllRepresentationsAction() {
        addAction("Load All Representations", () -> {
            StringBuilder out = new StringBuilder();
            Session.of(getCurrentEObject()).ifPresent(session -> {

                int alreadyLoaded = DialectManager.INSTANCE.getAllLoadedRepresentations(session).size();
                final int[] loaded = { 0 };
                long loadTime = time(() -> {
                    loaded[0] = DialectManager.INSTANCE.getAllRepresentations(session).size();
                });

                out.append("Load all representations in ").append(session.toString()).append(":\n");
                out.append("* prevously loaded: ").append(alreadyLoaded);
                out.append("\n* loaded: ").append(loaded[0]);
                out.append("\n* load time: ").append(TimeUnit.NANOSECONDS.toMillis(loadTime)).append("ms\n");

            });
            setText(out.toString());
        });

    }

    private long time(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return System.nanoTime() - start;
    }

    private void addDeferredChangeAction() {
        addAction("Deffered Change", () -> {
            EObject current = getCurrentEObject();
            if (current instanceof DSemanticDecorator && ((DSemanticDecorator) current).getTarget() instanceof ENamedElement) {
                final EClass target = (EClass) ((DSemanticDecorator) current).getTarget();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Session s = new EObjectQuery(target).getSession();
                        if (s != null) {
                            TransactionalEditingDomain ted = s.getTransactionalEditingDomain();
                            ted.getCommandStack().execute(new RecordingCommand(ted) {
                                @Override
                                protected void doExecute() {
                                    // target.setName(target.getName() + "X");
                                    target.setInstanceClassName(target.getInstanceClassName() + "X");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    private void addDeferredUnrelatedChangeAction() {
        addAction("Deffered Change (Unrelated)", () -> {
            EObject current = getCurrentEObject();
            if (current instanceof DSemanticDecorator && ((DSemanticDecorator) current).getTarget() instanceof ENamedElement) {
                final EClass target = (EClass) ((DSemanticDecorator) current).getTarget();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Session s = new EObjectQuery(target).getSession();
                        if (s != null) {
                            TransactionalEditingDomain ted = s.getTransactionalEditingDomain();
                            ted.getCommandStack().execute(new RecordingCommand(ted) {
                                @Override
                                protected void doExecute() {
                                    target.setAbstract(!target.isAbstract());
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    private void addShowResourceInformationAction() {
        addAction("Show resource information", new Runnable() {
            @Override
            public void run() {
                EObject current = getCurrentEObject();
                if (current instanceof Resource) {
                    setText(appendNbElement((Resource) current));
                } else if (current instanceof EObject) {
                    setText(appendNbElement(current.eResource()));
                }
            }

            private String appendNbElement(Resource res) {
                Iterable<EObject> it = () -> res.getAllContents();
                long nbElem = StreamSupport.stream(it.spliterator(), false).count();

                return "Resource " + res.getURI() + " contains " + nbElem + " elements \n";
            }
        });
    }

    private void addShowSiriusInverseReferences() {
        addAction("Show Sirius inverse references", new Runnable() {
            @Override
            public void run() {
                Session.of(getCurrentEObject()).map(DAnalysisSessionImpl.class::cast).ifPresent(session -> {
                    List<DRepresentationDescriptor> repDescriptors = session.getSiriusReferenceFinder()
                            .getImpactedRepresentationDescriptors(Collections.singletonList(getCurrentEObject()), SiriusReferenceFinder.SearchScope.ALL_REPRESENTATIONS_SCOPE).values().stream()
                            .flatMap(coll -> coll.stream()).collect(Collectors.toList());

                    List<EObject> siriusElements = session.getSiriusReferenceFinder()
                            .getReferencingSiriusElements(Collections.singletonList(getCurrentEObject()), SiriusReferenceFinder.SearchScope.ALL_REPRESENTATIONS_SCOPE).values().stream()
                            .flatMap(coll -> coll.stream()).collect(Collectors.toList());

                    setText(appendImpactedRepDesc(repDescriptors) + "\n" + appendImpactedSiriusElements(siriusElements));
                });
            }

            private String appendImpactedRepDesc(List<DRepresentationDescriptor> repDescriptors) {
                StringBuilder sb = new StringBuilder();
                sb.append("Impacted DRepresentationDescriptors (SiriusReferenceFinder.getImpactedRepresentationDescriptors):\n");
                repDescriptors.stream().forEach(repDesc -> {
                    sb.append(repDesc.getName() + "\n");
                });
                return sb.toString();
            }

            private String appendImpactedSiriusElements(List<EObject> siriusElements) {
                StringBuilder sb = new StringBuilder();
                sb.append("Impacted DRepresentation and DRepresentationElement (SiriusReferenceFinder.getReferencingSiriusElements):\n");
                siriusElements.stream().forEach(elem -> {
                    sb.append(elem + "\n");
                });
                return sb.toString();
            }
        });
    }

    private void addShowSessionStructureAction() {
        addAction("Show session structure", new Runnable() {
            @Override
            public void run() {
                EObject current = getCurrentEObject();
                if (current != null) {
                    Session s = SessionManager.INSTANCE.getSession(current);
                    if (s instanceof DAnalysisSessionImpl) {
                        setText(getStructure(((DAnalysisSessionImpl) s)));
                    } else if (s == null && current instanceof DSemanticDecorator) {
                        s = SessionManager.INSTANCE.getSession(((DSemanticDecorator) current).getTarget());
                        if (s instanceof DAnalysisSessionImpl) {
                            setText(getStructure(((DAnalysisSessionImpl) s)));
                        }
                    }
                }
            }

            private String getStructure(DAnalysisSessionImpl session) {
                StringBuilder sb = new StringBuilder();
                sb.append("DAnalysisSessionEObject references:\n");
                for (EStructuralFeature esf : session.eClass().getEAllStructuralFeatures()) {
                    sb.append("* ").append(esf.getName()).append(":\n");
                    appendValue(session, esf, sb);
                }
                sb.append("* ").append("srm resources:\n");
                int count = 0;
                for (Resource res : session.getSrmResources()) {
                    count++;
                    sb.append("  ").append(count).append(". ").append(res.getURI()).append("\n");
                }

                return sb.toString();
            }

            private void appendValue(EObject obj, EStructuralFeature esf, StringBuilder sb) {
                Object value = obj.eGet(esf);
                if (esf.isMany()) {
                    EList<?> values = (EList<?>) value;
                    for (int i = 0; i < values.size(); i++) {
                        Object v = values.get(i);
                        sb.append("  ").append(i + 1).append(". ").append(v instanceof EObject ? toString((EObject) v) : String.valueOf(v)).append("\n");
                    }
                } else {
                    sb.append("  - ").append(value instanceof EObject ? toString((EObject) value) : String.valueOf(value)).append("\n");
                }
            }

            private String toString(EObject obj) {
                if (obj == null) {
                    return null;
                } else if (obj.eIsProxy()) {
                    return ((InternalEObject) obj).eProxyURI().toString();
                } else {
                    return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
                }
            }
        });
    }

    private void addShowPayloadAccessLogAction() {
        addAction("Show Payload Access Log", () -> {
            int max = 50;
            List<FeatureAccess> log = PayloadMarkerAdapter.INSTANCE.getAccessLog();
            int totalSize = log.size();
            int shown = Math.min(totalSize, max);
            TabularReport tr = new TabularReport(/* "Timestamp", */"EObject", "Feature");

            try {
                PayloadMarkerAdapter.INSTANCE.setEnable(false);
                for (int i = log.size() > max ? log.size() - max : 0; i < log.size(); i++) {
                    FeatureAccess featureAccess = log.get(i);
                    tr.addLine(Arrays.asList(/*
                                              * String.format("%tT", featureAccess.timestamp),
                                              */((Component) featureAccess.setting.getEObject()).getName(), featureAccess.setting.getEStructuralFeature().getName()));
                }
            } finally {
                PayloadMarkerAdapter.INSTANCE.setEnable(true);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Showing " + shown + " of " + totalSize + " accesses.\n");
            Multiset<String> contexts = PayloadMarkerAdapter.INSTANCE.getUniqueContexts();
            sb.append("Unique contexts: " + contexts.elementSet().size()).append("\n\n");

            int i = 0;
            for (String stack : contexts.elementSet()) {
                int count = contexts.count(stack);
                sb.append("Context #" + i++ + " (" + count + " occurrences)").append("\n");
                sb.append(stack).append("\n");
            }

            sb.append("\n").append(tr.print()).append("\n");
            setText(sb.toString());
        });
    }

    private void addClearPayloadAccessLogAction() {
        addAction("Clear Payload Access Log", () -> PayloadMarkerAdapter.INSTANCE.clearAccessLog());
    }

    private void addShowAdaptersAction() {
        addAction("Show adapters", new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                EObject current = getCurrentEObject();
                if (current != null) {
                    sb.append("Selected element:\n");
                    appendAdapters(sb, current);
                    if (current instanceof DSemanticDecorator) {
                        sb.append("\nDSemanticDecorator.target:\n");
                        appendAdapters(sb, ((DSemanticDecorator) current).getTarget());
                    }
                    if (current instanceof DRepresentationElement) {
                        sb.append("\nDRepresentationElement.semanticElements:\n");
                        for (EObject o : ((DRepresentationElement) current).getSemanticElements()) {
                            if (o != null) {
                                appendAdapters(sb, o);
                            }
                        }
                    }
                    if (current.eResource() != null) {
                        Resource r = current.eResource();
                        sb.append("\nAdapters on Resource " + r.getURI().toString()).append(":\n");
                        appendAdapters2(sb, r);
                        if (r.getResourceSet() != null) {
                            ResourceSet rs = r.getResourceSet();
                            sb.append("\nAdapters on ResourceSet:\n");
                            appendAdapters2(sb, rs);
                        }
                    }
                }
                setText(sb.toString());
            }

            private void appendAdapters(StringBuilder sb, EObject current) {
                sb.append("Adapters on ").append(current.eClass().getName()).append("\n");
                appendAdapters2(sb, current);
            }

            private void appendAdapters2(StringBuilder sb, Notifier current) {
                EList<Adapter> adapters = current.eAdapters();
                for (int i = 0; i < adapters.size(); i++) {
                    Adapter adapter = adapters.get(i);
                    sb.append(i + 1).append(". ").append(adapter.getClass().getName()).append("@").append(Integer.toHexString(adapter.hashCode())).append("\n");
                }
            }
        });
    }

    private void addShowCrossReferencerMap() {
        addAction("Show Cross Referencer Map", new ShowCrossReferencerMap(this));
    }

    EObject getCurrentEObject() {
        if (getSelection() instanceof IGraphicalEditPart graphicalEditPart) {
            return graphicalEditPart.resolveSemanticElement();
        } else if (getSelection() instanceof EObject eObject) {
            return eObject;
        } else {
            return null;
        }
    }

    private void addShowResourceSetTopologyAction() {
        addAction("Show ResourceSet Topology", new Runnable() {
            @Override
            public void run() {
                for (Session s : SessionManager.INSTANCE.getSessions()) {
                    ResourceSetTopologyAnalyzer rsta = new ResourceSetTopologyAnalyzer(s.getTransactionalEditingDomain().getResourceSet());
                    Multimap<EStructuralFeature, Reference> result = rsta.analyze();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Found " + result.keys().size() + " distinct kinds of cross-resource references:\n");
                    for (EStructuralFeature esf : result.keys()) {
                        sb.append("- ");
                        appendFeatureName(sb, esf);
                        sb.append("\n");
                        for (Reference ref : result.get(esf)) {
                            sb.append("  - ").append(renderResource(ref.sourceResource)).append(" => ").append(renderResource(ref.targetResource)).append(" [" + ref.count + "]").append("\n");
                        }
                    }
                    setText(sb.toString());
                }
            }

            private final void appendFeatureName(StringBuilder out, EStructuralFeature esf) {
                out.append(esf.getEContainingClass().getEPackage().getName()).append("::").append(esf.getEContainingClass().getName()).append(".").append(esf.getName());
            }

            private final String renderResource(Resource res) {
                if (res == null) {
                    return "(no resource)";
                } else if (res.getURI() == null) {
                    return "(resource with no URI)";
                } else {
                    return res.getURI().toString();
                }
            }
        });
    }

    private void addLoadResourceWithProgressAction() {
        addAction("Load with progress", () -> {
            FileDialog dia = new FileDialog(getSite().getShell(), SWT.OPEN);
            dia.setFilterExtensions(new String[] { "*.ecore" });
            final String path = dia.open();
            final Resource[] result = new Resource[1];
            if (path != null) {
                try {
                    IRunnableWithProgress op = new IRunnableWithProgress() {
                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            ResourceSet rs = new ResourceSetImpl();
                            final SubMonitor loadingMonitor = SubMonitor.convert(monitor, (int) new File(path).length());
                            List<URIHandler> handlers = new ArrayList<URIHandler>(URIHandler.DEFAULT_HANDLERS);
                            handlers.set(1, new FileURIHandlerImpl() {
                                @Override
                                public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
                                    String filePath = uri.toFileString();
                                    File file = new File(filePath);
                                    InputStream inputStream = new ProgressMonitorInputStream(new FileInputStream(file), file.length(), 1, loadingMonitor) {
                                        private long previousRead = 0;

                                        @Override
                                        protected void updateMonitor(long bytesRead, long size, IProgressMonitor monitor) {
                                            long progress = bytesRead - previousRead;
                                            while (progress > Integer.MAX_VALUE) {
                                                loadingMonitor.worked(Integer.MAX_VALUE);
                                                progress -= Integer.MAX_VALUE;
                                            }
                                            loadingMonitor.worked((int) progress);
                                            previousRead = bytesRead;
                                        }
                                    };
                                    Map<Object, Object> response = getResponse(options);
                                    if (response != null) {
                                        response.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, file.lastModified());
                                    }
                                    return inputStream;
                                }

                            });
                            rs.setURIConverter(new ExtensibleURIConverterImpl(handlers, ContentHandler.Registry.INSTANCE.contentHandlers()));
                            result[0] = rs.getResource(URI.createFileURI(path), true);
                        }
                    };
                    new ProgressMonitorDialog(getSite().getShell()).run(true, false, op);
                } catch (InvocationTargetException e) {
                    // handle exception
                } catch (InterruptedException e) {
                    // handle cancelation
                }
                if (result[0] != null) {
                    setText("Loaded resource " + result[0] + " with " + Iterables.size(AllContents.of(result[0].getContents().get(0))) + " elements.");
                } else {
                    setText("Failed loading resource at " + path);
                }
            }
        });
    }

    private void addExtractExpressionsAction() {
        addAction("VSM - Extract Expressions", () -> {
            FileDialog dia = new FileDialog(getSite().getShell(), SWT.OPEN | SWT.MULTI);
            dia.setFilterExtensions(new String[] { "*.odesign" });
            String path = dia.open();
            List<String[]> lines = new ArrayList<>();
            if (path != null) {
                for (String file : dia.getFileNames()) {
                    ResourceSet rs = new ResourceSetImpl();
                    Resource vsm = rs.getResource(URI.createFileURI(dia.getFilterPath() + "/" + file), true);
                    for (EObject obj : AllContents.of(vsm.getContents().get(0))) {
                        EClass klass = obj.eClass();
                        for (EAttribute attr : klass.getEAllAttributes()) {
                            if (attr.getEType() == DescriptionPackage.Literals.INTERPRETED_EXPRESSION) {
                                String value = Optional.ofNullable((String) obj.eGet(attr)).orElse("").trim();
                                if (value.length() > 100) {
                                    value = value.substring(0, 97) + "...";
                                }
                                lines.add(new String[] { value, attr.getEContainingClass().getName() + "." + attr.getName() });
                            }
                        }
                    }
                }
            }
            StringBuilder result = new StringBuilder();
            for (String[] line : lines) {
                result.append(line[0] + "\n");
            }
            result.append("Total: " + lines.size() + " expressions");
            setText(result.toString());
        });
    }

    private void addShowCommandStackAction() {
        addAction("Show CommandStack", new Runnable() {
            @Override
            public void run() {
                IUndoContext undoContext = getUndoContext(getSelection());
                if (undoContext != null) {
                    TabularReport report = new TabularReport("IUndoableOperation's name");
                    IUndoableOperation[] redoableOperations = OperationHistoryFactory.getOperationHistory().getRedoHistory(undoContext);
                    if (redoableOperations != null) {
                        for (int i = redoableOperations.length - 1; i >= 0; i--) {
                            IUndoableOperation redoableOperation = redoableOperations[i];
                            report.addLine(redoableOperation.getLabel());
                        }
                    }
                    report.addLine("---------");
                    IUndoableOperation[] undoableOperations = OperationHistoryFactory.getOperationHistory().getUndoHistory(undoContext);
                    if (undoableOperations != null) {
                        for (int i = undoableOperations.length - 1; i >= 0; i--) {
                            IUndoableOperation undoableOperation = undoableOperations[i];
                            report.addLine(undoableOperation.getLabel());
                        }
                    }
                    setText(report.print());
                }
            }

            private IUndoContext getUndoContext(Object selection) {
                IUndoContext undoContext = IOperationHistory.GLOBAL_UNDO_CONTEXT;
                EObject eObject = null;
                if (selection instanceof EObject) {
                    eObject = (EObject) selection;
                } else if (selection instanceof IAdaptable) {
                    IAdaptable adaptable = (IAdaptable) selection;
                    eObject = adaptable.getAdapter(EObject.class);
                }
                if (eObject != null) {
                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(eObject);
                    if (domain == null && eObject instanceof Session) {
                        Session session = (Session) eObject;
                        domain = session.getTransactionalEditingDomain();
                    }
                    if (domain != null && domain.getCommandStack() instanceof IWorkspaceCommandStack) {
                        IWorkspaceCommandStack workspaceCommandStack = (IWorkspaceCommandStack) domain.getCommandStack();
                        undoContext = workspaceCommandStack.getDefaultUndoContext();
                    }
                }
                return undoContext;
            }
        });
    }

    private void addSelectReusedSiriussAction() {
        addAction("Viewpoint - Select reused", () -> {
            if (getSelection() instanceof Viewpoint viewpoint) {
                final Option<Set<URI>> sel = new ViewpoitnDependenciesSelectionDialog(viewpoint).selectReusedViewpoints(getSite().getShell());
                if (sel.some()) {
                    viewpoint.getReuses().clear();
                    Iterables.addAll(viewpoint.getReuses(), sel.get());
                }
            }
        });
    }

    private void addRefreshDiagramAction() {
        addAction("Refresh diagram", () -> {
            if (getSelection() instanceof DiagramEditPart diag) {
                diag.refresh();
                for (IGraphicalEditPart child : Iterables.filter(diag.getChildren(), IGraphicalEditPart.class)) {
                    child.refresh();
                }
            }
        });
    }

    private void addRefreshCoverageAction() {
        addAction("Sequence Diagram - Refresh Coverage", () -> {
            if (getSelection() instanceof SequenceDiagramEditPart sdep) {
                TransactionalEditingDomain ted = sdep.getEditingDomain();
                RefreshGraphicalCoverage refreshGraphicalCoverage = new RefreshGraphicalCoverage(ted, sdep);
                sdep.getEditingDomain().getCommandStack().execute(refreshGraphicalCoverage);
            }
        });
    }

    private void addExpandAction() {
        addAction("Sequence Diagram - Expand", () -> {
            if (getSelection() instanceof SequenceDiagramEditPart sdep) {
                final int start = Integer.parseInt(askStringFromUser("Expansion", "Start y", "0"));
                final int size = Integer.parseInt(askStringFromUser("Expansion", "Size", "0"));
                TransactionalEditingDomain ted = sdep.getEditingDomain();
                RecordingCommand verticalSpaceExpansion = CommandFactory.createRecordingCommand(ted,
                        new VerticalSpaceExpansionOrReduction(sdep.getSequenceDiagram(), new Range(start, start + size), 0, Collections.<ISequenceEvent> emptyList()));
                sdep.getEditingDomain().getCommandStack().execute(verticalSpaceExpansion);
            }
        });
    }

    private void addRefreshBenpointsAction() {
        addAction("Refresh bendpoints", () -> {
            if (getSelection() instanceof SequenceMessageEditPart smep) {
                TransactionalEditingDomain ted = smep.getEditingDomain();
                RefreshBendpoints refreshBendpoints = new RefreshBendpoints(ted, smep);
                smep.getEditingDomain().getCommandStack().execute(refreshBendpoints);
            } else if (getSelection() instanceof SequenceDiagramEditPart sequenceDiagramEditPart) {
                for (final SequenceMessageEditPart smep : Iterables.filter(sequenceDiagramEditPart.getChildren(), SequenceMessageEditPart.class)) {
                    TransactionalEditingDomain ted = smep.getEditingDomain();
                    RefreshBendpoints refreshBendpoints = new RefreshBendpoints(ted, smep);
                    smep.getEditingDomain().getCommandStack().execute(refreshBendpoints);
                }
            }
        });
    }

    private void addResetBendpointsAction() {
        addAction("Sequence Diagram - Reset Bendpoints", () -> {
            if (getSelection() instanceof SequenceDiagramEditPart sdep) {
                TransactionalEditingDomain ted = sdep.getEditingDomain();
                RecordingCommand verticalSpaceExpansion = CommandFactory.createRecordingCommand(ted,
                        new VerticalSpaceExpansionOrReduction(sdep.getSequenceDiagram(), new Range(0, 0), 0, Lists.<ISequenceEvent> newArrayList()));
                sdep.getEditingDomain().getCommandStack().execute(verticalSpaceExpansion);
            }
        });
    }

    /**
     * Reads and remembers the vertical position of each element in a sequence diagram. Use in conjunction with "Show
     * Position Changes".
     */
    private void addStorePositionsAction() {
        addAction("Sequence Diagram - Store positions", () -> {
            if (getSelection() instanceof SequenceDiagramEditPart sdep) {
                storedPositions = readVerticalPositions(sdep);
            }
        });
    }

    /**
     * Prints a report of which elements changed position in a sequence diagram since the last call to "Store
     * positions". Only the elements whose position is different are shown (i.e. an empty report means nothing changed).
     */
    private void addShowPositionChangesAction() {
        addAction("Sequence Diagram - Show Position Changes", () -> {
            if (getSelection() instanceof SequenceDiagramEditPart sdep) {
                if (storedPositions != null) {
                    Map<EObject, Integer> newPositions = readVerticalPositions(sdep);
                    List<EObject> elements = new ArrayList<EObject>(newPositions.keySet());
                    Collections.sort(elements, Ordering.natural().onResultOf(Functions.forMap(newPositions)));
                    TabularReport report = new TabularReport("Semantic element", "Old Position", "New Position", "deltaY");
                    AdapterFactoryLabelProvider lp = new AdapterFactoryLabelProvider(getAdapterFactory());
                    for (EObject element : elements) {
                        Integer oldY = storedPositions.get(element);
                        Integer newY = newPositions.get(element);
                        if (oldY != null && !oldY.equals(newY)) {
                            report.addLine(lp.getText(element), String.valueOf(oldY), String.valueOf(newY), String.valueOf(newY - oldY));
                        } else if (oldY == null) {
                            report.addLine(lp.getText(element), "-", String.valueOf(newY), "n/a");
                        } else if (newY == VerticalPositionFunction.INVALID_POSITION) {
                            report.addLine(lp.getText(element), String.valueOf(oldY), "-", "n/a");
                        }
                    }
                    setText(report.print());
                }
            }
        });
    }

    private Map<EObject, Integer> readVerticalPositions(SequenceDiagramEditPart sdep) {
        SequenceDDiagram diag = (SequenceDDiagram) sdep.resolveSemanticElement();
        VerticalPositionFunction vpf = new VerticalPositionFunction(diag);
        Map<EObject, Integer> positions = new HashMap<>();
        for (EventEnd end : diag.getGraphicalOrdering().getEventEnds()) {
            positions.put(end.getSemanticEnd(), (int) vpf.apply(end));
        }
        return positions;
    }

    /**
     * Prints a report of the graphical and semantic orderings on a sequence diagram. For elements which have a valid
     * graphical position, it is also reported.
     */
    private void addShowOrderingsAction() {
        addAction("Sequence Diagram - Orderings", () -> {
            if (getSelection() instanceof SequenceDiagramEditPart sdep) {
                AdapterFactoryLabelProvider lp = new AdapterFactoryLabelProvider(getAdapterFactory());
                SequenceDDiagram diag = (SequenceDDiagram) sdep.resolveSemanticElement();
                VerticalPositionFunction vpf = new VerticalPositionFunction(diag);
                Iterator<EventEnd> go = diag.getGraphicalOrdering().getEventEnds().iterator();
                Iterator<EventEnd> so = diag.getSemanticOrdering().getEventEnds().iterator();
                TabularReport report = new TabularReport("Semantic", "Graphical", "Sync?", "Y");
                while (so.hasNext() || go.hasNext()) {
                    List<String> line = new ArrayList<>();

                    final EObject sosEnd;
                    if (so.hasNext()) {
                        EventEnd soEnd = so.next();
                        sosEnd = soEnd.getSemanticEnd();
                        line.add(lp.getText(sosEnd));
                    } else {
                        sosEnd = null;
                        line.add("<none>");
                    }

                    final EObject gosEnd;
                    final int y;
                    if (go.hasNext()) {
                        EventEnd goEnd = go.next();
                        gosEnd = goEnd.getSemanticEnd();
                        line.add(lp.getText(gosEnd));
                        y = vpf.apply(goEnd);
                    } else {
                        gosEnd = null;
                        line.add("<none>");
                        y = -1;
                    }

                    if (sosEnd == gosEnd) {
                        line.add("yes");
                    } else {
                        line.add("NO");
                    }
                    line.add(y != -1 ? String.valueOf(y) : "n/a");
                    report.addLine(line);
                }
                setText(report.print());
            }
        });
    }

    private void addFoldingToggleAction() {
        addAction("Toggle folding", () -> {
            if (getSelection() instanceof IAbstractDiagramNodeEditPart part) {
                EdgeTargetQuery query = new EdgeTargetQuery((EdgeTarget) part.resolveDiagramElement());
                boolean isFoldingPoint = query.isFoldingPoint();
                if (isFoldingPoint) {
                    part.getEditingDomain().getCommandStack().execute(new ToggleFoldingStateCommand(part.getEditingDomain(), part));
                }
            }
        });
    }

    /**
     * Show details about each edit part
     */
    private void addShowEditPartsHierarchyAction() {
        addAction("Show Edit Parts", new ShowEditPartsHierarchy(this));
    }

    /**
     * Show details about each figure in the Draw2D hierarchy starting from the current selection's figure. See
     * {@link #figureDetails(IFigure)} for what information is actually shown for each figure.
     */
    private void addShowFiguresHierarchyAction() {
        addAction("Show figures", () -> {
            if (getSelection() instanceof IGraphicalEditPart part) {
                StringBuilder sb = new StringBuilder();
                showFigures(part.getFigure(), 0, sb);
                setText(sb.toString());
            }
        });
    }

    private void showFigures(IFigure figure, int level, StringBuilder out) {
        for (int i = 0; i < level; i++) {
            out.append("  ");
        }
        out.append(figureDetails(figure)).append("\n");
        for (IFigure child : Iterables.filter(figure.getChildren(), IFigure.class)) {
            showFigures(child, level + 1, out);
        }
    }

    private String figureDetails(IFigure figure) {
        String figureName = getClassName(figure);
        String layoutManager = figure.getLayoutManager() != null ? getClassName(figure.getLayoutManager()) : null;
        return "Class: " + figureName + " LAYOUTMANAGER: " + layoutManager + "  BOUNDS: " + figure.getBounds() + " Opaque: " + figure.isOpaque() + " Color: " + figure.getBackgroundColor();
    }

    String getClassName(Object object) {
        String name = object.getClass().getSimpleName();
        if (name.isEmpty()) {
            String[] split = object.getClass().getName().split("\\.");
            if (split.length > 0) {
                name = split[split.length - 1] + ":" + object.getClass().getSuperclass().getSimpleName();
            }
        }
        return name;
    }

    private static class RefreshGraphicalCoverage extends RecordingCommand {

        private SequenceDiagramEditPart sdep;

        public RefreshGraphicalCoverage(TransactionalEditingDomain domain, SequenceDiagramEditPart sdep) {
            super(domain);
            this.sdep = sdep;
        }

        @Override
        protected void doExecute() {
            new SequenceDiagramLayout(sdep).refreshGraphicalCoverage();
        }

    }

    private static class RefreshBendpoints extends RecordingCommand {

        private SequenceMessageEditPart smep;

        public RefreshBendpoints(TransactionalEditingDomain domain, SequenceMessageEditPart smep) {
            super(domain);
            this.smep = smep;
        }

        @Override
        protected void doExecute() {
            smep.refreshBendpoints();
        }

    }
}

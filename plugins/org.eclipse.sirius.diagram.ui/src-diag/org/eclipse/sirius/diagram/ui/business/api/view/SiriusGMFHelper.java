/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.view;

import java.util.Arrays;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Utility class to retrieve manipulate GMF View :
 * <UL>
 * <LI>retrieve GMF view form DSemanticDecorator</LI>
 * <LI></LI>
 * </UL>
 * .
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class SiriusGMFHelper {

    private static final int[] LABEL_VISUAL_IDS = { DEdgeNameEditPart.VISUAL_ID, DEdgeEndNameEditPart.VISUAL_ID, DEdgeBeginNameEditPart.VISUAL_ID, 5005, 5006, 3010, 5004, 5007, 5001, 5003, 5010, 5002 };
    static {
        Arrays.sort(LABEL_VISUAL_IDS);
    }

    private SiriusGMFHelper() {
        // Prevent instantiation.
    }

    /**
     * Get the GMF Diagram from the diagram.
     * 
     * @param diagram
     *            the diagram
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Diagram getGmfDiagram(final DDiagram diagram) {
        return SiriusGMFHelper.getGmfView(diagram, Diagram.class);
    }

    /**
     * Get the GMF edge from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the edge which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Edge getGmfEdge(final DDiagramElement diagramElement) {
        return SiriusGMFHelper.getGmfView(diagramElement, Edge.class);
    }

    /**
     * Get the GMF node from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the node which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Node getGmfNode(final DDiagramElement diagramElement) {
        return SiriusGMFHelper.getGmfView(diagramElement, Node.class);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    public static View getGmfView(final DDiagramElement diagramElement) {
        return SiriusGMFHelper.getGmfView(diagramElement, View.class);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param element
     *            the diagram element
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    public static View getGmfView(final DSemanticDecorator element) {
        return SiriusGMFHelper.getGmfView(element, View.class);
    }

    /**
     * Get the GMF Diagram from the diagram.
     * 
     * @param diagram
     *            the diagram
     * @param session
     *            the session
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Diagram getGmfDiagram(final DDiagram diagram, final Session session) {
        return SiriusGMFHelper.getGmfView(diagram, Diagram.class, session);
    }

    /**
     * Get the GMF edge from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @param session
     *            the session
     * @return the edge which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Edge getGmfEdge(final DDiagramElement diagramElement, final Session session) {
        return SiriusGMFHelper.getGmfView(diagramElement, Edge.class, session);
    }

    /**
     * Get the GMF edge from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @param crossReference
     *            the cross reference to use
     * @return the edge which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Edge getGmfEdge(final DDiagramElement diagramElement, final ECrossReferenceAdapter crossReference) {
        return SiriusGMFHelper.getGmfView(diagramElement, Edge.class, crossReference);
    }

    /**
     * Get the GMF node from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @param session
     *            the session
     * @return the node which has as element the diagram element given as
     *         parameter or null if any
     */
    public static Node getGmfNode(final DDiagramElement diagramElement, final Session session) {
        return SiriusGMFHelper.getGmfView(diagramElement, Node.class, session);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @param session
     *            the session
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    public static View getGmfView(final DDiagramElement diagramElement, final Session session) {
        return SiriusGMFHelper.getGmfView(diagramElement, View.class, session);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param element
     *            the diagram element
     * @param session
     *            the session
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    public static View getGmfView(final DSemanticDecorator element, final Session session) {
        return SiriusGMFHelper.getGmfView(element, View.class, session);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param <T>
     *            generic type
     * @param diagramElement
     *            the diagram element
     * @param clazz
     *            The type of the desired view
     * 
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    private static <T> T getGmfView(final EObject diagramElement, final Class<T> clazz) {
        if (diagramElement instanceof DSemanticDecorator) {
            EObject semanticElement = ((DSemanticDecorator) diagramElement).getTarget();
            Session sessionToUse = semanticElement != null ? SiriusGMFHelper.getSession(semanticElement) : SiriusGMFHelper.getSession(diagramElement);
            return SiriusGMFHelper.getGmfView(diagramElement, clazz, sessionToUse);
        }
        return null;
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param <T>
     *            generic type
     * @param diagramElement
     *            the diagram element
     * @param clazz
     *            The type of the desired view
     * @param session
     *            the session to use
     * 
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    private static <T> T getGmfView(final EObject diagramElement, final Class<T> clazz, final Session session) {

        if (diagramElement instanceof DSemanticDecorator) {

            final Session sessionToUse;
            if (session == null) {
                EObject semanticElement = ((DSemanticDecorator) diagramElement).getTarget();
                sessionToUse = semanticElement != null ? SiriusGMFHelper.getSession(semanticElement) : SiriusGMFHelper.getSession(diagramElement);
            } else {
                sessionToUse = session;
            }

            if (sessionToUse != null) {
                final ECrossReferenceAdapter crossReference = sessionToUse.getSemanticCrossReferencer();
                return getGmfView(diagramElement, clazz, crossReference);
            }
        }
        return null;
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param <T>
     *            generic type
     * @param diagramElement
     *            the diagram element
     * @param clazz
     *            The type of the desired view
     * @param semanticCrossReference
     *            the crossReference to use
     * 
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    @SuppressWarnings("unchecked")
    private static <T> T getGmfView(final EObject diagramElement, final Class<T> clazz, final ECrossReferenceAdapter semanticCrossReference) {
        if (semanticCrossReference != null) {
            for (final org.eclipse.emf.ecore.EStructuralFeature.Setting setting : semanticCrossReference.getInverseReferences(diagramElement)) {
                if (clazz.isInstance(setting.getEObject()) && setting.getEStructuralFeature() == NotationPackage.eINSTANCE.getView_Element()) {
                    return (T) setting.getEObject();
                }
            }
        }
        return null;
    }

    private static Session getSession(final EObject eObject) {
        return SessionManager.INSTANCE.getSession(eObject);
    }

    /**
     * Get the label associated with this edge.
     * 
     * @param gmfView
     *            the associated edge
     * @return A node representing the label
     */
    public static Node getLabelNode(final View gmfView) {
        Node result = null;
        Optional<Object> optionalLabelView = gmfView.getChildren().stream().filter(child -> child instanceof Node && SiriusGMFHelper.isLabel((Node) child)).findFirst();
        if (optionalLabelView.isPresent()) {
            result = (Node) optionalLabelView.get();
        }
        return result;
    }

    /**
     * Test is the <code>view</code> is a label (base on a list of VISUAL_ID).
     * 
     * @param gmfView
     *            The view to check
     * @return true is the view is a label, false otherwise.
     */
    private static boolean isLabel(final View gmfView) {
        final int visualId = SiriusVisualIDRegistry.getVisualID(gmfView);
        if (Arrays.binarySearch(LABEL_VISUAL_IDS, visualId) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Get a unique identifier for a {@link View} in a GMF notation model.
     * 
     * @param view
     *            the EObject {@link View} for which to get a uid
     * 
     * @return the uid
     */
    public static String getViewId(final View view) {
        String viewId = StringStatics.BLANK;
        Resource viewResource = view.eResource();
        if (viewResource != null) {
            String uriFragment = viewResource.getURIFragment(view);
            if (uriFragment != null) {
                viewId = uriFragment;
            }
        }
        return viewId;
    }
}

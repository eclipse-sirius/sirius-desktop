package org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.diagram.util;

import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;

/**
 * Utility functions for MappingBasedFormatManagerFactories.
 * 
 * @author adieumegard
 *
 */
public final class MappingBasedSiriusFormatManagerFactoryUtil {

    private MappingBasedSiriusFormatManagerFactoryUtil() {
        // Override default constructor
    }

    /**
     * Retrieve the GMF diagram for {@code sourceDiagram}. GMF diagram is created if necessary.
     * 
     * @param sourceDiagram
     *            The source Sirius diagram
     * @return The GMf diagram
     */
    public static Diagram getGMFDiagram(DDiagram sourceDiagram) {
        final DiagramCreationUtil sourceDiagramUtil = new DiagramCreationUtil(sourceDiagram);
        if (!sourceDiagramUtil.findAssociatedGMFDiagram()) {
            sourceDiagramUtil.createNewGMFDiagram();
        }
        return sourceDiagramUtil.getAssociatedGMFDiagram();
    }

    /**
     * Recursively add parent containers to map if they are not present. Stop as soon as a container is found in the
     * map.
     * 
     * @param sourceView
     *            The source.
     * @param targetView
     *            The target.
     * @param sourceToTargetView
     *            The holding map.
     */
    public static void addParentViewContainersToMap(View sourceView, View targetView, Map<View, View> sourceToTargetView) {
        EObject sourceViewContainer = sourceView.eContainer();
        if (sourceViewContainer instanceof View && !sourceToTargetView.containsKey(sourceView.eContainer())) {
            View targetViewContainer = (View) targetView.eContainer();
            sourceToTargetView.put((View) sourceViewContainer, targetViewContainer);
            addParentViewContainersToMap((View) sourceViewContainer, targetViewContainer, sourceToTargetView);
        }
    }

    /**
     * Recursively retrieve all child view of {@code elementView} having an Element set (isSetElement = true). If a
     * child has no element set, then search for element in this child.
     * 
     * @param elementView
     *            The view on which we search for childs.
     * @return A list of views.
     */
    public static EList<View> getAllPersistedChildrensWithElement(View elementView) {
        EList<View> childrens = new BasicEList<View>();
        elementView.getPersistedChildren().stream().forEach(child -> {
            if (child instanceof View) {
                if (!((View) child).isSetElement()) {
                    childrens.addAll(getAllPersistedChildrensWithElement((View) child));
                } else {
                    childrens.add((View) child);
                }
            }
        });
        return childrens;
    }

}

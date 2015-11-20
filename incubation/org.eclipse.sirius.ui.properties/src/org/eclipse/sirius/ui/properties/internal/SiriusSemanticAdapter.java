package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * An adapter factory to convert an element from Sirius into an EEF
 * SemanticAdapter, if possible.
 *
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class SiriusSemanticAdapter implements IAdapterFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
        Object adapter = null;
        // if Object comes from GMF
        if (adaptableObject != null && adapterType != null) {
            EObject semanticElement = null;
            if (adaptableObject instanceof GraphicalEditPart) {
                semanticElement = ((GraphicalEditPart) adaptableObject).resolveSemanticElement();
            } else if (adaptableObject instanceof ConnectionEditPart) {
                semanticElement = ((Edge) ((ConnectionEditPart) adaptableObject).getModel()).getElement();
            } else if (adaptableObject instanceof DSemanticDecorator) {
                semanticElement = ((DSemanticDecorator) adaptableObject).getTarget();
            }
            if (semanticElement instanceof DSemanticDecorator) {
                DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) semanticElement;
                return dSemanticDecorator.getTarget();
            }
        }
        return adapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { EObject.class };
    }

}

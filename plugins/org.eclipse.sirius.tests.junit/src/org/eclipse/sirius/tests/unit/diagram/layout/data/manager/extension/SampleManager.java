/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout.data.manager.extension;

import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData;
import org.eclipse.sirius.diagram.ui.tools.api.layout.AbstractSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.tests.unit.diagram.layout.data.manager.extension.keys.AbstractSampleLayouDataKey;
import org.eclipse.sirius.tests.unit.diagram.layout.data.manager.extension.keys.SampleEdgeLayouDataKey;
import org.eclipse.sirius.tests.unit.diagram.layout.data.manager.extension.keys.SampleNodeLayouDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Sample {@link SiriusLayoutDataManager} to able to copy/paste layout regarding
 * traceability/refinement links.
 * 
 * These links are simulated by an {@link EAnnotation} with
 * {@link SampleManager#SAMPLE_SOURCE} as source and a referenced
 * {@link EObject}.
 * 
 * @author mporhel
 * 
 */
public class SampleManager extends AbstractSiriusLayoutDataManager implements SiriusLayoutDataManager {

    /**
     * {@link EAnnotation} source of {@link EAnnotation} simulating
     * refinement/traceability links.
     */
    public static final String SAMPLE_SOURCE = "refinement.link";

    private final Map<AbstractSampleLayouDataKey, AbstractLayoutData> layoutDataMap = Maps.newHashMap();

    private final SessionManagerListener sessionMgrListener = new SampleSessionManagerListener();

    {
        // Avoid memory leak, react to session changes to clean cache.
        SessionManager.INSTANCE.addSessionsListener(sessionMgrListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractLayoutData getLayoutData(LayoutDataKey key) {
        if (key instanceof AbstractSampleLayouDataKey && validateKey((AbstractSampleLayouDataKey) key)) {
            return getLinkedLayoutData((AbstractSampleLayouDataKey) key);
        }

        return null;
    }

    private AbstractLayoutData getLinkedLayoutData(AbstractSampleLayouDataKey key) {
        AbstractLayoutData layoutData = null;
        if (layoutData == null) {
            // Retrieve traceability/refinement information
            EObject foundSemantic = retrieveLinkedEObject(key.getSemantic());
            if (foundSemantic != null) {
                if (key instanceof SampleNodeLayouDataKey) {
                    layoutData = layoutDataMap.get(new SampleNodeLayouDataKey(foundSemantic));
                } else if (key instanceof SampleEdgeLayouDataKey) {
                    layoutData = layoutDataMap.get(new SampleEdgeLayouDataKey(foundSemantic));
                }
            }
        }
        return layoutData;
    }

    /**
     * Look for an {@link EObject} linked to given one by a
     * traceability/refinement link.
     */
    private EObject retrieveLinkedEObject(EObject semantic) {
        EObject source = null;
        if (semantic instanceof EModelElement) {
            EAnnotation annot = ((EModelElement) semantic).getEAnnotation(SAMPLE_SOURCE);
            if (annot != null && !annot.getReferences().isEmpty()) {
                source = annot.getReferences().iterator().next();
            }
        }
        return source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutDataKey createKey(DSemanticDecorator semanticDecorator) {
        LayoutDataKey result = null;
        final EObject realSemanticElement = semanticDecorator.getTarget();
        if (semanticDecorator instanceof DEdge) {
            result = new SampleEdgeLayouDataKey(realSemanticElement);
        } else if (semanticDecorator instanceof AbstractDNode || semanticDecorator instanceof DDiagram) {
            result = new SampleNodeLayouDataKey(realSemanticElement);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLayoutData(LayoutDataKey key, AbstractLayoutData layoutData) {
        if (key instanceof AbstractSampleLayouDataKey && validateKey((AbstractSampleLayouDataKey) key)) {
            layoutDataMap.put((AbstractSampleLayouDataKey) key, layoutData);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsData() {
        return !layoutDataMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearLayoutData() {
        layoutDataMap.clear();
    }

    /**
     * Remove keys with dangling semantic {@link EObject} from cache.
     */
    private void cleanCache() {
        for (AbstractSampleLayouDataKey key : Lists.newArrayList(layoutDataMap.keySet())) {
            if (!validateKey(key)) {
                layoutDataMap.remove(key);
            }
        }
    }

    private boolean validateKey(AbstractSampleLayouDataKey key) {
        return key != null && key.getSemantic() != null && !key.getSemantic().eIsProxy() && key.getSemantic().eResource() != null;
    }

    /**
     * Specific session manager listener to avoid memory leaks when session
     * changes occur.
     * 
     * @author mporhel
     */
    private class SampleSessionManagerListener implements SessionManagerListener {

        @Override
        public void notifyRemoveSession(Session removedSession) {
            cleanCache();
        }

        @Override
        public void notify(Session updated, int notification) {
            switch (notification) {
            case SessionListener.CLOSED:
            case SessionListener.DIRTY:
            case SessionListener.SYNC:
            case SessionListener.REPLACED:
            case SessionListener.REPRESENTATION_CHANGE:
            case SessionListener.SEMANTIC_CHANGE:
                cleanCache();
                break;

            default:
                break;
            }
        }

        @Override
        public void notifyAddSession(Session newSession) {
            // Nothing to do
        }

        @Override
        public void viewpointSelected(Viewpoint selectedSirius) {
            // Nothing to do
        }

        @Override
        public void viewpointDeselected(Viewpoint deselectedSirius) {
            // Nothing to do

        }
    }
}

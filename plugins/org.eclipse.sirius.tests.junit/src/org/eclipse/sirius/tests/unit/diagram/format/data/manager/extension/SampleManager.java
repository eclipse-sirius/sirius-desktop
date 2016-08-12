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
package org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension;

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
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.ui.tools.api.format.AbstractSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.api.format.SiriusFormatDataManager;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.keys.AbstractSampleFormatDataKey;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.keys.SampleEdgeFormatDataKey;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.keys.SampleNodeFormatDataKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Sample {@link SiriusFormatDataManager} to able to copy/paste format regarding
 * traceability/refinement links.
 * 
 * These links are simulated by an {@link EAnnotation} with
 * {@link SampleManager#SAMPLE_SOURCE} as source and a referenced
 * {@link EObject}.
 * 
 * @author mporhel
 * 
 */
public class SampleManager extends AbstractSiriusFormatDataManager implements SiriusFormatDataManager {

    /**
     * {@link EAnnotation} source of {@link EAnnotation} simulating
     * refinement/traceability links.
     */
    public static final String SAMPLE_SOURCE = "refinement.link.format";

    private final Map<AbstractSampleFormatDataKey, AbstractFormatData> formatDataMap = Maps.newHashMap();

    private final SessionManagerListener sessionMgrListener = new SampleSessionManagerListener();

    {
        // Avoid memory leak, react to session changes to clean cache.
        SessionManager.INSTANCE.addSessionsListener(sessionMgrListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractFormatData getFormatData(FormatDataKey key) {
        if (key instanceof AbstractSampleFormatDataKey && validateKey((AbstractSampleFormatDataKey) key)) {
            return getLinkedFormatData((AbstractSampleFormatDataKey) key);
        }

        return null;
    }

    private AbstractFormatData getLinkedFormatData(AbstractSampleFormatDataKey key) {
        AbstractFormatData formatData = null;
        if (formatData == null) {
            // Retrieve traceability/refinement information
            EObject foundSemantic = retrieveLinkedEObject(key.getSemantic());
            if (foundSemantic != null) {
                if (key instanceof SampleNodeFormatDataKey) {
                    formatData = formatDataMap.get(new SampleNodeFormatDataKey(foundSemantic));
                } else if (key instanceof SampleEdgeFormatDataKey) {
                    formatData = formatDataMap.get(new SampleEdgeFormatDataKey(foundSemantic));
                }
            }
        }
        return formatData;
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
    public FormatDataKey createKey(DSemanticDecorator semanticDecorator) {
        FormatDataKey result = null;
        final EObject realSemanticElement = semanticDecorator.getTarget();
        if (semanticDecorator instanceof DEdge) {
            result = new SampleEdgeFormatDataKey(realSemanticElement);
        } else if (semanticDecorator instanceof AbstractDNode || semanticDecorator instanceof DDiagram) {
            result = new SampleNodeFormatDataKey(realSemanticElement);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFormatData(FormatDataKey key, AbstractFormatData formatData) {
        if (key instanceof AbstractSampleFormatDataKey && validateKey((AbstractSampleFormatDataKey) key)) {
            formatDataMap.put((AbstractSampleFormatDataKey) key, formatData);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsData() {
        return !formatDataMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearFormatData() {
        formatDataMap.clear();
    }

    /**
     * Remove keys with dangling semantic {@link EObject} from cache.
     */
    private void cleanCache() {
        for (AbstractSampleFormatDataKey key : Lists.newArrayList(formatDataMap.keySet())) {
            if (!validateKey(key)) {
                formatDataMap.remove(key);
            }
        }
    }

    private boolean validateKey(AbstractSampleFormatDataKey key) {
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

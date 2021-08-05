/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilter;
import org.eclipse.sirius.tools.api.command.ui.RefreshFilterManager;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

import junit.framework.TestCase;

/**
 * Test that {@link RefreshEditorsPrecommitListener} return a {@link Command} to refresh a {@link DRepresentation} only
 * on semantic change notification.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshEditorsPrecommitListenerTests extends TestCase {

    private static final String PATH = "/data/unit/refresh/compartmentListEltsReorderingOnDirectEdit/";

    private URI sessionResourceURI;

    private URI semanticResourceURI;

    private TransactionalEditingDomain domain;

    private ResourceSet resourceSet;

    private Resource sessionResource;

    private RefreshEditorsPrecommitListener refreshEditorsPrecommitListener;

    private RefreshFilterStub refreshFilterStub;

    private boolean oldRefreshAutoMode;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        sessionResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + "vp1753.aird", true);
        semanticResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + "vp1753.ecore", true);
        Session session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        domain = session.getTransactionalEditingDomain();

        resourceSet = domain.getResourceSet();

        sessionResource = resourceSet.getResource(sessionResourceURI, true);

        refreshEditorsPrecommitListener = new RefreshEditorsPrecommitListener(session);

        final DRepresentation firstDRepresentation = getFirstElement(sessionResource, DRepresentation.class);
        refreshFilterStub = new RefreshFilterStub(firstDRepresentation);
        RefreshFilterManager.INSTANCE.addRefreshFilter(refreshFilterStub);

        oldRefreshAutoMode = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
        InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    /**
     * Test that no refresh command is returned by the {@link RefreshEditorsPrecommitListener} when it receives a
     * {@link DRepresentationElement} change notification.
     */
    public void testNoRefreshOnDRepresentationElementChangeNotification() {
        // 1. Create a Notification for theRefreshEditorsPrecommitListener
        DNodeList firstDNodeList = getFirstElement(sessionResource, DNodeList.class);
        Notification dRepresentationElementChangeNotification = new ENotificationImpl((InternalEObject) firstDNodeList, Notification.SET, DiagramPackage.Literals.DDIAGRAM_ELEMENT__VISIBLE, false,
                true);
        ResourceSetChangeEvent event = new ResourceSetChangeEvent(domain, null, Collections.singletonList(dRepresentationElementChangeNotification));

        // 2. Checks that the RefreshEditorsPrecommitListener doesn't refresh
        // the representation
        Option<Command> refreshPrecommitCmd = refreshEditorsPrecommitListener.localChangesAboutToCommit(event.getNotifications());
        assertFalse("On a DRepresentationElement change notification the RefreshPrecommitListener shouldn't do refresh", refreshPrecommitCmd.some());
    }

    /**
     * Test that no refresh command is returned by the {@link RefreshEditorsPrecommitListener} when it receives a GMF
     * notation model change notification.
     */
    public void testNoRefreshOnGMFNodeChangeNotification() {
        // 1. Create a Notification for theRefreshEditorsPrecommitListener
        Bounds firstBounds = getFirstElement(sessionResource, Bounds.class);
        Notification dRepresentationElementChangeNotification = new ENotificationImpl((InternalEObject) firstBounds, Notification.SET, NotationPackage.Literals.LOCATION__X, 0, firstBounds.getX());
        ResourceSetChangeEvent event = new ResourceSetChangeEvent(domain, null, Collections.singletonList(dRepresentationElementChangeNotification));

        // 2. Checks that the RefreshEditorsPrecommitListener doesn't refresh
        // the representation
        Option<Command> refreshPrecommitCmd = refreshEditorsPrecommitListener.localChangesAboutToCommit(event.getNotifications());
        assertFalse("On a DRepresentationElement change notification the RefreshPrecommitListener shouldn't do refresh", refreshPrecommitCmd.some());
    }

    /**
     * Test that a refresh command is returned by the {@link RefreshEditorsPrecommitListener} when it receives a
     * semantic change notification.
     */
    public void testRefreshOnSemanticChangeNotification() {
        // 1. Create a Notification for theRefreshEditorsPrecommitListener
        Resource semanticResource = resourceSet.getResource(semanticResourceURI, true);
        EClass firstEClass = getFirstElement(semanticResource, EClass.class);
        Notification dRepresentationElementChangeNotification = new ENotificationImpl((InternalEObject) firstEClass, Notification.SET, EcorePackage.Literals.ECLASS__ABSTRACT, true, false);
        ResourceSetChangeEvent event = new ResourceSetChangeEvent(domain, null, Collections.singletonList(dRepresentationElementChangeNotification));

        // 2. Checks that the RefreshEditorsPrecommitListener refresh
        // the representation
        Option<Command> refreshPrecommitCmd = refreshEditorsPrecommitListener.localChangesAboutToCommit(event.getNotifications());
        assertTrue("On a semantic change notification the RefreshPrecommitListener should do refresh", refreshPrecommitCmd.some());
    }

    private <T extends EObject> T getFirstElement(Resource sessionResource, Class<T> type) {
        T result = null;
        TreeIterator<EObject> allContents = sessionResource.getAllContents();
        while (allContents.hasNext() && result == null) {
            EObject next = allContents.next();
            if (type.isInstance(next)) {
                result = type.cast(next);
            }
        }
        return result;
    }

    class RefreshFilterStub implements RefreshFilter {

        private DRepresentation dRepresentation;

        public RefreshFilterStub(DRepresentation dRepresentation) {
            this.dRepresentation = dRepresentation;
        }

        @Override
        public boolean shouldRefresh(DRepresentation representation) {
            return true;
        }

        @Override
        public Collection<DRepresentation> getOpenedRepresantationsToRefresh() {
            return Collections.singletonList(dRepresentation);
        }

    }

    @Override
    protected void tearDown() throws Exception {

        InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), oldRefreshAutoMode);
        sessionResourceURI = null;
        semanticResourceURI = null;
        domain.dispose();
        domain = null;
        resourceSet = null;
        sessionResource = null;
        refreshEditorsPrecommitListener = null;
        RefreshFilterManager.INSTANCE.removeRefreshFilter(refreshFilterStub);
        refreshFilterStub = null;

        super.tearDown();
    }
}

/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.tools;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.business.api.tool.ToolFilterDescriptionListener;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

import com.google.common.collect.Maps;

import junit.framework.TestCase;

public class ToolFilterDescriptionListenerTests extends TestCase {

    private static final String TEMPORARY_PROJECT_NAME = "DesignerTestProject";

    private static final String SESSION_MODEL_FILENAME = "test.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "test.ecore";

    private TransactionalEditingDomain editingDomain;

    private Session session;

    private EPackage ePackage;

    private DSemanticDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        /* create a resource set */
        ResourceSet rset = new ResourceSetImpl();
        /* create an editing domain */
        editingDomain = createEditingDomain(rset);

        URI semanticResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        final Resource resource = editingDomain.getResourceSet().createResource(semanticResourceURI);
        ePackage = createEPackage(resource);
        resource.save(Maps.newHashMap());

        URI sessionResourceURI = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        final Resource airdResource = editingDomain.getResourceSet().createResource(sessionResourceURI);

        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(airdResource.getURI(), new NullProgressMonitor());
        sessionCreationOperation.execute();
        session = sessionCreationOperation.getCreatedSession();
        session.getTransactionalEditingDomain().getCommandStack().execute(new AddSemanticResourceCommand(session, resource.getURI(), new NullProgressMonitor()));
        diagram = createDiagram(airdResource);

    }

    private TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
        IOperationHistory history = new DefaultOperationHistory();
        return WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain(rset, history);
    }

    private EPackage createEPackage(final Resource resource) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
                resource.getContents().add(ePackage);
            }
        });
        return (EPackage) resource.getContents().get(0);
    }

    private DSemanticDiagram createDiagram(final Resource airdResource) {
        final DSemanticDiagram diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        diagram.setTarget(ePackage);

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                airdResource.getContents().add(diagram);
            }
        });
        return diagram;
    }

    public void testSingleNotification() throws Exception {
        final ToolFilterDescriptionListener listener = new ToolFilterDescriptionListener(session.getInterpreter(), createToolFilterDescriptionOnDiagram("name"), diagram);

        final Runnable mock = createMock(Runnable.class);
        listener.setUpdateRunnable(mock);

        /* start recording */
        mock.run();
        mock.run();
        replay(mock);
        /* stop recording */
        editingDomain.addResourceSetListener(listener);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                diagram.setName("new name");
            }
        });
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                diagram.setName("quick name");
            }
        });
        verify(mock);
    }

    public void testMultipleNotifications() throws Exception {
        final ToolFilterDescriptionListener listener = new ToolFilterDescriptionListener(session.getInterpreter(), createToolFilterDescriptionOnDiagram("name", "info"), diagram);

        final Runnable mock = createMock(Runnable.class);
        listener.setUpdateRunnable(mock);

        /* start recording */
        mock.run();
        /*
         * runnable should be called only one time even with several
         * modifications
         */
        replay(mock);
        /* stop recording */
        editingDomain.addResourceSetListener(listener);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                diagram.setName("new name");
                diagram.setName("this is an annoucement");
            }
        });
        verify(mock);
    }

    public void testAddRemoveListener() throws Exception {
        final ToolFilterDescriptionListener listener = new ToolFilterDescriptionListener(session.getInterpreter(), createToolFilterDescriptionOnDiagram("name"), diagram);

        final Runnable mock = createMock(Runnable.class);
        listener.setUpdateRunnable(mock);

        /* start recording */
        mock.run();
        /*
         * runnable should be called only one time as we should not listen the
         * second notification
         */
        replay(mock);
        /* stop recording */
        editingDomain.addResourceSetListener(listener);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                diagram.setName("dirty name");
            }
        });
        editingDomain.removeResourceSetListener(listener);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                diagram.setName("another name");
            }
        });
        verify(mock);
    }

    private ToolFilterDescription createToolFilterDescriptionOnDiagram(String... featureNames) {
        ToolFilterDescription filterDescription = ToolFactory.eINSTANCE.createToolFilterDescription();
        filterDescription.setElementsToListen("aql:self");
        filterDescription.setPrecondition("");

        for (final String featureName : featureNames) {
            FeatureChangeListener featureChangeListener = ToolFactory.eINSTANCE.createFeatureChangeListener();
            featureChangeListener.setDomainClass("DDiagram");
            featureChangeListener.setFeatureName(featureName);
            filterDescription.getListeners().add(featureChangeListener);
        }
        return filterDescription;
    }

    @Override
    protected void tearDown() throws Exception {

        session.close(new NullProgressMonitor());
        editingDomain.dispose();

        editingDomain = null;
        session = null;
        ePackage = null;
        diagram = null;

        super.tearDown();
    }

}

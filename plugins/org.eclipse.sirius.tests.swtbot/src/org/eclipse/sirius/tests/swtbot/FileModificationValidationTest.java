/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.IFileModificationValidator;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemChildrenNumberCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * Test class for edge shape when the connected port are collapsed.
 * 
 * @author smonnier
 */
public class FileModificationValidationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "TC1481";

    private static final String REPRESENTATION_NAME = "TC1481";

    private static final String MODEL = "tc1481.ecore";

    private static final String MODEL_V2 = "tc1481_v2.ecore";

    private static final String SESSION_FILE = "tc1481.aird";

    private static final String VSM_FILE = "tc1481.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeStabilityOnPortCollapsing/";

    private static final String FILE_DIR = "/";

    private final List<Resource> resources = new ArrayList<Resource>();

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        Session openedSession = localSession.getOpenedSession();

        assertThat("Opened session not found", openedSession, notNullValue());

        resources.addAll(openedSession.getAllSessionResources());
        assertEquals("Number of loaded aird unexpected", 1, resources.size());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testFileModificationValidationReadOnly() throws Exception {

        final List<IFile> expected = new ArrayList<>();
        final Collection<Resource> analysisResources = localSession.getOpenedSession().getAllSessionResources();
        for (final Resource resource : analysisResources) {
            final IFile file = WorkspaceSynchronizer.getFile(resource);
            final ResourceAttributes attributes = new ResourceAttributes();
            attributes.setReadOnly(true);
            file.setResourceAttributes(attributes);
            assertTrue("This file must be in read only mode.", file.isReadOnly());
            assertFalse("This file must be in read only mode.", new File(file.getFullPath().toString()).canWrite());
            expected.add(file);
        }

        UIThreadRunnable.asyncExec(new VoidResult() {
            private IFileModificationValidator mock;

            public void run() {

                mock = createMock(IFileModificationValidator.class);
                ResourceSetSync.getOrInstallResourceSetSync(localSession.getOpenedSession().getTransactionalEditingDomain()).addFileModificationValidator(mock);

                // start recording

                expect(mock.validateEdit(eq(expected))).andReturn(new Status(0, Activator.PLUGIN_ID, ""));

                replay(mock);
                // stop recording

                editor.drag("p1", 350, 350);

                verify(mock);
                ResourceSetSync.getOrInstallResourceSetSync(localSession.getOpenedSession().getTransactionalEditingDomain()).removeFileModificationValidator(mock);
            }

        });

        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Read-only File Encountered"));
        bot.button("Yes").click();

        localSession.getOpenedSession().save(new NullProgressMonitor());
    }

    /**
     * This test validates session refresh after a read-only semantic resource
     * has been modified out of Eclipse.
     * 
     * The scenario is the following: <br>
     * - Open a session <br>
     * - Copy an alternative version of the semantic resource (with an extra
     * {@link EPackage}) instead of the current one. (This step simulate a
     * modification out of Eclipse). <br>
     * - Set the semantic resource file as Read-Only <br>
     * - Refresh the Modeling Project <br>
     * - Validate that the session has been properly refresh (Check for extra
     * {@link EPackage} existence)
     * 
     * VP-3627
     * 
     * @throws Exception
     */
    public void testFileModification_DirtyReadOnlyFile() throws Exception {
        // Initialization
        localSession.getOpenedSession().save(new NullProgressMonitor());
        Assert.assertEquals("Only 1 semantic resource is expected", 1, localSession.getOpenedSession().getSemanticResources().size());
        Resource semanticResource = Iterables.getOnlyElement(localSession.getOpenedSession().getSemanticResources());

        // Replace semantic resource file
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + MODEL_V2, getProjectName() + File.separator + MODEL, false);

        // Set semantic resource file to read only
        Assert.assertEquals("Only 1 semantic resource is expected", 1, localSession.getOpenedSession().getSemanticResources().size());
        semanticResource = Iterables.getOnlyElement(localSession.getOpenedSession().getSemanticResources());
        URI resourceURI = semanticResource.getURI();
        ResourceSet resourceSet = semanticResource.getResourceSet();
        Assert.assertNotNull("ResourceSet should not been null", resourceSet);
        HashMap<String, Boolean> attributes = Maps.<String, Boolean> newHashMap();
        attributes.put(URIConverter.ATTRIBUTE_READ_ONLY, true);
        resourceSet.getURIConverter().setAttributes(resourceURI, attributes, null);

        SWTBotTreeItem treeItem = localSession.getSemanticResourceNode(localSession.getSessionResource()).expandNode("p");
        Assert.assertEquals("Only 4 EPackage and 1 representation should be visible before refresh", 5, treeItem.getItems().length);

        // Refresh session
        SWTBotTreeItem projectTreeItem = localSession.getSWTBotTree().getTreeItem(getProjectName());
        projectTreeItem.select();
        SWTBotUtils.clickContextMenu(projectTreeItem, "Refresh");

        // Wait for refresh end
        treeItem = localSession.getSemanticResourceNode(localSession.getSessionResource()).expandNode("p");
        bot.waitUntil(new TreeItemChildrenNumberCondition(treeItem, 6, true));

        // Validate semantic resource
        Assert.assertEquals("Only 1 semantic resource is expected", 1, localSession.getOpenedSession().getSemanticResources().size());
        semanticResource = Iterables.getOnlyElement(localSession.getOpenedSession().getSemanticResources());
        Assert.assertEquals("Only 1 root is expected", 1, semanticResource.getContents().size());
        Assert.assertTrue("The root should be an EPackage", Iterables.getOnlyElement(semanticResource.getContents()) instanceof EPackage);
        EPackage rootPackage = (EPackage) Iterables.getOnlyElement(semanticResource.getContents());
        Assert.assertEquals("5 sub package are expected", 5, rootPackage.getESubpackages().size());
        localSession.getOpenedSession().save(new NullProgressMonitor());
    }
}

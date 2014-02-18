/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.business;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.tests.swtbot.support.api.business.UINewRepresentationBuilderFlow.ItemChoice;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILocalSessionBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionClosedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionSavedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemExpanded;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ViewpointSelectionDialog;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * Object to do graphical operations on sessions.
 * 
 * @author dlecan
 */
public class UILocalSession {

    private static final String SEPARATOR = "/";

    /** The UIResource associated to the model resource. */
    protected UIResource modelResource;

    /** The Session associated to this UILocalSession. */
    protected Session openedSession;

    /** The SWTBot bot used buy this LocalSession. */
    protected final SWTWorkbenchBot bot;

    /**
     * The UIResource associated to this Session.
     */
    protected final UIResource sessionResource;

    private SWTBotView modelContentView;

    /**
     * Default constructor, only visible for subclasses.
     * 
     * @param sessionResource
     *            Session resource
     */
    public UILocalSession(final UIResource sessionResource) {
        this.sessionResource = sessionResource;
        this.modelResource = null;
        bot = new SWTWorkbenchBot();
        initSession();
    }

    /**
     * Constructor.
     * 
     * @param modelResource
     *            Model resource
     * @param sessionResource
     *            Session resource
     */
    public UILocalSession(final UIResource modelResource, final UIResource sessionResource) {
        this.modelResource = modelResource;
        this.sessionResource = sessionResource;
        bot = new SWTWorkbenchBot();
        initSession();
    }

    /**
     * Constructor.
     * 
     * @param modelResource
     *            Model resource
     * @param sessionResource
     *            Session resource
     * @param openedSession
     *            Session
     */
    public UILocalSession(final UIResource modelResource, final UIResource sessionResource, final Session openedSession) {
        this(modelResource, sessionResource);
        this.openedSession = openedSession;
    }

    /**
     * Init the openedSession field from the sessionResource UIResource.
     */
    private void initSession() {
        URI sessionResourceURI = URI.createPlatformResourceURI(sessionResource.getFullPath(), true);
        openedSession = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        if (modelResource == null && !openedSession.getSemanticResources().isEmpty()) {
            Resource firstSemanticResource = openedSession.getSemanticResources().iterator().next();
            String path = sessionResource.getProject().getName();
            for (int i = 1; i < firstSemanticResource.getURI().segmentCount() - 1; i++) {
                path += UILocalSession.SEPARATOR + firstSemanticResource.getURI().segment(i);
            }
            modelResource = new UIResource(sessionResource.getProject(), path, firstSemanticResource.getURI().lastSegment());
        }
    }

    /**
     * Gets the opened session.
     * 
     * @return the openedSession. May be <code>null</code>.
     */
    public Session getOpenedSession() {
        return openedSession;
    }

    /**
     * Select "Local Session View".
     * 
     * @return Current {@link UILocalSession}
     */
    public UILocalSession selectView() {
        modelContentView = bot.viewById(IModelExplorerView.ID);
        modelContentView.setFocus();
        return this;
    }

    /**
     * Get ui tree node of semantic resource.
     * 
     * @param uiResource
     *            UI resource we want to get in local session.
     * @return Corresponding {@link SWTBotTreeItem}
     */
    public SWTBotTreeItem getSemanticResourceNode(final UIResource uiResource) {
        SWTBotTreeItem semanticResourceNode = null;
        SWTBotTreeItem rootTreeItem = getRootSessionTreeItem().expand();
        if (new URIQuery(this.openedSession.getSessionResource().getURI()).isInMemoryURI()) {
            // transient session case
            semanticResourceNode = rootTreeItem.expand();
        } else {
            if (isModelingProject()) {
                semanticResourceNode = rootTreeItem;
                if (uiResource.getNodePath() != null && !uiResource.getNodePath().isEmpty()) {
                    for (int i = 0; i < uiResource.getNodePath().size(); i++) {
                        String pathSegment = uiResource.getNodePath().get(i);
                        semanticResourceNode = semanticResourceNode.getNode(pathSegment).expand();
                    }
                }
            } else {
                semanticResourceNode = getRepresentationsFileTreeItem();
            }
            semanticResourceNode = semanticResourceNode.getNode(getSemanticResourceNodeLabel(uiResource)).expand();
        }
        return semanticResourceNode;
    }

    /**
     * Creation new diagram.<br/>
     * Equivalent to call
     * <code>newRepresentation(String, UIDiagramRepresentation.class)</code>.
     * 
     * @param clickedRepresentationName
     *            Representation name where to in contextual menu.
     * @return UI representation builder
     */
    public ItemChoice<UIDiagramRepresentation> newDiagramRepresentation(final String clickedRepresentationName) {
        return new UINewRepresentationBuilder<UIDiagramRepresentation>(clickedRepresentationName, UIDiagramRepresentation.class);
    }

    /**
     * Creation new diagram.<br/>
     * Equivalent to call
     * <code>newRepresentation(String, UIDiagramRepresentation.class)</code>.
     * 
     * @param clickedRepresentationName
     *            Representation name where to in contextual menu.
     * @return UI representation builder
     */
    public ItemChoice<UITableRepresentation> newTableRepresentation(final String clickedRepresentationName) {
        return new UINewRepresentationBuilder<UITableRepresentation>(clickedRepresentationName, UITableRepresentation.class);
    }

    /**
     * Creation new table.<br/>
     * Equivalent to call
     * <code>newRepresentation(String, UIDiagramRepresentation.class)</code>.
     * 
     * @param clickedRepresentationName
     *            Representation name where to in contextual menu.
     * @return UI representation builder
     */
    public ItemChoice<UITreeRepresentation> newTreeRepresentation(final String clickedRepresentationName) {
        return new UINewRepresentationBuilder<UITreeRepresentation>(clickedRepresentationName, UITreeRepresentation.class);
    }

    /**
     * Open the representation adding wizard to create a new local semantic
     * resource and add it to the current session or add a existing one.
     * 
     * @return UIAddLocalSemanticResourceWizardUIWrapper a UI wrapper to the
     *         Wizard to add semantic resource
     */
    public UIAddLocalSemanticResourceWizardUIWrapper addLocalSemanticResource() {
        return new UIAddLocalSemanticResourceWizardUIWrapper(this);
    }

    /**
     * Get browser for this local session.
     * 
     * @return Browser to browse local session.
     */
    public UILocalSessionBrowser getLocalSessionBrowser() {
        return new UILocalSessionBrowser(getRepresentationsFileTreeItem());
    }

    private String getLocalSessionNodeLabel() {
        if (isTransient()) {
            return modelResource.getName();
        }
        return sessionResource.getName();
    }

    /**
     * Returns the label associated to the selected semantic node.
     * 
     * @param modelUIResource
     *            a {@link UIResource} corresponding to a semantic resource
     * 
     * @return the label associated to the selected semantic node
     */
    protected String getSemanticResourceNodeLabel(UIResource modelUIResource) {
        String semanticResourceNodeLabel = null;
        if (isTransient()) {
            // transient session case
            semanticResourceNodeLabel = modelUIResource.getName();
        } else if (isFirstSemanticResourceShared()) {
            Resource semanticResource = getCorrespondingResource(modelUIResource, openedSession.getSemanticResources());
            if (semanticResource != null) {
                semanticResourceNodeLabel = modelUIResource.getName() + " - [" + semanticResource.getURI() + "]";
            }
        } else {
            semanticResourceNodeLabel = modelUIResource.getName() + " - [platform:/resource/" + modelResource.getProject().getName() + UILocalSession.SEPARATOR + modelUIResource.getLongName() + "]";
        }
        return semanticResourceNodeLabel;
    }

    private Resource getCorrespondingResource(UIResource modelUIResource, Collection<Resource> resources) {
        Resource correspondingResource = null;
        for (Resource resource : resources) {
            if (modelUIResource.getName().equals(resource.getURI().lastSegment())) {
                correspondingResource = resource;
                break;
            }
        }
        return correspondingResource;
    }

    private boolean isTransient() {
        return this.openedSession != null && this.openedSession.getSessionResource() != null && new URIQuery(this.openedSession.getSessionResource().getURI()).isInMemoryURI();
    }

    private boolean isFirstSemanticResourceShared() {
        boolean isFirstSemanticResourceShared = new URIQuery(openedSession.getSemanticResources().iterator().next().getURI()).isCDOURI();
        return isFirstSemanticResourceShared;
    }

    /**
     * Returns the UIResource associated to this Session.
     * 
     * @return the UIResource associated to this Session
     */
    public UIResource getSessionResource() {
        return sessionResource;
    }

    /**
     * Close <strong>unsaved</strong> session.
     * 
     * @param save
     *            <code>true</code> if session has to be saved before closing.
     */
    public void close(final boolean save) {
        if (isDirty()) {
            close();
            final SWTBotButton button;
            if (save) {
                button = bot.button("Yes");
            } else {
                button = bot.button("No");
            }
            bot.waitUntil(new ItemEnabledCondition(button));
            SWTBotShell saveShell = bot.shell("Save");
            button.click();
            bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses(saveShell));

            bot.waitUntil(new SessionClosedCondition(getOpenedSession()));
        } else {
            closeNoDirty();
        }
    }

    /**
     * Verify that session is dirty.
     * 
     * @return true if session is dirty
     */
    public boolean isDirty() {
        boolean isDirty = false;
        if (openedSession == null || SessionStatus.DIRTY.equals(openedSession.getStatus())) {
            isDirty = true;
        }
        return isDirty;
    }

    /**
     * Close session which is not dirty.
     */
    public void closeNoDirty() {
        Assert.assertFalse("The session is not expected to be dirty", isDirty());
        close();
        bot.waitUntil(new SessionClosedCondition(getOpenedSession()));
    }

    /**
     * Refresh the content of the session displayed in Model Explorer.
     */
    public void refresh() {
        final SWTBotTreeItem rootTreeItem = getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(rootTreeItem, "Refresh");
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Close the current session.
     */
    protected void close() {
        final SWTBotTreeItem rootTreeItem = getRootSessionTreeItem().select();
        if (getOpenedSession().isOpen()) {
            if (isModelingProject()) {
                SWTBotUtils.clickContextMenu(rootTreeItem, "Close Project");
                SWTBotUtils.waitAllUiEvents();
            } else {
                SWTBotUtils.clickContextMenu(rootTreeItem, "Close");
                SWTBotUtils.waitAllUiEvents();
            }
        }
    }

    /**
     * Close session and discard changes.
     */
    public void closeAndDiscardChanges() {
        close(false);
    }

    /**
     * Save session.
     * 
     * @return Current {@link UILocalSession}
     */
    public UILocalSession save() {
        final SWTBotTreeItem rootTreeItem = getRootSessionTreeItem().select();
        if (getOpenedSession().getStatus() == SessionStatus.DIRTY) {
            SWTBotUtils.clickContextMenu(rootTreeItem, "Save");
            SWTBotUtils.waitProgressMonitorClose("Progress Information");
            bot.waitUntil(new SessionSavedCondition(getOpenedSession()), 2 * SWTBotPreferences.TIMEOUT);
        }
        return this;
    }

    /**
     * Get {@link SWTBotTree} for the current local session.
     * 
     * @return Current {@link SWTBotTree}
     */
    public SWTBotTree getSWTBotTree() {
        return modelContentView.bot().tree();
    }

    /**
     * Get root of session tree.
     * 
     * @return Root of session tree.
     */
    public SWTBotTreeItem getRootSessionTreeItem() {
        selectView();
        SWTBotTreeItem treeItem = null;
        if (isModelingProject()) {
            treeItem = sessionResource.getProject().getProjectTreeItem();
        } else {
            treeItem = getRepresentationsFileTreeItem();
        }
        return treeItem;
    }

    /**
     * Get the {@link SWTBotTreeItem} representing the representations file.
     * 
     * @return the {@link SWTBotTreeItem} representing the representations file
     */
    public SWTBotTreeItem getRepresentationsFileTreeItem() {
        SWTBotTreeItem treeItem = null;
        selectView();
        final SWTBotTree modelContentTree = getSWTBotTree();

        bot.waitUntil(new ItemEnabledCondition(modelContentTree));

        SWTBotTreeItem[] allItems = modelContentTree.getAllItems();
        String localSessionNodeLabel = getLocalSessionNodeLabel();

        treeItem = findAndExpand(allItems, localSessionNodeLabel);
        return treeItem.expand();
    }

    /**
     * Tells if this session is a Modeling Project.
     * 
     * @return true if it is a Modeling Project
     */
    public boolean isModelingProject() {
        boolean isModelingProject = false;
        String projectName = sessionResource.getProject().getName();
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        try {
            isModelingProject = project.exists() && project.hasNature(ModelingProject.NATURE_ID);
        } catch (CoreException e) {
            isModelingProject = false;
        }
        return isModelingProject;
    }

    private SWTBotTreeItem findAndExpand(SWTBotTreeItem[] allItems, String localSessionNodeLabel) {
        SWTBotTreeItem treeItem = null;
        if (localSessionNodeLabel == null) {
            return treeItem;
        }

        String dirtylocalSessionNodeLabel = "*" + localSessionNodeLabel;
        for (SWTBotTreeItem item : allItems) {
            String text = item.getText();

            if (item.isEnabled() && localSessionNodeLabel.equals(text) || dirtylocalSessionNodeLabel.equals(text)) {
                treeItem = item;
            } else {
                boolean oldExpanded = item.isExpanded();
                item.expand();
                treeItem = findAndExpand(item.getItems(), localSessionNodeLabel);
                if (treeItem == null && !oldExpanded) {
                    item.collapse();
                }
            }
            if (treeItem != null) {
                break;
            }
        }
        return treeItem;
    }

    /**
     * Allow to change viewpoints selection.
     * 
     * This method opens the Viewpoints selection dialog from the contextual
     * menu on the session item.
     * 
     * @param viewpointsToSelect
     *            New viewpoints to select
     * @param viewpointsToDeselect
     *            new viewpoints to deselect
     * @return This
     */
    public UILocalSession changeViewpointSelection(final Set<String> viewpointsToSelect, final Set<String> viewpointsToDeselect) {
        final SWTBotTreeItem rootTreeItem = getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(rootTreeItem, ViewpointSelectionDialog.VIEWPOINT_DIALOG_NAME);
        return changeViewpointSelectionInOpenedSelectionDialog(viewpointsToSelect, viewpointsToDeselect);
    }

    /**
     * Allow to change viewpoints selection.
     * 
     * This method can be used when the selection dialog has been opened
     * elsewhere (from contextual menu, after session creation wizard, ...).
     * 
     * @param viewpointsToSelect
     *            New viewpoints to select
     * @param viewpointsToDeselect
     *            new viewpoints to deselect
     * @return This
     */
    public UILocalSession changeViewpointSelectionInOpenedSelectionDialog(final Set<String> viewpointsToSelect, final Set<String> viewpointsToDeselect) {
        new ViewpointSelectionDialog(bot).selectViewpoint(viewpointsToSelect, viewpointsToDeselect);
        return this;
    }

    /**
     * Export this Modeling Project to a repo and create a Shared Modeling
     * Project.
     * 
     * @return the shared modeling project
     */
    public UILocalSession export() {
        return export(false, true, false);
    }

    /**
     * Export this Modeling Project to a repo and create a Shared Modeling
     * Project.
     * 
     * @param overrideExistingResources
     *            specify if the export can override existing resources
     * @param connectToExportedProject
     *            specify if a shared modeling project must be created
     * @param exportReferencedWorkspaceImages
     *            specify if we must export referenced workspace image
     * @return the shared modeling project, or null if connectToExportedProject
     *         was at false
     */
    public UILocalSession export(boolean overrideExistingResources, boolean connectToExportedProject, boolean exportReferencedWorkspaceImages) {
        return export(overrideExistingResources, connectToExportedProject, exportReferencedWorkspaceImages, "localhost", "2037", "collab");
    }

    /**
     * Export this Modeling Project to a repo and create a Shared Modeling
     * Project.
     * 
     * @param overrideExistingResources
     *            specify if the export can override existing resources
     * @param connectToExportedProject
     *            specify if a shared modeling project must be created
     * @param exportReferencedWorkspaceImages
     *            specify if we must export referenced workspace image
     * @param serverLocation
     *            the server location where to export
     * @param serverPortNumber
     *            the server port number where to export
     * @param repositoryName
     *            the repository name where to export
     * @return the shared modeling project, or null if connectToExportedProject
     *         was at false
     */
    public UILocalSession export(boolean overrideExistingResources, boolean connectToExportedProject, boolean exportReferencedWorkspaceImages, String serverLocation, String serverPortNumber,
            String repositoryName) {
        SWTBotTreeItem projectTreeItem = getSessionResource().getProject().getProjectTreeItem();
        projectTreeItem.select();
        SWTBotUtils.clickContextMenu(projectTreeItem, "Export...");
        bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive("Export"));
        SWTBotShell activeShell = bot.activeShell();
        SWTBotUtils.waitAllUiEvents();
        SWTBot exportWizardBot = activeShell.bot();
        String exportWizardName = "Shared Modeling Project from Local";
        exportWizardBot.text().setText(exportWizardName);
        SWTBotTreeItem treeItem = exportWizardBot.tree().getTreeItem("Sirius");
        bot.waitUntil(new TreeItemExpanded(treeItem, exportWizardName));
        treeItem.getNode(exportWizardName).select();
        exportWizardBot.button("Next >").click();
        if (overrideExistingResources) {
            exportWizardBot.button("Override existing resources").click();
        }
        if (!connectToExportedProject) {
            exportWizardBot.button("Connect to the exported project").click();
        }
        if (!exportReferencedWorkspaceImages) {
            exportWizardBot.checkBox("Export referenced workspace images").click();
        }
        exportWizardBot.button("Next >").click();
        exportWizardBot.text(0).setText(serverLocation);
        exportWizardBot.text(1).setText(serverPortNumber);
        exportWizardBot.text(2).setText(repositoryName);
        exportWizardBot.button("Test connection").click();
        SWTBotButton finishButton = exportWizardBot.button("Finish");
        bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.widgetIsEnabled(finishButton));
        finishButton.click();
        if (connectToExportedProject) {
            bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive("Connect to the Exported Project"), 5 * SWTBotPreferences.TIMEOUT);
            SWTBot connectWizardBot = bot.activeShell().bot();
            finishButton = connectWizardBot.button("Finish");
            bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.widgetIsEnabled(finishButton));
            finishButton.click();
            bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellIsActive(ViewpointSelectionDialog.VIEWPOINT_DIALOG_NAME));
            SWTBotShell viewpointSelectionShell = bot.activeShell();
            SWTBot viewpointSelectionBot = viewpointSelectionShell.bot();
            Collection<Viewpoint> selectedViewpoints = getOpenedSession().getSelectedViewpoints(false);
            for (Viewpoint locallySelectedViewpoint : selectedViewpoints) {
                for (int i = 0; i < viewpointSelectionBot.table().rowCount(); i++) {
                    SWTBotTableItem tableItem = viewpointSelectionBot.table().getTableItem(i);
                    if (locallySelectedViewpoint.getName().equals(tableItem.getText(2))) {
                        viewpointSelectionBot.table().click(i, 0);
                        break;
                    }
                }
            }
            viewpointSelectionBot.button("OK").click();
            bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses(viewpointSelectionShell));
        }
        bot.waitUntil(org.eclipse.swtbot.swt.finder.waits.Conditions.shellCloses(activeShell));
        UIProject sharedModelingProject = new UIProject(getSessionResource().getProject().getName() + ".shared");
        UIResource sharedSessionResource = new UIResource(sharedModelingProject, sharedModelingProject.getName() + "." + SiriusUtil.SESSION_RESOURCE_EXTENSION);
        return new UILocalSession(sharedSessionResource);
    }
}

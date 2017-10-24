/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.airdeditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.sirius.business.internal.resource.AirDResourceImpl;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.AbstractSessionEditorPage;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;
import org.eclipse.sirius.ui.editor.api.pages.PageUpdateCommandBuilder.PageUpdateCommand;
import org.eclipse.ui.PlatformUI;

/**
 * This abstract class provides methods to initialize test context for aird editor extensibility testing.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SessionEditorTestPageProvider {
    /**
     * This enum is used to synchronize the page creation/removal/update conditions between the static call from
     * {@link PageProvider} and the dynamic call from the methods {@link AbstractSessionEditorPage#pageChanged(boolean)}
     * and {@link AbstractSessionEditorPage#resourceSetChanged(ResourceSetChangeEvent)}.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public enum CommandSynchronization {
        VISIBILITY_SYNCHRONIZATION, RESOURCE_SET_CHANGE_SYNCHRONIZATION, BOTH_SYNCHRONIZATION

    }

    public static final String PAGE2_ID = "page2"; // $NON-NLS-N$

    public static final String PAGE3_ID = "page3"; // $NON-NLS-N$

    public static final String PAGE4_ID = "page4"; // $NON-NLS-N$

    private PageProviderRegistry pageRegistry;

    private SessionEditor sessionEditor;

    private List<PageProviderExtension> pageProviders;

    private Map<String, AbstractSessionEditorPage> idToPageMap;

    public SessionEditorTestPageProvider(SessionEditor sessionEditor, Map<String, AbstractSessionEditorPage> idToPageMap, PageProviderRegistry pageRegistry,
            List<PageProviderExtension> pageProviders) {
        this.pageProviders = new ArrayList<PageProviderExtension>();
        this.pageRegistry = pageRegistry;
        this.idToPageMap = idToPageMap;
        this.sessionEditor = sessionEditor;
        this.pageProviders = pageProviders;
    }

    /**
     * Test page that can have its display conditions parameterized as well as the dynamic update it can provides with
     * methods {@link AbstractSessionEditorPage#resourceSetChanged(ResourceSetChangeEvent)} and
     * {@link AbstractSessionEditorPage#pageChanged(boolean)}.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    protected final class AbstractAirdEditorCustomPageExtension extends AbstractSessionEditorPage {
        private PositioningKind positioningKind;

        private String targetPageId;

        private Function<Boolean, Optional<PageUpdateCommand>> updateWhenVisibleSupplier;

        private Supplier<Optional<PageUpdateCommand>> updateWhenChangedSupplier;

        private CommandSynchronization commandSynchronization;

        private AbstractAirdEditorCustomPageExtension(String id, String title, PositioningKind positioningKind, String targetPageId) {
            super(sessionEditor, id, title);
            this.positioningKind = positioningKind;
            this.targetPageId = targetPageId;
        }

        public AbstractAirdEditorCustomPageExtension(String id, String title, PositioningKind positioningKind, String targetPageId,
                Function<Boolean, Optional<PageUpdateCommand>> updateWhenVisibleSupplier, Supplier<Optional<PageUpdateCommand>> updateWhenChangedSupplier,
                CommandSynchronization commandSynchronization) {
            this(id, title, positioningKind, targetPageId);
            this.updateWhenVisibleSupplier = updateWhenVisibleSupplier;
            this.updateWhenChangedSupplier = updateWhenChangedSupplier;
            this.commandSynchronization = commandSynchronization;
        }

        @Override
        public Optional<String> getLocationId() {
            if (commandSynchronization != null && updateWhenChangedSupplier != null
                    && !AirDResourceImpl.class.getName().equals(sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().get(0).getClass().getName())) {
                return Optional.of(updateWhenChangedSupplier.get().get().getReorderCommand().getTargetPageId());
            }
            return targetPageId == null ? Optional.empty() : Optional.of(targetPageId);
        }

        @Override
        public Optional<PositioningKind> getPositioning() {
            if (commandSynchronization != null && updateWhenChangedSupplier != null
                    && !AirDResourceImpl.class.getName().equals(sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().get(0).getClass().getName())) {
                return Optional.of(updateWhenChangedSupplier.get().get().getReorderCommand().getPositioningKind());
            }
            return positioningKind == null ? Optional.empty() : Optional.of(positioningKind);
        }

        @Override
        public Optional<PageUpdateCommand> resourceSetChanged(ResourceSetChangeEvent resourceSetChangeEvent) {
            if ((CommandSynchronization.RESOURCE_SET_CHANGE_SYNCHRONIZATION == commandSynchronization || CommandSynchronization.BOTH_SYNCHRONIZATION == commandSynchronization)
                    && AirDResourceImpl.class.getName().equals(sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().get(0).getClass().getName())) {
                return updateWhenChangedSupplier.get();
            } else {
                return updateWhenChangedSupplier == null ? Optional.empty() : updateWhenChangedSupplier.get();
            }
        }

        @Override
        public Optional<PageUpdateCommand> pageChanged(boolean isVisible) {
            if ((CommandSynchronization.VISIBILITY_SYNCHRONIZATION == commandSynchronization || CommandSynchronization.BOTH_SYNCHRONIZATION == commandSynchronization) && isVisible
                    && !AirDResourceImpl.class.getName().equals(sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().get(0).getClass().getName())) {
                return updateWhenVisibleSupplier.apply(isVisible);
            } else if (commandSynchronization == null) {
                return updateWhenVisibleSupplier == null ? Optional.empty() : updateWhenVisibleSupplier.apply(isVisible);
            }
            return Optional.empty();
        }
    }

    /**
     * Page provider allowing to create a page that can have its display condition parameterized.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public final class PageProviderExtension extends PageProvider {

        private String newPageId;

        private PositioningKind positioningKind;

        private String targetPageId;

        private Function<Boolean, Optional<PageUpdateCommand>> updateWhenVisibleSupplier;

        private Supplier<Optional<PageUpdateCommand>> updateWhenChangedSupplier;

        private CommandSynchronization commandSynchronization;

        public PageProviderExtension(PositioningKind positioningKind, String targetPageId, String newPageId) {
            this.positioningKind = positioningKind;
            this.targetPageId = targetPageId;
            this.newPageId = newPageId;
        }

        public PageProviderExtension(PositioningKind positioningKind, String targetPageId, String newPageId, Function<Boolean, Optional<PageUpdateCommand>> updateWhenVisibleSupplier,
                Supplier<Optional<PageUpdateCommand>> updateWhenChangedSupplier, CommandSynchronization commandSynchronization) {
            this(positioningKind, targetPageId, newPageId);
            this.updateWhenVisibleSupplier = updateWhenVisibleSupplier;
            this.updateWhenChangedSupplier = updateWhenChangedSupplier;
            this.commandSynchronization = commandSynchronization;
        }

        @Override
        public boolean provides(String pageId, SessionEditor editor) {
            return this.newPageId.equals(pageId);
        }

        @Override
        public Map<String, Supplier<AbstractSessionEditorPage>> getPages(SessionEditor editor) {
            Map<String, Supplier<AbstractSessionEditorPage>> resultMap = new HashMap<>();
            if (commandSynchronization == null || (commandSynchronization != null
                    && AirDResourceImpl.class.getName().equals(sessionEditor.getSession().getTransactionalEditingDomain().getResourceSet().getResources().get(0).getClass().getName()))) {
                resultMap.put(this.newPageId, () -> {
                    AbstractAirdEditorCustomPageExtension newPage = new AbstractAirdEditorCustomPageExtension(this.newPageId, this.newPageId, positioningKind, targetPageId, updateWhenVisibleSupplier,
                            updateWhenChangedSupplier, commandSynchronization);
                    idToPageMap.put(this.newPageId, newPage);
                    return newPage;
                });
            }
            return resultMap;
        }
    }

    /**
     * Initialize a page provider providing one page.
     * 
     * @param positioningKind
     *            the positioning of the page provided.
     * @param targetPageId
     *            the id of the page taken in consideration when positioning the page provided.
     * @param newPageId
     *            the id of the page produced by the provider.
     */
    public void initOnePageProvider(PositioningKind positioningKind, String targetPageId, String newPageId) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            PageProviderExtension pageProviderExtension = new PageProviderExtension(positioningKind, targetPageId, newPageId);
            pageProviders.add(pageProviderExtension);
            pageRegistry.addPageProvider(pageProviderExtension);
        });
    }

    public void initOnePageProvider(PositioningKind positioningKind, String targetPageId, String newPageId, Function<Boolean, Optional<PageUpdateCommand>> updateWhenVisibleSupplier,
            Supplier<Optional<PageUpdateCommand>> updateWhenChangedSupplier, CommandSynchronization commandSynchronization) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            PageProviderExtension pageProviderExtension = new PageProviderExtension(positioningKind, targetPageId, newPageId, updateWhenVisibleSupplier, updateWhenChangedSupplier,
                    commandSynchronization);
            pageProviders.add(pageProviderExtension);
            pageRegistry.addPageProvider(pageProviderExtension);
        });
    }

    public void initTwoPageProvider(PositioningKind positioningKind1, String pageId1, String newPageId1, PositioningKind positioningKind2, String pageId2, String newPageId2) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            PageProviderExtension pageProviderExtension1 = new PageProviderExtension(positioningKind1, pageId1, newPageId1);
            PageProviderExtension pageProviderExtension2 = new PageProviderExtension(positioningKind2, pageId2, newPageId2);
            pageProviders.add(pageProviderExtension1);
            pageRegistry.addPageProvider(pageProviderExtension1);
            pageProviders.add(pageProviderExtension2);
            pageRegistry.addPageProvider(pageProviderExtension2);
        });
    }

    public void initThreePageProvider(PositioningKind positioningKind1, String pageId1, String newPageId1, PositioningKind positioningKind2, String pageId2, String newPageId2,
            PositioningKind positioningKind3, String pageId3, String newPageId3) {
        PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
            PageProviderExtension pageProviderExtension1 = new PageProviderExtension(positioningKind1, pageId1, newPageId1);
            PageProviderExtension pageProviderExtension2 = new PageProviderExtension(positioningKind2, pageId2, newPageId2);
            PageProviderExtension pageProviderExtension3 = new PageProviderExtension(positioningKind3, pageId3, newPageId3);
            pageProviders.add(pageProviderExtension1);
            pageRegistry.addPageProvider(pageProviderExtension1);
            pageProviders.add(pageProviderExtension2);
            pageRegistry.addPageProvider(pageProviderExtension2);
            pageProviders.add(pageProviderExtension3);
            pageRegistry.addPageProvider(pageProviderExtension3);
        });
    }

}

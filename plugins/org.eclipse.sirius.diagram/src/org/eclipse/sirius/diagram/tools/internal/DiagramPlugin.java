/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.tools.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.tools.api.management.ToolManagement;
import org.eclipse.sirius.diagram.tools.internal.management.ToolManagementRegistryListener;
import org.eclipse.sirius.tools.internal.validation.EValidatorAdapter;
import org.osgi.framework.BundleContext;

/**
 * Sirius Diagram plug-in.
 * 
 * @was-generated
 */
public class DiagramPlugin extends EMFPlugin {

    /** The id. */
    public static final String ID = "org.eclipse.sirius.diagram"; //$NON-NLS-1$

    /**
     * Keep track of the singleton.
     */
    public static final DiagramPlugin INSTANCE = new DiagramPlugin();

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    /**
     * Creates a new plugin.
     * 
     * @was-generated
     */
    public DiagramPlugin() {
        super(new ResourceLocator[0]);
    }

    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getDefault() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipsePlugin {
        /**
         * A map associating a {@link DDiagram} to its {@link ToolManagement}.
         */
        private Map<DDiagram, ToolManagement> toolManagementMap;

        /**
         * Listens to ToolManagement extension registry changes
         */
        private ToolManagementRegistryListener toolManagementRegistryListener;

        /**
         * Creates an instance.
         */
        public Implementation() {
            plugin = this;
        }

        /**
         * Logs an error.
         * 
         * @param error
         *            the error message.
         */
        public void logError(String error) {
            logError(error, null);
        }

        /**
         * Logs an error.
         * 
         * @param error
         *            the error message.
         * @param throwable
         *            the throwable.
         */
        public void logError(String error, Throwable throwable) {
            logMessage(error, throwable, IStatus.ERROR);
        }

        /**
         * Logs an info.
         * 
         * @param message
         *            the info message.
         */
        public void logInfo(String message) {
            logInfo(message, null);
        }

        /**
         * Logs an info.
         * 
         * @param message
         *            the info message.
         * @param throwable
         *            the throwable.
         */
        public void logInfo(String message, Throwable throwable) {
            logMessage(message, throwable, IStatus.INFO);
        }

        /**
         * Logs a warning.
         * 
         * @param message
         *            the warning message.
         */
        public void logWarning(String message) {
            logWarning(message, null);
        }

        /**
         * Logs a warning.
         * 
         * @param message
         *            the warning message.
         * @param throwable
         *            the throwable.
         */
        public void logWarning(String message, Throwable throwable) {
            logMessage(message, throwable, IStatus.WARNING);
        }

        private void logMessage(String message, Throwable throwable, int code) {
            String msg = message;
            if (message == null && throwable != null) {
                msg = throwable.getMessage();
            }

            getLog().log(new Status(code, DiagramPlugin.ID, IStatus.OK, msg, throwable));
            debug(msg, throwable);
        }

        /**
         * @was-generated
         */
        private void debug(String message, Throwable throwable) {
            if (!isDebugging()) {
                return;
            }
            // CHECKSTYLE:OFF
            if (message != null) {
                System.err.println(message);
            }
            if (throwable != null) {
                throwable.printStackTrace();
            }
            // CHECKSTYLE:ON
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);

            // Sets the validator for these model.
            EValidator.Registry.INSTANCE.put(DiagramPackage.eINSTANCE, new EValidatorAdapter());
            EValidator.Registry.INSTANCE.put(DescriptionPackage.eINSTANCE, new EValidatorAdapter());
            EValidator.Registry.INSTANCE.put(ToolPackage.eINSTANCE, new EValidatorAdapter());

            toolManagementRegistryListener = new ToolManagementRegistryListener();
            toolManagementRegistryListener.init();

            toolManagementMap = new HashMap<>();
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            toolManagementRegistryListener.dispose();
            toolManagementMap = null;
            try {
                InstanceScope.INSTANCE.getNode(ID).flush();
            } finally {
                super.stop(context);
            }
        }

        /**
         * Returns the {@link ToolManagement} handling the tool of {@link DDiagram} associated to the given
         * {@link Diagram}.
         * 
         * @param diagram
         *            the diagram from which the {@link ToolManagement} must be retrieved.
         * @return the {@link ToolManagement} handling given {@link Diagram}.
         */
        public ToolManagement getToolManagement(Diagram diagram) {
            DDiagram dDiagram = getDDiagram(diagram);
            if (dDiagram != null) {
                ToolManagement toolManagement = toolManagementMap.get(dDiagram);
                if (toolManagement == null) {
                    toolManagement = new ToolManagement(dDiagram);
                    toolManagementMap.put(dDiagram, toolManagement);
                }
                return toolManagement;
            }
            return null;
        }

        private DDiagram getDDiagram(Diagram diagram) {
            if (diagram != null) {
                try {
                    EObject elt = diagram.getElement();
                    if (elt instanceof DDiagram) {
                        return (DDiagram) elt;
                    }
                } catch (IllegalStateException e) {
                    // Silent catch: this can happen when trying to get the session
                    // on a disposed Eobject
                }
            }
            return null;
        }

        /**
         * Returns the {@link ToolManagement} handling the tool of {@link DDiagram} associated to the given
         * {@link Diagram}.
         * 
         * @param dDiagram
         *            the diagram from which the {@link ToolManagement} must be retrieved.
         * @return the {@link ToolManagement} handling given {@link Diagram}.
         */
        public ToolManagement getToolManagement(DDiagram dDiagram) {
            if (dDiagram != null) {
                ToolManagement toolManagement = toolManagementMap.get(dDiagram);
                if (toolManagement == null) {
                    toolManagement = new ToolManagement(dDiagram);
                    toolManagementMap.put(dDiagram, toolManagement);
                }
                return toolManagement;
            }
            return null;
        }

        /**
         * Removes the {@link ToolManagement} handling given {@link DDiagram}.
         * 
         * @param diagram
         *            the diagram from which the {@link ToolManagement} must be removed.
         * @return the removed {@link ToolManagement} or null if no such element exists for the given diagram.
         */
        public ToolManagement removeToolManagement(DDiagram diagram) {
            ToolManagement toolManagement = toolManagementMap.get(diagram);
            if (toolManagement != null) {
                toolManagementMap.remove(diagram);
            }
            return toolManagement;
        }

        /**
         * Removes the {@link ToolManagement} handling given {@link DDiagram}.
         * 
         * @param diagram
         *            the diagram from which the {@link ToolManagement} must be removed.
         * @return the removed {@link ToolManagement} or null if no such element exists for the given diagram.
         */
        public ToolManagement removeToolManagement(Diagram diagram) {
            if (diagram != null && diagram.getElement() instanceof DDiagram) {
                DDiagram dDiagram = (DDiagram) diagram.getElement();
                return removeToolManagement(dDiagram);
            }
            return null;
        }
    }
}

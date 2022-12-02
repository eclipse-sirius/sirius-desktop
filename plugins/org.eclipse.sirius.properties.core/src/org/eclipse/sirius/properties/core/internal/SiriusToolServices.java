/*******************************************************************************
 * Copyright (c) 2015, 2022 Obeo.
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
package org.eclipse.sirius.properties.core.internal;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.properties.EditSupport;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.sirius.properties.defaultrules.api.DefaultRulesProvider;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * This class contains various services provided by the Sirius UI Properties bundle to the interpreter.
 * 
 * @author sbegaudeau
 */
public class SiriusToolServices {

    /**
     * Executes the operation with the given URI.
     * 
     * @param self
     *            the service invocation target.
     * @param eObject
     *            The EObject to use as the operation's context
     * @param initialCommandUri
     *            the URI of the operation to execute
     * @return the model element on which the tool was executed.
     */
    public EObject executeOperation(SiriusInputDescriptor self, EObject eObject, String initialCommandUri) {
        if (!eObject.eIsProxy()) {
            Session session = new EObjectQuery(eObject).getSession();
            if (session != null) {
                ModelOperation modelOperation = findModelOperation(initialCommandUri, session);
                if (modelOperation != null) {
                    ModelAccessor modelAccessor = session.getModelAccessor();
                    ICommandTask task = new TaskHelper(modelAccessor, SiriusPlugin.getDefault().getUiCallback()).buildTaskFromModelOperation(eObject, modelOperation);
                    SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), "SiriusToolServices#executeOperation"); //$NON-NLS-1$
                    command.getTasks().add(task);
                    try {
                        if (command.canExecute()) {
                            command.execute();
                        }
                    } catch (OperationCanceledException cancel) {
                        // A tool (possibly via a Java service) can request the cancellation of its execution. This is
                        // handled as a rollback by EMF Transaction, but the exception should not propagate above this
                        // point: it's a signal for requesting cancellation/rollback, which at this point is done, but
                        // not an error that the caller should be aware of.
                    } finally {
                        command.dispose();
                    }
                }
            }
        }
        return eObject;
    }

    /**
     * Resolves the actual {@link ModelOperation} to execute given its URI.
     * 
     * @param initialCommandUri
     *            the URI of the operation to search for.
     * @param session
     *            the Sirius session which determines the scope to search into.
     * @return the {@link ModelOperation} instance found at the specified URI, either in one of the VSMs for which at
     *         least one Viewpoint is currently enabled in the session, or from the default ruleset, or
     *         <code>null</code> if no matching operation could be located.
     */
    private ModelOperation findModelOperation(String initialCommandUri, Session session) {
        URI commandResourceURI = URI.createURI(initialCommandUri).trimFragment();
        for (Resource res : getResourcesInScope(session)) {
            if (commandResourceURI.equals(res.getURI())) {
                EObject modelOperationEObject = res.getEObject(URI.createURI(initialCommandUri).fragment());
                if (modelOperationEObject instanceof InitialOperation) {
                    return ((InitialOperation) modelOperationEObject).getFirstModelOperations();
                }
            }
        }
        return null;
    }

    /**
     * Returns all the (VSM-like) resources in which to search for the {@link ModelOperation} to execute.
     * 
     * @param session
     *            the Sirius session.
     * @return all the resources in which to look for the ModelOperation, in order of preference.
     */
    private Set<Resource> getResourcesInScope(Session session) {
        Set<Resource> result = new LinkedHashSet<>();
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
        for (Viewpoint viewpoint : selectedViewpoints) {
            Resource eResource = viewpoint.eResource();
            if (eResource != null) {
                result.add(eResource);
            }
        }
        ViewExtensionDescription defaults = DefaultRulesProvider.INSTANCE.getDefaultRules();
        if (defaults != null && defaults.eResource() != null) {
            result.add(defaults.eResource());
        }
        return result;
    }

    /**
     * Returns the {@link SiriusContext} associated with a {@link SiriusInputDescriptor} (typically the "input" variable
     * of the properties view).
     * 
     * @param sid
     *            a {@link SiriusInputDescriptor} (typically the "input" variable of the properties view).
     * @return the input's full context.
     */
    public SiriusContext context(SiriusInputDescriptor sid) {
        return sid.getFullContext();
    }

    /**
     * Returns the list of {@link SiriusContext} associated with a {@link SiriusInputDescriptor} (typically the "input" variable
     * of the properties view).
     * 
     * @param sid
     *            a {@link SiriusInputDescriptor} (typically the "input" variable of the properties view).
     * @return the list of all contexts of the input
     */
    public List<SiriusContext> getContextSelections(SiriusInputDescriptor sid) {
        return sid.getContextSelections();
    }

    /**
     * Returns the semantic element for the given input descriptor.
     * 
     * @param sid
     *            The input descriptor
     * @return The semantic element for the given input descriptor
     */
    public EObject getSemanticElement(SiriusInputDescriptor sid) {
        return sid.getSemanticElement();
    }

    /**
     * Returns the list of semantic elements for the given input descriptor.
     * 
     * @param sid
     *            The input descriptor
     * @return The list of semantic elements for the given input descriptor
     */
    public List<EObject> getSelectedSemanticElements(SiriusInputDescriptor sid) {
        return sid.getSemanticElements();
    }

    /**
     * Returns all the semantic elements for the given input descriptor.
     * 
     * @param sid
     *            The input descriptor
     * @return The semantic element for the given input descriptor
     */
    public Set<EObject> getAllSemanticElements(SiriusInputDescriptor sid) {
        return sid.getAllSemanticElements();
    }

    /**
     * Returns the original selection for the given input descriptor.
     * 
     * @param sid
     *            The input descriptor
     * @return The original selection for the given input descriptor
     */
    public Object getOriginalSelection(SiriusInputDescriptor sid) {
        return sid.getOriginalSelection();
    }

    /**
     * Returns the list of original selections for the given input descriptor.
     * 
     * @param sid
     *            The input descriptor
     * @return the list of original selections for the given input descriptor
     */
    public List<Object> getSelectedOriginalSelections(SiriusInputDescriptor sid) {
        return sid.getOriginalSelections();
    }

    /**
     * Returns the Sirius session associated to a given context.
     * 
     * @param ctx
     *            a Sirius context.
     * 
     * @return the Sirius session associated to a given context.
     */
    public Session session(SiriusContext ctx) {
        Option<Session> s = ctx.getSession();
        if (s.some()) {
            return s.get();
        } else {
            return null;
        }
    }

    /**
     * Returns the Sirius representation associated to a given context.
     * 
     * @param ctx
     *            a Sirius context.
     * 
     * @return the Sirius representation associated to a given context.
     */
    public DRepresentation representation(SiriusContext ctx) {
        Option<DRepresentation> r = ctx.getDRepresentation();
        if (r.some()) {
            return r.get();
        } else {
            return null;
        }
    }

    /**
     * Returns the Sirius {@link DSemanticDecorator} associated to a given context.
     * 
     * @param ctx
     *            a Sirius context.
     * 
     * @return the Sirius {@link DSemanticDecorator} associated to a given context.
     */
    public DSemanticDecorator semanticDecorator(SiriusContext ctx) {
        return ctx.getSemanticDecorator();
    }

    /**
     * Returns the main semantic element associated to a given context.
     * 
     * @param ctx
     *            a Sirius context.
     * 
     * @return the main semantic element associated to a given context.
     */
    public EObject mainSemanticElement(SiriusContext ctx) {
        Option<EObject> target = ctx.getMainSemanticElement();
        if (target.some()) {
            return target.get();
        } else {
            return null;
        }
    }

    /**
     * Returns all the semantic elements associated to a given context.
     * 
     * @param ctx
     *            a Sirius context.
     * 
     * @return all the semantic elements associated to a given context.
     */
    public List<EObject> allSemanticElements(SiriusContext ctx) {
        Option<List<EObject>> elements = ctx.getAdditionalSemanticElements();
        if (elements.some()) {
            return elements.get();
        } else {
            return null;
        }
    }

    /**
     * Returns a helper with EMF Edit-related operations on a given element.
     * 
     * @param input
     *            a {@link SiriusInputDescriptor} (typically the "input" variable of the properties view).
     * @param self
     *            the target semantic element on which the helper should operator.
     * @return an instance of EditSupport bounnd to the specified semantic element.
     */
    public EditSupport emfEditServices(SiriusInputDescriptor input, EObject self) {
        EditSupportSpec ess = new EditSupportSpec(input.getFullContext(), self);
        return ess;
    }
}

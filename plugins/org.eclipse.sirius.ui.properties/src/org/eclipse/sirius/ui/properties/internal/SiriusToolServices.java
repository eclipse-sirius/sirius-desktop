/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.sirius.properties.EditSupport;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.properties.internal.tabprovider.SiriusTabDescriptorProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * This class contains various services provided by the Sirius UI Properties
 * bundle to the interpreter.
 * 
 * @author sbegaudeau
 */
public class SiriusToolServices {

    private final EditingDomainServices editServices = new EditingDomainServices();

    /**
     * Returns the image representing the given EObject.
     * 
     * @param eObject
     *            The EObject
     * @return The image representing the given EObject or <code>null</code> if
     *         none could be found
     */
    public Object eefViewImage(EObject eObject) {
        return this.editServices.getLabelProviderImage(eObject);
    }

    /**
     * Returns the text representing the given EObject.
     * 
     * @param object
     *            The object
     * @return The text representing the given EObject or <code>null</code> if
     *         none could be found
     */
    public String eefViewText(Object object) {
        if (object instanceof EObject) {
            return this.editServices.getLabelProviderText((EObject) object);
        }
        return String.valueOf(object);
    }

    /**
     * Returns the text representing the given EObject.
     * 
     * @param sid
     *            a {@link SiriusInputDescriptor} (typically the "input"
     *            variable of the properties view).
     * @param object
     *            The object
     * @return The text representing the given EObject or <code>null</code> if
     *         none could be found
     */
    public String eefViewText(Object object, SiriusInputDescriptor sid) {
        String result = String.valueOf(object);
        if (object instanceof EObject) {
            if (mainSemanticElement(sid.getFullContext()).equals(object)) {
                result = Messages.SiriusToolServices_MainTabLabel;
            } else {
                result = this.editServices.getLabelProviderText((EObject) object);
            }
        }
        return result;
    }

    /**
     * Returns the text representing the given EStructuralFeature.
     * 
     * @param eObject
     *            The EObject
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return The text representing the given EStructuralFeature or
     *         <code>null</code> if none could be found
     */
    public Object eefViewText(EObject eObject, EStructuralFeature eStructuralFeature) {
        String result = this.editServices.getPropertyDescriptorDisplayName(eObject, eStructuralFeature.getName());
        if (result == null || result.trim().isEmpty()) {
            result = this.editServices.getLabelProviderText(eStructuralFeature);
        }
        if (result == null || result.trim().isEmpty()) {
            result = eStructuralFeature.getName();
        }
        return result;
    }

    /**
     * Returns the choices of all the values that the given EStructuralFeature
     * may take one.
     * 
     * @param eObject
     *            The EObject
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return the choices of all the values or <code>null</code> if none could
     *         be found
     */
    public Collection<?> eefViewChoiceOfValues(EObject eObject, EStructuralFeature eStructuralFeature) {
        return this.editServices.getPropertyDescriptorChoiceOfValues(eObject, eStructuralFeature.getName());
    }

    /**
     * Returns if the given EObject is multiline.
     * 
     * @param eObject
     *            The EObject
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return True if the given EObject is multiline otherwise false
     */
    public boolean eefViewMultiline(EObject eObject, EStructuralFeature eStructuralFeature) {
        return this.editServices.isPropertyDescriptorMultiLine(eObject, eStructuralFeature.getName());
    }

    /**
     * Returns the description of the given EObject.
     * 
     * @param eObject
     *            The EObject
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return The description associated to the given EStructuralFeature or
     *         <code>null</code> if none could be found
     */
    public String eefViewDescription(EObject eObject, EStructuralFeature eStructuralFeature) {
        return this.editServices.getPropertyDescriptorDescription(eObject, eStructuralFeature.getName());
    }

    /**
     * Get the category associated to a given EObject.
     * 
     * @param eObject
     *            The EObject
     * @return The category associated to a given EObject, if none is defined
     *         the default one is returned
     */
    public String eefViewCategory(EObject eObject) {
        String result = Messages.SiriusToolServices_DefaultCategoryName;
        if (eObject instanceof EEFViewCategory) {
            result = ((EEFViewCategory) eObject).getCategory();
        }
        return result;
    }

    /**
     * Get the categories existing for all the features of a given EObject. If
     * no category is defined a default one is created.
     * 
     * @param eObject
     *            The EObject
     * @return A list of EObjects representing the different categories ordered
     *         by alphabetical order and with the default one at the end
     */
    public Collection<EEFViewCategory> eefViewCategories(EObject eObject) {
        List<EEFViewCategory> categories = new ArrayList<EEFViewCategory>();

        // Get all the visible features associated to an eObject
        Collection<EStructuralFeature> features = getVisibleEStructuralFeatures(eObject);

        // Get all the categories defined in the genmodel for all the features
        // of the given EObject
        Set<String> propertyDescriptorCategories = new HashSet<String>();
        String defaultCategoryName = Messages.SiriusToolServices_DefaultCategoryName;
        for (EStructuralFeature feature : features) {
            String category = this.editServices.getPropertyDescriptorCategory(eObject, feature.getName(), defaultCategoryName);
            if (category != null) {
                propertyDescriptorCategories.add(category);
            } else {
                propertyDescriptorCategories.add(defaultCategoryName);
            }
        }

        // Sort the categories by alphabetical order
        List<String> sortedPropertyDescriptorCategories = new ArrayList<String>(propertyDescriptorCategories);
        Collections.sort(sortedPropertyDescriptorCategories);

        // Put the default category at the end of the list
        if (sortedPropertyDescriptorCategories.contains(defaultCategoryName)) {
            sortedPropertyDescriptorCategories.remove(defaultCategoryName);
            sortedPropertyDescriptorCategories.add(defaultCategoryName);
        }

        // Create the EObjects associated to the visible categories
        for (String category : sortedPropertyDescriptorCategories) {
            if (eObject instanceof InternalEObject) {
                EEFViewCategory eefViewCategory = new EEFViewCategory((InternalEObject) eObject, category);
                categories.add(eefViewCategory);
            }
        }

        return categories;
    }

    /**
     * Compute all the visible features (not derived, not transient, not a
     * containment reference) associated to a given EObject.
     * 
     * @param eObject
     *            The EObject
     * @return List of visible features.
     */
    private Collection<EStructuralFeature> getVisibleEStructuralFeatures(EObject eObject) {
        List<EStructuralFeature> visibleFeaturesCache = new ArrayList<EStructuralFeature>();
        for (EStructuralFeature eStructuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
            if (!eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !(eStructuralFeature instanceof EReference && ((EReference) eStructuralFeature).isContainment())) {
                visibleFeaturesCache.add(eStructuralFeature);
            }
        }

        return visibleFeaturesCache;
    }

    /**
     * Get all the visible structural features associated to a category.
     * 
     * @param eObject
     *            The category
     * @return List of structural features which are visible in the given
     *         category
     */
    public Collection<EStructuralFeature> eefViewEStructuralFeatures(EObject eObject) {
        List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
        // Get all the features associated to the eObject and filtered by
        // category
        if (eObject instanceof EEFViewCategory) {
            EEFViewCategory category = (EEFViewCategory) eObject;
            String groupCategory = category.getCategory();
            for (EStructuralFeature eStructuralFeature : getVisibleEStructuralFeatures(category.getWrappedEObject())) {
                String featureCategory = this.editServices.getPropertyDescriptorCategory(category.getWrappedEObject(), eStructuralFeature.getName(), Messages.SiriusToolServices_DefaultCategoryName);
                if (groupCategory.equals(featureCategory)) {
                    result.add(eStructuralFeature);
                }
            }
        }
        return result;
    }

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
                    ICommandTask task = new TaskHelper(modelAccessor, SiriusEditPlugin.getPlugin().getUiCallback()).buildTaskFromModelOperation(eObject, modelOperation);
                    SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), "SiriusToolServices#executeOperation"); //$NON-NLS-1$
                    command.getTasks().add(task);
                    session.getTransactionalEditingDomain().getCommandStack().execute(command);
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
     * @return the {@link ModelOperation} instance found at the specified URI,
     *         either in one of the VSMs for which at least one Viewpoint is
     *         currently enabled in the session, or from the default ruleset, or
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
     * Returns all the (VSM-like) resources in which to search for the
     * {@link ModelOperation} to execute.
     * 
     * @param session
     *            the Sirius session.
     * @return all the resources in which to look for the ModelOperation, in
     *         order of preference.
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
        ViewExtensionDescription defaults = SiriusTabDescriptorProvider.getDefaultRules();
        if (defaults != null && defaults.eResource() != null) {
            result.add(defaults.eResource());
        }
        return result;
    }

    /**
     * Returns the value of the given structural feature for the given object.
     * 
     * @param eObject
     *            The EObject
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return The value
     */
    public Object eGet(EObject eObject, EStructuralFeature eStructuralFeature) {
        return eObject.eGet(eStructuralFeature);
    }

    /**
     * Sets the value of the given structural feature for the given object.
     * 
     * @param eObject
     *            The EObject
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @param value
     *            The new value
     * @return the model element on which the service was executed.
     */
    public EObject eSet(EObject eObject, EStructuralFeature eStructuralFeature, Object value) {
        Object finalValue = value;
        if (eStructuralFeature instanceof EAttribute && value instanceof String) {
            finalValue = EcoreUtil.createFromString(((EAttribute) eStructuralFeature).getEAttributeType(), (String) value);
        }
        eObject.eSet(eStructuralFeature, finalValue);
        return eObject;
    }

    /**
     * Returns the {@link SiriusContext} associated with a
     * {@link SiriusInputDescriptor} (typically the "input" variable of the
     * properties view).
     * 
     * @param sid
     *            a {@link SiriusInputDescriptor} (typically the "input"
     *            variable of the properties view).
     * @return the input's full context.
     */
    public SiriusContext context(SiriusInputDescriptor sid) {
        return sid.getFullContext();
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
     * Returns all the semantic elements for the given input descriptor.
     * 
     * @param sid
     *            The input descriptor
     * @return The semantic element for the given input descriptor
     */
    public Collection<EObject> getAllSemanticElements(SiriusInputDescriptor sid) {
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
     * Returns the Sirius {@link DSemanticDecorator} associated to a given
     * context.
     * 
     * @param ctx
     *            a Sirius context.
     * 
     * @return the Sirius {@link DSemanticDecorator} associated to a given
     *         context.
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
    public Collection<EObject> allSemanticElements(SiriusContext ctx) {
        Option<Collection<EObject>> elements = ctx.getAdditionalSemanticElements();
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
     *            a {@link SiriusInputDescriptor} (typically the "input"
     *            variable of the properties view).
     * @param self
     *            the target semantic element on which the helper should
     *            operator.
     * @return an instance of EditSupport bounnd to the specified semantic
     *         element.
     */
    public EditSupport emfEditServices(SiriusInputDescriptor input, EObject self) {
        EditSupportSpec ess = new EditSupportSpec(input.getFullContext(), self);
        return ess;
    }
}
